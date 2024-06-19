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
package it.eng.spagobi.mapcatalogue.metadata;


// Generated 31-mag-2007 14.53.27 by Hibernate Tools 3.2.0.beta8

/**
 * SbiGeoMapFeaturesId generated by hbm2java
 */
public class SbiGeoMapFeaturesId implements java.io.Serializable {

	// Fields    

	private int mapId;

	private int featureId;

	// Constructors

	/**
	 * default constructor.
	 */
	public SbiGeoMapFeaturesId() {
	}

	/**
	 * full constructor.
	 * 
	 * @param mapId the map id
	 * @param featureId the feature id
	 */
	public SbiGeoMapFeaturesId(int mapId, int featureId) {
		this.mapId = mapId;
		this.featureId = featureId;
	}

	// Property accessors
	/**
	 * Gets the map id.
	 * 
	 * @return the map id
	 */
	public int getMapId() {
		return this.mapId;
	}

	/**
	 * Sets the map id.
	 * 
	 * @param mapId the new map id
	 */
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	/**
	 * Gets the feature id.
	 * 
	 * @return the feature id
	 */
	public int getFeatureId() {
		return this.featureId;
	}

	/**
	 * Sets the feature id.
	 * 
	 * @param featureId the new feature id
	 */
	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SbiGeoMapFeaturesId))
			return false;
		SbiGeoMapFeaturesId castOther = (SbiGeoMapFeaturesId) other;

		return (this.getMapId() == castOther.getMapId())
				&& (this.getFeatureId() == castOther.getFeatureId());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getMapId();
		result = 37 * result + this.getFeatureId();
		return result;
	}

}
