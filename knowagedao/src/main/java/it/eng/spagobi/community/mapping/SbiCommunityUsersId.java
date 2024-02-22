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
package it.eng.spagobi.community.mapping;

import it.eng.spagobi.commons.metadata.SbiHibernateModel;

// Generated 15-lug-2013 11.45.55 by Hibernate Tools 3.4.0.CR1

/**
 * SbiCommunityUsersId generated by hbm2java
 */
public class SbiCommunityUsersId extends SbiHibernateModel{

	private int communityId;
	private String userId;

	public SbiCommunityUsersId() {
	}

	public SbiCommunityUsersId(int communityId, String userId) {
		this.communityId = communityId;
		this.userId = userId;
	}

	public int getCommunityId() {
		return this.communityId;
	}

	private void setCommunityId(int communityId) {
		this.communityId = communityId;
	}

	public String getUserId() {
		return this.userId;
	}

	private void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SbiCommunityUsersId))
			return false;
		SbiCommunityUsersId castOther = (SbiCommunityUsersId) other;

		return (this.getCommunityId() == castOther.getCommunityId())
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCommunityId();
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}
