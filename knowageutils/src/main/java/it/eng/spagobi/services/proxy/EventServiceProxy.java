/*
 * Knowage, Open Source Business Intelligence suite
 * Copyright (C) 2021 Engineering Ingegneria Informatica S.p.A.
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
package it.eng.spagobi.services.proxy;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.log4j.Logger;

import it.eng.spagobi.services.event.EventService;
import it.eng.spagobi.services.security.exceptions.SecurityException;

/**
 *
 * proxy of Event service
 *
 */
public final class EventServiceProxy extends AbstractServiceProxy {

	private static final String SERVICE_NAME = "Event Service";

	private static final QName SERVICE_QNAME = new QName("http://event.services.spagobi.eng.it/", "EventService");

	public static final String START_EVENT_ID = "startEventId";
	public static final String BIOBJECT_ID = "biobjectId";
	public static final String USER = "user";
	public static final String EVENT_TYPE = "event-type";
	public static final String DOCUMENT_EXECUTION_START = "biobj-start-execution";
	public static final String DOCUMENT_EXECUTION_END = "biobj-end-execution";

	private static Logger logger = Logger.getLogger(EventServiceProxy.class);

	/**
	 * use it only in engine context.
	 *
	 * @param user    user Id
	 * @param session HttpSession
	 */
	public EventServiceProxy(String user, HttpSession session) {
		super(user, session);
	}

	private EventServiceProxy() {
		super();
	}

	private EventService lookUp() throws SecurityException {
		try {
			EventService service = null;

			if (serviceUrl != null) {
				URL serviceUrlWithWsdl = new URL(serviceUrl.toString() + "?wsdl");

				service = Service.create(serviceUrlWithWsdl, SERVICE_QNAME).getPort(EventService.class);
			} else {
				service = Service.create(SERVICE_QNAME).getPort(EventService.class);
			}

			setCommonHeader(service);

			return service;
		} catch (Exception e) {
			logger.error("Impossible to locate [" + SERVICE_NAME + "] at [" + serviceUrl + "]");
			throw new SecurityException("Impossible to locate [" + SERVICE_NAME + "] at [" + serviceUrl + "]", e);
		}
	}

	/**
	 * Fire event.
	 *
	 * @param description         String
	 * @param parameters          String
	 * @param rolesHandler        String
	 * @param presentationHandler String
	 *
	 * @return String
	 */
	public String fireEvent(String description, String parameters, String rolesHandler, String presentationHandler) {
		logger.debug("IN");
		try {
			return lookUp().fireEvent(readTicket(), userId, description, parameters, rolesHandler, presentationHandler);
		} catch (Exception e) {
			logger.error("Error during service execution", e);

		} finally {
			logger.debug("OUT");
		}
		return null;
	}

	/**
	 * Fire event.
	 *
	 * @param description         String
	 * @param parameters          Map
	 * @param rolesHandler        String
	 * @param presentationHandler String
	 *
	 * @return String
	 */
	public String fireEvent(String description, Map parameters, String rolesHandler, String presentationHandler) {
		logger.debug("IN");
		try {
			return lookUp().fireEvent(readTicket(), userId, description, getParamsStr(parameters), rolesHandler, presentationHandler);
		} catch (Exception e) {
			logger.error("Error during service execution", e);

		} finally {
			logger.debug("OUT");
		}
		return null;
	}

	private String getParamsStr(Map params) {
		StringBuffer buffer = new StringBuffer();
		Iterator it = params.keySet().iterator();
		boolean isFirstParameter = true;
		while (it.hasNext()) {
			String pname = (String) it.next();
			String pvalue = (String) params.get(pname);
			if (!isFirstParameter)
				buffer.append("&");
			else
				isFirstParameter = false;
			buffer.append(pname + "=" + pvalue);
		}
		return buffer.toString();
	}

}
