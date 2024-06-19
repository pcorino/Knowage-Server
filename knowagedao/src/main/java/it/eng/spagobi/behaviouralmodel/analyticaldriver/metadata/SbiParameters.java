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
package it.eng.spagobi.behaviouralmodel.analyticaldriver.metadata;

import java.util.Set;

import it.eng.spagobi.commons.metadata.SbiDomains;
import it.eng.spagobi.commons.metadata.SbiHibernateModel;

/**
 * SbiParameters generated by hbm2java
 */
public class SbiParameters extends SbiHibernateModel {

	// Fields

	private Integer parId;
	private SbiDomains parameterType;
	private Short length;
	private String parameterTypeCode;
	private String label;
	private String name;
	private String mask;
	private String descr;
	private Set sbiParuses;
	private Set sbiObjPars;
	private Short functionalFlag;
	private Short temporalFlag;
	private String valueSelection;
	private String selectedLayer;
	private String selectedLayerProp;

	// Constructors

	/**
	 * default constructor.
	 */
	public SbiParameters() {
		this.parId = -1;
	}

	/**
	 * constructor with id.
	 *
	 * @param parId the par id
	 */
	public SbiParameters(Integer parId) {
		this.parId = parId;
	}

	// Property accessors

	/**
	 * Gets the par id.
	 *
	 * @return the par id
	 */
	public Integer getParId() {
		return this.parId;
	}

	public void changeParId(Integer parId) {
		this.setParId(parId);
	}
	/**
	 * Sets the par id.
	 *
	 * @param parId the new par id
	 */
	private void setParId(Integer parId) {
		this.parId = parId;
	}

	/**
	 * Gets the parameter type.
	 *
	 * @return the parameter type
	 */
	public SbiDomains getParameterType() {
		return this.parameterType;
	}

	/**
	 * Sets the parameter type.
	 *
	 * @param sbiDomains the new parameter type
	 */
	public void setParameterType(SbiDomains sbiDomains) {
		this.parameterType = sbiDomains;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public Short getLength() {
		return this.length;
	}

	/**
	 * Sets the length.
	 *
	 * @param length the new length
	 */
	public void setLength(Short length) {
		this.length = length;
	}

	/**
	 * Gets the parameter type code.
	 *
	 * @return the parameter type code
	 */
	public String getParameterTypeCode() {
		return this.parameterTypeCode;
	}

	/**
	 * Sets the parameter type code.
	 *
	 * @param parTypeCd the new parameter type code
	 */
	public void setParameterTypeCode(String parTypeCd) {
		this.parameterTypeCode = parTypeCd;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the mask.
	 *
	 * @return the mask
	 */
	public String getMask() {
		return this.mask;
	}

	/**
	 * Sets the mask.
	 *
	 * @param mask the new mask
	 */
	public void setMask(String mask) {
		this.mask = mask;
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
	 * @param descr the new descr
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}

	/**
	 * Gets the sbi paruses.
	 *
	 * @return the sbi paruses
	 */
	public Set getSbiParuses() {
		return this.sbiParuses;
	}

	/**
	 * Sets the sbi paruses.
	 *
	 * @param sbiParuses the new sbi paruses
	 */
	public void setSbiParuses(Set sbiParuses) {
		this.sbiParuses = sbiParuses;
	}

	/**
	 * Gets the sbi obj pars.
	 *
	 * @return the sbi obj pars
	 */
	public Set getSbiObjPars() {
		return this.sbiObjPars;
	}

	/**
	 * Sets the sbi obj pars.
	 *
	 * @param sbiObjPars the new sbi obj pars
	 */
	public void setSbiObjPars(Set sbiObjPars) {
		this.sbiObjPars = sbiObjPars;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the functional flag.
	 *
	 * @return the functional flag
	 */
	public Short getFunctionalFlag() {
		return functionalFlag;
	}

	/**
	 * Sets the functional flag.
	 *
	 * @param functionalFlag the new functional flag
	 */
	public void setFunctionalFlag(Short functionalFlag) {
		this.functionalFlag = functionalFlag;
	}

	public Short getTemporalFlag() {
		return temporalFlag;
	}

	public void setTemporalFlag(Short temporalFlag) {
		this.temporalFlag = temporalFlag;
	}

	/**
	 * Gets the value selection.
	 *
	 * @return Returns the valueSelection.
	 */
	public String getValueSelection() {
		return valueSelection;
	}

	/**
	 * Sets the value selection.
	 *
	 * @param valueSelection The value selection to set.
	 */
	public void setValueSelection(String valueSelection) {
		this.valueSelection = valueSelection;
	}

	/**
	 * Gets the selected layer property.
	 *
	 * @return Returns the selectedLayerProp.
	 */
	public String getSelectedLayerProp() {
		return selectedLayerProp;
	}

	/**
	 * Sets the selected layer property.
	 *
	 * @param selectedLayerProp The layer property to set.
	 */
	public void setSelectedLayerProp(String selectedLayerProp) {
		this.selectedLayerProp = selectedLayerProp;
	}

	/**
	 * Gets the selected layer.
	 *
	 * @return Returns the selectedLayer.
	 */
	public String getSelectedLayer() {
		return selectedLayer;
	}

	/**
	 * Sets the selected layer.
	 *
	 * @param selectedLayer The layer to set.
	 */
	public void setSelectedLayer(String selectedLayer) {
		this.selectedLayer = selectedLayer;
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
		result = prime * result + ((parId == null) ? 0 : parId.hashCode());
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
		SbiParameters other = (SbiParameters) obj;
		if (parId == null) {
			if (other.parId != null)
				return false;
		} else if (!parId.equals(other.parId))
			return false;
		return true;
	}

}