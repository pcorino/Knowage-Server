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
package it.eng.spagobi.profiling.bean;

// Generated 22-dic-2009 14.20.04 by Hibernate Tools 3.2.4.GA

/**
 * SbiUserAttributesId generated by hbm2java
 */
public class SbiUserAttributesId implements java.io.Serializable {

	private int id;
	private int attributeId;

	public SbiUserAttributesId() {
	}

	public SbiUserAttributesId(int id, int attributeId) {
		this.id = id;
		this.attributeId = attributeId;
	}

	public SbiUserAttributesId(int attributeId) {
		this.attributeId = attributeId;
	}
	
	public int getId() {
		return this.id;
	}

	private void setId(int id) {
		this.id = id;
	}
	
	public void changeId(int id) {
		this.setId(id);
	}

	public int getAttributeId() {
		return this.attributeId;
	}

	private void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}
	
	public void changeAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SbiUserAttributesId))
			return false;
		SbiUserAttributesId castOther = (SbiUserAttributesId) other;

		return (this.getId() == castOther.getId())
				&& (this.getAttributeId() == castOther.getAttributeId());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getId();
		result = 37 * result + this.getAttributeId();
		return result;
	}

}
