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
package it.eng.qbe.statement.jpa;

import it.eng.qbe.query.Query;
import it.eng.qbe.statement.IConditionalOperator;
import it.eng.qbe.statement.runtime.AbstractStatementHavingClause;

import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author Andrea Gioia (andrea.gioia@eng.it)
 *
 */
public class JPQLStatementHavingClause extends AbstractStatementHavingClause {
	

	
	public static transient Logger logger = Logger.getLogger(JPQLStatementHavingClause.class);
	
	public static String build(JPQLStatement parentStatement, Query query, Map<String, Map<String, String>> entityAliasesMaps){
		JPQLStatementHavingClause clause = new JPQLStatementHavingClause(parentStatement);
		return clause.buildClause(query, entityAliasesMaps);
	}
	
	protected JPQLStatementHavingClause(JPQLStatement statement) {
		parentStatement = statement;
	}
	
	
	@Override
	public IConditionalOperator getOperator(String operator){
		return JPQLStatementConditionalOperators.getOperator( operator );
	}

	
}
