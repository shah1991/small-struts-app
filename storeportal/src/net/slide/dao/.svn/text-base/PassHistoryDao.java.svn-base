package net.slide.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



import net.slide.pojo.TblUser;
import net.slide.pojo.TblPassHistory;

import net.slide.util.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;

public class PassHistoryDao {
	
	@SuppressWarnings("unchecked")
	public Boolean getSearchList(String searchValue, String password) {
		Session session = HibernateUtil.currentSession();
		searchValue = searchValue.toLowerCase();
		try {
                        ScrollableResults results = session.createSQLQuery("SELECT * FROM user_passhist "
                                + "WHERE lower(username) = :username ORDER BY created_date DESC")
					.addEntity(TblPassHistory.class)
					.setString("username", searchValue.toLowerCase())
                                        .setMaxResults(4)
					.setReadOnly(true)
					.setCacheable(false)
					.scroll(ScrollMode.FORWARD_ONLY);
			while (results.next()) {
				TblPassHistory pass = (TblPassHistory)results.get()[0];
                                if (pass != null) {     
                                    try{
                                    if (pass.getPassword().equals(password)){
                                        return false; 
                                    }
                                    }catch(NullPointerException ex){

				    }
                		    session.flush();
                        	    session.clear();
                                }         
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return true;
	}    
        
	public TblPassHistory getTblPassHistory(String username){
		TblPassHistory retval = null;
		Session session = HibernateUtil.currentSession();
		try{
			retval = (TblPassHistory)session.createQuery("FROM user_passhist AS d " +
						"WHERE d.username= :username order by createdDate desc")
						.setString("username", username)
                                                .setMaxResults(10);
//						.uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
        
	
	public boolean saveTblPassHistory(TblPassHistory c){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
				
		try {
			
			transaction = session.beginTransaction();
			session.saveOrUpdate(c);
			
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
