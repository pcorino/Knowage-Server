/*
 * Knowage, Open Source Business Intelligence suite
 * Copyright (C) 2016 Engineering Ingegneria Informatica S.p.A.

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
package it.eng.spagobi.api.v2.export;

import static it.eng.spagobi.api.v2.export.AbstractExportJob.MAP_KEY_DATA_SET_ID;
import static it.eng.spagobi.api.v2.export.AbstractExportJob.MAP_KEY_DRIVERS;
import static it.eng.spagobi.api.v2.export.AbstractExportJob.MAP_KEY_ID;
import static it.eng.spagobi.api.v2.export.AbstractExportJob.MAP_KEY_LOCALE;
import static it.eng.spagobi.api.v2.export.AbstractExportJob.MAP_KEY_PARAMETERS;
import static it.eng.spagobi.api.v2.export.AbstractExportJob.MAP_KEY_RESOURCE_PATH;
import static it.eng.spagobi.api.v2.export.AbstractExportJob.MAP_KEY_USER_PROFILE;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;

import it.eng.spagobi.commons.bo.UserProfile;
import it.eng.spagobi.commons.utilities.SpagoBIUtilities;

/**
 * Builder for export job.
 *
 * @author Marco Libanori
 */
public class ExportJobBuilder {

	private static final Logger logger = Logger.getLogger(ExportJobBuilder.class);

	private static final String EXPORT_TYPE_XLSX = "xlsx";
	private static final String EXPORT_TYPE_AVRO = "avro";
	private static final String EXPORT_TYPE_CSV = "csv";

	/**
	 * Quartz group for export process job.
	 */
	private static final String EXPORT_GROUP = "export";

	/**
	 * Instantiates new builder.
	 *
	 * @param datasetId   Data set id to export
	 * @param userProfile User that request the export
	 * @return Current builder
	 */
	public static ExportJobBuilder fromDataSetIdAndUserProfile(Integer datasetId, UserProfile userProfile) {
		return new ExportJobBuilder(datasetId, userProfile);
	}

	/**
	 * String value of main resource path.
	 */
	private final String resoursePath = SpagoBIUtilities.getResourcePath();

	/**
	 * Dataset id.
	 */
	private final Integer dataSetId;

	/**
	 * Random UUID that identify the job and the future file.
	 */
	private final UUID randomUUID = UUID.randomUUID();

	/**
	 * Job name.
	 */
	private final String jobName = "export-" + randomUUID;

	/**
	 * Type of file generated by the exporter.
	 */
	private String type = EXPORT_TYPE_CSV;

	/**
	 * Reference to user profile.
	 */
	private UserProfile userProfile;

	/**
	 * Parameters data.
	 */
	Map<String, String> parameters = new HashMap<>();

	/**
	 * Drivers data.
	 */
	Map<String, Object> drivers = new HashMap<>();

	/**
	 * Default locale.
	 */
	private Locale locale = Locale.getDefault();

	private ExportJobBuilder(final Integer dataSetId, final UserProfile userProfile) {
		this.dataSetId = dataSetId;
		this.userProfile = userProfile;
	}

	/**
	 * Build the job and return it.
	 *
	 * @return A new instance of the export job ready to schedule
	 */
	public JobDetail build() {

		logger.debug("Building export job for...");

		if (dataSetId == null) {
			throw new IllegalArgumentException("Attribute dataSetId cannot be null");
		}
		if (userProfile == null) {
			throw new IllegalArgumentException("Attribute userProfile cannot be null");
		}

		String jobDescription = String.format("Export of dataset %d to %s", dataSetId, type);

		JobDataMap jobDataMap = new JobDataMap();

		jobDataMap.put(MAP_KEY_DATA_SET_ID, dataSetId);
		jobDataMap.put(MAP_KEY_DRIVERS, drivers);
		jobDataMap.put(MAP_KEY_ID, randomUUID);
		jobDataMap.put(MAP_KEY_LOCALE, locale);
		jobDataMap.put(MAP_KEY_PARAMETERS, parameters);
		jobDataMap.put(MAP_KEY_RESOURCE_PATH, resoursePath);
		jobDataMap.put(MAP_KEY_USER_PROFILE, userProfile);

		logger.debug("\t- Dataset id: " + String.valueOf(dataSetId));
		logger.debug("\t- UUID: " + String.valueOf(randomUUID));
		logger.debug("\t- Resource path: " + String.valueOf(resoursePath));
		logger.debug("\t- Type: " + String.valueOf(type));

		JobDetail job = null;
		switch (type) {
		case EXPORT_TYPE_CSV:
			job = new JobDetail(jobName, EXPORT_GROUP, CSVExportJob.class);
			break;
		case EXPORT_TYPE_XLSX:
			job = new JobDetail(jobName, EXPORT_GROUP, ExcelExportJob.class);
			break;
		case EXPORT_TYPE_AVRO:
			job = new JobDetail(jobName, EXPORT_GROUP, AvroExportJob.class);
			break;

		default:
			String msg = String.format("Export job type %s not supported", type);
			throw new IllegalArgumentException(msg);
		}

		job.setDescription(jobDescription);
		job.setJobDataMap(jobDataMap);
		job.setDurability(false);

		logger.debug("Export job built!");

		return job;
	}

	/**
	 * Specificy drivers data.
	 *
	 * @param drivers Drivers data
	 * @return Current builder
	 */
	public ExportJobBuilder withDrivers(final Map<String, Object> drivers) {
		if (drivers != null) {
			this.drivers.putAll(drivers);
		}
		return this;
	}

	/**
	 * Specificy parameters data.
	 *
	 * @param parameters Parameters data
	 * @return Current builder
	 */
	public ExportJobBuilder withParameters(final Map<String, String> parameters) {
		if (parameters != null) {
			this.parameters.putAll(parameters);
		}
		return this;
	}

	/**
	 * Specify CSV output.
	 *
	 * @return Current builder
	 */
	public ExportJobBuilder withTypeOfCsv() {
		type = EXPORT_TYPE_CSV;
		return this;
	}

	/**
	 * Specify Avro output.
	 *
	 * @return Current builder
	 */
	public ExportJobBuilder withTypeOfAvro() {
		type = EXPORT_TYPE_AVRO;
		return this;
	}

	/**
	 * Specify Excel output.
	 *
	 * @return Current builder
	 */
	public ExportJobBuilder withLocale(final Locale locale) {
		if (locale != null) {
			this.locale = locale;
		}
		return this;
	}

	/**
	 * Specify Excel output.
	 *
	 * @return Current builder
	 */
	public ExportJobBuilder withTypeOfXlsx() {
		type = EXPORT_TYPE_XLSX;
		return this;
	}
}
