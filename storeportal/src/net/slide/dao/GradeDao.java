package net.slide.dao;

import java.util.ArrayList;

import net.slide.bean.SearchTO;
import net.slide.pojo.TblArea;
import net.slide.pojo.TblBanner;
import net.slide.pojo.TblDept;
import net.slide.pojo.TblGrade;
import net.slide.pojo.TblStore;
import net.slide.util.HibernateUtil;
import net.slide.util.TransException;
import net.slide.pojores.TblMasterRes;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GradeDao {
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblGrade> search(String searchTxt) {
		ArrayList<TblGrade> gradeList = null;
		Session session = HibernateUtil.currentSession();
		try {
			
			Query query = session.createQuery("FROM TblGrade AS gd WHERE 1 = 1 ");
			if (searchTxt != null && !searchTxt.equals("")) {
				searchTxt = searchTxt.replace(' ', '%');
			
				query=session.createQuery("FROM TblGrade AS gd WHERE upper(gd.grade) LIKE :searchTxt ")
									.setString("searchTxt", "%"+searchTxt.toUpperCase()+"%");
			}
			gradeList = (ArrayList<TblGrade>) query.list();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return gradeList;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> gradeList(String searchTxt)
	{
		ArrayList<TblMasterRes> retval = null;
		ArrayList<TblGrade> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblGrade AS f ORDER BY gradeId");
			if(searchTxt != null && searchTxt.length() > 0){
				searchTxt = searchTxt.replace(' ', '%');
				query =  session.createQuery("FROM TblGrade AS f WHERE upper(gradeName) LIKE :searchTxt ORDER BY gradeId").setText("searchTxt", "%"+searchTxt.toUpperCase()+"%");
			}
			resList = (ArrayList<TblGrade>) query.list();

			retval = new ArrayList<TblMasterRes>();
			for(TblGrade f: resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName(f.getGradeValue());
				res.setParentName(f.getGradeName());
				res.setId(f.getGradeId());
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	public TblGrade getGradeById(int id){
		TblGrade tblGrade = null;
		Session session = HibernateUtil.currentSession();
		try{
			tblGrade = (TblGrade)session.get(TblGrade.class, id);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return tblGrade;
	}
	
	
	@SuppressWarnings("unchecked")
	
	public long getgradeCountBygrade(String gradeName) {
		long cnt = 0;
		StringBuffer st = new StringBuffer();
		
		st.append("SELECT count(*) FROM TblGrade AS g WHERE UPPER(g.gradeName) = :gradeName");
		
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());
			
			query.setString("gradeName", gradeName.toUpperCase());
			cnt = (Long) query.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return cnt;
	}
	public boolean addGrade(TblGrade grade){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {

			transaction = session.beginTransaction();

			
			session.saveOrUpdate(grade);
			
			transaction.commit();
			
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	public TblGrade getGradeBygradename(String gradename) {
		TblGrade grade = null;
		StringBuffer st = new StringBuffer();
		st.append("FROM Tblgrade AS st WHERE UPPER(st.gradename) = :gradename ");
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());
			
			query.setString("gradename", gradename.toUpperCase());
			grade = (TblGrade) query.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return grade;
	}
	public boolean updateGrade(TblGrade grade){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {

			transaction = session.beginTransaction();
			session.merge(grade);
			transaction.commit();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	public boolean deleteGrade(int gradeId) throws TransException{
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			
			TblGrade a = (TblGrade)session.get(TblGrade.class, gradeId);
			session.delete(a);
			
			transaction.commit();
			retval = true;
		}catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			throw new TransException("Grade already in use ....!!!");
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	
}
