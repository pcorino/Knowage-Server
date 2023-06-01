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
package it.eng.spagobi.engines.config.metadata;

import java.util.HashSet;
import java.util.Set;

import it.eng.spagobi.commons.metadata.SbiDomains;
import it.eng.spagobi.commons.metadata.SbiHibernateModel;

/**
 * SbiEngines generated by hbm2java
 */
public class SbiEngines extends SbiHibernateModel {

	// Fields

	private Integer engineId;
	private Short encrypt;
	private String name;
	private String descr;
	private String mainUrl;
	private String secnUrl;
	private String objUplDir;
	private String objUseDir;
	private String driverNm;
	private String label;
	private SbiDomains engineType;
	private String classNm;
	private SbiDomains biobjType;
	private Boolean useDataSource;
	private Boolean useDataSet;
	private Set sbiProductTypeEngine = new HashSet(0);

	// Constructors

	/**
	 * default constructor.
	 */
	public SbiEngines() {
	}

	/**
	 * constructor with id.
	 *
	 * @param engineId
	 *            the engine id
	 */
	public SbiEngines(Integer engineId) {
		this.engineId = engineId;
	}

	// Property accessors

	/**
	 * Gets the engine id.
	 *
	 * @return the engine id
	 */
	public Integer getEngineId() {
		return this.engineId;
	}

	/**
	 * Sets the engine id.
	 *
	 * @param engineId
	 *            the new engine id
	 */
	public void setEngineId(Integer engineId) {
		this.engineId = engineId;
	}

	/**
	 * Gets the encrypt.
	 *
	 * @return the encrypt
	 */
	public Short getEncrypt() {
		return this.encrypt;
	}

	/**
	 * Sets the encrypt.
	 *
	 * @param encrypt
	 *            the new encrypt
	 */
	public void setEncrypt(Short encrypt) {
		this.encrypt = encrypt;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the descr.
	 *
	 * @return the descr
	 */
	public String getDescr() {
		return this.descr;
	}

	/**
	 * Sets the descr.
	 *
	 * @param descr
	 *            the new descr
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}

	/**
	 * Gets the main url.
	 *
	 * @return the main url
	 */
	public String getMainUrl() {
		return this.mainUrl;
	}

	/**
	 * Sets the main url.
	 *
	 * @param mainUrl
	 *            the new main url
	 */
	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	/**
	 * Gets the secn url.
	 *
	 * @return the secn url
	 */
	public String getSecnUrl() {
		return this.secnUrl;
	}

	/**
	 * Sets the secn url.
	 *
	 * @param secnUrl
	 *            the new secn url
	 */
	public void setSecnUrl(String secnUrl) {
		this.secnUrl = secnUrl;
	}

	/**
	 * Gets the obj upl dir.
	 *
	 * @return the obj upl dir
	 */
	public String getObjUplDir() {
		return this.objUplDir;
	}

	/**
	 * Sets the obj upl dir.
	 *
	 * @param objUplDir
	 *            the new obj upl dir
	 */
	public void setObjUplDir(String objUplDir) {
		this.objUplDir = objUplDir;
	}

	/**
	 * Gets the obj use dir.
	 *
	 * @return the obj use dir
	 */
	public String getObjUseDir() {
		return this.objUseDir;
	}

	/**
	 * Sets the obj use dir.
	 *
	 * @param objUseDir
	 *            the new obj use dir
	 */
	public void setObjUseDir(String objUseDir) {
		this.objUseDir = objUseDir;
	}

	/**
	 * Gets the driver nm.
	 *
	 * @return the driver nm
	 */
	public String getDriverNm() {
		return this.driverNm;
	}

	/**
	 * Sets the driver nm.
	 *
	 * @param driverNm
	 *            the new driver nm
	 */
	public void setDriverNm(String driverNm) {
		this.driverNm = driverNm;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label
	 *            the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the biobj type.
	 *
	 * @return the biobj type
	 */
	public SbiDomains getBiobjType() {
		return biobjType;
	}

	/**
	 * Sets the biobj type.
	 *
	 * @param biobjType
	 *            the new biobj type
	 */
	public void setBiobjType(SbiDomains biobjType) {
		this.biobjType = biobjType;
	}

	/**
	 * Gets the class nm.
	 *
	 * @return the class nm
	 */
	public String getClassNm() {
		return classNm;
	}

	/**
	 * Sets the class nm.
	 *
	 * @param classNm
	 *            the new class nm
	 */
	public void setClassNm(String classNm) {
		this.classNm = classNm;
	}

	/**
	 * Gets the engine type.
	 *
	 * @return the engine type
	 */
	public SbiDomains getEngineType() {
		return engineType;
	}

	/**
	 * Sets the engine type.
	 *
	 * @param engineType
	 *            the new engine type
	 */
	public void setEngineType(SbiDomains engineType) {
		this.engineType = engineType;
	}

	/**
	 * Gets the use data source.
	 *
	 * @return the use data source
	 */
	public Boolean getUseDataSource() {
		return useDataSource;
	}

	/**
	 * Sets the use data source.
	 *
	 * @param useDataSource
	 *            the new use data source
	 */
	public void setUseDataSource(Boolean useDataSource) {
		this.useDataSource = useDataSource;
	}

	/**
	 * Gets the use data set.
	 *
	 * @return the use data set
	 */
	public Boolean getUseDataSet() {
		return useDataSet;
	}

	/**
	 * Sets the use data set.
	 *
	 * @param useDataSet
	 *            the new use data set
	 */
	public void setUseDataSet(Boolean useDataSet) {
		this.useDataSet = useDataSet;
	}

	public Set getSbiProductTypeEngine() {
		return sbiProductTypeEngine;
	}

	public void setSbiProductTypeEngine(Set sbiProductTypeEngine) {
		this.sbiProductTypeEngine = sbiProductTypeEngine;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((engineId == null) ? 0 : engineId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SbiEngines other = (SbiEngines) obj;
		if (engineId == null) {
			if (other.engineId != null)
				return false;
		} else if (!engineId.equals(other.engineId))
			return false;
		return true;
	}

}