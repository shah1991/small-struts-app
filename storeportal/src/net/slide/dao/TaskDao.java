package net.slide.dao;

import java.util.ArrayList;
import java.util.List;

import net.slide.pojo.TblRole;
import net.slide.pojo.TblSystxno;
import net.slide.pojo.TblTaskPortfolio;

import net.slide.pojores.TblMasterRes;
import net.slide.util.HibernateUtil;
import net.slide.util.TransException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unused")
public class TaskDao extends BaseDao {
@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> taskPortfoloioList(String searchTxt)
	{
		ArrayList<TblMasterRes> retval = null;
		ArrayList<TblTaskPortfolio> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblTaskPortfolio AS f ORDER BY portfolioName");
			if(searchTxt != null && searchTxt.length() > 0){
				query=session.createQuery("FROM TblTaskPortfolio AS f WHERE upper(f.portfolioName) LIKE :searchTxt ")
						.setString("searchTxt", "%"+searchTxt.toUpperCase()+"%");
				
			}
			resList = (ArrayList<TblTaskPortfolio>) query.list();

			retval = new ArrayList<TblMasterRes>();
			for(TblTaskPortfolio f: resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName(f.getPortfolioName());
				res.setId(f.getPortfolioId());
//				if(f.getTblTaskTopics() != null)
//					res.setDtlCount(f.getTblTaskTopics().size());
//				else
					res.setDtlCount(0);
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}



//@SuppressWarnings({ "rawtypes" })
//public String maxTaskPortfoloiocode(){
//	String retval=null;
//	Session session = HibernateUtil.currentSession();
//	try {
//		Query  query =  session.createSQLQuery("SELECT  MAX(portfolio_cd) from tbl_task_portfolio");
//		List list = query.list();
//		
//		retval=list.get(0).toString();
//		
//		
//	}catch (HibernateException e) {
//		e.printStackTrace();
//	}
//	HibernateUtil.closeSession();
//	return retval;
//	
//}

	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskPortfolio> getTaskPortfolioList()
	{
		ArrayList<TblTaskPortfolio> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblTaskPortfolio AS f ORDER BY portfolioName");
			resList = (ArrayList<TblTaskPortfolio>) query.list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}
	/**
	 * Get Task By ID
	 * @param id
	 * @return
	 */
	public TblTaskPortfolio getPortfolioById(int id){
		TblTaskPortfolio f = null;
		Session session = HibernateUtil.currentSession();
		try{
			f = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, id);
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return f;
	}
	
	
	
	/**
	 * Get Task by Name
	 * @param pfName
	 * @return
	 */
	public TblTaskPortfolio getPortfolioByName(String pfName) {
		TblTaskPortfolio retval = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery("FROM TblTaskPortfolio AS f WHERE UPPER(f.portfolioName) = :pfName ");
			query.setString("pfName", pfName.toUpperCase());
			retval = (TblTaskPortfolio) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	/**
	 * Save Task Details
	 * @param tblTaskPortfolio
	 * @return
	 */
	public String saveTaskPortfoloio(TblTaskPortfolio tblTaskPortfolio){
		String portfolioCd = null;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();

			portfolioCd = generateTxnCode(session, "PF", "PF");
			tblTaskPortfolio.setPortfolioCd(portfolioCd);
			
			session.saveOrUpdate(tblTaskPortfolio);
			transaction.commit();
			HibernateUtil.closeSession();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			portfolioCd = null;
		} 
		
		return portfolioCd;		
	}
	
	/**
	 * Delete Task
	 * @param grpId
	 * @return
	 */
	public boolean deleteTaskPortfoloio(int id) throws TransException{
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();

			TblTaskPortfolio c = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, id);
			session.delete(c);
			
			transaction.commit();
			retval = true;
		}catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			throw new TransException("Portfolio already in use ....!!!");
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	
	/*
	 * Update Task Portfoloio
	 */
	
	
	public boolean updatePortfoloio(TblTaskPortfolio r){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			session.merge(r);
			transaction.commit();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
}
