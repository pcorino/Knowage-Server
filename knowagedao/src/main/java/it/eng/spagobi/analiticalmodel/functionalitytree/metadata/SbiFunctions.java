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
package it.eng.spagobi.analiticalmodel.functionalitytree.metadata;

import java.util.Set;

import it.eng.spagobi.commons.metadata.SbiDomains;
import it.eng.spagobi.commons.metadata.SbiHibernateModel;

/**
 * SbiFunctions generated by hbm2java
 */
public class SbiFunctions extends SbiHibernateModel {

	// Fields

	private Integer functId;
	private SbiDomains functType;
	private SbiFunctions parentFunct;
	private String functTypeCd;
	private String code;
	private String name;
	private String descr;
	private String path;
	private Set sbiObjFuncs;
	private Set<SbiFuncRole> sbiFuncRoles;
	private Integer prog;

	// Constructors

	/**
	 * default constructor.
	 */
	public SbiFunctions() {
		this.functId = -1;
	}

	/**
	 * constructor with id.
	 *
	 * @param functId the funct id
	 */
	public SbiFunctions(Integer functId) {
		this.functId = functId;
	}

	// Property accessors

	/**
	 * Gets the funct id.
	 *
	 * @return the funct id
	 */
	public Integer getFunctId() {
		return this.functId;
	}

	public void changeFunctId(Integer functId) {
		this.setFunctId(functId);
	}
	/**
	 * Sets the funct id.
	 *
	 * @param functId the new funct id
	 */
	private void setFunctId(Integer functId) {
		this.functId = functId;
	}

	/**
	 * Gets the funct type cd.
	 *
	 * @return the funct type cd
	 */
	public String getFunctTypeCd() {
		return this.functTypeCd;
	}

	/**
	 * Sets the funct type cd.
	 *
	 * @param functTypeCd the new funct type cd
	 */
	public void setFunctTypeCd(String functTypeCd) {
		this.functTypeCd = functTypeCd;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @param name the new name
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
	 * @param descr the new descr
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Gets the sbi obj funcs.
	 *
	 * @return the sbi obj funcs
	 */
	public Set getSbiObjFuncs() {
		return this.sbiObjFuncs;
	}

	/**
	 * Sets the sbi obj funcs.
	 *
	 * @param sbiObjFuncs the new sbi obj funcs
	 */
	public void setSbiObjFuncs(Set sbiObjFuncs) {
		this.sbiObjFuncs = sbiObjFuncs;
	}

	/**
	 * Gets the sbi func roles.
	 *
	 * @return the sbi func roles
	 */
	public Set<SbiFuncRole> getSbiFuncRoles() {
		return this.sbiFuncRoles;
	}

	/**
	 * Sets the sbi func roles.
	 *
	 * @param sbiFuncRoles the new sbi func roles
	 */
	public void setSbiFuncRoles(Set<SbiFuncRole> sbiFuncRoles) {
		this.sbiFuncRoles = sbiFuncRoles;
	}

	/**
	 * Gets the funct type.
	 *
	 * @return the funct type
	 */
	public SbiDomains getFunctType() {
		return functType;
	}

	/**
	 * Sets the funct type.
	 *
	 * @param functType the new funct type
	 */
	public void setFunctType(SbiDomains functType) {
		this.functType = functType;
	}

	/**
	 * Gets the parent funct.
	 *
	 * @return the parent funct
	 */
	public SbiFunctions getParentFunct() {
		return parentFunct;
	}

	/**
	 * Sets the parent funct.
	 *
	 * @param parentFunct the new parent funct
	 */
	public void setParentFunct(SbiFunctions parentFunct) {
		this.parentFunct = parentFunct;
	}

	/**
	 * Gets the prog.
	 *
	 * @return the prog
	 */
	public Integer getProg() {
		return prog;
	}

	/**
	 * Sets the prog.
	 *
	 * @param prog the new prog
	 */
	public void setProg(Integer prog) {
		this.prog = prog;
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
		result = prime * result + ((functId == null) ? 0 : functId.hashCode());
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
		SbiFunctions other = (SbiFunctions) obj;
		if (functId == null) {
			if (other.functId != null)
				return false;
		} else if (!functId.equals(other.functId))
			return false;
		return true;
	}

}