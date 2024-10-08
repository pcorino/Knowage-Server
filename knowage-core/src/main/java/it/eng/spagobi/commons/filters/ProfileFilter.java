/*
 * Knowage, Open Source Business Intelligence suite
 * Copyright (C) 2016 Engineering Ingegneria Informatica S.p.A.
 *
 * Knowage is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Knowage is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.eng.spagobi.commons.filters;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eng.knowage.privacymanager.LoginEventBuilder;
import it.eng.knowage.privacymanager.PrivacyManagerClient;
import it.eng.spago.base.Constants;
import it.eng.spago.security.DefaultCipher;
import it.eng.spago.security.IEngUserProfile;
import it.eng.spagobi.commons.bo.SessionUserProfileBuilder;
import it.eng.spagobi.commons.bo.UserProfile;
import it.eng.spagobi.commons.bo.UserProfileUtility;
import it.eng.spagobi.commons.services.LoginActionByToken;
import it.eng.spagobi.commons.services.LoginActionWeb;
import it.eng.spagobi.commons.services.LoginModule;
import it.eng.spagobi.commons.utilities.ChannelUtilities;
import it.eng.spagobi.commons.utilities.GeneralUtilities;
import it.eng.spagobi.services.common.SsoServiceFactory;
import it.eng.spagobi.services.common.SsoServiceInterface;
import it.eng.spagobi.services.security.bo.SpagoBIUserProfile;
import it.eng.spagobi.services.security.service.ISecurityServiceSupplier;
import it.eng.spagobi.services.security.service.SecurityServiceSupplierFactory;
import it.eng.spagobi.tenant.Tenant;
import it.eng.spagobi.tenant.TenantManager;
import it.eng.spagobi.user.UserProfileManager;

/**
 * @author Zerbetto (davide.zerbetto@eng.it)
 *
 *         This filter tries to build the user profile object, using the user identifier
 */

public class ProfileFilter implements Filter {

	private static final Logger LOGGER = LogManager.getLogger(ProfileFilter.class);

