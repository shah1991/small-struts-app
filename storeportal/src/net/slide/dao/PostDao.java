package net.slide.dao;

import java.math.BigInteger;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.slide.pojo.TblArea;
import net.slide.pojo.TblPostArea;
import net.slide.pojo.TblPostForum;
import net.slide.pojo.TblPost;
import net.slide.pojo.TblPostStore;
import net.slide.pojo.TblPostTopic;
import net.slide.pojo.TblPostUpload;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblTask;
import net.slide.pojo.TblTaskArea;
import net.slide.pojo.TblTaskPortfolio;
import net.slide.pojo.TblTaskStore;
import net.slide.pojores.TblMasterRes;
import net.slide.pojores.TblTaskRes;
import net.slide.pojores.TblUploadRes;
import net.slide.util.HibernateUtil;
import net.slide.util.TransException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class PostDao {
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblPost> getPostList(String searchTxt)
	{
		ArrayList<TblPost> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblPost AS f ORDER BY postSubject");
			if(searchTxt != null && searchTxt.length() > 0){
				searchTxt = searchTxt.replace(' ', '%');
				query =  session.createQuery("FROM TblPost AS f WHERE postSubject LIKE :searchTxt ORDER BY created_date").setText("searchTxt", "%"+searchTxt+"%");
			}
			resList = (ArrayList<TblPost>) query.list();
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getPostYears(int topicId)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT TO_CHAR(updated_date,'YYYY') AS yyyy, COUNT(*) AS cnt " +
												"FROM  tbl_post " +
												"WHERE topic_id = :topic " +
												"GROUP BY yyyy " +
												"ORDER BY yyyy")
												.setInteger("topic", topicId);;
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName("Browse Posts "+(String)val[0]);
				res.setDtlCount(((BigInteger)val[1]).intValue());
				res.setParentName((String)val[0]);
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getPostMonths(int topicId, String postYear)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT  TO_CHAR(updated_date,'Month') AS mmm, TO_CHAR(updated_date,'MM') AS mm, COUNT(*) AS cnt " +
												"FROM  tbl_post " +
												"WHERE TO_CHAR(updated_date,'YYYY') = :yyyy " +
												"AND topic_id = :topic " +
												"GROUP BY mmm, mm " +
												"ORDER BY mmm")
												.setString("yyyy", postYear)
												.setInteger("topic", topicId);
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName((String)val[0]);
				res.setParentName(""+(String)val[1]);
				res.setDtlCount(((BigInteger)val[2]).intValue());
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getPostWeek(int topicId, String postYear, String postMonth)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT ((cast(TO_CHAR(updated_date,'DD') as integer))/ 7+1) AS ww, COUNT(*) AS cnt " +
													"FROM  tbl_post " +
													"WHERE TO_CHAR(updated_date,'YYYY') = :yyyy and TO_CHAR(updated_date,'MM') = :mm " +
													"AND topic_id = :topic " +
													"GROUP BY ww " +
													"ORDER BY ww")
													.setString("yyyy", postYear)
													.setString("mm", postMonth)
													.setInteger("topic", topicId);
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName("Week "+(Integer)val[0]);
				res.setParentName(""+(Integer)val[0]);
				res.setDtlCount(((BigInteger)val[1]).intValue());
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}

	
	@SuppressWarnings("unchecked")
	public ArrayList<TblPost> getPosts(int topicId, String postYear, String postMonth, Integer postWeek)
	{
		ArrayList<TblPost> retval = new ArrayList<TblPost>();
		Session session = HibernateUtil.currentSession();
		try {
			
			TblPostTopic t = (TblPostTopic)session.get(TblPostTopic.class, topicId);
			
			Query  query =  session.createQuery("FROM  TblPost f " +
													"WHERE TO_CHAR(updatedDate,'YYYYMM') = :yyyymm AND " +
													"(((cast(TO_CHAR(updatedDate,'DD') as integer))/ 7+1)) = :ww " +
													"AND f.tblPostTopic = :topic " +
													"ORDER BY updatedDate")
													.setString("yyyymm", postYear+postMonth)
													.setInteger("ww", postWeek)
													.setEntity("topic", t);
			retval = (ArrayList<TblPost>) query.list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}

	
	/**
	 * Get Post By ID
	 * @param id
	 * @return
	 */
	public TblPost getPostById(int id){
		TblPost f = null;
		Session session = HibernateUtil.currentSession();
		try{
			f = (TblPost)session.get(TblPost.class, id);
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return f;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblPostUpload> getPostUpload(TblPost p){
		ArrayList<TblPostUpload> alist=null;
		Session session = HibernateUtil.currentSession();
		try{
			Query query = session.createQuery("FROM TblPostUpload AS f WHERE f.tblPost = :tblPost");
			query.setEntity("tblPost", p);
			alist=(ArrayList<TblPostUpload>)query.list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return alist;
	}
	
	/**
	 * Get Topic by Name
	 * @param f TODO
	 * @param topicName
	 * @return
	 */
	public TblPost getPostByName(TblPostForum f, String topicName) {
		TblPost retval = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery("FROM TblPost AS f WHERE f.tblPostForum = :forum AND UPPER(f.topicName) = :topicName ");
			query.setString("topicName", topicName.toUpperCase());
			query.setEntity("forum", f);
			retval = (TblPost) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	/**
	 * Save Topic Details
	 * @param f
	 * @param topicId TODO
	 * @return
	 */
	public boolean savePost(TblPost f, int topicId,int[] alist, int[] slist,ArrayList<TblUploadRes> ulist){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			TblPostTopic t = (TblPostTopic)session.get(TblPostTopic.class, topicId);
			f.setTblPostForum(t.getTblPostForum());
			f.setTblPostTopic(t);
			session.saveOrUpdate(f);

			// Add Task Store
			if(slist != null){
				saveStore(f, slist, session);
			}
			if(alist != null){
				saveArea(f, alist, session);
			}
			
			if(ulist!=null){
				saveUpload(f,ulist,session);
			}
			transaction.commit();
			HibernateUtil.closeSession();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		
		return retval;		
	}
	
	

	
	private void saveUpload(TblPost tblPost, ArrayList<TblUploadRes> ulist, Session session) {
		if(ulist!=null){
			for(TblUploadRes r: ulist){
				
				TblPostUpload u=new TblPostUpload();
				u.setTblPost(tblPost);
				u.setPostAttachPath(r.getAttachPath());
				u.setPostAttachFilename(r.getAttachName());
				session.saveOrUpdate(u);
				
				
				
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void saveArea(TblPost tblPost, int[] slist, Session session) {
		if(slist!=null){
			for(int areaid: slist){
				ArrayList<TblPostArea> arealist=new ArrayList<TblPostArea>();
				TblPostArea ta=new TblPostArea();
				ta.setPostStatus("P");
				TblArea tblArea = (TblArea)session.get(TblArea.class, areaid);
				ta.setTblArea(tblArea);
				ta.setTblPost(tblPost);
				Query query = session.createQuery("FROM TblPostArea AS p WHERE p.tblArea = :area AND p.tblPost = :post ");
				query.setEntity("area", tblArea);
				query.setEntity("post", tblPost);
				arealist=(ArrayList<TblPostArea>)query.list();
				if(arealist.size()==0){
				session.saveOrUpdate(ta);
				}
				
				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void saveStore(TblPost tblPost, int[] slist, Session session) {
		if(slist!=null){
			for(int storeid: slist){
				ArrayList<TblPostStore> store=new ArrayList<TblPostStore>();
				TblPostStore ta=new TblPostStore();
				ta.setPostStatus("P");
				TblStore tblStore = (TblStore)session.get(TblStore.class, storeid);
				ta.setTblStore(tblStore);
				ta.setTblPost(tblPost);
				Query query = session.createQuery("FROM TblPostStore AS p WHERE p.tblStore = :store AND p.tblPost = :post ");
				query.setEntity("store", tblStore);
				query.setEntity("post", tblPost);
				store=(ArrayList<TblPostStore>)query.list();
				if(store.size()==0){
				session.saveOrUpdate(ta);
				}
				
			}
		}
	}
	/**
	 * Delete Topic
	 * @param grpId
	 * @return
	 */
	
	// Store Details
	@SuppressWarnings("unchecked")
	public ArrayList<TblPostStore> getStores(TblPost p){
		
		ArrayList<TblPostStore> storeList=new ArrayList<TblPostStore>();
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			
			Query  query =  session.createQuery("FROM  TblPostStore t INNER JOIN FETCH t.tblStore WHERE t.tblPost = :taskId ")
	    			.setEntity("taskId", p);
			storeList = (ArrayList<TblPostStore>) query.list();
		
			
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return  storeList;
	}
	

	// Area Details
	@SuppressWarnings("unchecked")
	public ArrayList<TblPostArea> getAreas(TblPost p){
		
		ArrayList<TblPostArea> areaList=new ArrayList<TblPostArea>();
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			
			Query  query =  session.createQuery("FROM  TblPostArea t INNER JOIN FETCH t.tblArea WHERE t.tblPost = :taskId ")
	    			.setEntity("taskId", p);
			areaList = (ArrayList<TblPostArea>) query.list();
		
			
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return  areaList;
	}
	
	
	
	//Update Area Post

	public void  getUpdateAreaPost(TblPost p,int areaId){
		
		TblPostArea areapost=new TblPostArea();
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			TblArea a=(TblArea)session.get(TblArea.class, areaId);
			Query  query =  session.createQuery("FROM  TblPostArea t INNER JOIN FETCH t.tblArea WHERE t.tblPost = :tblPost AND t.tblArea= :tblArea ")
					.setEntity("tblArea", a)
			        .setEntity("tblPost", p);
			areapost = (TblPostArea) query.uniqueResult();
			
			areapost.setPostStatus("R");
			session.saveOrUpdate(areapost);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		
	}
	
	//
	public void getUpdateStorePost(TblPost p,int storeId){
	
	TblPostStore storepost=new TblPostStore();
	Transaction transaction = null;
	Session session = HibernateUtil.currentSession();
	try {
		transaction = session.beginTransaction();
		TblStore s=(TblStore)session.get(TblStore.class, storeId);
		Query  query =  session.createQuery("FROM  TblPostStore t INNER JOIN FETCH t.tblStore WHERE t.tblPost = :tblPost AND t.tblStore= :tblStore")
				.setEntity("tblStore", s)
		        .setEntity("tblPost", p);
		storepost = (TblPostStore) query.uniqueResult();
		storepost.setPostStatus("R");
		session.saveOrUpdate(storepost);
		transaction.commit();
		
	} catch (Exception e) {
		transaction.rollback();
		e.printStackTrace();
	} 
	HibernateUtil.closeSession();
	
}

	
	@SuppressWarnings("unchecked")
	public boolean deletePost(int id){
		boolean retval = false;
		ArrayList<TblPostStore> storeList=new ArrayList<TblPostStore>();
		ArrayList<TblPostArea> areaList=new ArrayList<TblPostArea>();
		ArrayList<TblPostUpload> uploadList=new ArrayList<TblPostUpload>();
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			TblPost c = (TblPost)session.get(TblPost.class, id);
			Query  query =  session.createQuery("FROM  TblPostStore t WHERE t.tblPost = :taskId ")
	    			.setEntity("taskId", c);
			storeList = (ArrayList<TblPostStore>) query.list();
			for(TblPostStore t: storeList){
				session.delete(t);
			}
			Query  query1 =  session.createQuery("FROM  TblPostArea t WHERE t.tblPost = :taskId ")
					.setEntity("taskId", c);
			areaList = (ArrayList<TblPostArea>) query1.list();
			for(TblPostArea t: areaList){
				session.delete(t);
			}
			
			Query  upload =  session.createQuery("FROM  TblPostUpload t WHERE t.tblPost = :taskId ")
					.setEntity("taskId", c);
			uploadList = (ArrayList<TblPostUpload>) upload.list();
			for(TblPostUpload t: uploadList){
				session.delete(t);
			}
			
			
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
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> forumList(String searchTxt)
	{
		ArrayList<TblMasterRes> retval = null;
		ArrayList<TblPostForum> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblPostForum AS f ORDER BY forumName");
			if(searchTxt != null && searchTxt.length() > 0){
				
				
				query=session.createQuery("FROM TblPostForum AS f WHERE upper(f.forumName) LIKE :searchTxt ")
						.setString("searchTxt", "%"+searchTxt.toUpperCase()+"%");
				
				}
			resList = (ArrayList<TblPostForum>) query.list();

			retval = new ArrayList<TblMasterRes>();
			for(TblPostForum f: resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName(f.getForumName());
				res.setId(f.getId());
				if(f.getTblPostTopics() != null)
					res.setDtlCount(f.getTblPostTopics().size());
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
	public ArrayList<TblPostForum> getForumList()
	{
		ArrayList<TblPostForum> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblPostForum AS f ORDER BY forumName");
			resList = (ArrayList<TblPostForum>) query.list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}
	/**
	 * Get Forum By ID
	 * @param id
	 * @return
	 */
	public TblPostForum getForumById(int id){
		TblPostForum f = null;
		Session session = HibernateUtil.currentSession();
		try{
			f = (TblPostForum)session.get(TblPostForum.class, id);
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return f;
	}
        
        // change class getforum by id by hafizd on January 24nd 2014
	
        /*@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getForumById(int topicId)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("select a.topic_name,(SELECT COUNT(b.forum_id) FROM " +
                                "tbl_post b WHERE b.topic_id=a.id) AS counter FROM tbl_post_topic a " + 
                                "WHERE a.forum_id = :forum ORDER BY a.topic_name").setInteger("forum",topicId);
			
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
		return retval;
	}*/
        // end class
	
        /**
	 * Get Forum by Name
	 * @param forumName
	 * @return
	 */
	public TblPostForum getForumByName(String forumName) {
		TblPostForum retval = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery("FROM TblPostForum AS f WHERE UPPER(f.forumName) = :forumName ");
			query.setString("forumName", forumName.toUpperCase());
			retval = (TblPostForum) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	/**
	 * Save Forum Details
	 * @param f
	 * @return
	 */
	public boolean saveForum(TblPostForum f){
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
	 * Delete Forum
	 * @param grpId
	 * @return
	 */
	public boolean deleteForum(int id) throws TransException{
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();

			TblPostForum c = (TblPostForum)session.get(TblPostForum.class, id);
			session.delete(c);
			
			transaction.commit();
			retval = true;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			throw new TransException("Forum already in use ....!!!");
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	

	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getAreaForum(int topicId,int postOwnerRole, int postOwnerSite)
	{
		ArrayList<TblMasterRes> retval = null;
		Session session = HibernateUtil.currentSession();
		ArrayList<TblPost> resList = null;
		try {
			TblPostTopic tp = (TblPostTopic)session.get(TblPostTopic.class, topicId);
			Query  query =  session.createQuery("FROM  TblPost ta where ta.postOwnerRole=:postOwnerRole and ta.postOwnerSite=:postOwnerSite and ta.tblPostTopic = :topic  ORDER BY ta.updatedDate DESC")
						    .setInteger("postOwnerRole", postOwnerRole)
						    .setInteger("postOwnerSite", postOwnerSite)
							.setEntity("topic", tp);
			resList = (ArrayList<TblPost>) query.list();
		    retval = new ArrayList<TblMasterRes>();
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		for(TblPost t: resList){

			TblMasterRes res=new TblMasterRes();
			res.setId(t.getPostId());
			res.setMstName(t.getPostSubject());
			
		   	retval.add(res);
		}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	
	}
	

	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getAreaCompletedForum(int topicId,int postOwnerRole, int postOwnerSite)
	{
		ArrayList<TblMasterRes> retval = null;
		Session session = HibernateUtil.currentSession();
		ArrayList<TblPost> resList = null;
		try {
			TblPostTopic tp = (TblPostTopic)session.get(TblPostTopic.class, topicId);
			Query  query =  session.createQuery("FROM  TblPost ta where ta.postOwnerRole=:postOwnerRole and ta.postOwnerSite=:postOwnerSite and ta.tblPostTopic = :topic  ORDER BY ta.updatedDate DESC")
						    .setInteger("postOwnerRole", postOwnerRole)
						    .setInteger("postOwnerSite", postOwnerSite)
							.setEntity("topic", tp);
			resList = (ArrayList<TblPost>) query.list();
		    retval = new ArrayList<TblMasterRes>();
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		for(TblPost t: resList){

			TblMasterRes res=new TblMasterRes();
			res.setId(t.getPostId());
			res.setMstName(t.getPostSubject());
			
		   	retval.add(res);
		}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getStorePending(int storeId, int topicId)
	{
		Session session = HibernateUtil.currentSession();
		ArrayList<TblPostStore> resList = new ArrayList<TblPostStore>();
		ArrayList<TblMasterRes> retval=new ArrayList<TblMasterRes>();
		TblStore ts = (TblStore)session.get(TblStore.class, storeId);

		try {
			TblPostTopic tp = (TblPostTopic)session.get(TblPostTopic.class, topicId);
			Query  query =  session.createQuery("FROM  TblPostStore t WHERE t.tblStore = :storeId   and t.tblPost.tblPostTopic = :topic ORDER BY t.postStoreId DESC")
					 		.setEntity("storeId", ts)
					 		.setEntity("topic", tp);
			resList = (ArrayList<TblPostStore>) query.list();
			
			for(TblPostStore t: resList){

				TblMasterRes res=new TblMasterRes();
				res.setId(t.getTblPost().getPostId());
				res.setMstName(t.getTblPost().getPostSubject());
				
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	

	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getAreaPending(int areaId, int topicId)
	{
		Session session = HibernateUtil.currentSession();
		ArrayList<TblPostArea> resList = new ArrayList<TblPostArea>();
		ArrayList<TblMasterRes> retval=new ArrayList<TblMasterRes>();
		TblArea ts = (TblArea)session.get(TblArea.class, areaId);

		try {
			TblPostTopic tp = (TblPostTopic)session.get(TblPostTopic.class, topicId);
			Query  query =  session.createQuery("FROM  TblPostArea t WHERE t.tblArea = :areaId   and t.tblPost.tblPostTopic = :topic ORDER BY t.postAreaId DESC")
					 		.setEntity("areaId", ts)
					 		.setEntity("topic", tp);
			resList = (ArrayList<TblPostArea>) query.list();
			
			for(TblPostArea t: resList){

				TblMasterRes res=new TblMasterRes();
				res.setId(t.getTblPost().getPostId());
				res.setMstName(t.getTblPost().getPostSubject());
				
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	

	@SuppressWarnings("unchecked")
	public ArrayList<TblPostArea> getAreaNotification(int areaId, int forumId)
	{
		Session session = HibernateUtil.currentSession();
		ArrayList<TblPostArea> resList = new ArrayList<TblPostArea>();
		TblArea ts = (TblArea)session.get(TblArea.class, areaId);

		try {
			TblPostForum tp = (TblPostForum)session.get(TblPostForum.class, forumId);
			Query  query =  session.createQuery("FROM  TblPostArea t WHERE t.tblArea = :areaId AND t.postStatus='P' and t.tblPost.tblPostForum = :forumId ")
					 		.setEntity("areaId", ts)
					 		.setEntity("forumId", tp);
			resList = (ArrayList<TblPostArea>) query.list();
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}
	

	@SuppressWarnings("unchecked")
	public ArrayList<TblPostStore> getStoreNotification(int storeId, int forumId)
	{
		Session session = HibernateUtil.currentSession();
		ArrayList<TblPostStore> resList = new ArrayList<TblPostStore>();
		TblStore ts = (TblStore)session.get(TblStore.class, storeId);

		try {
			TblPostForum tp = (TblPostForum)session.get(TblPostForum.class, forumId);
			Query  query =  session.createQuery("FROM  TblPostStore t WHERE t.tblStore = :storeId AND t.postStatus='P' and t.tblPost.tblPostForum = :forumId ")
					 		.setEntity("storeId", ts)
					 		.setEntity("forumId", tp);
			resList = (ArrayList<TblPostStore>) query.list();
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}
	
}
