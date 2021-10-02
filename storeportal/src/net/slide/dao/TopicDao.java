package net.slide.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.io.DataOutput.*;
import net.slide.pojo.TblPostForum;
import net.slide.pojo.TblPostTopic;
import net.slide.pojores.TblMasterRes;
import net.slide.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class TopicDao {
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> topicList(String searchTxt)
	{
		ArrayList<TblMasterRes> retval = null;
		ArrayList<TblPostTopic> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblPostTopic AS f ORDER BY topicName");
			if(searchTxt != null && searchTxt.length() > 0){
			//	searchTxt = searchTxt.replace(' ', '%');
				
				query=session.createQuery("FROM TblPostTopic AS f WHERE upper(f.topicName) LIKE :searchTxt ")
									.setString("searchTxt", "%"+searchTxt.toUpperCase()+"%");
			
				
			}
			resList = (ArrayList<TblPostTopic>) query.list();

			retval = new ArrayList<TblMasterRes>();
			for(TblPostTopic f: resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName(f.getTopicName());
				res.setParentName(f.getTblPostForum().getForumName());
				res.setId(f.getId());
				if(f.getTblPosts() != null)
					res.setDtlCount(f.getTblPosts().size());
				else
					res.setDtlCount(0);
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> topicList(TblPostForum f)
	{
                
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			//Query  query =  session.createQuery("FROM TblPostTopic AS f WHERE f.tblPostForum = :forum ORDER BY topicName").setEntity("forum", f);
			//Query  query =  session.createQuery("FROM TblPostTopic AS f ");
				Query  query =  session.createSQLQuery("select a.id AS topicId,a.topic_name AS topicName,(SELECT COUNT(b.forum_id) FROM " +
                                "tbl_post b WHERE b.topic_id=a.id) AS counter FROM tbl_post_topic a " + 
                                "WHERE a.forum_id = :forum ORDER BY a.topic_name").setEntity("forum",f);
                                    
                         ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
		        for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
                                res.setId((Integer)val[0]);
				res.setMstName((String)val[1]);
				res.setDtlCount(((BigInteger)val[2]).intValue());
				retval.add(res);
			}
                        //System.out.println(resList.get(0));
                }catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
        
        /*@SuppressWarnings("unchecked")
	public ArrayList<TblPostTopic> topicList(int id)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			//Query  query =  session.createQuery("FROM TblPostTopic AS f WHERE f.tblPostForum = :forum ORDER BY topicName").setEntity("forum", f);
			//Query  query =  session.createQuery("FROM TblPostTopic AS f ");
			Query  query =  session.createSQLQuery("select a.topic_name,(SELECT COUNT(b.forum_id) FROM " +
                                "tbl_post b WHERE b.topic_id=a.id) AS counter " + 
                                "WHERE a.forum_id = :forum ORDER BY topicName").setInteger("forum_id",id);
			
                        //resList = (ArrayList<TblPostTopic>) query.list();
                        ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName((String)val[0]);
				res.setDtlCount(((BigInteger)val[1]).intValue());
				res.setParentName((String)val[0]);
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}*/
	
	/**
	 * Get Topic By ID
	 * @param id
	 * @return
	 */
	public TblPostTopic getTopicById(int id){
		TblPostTopic retval = null;
		Session session = HibernateUtil.currentSession();
		try{
			Query query = session.createQuery("FROM TblPostTopic AS f " +
								"INNER JOIN FETCH f.tblPostForum  WHERE f.id = :topicId ");
			
			query.setInteger("topicId", id);
			retval = (TblPostTopic) query.uniqueResult();
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
		
	/**
	 * Get Topic by Name
	 * @param f TODO
	 * @param topicName
	 * @return
	 */
	public TblPostTopic getTopicByName(TblPostForum f, String topicName) {
		TblPostTopic retval = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery("FROM TblPostTopic AS f WHERE f.tblPostForum = :forum AND UPPER(f.topicName) = :topicName ");
			query.setString("topicName", topicName.toUpperCase());
			query.setEntity("forum", f);
			retval = (TblPostTopic) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	/**
	 * Save Topic Details
	 * @param f
	 * @return
	 */
	public boolean saveTopic(TblPostTopic f){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(f);
			transaction.commit();
			HibernateUtil.closeSession();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		
		return retval;		
	}
	
	/**
	 * Delete Topic
	 * @param grpId
	 * @return
	 */
	public boolean deleteTopic(int id){
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();

			TblPostTopic c = (TblPostTopic)session.get(TblPostTopic.class, id);
			session.delete(c);
			
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