	@Override
	public void destroy() {
		// do nothing
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			if (request instanceof HttpServletRequest) {
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				HttpSession session = httpRequest.getSession();

				// @formatter:off
				// TODO ML				RequestContainer requestContainer = (RequestContainer) session
				// TODO ML						.getAttribute(Constants.REQUEST_CONTAINER);
				// TODO ML				if (requestContainer == null) {
				// TODO ML					// RequestContainer does not exists yet (maybe it is the
				// TODO ML					// first call to Spago)
				// TODO ML					// initializing Spago objects (user profile object must
				// TODO ML					// be put into PermanentContainer)
				// TODO ML					requestContainer = new RequestContainer();
				// TODO ML					SessionContainer sessionContainer = new SessionContainer(true);
				// TODO ML					requestContainer.setSessionContainer(sessionContainer);
				// TODO ML					session.setAttribute(Constants.REQUEST_CONTAINER, requestContainer);
				// TODO ML				}
				// TODO ML				ResponseContainer responseContainer = (ResponseContainer) session
				// TODO ML						.getAttribute(Constants.RESPONSE_CONTAINER);
				// TODO ML				if (responseContainer == null) {
				// TODO ML					responseContainer = new ResponseContainer();
				// TODO ML					SourceBean serviceResponse = new SourceBean(Constants.SERVICE_RESPONSE);
				// TODO ML					responseContainer.setServiceResponse(serviceResponse);
				// TODO ML					session.setAttribute(Constants.RESPONSE_CONTAINER, responseContainer);
				// TODO ML				}
				// TODO ML				SessionContainer sessionContainer = requestContainer.getSessionContainer();
				// TODO ML				SessionContainer permanentSession = sessionContainer.getPermanentContainer();
				// TODO ML				IEngUserProfile profile = (IEngUserProfile) permanentSession
				// TODO ML						.getAttribute(IEngUserProfile.ENG_USER_PROFILE);
				// @formatter:off
				IEngUserProfile profile = (IEngUserProfile) session
						.getAttribute(IEngUserProfile.ENG_USER_PROFILE);

				if (profile == null) {
					// in case the profile does not exist, creates a new one
					LOGGER.debug("User profile not found in session, creating a new one and putting in session....");

					String userId = null;

					if (ChannelUtilities.isWebRunning() && !GeneralUtilities.isSSOEnabled()) {
						// case of installation as web application without SSO
						try {
							userId = getUserIdInWebModeWithoutSSO(httpRequest);
						} catch (Exception e) {
							LOGGER.error("Error authenticating user", e);
							httpRequest.getRequestDispatcher("/WEB-INF/jsp/commons/silentLoginFailed.jsp")
									.forward(request, response);
							return;
						}
					} else {
						// case of installation as portlet application and/or with SSO
						userId = getUserIdWithSSO(httpRequest);
					}

					LOGGER.debug("User id = {}", userId);
					if (userId != null && !userId.trim().equals("")) {
						profile = GeneralUtilities.createNewUserProfile(userId);

						if (profile == null) {
							LOGGER.error("User [{}] has no profile defined.", userId);
							httpRequest.getRequestDispatcher("/unprofiledUser.jsp").forward(request, response);
							return;
						}

						if (requestIsForHomePage(httpRequest)) {
							// in case user has a default role, we get his default user profile object only in case the request is for the home page, otherwise
							// we can have inconsistencies (example: request is for execution of a document not executable by the default role, but another one)
							profile = SessionUserProfileBuilder.getDefaultUserProfile((UserProfile) profile);
						}

						// put user profile into session
						storeProfileInSession((UserProfile) profile, /* TODO ML: permanentSession, */ session, httpRequest);
					} else {
						LOGGER.debug("User identifier not found.");
					}

					// PM-int
					if (profile != null) {
						UserProfile up = UserProfileUtility.enrichProfile((UserProfile) profile, httpRequest, session);

						// PM-int todo chiamata al servizio JMS LoginEventBuilder eventBuilder = new LoginEventBuilder(); UserProfile up = (UserProfile)
						// profile;
						LoginEventBuilder eventBuilder = new LoginEventBuilder();
						eventBuilder.appendSession("knowage", up.getSourceIpAddress(), up.getSessionId(),
								up.getSessionStart(), up.getUserId().toString());
						eventBuilder.appendUserAgent(up.getOs(), up.getSourceIpAddress(), up.getSourceSocketEnabled(),
								up.getUserAgent());
						PrivacyManagerClient.getInstance().sendMessage(eventBuilder.getDTO());
					}

				} else {
					// in case the profile is different, creates a new one
					// and overwrites the existing
					/*
					 * if (!((UserProfile) profile).getUserUniqueIdentifier().toString ().equals(userId)) {LOGGER.debug(
					 * "Different user profile found in session, creating a new one and replacing in session...." ); profile = GeneralUtilities.createNewUserProfile(userId);
					 * permanentSession .setAttribute(IEngUserProfile.ENG_USER_PROFILE, profile); } else { LOGGER.debug("User profile object for user [" + userId +
					 * "] already existing in session, ok"); }
					 */
				}

				if (profile != null) {
					manageTenant(profile);
					UserProfileManager.setProfile((UserProfile) profile);

				} else {
					// @formatter:off
					if (!requestIsForHomePage(httpRequest) &&
							!requestIsForLoginByToken(httpRequest) &&
							!requestIsForLoginByJavaScriptSDK(httpRequest) &&
							!requestIsForSessionExpired(httpRequest))
					// @formatter:on
					{
						String contextName = ChannelUtilities.getSpagoBIContextName(httpRequest);
						String targetService = httpRequest.getRequestURI() + "?" + httpRequest.getQueryString();
						String redirectURL = contextName
								+ "/servlet/AdapterHTTP?PAGE=LoginPage&NEW_SESSION=TRUE&targetService="
								+ URLEncoder.encode(targetService, UTF_8.name());
						httpResponse.sendRedirect(redirectURL);
						return;
					}

				}

				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			LOGGER.error("Error while service execution", e);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} finally {
			// since TenantManager uses a ThreadLocal, we must clean after
			// request processed in each case
			TenantManager.unset();
			UserProfileManager.unset();
		}
	}

