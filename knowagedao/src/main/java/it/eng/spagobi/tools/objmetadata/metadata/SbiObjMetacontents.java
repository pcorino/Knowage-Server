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
package it.eng.spagobi.tools.objmetadata.metadata;

import java.util.Date;

import org.json.JSONObject;

// Generated 18-nov-2009 17.58.50 by Hibernate Tools 3.1.0 beta3

import it.eng.spagobi.analiticalmodel.document.metadata.SbiObjects;
import it.eng.spagobi.analiticalmodel.document.metadata.SbiSubObjects;
import it.eng.spagobi.commons.metadata.SbiBinContents;
import it.eng.spagobi.commons.metadata.SbiHibernateModel;

/**
 * SbiObjMetacontents generated by hbm2java
 */

public class SbiObjMetacontents extends SbiHibernateModel {

	// Fields

	private Integer objMetacontentId;
	private SbiObjects sbiObjects;
	private SbiSubObjects sbiSubObjects;
	private SbiBinContents sbiBinContents;
	private Integer objmetaId;
	private Date creationDate;
	private Date lastChangeDate;
	private JSONObject additionalInfo;

	// Constructors

	/** default constructor */
	public SbiObjMetacontents() {
	}

	/** minimal constructor */
	public SbiObjMetacontents(Integer objMetacontentId, SbiObjects sbiObjects, Integer objmetaId, Date creationDate, Date lastChangeDate) {
		this.objMetacontentId = objMetacontentId;
		this.sbiObjects = sbiObjects;
		this.objmetaId = objmetaId;
		this.creationDate = creationDate;
		this.lastChangeDate = lastChangeDate;
	}

	/** full constructor */
	public SbiObjMetacontents(Integer objMetacontentId, SbiObjects sbiObjects, SbiSubObjects sbiSubObjects, SbiBinContents sbiBinContents, Integer objmetaId,
			Date creationDate, Date lastChangeDate, JSONObject additionalInfo) {
		this.objMetacontentId = objMetacontentId;
		this.sbiObjects = sbiObjects;
		this.sbiSubObjects = sbiSubObjects;
		this.sbiBinContents = sbiBinContents;
		this.objmetaId = objmetaId;
		this.creationDate = creationDate;
		this.lastChangeDate = lastChangeDate;
		this.additionalInfo = additionalInfo;
	}

	// Property accessors

	public Integer getObjMetacontentId() {
		return this.objMetacontentId;
	}

	private void setObjMetacontentId(Integer objMetacontentId) {
		this.objMetacontentId = objMetacontentId;
	}

	public SbiObjects getSbiObjects() {
		return this.sbiObjects;
	}

	public void setSbiObjects(SbiObjects sbiObjects) {
		this.sbiObjects = sbiObjects;
	}

	public SbiSubObjects getSbiSubObjects() {
		return this.sbiSubObjects;
	}

	public void setSbiSubObjects(SbiSubObjects sbiSubObjects) {
		this.sbiSubObjects = sbiSubObjects;
	}

	public SbiBinContents getSbiBinContents() {
		return this.sbiBinContents;
	}

	public void setSbiBinContents(SbiBinContents sbiBinContents) {
		this.sbiBinContents = sbiBinContents;
	}

	public Integer getObjmetaId() {
		return this.objmetaId;
	}

	public void setObjmetaId(Integer objmetaId) {
		this.objmetaId = objmetaId;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastChangeDate() {
		return this.lastChangeDate;
	}

	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public JSONObject getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(JSONObject additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

}
