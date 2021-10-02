package net.slide.dao;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import net.slide.pojo.TblArea;
import net.slide.pojo.TblChklst;
import net.slide.pojo.TblChklstArea;
import net.slide.pojo.TblChklstAreaDetail;
import net.slide.pojo.TblChklstDetail;
import net.slide.pojo.TblChklstGroup;
import net.slide.pojo.TblChklstStore;
import net.slide.pojo.TblChklstStoreDetail;
import net.slide.pojo.TblChklstTemplate;
import net.slide.pojo.TblChklstTopic;
import net.slide.pojo.TblStore;
import net.slide.pojores.TblChklstDetailRes;
import net.slide.pojores.TblChklstRes;
import net.slide.pojores.TblMasterRes;
import net.slide.util.HibernateUtil;
import net.slide.util.TransException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class ChklstDao  extends BaseDao {
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblChklst> getChklstList(String searchTxt)
	{
		ArrayList<TblChklst> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblChklst AS f ORDER BY postSubject");
			if(searchTxt != null && searchTxt.length() > 0){
				searchTxt = searchTxt.replace(' ', '%');
				query =  session.createQuery("FROM TblChklst AS f WHERE postSubject LIKE :searchTxt ORDER BY created_date").setText("searchTxt", "%"+searchTxt+"%");
			}
			resList = (ArrayList<TblChklst>) query.list();
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}
	
	

	public boolean updateResponsesm(int storeId, int chklstId,TblChklstStore a, int chklstStoreId,ArrayList<TblChklstDetailRes> response){
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			
			transaction = session.beginTransaction();
			String storeDetail = "update TblChklstStoreDetail as a set a.chklstResponseSm=:response where a.chklstStoreDetailId = :chklstStoreId ";
			for(TblChklstDetailRes res:response){
				Query query=session.createQuery(storeDetail);
				query.setString("response",res.getChkResponseSm());

				query.setInteger("chklstStoreId", res.getChklstDetailId());
				query.executeUpdate();
							
			}
			
			String store = "update TblChklstStore as a set a.chklstStatus='R' where a.chklstStoreId = :chklstStoreId ";
			Query querystore=session.createQuery(store);
			
			querystore.setInteger("chklstStoreId",chklstStoreId);
			querystore.executeUpdate();
			transaction.commit();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	
	
	public boolean updateAMRemark( int chklstStoreId,ArrayList<TblChklstDetailRes> response){
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			
			transaction = session.beginTransaction();
			String storeDetail = "update TblChklstStoreDetail as a set a.chklstResponseSm=:response where a.chklstStoreDetailId = :chklstStoreId ";
			for(TblChklstDetailRes res:response){
				Query query=session.createQuery(storeDetail);
				query.setString("response",res.getChkResponseSm());
				query.setInteger("chklstStoreId", res.getChklstDetailId());
				query.executeUpdate();
							
			}
			
			String store = "update TblChklstStore as a set a.chklstStatus='R' where a.chklstStoreId = :chklstStoreId ";
			Query querystore=session.createQuery(store);
			querystore.setInteger("chklstStoreId",chklstStoreId);
			querystore.executeUpdate();
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
	public ArrayList<TblMasterRes> getChklstYears(int topicId,int chklstOwnerRole, int chklstOwnerSite)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT TO_CHAR(updated_date,'YYYY') AS yyyy, COUNT(*) AS cnt " +
												"FROM  tbl_chklst f " +
												"WHERE f.chklst_owner_role = :chklstOwnerRole " +
												"AND f.chklst_owner_site = :chklstOwnerSite " +
												"AND chklst_topic_id = :topic " +
												"GROUP BY yyyy " +
												"ORDER BY yyyy")
												.setInteger("chklstOwnerRole", chklstOwnerRole)
												.setInteger("chklstOwnerSite", chklstOwnerSite)
									
												.setInteger("topic", topicId);
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName("Browse CheckList "+(String)val[0]);
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
	public ArrayList<TblMasterRes> getChklstMonths(int topicId,int chklstOwnerRole, int chklstOwnerSite, String postYear)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT  TO_CHAR(updated_date,'Month') AS mmm, TO_CHAR(updated_date,'MM') AS mm, COUNT(*) AS cnt " +
												"FROM  tbl_chklst f " +
												"WHERE TO_CHAR(updated_date,'YYYY') = :yyyy " +
												"AND f.chklst_owner_role = :chklstOwnerRole " +
												"AND f.chklst_owner_site = :chklstOwnerSite " +
												"AND chklst_topic_id = :topic " +
												"GROUP BY mmm, mm " +
												"ORDER BY mmm")
												.setString("yyyy", postYear)
												.setInteger("chklstOwnerRole", chklstOwnerRole)
												.setInteger("chklstOwnerSite", chklstOwnerSite)
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
	public ArrayList<TblMasterRes> getChklstWeek(int topicId,int chklstOwnerRole, int chklstOwnerSite, String postYear, String postMonth)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT ((cast(TO_CHAR(updated_date,'DD') as integer))/ 7+1) AS ww, COUNT(*) AS cnt " +
													"FROM  tbl_chklst f " +
													"WHERE TO_CHAR(updated_date,'YYYY') = :yyyy and TO_CHAR(updated_date,'MM') = :mm " +
													"AND f.chklst_owner_role = :chklstOwnerRole " +
													"AND f.chklst_owner_site = :chklstOwnerSite " +
													"AND chklst_topic_id = :topic " +
													"GROUP BY ww " +
													"ORDER BY ww")
													.setString("yyyy", postYear)
													.setString("mm", postMonth)
													.setInteger("chklstOwnerRole", chklstOwnerRole)
													.setInteger("chklstOwnerSite", chklstOwnerSite)
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
	public ArrayList<TblChklst> getChklsts(int topicId,int chklstOwnerRole, int chklstOwnerSite, String postYear, String postMonth, Integer postWeek)
	{
		ArrayList<TblChklst> retval = new ArrayList<TblChklst>();
		Session session = HibernateUtil.currentSession();
		try {
			
			TblChklstTopic t = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
			
			Query  query =  session.createQuery("FROM  TblChklst f " +
													"WHERE TO_CHAR(f.updatedDate,'YYYYMM') = :yyyymm AND " +
													"(((cast(TO_CHAR(f.updatedDate,'DD') as integer))/ 7+1)) = :ww " +
													"AND f.chklstOwnerRole = :chklstOwnerRole " +
													"AND f.chklstOwnerSite = :chklstOwnerSite " +
													"AND f.tblChklstTopic = :topic " +
													"ORDER BY f.updatedDate")
													.setString("yyyymm", postYear+postMonth)
													.setInteger("ww", postWeek)
													.setInteger("chklstOwnerRole", chklstOwnerRole)
													.setInteger("chklstOwnerSite", chklstOwnerSite)
													.setEntity("topic", t);
			retval = (ArrayList<TblChklst>) query.list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}

	
	/**
	 * Get Chklst By ID
	 * @param id
	 * @return
	 */
	public TblChklst getChklstById(int id){
		TblChklst f = null;
		Session session = HibernateUtil.currentSession();
		try{
			f = (TblChklst)session.get(TblChklst.class, id);
			
			//Query  query =  session.createQuery("FROM TblChklstDetail AS c WHERE c.tblChklst = :tblChklst").setEntity("tblChklst", f);
			//Set<TblChklstDetail> detSet = new HashSet<TblChklstDetail>((ArrayList<TblChklstDetail>) query.list());
			//f.setTblChklstDetails(detSet);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return f;
	}
	
	/**
	 * Get Topic by Name
	 * @param tblChklstGroup 
	 * @param topicName
	 * @return
	 */
	public TblChklst getChklstByName(TblChklstGroup tblChklstGroup, String topicName) {
		TblChklst retval = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery("FROM TblChklst AS f WHERE f.TblChklstGroup = :group AND UPPER(f.topicName) = :topicName ");
			query.setString("topicName", topicName.toUpperCase());
			query.setEntity("group", tblChklstGroup);
			retval = (TblChklst) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	/**
	 * Save Topic Details
	 * @param tblChklst
	 * @param topicId 
	 * @return
	 */
	public boolean saveChklst(TblChklst tblChklst, int topicId){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			TblChklstTopic t = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
			tblChklst.setTblChklstGroup(t.getTblChklstGroup());
			tblChklst.setTblChklstTopic(t);
			session.saveOrUpdate(tblChklst);
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
	 * Delete CheckList
	 * @param grpId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean deleteChklst(int id){
		boolean retval = false;
		ArrayList<TblChklstStore> resList = new ArrayList<TblChklstStore>();
		ArrayList<TblChklstArea> areaList = new ArrayList<TblChklstArea>();
		ArrayList<TblChklstStoreDetail> storeDetails = new ArrayList<TblChklstStoreDetail>();
		ArrayList<TblChklstAreaDetail> areaDetails = new ArrayList<TblChklstAreaDetail>();
		ArrayList<TblChklstDetail> chkDetails = new ArrayList<TblChklstDetail>();
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();

			TblChklst c = (TblChklst)session.get(TblChklst.class, id);

			Query  query =  session.createQuery("FROM  TblChklstStore t WHERE t.tblChklst = :chklstId ")
				    			.setEntity("chklstId", c);
			resList = (ArrayList<TblChklstStore>) query.list();
			for(TblChklstStore t: resList){
				session.delete(t);
			}
			Query  query1 =  session.createQuery("FROM  TblChklstArea t WHERE t.tblChklst = :chklstId ")
	    			.setEntity("chklstId", c);
			areaList = (ArrayList<TblChklstArea>) query1.list();
			for(TblChklstArea t: areaList){
				session.delete(t);
			}
			
			Query storeDet =  session.createQuery("FROM  TblChklstStoreDetail t WHERE t.tblChklst = :chklstId ")
	    			.setEntity("chklstId", c);
			storeDetails = (ArrayList<TblChklstStoreDetail>) storeDet.list();
			for(TblChklstStoreDetail t: storeDetails){
				session.delete(t);
			}
			Query areaDet =  session.createQuery("FROM  TblChklstAreaDetail t WHERE t.tblChklst = :chklstId ")
	    			.setEntity("chklstId", c);
			areaDetails = (ArrayList<TblChklstAreaDetail>) areaDet.list();
			for(TblChklstAreaDetail t: areaDetails){
				session.delete(t);
			}
			
		
			
			Query chkDet =  session.createQuery("FROM  TblChklstDetail t WHERE t.tblChklst = :chklstId ")
	    			.setEntity("chklstId", c);
			chkDetails = (ArrayList<TblChklstDetail>) chkDet.list();
			for(TblChklstDetail t: chkDetails){
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
	public ArrayList<TblMasterRes> groupList(String searchTxt)
	{
		ArrayList<TblMasterRes> retval = null;
		ArrayList<TblChklstGroup> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblChklstGroup AS f ORDER BY chklstGroupName");
			if(searchTxt != null && searchTxt.length() > 0){
				
				
				query=session.createQuery("FROM TblChklstGroup AS f WHERE upper(f.chklstGroupName) LIKE :searchTxt ")
						.setString("searchTxt", "%"+searchTxt.toUpperCase()+"%");
			}
			resList = (ArrayList<TblChklstGroup>) query.list();

			retval = new ArrayList<TblMasterRes>();
			for(TblChklstGroup f: resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName(f.getChklstGroupName());
				res.setId(f.getChklstGroupId());
				if(f.getTblChklstTopics() != null)
					res.setDtlCount(f.getTblChklstTopics().size());
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
	public ArrayList<TblChklstGroup> getGroupList()
	{
		ArrayList<TblChklstGroup> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblChklstGroup AS f ORDER BY chklstGroupName");
			resList = (ArrayList<TblChklstGroup>) query.list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}
	/**
	 * Get Group By ID
	 * @param id
	 * @return
	 */
	public TblChklstGroup getGroupById(int id){
		TblChklstGroup f = null;
		Session session = HibernateUtil.currentSession();
		try{
			f = (TblChklstGroup)session.get(TblChklstGroup.class, id);
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return f;
	}
	
	/**
	 * Get Group by Name
	 * @param chklstGroupName
	 * @return
	 */
	public TblChklstGroup getGroupByName(String chklstGroupName) {
		TblChklstGroup retval = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery("FROM TblChklstGroup AS f WHERE UPPER(f.chklstGroupName) = :chklstGroupName ");
			query.setString("chklstGroupName", chklstGroupName.toUpperCase());
			retval = (TblChklstGroup) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	/**
	 * Save Group Details
	 * @param f
	 * @return
	 */
	public boolean saveGroup(TblChklstGroup f){
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
	 * Delete Group
	 * @param grpId
	 * @return
	 */
	public boolean deleteGroup(int id) throws TransException{
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();

			TblChklstGroup c = (TblChklstGroup)session.get(TblChklstGroup.class, id);
			session.delete(c);
			
			transaction.commit();
			retval = true;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			throw new TransException("Checklist group already in use ....!!!");
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> topicList(String searchTxt)
	{
		ArrayList<TblMasterRes> retval = null;
		ArrayList<TblChklstTopic> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblChklstTopic AS f ORDER BY topicName");
			if(searchTxt != null && searchTxt.length() > 0){
			
				
				query=session.createQuery("FROM TblChklstTopic AS f WHERE upper(f.topicName) LIKE :searchTxt ")
						.setString("searchTxt", "%"+searchTxt.toUpperCase()+"%");
			}
			resList = (ArrayList<TblChklstTopic>) query.list();

			retval = new ArrayList<TblMasterRes>();
			for(TblChklstTopic f: resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName(f.getTopicName());
				res.setParentName(f.getTblChklstGroup().getChklstGroupName());
				res.setId(f.getTopicId());
				if(f.getTblChklsts() != null)
					res.setDtlCount(f.getTblChklsts().size());
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
	public ArrayList<TblChklstTopic> topicList(TblChklstGroup f)
	{
		ArrayList<TblChklstTopic> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			//Query  query =  session.createQuery("FROM TblChklstTopic AS f WHERE f.tblChklstGroup = :group ORDER BY topicName").setEntity("group", f);
			Query  query =  session.createQuery("FROM TblChklstTopic AS f WHERE f.tblChklstGroup = :group AND f.topicStatus!='N' ORDER BY topicName").setEntity("group", f);
						
			resList = (ArrayList<TblChklstTopic>) query.list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}
	
	/**
	 * Get Topic By ID
	 * @param id
	 * @return
	 */
	public TblChklstTopic getTopicById(int id){
		TblChklstTopic f = null;
		Session session = HibernateUtil.currentSession();
		try{
			f = (TblChklstTopic)session.get(TblChklstTopic.class, id);
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return f;
	}
		
	/**
	 * Get Topic by Name
	 * @param topicName
	 * @return
	 */
	public TblChklstTopic getTopicByName(TblChklstGroup g, String topicName) {
		TblChklstTopic retval = null;
		Session session = HibernateUtil.currentSession();
		try {
		
			Query query = session.createQuery("FROM TblChklstTopic AS f WHERE f.tblChklstGroup = :group AND UPPER(f.topicName) = :topicName ");
			query.setString("topicName", topicName.toUpperCase());
			query.setEntity("group", g);
			retval = (TblChklstTopic) query.uniqueResult();
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
	public boolean saveTopic(TblChklstTopic f){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			//TblChklstTemplate tm = (TblChklstTemplate)session.get(TblChklstTemplate.class, tempId);
			//TblChklstGroup tg=(TblChklstGroup)session.get(TblChklstGroup.class,groupId);
		//	f.setTblChklstGroup(tg);
		//	f.setTblChklstTemplate(tm);
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
	public boolean deleteTopic(int id) throws TransException{
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();

			TblChklstTopic c = (TblChklstTopic)session.get(TblChklstTopic.class, id);
			session.delete(c);
			
			transaction.commit();
			retval = true;
		}  catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			throw new TransException("Checklist topic already in use ....!!!");
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	public boolean savePost(TblChklst tblChklst, int topicId,int[] alist , int[] slist,ArrayList<TblChklstDetailRes> tempHead){
		
		boolean res=true ;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		String recType="S";
		try {
			transaction = session.beginTransaction();
			TblChklstTopic t = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
			tblChklst.setTblChklstTopic(t);
			TblChklstGroup tg=(TblChklstGroup)session.get(TblChklstGroup.class, t.getTblChklstGroup().getChklstGroupId());
			tblChklst.setTblChklstGroup(tg);
			session.saveOrUpdate(tblChklst);
			
			if(alist!=null){
				recType="A";
			}
			if(slist != null ){
				
				saveChklstStore(tblChklst, slist, session,recType);
				
				
				
			}
			if(alist != null ){
				
				saveChklstArea(tblChklst, alist, session,recType);
				
			}
			
			if(tempHead!=null && tempHead.size() > 0){
				
				saveChklst(tblChklst, tempHead,alist,slist, session);
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			res=false;
		} 
		HibernateUtil.closeSession();
		return res;		
	}
	

	
	public boolean saveUpdatePost(TblChklst tblChklst, int topicId,int[] alist , int[] slist,ArrayList<TblChklstDetailRes> tempHead){
		boolean res=true ;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		String recType="S";
		try {
			transaction = session.beginTransaction();
			TblChklstTopic t = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
			tblChklst.setTblChklstTopic(t);
			TblChklstGroup tg=(TblChklstGroup)session.get(TblChklstGroup.class, t.getTblChklstGroup().getChklstGroupId());
			tblChklst.setTblChklstGroup(tg);
			session.saveOrUpdate(tblChklst);
			
			if(alist!=null){
				recType="A";
			}
			if(slist != null ){
				
				saveChklstStore(tblChklst, slist, session,recType);
				
				
				
			}
			if(alist != null ){
				
				saveChklstArea(tblChklst, alist, session,recType);
				
			}
			
			if(tempHead!=null && tempHead.size() > 0){
				
				saveUpdateChklst(tblChklst, tempHead,alist,slist, session);
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			res=false;
		} 
		HibernateUtil.closeSession();
		return res;		
	}
	public boolean saveUpdateStorePost(TblChklst tblChklst, int topicId,int[] alist , int[] slist,ArrayList<TblChklstDetailRes> tempHead){
		boolean res=true ;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		String recType="S";
		try {
			transaction = session.beginTransaction();
			TblChklstTopic t = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
			tblChklst.setTblChklstTopic(t);
			TblChklstGroup tg=(TblChklstGroup)session.get(TblChklstGroup.class, t.getTblChklstGroup().getChklstGroupId());
			tblChklst.setTblChklstGroup(tg);
			session.saveOrUpdate(tblChklst);
			
			
			if(slist != null ){
				
				saveChklstStore(tblChklst, slist, session,recType);
				
				
				
			}
			if(alist != null ){
				
				saveChklstArea(tblChklst, alist, session,recType);
				
			}
			
			if(tempHead!=null && tempHead.size() > 0){
				
				saveUpdateChklst(tblChklst, tempHead,alist,slist, session);
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			res=false;
		} 
		HibernateUtil.closeSession();
		return res;		
	}
	
	public boolean saveStorePost(TblChklst tblChklst, int topicId,int[] alist , int[] slist,ArrayList<TblChklstDetailRes> tempHead){
		boolean res=true ;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		String recType="S";
		try {
			transaction = session.beginTransaction();
			TblChklstTopic t = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
			tblChklst.setTblChklstTopic(t);
			TblChklstGroup tg=(TblChklstGroup)session.get(TblChklstGroup.class, t.getTblChklstGroup().getChklstGroupId());
			tblChklst.setTblChklstGroup(tg);
			session.saveOrUpdate(tblChklst);
			
			
			if(slist != null ){
				
				saveChklstStore(tblChklst, slist, session,recType);
				
				
				
			}
			if(alist != null ){
				
				saveChklstArea(tblChklst, alist, session,recType);
				
			}
			
			if(tempHead!=null && tempHead.size() > 0){
				
				saveChklst(tblChklst, tempHead,alist,slist, session);
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			res=false;
		} 
		HibernateUtil.closeSession();
		return res;		
	}
	
	private void saveChklst(TblChklst tblChklst, ArrayList<TblChklstDetailRes> tempList,int[] alist,int[] slist, Session session) {
				
		for(TblChklstDetailRes cVal: tempList){
			TblChklstDetail t=new TblChklstDetail();
			t.setTblChklst(tblChklst);
			t.setChklstValue(cVal.getChklstValue());
			t.setUpdatedBy(tblChklst.getUpdatedBy());
			t.setUpdatedDate(new Date());
			
			session.saveOrUpdate(t);
			if(alist!=null){
			for(int areaid: alist){
			TblArea ts = (TblArea)session.get(TblArea.class, areaid);
			TblChklstAreaDetail ad=new TblChklstAreaDetail();
			ad.setTblChklstDetail(t);
			ad.setTblChklst(tblChklst);
		//	t.setChklstResponse(res.getChkResponse());
			ad.setUpdatedBy(tblChklst.getUpdatedBy());
			ad.setTblArea(ts);
			ad.setChklstResponseStatus("A");
			ad.setUpdatedDate(new Date());
			session.saveOrUpdate(ad);
			}}
			if(slist!=null){
			for(int storeid: slist){
				TblStore ts = (TblStore)session.get(TblStore.class, storeid);
				TblChklstStoreDetail ad=new TblChklstStoreDetail();
				ad.setTblChklstDetail(t);
				ad.setTblChklst(tblChklst);
			//	t.setChklstResponse(res.getChkResponse());
				ad.setUpdatedBy(tblChklst.getUpdatedBy());
				ad.setTblStore(ts);
				//ad.setChklstResponseStatus("A");
				ad.setChklstResponseStatus(null); //No any default value 
				ad.setUpdatedDate(new Date());
				session.saveOrUpdate(ad);
				}}
		}
	}

	
	@SuppressWarnings("unchecked")
	private void saveUpdateChklst(TblChklst tblChklst, ArrayList<TblChklstDetailRes> tempList,int[] alist,int[] slist, Session session) {
		
		ArrayList<TblChklstDetail> detailList=new ArrayList<TblChklstDetail> ();
		ArrayList<TblChklstAreaDetail> detailAreaList=new ArrayList<TblChklstAreaDetail> ();
		ArrayList<TblChklstStoreDetail> detailStoreList=new ArrayList<TblChklstStoreDetail> ();
		
		
		
		
		Query  query =  session.createQuery("FROM TblChklstDetail AS f WHERE f.tblChklst = :tblChklst ").setEntity("tblChklst", tblChklst);
		detailList = (ArrayList<TblChklstDetail>) query.list();
		String updateCheck="Y",check="N";
		for(TblChklstDetail det:detailList){
			updateCheck="Y";
			for(TblChklstDetailRes res:tempList){
				if(res.getChkStatus().equals("U")){
					check="Y";
                 if(det.getChklstDetailId()==res.getChklstDetailId()){
                	
                	 updateCheck="N";
                 }else{
                	
                 }
				}
			}
			if(updateCheck.equals("Y")&&(check.equals("Y"))){
				Query  queryArea =  session.createQuery("FROM TblChklstAreaDetail AS f WHERE f.tblChklstDetail = :tblChklstDetail ").setEntity("tblChklstDetail", det);
				detailAreaList= (ArrayList<TblChklstAreaDetail>) queryArea.list();
				for(TblChklstAreaDetail areaDet:detailAreaList){
					session.delete(areaDet);
				}
				
				Query  queryStore=  session.createQuery("FROM TblChklstStoreDetail AS f WHERE f.tblChklstDetail = :tblChklstDetail ").setEntity("tblChklstDetail", det);
				detailStoreList= (ArrayList<TblChklstStoreDetail>) queryStore.list();
				for(TblChklstStoreDetail storeDet:detailStoreList){
					session.delete(storeDet);
				}
				session.delete(det);

			}
			
			//
		}
		for(TblChklstDetailRes cVal: tempList){
			if(cVal.getChkStatus().equals("I")){
			TblChklstDetail t=new TblChklstDetail();
			t.setTblChklst(tblChklst);
			t.setChklstValue(cVal.getChklstValue());
			t.setUpdatedBy(tblChklst.getUpdatedBy());
			t.setUpdatedDate(new Date());
			
			session.saveOrUpdate(t);
			if(alist!=null){
			for(int areaid: alist){
			TblArea ts = (TblArea)session.get(TblArea.class, areaid);
			TblChklstAreaDetail ad=new TblChklstAreaDetail();
			ad.setTblChklstDetail(t);
			ad.setTblChklst(tblChklst);
		//	t.setChklstResponse(res.getChkResponse());
			ad.setUpdatedBy(tblChklst.getUpdatedBy());
			ad.setTblArea(ts);
			ad.setChklstResponseStatus("A");
			ad.setUpdatedDate(new Date());
			session.saveOrUpdate(ad);
			}}
			if(slist!=null){
			for(int storeid: slist){
				TblStore ts = (TblStore)session.get(TblStore.class, storeid);
				TblChklstStoreDetail ad=new TblChklstStoreDetail();
				ad.setTblChklstDetail(t);
				ad.setTblChklst(tblChklst);
			//	t.setChklstResponse(res.getChkResponse());
				ad.setUpdatedBy(tblChklst.getUpdatedBy());
				ad.setTblStore(ts);
				ad.setChklstResponseStatus("A");
				ad.setUpdatedDate(new Date());
				session.saveOrUpdate(ad);
				}}
		}}
	}

	
	
	

	private void saveStoreChklst(TblChklst tblChklst, ArrayList<String> tempList,int[] slist, Session session) {
		
		for(String cVal: tempList){
			TblChklstDetail t=new TblChklstDetail();
			t.setTblChklst(tblChklst);
			t.setChklstValue(cVal);
			t.setUpdatedBy(tblChklst.getUpdatedBy());
			t.setUpdatedDate(new Date());
			
			session.saveOrUpdate(t);
			for(int storeid: slist){
			TblStore ts = (TblStore)session.get(TblStore.class, storeid);
			TblChklstStoreDetail ad=new TblChklstStoreDetail();
			ad.setTblChklstDetail(t);
			ad.setTblChklst(tblChklst);
		//	t.setChklstResponse(res.getChkResponse());
			ad.setUpdatedBy(tblChklst.getUpdatedBy());
			ad.setTblStore(ts);
			ad.setChklstResponseStatus("A");
			ad.setUpdatedDate(new Date());
			session.saveOrUpdate(ad);
			}
		}
	}

	private void saveChklstAreaDetail(int areaId, int chklstId,TblChklstArea a, ArrayList<TblChklstDetailRes> getChklstDetail,  Session session) {
		
		
		TblChklst tc = (TblChklst)session.get(TblChklst.class, chklstId);
		for(TblChklstDetailRes res: getChklstDetail){
			TblChklstAreaDetail t=new TblChklstAreaDetail();
			TblChklstDetail ta = (TblChklstDetail)session.get(TblChklstDetail.class, res.getChklstDetailId());
			t.setTblChklstDetail(ta);
			t.setTblChklst(tc);
		//	t.setChklstResponse(res.getChkResponse());
			t.setUpdatedBy(a.getUpdatedBy());
			//t.setTblArea(ts);
			t.setChklstResponseStatus("A");
			t.setUpdatedDate(new Date());
			session.saveOrUpdate(t);
			
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void saveChklstArea(TblChklst tblChklst, int[] slist, Session session,String recType) {
		
		for(int areaid: slist){
			
			ArrayList<TblChklstArea> areaList=new ArrayList<TblChklstArea>();
			TblChklstArea ta=new TblChklstArea();
			tblChklst.setChklstStatus("P");
			TblArea tblArea = (TblArea)session.get(TblArea.class, areaid);
			ta.setTblArea(tblArea);
			ta.setTblChklst(tblChklst);
			ta.setChklstStatus("P");
			ta.setUpdatedDate(new Date());
			ta.setChklstSite(recType);
			Query query = session.createQuery("FROM TblChklstArea AS p WHERE p.tblArea = :area AND p.tblChklst = :chklst ");
			query.setEntity("area", tblArea);
			query.setEntity("chklst", tblChklst);
			areaList=(ArrayList<TblChklstArea>)query.list();
			if(areaList.size()==0){
			session.saveOrUpdate(ta);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void saveChklstStore(TblChklst tblChklst, int[] slist, Session session,String recType) {
		
		for(int storeid: slist){
			ArrayList<TblChklstStore> storeList=new ArrayList<TblChklstStore>();
			TblChklstStore ts=new TblChklstStore();
			tblChklst.setChklstStatus("P");
			TblStore tblStore = (TblStore)session.get(TblStore.class, storeid);
			ts.setTblStore(tblStore);
			ts.setTblChklst(tblChklst);
			ts.setChklstStatus("P");
			ts.setUpdatedDate(new Date());
			ts.setChklstSite(recType);
			Query query = session.createQuery("FROM TblChklstStore AS p WHERE p.tblStore = :store AND p.tblChklst = :chklst ");
			query.setEntity("store", tblStore);
			query.setEntity("chklst", tblChklst);
			storeList=(ArrayList<TblChklstStore>)query.list();
			if(storeList.size()==0){
			session.saveOrUpdate(ts);
		}
		}
	}
	
	
	
	
	//Hq user

		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getChklstAll(int topicId,int chklstOwnerRole, int chklstOwnerSite)
		{	
			ArrayList<TblChklstRes> retval = null;
			Session session = HibernateUtil.currentSession();
			ArrayList<TblChklst> resList = null;
			try {
				TblChklstTopic tp = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
				Query  query =  session.createQuery("FROM  TblChklst ta where ta.chklstOwnerRole=:chklstOwnerRole and ta.chklstOwnerSite=:chklstOwnerSite and ta.tblChklstTopic = :topicId and  ta.chklstEndDate>=current_date ORDER BY ta.updatedDate DESC")
							    .setInteger("chklstOwnerRole", chklstOwnerRole)
							    .setInteger("chklstOwnerSite", chklstOwnerSite)
								.setEntity("topicId", tp);
				resList = (ArrayList<TblChklst>) query.list();
			    retval = new ArrayList<TblChklstRes>();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			for(TblChklst t: resList){

				TblChklstRes res=new TblChklstRes();
				res.setChklstSubject(t.getChklstSubject());
				res.setChklstId(t.getChklstId());
			//	res.setChklstType(t.getChklstType());
				res.setChklstStartDate(formatter.format(t.getChklstStartDate()));
				res.setChklstEndDate(formatter.format(t.getChklstEndDate()));
				Query areaQuery =  session.createSQLQuery("SELECT  COUNT(chklst_id) from tbl_chklst_area where (chklst_status='R' or chklst_status='I')  and chklst_id=:chklst_id")
						           .setInteger("chklst_id", t.getChklstId());
				List listArea= areaQuery.list();
				Query storeQuery =  session.createSQLQuery("SELECT  COUNT(chklst_id) from tbl_chklst_store where (chklst_status='R' or chklst_status='I') and chklst_site='S' and chklst_id=:chklst_id")
				           .setInteger("chklst_id", t.getChklstId());
				List listStore= storeQuery.list();
				if(listArea.get(0)!=null){
					if(listArea.get(0).toString().equals("0")){}else{
					res.setAreaCount(listArea.get(0).toString());
					}
				}
				if(listStore.get(0)!=null){
					if(listStore.get(0).toString().equals("0")){}else{
					res.setStoreCount(listStore.get(0).toString());
					}
				}
				res.setTotalAreaCount(String.valueOf(t.getTblChklstAreas().size()));
			   	retval.add(res);
			}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
			
		//Hq user completed chklst
		
		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getCompleteChklst(int topicId,int chklstOwnerRole, int chklstOwnerSite)
		{	
			ArrayList<TblChklstRes> retval = null;
			Session session = HibernateUtil.currentSession();
			ArrayList<TblChklst> resList = null;
			try {
				TblChklstTopic tp = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
				Query  query =  session.createQuery("FROM  TblChklst ta where ta.chklstOwnerRole=:chklstOwnerRole and ta.chklstOwnerSite=:chklstOwnerSite and ta.tblChklstTopic = :topicId and ta.chklstEndDate < current_date ORDER BY ta.updatedDate DESC")
								.setInteger("chklstOwnerRole", chklstOwnerRole)
								.setInteger("chklstOwnerSite", chklstOwnerSite)
								.setEntity("topicId", tp);
				resList = (ArrayList<TblChklst>) query.list();
			    retval = new ArrayList<TblChklstRes>();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			for(TblChklst t: resList){

				TblChklstRes res=new TblChklstRes();
				res.setChklstSubject(t.getChklstSubject());
				res.setChklstId(t.getChklstId());
				//res.setChklstType(t.getChklstType());
				res.setChklstStartDate(formatter.format(t.getChklstStartDate()));
				res.setChklstEndDate(formatter.format(t.getChklstEndDate()));
				Query areaQuery =  session.createSQLQuery("SELECT  COUNT(chklst_id) from tbl_chklst_area where (chklst_status='R' or chklst_status='I') and chklst_id=:chklst_id")
						           .setInteger("chklst_id", t.getChklstId());
				List listArea= areaQuery.list();
				Query storeQuery =  session.createSQLQuery("SELECT  COUNT(chklst_id) from tbl_chklst_store where (chklst_status='R' or chklst_status='I') and chklst_id=:chklst_id")
				           .setInteger("chklst_id", t.getChklstId());
				List listStore= storeQuery.list();
				if(listArea.get(0)!=null){
					if(listArea.get(0).toString().equals("0")){}else{
					res.setAreaCount(listArea.get(0).toString());
					}
				}
				if(listStore.get(0)!=null){
					if(listStore.get(0).toString().equals("0")){}else{
					res.setStoreCount(listStore.get(0).toString());
					}
				}
				res.setTotalAreaCount(String.valueOf(t.getTblChklstAreas().size()));
			   	retval.add(res);
			}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
			
		


		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getStoreManageChklst(int storeId,int topicId)
		{
			Session session = HibernateUtil.currentSession();
			ArrayList<TblChklstStore> resList = new ArrayList<TblChklstStore>();
			ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
			TblStore ts = (TblStore)session.get(TblStore.class, storeId);
			
			try {
				
				Query  query =  session.createQuery("FROM  TblChklstStore t WHERE t.tblStore = :storeId  AND t.chklstSite='S'")
						 		.setEntity("storeId", ts);
				resList = (ArrayList<TblChklstStore>) query.list();
				
				for(TblChklstStore t: resList){
					TblChklstRes res=new TblChklstRes();
					t.getTblChklst();
					
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		
//Store View Area Pending
		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getStorePendingChklst(int storeId, int topicId)
		{
			Session session = HibernateUtil.currentSession();
			ArrayList<TblChklstStore> resList = new ArrayList<TblChklstStore>();
			ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
			TblStore ts = (TblStore)session.get(TblStore.class, storeId);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			try {
				TblChklstTopic tp = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
				Query  query =  session.createQuery("FROM  TblChklstStore t WHERE t.tblStore = :storeId AND (t.chklstStatus='P' OR t.chklstStatus='I') AND t.chklstSite='A'  AND t.tblChklst.chklstEndDate >= :dt and t.tblChklst.tblChklstTopic = :topicId ")
						 		.setEntity("storeId", ts)
						 		.setDate("dt", new Date())
						 		.setEntity("topicId", tp);
				resList = (ArrayList<TblChklstStore>) query.list();
				
				for(TblChklstStore t: resList){
					 
					TblChklstRes res=new TblChklstRes();
					res.setChklstSubject(t.getTblChklst().getChklstSubject());
					res.setChklstId(t.getTblChklst().getChklstId());
					res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
					res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
					res.setChklstRemark(t.getChklstRemark());
					res.setChklstStoreId(t.getChklstStoreId());
					res.setChklstType(t.getChklstSite());
					res.setChklstStatus(t.getChklstStatus());
					
                                        double percentage=0;
                                        Query queryTotal=session.createQuery("FROM TblChklstStoreDetail d "+
                                                 " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore ")
                                                .setEntity("TblChklst", t.getTblChklst())
                                                .setEntity("TblStore", t.getTblStore());
                                        ArrayList<TblChklstStoreDetail> storeDetList=(ArrayList<TblChklstStoreDetail>) queryTotal.list();
                                        Query queryYes=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                 " INNER JOIN FETCH d.tblChklstDetail  WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='Y' ").setEntity("TblChklst",t.getTblChklst()).setEntity("TblStore", t.getTblStore());
                                        ArrayList<TblChklstStoreDetail> areaYesList=(ArrayList<TblChklstStoreDetail>) queryYes.list();

                                        Query queryNo=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='A' ").setEntity("TblChklst",t.getTblChklst()).setEntity("TblStore", t.getTblStore());
                                        ArrayList<TblChklstStoreDetail> areaNoList=(ArrayList<TblChklstStoreDetail>) queryNo.list();
                                        NumberFormat formatt = new DecimalFormat("#0.00");


                                        double total1=0,yes1=0,no1=0;
                                        double temval;
                                        for(TblChklstStoreDetail dt: storeDetList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            total1 = total1 + temval;
                                         }
                                        for(TblChklstStoreDetail dt: areaYesList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            yes1 = yes1 + temval;
                                         }
                                        for(TblChklstStoreDetail dt: areaNoList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            no1 = no1 + temval;
                                         }               
                                        percentage=(yes1/(total1-no1))*100;

				
					if(Double.isNaN(percentage)){
						res.setPercentage("N/A");
						res.setGrade("N/A");
					}else{
					if(percentage>=90){
						res.setGrade("Excellent");
					}else if(percentage>=80){
						res.setGrade("Good");	
					}else if(percentage>=70){
						res.setGrade("Satisfactory");	
					}else if(percentage>=60){
						res.setGrade("Need Improvement");
					}else if(percentage<60){
						res.setGrade("Unacceptable");
					}
					
						res.setPercentage(formatt.format(percentage)+"%");
						
					}
					
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		
		//Direct Store CheckList
				@SuppressWarnings("unchecked")
				public ArrayList<TblChklstRes> getDirectStoreChklst(int storeId, int topicId)
				{
					Session session = HibernateUtil.currentSession();
					ArrayList<TblChklstStore> resList = new ArrayList<TblChklstStore>();
					ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
					TblStore ts = (TblStore)session.get(TblStore.class, storeId);
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					try {
						TblChklstTopic tp = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
						Query  query =  session.createQuery("FROM  TblChklstStore t WHERE t.tblStore = :storeId AND (t.chklstStatus='P' OR t.chklstStatus='I') AND t.chklstSite='S'  AND t.tblChklst.chklstEndDate >= :dt and t.tblChklst.tblChklstTopic = :topicId ")
								 		.setEntity("storeId", ts)
								 		.setDate("dt", new Date())
								 		.setEntity("topicId", tp);
						resList = (ArrayList<TblChklstStore>) query.list();
						
						for(TblChklstStore t: resList){
							 
							TblChklstRes res=new TblChklstRes();
							res.setChklstSubject(t.getTblChklst().getChklstSubject());
							res.setChklstId(t.getTblChklst().getChklstId());
							res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
							res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
							res.setChklstRemark(t.getChklstRemark());
							res.setChklstStoreId(t.getChklstStoreId());
							res.setChklstType(t.getChklstSite());
							res.setChklstStatus(t.getChklstStatus());
							
                                                        double percentage=0;
							Query queryTotal=session.createQuery("FROM TblChklstStoreDetail d "+
                                                                 " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore ")
                                                                .setEntity("TblChklst", t.getTblChklst())
                                                                .setEntity("TblStore", t.getTblStore());
							ArrayList<TblChklstStoreDetail> storeDetList=(ArrayList<TblChklstStoreDetail>) queryTotal.list();

                                                        Query queryYes=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                                 " INNER JOIN FETCH d.tblChklstDetail  WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='Y' ").setEntity("TblChklst",t.getTblChklst()).setEntity("TblStore", t.getTblStore());
							ArrayList<TblChklstStoreDetail> areaYesList=(ArrayList<TblChklstStoreDetail>) queryYes.list();
							
							Query queryNo=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                                " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='A' ").setEntity("TblChklst",t.getTblChklst()).setEntity("TblStore", t.getTblStore());
							ArrayList<TblChklstStoreDetail> areaNoList=(ArrayList<TblChklstStoreDetail>) queryNo.list();
							NumberFormat formatt = new DecimalFormat("#0.00");


                                                        double total1=0,yes1=0,no1=0;
                                                        double temval;
                                                        for(TblChklstStoreDetail dt: storeDetList){
                                                            String text = dt.getTblChklstDetail().getChklstValue();
                                                            String[] array = text.split("\\|",-1);
                                                            if (text.substring(text.length()-1).equals("|")){
                                                                temval=1;
                                                            }else{
                                                                temval = Double.parseDouble(array[array.length-1]); 
                                                            }
                                                            total1 = total1 + temval;
                                                         }
                                                        for(TblChklstStoreDetail dt: areaYesList){
                                                            String text = dt.getTblChklstDetail().getChklstValue();
                                                            String[] array = text.split("\\|",-1);
                                                            if (text.substring(text.length()-1).equals("|")){
                                                                temval=1;
                                                            }else{
                                                                temval = Double.parseDouble(array[array.length-1]); 
                                                            }
                                                            yes1 = yes1 + temval;
                                                         }
                                                        for(TblChklstStoreDetail dt: areaNoList){
                                                            String text = dt.getTblChklstDetail().getChklstValue();
                                                            String[] array = text.split("\\|",-1);
                                                            if (text.substring(text.length()-1).equals("|")){
                                                                temval=1;
                                                            }else{
                                                                temval = Double.parseDouble(array[array.length-1]); 
                                                            }
                                                            no1 = no1 + temval;
                                                         }               
 							percentage=(yes1/(total1-no1))*100;
						
						        
							if(Double.isNaN(percentage)){
								res.setPercentage("N/A");
								res.setGrade("N/A");
							}else{
								if(percentage>=90){
									res.setGrade("Excellent");
								}else if(percentage>=80){
									res.setGrade("Good");	
								}else if(percentage>=70){
									res.setGrade("Satisfactory");	
								}else if(percentage>=60){
									res.setGrade("Need Improvement");
								}else if(percentage<60){
									res.setGrade("Unacceptable");
								}

								res.setPercentage(formatt.format(percentage)+"%");
								
							}
							
							retval.add(res);
						}
					}catch (HibernateException e) {
						e.printStackTrace();
					}
					HibernateUtil.closeSession();
					return retval;
				}
				
				
		
		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getStoreForAreaChklst(int storeId, int topicId)
		{
			Session session = HibernateUtil.currentSession();
			ArrayList<TblChklstStore> resList = new ArrayList<TblChklstStore>();
			ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
			TblStore ts = (TblStore)session.get(TblStore.class, storeId);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			try {
				TblChklstTopic tp = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
				Query  query =  session.createQuery("FROM  TblChklstStore t WHERE t.tblStore = :storeId  and t.tblChklst.chklstEndDate >= :dt and t.tblChklst.tblChklstTopic = :topicId ")
						 		.setEntity("storeId", ts)
						 		.setDate("dt", new Date())
						 		.setEntity("topicId", tp);
				resList = (ArrayList<TblChklstStore>) query.list();
				
				for(TblChklstStore t: resList){
					 
					TblChklstRes res=new TblChklstRes();
					res.setChklstSubject(t.getTblChklst().getChklstSubject());
					res.setChklstId(t.getTblChklst().getChklstId());
					res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
					res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
					res.setChklstRemark(t.getChklstRemark());
					res.setChklstStoreId(t.getChklstStoreId());
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		
		
		
		
		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getStoreCompletedChklst(int storeId, int topicId)
		{
			Session session = HibernateUtil.currentSession();
			ArrayList<TblChklstStore> resList = new ArrayList<TblChklstStore>();
			ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
			TblStore ts = (TblStore)session.get(TblStore.class, storeId);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			try {
				TblChklstTopic tp = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
				Query  query =  session.createQuery("FROM  TblChklstStore t WHERE t.tblStore = :storeId AND t.chklstStatus='R' AND t.tblChklst.tblChklstTopic = :topicId ")
				 		.setEntity("storeId", ts)
						.setEntity("topicId", tp);
				resList = (ArrayList<TblChklstStore>) query.list();
				
				for(TblChklstStore t: resList){
					TblChklstRes res=new TblChklstRes();
					res.setChklstSubject(t.getTblChklst().getChklstSubject());
					res.setChklstId(t.getTblChklst().getChklstId());
					res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
					res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
					res.setChklstRemark(t.getChklstRemark());
					res.setChklstStoreId(t.getChklstStoreId());
					res.setChklstType(t.getChklstSite());
                                        double percentage=0;
                                        Query queryTotal=session.createQuery("FROM TblChklstStoreDetail d "+
                                                 " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore ")
                                                .setEntity("TblChklst", t.getTblChklst())
                                                .setEntity("TblStore", t.getTblStore());
                                        ArrayList<TblChklstStoreDetail> storeDetList=(ArrayList<TblChklstStoreDetail>) queryTotal.list();
                                        Query queryYes=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                 " INNER JOIN FETCH d.tblChklstDetail  WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='Y' ").setEntity("TblChklst",t.getTblChklst()).setEntity("TblStore", t.getTblStore());
                                        ArrayList<TblChklstStoreDetail> areaYesList=(ArrayList<TblChklstStoreDetail>) queryYes.list();
					
                                        Query queryNo=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='A' ").setEntity("TblChklst",t.getTblChklst()).setEntity("TblStore", t.getTblStore());
                                        ArrayList<TblChklstStoreDetail> areaNoList=(ArrayList<TblChklstStoreDetail>) queryNo.list();
                                        NumberFormat formatt = new DecimalFormat("#0.00");


                                        double total1=0,yes1=0,no1=0;
                                        double temval;
                                        for(TblChklstStoreDetail dt: storeDetList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            total1 = total1 + temval;
                                         }
                                        for(TblChklstStoreDetail dt: areaYesList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            yes1 = yes1 + temval;
                                         }
                                        for(TblChklstStoreDetail dt: areaNoList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            no1 = no1 + temval;
                                         }               
                                        percentage=(yes1/(total1-no1))*100;
				
					if(Double.isNaN(percentage)){
						res.setPercentage("N/A");
						res.setGrade("N/A");
					}else{
						if(percentage>=90){
							res.setGrade("Excellent");
						}else if(percentage>=80){
							res.setGrade("Good");	
						}else if(percentage>=70){
							res.setGrade("Satisfactory");	
						}else if(percentage>=60){
							res.setGrade("Need Improvement");
						}else if(percentage<60){
							res.setGrade("Unacceptable");
						}
						res.setPercentage(formatt.format(percentage)+"%");
						
					}
					
					
					
					
					
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		

		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getStoreFailedChklst(int storeId, int topicId)
		{
			Session session = HibernateUtil.currentSession();
			ArrayList<TblChklstStore> resList = new ArrayList<TblChklstStore>();
			ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
			TblStore ts = (TblStore)session.get(TblStore.class, storeId);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			try {
				TblChklstTopic tp = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
				Query  query =  session.createQuery("FROM  TblChklstStore t WHERE t.tblStore = :storeId AND t.chklstStatus='P' and t.tblChklst.chklstEndDate< :dt and t.tblChklst.tblChklstTopic = :topicId AND t.chklstSite='S'")
						 		.setEntity("storeId", ts)
						 		.setDate("dt", new Date())
						 		.setEntity("topicId", tp);
				resList = (ArrayList<TblChklstStore>) query.list();
				
				for(TblChklstStore t: resList){
					 
					TblChklstRes res=new TblChklstRes();
					res.setChklstSubject(t.getTblChklst().getChklstSubject());
					res.setChklstId(t.getTblChklst().getChklstId());
					res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
					res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
					res.setChklstRemark(t.getChklstRemark());
					res.setChklstStoreId(t.getChklstStoreId());
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		

		
		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getAreaPendingChklst(int areaId, int topicId)
		{
			Session session = HibernateUtil.currentSession();
			ArrayList<TblChklstArea> resList = new ArrayList<TblChklstArea>();
			ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
			TblArea ts = (TblArea)session.get(TblArea.class, areaId);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			try {
				TblChklstTopic tp = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
				Query  query =  session.createQuery("FROM  TblChklstArea t WHERE t.tblArea = :areaId AND (t.chklstStatus='P' or t.chklstStatus='I' ) AND t.chklstSite='A' and t.tblChklst.chklstEndDate >= :dt and t.tblChklst.tblChklstTopic = :topicId ")
						 		.setEntity("areaId", ts)
						 		.setDate("dt", new Date())
						 		.setEntity("topicId", tp);
				resList = (ArrayList<TblChklstArea>) query.list();
				
				for(TblChklstArea t: resList){
					 
					TblChklstRes res=new TblChklstRes();
					res.setChklstSubject(t.getTblChklst().getChklstSubject());
					res.setChklstId(t.getTblChklst().getChklstId());
					res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
					res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
					res.setChklstRemark(t.getChklstRemark());
					res.setChklstAreaId(t.getChklstAreaId());
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		
		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getAreaStorePendingChklst(int areaId, int topicId)
		{
			Session session = HibernateUtil.currentSession();
			ArrayList<TblChklstArea> resList = new ArrayList<TblChklstArea>();
			ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
			TblArea ts = (TblArea)session.get(TblArea.class, areaId);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			try {
				TblChklstTopic tp = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
				Query  query =  session.createQuery("FROM  TblChklstArea t WHERE t.tblArea = :areaId AND (t.chklstStatus='P' or t.chklstStatus='I' ) AND t.chklstSite='S' and t.tblChklst.chklstEndDate >= :dt and t.tblChklst.tblChklstTopic = :topicId ")
						 		.setEntity("areaId", ts)
						 		.setDate("dt", new Date())
						 		.setEntity("topicId", tp);
				resList = (ArrayList<TblChklstArea>) query.list();
				
				for(TblChklstArea t: resList){
					 
					TblChklstRes res=new TblChklstRes();
					res.setChklstSubject(t.getTblChklst().getChklstSubject());
					res.setChklstId(t.getTblChklst().getChklstId());
					res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
					res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
					res.setChklstRemark(t.getChklstRemark());
					res.setChklstAreaId(t.getChklstAreaId());
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		
		

		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getAreaFailedChklst(int areaId, int topicId)
		{
			Session session = HibernateUtil.currentSession();
			ArrayList<TblChklstArea> resList = new ArrayList<TblChklstArea>();
			ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
			TblArea ts = (TblArea)session.get(TblArea.class, areaId);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			try {
				TblChklstTopic tp = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
				Query  query =  session.createQuery("FROM  TblChklstArea t WHERE t.tblArea = :areaId AND t.chklstStatus='P' and t.tblChklst.chklstEndDate< :dt and t.tblChklst.tblChklstTopic = :topicId ")
						 		.setEntity("areaId", ts)
						 		.setDate("dt", new Date())
						 		.setEntity("topicId", tp);
				resList = (ArrayList<TblChklstArea>) query.list();
				
				for(TblChklstArea t: resList){
					 
					TblChklstRes res=new TblChklstRes();
					res.setChklstSubject(t.getTblChklst().getChklstSubject());
					res.setChklstId(t.getTblChklst().getChklstId());
					res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
					res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
					res.setChklstRemark(t.getChklstRemark());
					res.setChklstAreaId(t.getChklstAreaId());
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		

		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getAreaCompletedChklst(int areaId, int topicId)
		{
			Session session = HibernateUtil.currentSession();
			ArrayList<TblChklstArea> resList = new ArrayList<TblChklstArea>();
			ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
			TblArea ts = (TblArea)session.get(TblArea.class, areaId);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			try {
				TblChklstTopic tp = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
				Query  query =  session.createQuery("FROM  TblChklstArea t WHERE t.tblArea = :areaId AND t.chklstStatus='R' and t.tblChklst.tblChklstTopic = :topicId ")
						 		.setEntity("areaId", ts)
						 		.setEntity("topicId", tp);
				resList = (ArrayList<TblChklstArea>) query.list();
				
				for(TblChklstArea t: resList){
					 
					TblChklstRes res=new TblChklstRes();
					res.setChklstSubject(t.getTblChklst().getChklstSubject());
					res.setChklstId(t.getTblChklst().getChklstId());
					res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
					res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
					res.setChklstRemark(t.getChklstRemark());
					res.setChklstAreaId(t.getChklstAreaId());
//					if(t.getChklstResponse().equals("A")){
//						res.setChklstResponseType("Active");
//					}else if(t.getChklstResponse().equals("D")){
//						res.setChklstResponseType("Declain");	
//					}else{
//						res.setChklstResponseType("Non Applicable");
//					}
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		

		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getAreaManageChklst(int topicId,int chklstOwnerRole, int chklstOwnerSite)
		{
			ArrayList<TblChklstRes> retval = null;
			Session session = HibernateUtil.currentSession();
			ArrayList<TblChklst> resList = null;
			try {
				TblChklstTopic tp = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
				Query  query =  session.createQuery("FROM  TblChklst ta where ta.chklstOwnerRole=:chklstOwnerRole and ta.chklstOwnerSite=:chklstOwnerSite and ta.tblChklstTopic = :topicId and  ta.chklstEndDate>=current_date ORDER BY ta.updatedDate DESC")
							    .setInteger("chklstOwnerRole", chklstOwnerRole)
							    .setInteger("chklstOwnerSite", chklstOwnerSite)
								.setEntity("topicId", tp);
				resList = (ArrayList<TblChklst>) query.list();
			    retval = new ArrayList<TblChklstRes>();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			for(TblChklst t: resList){

				TblChklstRes res=new TblChklstRes();
				res.setChklstSubject(t.getChklstSubject());
				res.setChklstId(t.getChklstId());
			//	res.setChklstType(t.getChklstType());
				res.setChklstStartDate(formatter.format(t.getChklstStartDate()));
				res.setChklstEndDate(formatter.format(t.getChklstEndDate()));
				Query storeQuery =  session.createSQLQuery("SELECT  COUNT(chklst_id) from tbl_chklst_store where (chklst_status='R' or chklst_status='I' ) and chklst_id=:chklst_id")
				           .setInteger("chklst_id", t.getChklstId());
				List listStore= storeQuery.list();
				
				if(listStore.get(0)!=null){
					if(listStore.get(0).toString().equals("0")){}else{
					res.setStoreCount(listStore.get(0).toString());
					}
				}
				res.setTotalAreaCount(String.valueOf(t.getTblChklstAreas().size()));
			   	retval.add(res);
			}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		
		}
		
		/**
		 * Get View the Area chklst 
		 * @param id
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public TblChklst getAreaChklst(int chklstId,int chklstAreaId){
			
			Set<TblChklstArea> resList=new HashSet<TblChklstArea>();
			Session session = HibernateUtil.currentSession();
			TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
			try{
				List<TblChklstArea> list = session.createQuery("FROM  TblChklstArea t WHERE t.chklstAreaId=:chklstAreaId AND t.tblChklst = :chklstId")
					    .setInteger("chklstAreaId", chklstAreaId)    
						.setEntity("chklstId", ta).list();
				 resList = new HashSet<TblChklstArea>(list);
				 ta.setTblChklstAreas(resList);
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return ta;
		}
		

		
		/**
		 * Get View the Store chklst 
		 * @param id
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public TblChklst getStoreChklst(int chklstId,int chklstStoreId){
			
			Set<TblChklstStore> resList=new HashSet<TblChklstStore>();
			Session session = HibernateUtil.currentSession();
			TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
			try{
				List<TblChklstStore> list = session.createQuery("FROM  TblChklstStore t WHERE t.chklstStoreId=:chklstStoreId AND t.tblChklst = :chklstId")
					    .setInteger("chklstStoreId", chklstStoreId)    
						.setEntity("chklstId", ta).list();
				resList = new HashSet<TblChklstStore>(list);
				ta.setTblChklstStores(resList);
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return ta;
		}
		

		@SuppressWarnings("unchecked")
		public ArrayList<TblChklst> getPostAll(int topicId)
		{
			ArrayList<TblChklst> retval = new ArrayList<TblChklst>();
			Session session = HibernateUtil.currentSession();
			try {
				TblChklstTopic tp = (TblChklstTopic)session.get(TblChklstTopic.class, topicId);
				Query  query =  session.createQuery("FROM  TblChklst t WHERE t.tblChklstTopic = :topicId ")
						        .setEntity("topicId", tp);
				retval = (ArrayList<TblChklst>) query.list();
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		


		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getAreaResponse(int chklstId)
		{
			
			ArrayList<TblChklstArea> resList = new ArrayList<TblChklstArea>();
			ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
			Session session = HibernateUtil.currentSession();
			try {
			
				TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
				
				Query  query =  session.createQuery("FROM  TblChklstArea t WHERE (t.chklstStatus='R' OR t.chklstStatus='I') AND t.tblChklst = :chklstId ")
							.setEntity("chklstId", ta);
							
				
				resList = (ArrayList<TblChklstArea>) query.list();
				for(TblChklstArea t: resList){
                                        double percentage=0;
                                        Query queryTotal=session.createQuery("FROM TblChklstAreaDetail d "+
                                                 " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblArea=:TblArea ")
                                                .setEntity("TblChklst", ta)
                                                .setEntity("TblArea", t.getTblArea());
                                        ArrayList<TblChklstAreaDetail> areaDetList=(ArrayList<TblChklstAreaDetail>) queryTotal.list();

                                        Query queryYes=session.createQuery("FROM TblChklstAreaDetail d  "+
                                                 " INNER JOIN FETCH d.tblChklstDetail  WHERE d.tblChklst = :TblChklst AND d.tblArea=:TblArea AND d.chklstResponseStatus='Y' ")
                                                .setEntity("TblChklst",ta)
                                                .setEntity("TblArea", t.getTblArea());
                                        ArrayList<TblChklstAreaDetail> areaYesList=(ArrayList<TblChklstAreaDetail>) queryYes.list();

                                        Query queryNo=session.createQuery("FROM TblChklstAreaDetail d  "+
                                                " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblArea=:TblArea AND d.chklstResponseStatus='A' ")
                                                .setEntity("TblChklst",ta)
                                                .setEntity("TblArea", t.getTblArea());
                                        ArrayList<TblChklstAreaDetail> areaNoList=(ArrayList<TblChklstAreaDetail>) queryNo.list();
                                        SimpleDateFormat formatdate = new SimpleDateFormat("dd-MMM-yyyy"); 
                                        NumberFormat formatter = new DecimalFormat("#0.00");


                                        double total1=0,yes1=0,no1=0;
                                        double temval;
                                        for(TblChklstAreaDetail dt: areaDetList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            total1 = total1 + temval;
                                         }
                                        for(TblChklstAreaDetail dt: areaYesList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            yes1 = yes1 + temval;
                                         }
                                        for(TblChklstAreaDetail dt: areaNoList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            no1 = no1 + temval;
                                         }               
                                        percentage=(yes1/(total1-no1))*100;
                                    
					TblChklstRes res=new TblChklstRes();
					if(Double.isNaN(percentage)){
						res.setPercentage("N/A");
						res.setGrade("N/A");
					}else{
						if(percentage>=90){
							res.setGrade("Excellent");
						}else if(percentage>=80){
							res.setGrade("Good");	
						}else if(percentage>=70){
							res.setGrade("Satisfactory");	
						}else if(percentage>=60){
							res.setGrade("Need Improvement");
						}else if(percentage<60){
							res.setGrade("Unacceptable");
						}
						res.setPercentage(formatter.format(percentage)+"%");
						
					}
					
					res.setChklstSubject(ta.getChklstSubject());
					res.setChklstId(ta.getChklstId());
					res.setChklstRemark(t.getChklstRemark());
					res.setChklstAreaId(t.getChklstAreaId());
					res.setRecipient(t.getTblArea().getAreaName());
					
					res.setAreaId(t.getTblArea().getAreaId());
                                        res.setChklstStartDate(formatdate.format(ta.getChklstStartDate()));
                                        res.setResponseDate(formatdate.format(t.getUpdatedDate()));                                       
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		

		

		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getStoreResponse(int chklstId)
		{
			ArrayList<TblChklstStore> resList = new ArrayList<TblChklstStore>();
			ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
			Session session = HibernateUtil.currentSession();
			try {
				TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
				
				Query  query =  session.createQuery("FROM  TblChklstStore t WHERE (t.chklstStatus='R' OR t.chklstStatus='I') AND t.tblChklst = :chklstId ")
						    
							.setEntity("chklstId", ta);
				resList = (ArrayList<TblChklstStore>) query.list();
				for(TblChklstStore t: resList){
                                        double percentage=0;
                                        Query queryTotal=session.createQuery("FROM TblChklstStoreDetail d "+
                                                 " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore ")
                                                .setEntity("TblChklst", ta)
                                                .setEntity("TblStore", t.getTblStore());
                                        ArrayList<TblChklstStoreDetail> storeDetList=(ArrayList<TblChklstStoreDetail>) queryTotal.list();

                                        Query queryYes=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                 " INNER JOIN FETCH d.tblChklstDetail  WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='Y' ")
                                                .setEntity("TblChklst",ta)
                                                .setEntity("TblStore", t.getTblStore());
                                        ArrayList<TblChklstStoreDetail> areaYesList=(ArrayList<TblChklstStoreDetail>) queryYes.list();

                                        Query queryNo=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='A' ").setEntity("TblChklst",ta).setEntity("TblStore", t.getTblStore());
                                        ArrayList<TblChklstStoreDetail> areaNoList=(ArrayList<TblChklstStoreDetail>) queryNo.list();
                                        SimpleDateFormat formatdate = new SimpleDateFormat("dd-MMM-yyyy"); 
                                        NumberFormat formatter = new DecimalFormat("#0.00");


                                        double total1=0,yes1=0,no1=0;
                                        double temval;
                                        for(TblChklstStoreDetail dt: storeDetList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            total1 = total1 + temval;
                                         }
                                        for(TblChklstStoreDetail dt: areaYesList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            yes1 = yes1 + temval;
                                         }
                                        for(TblChklstStoreDetail dt: areaNoList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            no1 = no1 + temval;
                                         }               
                                        percentage=(yes1/(total1-no1))*100;
                                    
					TblChklstRes res=new TblChklstRes();
					if(Double.isNaN(percentage)){
						res.setPercentage("N/A");
						res.setGrade("N/A");
					}else{
						if(percentage>=90){
							res.setGrade("Excellent");
						}else if(percentage>=80){
							res.setGrade("Good");	
						}else if(percentage>=70){
							res.setGrade("Satisfactory");	
						}else if(percentage>=60){
							res.setGrade("Need Improvement");
						}else if(percentage<60){
							res.setGrade("Unacceptable");
						}
						res.setPercentage(formatter.format(percentage)+"%");
						
					}
					res.setChklstSubject(ta.getChklstSubject());
					res.setChklstId(ta.getChklstId());
					
					res.setChklstRemark(t.getChklstRemark());
					res.setRecipient(t.getTblStore().getStoreName());
					res.setChklstStoreId(t.getChklstStoreId());
                                        
                                        res.setAreaName(t.getTblStore().getTblArea().getAreaCd());
                                        res.setChklstStartDate(formatdate.format(ta.getChklstStartDate()));
                                        res.setResponseDate(formatdate.format(t.getUpdatedDate()));
                                        
                                        
//					if(t.getChklstResponse().equals("A")){
//						res.setChklstResponseType("Active");
//					}else if(t.getChklstResponse().equals("D")){
//						res.setChklstResponseType("Declain");	
//					}else{
//						res.setChklstResponseType("Non Applicable");
//					}
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		

		//Template
		

		@SuppressWarnings("unchecked")
		public ArrayList<TblMasterRes> getTemplateList(String searchTxt)
		{
			ArrayList<TblMasterRes> retval = null;
			ArrayList<TblChklstTemplate> resList = null;
			Session session = HibernateUtil.currentSession();
			try {
				Query  query =  session.createQuery("FROM TblChklstTemplate AS f ORDER BY templateName");
				if(searchTxt != null && searchTxt.length() > 0){
					
					query=session.createQuery("FROM TblChklstTemplate AS f WHERE upper(f.templateName) LIKE :searchTxt ")
							.setString("searchTxt", "%"+searchTxt.toUpperCase()+"%");
				}
				resList = (ArrayList<TblChklstTemplate>) query.list();

				retval = new ArrayList<TblMasterRes>();
				for(TblChklstTemplate f: resList){
					TblMasterRes res = new TblMasterRes();
					res.setMstName(f.getTemplateName());
					res.setId(f.getTemplateId());
					if(f.getTblChklstTopics() != null)
						res.setDtlCount(f.getTblChklstTopics().size());
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
		public ArrayList<TblChklstTemplate> getTemplateAll()
		{
			
			ArrayList<TblChklstTemplate> resList = null;
			Session session = HibernateUtil.currentSession();
			try {
				Query  query =  session.createQuery("FROM TblChklstTemplate AS f ORDER BY templateName");
				
				resList = (ArrayList<TblChklstTemplate>) query.list();

			
				
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return resList;
		}
		
		public TblChklstTemplate getTemplateById(int id){
			TblChklstTemplate f = null;
			Session session = HibernateUtil.currentSession();
			try{
				f = (TblChklstTemplate)session.get(TblChklstTemplate.class, id);
				
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return f;
		}
		
		public TblChklstTemplate getTemplateByName(String templateName) {
			TblChklstTemplate retval = null;
			Session session = HibernateUtil.currentSession();
			try {
				Query query = session.createQuery("FROM TblChklstTemplate AS f WHERE UPPER(f.templateName) = :templateName ");
				query.setString("templateName", templateName.toUpperCase());
				retval = (TblChklstTemplate) query.uniqueResult();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		
		/**
		 * Save Template Details
		 * @param f
		 * @return
		 */
		public boolean saveTemplate(TblChklstTemplate f){
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
		
		public boolean updateTemplate(TblChklstTemplate f){
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
		
		
		public boolean updateTemplateHeader(String header,int templateId){
			boolean retval = false;
			Transaction transaction = null;
			Session session = HibernateUtil.currentSession();
			try {
				transaction = session.beginTransaction();
				Query query=session.createSQLQuery("UPDATE tbl_chklst_template  SET template_headers=:tempHead WHERE template_id=:templateId")
						.setString("tempHead", header)
						.setInteger("templateId", templateId);
				query.executeUpdate();
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
		 * Delete 
		 * @param grpId
		 * @return
		 */
		public boolean deleteTemplate(int id) throws TransException{
			boolean retval = false;

			Transaction transaction = null;
			Session session = HibernateUtil.currentSession();
			try {
				transaction = session.beginTransaction();

				TblChklstTemplate c = (TblChklstTemplate)session.get(TblChklstTemplate.class, id);
				
				session.delete(c);
				
				transaction.commit();
				retval = true;
			}  catch (HibernateException e) {
				transaction.rollback();
				e.printStackTrace();
				throw new TransException("Checklist template already in use ....!!!");
			} 
			HibernateUtil.closeSession();
			return retval;		
		}
		
		
		public TblChklstTemplate getTemplate(int topicId) {
			TblChklstTemplate retval = null;
			Session session = HibernateUtil.currentSession();
			try {
				Query topicQuery=session.createSQLQuery("select template_id from tbl_chklst_topic where topic_id=:topicId");
				
				topicQuery.setInteger("topicId", topicId);
				Integer templateId=(Integer) topicQuery.uniqueResult();
		
				Query query = session.createQuery("FROM TblChklstTemplate AS f WHERE f.templateId = :templateId ");
				
				query.setInteger("templateId", templateId);
				retval = (TblChklstTemplate) query.uniqueResult();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		
		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstDetailRes> chklstDetailList(int chklstId,String recType,int areaId, int storeId)
		{
			ArrayList<TblChklstDetailRes> retval = null;
			ArrayList<TblChklstAreaDetail> resList = null;
			ArrayList<TblChklstStoreDetail> resStoreList = null;
			Session session = HibernateUtil.currentSession();
			try {
				TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
				if(recType.equals("A")){
				TblArea ts = (TblArea)session.get(TblArea.class, areaId);	
				Query  query =  session.createQuery("FROM TblChklstAreaDetail AS d INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblArea=:TblArea ORDER BY d.chklstAreaDetailId").setEntity("TblChklst", ta).setEntity("TblArea", ts);
				resList = (ArrayList<TblChklstAreaDetail>) query.list();
				retval = new ArrayList<TblChklstDetailRes>();
				for(TblChklstAreaDetail c: resList){
					TblChklstDetailRes res = new TblChklstDetailRes();
					res.setChklstDetailId(c.getChklstAreaDetailId());
					res.setChklstId(c.getTblChklst().getChklstId());
					StringTokenizer st=new StringTokenizer(c.getTblChklstDetail().getChklstValue(), "|");
					ArrayList<String>	headdesc=new ArrayList<String>();
			    	while(st.hasMoreTokens()){
		    		
						headdesc.add(st.nextElement().toString());
					    
					}
			    	res.setTempList(headdesc);
			    	res.setChkChoice(c.getChklstResponseStatus());
			    	res.setChkResponse(c.getChklstResponse());
					retval.add(res);
				
				}}else{
					TblStore ts = (TblStore)session.get(TblStore.class, storeId);
			
					Query  query =  session.createQuery("FROM TblChklstStoreDetail AS d INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore ORDER BY d.chklstStoreDetailId").setEntity("TblChklst", ta).setEntity("TblStore", ts);
					resStoreList = (ArrayList<TblChklstStoreDetail>) query.list();
					retval = new ArrayList<TblChklstDetailRes>();
					for(TblChklstStoreDetail c: resStoreList){
						
						TblChklstDetailRes res = new TblChklstDetailRes();
						res.setChklstDetailId(c.getChklstStoreDetailId());
						res.setChklstId(c.getTblChklst().getChklstId());
						StringTokenizer st=new StringTokenizer(c.getTblChklstDetail().getChklstValue(), "|");
						ArrayList<String>	headdesc=new ArrayList<String>();
				    	while(st.hasMoreTokens()){
			    		
							headdesc.add(st.nextElement().toString());
						    
						}
				     	res.setTempList(headdesc);
				    	res.setChkChoice(c.getChklstResponseStatus());
				    	res.setChkResponse(c.getChklstResponse());
				    	res.setChkResponseSm(c.getChklstResponseSm());
						retval.add(res);
					
					}
				}
				
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		

		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstDetailRes> chklstStoreDetailList(int chklstId,String recType, int storeId)
		{
			ArrayList<TblChklstDetailRes> retval = null;
			ArrayList<TblChklstStoreDetail> resStoreList = null;
			Session session = HibernateUtil.currentSession();
			try {
				TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
				
					TblStore ts = (TblStore)session.get(TblStore.class, storeId);
					Query  query =  session.createQuery("FROM TblChklstStoreDetail AS d INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore  ORDER BY d.chklstStoreDetailId").setEntity("TblChklst", ta).setEntity("TblStore", ts);
					resStoreList = (ArrayList<TblChklstStoreDetail>) query.list();
					retval = new ArrayList<TblChklstDetailRes>();
					for(TblChklstStoreDetail c: resStoreList){
						
						TblChklstDetailRes res = new TblChklstDetailRes();
						res.setChklstDetailId(c.getChklstStoreDetailId());
						res.setChklstId(c.getTblChklst().getChklstId());
						StringTokenizer st=new StringTokenizer(c.getTblChklstDetail().getChklstValue(), "|");
						ArrayList<String>	headdesc=new ArrayList<String>();
				    	while(st.hasMoreTokens()){
			    		
							headdesc.add(st.nextElement().toString());
						    
						}
				     	res.setTempList(headdesc);
				    	res.setChkChoice(c.getChklstResponseStatus());
				    	res.setChkResponse(c.getChklstResponse());
				    	res.setChkResponseSm(c.getChklstResponseSm());
						retval.add(res);
					
					}
				
				
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		
		
		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstDetailRes> chklstDetail(int chklstId)
		{
			ArrayList<TblChklstDetailRes> retval = null;
			ArrayList<TblChklstDetail> resList = null;
			
			Session session = HibernateUtil.currentSession();
			try {
				TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
				Query  query =  session.createQuery("FROM TblChklstDetail AS d WHERE d.tblChklst = :TblChklst ").setEntity("TblChklst", ta);
				resList = (ArrayList<TblChklstDetail>) query.list();
				retval = new ArrayList<TblChklstDetailRes>();
				for(TblChklstDetail c: resList){
					
					TblChklstDetailRes res = new TblChklstDetailRes();
					res.setChklstDetailId(c.getChklstDetailId());
					res.setChklstId(c.getTblChklst().getChklstId());
					StringTokenizer st=new StringTokenizer(c.getChklstValue(), "|");
					ArrayList<String>	headdesc=new ArrayList<String>();
			    	while(st.hasMoreTokens()){
					
						headdesc.add(st.nextElement().toString());
					    
					}
			    	res.setTempList(headdesc);
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		
		

		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstDetail> chklstResDetail(int chklstId)
		{
		
			ArrayList<TblChklstDetail> resList = null;
			
			Session session = HibernateUtil.currentSession();
			try {
				TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
				Query  query =  session.createQuery("FROM TblChklstDetail AS d WHERE d.tblChklst = :TblChklst ").setEntity("TblChklst", ta);
				resList = (ArrayList<TblChklstDetail>) query.list();
				
				
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return resList;
		}
		
		
		
		
		
		
		
		
		////
		@SuppressWarnings("unchecked")
		public boolean updateStoreResponse(int storeId, int chklstId,TblChklstStore a, int chklstStoreId,ArrayList<TblChklstDetailRes> response){
			boolean retval = false;

			Transaction transaction = null;
			Session session = HibernateUtil.currentSession();
			try {
				
				transaction = session.beginTransaction();
				TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
				TblStore ts = (TblStore)session.get(TblStore.class, storeId);
				String storeDetail = "update TblChklstStoreDetail as a set a.chklstResponse=:response,a.chklstResponseStatus =:status where a.chklstStoreDetailId = :chklstStoreId ";
				for(TblChklstDetailRes res:response){
					Query query=session.createQuery(storeDetail);
					query.setString("response",res.getChkResponse());
					query.setString("status", res.getChkChoice());
					query.setInteger("chklstStoreId", res.getChklstDetailId());
					query.executeUpdate();
								
				}
				Query  queryStore =  session.createQuery("FROM TblChklstStoreDetail AS d WHERE d.tblChklst = :TblChklst AND d.tblStore =:store AND (d.chklstResponseStatus='N' or d.chklstResponseStatus='A') ").setEntity("TblChklst", ta).setEntity("store", ts);
				ArrayList<TblChklstStoreDetail> resStore=(ArrayList<TblChklstStoreDetail>)queryStore.list();
				String hql = "update TblChklstStore as a set a.chklstStatus=:chklstStatus,a.chklstRemark =:chklstRemark,a.updatedBy=:updatedBy,a.updatedDate=CURRENT_TIMESTAMP() where a.chklstStoreId = :chklstStoreId ";
			    Query query = session.createQuery(hql);
			    if(resStore.size()==0){
			    query.setString("chklstStatus","R");
				}else{
				query.setString("chklstStatus","I");
				}
			    query.setString("chklstRemark",a.getChklstRemark());
			    query.setString("updatedBy",a.getUpdatedBy());
			    //query.setDate("updatedDate", a.getUpdatedDate());
			    query.setInteger("chklstStoreId", chklstStoreId);
			    query.executeUpdate();
			//	saveChklstStoreDetail(storeId,chklstId,a,response,session);
				transaction.commit();
				retval = true;
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			} 
			HibernateUtil.closeSession();
			return retval;		
		}
		
		
		
	
		
		//// Temporarily Save checklist
		public boolean tempSaveStoreResponse(int storeId, int chklstId,TblChklstStore a, int chklstStoreId,ArrayList<TblChklstDetailRes> response){
			boolean retval = false;

			Transaction transaction = null;
			Session session = HibernateUtil.currentSession();
			try {
				
				transaction = session.beginTransaction();
				TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
				TblStore ts = (TblStore)session.get(TblStore.class, storeId);
				String storeDetail = "update TblChklstStoreDetail as a set a.chklstResponse=:response,a.chklstResponseStatus =:status where a.chklstStoreDetailId = :chklstStoreId ";
				for(TblChklstDetailRes res:response){
					Query query=session.createQuery(storeDetail);
					query.setString("response",res.getChkResponse());
					query.setString("status", res.getChkChoice());
					query.setInteger("chklstStoreId", res.getChklstDetailId());
					query.executeUpdate();
								
				}
				//Query  queryStore =  session.createQuery("FROM TblChklstStoreDetail AS d WHERE d.tblChklst = :TblChklst AND d.tblStore =:store AND (d.chklstResponseStatus='N' or d.chklstResponseStatus='A') ").setEntity("TblChklst", ta).setEntity("store", ts);
			//	ArrayList<TblChklstStoreDetail> resStore=(ArrayList<TblChklstStoreDetail>)queryStore.list();
				String hql = "update TblChklstStore as a set a.chklstStatus=:chklstStatus,a.chklstRemark =:chklstRemark,a.updatedBy=:updatedBy,a.updatedDate=:updatedDate where a.chklstStoreId = :chklstStoreId ";
			    Query query = session.createQuery(hql);
			   // if(resStore.size()==0){
			    query.setString("chklstStatus","P");
			/*	}else{
				query.setString("chklstStatus","I");
				}*/
			    query.setString("chklstRemark",a.getChklstRemark());
			    query.setString("updatedBy",a.getUpdatedBy());
			    query.setDate("updatedDate", a.getUpdatedDate());
			    query.setInteger("chklstStoreId", chklstStoreId);
			    query.executeUpdate();
			//	saveChklstStoreDetail(storeId,chklstId,a,response,session);
				transaction.commit();
				retval = true;
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			} 
			HibernateUtil.closeSession();
			return retval;		
		}
		
		
		
		// Area Temp Save 

		public boolean tempSaveAreaResponse(int areaId, int chklstId,TblChklstArea a, int chklstAreaId,ArrayList<TblChklstDetailRes> response){
			boolean retval = false;
			boolean resval=true;
			Transaction transaction = null;
			Session session = HibernateUtil.currentSession();
			try {
				transaction = session.beginTransaction();
				TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
				TblArea ts = (TblArea)session.get(TblArea.class, areaId);
				String storeDetail = "update TblChklstAreaDetail as a set a.chklstResponse=:response,a.chklstResponseStatus =:status where a.chklstAreaDetailId = :chklstAreaId ";
				for(TblChklstDetailRes res:response){
					Query query=session.createQuery(storeDetail);
					query.setString("response",res.getChkResponse());
					query.setString("status", res.getChkChoice());
					query.setInteger("chklstAreaId", res.getChklstDetailId());
					query.executeUpdate();
								
				}
				String hql = "update TblChklstArea as a set a.chklstStatus=:chklstStatus,a.chklstRemark =:chklstRemark,a.updatedBy=:updatedBy,a.updatedDate=:updatedDate where a.chklstAreaId = :chklstAreaId ";
			    Query query = session.createQuery(hql);
			   
				query.setString("chklstStatus","P");
				
			    query.setString("chklstRemark",a.getChklstRemark());
			    query.setString("updatedBy",a.getUpdatedBy());
			    query.setDate("updatedDate", a.getUpdatedDate());
			    query.setInteger("chklstAreaId", chklstAreaId);
			   
			    transaction.commit();
				retval = true;
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			} 
			HibernateUtil.closeSession();
			return retval;		
		}
		
		
		////
		@SuppressWarnings("unchecked")
		public boolean updateStoreResponseFromArea(int storeId, int chklstId,TblChklstStore a, int chklstStoreId,ArrayList<TblChklstDetailRes> response){
			boolean retval = false;

			Transaction transaction = null;
			Session session = HibernateUtil.currentSession();
			try {
				
				transaction = session.beginTransaction();
				TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
				TblStore ts = (TblStore)session.get(TblStore.class, storeId);
				String storeDetail = "update TblChklstStoreDetail as a set a.chklstResponse=:response,a.chklstResponseStatus =:status where a.chklstStoreDetailId = :chklstStoreId ";
				for(TblChklstDetailRes res:response){
					Query query=session.createQuery(storeDetail);
					query.setString("response",res.getChkResponse());
					query.setString("status", res.getChkChoice());
					query.setInteger("chklstStoreId", res.getChklstDetailId());
					query.executeUpdate();
								
				}
				//String hql = "update TblChklstStore as a set a.chklstStatus=:chklstStatus,a.chklstRemark =:chklstRemark,a.updatedBy=:updatedBy,a.updatedDate=:updatedDate where a.chklstStoreId = :chklstStoreId ";
				String hql = "update TblChklstStore as a set a.chklstStatus=:chklstStatus,a.chklstRemark =:chklstRemark,a.updatedBy=:updatedBy,a.updatedDate=CURRENT_TIMESTAMP() where a.chklstStoreId = :chklstStoreId ";
				
				
			    Query query = session.createQuery(hql);
			   
				query.setString("chklstStatus","I");
				
			    query.setString("chklstRemark",a.getChklstRemark());
			    query.setString("updatedBy",a.getUpdatedBy());
			    //query.setDate("updatedDate", a.getUpdatedDate());
			    query.setInteger("chklstStoreId", chklstStoreId);
			    query.executeUpdate();
			//	saveChklstStoreDetail(storeId,chklstId,a,response,session);
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
		public boolean updateAreaResponse(int areaId, int chklstId,TblChklstArea a, int chklstAreaId,ArrayList<TblChklstDetailRes> response){
			boolean retval = false;
			boolean resval=true;
			Transaction transaction = null;
			Session session = HibernateUtil.currentSession();
			try {
				transaction = session.beginTransaction();
				TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
				TblArea ts = (TblArea)session.get(TblArea.class, areaId);
				String storeDetail = "update TblChklstAreaDetail as a set a.chklstResponse=:response,a.chklstResponseStatus =:status where a.chklstAreaDetailId = :chklstAreaId ";
				for(TblChklstDetailRes res:response){
					Query query=session.createQuery(storeDetail);
					query.setString("response",res.getChkResponse());
					query.setString("status", res.getChkChoice());
					query.setInteger("chklstAreaId", res.getChklstDetailId());
					query.executeUpdate();
								
				}
				Query  queryStore =  session.createQuery("FROM TblChklstAreaDetail AS d WHERE d.tblChklst = :TblChklst AND d.tblArea =:area AND (d.chklstResponseStatus='N' or d.chklstResponseStatus='A') ").setEntity("TblChklst", ta).setEntity("area", ts);
				ArrayList<TblChklstAreaDetail> resStore=(ArrayList<TblChklstAreaDetail>)queryStore.list();
				//String hql = "update TblChklstArea as a set a.chklstStatus=:chklstStatus,a.chklstRemark =:chklstRemark,a.updatedBy=:updatedBy,a.updatedDate=:updatedDate where a.chklstAreaId = :chklstAreaId ";
				String hql = "update TblChklstArea as a set a.chklstStatus=:chklstStatus,a.chklstRemark =:chklstRemark,a.updatedBy=:updatedBy,a.updatedDate=CURRENT_TIMESTAMP() where a.chklstAreaId = :chklstAreaId ";
				
				
			    Query query = session.createQuery(hql);
			    if(resStore.size()==0){
			    query.setString("chklstStatus","R");
				}else{
				query.setString("chklstStatus","I");
				}
			    query.setString("chklstRemark",a.getChklstRemark());
			    query.setString("updatedBy",a.getUpdatedBy());
			    //query.setDate("updatedDate", a.getUpdatedDate());
			    query.setInteger("chklstAreaId", chklstAreaId);
			   

//start Percentage
			    
			    TblChklst tc = (TblChklst)session.get(TblChklst.class, chklstId);
			    ArrayList<TblChklstStore> storeList=new ArrayList<TblChklstStore>();
				
				Query  storeQuery =  session.createQuery("FROM  TblChklstStore t INNER JOIN FETCH t.tblStore WHERE t.tblChklst = :chklstId AND t.chklstSite='A' ")
		    			.setEntity("chklstId", tc);
		    			
				storeList = (ArrayList<TblChklstStore>) storeQuery.list();
			for(TblChklstStore s: storeList){
				if(areaId==s.getTblStore().getTblArea().getAreaId()){
					SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
					
                                        double percentage=0;
                                        Query queryTotal=session.createQuery("FROM TblChklstStoreDetail d "+
                                                 " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore ")
                                                .setEntity("TblChklst", tc)
                                                .setEntity("TblStore", s.getTblStore());
                                        ArrayList<TblChklstStoreDetail> storeDetList=(ArrayList<TblChklstStoreDetail>) queryTotal.list();

                                        Query queryYes=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                 " INNER JOIN FETCH d.tblChklstDetail  WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='Y' ").setEntity("TblChklst",tc).setEntity("TblStore", s.getTblStore());
                                        ArrayList<TblChklstStoreDetail> areaYesList=(ArrayList<TblChklstStoreDetail>) queryYes.list();

                                        Query queryNo=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='A' ").setEntity("TblChklst",tc).setEntity("TblStore", s.getTblStore());
                                        ArrayList<TblChklstStoreDetail> areaNoList=(ArrayList<TblChklstStoreDetail>) queryNo.list();
                                        NumberFormat formatt = new DecimalFormat("#0.00");


                                        double total1=0,yes1=0,no1=0;
                                        double temval;
                                        for(TblChklstStoreDetail dt: storeDetList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            total1 = total1 + temval;
                                         }
                                        for(TblChklstStoreDetail dt: areaYesList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            yes1 = yes1 + temval;
                                         }
                                        for(TblChklstStoreDetail dt: areaNoList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            no1 = no1 + temval;
                                         }               
                                        percentage=(yes1/(total1-no1))*100;
                                        
				
					if(Double.isNaN(percentage)){
						resval=false;
						
					}else{
					if(percentage>80){
						if(percentage==100){
							
						}else{
						resval=false;
						}
						
					}else if(percentage>50){
						resval=false;
					}else{
						resval=false;
					}
						//r.setPercentage(formatter.format(percentage)+"%");
						
					}}}
				if(resval){
					 query.setString("chklstStatus","R");
				}
				query.executeUpdate();
			   
			    //End
			    
			    
				transaction.commit();
				retval = true;
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			} 
			HibernateUtil.closeSession();
			return retval;		
		}
		
//		
//		private void saveChklstAreaDetail(int areaId, int chklstId,TblChklstArea a1, ArrayList<TblChklstDetailRes> getChklstDetail,  Session session) {
//			
//			TblArea ts = (TblArea)session.get(TblArea.class, areaId);
//			TblChklst tc = (TblChklst)session.get(TblChklst.class, chklstId);
//			for(TblChklstDetailRes res: getChklstDetail){
//				TblChklstAreaDetail t=new TblChklstAreaDetail();
//				TblChklstDetail ta = (TblChklstDetail)session.get(TblChklstDetail.class, res.getChklstDetailId());
//				t.setTblChklstDetail(ta);
//				t.setTblChklst(tc);
//			//	t.setChklstResponse(res.getChkResponse());
//				t.setUpdatedBy(a.getUpdatedBy());
//				t.setTblArea(ts);
//				t.setChklstResponseStatus("A");
//				t.setUpdatedDate(new Date());
//				session.saveOrUpdate(t);
//				
//			}
//		}
		
		
		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstDetailRes> chklstStoreDetail(int chklstId,int storeId)
		{
			ArrayList<TblChklstDetailRes> retval = null;
			ArrayList<TblChklstStoreDetail> resList = null;
			
			Session session = HibernateUtil.currentSession();
			try {
				TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
				TblStore ts = (TblStore)session.get(TblStore.class, storeId);
				Query  query =  session.createQuery("FROM TblChklstStoreDetail AS d WHERE d.tblChklst = :tblChklst and d.tblStore=:tblStore").setEntity("tblChklst", ta).setEntity("tblStore", ts);
				resList = (ArrayList<TblChklstStoreDetail>) query.list();
				retval = new ArrayList<TblChklstDetailRes>();
				for(TblChklstStoreDetail c: resList){
					
					TblChklstDetailRes res = new TblChklstDetailRes();
				
					StringTokenizer st=new StringTokenizer(c.getTblChklstDetail().getChklstValue(), "|");
					ArrayList<String>	headdesc=new ArrayList<String>();
			    	while(st.hasMoreTokens()){
					
						headdesc.add(st.nextElement().toString());
					    
					}
			    	headdesc.add(c.getChklstResponse());
			    	res.setChkChoice(c.getChklstResponseStatus());
			    	res.setChkResponse(c.getChklstResponse());
			    	res.setChkResponseSm(c.getChklstResponseSm());
			    	res.setChklstDetailId(c.getChklstStoreDetailId());
			    	res.setTempList(headdesc);
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		
		
		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstDetailRes> chklstAreaDetail(int chklstId,int areaId)
		{
			ArrayList<TblChklstDetailRes> retval = null;
			ArrayList<TblChklstAreaDetail> resList = null;
			
			Session session = HibernateUtil.currentSession();
			try {
				TblChklst ta = (TblChklst)session.get(TblChklst.class, chklstId);
				TblArea ts = (TblArea)session.get(TblArea.class, areaId);
				Query  query =  session.createQuery("FROM TblChklstAreaDetail AS d WHERE d.tblChklst = :tblChklst and d.tblArea=:tblArea").setEntity("tblChklst", ta).setEntity("tblArea", ts);
				resList = (ArrayList<TblChklstAreaDetail>) query.list();
				retval = new ArrayList<TblChklstDetailRes>();
				for(TblChklstAreaDetail c: resList){
					
					TblChklstDetailRes res = new TblChklstDetailRes();
					StringTokenizer st=new StringTokenizer(c.getTblChklstDetail().getChklstValue(), "|");
					ArrayList<String>	headdesc=new ArrayList<String>();
			    	while(st.hasMoreTokens()){
					
						headdesc.add(st.nextElement().toString());
					    
					}
			    	headdesc.add(c.getChklstResponse());
			    	res.setTempList(headdesc);
			    	res.setChkChoice(c.getChklstResponseStatus());
			    	res.setChkResponse(c.getChklstResponse());
					retval.add(res);
				}
			}catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return retval;
		}
		
		// Store Details
		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstStore> getStores(TblChklst p){
			
			ArrayList<TblChklstStore> storeList=new ArrayList<TblChklstStore>();
			Transaction transaction = null;
			Session session = HibernateUtil.currentSession();
			try {
				transaction = session.beginTransaction();
				
				Query  query =  session.createQuery("FROM  TblChklstStore t INNER JOIN FETCH t.tblStore WHERE t.tblChklst = :chklstId AND t.chklstSite='S' ")
		    			.setEntity("chklstId", p);
				storeList = (ArrayList<TblChklstStore>) query.list();
			
				
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			} 
			HibernateUtil.closeSession();
			return  storeList;
		}
	
		// Area Details
		@SuppressWarnings("unchecked")
		public ArrayList<TblChklstArea> getAreas(TblChklst p){
			
			ArrayList<TblChklstArea> areaList=new ArrayList<TblChklstArea>();
			Transaction transaction = null;
			Session session = HibernateUtil.currentSession();
			try {
				transaction = session.beginTransaction();
				
				Query  query =  session.createQuery("FROM  TblChklstArea t INNER JOIN FETCH t.tblArea WHERE t.tblChklst = :chklstId ")
		    			.setEntity("chklstId", p);
				areaList = (ArrayList<TblChklstArea>) query.list();
			
				
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			} 
			HibernateUtil.closeSession();
			return  areaList;
		}
		
		
		
		// Store Details
				@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getAreaStores(int chklstId, int areaId,int chklstAreaId){
					ArrayList<TblChklstRes> res=new ArrayList<TblChklstRes>();
					ArrayList<TblChklstStore> storeList=new ArrayList<TblChklstStore>();
					Transaction transaction = null;
					Session session = HibernateUtil.currentSession();
					try {
						transaction = session.beginTransaction();
						TblChklst tc = (TblChklst)session.get(TblChklst.class, chklstId);
					
					
				//		Query  query =  session.createQuery("FROM  TblChklstStore t INNER JOIN FETCH t.tblStore WHERE t.tblChklst = :chklstId AND (t.chklstSite='A' OR t.chklstSite='S')")
						Query  query =  session.createQuery("FROM  TblChklstStore t INNER JOIN FETCH t.tblStore WHERE t.tblChklst = :chklstId AND t.chklstSite='A'")
				    			.setEntity("chklstId", tc);
				    			
						storeList = (ArrayList<TblChklstStore>) query.list();
					for(TblChklstStore s: storeList){
						if(areaId==s.getTblStore().getTblArea().getAreaId()){
							SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
							
                                        double percentage=0;
                                        Query queryTotal=session.createQuery("FROM TblChklstStoreDetail d "+
                                                 " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore ")
                                                .setEntity("TblChklst", tc)
                                                .setEntity("TblStore", s.getTblStore());
                                        ArrayList<TblChklstStoreDetail> storeDetList=(ArrayList<TblChklstStoreDetail>) queryTotal.list();

                                        Query queryYes=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                 " INNER JOIN FETCH d.tblChklstDetail  WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='Y' ").setEntity("TblChklst",tc).setEntity("TblStore", s.getTblStore());
                                        ArrayList<TblChklstStoreDetail> areaYesList=(ArrayList<TblChklstStoreDetail>) queryYes.list();

                                        Query queryNo=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='A' ").setEntity("TblChklst",tc).setEntity("TblStore", s.getTblStore());
                                        ArrayList<TblChklstStoreDetail> areaNoList=(ArrayList<TblChklstStoreDetail>) queryNo.list();
                                        NumberFormat formatter = new DecimalFormat("#0.00");


                                        double total1=0,yes1=0,no1=0;
                                        double temval;
                                        for(TblChklstStoreDetail dt: storeDetList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            total1 = total1 + temval;
                                         }
                                        for(TblChklstStoreDetail dt: areaYesList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            yes1 = yes1 + temval;
                                         }
                                        for(TblChklstStoreDetail dt: areaNoList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            no1 = no1 + temval;
                                         }               
                                        percentage=(yes1/(total1-no1))*100;
                                                        
							TblChklstRes r=new TblChklstRes();
							r.setUpdateDate(sformat.format(s.getUpdatedDate()));
							if(Double.isNaN(percentage)){
								r.setPercentage("N/A");
								r.setGrade("N/A");
							}else{
								if(percentage>=90){
									r.setGrade("Excellent");
								}else if(percentage>=80){
									r.setGrade("Good");	
								}else if(percentage>=70){
									r.setGrade("Satisfactory");	
								}else if(percentage>=60){
									r.setGrade("Need Improvement");
								}else if(percentage<60){
									r.setGrade("Unacceptable");
								}
								r.setPercentage(formatter.format(percentage)+"%");
								
							}
							
							
							r.setChklstSite(s.getChklstSite());
							r.setChklstStatus(s.getChklstStatus());
							r.setChklstStoreId(s.getChklstStoreId());
							r.setChklstAreaId(chklstAreaId);
							r.setChklstId(chklstId);
							//r.setRecipient(s.getTblStore().getStoreCode()+" - " +s.getTblStore().getStoreName());
							r.setRecipient(s.getTblStore().getStoreCode()+" - " +s.getTblStore().getStoreLocation());

							r.setStoreId(s.getTblStore().getStoreId());
							r.setAreaName(s.getTblStore().getTblArea().getAreaName());
							
							if("P".equals(s.getChklstStatus())==false){
								SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a"); 
								r.setResponseDate(sdf.format(s.getUpdatedDate()));
							}else
								r.setResponseDate("");
							
							res.add(r);
						}
					}
					
					} catch (Exception e) {
						transaction.rollback();
						e.printStackTrace();
					} 
					HibernateUtil.closeSession();
					return  res;
				}
				
				
				
				// Store Details
				@SuppressWarnings("unchecked")
		public ArrayList<TblChklstRes> getAreaStore(int chklstId, int areaId,int chklstAreaId){
					ArrayList<TblChklstRes> res=new ArrayList<TblChklstRes>();
					ArrayList<TblChklstStore> storeList=new ArrayList<TblChklstStore>();
					Transaction transaction = null;
					Session session = HibernateUtil.currentSession();
					try {
						transaction = session.beginTransaction();
						TblChklst tc = (TblChklst)session.get(TblChklst.class, chklstId);
					
					
				//		Query  query =  session.createQuery("FROM  TblChklstStore t INNER JOIN FETCH t.tblStore WHERE t.tblChklst = :chklstId AND (t.chklstSite='A' OR t.chklstSite='S')")
						Query  query =  session.createQuery("FROM  TblChklstStore t INNER JOIN FETCH t.tblStore WHERE t.tblChklst = :chklstId AND t.chklstSite='S'")
				    			.setEntity("chklstId", tc);
				    			
						storeList = (ArrayList<TblChklstStore>) query.list();
					for(TblChklstStore s: storeList){
						if(areaId==s.getTblStore().getTblArea().getAreaId()){
							SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
							
                                        double percentage=0;
                                        Query queryTotal=session.createQuery("FROM TblChklstStoreDetail d "+
                                                 " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore ")
                                                .setEntity("TblChklst", tc)
                                                .setEntity("TblStore", s.getTblStore());
                                        ArrayList<TblChklstStoreDetail> storeDetList=(ArrayList<TblChklstStoreDetail>) queryTotal.list();

                                        Query queryYes=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                 " INNER JOIN FETCH d.tblChklstDetail  WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='Y' ").setEntity("TblChklst",tc).setEntity("TblStore", s.getTblStore());
                                        ArrayList<TblChklstStoreDetail> areaYesList=(ArrayList<TblChklstStoreDetail>) queryYes.list();

                                        Query queryNo=session.createQuery("FROM TblChklstStoreDetail d  "+
                                                " INNER JOIN FETCH d.tblChklstDetail WHERE d.tblChklst = :TblChklst AND d.tblStore=:TblStore AND d.chklstResponseStatus='A' ").setEntity("TblChklst",tc).setEntity("TblStore", s.getTblStore());
                                        ArrayList<TblChklstStoreDetail> areaNoList=(ArrayList<TblChklstStoreDetail>) queryNo.list();
                                        NumberFormat formatter = new DecimalFormat("#0.00");


                                        double total1=0,yes1=0,no1=0;
                                        double temval;
                                        for(TblChklstStoreDetail dt: storeDetList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            total1 = total1 + temval;
                                         }
                                        for(TblChklstStoreDetail dt: areaYesList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            yes1 = yes1 + temval;
                                         }
                                        for(TblChklstStoreDetail dt: areaNoList){
                                            String text = dt.getTblChklstDetail().getChklstValue();
                                            String[] array = text.split("\\|",-1);
                                            if (text.substring(text.length()-1).equals("|")){
                                                temval=1;
                                            }else{
                                                temval = Double.parseDouble(array[array.length-1]); 
                                            }
                                            no1 = no1 + temval;
                                         }               
                                        percentage=(yes1/(total1-no1))*100;
						
							TblChklstRes r=new TblChklstRes();
							r.setUpdateDate(sformat.format(s.getUpdatedDate()));
							if(Double.isNaN(percentage)){
								r.setPercentage("N/A");
								r.setGrade("N/A");
							}else{
								if(percentage>=90){
									r.setGrade("Excellent");
								}else if(percentage>=80){
									r.setGrade("Good");	
								}else if(percentage>=70){
									r.setGrade("Satisfactory");	
								}else if(percentage>=60){
									r.setGrade("Need Improvement");
								}else if(percentage<60){
									r.setGrade("Unacceptable");
								}
								r.setPercentage(formatter.format(percentage)+"%");
								
							}
							
							
							r.setChklstSite(s.getChklstSite());
							r.setChklstStatus(s.getChklstStatus());
							r.setChklstStoreId(s.getChklstStoreId());
							r.setChklstAreaId(chklstAreaId);
							r.setChklstId(chklstId);
							r.setRecipient(s.getTblStore().getStoreCode()+" - " +s.getTblStore().getStoreName());
							r.setStoreId(s.getTblStore().getStoreId());
							r.setAreaName(s.getTblStore().getTblArea().getAreaName());
							res.add(r);
						}
					}
					
					} catch (Exception e) {
						transaction.rollback();
						e.printStackTrace();
					} 
					HibernateUtil.closeSession();
					return  res;
				}
				
				
				
				@SuppressWarnings("unchecked")
				public ArrayList<TblChklstRes> getAreaPendingList(int areaId, int groupId)
				{
					Session session = HibernateUtil.currentSession();
					ArrayList<TblChklstArea> resList = new ArrayList<TblChklstArea>();
					ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
					TblArea ts = (TblArea)session.get(TblArea.class, areaId);
					TblChklstGroup f=(TblChklstGroup)session.get(TblChklstGroup.class, groupId);
					Query  queryTopic =  session.createQuery("FROM TblChklstTopic AS f WHERE f.tblChklstGroup = :group ORDER BY topicName").setEntity("group", f);
					ArrayList<TblChklstTopic> topicList = (ArrayList<TblChklstTopic>) queryTopic.list();
					
				
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					try {
						for(TblChklstTopic tp:topicList){
						Query  query =  session.createQuery("FROM  TblChklstArea t WHERE t.tblArea = :areaId AND (t.chklstStatus='P' or t.chklstStatus='I' ) and t.tblChklst.chklstEndDate >= :dt and t.tblChklst.tblChklstTopic = :topicId ")
								 		.setEntity("areaId", ts)
								 		.setDate("dt", new Date())
								 		.setEntity("topicId", tp);
						resList = (ArrayList<TblChklstArea>) query.list();
						
						for(TblChklstArea t: resList){
							 
							TblChklstRes res=new TblChklstRes();
							res.setChklstSubject(t.getTblChklst().getChklstSubject());
							res.setChklstId(t.getTblChklst().getChklstId());
							res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
							res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
							res.setChklstRemark(t.getChklstRemark());
							res.setChklstAreaId(t.getChklstAreaId());
							retval.add(res);
						}}
					}catch (HibernateException e) {
						e.printStackTrace();
					}
					HibernateUtil.closeSession();
					return retval;
				}
				
				
				@SuppressWarnings("unchecked")
				public ArrayList<TblChklstRes> getStorePendingList(int storeId, int groupId)
				{
					Session session = HibernateUtil.currentSession();
					ArrayList<TblChklstStore> resList = new ArrayList<TblChklstStore>();
					ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
					TblStore ts = (TblStore)session.get(TblStore.class, storeId);
					TblChklstGroup f=(TblChklstGroup)session.get(TblChklstGroup.class, groupId);
					Query  queryTopic =  session.createQuery("FROM TblChklstTopic AS f WHERE f.tblChklstGroup = :group ORDER BY topicName").setEntity("group", f);
					ArrayList<TblChklstTopic> topicList = (ArrayList<TblChklstTopic>) queryTopic.list();
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					try {
						for(TblChklstTopic tp:topicList){
						Query  query =  session.createQuery("FROM  TblChklstStore t WHERE t.chklstSite='S' AND t.tblStore = :storeId AND (t.chklstStatus='P' or t.chklstStatus='I') and t.tblChklst.chklstEndDate >= :dt and t.tblChklst.tblChklstTopic = :topicId ")
								 		.setEntity("storeId", ts)
								 		.setDate("dt", new Date())
								 		.setEntity("topicId", tp);
						resList = (ArrayList<TblChklstStore>) query.list();
						
						for(TblChklstStore t: resList){
							 
							TblChklstRes res=new TblChklstRes();
							res.setChklstSubject(t.getTblChklst().getChklstSubject());
							res.setChklstId(t.getTblChklst().getChklstId());
							res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
							res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
							res.setChklstRemark(t.getChklstRemark());
							res.setChklstStoreId(t.getChklstStoreId());
							retval.add(res);
						}}
					}catch (HibernateException e) {
						e.printStackTrace();
					}
					HibernateUtil.closeSession();
					return retval;
				}
				
				
				
				@SuppressWarnings("unchecked")
				public ArrayList<TblChklstRes> getStoreNotification(int storeId, int groupId)
				{
					Session session = HibernateUtil.currentSession();
					ArrayList<TblChklstStore> resList = new ArrayList<TblChklstStore>();
					ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
					TblStore ts = (TblStore)session.get(TblStore.class, storeId);
					TblChklstGroup f=(TblChklstGroup)session.get(TblChklstGroup.class, groupId);
					Query  queryTopic =  session.createQuery("FROM TblChklstTopic AS f WHERE f.tblChklstGroup = :group ORDER BY topicName").setEntity("group", f);
					ArrayList<TblChklstTopic> topicList = (ArrayList<TblChklstTopic>) queryTopic.list();
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					try {
						for(TblChklstTopic tp:topicList){
						Query  query =  session.createQuery("FROM  TblChklstStore t WHERE  t.tblStore = :storeId AND (t.chklstStatus='P' or t.chklstStatus='I') and t.tblChklst.chklstEndDate >= :dt and t.tblChklst.tblChklstTopic = :topicId ")
								 		.setEntity("storeId", ts)
								 		.setDate("dt", new Date())
								 		.setEntity("topicId", tp);
						resList = (ArrayList<TblChklstStore>) query.list();
						
						for(TblChklstStore t: resList){
							 
							TblChklstRes res=new TblChklstRes();
							res.setChklstSubject(t.getTblChklst().getChklstSubject());
							res.setChklstId(t.getTblChklst().getChklstId());
							res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
							res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
							res.setChklstRemark(t.getChklstRemark());
							res.setChklstStoreId(t.getChklstStoreId());
							retval.add(res);
						}}
					}catch (HibernateException e) {
						e.printStackTrace();
					}
					HibernateUtil.closeSession();
					return retval;
				}
                                
                                
				@SuppressWarnings("unchecked")
				public ArrayList<TblChklstRes> getStoreHqNotification(int groupId)
				{
					Session session = HibernateUtil.currentSession();
					ArrayList<TblChklstStore> resList = new ArrayList<TblChklstStore>();
					ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
					
					TblChklstGroup f=(TblChklstGroup)session.get(TblChklstGroup.class, groupId);
					Query  queryTopic =  session.createQuery("FROM TblChklstTopic AS f WHERE f.tblChklstGroup = :group ORDER BY topicName").setEntity("group", f);
					ArrayList<TblChklstTopic> topicList = (ArrayList<TblChklstTopic>) queryTopic.list();
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					try {
						for(TblChklstTopic tp:topicList){
						Query  query =  session.createQuery("FROM  TblChklstStore t WHERE  (t.chklstStatus='P' or t.chklstStatus='I') and t.tblChklst.chklstEndDate >= :dt and t.tblChklst.tblChklstTopic = :topicId ")
								 		.setDate("dt", new Date())
								 		.setEntity("topicId", tp);
						resList = (ArrayList<TblChklstStore>) query.list();
						
						for(TblChklstStore t: resList){
							 
							TblChklstRes res=new TblChklstRes();
							res.setChklstSubject(t.getTblChklst().getChklstSubject());
							res.setChklstId(t.getTblChklst().getChklstId());
							res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
							res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
							res.setChklstRemark(t.getChklstRemark());
							res.setChklstStoreId(t.getChklstStoreId());
							retval.add(res);
						}}
					}catch (HibernateException e) {
						e.printStackTrace();
					}
					HibernateUtil.closeSession();
					return retval;
				}
                                
                               @SuppressWarnings("unchecked")
				public ArrayList<TblChklstRes> getAreaHqNotification(int groupId)
				{
					Session session = HibernateUtil.currentSession();
					ArrayList<TblChklstArea> resList = new ArrayList<TblChklstArea>();
					ArrayList<TblChklstRes> retval=new ArrayList<TblChklstRes>();
					TblChklstGroup f=(TblChklstGroup)session.get(TblChklstGroup.class, groupId);
					Query  queryTopic =  session.createQuery("FROM TblChklstTopic AS f WHERE f.tblChklstGroup = :group ORDER BY topicName").setEntity("group", f);
					ArrayList<TblChklstTopic> topicList = (ArrayList<TblChklstTopic>) queryTopic.list();
					
				
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					try {
						for(TblChklstTopic tp:topicList){
						Query  query =  session.createQuery("FROM  TblChklstArea t WHERE (t.chklstStatus='P' or t.chklstStatus='I' ) and t.tblChklst.chklstEndDate >= :dt and t.tblChklst.tblChklstTopic = :topicId ")
								 		.setDate("dt", new Date())
								 		.setEntity("topicId", tp);
						resList = (ArrayList<TblChklstArea>) query.list();
						
						for(TblChklstArea t: resList){
							 
							TblChklstRes res=new TblChklstRes();
							res.setChklstSubject(t.getTblChklst().getChklstSubject());
							res.setChklstId(t.getTblChklst().getChklstId());
							res.setChklstStartDate(formatter.format(t.getTblChklst().getChklstStartDate()));
							res.setChklstEndDate(formatter.format(t.getTblChklst().getChklstEndDate()));
							res.setChklstRemark(t.getChklstRemark());
							res.setChklstAreaId(t.getChklstAreaId());
							retval.add(res);
						}}
					}catch (HibernateException e) {
						e.printStackTrace();
					}
					HibernateUtil.closeSession();
					return retval;
				}
				
}
