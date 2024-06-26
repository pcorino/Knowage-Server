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
package it.eng.knowage.features;

public enum Feature {

	// knowage.feature.editFunctionsCatalog represents the feature that allows users to insert or modify Python/R functions from their catalog
	EDIT_FUNCTIONS_CATALOG("knowage.feature.editFunctionsCatalog", "KNOWAGE_FEATURE_EDITFUNCTIONSCATALOG"),
	EDIT_DATASOURCE("knowage.feature.editDataSource", "KNOWAGE_FEATURE_EDITDATASOURCE"),
	EDIT_DOCUMENT("knowage.feature.editDocument", "KNOWAGE_FEATURE_EDITDOCUMENT");

	Feature(String systemPropertyName, String envVariableName) {
		this.systemPropertyName = systemPropertyName;
		this.envVariableName = envVariableName;
	}

	private final String systemPropertyName;

	private final String envVariableName;

	public String getSystemPropertyName() {
		return systemPropertyName;
	}

	public String getEnvVariableName() {
		return envVariableName;
	}

}
