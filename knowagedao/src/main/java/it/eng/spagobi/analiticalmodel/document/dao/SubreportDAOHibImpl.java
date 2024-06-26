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
package it.eng.spagobi.analiticalmodel.document.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.eng.spago.error.EMFErrorSeverity;
import it.eng.spago.error.EMFUserError;
import it.eng.spagobi.analiticalmodel.document.metadata.SbiObjects;
import it.eng.spagobi.analiticalmodel.document.metadata.SbiSubreports;
import it.eng.spagobi.analiticalmodel.document.metadata.SbiSubreportsId;
import it.eng.spagobi.commons.bo.Subreport;
import it.eng.spagobi.commons.dao.AbstractHibernateDAO;

/**
 * @author Gioia
 *
 */
public class SubreportDAOHibImpl extends AbstractHibernateDAO implements ISubreportDAO {

	/*
	 * (non-Javadoc)
	 *
	 * @see it.eng.spagobi.analiticalmodel.document.dao.ISubreportDAO#loadSubreportsByMasterRptId(java.lang.Integer)
	 */
	@Override
	public List<Subreport> loadSubreportsByMasterRptId(Integer masterRptId) throws EMFUserError {
		Session aSession = null;
		Transaction tx = null;
		List<Subreport> realResult = new ArrayList<>();
		String hql = null;
		Query hqlQuery = null;

		try {
			aSession = getSession();
			tx = aSession.beginTransaction();

			hql = " from SbiSubreports as subreport where subreport.id.masterReport.biobjId = ?";

			hqlQuery = aSession.createQuery(hql);
			hqlQuery.setInteger(0, masterRptId.intValue());
			List<SbiSubreports> hibList = hqlQuery.list();

			Iterator<SbiSubreports> it = hibList.iterator();

			while (it.hasNext()) {
				realResult.add(toSubreport(it.next()));
			}
			tx.commit();
		} catch (HibernateException he) {
			rollbackIfActive(tx);
			logException(he);
			throw new EMFUserError(EMFErrorSeverity.ERROR, 100);
		} finally {
			closeSessionIfOpen(aSession);
		}
		return realResult;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.eng.spagobi.analiticalmodel.document.dao.ISubreportDAO#loadSubreportsBySubRptId(java.lang.Integer)
	 */
	@Override
	public List<Subreport> loadSubreportsBySubRptId(Integer subRptId) throws EMFUserError {
		Session aSession = null;
		Transaction tx = null;
		List<Subreport> realResult = new ArrayList<>();
		String hql = null;
		Query hqlQuery = null;

		try {
			aSession = getSession();
			tx = aSession.beginTransaction();

			hql = " from SbiSubreports as subreport " + "where subreport.id.subReport.biobjId = ?";

			hqlQuery = aSession.createQuery(hql);
			hqlQuery.setInteger(0, subRptId.intValue());
			List<SbiSubreports> hibList = hqlQuery.list();

			Iterator<SbiSubreports> it = hibList.iterator();

			while (it.hasNext()) {
				realResult.add(toSubreport(it.next()));
			}
			tx.commit();
		} catch (HibernateException he) {
			rollbackIfActive(tx);
			logException(he);
			throw new EMFUserError(EMFErrorSeverity.ERROR, 100);
		} finally {
			closeSessionIfOpen(aSession);
		}
		return realResult;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.eng.spagobi.analiticalmodel.document.dao.ISubreportDAO#insertSubreport(it.eng.spagobi.commons.bo.Subreport)
	 */
	@Override
	public void insertSubreport(Subreport aSubreport) throws EMFUserError {
		Session aSession = null;
		Transaction tx = null;
		try {
			aSession = getSession();
			tx = aSession.beginTransaction();
			SbiSubreportsId hibSubreportid = new SbiSubreportsId();
			SbiObjects masterReport = (SbiObjects) aSession.load(SbiObjects.class, aSubreport.getMaster_rpt_id());
			SbiObjects subReport = (SbiObjects) aSession.load(SbiObjects.class, aSubreport.getSub_rpt_id());
			hibSubreportid.setMasterReport(masterReport);
			hibSubreportid.setSubReport(subReport);
			SbiSubreports hibSubreport = new SbiSubreports(hibSubreportid);
			updateSbiCommonInfo4Insert(hibSubreport);
			aSession.save(hibSubreport);
			tx.commit();
		} finally {
			closeSessionIfOpen(aSession);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.eng.spagobi.analiticalmodel.document.dao.ISubreportDAO#eraseSubreportByMasterRptId(java.lang.Integer)
	 */
	@Override
	public void eraseSubreportByMasterRptId(Integer masterRptId) throws EMFUserError {
		Session aSession = null;
		Transaction tx = null;
		String hql = null;
		Query hqlQuery = null;
		try {
			aSession = getSession();
			tx = aSession.beginTransaction();

			hql = " from SbiSubreports as subreport where subreport.id.masterReport.biobjId = ?";

			hqlQuery = aSession.createQuery(hql);
			hqlQuery.setInteger(0, masterRptId.intValue());
			List<SbiSubreports> subreports = hqlQuery.list();

			Iterator<SbiSubreports> it = subreports.iterator();
			while (it.hasNext()) {
				aSession.delete(it.next());
			}
			tx.commit();
		} catch (HibernateException he) {
			rollbackIfActive(tx);
			logException(he);
			throw new EMFUserError(EMFErrorSeverity.ERROR, 100);
		} catch (Exception ex) {
			rollbackIfActive(tx);
			logException(ex);
			throw new EMFUserError(EMFErrorSeverity.ERROR, 100);
		} finally {
			closeSessionIfOpen(aSession);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.eng.spagobi.analiticalmodel.document.dao.ISubreportDAO#eraseSubreportBySubRptId(java.lang.Integer)
	 */
	@Override
	public void eraseSubreportBySubRptId(Integer subRptId) throws EMFUserError {
		Session aSession = null;
		Transaction tx = null;
		String hql = null;
		Query hqlQuery = null;
		try {
			aSession = getSession();
			tx = aSession.beginTransaction();

			hql = " from SbiSubreports as subreport " + "where subreport.id.subReport.biobjId = ?";

			hqlQuery = aSession.createQuery(hql);
			hqlQuery.setInteger(0, subRptId.intValue());
			List<SbiSubreports> subreports = hqlQuery.list();

			Iterator<SbiSubreports> it = subreports.iterator();
			while (it.hasNext()) {
				aSession.delete(it.next());
			}
			tx.commit();
		} catch (HibernateException he) {
			rollbackIfActive(tx);
			logException(he);
			throw new EMFUserError(EMFErrorSeverity.ERROR, 100);
		} catch (Exception ex) {
			rollbackIfActive(tx);
			logException(ex);
			throw new EMFUserError(EMFErrorSeverity.ERROR, 100);
		} finally {
			closeSessionIfOpen(aSession);
		}
	}

	/**
	 * From the hibernate subreports at input, gives the corrispondent <code>Subreports</code> object.
	 *
	 * @param hibSubreport the hib subreport
	 *
	 * @return The corrispondent <code>Parameter</code> object
	 */
	public Subreport toSubreport(SbiSubreports hibSubreport) {
		Subreport aSubreport = new Subreport();
		aSubreport.setMaster_rpt_id(hibSubreport.getId().getMasterReport().getBiobjId());
		aSubreport.setSub_rpt_id(hibSubreport.getId().getSubReport().getBiobjId());
		return aSubreport;
	}

}
