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
package it.eng.knowage.commons.utilities.urls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eng.knowage.wapp.Environment;
import it.eng.knowage.wapp.Version;

/**
 * Use this object for importing our custom static resources. Instantiate class in base jsp file, like "angularResources.jsp", then use "getResourcePath(String
 * contextpath, String url)" method in "angularImport.jsp" files.
 *
 * @author Predrag Josipovic
 *
 */
public class UrlBuilder {

	private static final Logger LOGGER = LogManager.getLogger(UrlBuilder.class);

	private String KNOWAGE_VERSION = Version.getCompleteVersion();
	private Environment ENVIRONMENT = Version.getEnvironment();

	private String baseEngineContext;
	private String currentEngineContext;

	private String[] regExpResources = { "/js/(src)", "/themes/commons/(css)/" };

	public UrlBuilder(String baseEngineContext) {
		this.baseEngineContext = baseEngineContext;
	}

	public UrlBuilder(String baseEngineContext, String currentEngineContext) {
		this.baseEngineContext = baseEngineContext;
		this.currentEngineContext = currentEngineContext;
	}

	public String getResourcePath(String contextPath, String url) {
		LOGGER.debug("Get resource path for {} context and {} URL", contextPath, url);
		String fullUrl = null;
		try {
			if (!url.startsWith("/"))
				url = "/" + url;

			fullUrl = contextPath + url;
			// In production mode create src-[version]
			if (ENVIRONMENT == Environment.PRODUCTION)
				fullUrl = concatSrcWithKnowageVersion(fullUrl);
		} catch (Exception e) {
			LOGGER.error("Cannot build a resource url path for {} context and {} URL", contextPath, url, e);
		}

		LOGGER.debug("Full URL for {} context and {} URL is: {}", contextPath, url, fullUrl);
		return fullUrl;
	}

	private String concatSrcWithKnowageVersion(String url) {
		LOGGER.debug("Concat KNOWAGE version to {}", url);
		for (int i = 0; i < regExpResources.length; i++) {
			String pattern = regExpResources[i];
			Pattern srcPattern = Pattern.compile(pattern);
			Matcher srcMatcher = srcPattern.matcher(url);

			if (srcMatcher.find()) {
				String src = srcMatcher.group(1);
				url = url.replaceFirst(src, src + "-" + KNOWAGE_VERSION);
			}
		}
		LOGGER.debug("Concatenation of KNOWAGE version is {}", url);
		return url;
	}

	/**
	 * Dynamically creates a base resources path in core application engine (knowage). Depends in which mode environment is built ("production" or
	 * "development") it will create "/knowage/js/src-7.0.0/ or "/knowage/js/src/".
	 *
	 * @return /knowage/js/src-[version]/ if it is "production" mode, or /knowage/js/src/ if it is "development" mode.
	 */
	public String getDynamicResorucesBasePath() {
		return createDynamicResourcesPath(baseEngineContext);
	}

	/**
	 * Dynamically creates a base resources path in current application engine (i.e. cockpitengine). Depends in which mode environment is built ("production" or
	 * "development") it will create "/cockpitengine/js/src-7.0.0/ or "/cockpitengine/js/src/".
	 *
	 * @return /cockpitengine/js/src-[version]/ if it is "production" mode, or /cockpitengine/js/src/ if it is "development" mode.
	 */
	public String getDynamicResourcesEnginePath() {
		return createDynamicResourcesPath(currentEngineContext);
	}

	private String createDynamicResourcesPath(String sourceEngineContext) {
		LOGGER.debug("Create dynamic resource path for {}", sourceEngineContext);
		StringBuilder dynamicResourcesPath = new StringBuilder(sourceEngineContext);
		if (ENVIRONMENT == Environment.PRODUCTION) {
			dynamicResourcesPath.append("/js/src-").append(KNOWAGE_VERSION);
		} else {
			dynamicResourcesPath.append("/js/src");
		}

		String ret = dynamicResourcesPath.toString();
		LOGGER.debug("Dynamic resource path for {} is {}", sourceEngineContext, ret);
		return ret;
	}

}
