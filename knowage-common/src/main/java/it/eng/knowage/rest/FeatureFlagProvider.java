/*
 * Knowage, Open Source Business Intelligence suite
 * Copyright (C) 2023 Engineering Ingegneria Informatica S.p.A.

 * Knowage is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * Knowage is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.eng.knowage.rest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Stream;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.eng.knowage.features.Feature;
import it.eng.knowage.rest.annotation.FeatureFlag;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class FeatureFlagProvider implements ContainerRequestFilter {

	private static final Logger LOGGER = LogManager.getLogger(FeatureFlagProvider.class);

	@Context
	private ResourceInfo resourceInfo;

	private final Properties properties = new Properties();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		FeatureFlag annotation = resourceInfo.getResourceMethod().getAnnotation(FeatureFlag.class);
		if (annotation != null && !isFeatureEnabled(annotation)) {
			Map<String, Object> response = new HashMap<>();

			List<Map<String, Object>> messages = new ArrayList<>();
			Map<String, Object> message = new HashMap<>();
			message.put("message", "not-enabled-to-call-service");
			messages.add(message);

			response.put("errors", messages);

			requestContext.abortWith(
					Response.status(Status.FORBIDDEN).type(MediaType.APPLICATION_JSON).entity(response).build());
		}

	}

	private boolean isFeatureEnabled(FeatureFlag annotation) {

		Feature feature = annotation.value();

		loadProperty();

		String fromPropertyFile = properties.getProperty(feature.getSystemPropertyName());
		String fromSysProp = System.getProperty(feature.getSystemPropertyName());
		String fromEnv = System.getenv(feature.getEnvVariableName());

		// @formatter:off
		return Stream.of(fromPropertyFile, fromSysProp, fromEnv)
				.filter(Objects::nonNull)
				.findFirst()
				.map(Boolean::parseBoolean)
				.orElse(true);
		// @formatter:on

	}

	private void loadProperty() {

		// TODO : Leggere il valore da JNDI usando la chiave "java:comp/env/resource_path"
		// TODO : Usare java.nio.Paths per fare il fare il parsing del valore come java.nio.Path
		// TODO : Usare il metodo java.nio.Path.resolve() per andare dalla cartella resources al file feature-list.properties dentro alla cartella ../knowage

		String featurePropertyFilePathAsString = ???;

		if (Objects.nonNull(featurePropertyFilePathAsString)) {
			Path featurePropertyFilePath = Paths.get(featurePropertyFilePathAsString);

			if (Files.exists(featurePropertyFilePath)) {
				try (InputStream is = Files.newInputStream(featurePropertyFilePath)) {
					properties.load(is);
				} catch (IOException e) {
					LOGGER.warn("Error reading {} as feature property file. Ignoring the file!",
							featurePropertyFilePath, e);
				}
			} else {
				LOGGER.warn("Feature property file at {} doesn't exists!", featurePropertyFilePath);
			}
		}

	}
}