	private boolean requestIsForHomePage(HttpServletRequest request) {
		// returns true in case request has PAGE=LoginPage parameter, false otherwise
		return request.getParameter(Constants.PAGE) != null
				&& request.getParameter(Constants.PAGE).equalsIgnoreCase(LoginModule.PAGE_NAME);
	}

	private boolean requestIsForLoginByToken(HttpServletRequest request) {
		// returns true in case request has ACTION_NAME=LOGIN_ACTION_BY_TOKEN parameter, false otherwise
		return request.getParameter(Constants.ACTION_NAME) != null
				&& request.getParameter(Constants.ACTION_NAME).equalsIgnoreCase(LoginActionByToken.SERVICE_NAME);
	}

	private boolean requestIsForLoginByJavaScriptSDK(HttpServletRequest request) {
		// returns true in case request has ACTION_NAME=LOGIN_ACTION_WEB parameter, false otherwise
		return request.getParameter(Constants.ACTION_NAME) != null
				&& request.getParameter(Constants.ACTION_NAME).equalsIgnoreCase(LoginActionWeb.SERVICE_NAME);
	}

	private boolean requestIsForSessionExpired(HttpServletRequest request) {
		// returns true in case request contains the sessionExpiredURL read from Knowage configuration
		return request.getRequestURI().contains(GeneralUtilities.getSessionExpiredURL());
	}

	private void storeProfileInSession(UserProfile userProfile, /* TODO ML: SessionContainer permanentContainer, */
			HttpSession httpSession, HttpServletRequest httpRequest) {
		LOGGER.debug("IN");

		// PM-int
		UserProfileUtility.enrichProfile(userProfile, httpRequest, httpSession);

		// @formatter:off
		// TODO ML: permanentContainer.setAttribute(IEngUserProfile.ENG_USER_PROFILE, userProfile);
		// @formatter:on
		httpSession.setAttribute(IEngUserProfile.ENG_USER_PROFILE, userProfile);
		LOGGER.debug("OUT");
	}

	private String getUserIdInWebModeWithoutSSO(HttpServletRequest httpRequest) {
		SpagoBIUserProfile profile = null;
		UsernamePasswordCredentials credentials = this.findUserCredentials(httpRequest);
		if (credentials != null) {
			LOGGER.debug("User credentials found.");
			if (!httpRequest.getMethod().equalsIgnoreCase("POST")) {
				LOGGER.error("Request method is not POST!!!");
				throw new InvalidMethodException();
			}
			LOGGER.debug("Authenticating user ...");
			try {
				profile = this.authenticate(credentials);
				LOGGER.debug("User authenticated");
				httpRequest.getSession().setAttribute(SsoServiceInterface.SILENT_LOGIN, Boolean.TRUE);
			} catch (Throwable t) {
				LOGGER.error("Authentication failed", t);
				throw new SilentAuthenticationFailedException();
			}
		} else {
			LOGGER.debug("User credentials not found.");
		}

		return profile != null ? profile.getUniqueIdentifier() : null;
	}

	private SpagoBIUserProfile authenticate(UsernamePasswordCredentials credentials) throws Throwable {
		LOGGER.debug("IN: userId = {}", credentials.getUserName());
		try {
			ISecurityServiceSupplier supplier = SecurityServiceSupplierFactory.createISecurityServiceSupplier();
			SpagoBIUserProfile profile = supplier.checkAuthentication(credentials.getUserName(),
					credentials.getPassword());
			if (profile == null) {
				LOGGER.error("Authentication failed for user {}", credentials.getUserName());
				throw new SecurityException("Authentication failed");
			}
			return profile;
		} catch (Throwable t) {
			LOGGER.error("Error while authenticating userId = {}", credentials.getUserName(), t);
			throw t;
		} finally {
			LOGGER.debug("OUT");
		}

	}

