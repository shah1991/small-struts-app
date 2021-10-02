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
import net.slide.pojo.TblArea;
import net.slide.pojo.TblChklst;
import net.slide.pojo.TblChklstStore;
import net.slide.pojo.TblChklstStoreDetail;
import net.slide.pojo.TblPost;
import net.slide.pojo.TblPostUpload;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblTask;
import net.slide.pojo.TblTaskArea;
import net.slide.pojo.TblTaskPortfolio;
import net.slide.pojo.TblTaskResponse;
import net.slide.pojo.TblTaskResponseArea;
import net.slide.pojo.TblTaskStore;
import net.slide.pojo.TblTaskUpload;
import net.slide.pojores.TblChklstRes;
import net.slide.pojores.TblMasterRes;
import net.slide.pojores.TblTaskRes;
import net.slide.pojores.TblUploadRes;
import net.slide.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TaskPostDao extends BaseDao {
	@SuppressWarnings("unchecked")
	public ArrayList<TblTask> getPostList(String searchTxt)
	{
		ArrayList<TblTask> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblTaskPost AS f ORDER BY postSubject");
			if(searchTxt != null && searchTxt.length() > 0){
				searchTxt = searchTxt.replace(' ', '%');
				query =  session.createQuery("FROM TblTaskPost AS f WHERE postSubject LIKE :searchTxt ORDER BY created_date").setText("searchTxt", "%"+searchTxt+"%");
			}
			resList = (ArrayList<TblTask>) query.list();
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getPostYears(int portfolioId,int taskOwnerRole, int taskOwnerSite)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			StringBuffer sb=new StringBuffer();
			sb.append("SELECT TO_CHAR(updated_date,'YYYY') AS yyyy, COUNT(*) AS cnt FROM  tbl_task WHERE portfolio_id =").append(portfolioId).append(" and task_owner_role=").append(taskOwnerRole).append(" and task_owner_site=").append(taskOwnerSite).append(" GROUP BY yyyy ORDER BY yyyy");
			Query  query =  session.createSQLQuery(sb.toString());
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName("Browse Tasks "+(String)val[0]);
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
	public ArrayList<TblMasterRes> getPostMonths(int portfolioId,int taskOwnerRole, int taskOwnerSite, String postYear)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT  TO_CHAR(updated_date,'Month') AS mmm, TO_CHAR(updated_date,'MM') AS mm, COUNT(*) AS cnt " +
												"FROM  tbl_task " +
												"WHERE TO_CHAR(updated_date,'YYYY') = :yyyy " +
												"AND task_owner_role=:taskOwnerRole "+
												"AND task_owner_site=:taskOwnerSite "+
												"AND portfolio_id = :portfolioId " +
												"GROUP BY mmm, mm " +
												"ORDER BY mmm")
												.setString("yyyy", postYear)
												.setInteger("taskOwnerRole", taskOwnerRole)
												.setInteger("taskOwnerSite", taskOwnerSite)
												.setInteger("portfolioId", portfolioId);
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
	public ArrayList<TblMasterRes> getPostWeek(int portfolioId,int taskOwnerRole, int taskOwnerSite, String postYear, String postMonth)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT ((cast(TO_CHAR(updated_date,'DD') as integer))/ 7+1) AS ww, COUNT(*) AS cnt " +
													"FROM  tbl_task " +
													"WHERE TO_CHAR(updated_date,'YYYY') = :yyyy and TO_CHAR(updated_date,'MM') = :mm " +
													"AND task_owner_role=:taskOwnerRole "+
													"AND task_owner_site=:taskOwnerSite "+
													"AND portfolio_id = :portfolioId " +
													"GROUP BY ww " +
													"ORDER BY ww")
													.setString("yyyy", postYear)
													.setString("mm", postMonth)
													.setInteger("taskOwnerRole", taskOwnerRole)
													.setInteger("taskOwnerSite", taskOwnerSite)
													.setInteger("portfolioId", portfolioId);
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
	public ArrayList<TblTaskUpload> getPostUpload(TblTask t){
		ArrayList<TblTaskUpload> alist=null;
		Session session = HibernateUtil.currentSession();
		try{
			Query query = session.createQuery("FROM TblTaskUpload AS f WHERE f.tblTask = :tblTask");
			query.setEntity("tblTask", t);
			alist=(ArrayList<TblTaskUpload>)query.list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return alist;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblTask> getPosts(int portfolioId,int taskOwnerRole, int taskOwnerSite, String postYear, String postMonth, Integer postWeek)
	{
		ArrayList<TblTask> retval = new ArrayList<TblTask>();
		Session session = HibernateUtil.currentSession();
		try {
			
			TblTaskPortfolio t = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, portfolioId);
			
			Query  query =  session.createQuery("FROM  TblTask f " +
													"WHERE TO_CHAR(updatedDate,'YYYYMM') = :yyyymm AND " +
													"(((cast(TO_CHAR(updatedDate,'DD') as integer))/ 7+1)) = :ww " +
													"AND task_owner_role=:taskOwnerRole "+
													"AND task_owner_site=:taskOwnerSite "+
													"AND f.tblTaskPortfolio = :portfolioId " +
													"ORDER BY updatedDate")
													.setString("yyyymm", postYear+postMonth)
													.setInteger("ww", postWeek)
													.setInteger("taskOwnerRole", taskOwnerRole)
													.setInteger("taskOwnerSite", taskOwnerSite)
													.setEntity("portfolioId", t);
			retval = (ArrayList<TblTask>) query.list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	//Hq user
	

	
	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskRes> getTaskAll(int portfolioId,int taskOwnerRole, int taskOwnerSite)
	{	
		ArrayList<TblTaskRes> retval = null;
		Session session = HibernateUtil.currentSession();
		ArrayList<TblTask> resList = null;
		try {
			TblTaskPortfolio tp = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, portfolioId);
			Query  query =  session.createQuery("FROM  TblTask ta where ta.taskOwnerRole=:taskOwnerRole and ta.taskOwnerSite=:taskOwnerSite and ta.tblTaskPortfolio = :portfolioId and  ta.taskEndDate>=current_date ORDER BY ta.updatedDate DESC")
						    .setInteger("taskOwnerRole", taskOwnerRole)
						    .setInteger("taskOwnerSite", taskOwnerSite)
							.setEntity("portfolioId", tp);
			resList = (ArrayList<TblTask>) query.list();
		    retval = new ArrayList<TblTaskRes>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		for(TblTask t: resList){

			TblTaskRes res=new TblTaskRes();
			res.setTaskSubject(t.getTaskSubject());
			res.setTaskId(t.getTaskId());
			res.setTaskType(t.getTaskType());
			res.setTaskStartDate(formatter.format(t.getTaskStartDate()));
			res.setTaskEndDate(formatter.format(t.getTaskEndDate()));
			Query areaQuery =  session.createSQLQuery("SELECT  COUNT(task_id) from tbl_task_area where task_status='R' and task_id=:task_id")
					           .setInteger("task_id", t.getTaskId());
			List listArea= areaQuery.list();
			Query storeQuery =  session.createSQLQuery("SELECT  COUNT(task_id) from tbl_task_store where task_status='R' and task_id=:task_id")
			           .setInteger("task_id", t.getTaskId());
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
			res.setTotalAreaCount(String.valueOf(t.getTblTaskAreas().size()));
		   	retval.add(res);
		}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
		
	//Hq user completed task
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskRes> getCompleteTask(int portfolioId,int taskOwnerRole, int taskOwnerSite)
	{	
		ArrayList<TblTaskRes> retval = null;
		Session session = HibernateUtil.currentSession();
		ArrayList<TblTask> resList = null;
		try {
			TblTaskPortfolio tp = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, portfolioId);
			Query  query =  session.createQuery("FROM  TblTask ta where ta.taskOwnerRole=:taskOwnerRole and ta.taskOwnerSite=:taskOwnerSite and ta.tblTaskPortfolio = :portfolioId ORDER BY ta.updatedDate DESC")
							.setInteger("taskOwnerRole", taskOwnerRole)
							.setInteger("taskOwnerSite", taskOwnerSite)
							.setEntity("portfolioId", tp);
			resList = (ArrayList<TblTask>) query.list();
		    retval = new ArrayList<TblTaskRes>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		for(TblTask t: resList){

			TblTaskRes res=new TblTaskRes();
			res.setTaskSubject(t.getTaskSubject());
			res.setTaskId(t.getTaskId());
			res.setTaskType(t.getTaskType());
			res.setTaskStartDate(formatter.format(t.getTaskStartDate()));
			res.setTaskEndDate(formatter.format(t.getTaskEndDate()));
			Query areaQuery =  session.createSQLQuery("SELECT  COUNT(task_id) from tbl_task_area where task_status='R' and task_id=:task_id")
					           .setInteger("task_id", t.getTaskId());
			List listArea= areaQuery.list();
			Query storeQuery =  session.createSQLQuery("SELECT  COUNT(task_id) from tbl_task_store where task_status='R' and task_id=:task_id")
			           .setInteger("task_id", t.getTaskId());
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
			res.setTotalAreaCount(String.valueOf(t.getTblTaskAreas().size()));
		   	retval.add(res);
		}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
		
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskRes> getFailedTask(int portfolioId,int taskOwnerRole, int taskOwnerSite)
	{	
		ArrayList<TblTaskRes> retval = null;
		Session session = HibernateUtil.currentSession();
		ArrayList<TblTask> resList = null;
		try {
			TblTaskPortfolio tp = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, portfolioId);
			Query  query =  session.createQuery("FROM  TblTask ta where ta.taskOwnerRole=:taskOwnerRole and ta.taskOwnerSite=:taskOwnerSite and ta.tblTaskPortfolio = :portfolioId and  ta.taskEndDate<current_date ORDER BY ta.updatedDate DESC")
							.setInteger("taskOwnerRole", taskOwnerRole)
							.setInteger("taskOwnerSite", taskOwnerSite)
							.setEntity("portfolioId", tp);
			resList = (ArrayList<TblTask>) query.list();
		    retval = new ArrayList<TblTaskRes>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		for(TblTask t: resList){

			TblTaskRes res=new TblTaskRes();
			res.setTaskSubject(t.getTaskSubject());
			res.setTaskId(t.getTaskId());
			res.setTaskType(t.getTaskType());
			res.setTaskStartDate(formatter.format(t.getTaskStartDate()));
			res.setTaskEndDate(formatter.format(t.getTaskEndDate()));
			
		   	retval.add(res);
		}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskRes> getStoreManageTask(int storeId,int portfolioId)
	{
		Session session = HibernateUtil.currentSession();
		ArrayList<TblTaskStore> resList = new ArrayList<TblTaskStore>();
		ArrayList<TblTaskRes> retval=new ArrayList<TblTaskRes>();
		TblStore ts = (TblStore)session.get(TblStore.class, storeId);
		
		try {
			
			Query  query =  session.createQuery("FROM  TblTaskStore t WHERE t.tblStore = :storeId")
					 		.setEntity("storeId", ts);
			resList = (ArrayList<TblTaskStore>) query.list();
			
			for(TblTaskStore t: resList){
				TblTaskRes res=new TblTaskRes();
				t.getTblTask();
				
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	

	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskRes> getStorePendingTask(int storeId, int portfolioId)
	{
		Session session = HibernateUtil.currentSession();
		ArrayList<TblTaskStore> resList = new ArrayList<TblTaskStore>();
		ArrayList<TblTaskRes> retval=new ArrayList<TblTaskRes>();
		TblStore ts = (TblStore)session.get(TblStore.class, storeId);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		try {
			TblTaskPortfolio tp = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, portfolioId);
			Query  query =  session.createQuery("FROM  TblTaskStore t WHERE t.tblStore = :storeId AND t.taskStatus='P' and t.tblTask.taskEndDate >= :enddate and t.tblTask.taskStartDate <= :startdate  and t.tblTask.tblTaskPortfolio = :portfolioId ")
					 		.setEntity("storeId", ts)
					 		.setDate("enddate", new Date())
					 		.setDate("startdate", new Date())
					 		.setEntity("portfolioId", tp);
			resList = (ArrayList<TblTaskStore>) query.list();
			
			for(TblTaskStore t: resList){
				 
				TblTaskRes res=new TblTaskRes();
				res.setTaskSubject(t.getTblTask().getTaskSubject());
				res.setTaskId(t.getTblTask().getTaskId());
				res.setTaskStartDate(formatter.format(t.getTblTask().getTaskStartDate()));
				res.setTaskEndDate(formatter.format(t.getTblTask().getTaskEndDate()));
				res.setTaskRemark(t.getTaskRemark());
				res.setTaskStoreId(t.getTaskStoreId());
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskRes> getStoreCompletedTask(int storeId, int portfolioId)
	{
		Session session = HibernateUtil.currentSession();
		ArrayList<TblTaskStore> resList = new ArrayList<TblTaskStore>();
		ArrayList<TblTaskRes> retval=new ArrayList<TblTaskRes>();
		TblStore ts = (TblStore)session.get(TblStore.class, storeId);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		try {
			TblTaskPortfolio tp = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, portfolioId);
			Query  query =  session.createQuery("FROM  TblTaskStore t WHERE t.tblStore = :storeId AND t.taskStatus='R' and t.tblTask.tblTaskPortfolio = :portfolioId ")
			 		.setEntity("storeId", ts)
					.setEntity("portfolioId", tp);
			resList = (ArrayList<TblTaskStore>) query.list();
			
			for(TblTaskStore t: resList){
				TblTaskRes res=new TblTaskRes();
				res.setTaskSubject(t.getTblTask().getTaskSubject());
				res.setTaskId(t.getTblTask().getTaskId());
				res.setTaskStartDate(formatter.format(t.getTblTask().getTaskStartDate()));
				res.setTaskEndDate(formatter.format(t.getTblTask().getTaskEndDate()));
				res.setTaskRemark(t.getTaskRemark());
				res.setTaskStoreId(t.getTaskStoreId());
				if(t.getTaskResponse().equals("A")){
					res.setTaskResponseType("N/A");
				}else if(t.getTaskResponse().equals("N")){
					res.setTaskResponseType("No");	
				}else{
					res.setTaskResponseType("Yes");
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
	public ArrayList<TblTaskRes> getStoreFailedTask(int storeId, int portfolioId)
	{
		Session session = HibernateUtil.currentSession();
		ArrayList<TblTaskStore> resList = new ArrayList<TblTaskStore>();
		ArrayList<TblTaskRes> retval=new ArrayList<TblTaskRes>();
		TblStore ts = (TblStore)session.get(TblStore.class, storeId);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		try {
			TblTaskPortfolio tp = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, portfolioId);
			Query  query =  session.createQuery("FROM  TblTaskStore t WHERE t.tblStore = :storeId AND t.taskStatus='P' and t.tblTask.taskEndDate< :dt and t.tblTask.tblTaskPortfolio = :portfolioId ")
					 		.setEntity("storeId", ts)
					 		.setDate("dt", new Date())
					 		.setEntity("portfolioId", tp);
			resList = (ArrayList<TblTaskStore>) query.list();
			
			for(TblTaskStore t: resList){
				 
				TblTaskRes res=new TblTaskRes();
				res.setTaskSubject(t.getTblTask().getTaskSubject());
				res.setTaskId(t.getTblTask().getTaskId());
				res.setTaskStartDate(formatter.format(t.getTblTask().getTaskStartDate()));
				res.setTaskEndDate(formatter.format(t.getTblTask().getTaskEndDate()));
				res.setTaskRemark(t.getTaskRemark());
				res.setTaskStoreId(t.getTaskStoreId());
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	

	
	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskRes> getAreaPendingTask(int areaId, int portfolioId)
	{
		Session session = HibernateUtil.currentSession();
		ArrayList<TblTaskArea> resList = new ArrayList<TblTaskArea>();
		ArrayList<TblTaskRes> retval=new ArrayList<TblTaskRes>();
		TblArea ts = (TblArea)session.get(TblArea.class, areaId);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		try {
			
			
			TblTaskPortfolio tp = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, portfolioId);
			Query  query =  session.createQuery("FROM  TblTaskArea t WHERE t.tblArea = :areaId AND t.taskStatus='P'  and t.tblTask.taskEndDate >= :enddate and t.tblTask.taskStartDate <= :startdate  and t.tblTask.tblTaskPortfolio = :portfolioId ")
					 		.setEntity("areaId", ts)
					 		.setDate("enddate", new Date())
					 		.setDate("startdate", new Date())
			 		 		.setEntity("portfolioId", tp);
			resList = (ArrayList<TblTaskArea>) query.list();
			
			for(TblTaskArea t: resList){
				 
				TblTaskRes res=new TblTaskRes();
				res.setTaskSubject(t.getTblTask().getTaskSubject());
				res.setTaskId(t.getTblTask().getTaskId());
				res.setTaskStartDate(formatter.format(t.getTblTask().getTaskStartDate()));
				res.setTaskEndDate(formatter.format(t.getTblTask().getTaskEndDate()));
				res.setTaskRemark(t.getTaskRemark());
				res.setTaskAreaId(t.getTaskAreaId());
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	
	

	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskRes> getAreaFailedTask(int areaId, int portfolioId)
	{
		Session session = HibernateUtil.currentSession();
		ArrayList<TblTaskArea> resList = new ArrayList<TblTaskArea>();
		ArrayList<TblTaskRes> retval=new ArrayList<TblTaskRes>();
		TblArea ts = (TblArea)session.get(TblArea.class, areaId);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		try {
			TblTaskPortfolio tp = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, portfolioId);
			Query  query =  session.createQuery("FROM  TblTaskArea t WHERE t.tblArea = :areaId AND t.taskStatus='P' and t.tblTask.taskEndDate< :dt and t.tblTask.tblTaskPortfolio = :portfolioId ")
					 		.setEntity("areaId", ts)
					 		.setDate("dt", new Date())
					 		.setEntity("portfolioId", tp);
			resList = (ArrayList<TblTaskArea>) query.list();
			
			for(TblTaskArea t: resList){
				 
				TblTaskRes res=new TblTaskRes();
				res.setTaskSubject(t.getTblTask().getTaskSubject());
				res.setTaskId(t.getTblTask().getTaskId());
				res.setTaskStartDate(formatter.format(t.getTblTask().getTaskStartDate()));
				res.setTaskEndDate(formatter.format(t.getTblTask().getTaskEndDate()));
				res.setTaskRemark(t.getTaskRemark());
				res.setTaskAreaId(t.getTaskAreaId());
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	

	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskRes> getAreaCompletedTask(int areaId, int portfolioId)
	{
		Session session = HibernateUtil.currentSession();
		ArrayList<TblTaskArea> resList = new ArrayList<TblTaskArea>();
		ArrayList<TblTaskRes> retval=new ArrayList<TblTaskRes>();
		TblArea ts = (TblArea)session.get(TblArea.class, areaId);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		try {
			TblTaskPortfolio tp = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, portfolioId);
			Query  query =  session.createQuery("FROM  TblTaskArea t WHERE t.tblArea = :areaId AND t.taskStatus='R' and t.tblTask.tblTaskPortfolio = :portfolioId ")
					 		.setEntity("areaId", ts)
					 		.setEntity("portfolioId", tp);
			resList = (ArrayList<TblTaskArea>) query.list();
			
			for(TblTaskArea t: resList){
				 
				TblTaskRes res=new TblTaskRes();
				res.setTaskSubject(t.getTblTask().getTaskSubject());
				res.setTaskId(t.getTblTask().getTaskId());
				res.setTaskStartDate(formatter.format(t.getTblTask().getTaskStartDate()));
				res.setTaskEndDate(formatter.format(t.getTblTask().getTaskEndDate()));
				res.setTaskRemark(t.getTaskRemark());
				res.setTaskAreaId(t.getTaskAreaId());
				if(t.getTaskResponse().equals("A")){
					res.setTaskResponseType("N/A");
				}else if(t.getTaskResponse().equals("N")){
					res.setTaskResponseType("No");	
				}else{
					res.setTaskResponseType("Yes");
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
	public ArrayList<TblTaskRes> getAreaManageTask(int portfolioId,int taskOwnerRole, int taskOwnerSite)
	{
		ArrayList<TblTaskRes> retval = null;
		Session session = HibernateUtil.currentSession();
		ArrayList<TblTask> resList = null;
		try {
			TblTaskPortfolio tp = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, portfolioId);
			Query  query =  session.createQuery("FROM  TblTask ta where ta.taskOwnerRole=:taskOwnerRole and ta.taskOwnerSite=:taskOwnerSite and ta.tblTaskPortfolio = :portfolioId and  ta.taskEndDate>=current_date ORDER BY ta.updatedDate DESC")
						    .setInteger("taskOwnerRole", taskOwnerRole)
						    .setInteger("taskOwnerSite", taskOwnerSite)
							.setEntity("portfolioId", tp);
			resList = (ArrayList<TblTask>) query.list();
		    retval = new ArrayList<TblTaskRes>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		for(TblTask t: resList){

			TblTaskRes res=new TblTaskRes();
			res.setTaskSubject(t.getTaskSubject());
			res.setTaskId(t.getTaskId());
			res.setTaskType(t.getTaskType());
			res.setTaskStartDate(formatter.format(t.getTaskStartDate()));
			res.setTaskEndDate(formatter.format(t.getTaskEndDate()));
			Query storeQuery =  session.createSQLQuery("SELECT  COUNT(task_id) from tbl_task_store where task_status='R' and task_id=:task_id")
			           .setInteger("task_id", t.getTaskId());
			List listStore= storeQuery.list();
			
			if(listStore.get(0)!=null){
				if(listStore.get(0).toString().equals("0")){}else{
				res.setStoreCount(listStore.get(0).toString());
				}
			}
			res.setTotalAreaCount(String.valueOf(t.getTblTaskAreas().size()));
		   	retval.add(res);
		}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	
	}
	
	
	/**
	 * Get View the Area task 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TblTask getAreaTask(int taskId,int taskAreaId){
		
		Set<TblTaskArea> resList=new HashSet<TblTaskArea>();
		Session session = HibernateUtil.currentSession();
		TblTask ta = (TblTask)session.get(TblTask.class, taskId);
		try{
			List<TblTaskArea> list = session.createQuery("FROM  TblTaskArea t WHERE t.taskAreaId=:taskAreaId AND t.tblTask = :taskId")
				    .setInteger("taskAreaId", taskAreaId)    
					.setEntity("taskId", ta).list();
			 resList = new HashSet<TblTaskArea>(list);
			 ta.setTblTaskAreas(resList);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return ta;
	}
	

	
	/**
	 * Get View the Store task 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TblTask getStoreTask(int taskId,int taskStoreId){
		
		Set<TblTaskStore> resList=new HashSet<TblTaskStore>();
		Session session = HibernateUtil.currentSession();
		TblTask ta = (TblTask)session.get(TblTask.class, taskId);
		try{
			List<TblTaskStore> list = session.createQuery("FROM  TblTaskStore t WHERE t.taskStoreId=:taskStoreId AND t.tblTask = :taskId")
				    .setInteger("taskStoreId", taskStoreId)    
					.setEntity("taskId", ta).list();
			resList = new HashSet<TblTaskStore>(list);
			ta.setTblTaskStores(resList);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return ta;
	}
	
	public TblTaskStore getTaskStore(int taskId,int taskStoreId){
		
		TblTaskStore resList=null;
		Session session = HibernateUtil.currentSession();
		TblTask ta = (TblTask)session.get(TblTask.class, taskId);
		try{
			 TblTaskStore tblTaskStore = (TblTaskStore)session.createQuery("FROM  TblTaskStore t WHERE t.taskStoreId=:taskStoreId AND t.tblTask = :taskId")
				    .setInteger("taskStoreId", taskStoreId)    
				    .setEntity("taskId", ta).uniqueResult();
			resList = tblTaskStore;
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}        
        
	public TblTaskArea getTaskArea(int taskId,int taskAreaId){
		
		TblTaskArea resList=null;
		Session session = HibernateUtil.currentSession();
		TblTask ta = (TblTask)session.get(TblTask.class, taskId);
		try{
			 TblTaskArea tblTaskArea = (TblTaskArea)session.createQuery("FROM  TblTaskArea t WHERE t.taskAreaId=:taskAreaId AND t.tblTask = :taskId")
				    .setInteger("taskAreaId", taskAreaId)    
				    .setEntity("taskId", ta).uniqueResult();
			resList = tblTaskArea;
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}        
        

	@SuppressWarnings("unchecked")
	public ArrayList<TblTask> getPostAll(int portfolioId)
	{
		ArrayList<TblTask> retval = new ArrayList<TblTask>();
		Session session = HibernateUtil.currentSession();
		try {
			TblTaskPortfolio tp = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, portfolioId);
			Query  query =  session.createQuery("FROM  TblTask t WHERE t.tblTaskPortfolio = :portfolioId ")
					        .setEntity("portfolioId", tp);
			retval = (ArrayList<TblTask>) query.list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	
	
	/**
	 * Get View the Area task 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskArea> getAreaTaskList(int taskId){
		
		ArrayList<TblTaskArea> resList=new ArrayList<TblTaskArea>();
		Session session = HibernateUtil.currentSession();
		TblTask ta = (TblTask)session.get(TblTask.class, taskId);
		try{
			resList=(ArrayList<TblTaskArea>) session.createQuery("FROM  TblTaskArea t WHERE t.tblTask = :taskId")
				     	.setEntity("taskId", ta).list();
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}
	

	
	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskStore> getStoreTaskList(int taskId){
		
		ArrayList<TblTaskStore> resList=new ArrayList<TblTaskStore>();
		Session session = HibernateUtil.currentSession();
		TblTask ta = (TblTask)session.get(TblTask.class, taskId);
		try{
			resList =(ArrayList<TblTaskStore>) session.createQuery("FROM  TblTaskStore t WHERE t.tblTask = :taskId")
				   
					.setEntity("taskId", ta).list();
			
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}
	

	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskRes> getAreaResponse(int taskId)
	{
		
		ArrayList<TblTaskArea> resList = new ArrayList<TblTaskArea>();
		ArrayList<TblTaskRes> retval=new ArrayList<TblTaskRes>();
		Session session = HibernateUtil.currentSession();
		try {
		
			TblTask ta = (TblTask)session.get(TblTask.class, taskId);
			ta.getTblTaskAreas().size();
			Query  query =  session.createQuery("FROM  TblTaskArea t WHERE t.taskStatus=:taskStatus AND t.tblTask = :taskId ")
					    .setString("taskStatus", "R")    
						.setEntity("taskId", ta);
						
			
			resList = (ArrayList<TblTaskArea>) query.list();
			SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
			for(TblTaskArea t: resList){
				TblTaskRes res=new TblTaskRes();
				res.setTaskSubject(ta.getTaskSubject());
				res.setTaskId(ta.getTaskId());
				res.setTaskType(ta.getTaskType());
				res.setTaskRemark(t.getTaskRemark());
				res.setTaskAreaId(t.getTaskAreaId());
				res.setRecipient(t.getTblArea().getAreaCd()+" - "+t.getTblArea().getAreaName());
				res.setAreaId(t.getTblArea().getAreaId());
				res.setUpdateDate(sformat.format(t.getUpdatedDate()));
				if(t.getTaskResponse().equals("A")){
					res.setTaskResponseType("N/A");
				}else if(t.getTaskResponse().equals("N")){
					res.setTaskResponseType("No");	
				}else{
					res.setTaskResponseType("Yes");
				}
				
				if("P".equals(t.getTaskStatus())==false)
				{
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
					res.setResponseDate(sdf.format(t.getUpdatedDate()));
				}
				else
					res.setResponseDate("");
				
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	

	

	@SuppressWarnings("unchecked")
	public ArrayList<TblTaskRes> getStoreResponse(int taskId)
	{
		ArrayList<TblTaskStore> resList = new ArrayList<TblTaskStore>();
		ArrayList<TblTaskRes> retval=new ArrayList<TblTaskRes>();
		Session session = HibernateUtil.currentSession();
		try {
			TblTask ta = (TblTask)session.get(TblTask.class, taskId);
			ta.getTblTaskAreas().size();
			Query  query =  session.createQuery("FROM  TblTaskStore t WHERE t.taskStatus=:taskStatus AND t.tblTask = :taskId ")
					    .setString("taskStatus", "R")    
						.setEntity("taskId", ta);
			resList = (ArrayList<TblTaskStore>) query.list();
			SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
			for(TblTaskStore t: resList){
				TblTaskRes res=new TblTaskRes();
				res.setTaskSubject(ta.getTaskSubject());
				res.setTaskId(ta.getTaskId());
				res.setTaskType(ta.getTaskType());
				res.setTaskRemark(t.getTaskRemark());
				//res.setRecipient(t.getTblStore().getStoreCode()+" - "+t.getTblStore().getStoreName());
				res.setRecipient(t.getTblStore().getStoreCode()+" - "+t.getTblStore().getStoreLocation()); /*Store address to be displayed*/
				res.setTaskStoreId(t.getTaskStoreId());
				res.setUpdateDate(sformat.format(t.getUpdatedDate()));
				if(t.getTaskResponse().equals("A")){
					res.setTaskResponseType("N/A");
				}else if(t.getTaskResponse().equals("N")){
					res.setTaskResponseType("No");	
				}else{
					res.setTaskResponseType("Yes");
				}
				
				if("P".equals(t.getTaskStatus())==false)
				{
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
					res.setResponseDate(sdf.format(t.getUpdatedDate()));
				}
				else
					res.setResponseDate("");
				
				
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	
	/**
	 * Delete Post
	 * @param grpId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean deletePost(int taskId){
		boolean retval = false;
		ArrayList<TblTaskStore> resList = new ArrayList<TblTaskStore>();
		ArrayList<TblTaskArea> areaList = new ArrayList<TblTaskArea>();
		ArrayList<TblTaskUpload> uploadList = new ArrayList<TblTaskUpload>();
		
		
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			
			TblTask c = (TblTask)session.get(TblTask.class, taskId);
			Query  query =  session.createQuery("FROM  TblTaskStore t WHERE t.tblTask = :taskId ")
				    		.setEntity("taskId", c);
			resList = (ArrayList<TblTaskStore>) query.list();
			for(TblTaskStore t: resList){
				session.delete(t);
			}
			Query  query1 =  session.createQuery("FROM  TblTaskArea t WHERE t.tblTask = :taskId ")
	    			.setEntity("taskId", c);
			areaList = (ArrayList<TblTaskArea>) query1.list();
			for(TblTaskArea t: areaList){
				session.delete(t);
			}
			
			Query  queryupload =  session.createQuery("FROM  TblTaskUpload t WHERE t.tblTask = :taskId ")
	    			.setEntity("taskId", c);
			uploadList = (ArrayList<TblTaskUpload>) queryupload.list();
			for(TblTaskUpload t: uploadList){
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
	
	
	
	/**
	 * Get Post By ID
	 * @param id
	 * @return
	 */
	public TblTask getPostById(int taskId){
		TblTask t = null;
		Session session = HibernateUtil.currentSession();
		try{
			t= (TblTask)session.get(TblTask.class, taskId);
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return t;
	}
	
	/**
	 * Save Topic Details
	 * @param tblTask
	 * @param slist 
	 * @param recType 
	 * @param topicId TODO
	 * @return
	 */
	public String savePost(TblTask tblTask, int portfolioId, int[] alist, int[] slist,ArrayList<TblUploadRes> ulist){
		String taskCd = null;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			TblTaskPortfolio t = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, portfolioId);
			tblTask.setTblTaskPortfolio(t);
			
			taskCd = generateTxnCode(session, "TASK", "TSK");
			
			tblTask.setTaskCd(taskCd);
			
			session.saveOrUpdate(tblTask);

			
			if(slist != null ){
				// Add Task Store
				saveTaskStore(tblTask, slist, session);
			}
			if(alist != null){
			    // Add Task Area
				saveTaskArea(tblTask, alist, session);
			}
			if(ulist!=null){
				saveUpload(tblTask,ulist,session);
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			taskCd = null;
		} 
		HibernateUtil.closeSession();
		return taskCd;		
	}
	
	

	private void saveUpload(TblTask tblTask, ArrayList<TblUploadRes> ulist, Session session) {
		if(ulist!=null){
			for(TblUploadRes r: ulist){
				
				TblTaskUpload u=new TblTaskUpload();
				u.setTblTask(tblTask);
				u.setTaskAttachPath(r.getAttachPath());
				u.setTaskAttachFilename(r.getAttachName());
				session.saveOrUpdate(u);
				
				
				
			}
		}
	}
        
        //added by hafizd on January 22nd 2014
        
        /*private void saveUploadApprove(int taskStoreId,ArrayList<TblUploadRes> ulist, Session session) {
		if(ulist!=null){
			for(TblUploadRes r: ulist){
				
           			/*TblTaskStore u=new TblTaskStore();
				u.setTaskStoreId(taskStoreId);
                                u.setTaskProofPath(r.getApprovePath());
				u.setTaskProofFilename(r.getApproveName());
				session.saveOrUpdate(u);		
				
				
			}
		}
	}*/

        //end added

	@SuppressWarnings("unchecked")
	private void saveTaskArea(TblTask tblTask, int[] slist, Session session) {
		if(slist!=null){
			for(int areaid: slist){
				ArrayList<TblTaskArea> areaList=new ArrayList<TblTaskArea>();
				TblTaskArea ta=new TblTaskArea();
				ta.setTaskStatus("P");
				TblArea tblArea = (TblArea)session.get(TblArea.class, areaid);
				ta.setTblArea(tblArea);
				ta.setTblTask(tblTask);
				ta.setTaskResponse("N");
				ta.setUpdatedDate(new Date());
				Query query = session.createQuery("FROM TblTaskArea AS p WHERE p.tblArea = :area AND p.tblTask = :task ");
				query.setEntity("area", tblArea);
				query.setEntity("task", tblTask);
				areaList=(ArrayList<TblTaskArea>)query.list();
				if(areaList.size()==0){
				session.saveOrUpdate(ta);
				}
				
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void saveTaskStore(TblTask tblTask, int[] slist, Session session) {
		
		if(slist!=null){
			for(int storeid: slist){
				ArrayList<TblTaskStore> storeList=new ArrayList<TblTaskStore>();
				TblTaskStore ts=new TblTaskStore();
				ts.setTaskStatus("P");
				ts.setTaskResponse("N");
				TblStore tblStore = (TblStore)session.get(TblStore.class, storeid);
				ts.setTblStore(tblStore);
				ts.setTblTask(tblTask);
				ts.setUpdatedDate(new Date());
				Query query = session.createQuery("FROM TblTaskStore AS p WHERE p.tblStore = :store AND p.tblTask = :task ");
				query.setEntity("store", tblStore);
				query.setEntity("task", tblTask);
				storeList=(ArrayList<TblTaskStore>)query.list();
				if(storeList.size()==0){
				session.saveOrUpdate(ts);
				}
			}
		}
	}
	
	/*public boolean updateAreaResponse(TblTaskArea a, int taskAreaId){
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			String hql = "update TblTaskArea as a set a.taskStatus=:taskStatus, a.taskRemark =:taskRemark,a.updatedBy=:updatedBy,a.updatedDate=:updatedDate,a.taskResponse=:taskResponse where a.taskAreaId = :taskAreaId";
		    Query query = session.createQuery(hql);
		    query.setString("taskStatus","R");
		    query.setString("taskRemark",a.getTaskRemark());
		    query.setString("updatedBy",a.getUpdatedBy());
		    query.setDate("updatedDate", a.getUpdatedDate());
		    query.setString("taskResponse", a.getTaskResponse());
		    query.setInteger("taskAreaId", taskAreaId);
		 
		    query.executeUpdate();
			transaction.commit();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}*/
        
        //change class of updateAreaResponse by hafizd on January 22nd 2014
/*        
	public boolean updateAreaResponse(TblTaskArea a, int taskAreaId,ArrayList<TblUploadRes> ulist){
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			
			String hql = "update TblTaskArea as a set a.taskStatus=:taskStatus,a.taskRemark =:taskRemark,a.updatedBy=:updatedBy,a.updatedDate=CURRENT_TIMESTAMP(), a.taskResponse=:taskResponse where a.taskAreaId = :taskAreaId";
		    Query query = session.createQuery(hql);
		    query.setString("taskStatus","R");
		    query.setString("taskRemark",a.getTaskRemark());
		    query.setString("updatedBy",a.getUpdatedBy());
		    //query.setDate("updatedDate", a.getUpdatedDate());
		    query.setString("taskResponse", a.getTaskResponse());
		    query.setInteger("taskAreaId", taskAreaId);
		 
		    query.executeUpdate();
                    if(ulist!=null){
                        for(TblUploadRes r: ulist){
				
                                String hql2 = "update TblTaskArea as a set a.taskProofPath=:taskProofPath,a.taskProofFilename =:taskProofFilename where a.taskAreaId = :taskAreaId";
                                Query query2 = session.createQuery(hql2);
                                query2.setString("taskProofPath", r.getApprovePath());
                                query2.setString("taskProofFilename", r.getApproveName());
                                query2.setInteger("taskAreaId", taskAreaId);
		 
                                query2.executeUpdate();
				/*TblTaskStore u=new TblTaskStore();
				u.setTaskStoreId(taskStoreId);
                                u.setTaskProofPath(r.getApprovePath());
				u.setTaskProofFilename(r.getApproveName());
				session.saveOrUpdate(u);*/		
				
/*				
			}
				//saveUploadApprove(taskStoreId,ulist,session);
                    }
			transaction.commit();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
        
*/
        //change class of updateStoreResponse by hafizd on January 22nd 2014
          @SuppressWarnings("unchecked")
	public boolean updateTaskStoreResponse(TblTaskStore a,  ArrayList<TblUploadRes> ulist){
		boolean retval = false;            
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
                        session.saveOrUpdate(a);
                        saveStoreResponse(a,ulist,session);
			transaction.commit();
                        retval= true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
                        
	}        

      
        
        @SuppressWarnings("unchecked")
	public boolean updateTaskAreaResponse(TblTaskArea a,  ArrayList<TblUploadRes> ulist){
		boolean retval = false;            
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
                        session.saveOrUpdate(a);
                        saveAreaResponse(a,ulist,session);
			transaction.commit();
                        retval= true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
                        
	}        
        
        @SuppressWarnings("unchecked")
	public void saveStoreResponse(TblTaskStore a, ArrayList<TblUploadRes> ulist, Session session){
                if(ulist!=null){
			for(TblUploadRes r: ulist){
				TblTaskResponse u=new TblTaskResponse();
				u.setTblTaskStore(a);
				u.setTaskProofPath(r.getApprovePath());
				u.setTaskProofFilename(r.getApproveName());
				session.saveOrUpdate(u);
			}
		}
                return;
	}
        
        @SuppressWarnings("unchecked")
	public void saveAreaResponse(TblTaskArea a, ArrayList<TblUploadRes> ulist, Session session){
                if(ulist!=null){
			for(TblUploadRes r: ulist){
				TblTaskResponseArea u=new TblTaskResponseArea();
				u.setTblTaskArea(a);
				u.setTaskProofPath(r.getApprovePath());
				u.setTaskProofFilename(r.getApproveName());
				session.saveOrUpdate(u);
			}
		}
                return;
	}
/*
	public boolean updateStoreResponse(TblTaskStore a, int taskStoreId,ArrayList<TblUploadRes> ulist){
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			
			String hql = "update TblTaskStore as a set a.taskStatus=:taskStatus,a.taskRemark =:taskRemark,a.updatedBy=:updatedBy,a.updatedDate=CURRENT_TIMESTAMP(), a.taskResponse=:taskResponse where a.taskStoreId = :taskStoreId";
		    Query query = session.createQuery(hql);
		    query.setString("taskStatus","R");
		    query.setString("taskRemark",a.getTaskRemark());
		    query.setString("updatedBy",a.getUpdatedBy());
		    //query.setDate("updatedDate", a.getUpdatedDate());
		    query.setString("taskResponse", a.getTaskResponse());
		    query.setInteger("taskStoreId", taskStoreId);
		  
		    query.executeUpdate();
                    if(ulist!=null){
                        for(TblUploadRes r: ulist){
				
                                String hql2 = "update TblTaskStore as a set a.taskProofPath=:taskProofPath,a.taskProofFilename =:taskProofFilename where a.taskStoreId = :taskStoreId";
                                Query query2 = session.createQuery(hql2);
                                query2.setString("taskProofPath", r.getApprovePath());
                                query2.setString("taskProofFilename", r.getApproveName());
                                query2.setInteger("taskStoreId", taskStoreId);

                                query2.executeUpdate();
				/*TblTaskStore u=new TblTaskStore();
				u.setTaskStoreId(taskStoreId);
                                u.setTaskProofPath(r.getApprovePath());
				u.setTaskProofFilename(r.getApproveName());
				session.saveOrUpdate(u);*/		
				
/*				
			}
				//saveUploadApprove(taskStoreId,ulist,session);
                    }
			transaction.commit();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
*/  
      
	/*public boolean updateStoreResponse(TblTaskStore a, int taskStoreId){
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			
			String hql = "update TblTaskStore as a set a.taskStatus=:taskStatus,a.taskRemark =:taskRemark,a.updatedBy=:updatedBy,a.updatedDate=:updatedDate, a.taskResponse=:taskResponse where a.taskStoreId = :taskStoreId";
		    Query query = session.createQuery(hql);
		    query.setString("taskStatus","R");
		    query.setString("taskRemark",a.getTaskRemark());
		    query.setString("updatedBy",a.getUpdatedBy());
		    query.setDate("updatedDate", a.getUpdatedDate());
		    query.setString("taskResponse", a.getTaskResponse());
		    query.setInteger("taskStoreId", taskStoreId);
		  
		    query.executeUpdate();
			transaction.commit();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}*/
	
	// Store Details
			@SuppressWarnings("unchecked")
			public ArrayList<TblTaskStore> getStores(TblTask p){
				
				ArrayList<TblTaskStore> storeList=new ArrayList<TblTaskStore>();
				Session session = HibernateUtil.currentSession();
				try {

					
					Query  query =  session.createQuery("FROM  TblTaskStore t INNER JOIN FETCH t.tblStore WHERE t.tblTask = :taskId ")
			    			.setEntity("taskId", p);
					storeList = (ArrayList<TblTaskStore>) query.list();
				
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
				HibernateUtil.closeSession();
				return  storeList;
			}
                        
                        
			@SuppressWarnings("unchecked")
			public ArrayList<TblTaskResponse> getResponse(TblTaskStore p){
				ArrayList<TblTaskResponse> responseList=null;
				Session session = HibernateUtil.currentSession();
				try {
					Query  query =  session.createQuery("FROM  TblTaskResponse as t WHERE t.tblTaskStore = :tblTaskStore ")
			    			.setEntity("tblTaskStore", p);
					responseList = (ArrayList<TblTaskResponse>) query.list();
				
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
				HibernateUtil.closeSession();
				return  responseList;
			}                        
		
			// Area Details
			@SuppressWarnings("unchecked")
			public ArrayList<TblTaskArea> getAreas(TblTask p){
				
				ArrayList<TblTaskArea> areaList=new ArrayList<TblTaskArea>();
				Session session = HibernateUtil.currentSession();
				try {
					
					Query  query =  session.createQuery("FROM  TblTaskArea t INNER JOIN FETCH t.tblArea WHERE t.tblTask = :taskId ")
			    			.setEntity("taskId", p);
					areaList = (ArrayList<TblTaskArea>) query.list();
				
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
				HibernateUtil.closeSession();
				return  areaList;
			}
	
			@SuppressWarnings("unchecked")
			public ArrayList<TblTaskResponseArea> getResponseArea(TblTaskArea p){
				ArrayList<TblTaskResponseArea> responseList=null;
				Session session = HibernateUtil.currentSession();
				try {
					Query  query =  session.createQuery("FROM  TblTaskResponseArea as t WHERE t.tblTaskArea = :tblTaskArea ")
			    			.setEntity("tblTaskArea", p);
					responseList = (ArrayList<TblTaskResponseArea>) query.list();
				
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
				HibernateUtil.closeSession();
				return  responseList;
			}                        
	
			
			// Store Details
			@SuppressWarnings("unchecked")
			public ArrayList<TblTaskRes> getAreaStores(int taskId, int areaId,int taskAreaId){
				ArrayList<TblTaskRes> res=new ArrayList<TblTaskRes>();
				ArrayList<TblTaskStore> storeList=new ArrayList<TblTaskStore>();
				Session session = HibernateUtil.currentSession();
				try {
					TblTask c = (TblTask)session.get(TblTask.class, taskId);
				
				
					Query  query =  session.createQuery("FROM  TblTaskStore t INNER JOIN FETCH t.tblStore WHERE t.tblTask = :taskId AND t.taskSite='A' ")
			    			.setEntity("taskId", c);
			    			
					storeList = (ArrayList<TblTaskStore>) query.list();
				for(TblTaskStore s: storeList){
					if(areaId==s.getTblStore().getTblArea().getAreaId()){
						SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
						
							
						TblTaskRes r=new TblTaskRes();
						r.setUpdateDate(sformat.format(s.getUpdatedDate()));
						
						
						r.setTaskId(taskId);
						
						r.setTaskAreaId(taskAreaId);
				
						r.setTaskStoreId(s.getTaskStoreId());
						r.setRecipient(s.getTblStore().getStoreCode()+" - " +s.getTblStore().getStoreName());
						r.setStoreId(s.getTblStore().getStoreId());
						r.setAreaName(s.getTblStore().getTblArea().getAreaName());
						res.add(r);
					}
				}
				
				} catch (Exception e) {
					e.printStackTrace();
				} 
				HibernateUtil.closeSession();
				return  res;
			}
			
			@SuppressWarnings("unchecked")
			public ArrayList<TblTaskUpload> getTaskUpload(TblTask p){
				ArrayList<TblTaskUpload> alist=null;
				Session session = HibernateUtil.currentSession();
				try{
					Query query = session.createQuery("FROM TblTaskUpload AS f WHERE f.tblTask = :tblTask");
					query.setEntity("tblTask", p);
					alist=(ArrayList<TblTaskUpload>)query.list();
				}catch (HibernateException e) {
					e.printStackTrace();
				}
				HibernateUtil.closeSession();
				return alist;
			}
			
			
}



