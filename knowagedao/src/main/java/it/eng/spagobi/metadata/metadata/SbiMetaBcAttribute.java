package it.eng.spagobi.metadata.metadata;

// Generated 12-apr-2016 10.43.25 by Hibernate Tools 3.4.0.CR1

import it.eng.spagobi.commons.metadata.SbiHibernateModel;

/**
 * SbiMetaBcAttribute generated by hbm2java
 */
public class SbiMetaBcAttribute extends SbiHibernateModel {

	private Integer attributeId;
	private SbiMetaTableColumn sbiMetaTableColumn;
	private SbiMetaBc sbiMetaBc;
	private String name;
	private String type;
	private boolean deleted;

	public SbiMetaBcAttribute() {
	}

	public SbiMetaBcAttribute(SbiMetaTableColumn sbiMetaTableColumn, SbiMetaBc sbiMetaBc, String name, String type, boolean deleted) {
		this.sbiMetaTableColumn = sbiMetaTableColumn;
		this.sbiMetaBc = sbiMetaBc;
		this.name = name;
		this.type = type;
		this.deleted = deleted;
	}

	public Integer getAttributeId() {
		return this.attributeId;
	}

	private void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	public SbiMetaTableColumn getSbiMetaTableColumn() {
		return this.sbiMetaTableColumn;
	}

	public void setSbiMetaTableColumn(SbiMetaTableColumn sbiMetaTableColumn) {
		this.sbiMetaTableColumn = sbiMetaTableColumn;
	}

	public SbiMetaBc getSbiMetaBc() {
		return this.sbiMetaBc;
	}

	public void setSbiMetaBc(SbiMetaBc sbiMetaBc) {
		this.sbiMetaBc = sbiMetaBc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isDeleted() {
		return this.deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void changeAttributeId(Integer bcAttrId) {
		this.setAttributeId(bcAttrId);
		
	}

}