	private UsernamePasswordCredentials findUserCredentials(HttpServletRequest httpRequest) {
		UsernamePasswordCredentials toReturn = null;
		String userId = httpRequest.getParameter(SsoServiceInterface.USER_NAME_REQUEST_PARAMETER.toLowerCase());
		LOGGER.debug("Request parameter {} is [{}]", SsoServiceInterface.USER_NAME_REQUEST_PARAMETER.toLowerCase(),
				userId);
		if (userId == null) {
			userId = httpRequest.getParameter(SsoServiceInterface.USER_NAME_REQUEST_PARAMETER.toUpperCase());
			LOGGER.debug("Request parameter {} is [{}]", SsoServiceInterface.USER_NAME_REQUEST_PARAMETER.toUpperCase(),
					userId);
		}
		String password = httpRequest.getParameter(SsoServiceInterface.SECRETPHRASE_REQUEST_PARAMETER.toLowerCase());
		if (password == null) {
			password = httpRequest.getParameter(SsoServiceInterface.SECRETPHRASE_REQUEST_PARAMETER.toUpperCase());
		}
		if (!isEmpty(userId) && !isNull(password)) {
			LOGGER.debug("Read credentials from request: user id is [{}]", userId);
			String passwordMode = httpRequest.getParameter(SsoServiceInterface.SECRETPHRASE_MODE_REQUEST_PARAMETER);
			if (!isEmpty(passwordMode) && passwordMode.equalsIgnoreCase(SsoServiceInterface.SECRETPHRASE_MODE_ENCRYPTED)) {
				LOGGER.debug("Password mode is encrypted. Decripting password...");
				DefaultCipher chiper = new DefaultCipher();
				password = chiper.decrypt(password);
				LOGGER.debug("Password decrypted.");
			}
			toReturn = new UsernamePasswordCredentials(userId, password);
		}
		return toReturn;
	}

	private void manageTenant(IEngUserProfile profile) {
		UserProfile userProfile = (UserProfile) profile;
		// retrieving tenant id
		String tenantId = userProfile.getOrganization();
		LOGGER.debug("Retrieved tenantId from user profile object : [{}]", tenantId);
		// putting tenant id on thread local
		Tenant tenant = new Tenant(tenantId);
		TenantManager.setTenant(tenant);
		LOGGER.debug("Tenant [{}] set into TenantManager", tenantId);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// do nothing
	}

	/**
	 * Finds the user identifier from http request or from SSO system (by the http request in input). Use the SsoServiceInterface for read the userId in all cases,
	 * if SSO is disabled use FakeSsoService. Check spagobi_sso.xml
	 *
	 * @param httpRequest The http request
	 *
	 * @return the current user unique identified
	 *
	 * @throws Exception in case the SSO is enabled and the user identifier specified on http request is different from the SSO detected one.
	 */
	private String getUserIdWithSSO(HttpServletRequest request) {
		LOGGER.debug("IN");
		String userId = null;
		try {
			SsoServiceInterface userProxy = SsoServiceFactory.createProxyService();
			userId = userProxy.readUserIdentifier(request);
			request.getSession().setAttribute(SsoServiceInterface.SILENT_LOGIN, Boolean.TRUE);
		} catch (Exception e) {
			LOGGER.error("Authentication failed", e);
			throw new SilentAuthenticationFailedException();
		} finally {
			LOGGER.debug("OUT");
		}
		return userId;
	}

	public class SilentAuthenticationFailedException extends RuntimeException {

	}

	public class InvalidMethodException extends RuntimeException {

	}

}
