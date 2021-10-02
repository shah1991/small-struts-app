package net.slide.dao;

import java.util.ArrayList;

import net.slide.pojo.TblBanner;
import net.slide.pojo.TblChklstGroup;
import net.slide.pojo.TblFileGroup;
import net.slide.pojo.TblFileShare;
import net.slide.pojo.TblLink;
import net.slide.pojo.TblLink;
import net.slide.pojo.TblPostForum;
import net.slide.pojo.TblTaskPortfolio;
import net.slide.pojores.TblHomeRes;
import net.slide.pojores.TblMasterRes;
import net.slide.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unused")
public class LinkDao  extends BaseDao {

	@SuppressWarnings("unchecked")
	public ArrayList<TblLink> linkList(String searchTxt)
	{
		ArrayList<TblLink> resList = null;
		
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblLink AS f ORDER BY orderNo");
			if(searchTxt != null && searchTxt.length() > 0){
				searchTxt = searchTxt.replace(' ', '%');
				query =  session.createQuery("FROM TblLink AS f WHERE  upper(title) LIKE :searchTxt ORDER BY orderNo").setText("searchTxt", "%"+searchTxt.toUpperCase()+"%");
			}
			resList = (ArrayList<TblLink>) query.list();

			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}

        @SuppressWarnings("unchecked")
	public String getTitleByUrlId(int topicId)
	{
		String res = "";
		
		Session session = HibernateUtil.currentSession();
		try {
			res  =  (String)session.createSQLQuery("SELECT title FROM tbl_link AS f WHERE  url_id = :searchTxt limit 1")
                                .setInteger("searchTxt", topicId)
                                .uniqueResult();

			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return res;
	}
        
        @SuppressWarnings("unchecked")
	public String getLinkByUrlId(int topicId)
	{
		String res = "";
		
		Session session = HibernateUtil.currentSession();
		try {
			res  =  (String)session.createSQLQuery("SELECT url FROM tbl_link AS f WHERE  url_id = :searchTxt limit 1")
                                .setInteger("searchTxt", topicId)
                                .uniqueResult();

			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return res;
	}
	

	

	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> listURL(String url) {
		ArrayList<TblMasterRes> masterList = new ArrayList<TblMasterRes>();
		ArrayList<TblPostForum> forumList = null;
		ArrayList<TblTaskPortfolio> taskList = null;
		ArrayList<TblChklstGroup> checkList = null;
		ArrayList<TblFileGroup> fileList = null;
		
		Session session = HibernateUtil.currentSession();
		try {
			if(url.equals("F")){
			Query  queryForum =  session.createQuery("FROM TblPostForum AS f ORDER BY forumName");
			forumList = (ArrayList<TblPostForum>) queryForum.list();
			for(TblPostForum f:forumList){
				TblMasterRes res=new TblMasterRes();
				res.setId(f.getId());
				res.setMstName(f.getId()+" - "+f.getForumName());
				masterList.add(res);
				}
			}else if(url.equals("T")){
				Query  queryTask =  session.createQuery("FROM TblTaskPortfolio AS f ORDER BY portfolioName");
				taskList = (ArrayList<TblTaskPortfolio>) queryTask.list();
				for(TblTaskPortfolio f:taskList){
					TblMasterRes res=new TblMasterRes();
					res.setId(f.getPortfolioId());
					res.setMstName(f.getPortfolioId()+" - "+f.getPortfolioName());
					masterList.add(res);
					}
			}else if(url.equals("C")){
				Query  queryCheck =  session.createQuery("FROM TblChklstGroup AS f ORDER BY chklstGroupName");
				checkList = (ArrayList<TblChklstGroup>) queryCheck.list();
				for(TblChklstGroup f:checkList){
					TblMasterRes res=new TblMasterRes();
					res.setId(f.getChklstGroupId());
					res.setMstName(f.getChklstGroupId()+" - "+f.getChklstGroupName());
					masterList.add(res);
					}
			}else if(url.equals("S")){
				Query  queryCheck =  session.createQuery("FROM TblFileGroup AS f ORDER BY groupName");
				fileList = (ArrayList<TblFileGroup>) queryCheck.list();
				for(TblFileGroup f:fileList){
					TblMasterRes res=new TblMasterRes();
					res.setId(f.getGroupId());
					res.setMstName(f.getGroupId()+" - "+f.getGroupName());
					masterList.add(res);
					}
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return masterList;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblLink> listLink() {
		ArrayList<TblLink> deptList = null;
		Session session = HibernateUtil.currentSession();
		try {
			
			Query query = session.createQuery("FROM TblLink AS a ");
			deptList = (ArrayList<TblLink>) query.list();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return deptList;
	}
	
	public TblLink getLinkById(int id){
		TblLink tblLink = null;
		Session session = HibernateUtil.currentSession();
		try{
			tblLink = (TblLink)session.get(TblLink.class, id);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return tblLink;
	}
	
	
	
	public boolean updateLink(TblLink link){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {

			transaction = session.beginTransaction();
			session.merge(link);
			transaction.commit();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	
	public boolean deleteLink(int linkId){
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			
			TblLink a = (TblLink)session.get(TblLink.class, linkId);
			session.delete(a);
			
			transaction.commit();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	public boolean addLink(TblLink link){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {

			transaction = session.beginTransaction();
			session.saveOrUpdate(link);
			
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
