package net.slide.dao;

import java.util.ArrayList;

import net.slide.pojo.TblBanner;
import net.slide.pojo.TblDept;
import net.slide.pojores.TblMasterRes;
import net.slide.util.HibernateUtil;
import net.slide.util.TransException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeptDao {
	/**
	 * Get Department list for main with search string
	 * @param searchTxt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> deptList(String searchTxt)
	{
		ArrayList<TblMasterRes> retval = null;
		ArrayList<TblDept> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblDept AS f ORDER BY deptCd");
			if(searchTxt != null && searchTxt.length() > 0){
				searchTxt = searchTxt.replace(' ', '%');
				query =  session.createQuery("FROM TblDept AS f WHERE upper(deptCd||deptName) LIKE :searchTxt ORDER BY deptCd").setText("searchTxt", "%"+searchTxt.toUpperCase()+"%");
			}
			resList = (ArrayList<TblDept>) query.list();

			retval = new ArrayList<TblMasterRes>();
			for(TblDept f: resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName(f.getDeptName());
				res.setParentName(f.getDeptCd());
				res.setId(f.getDeptId());
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}

	
	@SuppressWarnings("unchecked")
	public ArrayList<TblDept> listDept() {
		ArrayList<TblDept> deptList = null;
		Session session = HibernateUtil.currentSession();
		try {
			
			Query query = session.createQuery("FROM TblDept AS a ");
			deptList = (ArrayList<TblDept>) query.list();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return deptList;
	}
	

//	public TblDept getDeptById(int id){
//		TblDept tblDept = null;
//		Session session = HibernateUtil.currentSession();
//		try{
//			 tblDept = (TblDept)  session.createQuery("FROM TblDept d "+
//											"INNER JOIN FETCH d.tblUser  deptId:=deptId " ).setInteger("deptId", id).uniqueResult();
//		}catch (HibernateException e) {
//			e.printStackTrace();
//		}
//		HibernateUtil.closeSession();
//		return tblDept;
//	}
	public TblDept getDeptById(int id){
		TblDept tblDept = null;
		Session session = HibernateUtil.currentSession();
		try{
			tblDept = (TblDept)session.get(TblDept.class, id);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return tblDept;
	}
	
	public TblDept getDeptByCode(String deptCd) {
		TblDept store = null;
		StringBuffer st = new StringBuffer();
		
		st.append("FROM TblDept AS st WHERE UPPER(st.deptCd) = :deptCd ");
		
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());
			
			query.setString("deptCd", deptCd.toUpperCase());
			store = (TblDept) query.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return store;
	}
	
	public long getDeptCountByCode(String deptCd) {
		long cnt = 0;
		StringBuffer st = new StringBuffer();
		
		st.append("SELECT count(g.id) FROM TblDept AS g WHERE UPPER(g.deptCd) = :deptCd");
		
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());
			
			query.setString("deptCd", deptCd.toUpperCase());
			cnt = (Long) query.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return cnt;
	}
	
	
	public boolean updateDept(TblDept dept){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {

			transaction = session.beginTransaction();
			session.merge(dept);
			transaction.commit();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	
	public boolean deleteDept(int deptId) throws TransException{
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			
			TblDept a = (TblDept)session.get(TblDept.class, deptId);
			session.delete(a);
			
			transaction.commit();
			retval = true;
		}catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			throw new TransException("Department already in use ....!!!");
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	public boolean addDept(TblDept dept){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {

			transaction = session.beginTransaction();

			TblBanner tblBanner = (TblBanner)session.get(TblBanner.class, 1);
			dept.setTblBanner(tblBanner);
			session.saveOrUpdate(dept);
			
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
