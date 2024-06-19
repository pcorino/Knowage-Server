package it.eng.spagobi.api.v2.documentdetails.subresources;

import java.util.ArrayList;

/**
 * @author albnale
 * @since 2020/03/06
 */

import java.util.List;

import it.eng.spagobi.analiticalmodel.document.bo.BIObject;
import it.eng.spagobi.analiticalmodel.document.bo.OutputParameter;
import it.eng.spagobi.commons.dao.DAOFactory;
import it.eng.spagobi.tools.crossnavigation.metadata.SbiCrossNavigationPar;

public class OutputParameterResourseAPI {

	public boolean isUsedInCrossNavigations(OutputParameter outputParameter) {
		List<OutputParameter> list = new ArrayList<>();
		list.add(outputParameter);
		return foundRelatedCrossNavs(list);
	}

	public boolean isUsedInCrossNavigations(BIObject document) {
		return foundRelatedCrossNavs(document.getOutputParameters());
	}

	private boolean foundRelatedCrossNavs(List<OutputParameter> outputParameters) {
		for (OutputParameter outputParameter : outputParameters) {

			List<SbiCrossNavigationPar> crossNavigationsFound = DAOFactory.getCrossNavigationDAO()
					.listNavigationsByOutputParameters(outputParameter.getId());

			if (!crossNavigationsFound.isEmpty())
				return true;
		}

		return false;

	}
}
