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
package it.eng.spagobi.tools.dataset.actions;

import java.util.List;
import java.util.stream.Collectors;

import it.eng.spago.error.EMFUserError;
import it.eng.spagobi.commons.bo.Domain;
import it.eng.spagobi.commons.bo.UserProfile;
import it.eng.spagobi.commons.constants.SpagoBIConstants;
import it.eng.spagobi.commons.dao.DAOFactory;
import it.eng.spagobi.commons.dao.IDomainDAO;
import it.eng.spagobi.commons.metadata.SbiDomains;
import it.eng.spagobi.commons.utilities.UserUtilities;
import it.eng.spagobi.tools.dataset.bo.IDataSet;
import it.eng.spagobi.utilities.exceptions.ActionNotPermittedException;

public abstract class AbstractDatasetActionsChecker implements IDatasetActionsChecker {

	private final UserProfile userProfile;
	private final IDataSet dataset;

	public AbstractDatasetActionsChecker(UserProfile userProfile, IDataSet dataset) {
		this.userProfile = userProfile;
		this.dataset = dataset;
	}

	protected UserProfile getUserProfile() {
		return userProfile;
	}

	protected IDataSet getDataset() {
		return dataset;
	}

	@Override
	public void canSee() throws ActionNotPermittedException {
		IDataSet dataSet = getDataset();

		IDomainDAO domainDAO = DAOFactory.getDomainDAO();

		boolean isAdmin = UserUtilities.hasAdministratorRole(userProfile);
		boolean isDeveloper = UserUtilities.hasDeveloperRole(userProfile);
		boolean isUser = UserUtilities.hasUserRole(userProfile);
		boolean isTester = UserUtilities.hasTesterRole(userProfile);
		boolean isModelAdministrator = UserUtilities.hasModelAdminRole(userProfile);

		Object userId = userProfile.getUserUniqueIdentifier();

		SbiDomains scopeEnterprise = null;
		SbiDomains scopeTechnical = null;
		SbiDomains scopeUser = null;

		try {
			scopeEnterprise = domainDAO.loadSbiDomainByCodeAndValue("DS_SCOPE", SpagoBIConstants.DS_SCOPE_ENTERPRISE);
			scopeTechnical = domainDAO.loadSbiDomainByCodeAndValue("DS_SCOPE", SpagoBIConstants.DS_SCOPE_TECHNICAL);
			scopeUser = domainDAO.loadSbiDomainByCodeAndValue("DS_SCOPE", SpagoBIConstants.DS_SCOPE_USER);
		} catch (EMFUserError e) {
			datasetNotVisible();
		}

		List<Integer> categories = UserUtilities.getDataSetCategoriesByUser(userProfile).stream()
				.map(Domain::getValueId).collect(Collectors.toList());

		String currentOwner = dataSet.getOwner();
		String currentScope = dataSet.getScopeCd();
		Integer currentCategory = dataSet.getCategoryId();

		boolean owned = currentOwner.equals(userId);
		boolean isScopeEnterprise = scopeEnterprise.getValueCd().equals(currentScope);
		boolean isScopeTechnical = scopeTechnical.getValueCd().equals(currentScope);
		boolean isScopeUser = scopeUser.getValueCd().equals(currentScope);
		boolean inVisibleCategories = categories.contains(currentCategory);

		if (isAdmin) {
			// All dataset
		} else if (isDeveloper) {
			// @formatter:off
			if (!owned
					&& !(isScopeEnterprise && inVisibleCategories)
					&& !(isScopeTechnical && inVisibleCategories)
					&& !(isScopeUser && inVisibleCategories)) {
				datasetNotVisible();
			}
			// @formatter:on
		} else if (isUser || isTester || isModelAdministrator) {
			// @formatter:off
			if (!owned
					&& !(isScopeEnterprise && inVisibleCategories)
					&& !(isScopeTechnical)
					&& !(isScopeUser && inVisibleCategories)) {
				datasetNotVisible();
			}
			// @formatter:on
		}

	}

	@Override
	public void canDelete() throws ActionNotPermittedException {
		IDataSet dataSet = getDataset();

		IDomainDAO domainDAO = DAOFactory.getDomainDAO();

		boolean isAdmin = UserUtilities.hasAdministratorRole(userProfile);
		boolean isDeveloper = UserUtilities.hasDeveloperRole(userProfile);
		boolean isUser = UserUtilities.hasUserRole(userProfile);
		boolean isTester = UserUtilities.hasTesterRole(userProfile);
		boolean isModelAdministrator = UserUtilities.hasModelAdminRole(userProfile);

		Object userId = userProfile.getUserUniqueIdentifier();

		SbiDomains scopeEnterprise = null;
		SbiDomains scopeTechnical = null;
		SbiDomains scopeUser = null;

		try {
			scopeEnterprise = domainDAO.loadSbiDomainByCodeAndValue("DS_SCOPE", SpagoBIConstants.DS_SCOPE_ENTERPRISE);
			scopeTechnical = domainDAO.loadSbiDomainByCodeAndValue("DS_SCOPE", SpagoBIConstants.DS_SCOPE_TECHNICAL);
			scopeUser = domainDAO.loadSbiDomainByCodeAndValue("DS_SCOPE", SpagoBIConstants.DS_SCOPE_USER);
		} catch (EMFUserError e) {
			datasetNotVisible();
		}

		List<Integer> categories = UserUtilities.getDataSetCategoriesByUser(userProfile).stream()
				.map(Domain::getValueId).collect(Collectors.toList());

		String currentOwner = dataSet.getOwner();
		String currentScope = dataSet.getScopeCd();
		Integer currentCategory = dataSet.getCategoryId();

		boolean owned = currentOwner.equals(userId);
		boolean isScopeEnterprise = scopeEnterprise.getValueCd().equals(currentScope);
		boolean isScopeTechnical = scopeTechnical.getValueCd().equals(currentScope);
		boolean isScopeUser = scopeUser.getValueCd().equals(currentScope);
		boolean inVisibleCategories = categories.contains(currentCategory);

		if (isAdmin) {
			// All dataset
		} else if (isDeveloper) {
			// @formatter:off
			if (!owned
					&& !(isScopeEnterprise && inVisibleCategories)
					&& !(isScopeTechnical && inVisibleCategories)
					&& !(isScopeUser && inVisibleCategories)) {
				datasetNotVisible();
			}
			// @formatter:on
		} else if (isUser || isTester || isModelAdministrator) {
			// @formatter:off
			if (!owned
					&& !(isScopeEnterprise && inVisibleCategories)
					&& !(isScopeTechnical)
					&& !(isScopeUser && inVisibleCategories)) {
				datasetNotVisible();
			}
			// @formatter:on
		}

	}

	private void datasetNotVisible() throws ActionNotPermittedException {
		throw new ActionNotPermittedException("User cannot see dataset " + dataset.getLabel(),
				"dataset.error.qbe.model.not.visible");
	}

}
