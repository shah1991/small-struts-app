package net.slide.dao;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import net.slide.pojo.TblChklst;
import net.slide.pojo.TblChklstTopic;
import net.slide.pojo.TblFileGroup;
import net.slide.pojo.TblFileShare;
import net.slide.pojo.TblPostStore;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblFileRes;
import net.slide.pojores.TblMasterRes;
import net.slide.pojores.TblUploadRes;
import net.slide.util.Constants;
import net.slide.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FileShareDao {
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getFileList(int groupId,int fileOwnerRole,int fileOwnerSite){
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
	/*	Query  query =  session.createQuery("FROM  TblFileShare ta where ta.fileOwnerRole=:fileOwnerRole and ta.fileOwnerSite=:fileOwnerSite and ta.tblFileGroup = :fileGroup order by ")
					    .setInteger("fileOwnerRole", fileOwnerRole)
					    .setInteger("fileOwnerSite", fileOwnerSite)
						.setEntity("fileGroup", g);*/
		
		Query  query =  session.createQuery("FROM  TblFileShare ta where  ta.tblFileGroup = :fileGroup and  date(updatedDate) between current_date-30 and current_date order by storeCode, updatedDate DESC")
			    				.setEntity("fileGroup", g);

		resList = (ArrayList<TblFileShare>) query.list();
		
		String folderNameStr[] = null;String folderName=null;
		
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			if(f.getStore().equals("Y")){
			r.setStore("Yes");
			}else{
			r.setStore("No");	
			}
			if(f.getFranchise().equals("Y")){
			r.setFranchise("Yes");
			}else{
			r.setFranchise("No");
			}
	      r.setCreatedDate(sformat.format(f.getCreatedDate()));
	      	        
			folderNameStr = f.getFilePath().split("/");
			folderName = folderNameStr[1];
			r.setFileFolderName(folderName);
			
			fileList.add(r);
		}
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;

	
	}
	
	
	

	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getHQDateList(int groupId,int fileOwnerRole,int fileOwnerSite){
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	
	try {
		Query  query =  session.createSQLQuery("SELECT TO_CHAR(updated_date,'dd-MM-yyyy') AS mm, COUNT(*) AS cnt, TO_CHAR(updated_date,'yyyyMMdd') ymd " +
				"FROM  tbl_file_share  " +
				"WHERE date(updated_date) between current_date-300 and current_date " +
				"AND  file_group_id = :fileGroup " +
				"GROUP BY mm, ymd ORDER BY ymd DESC " )
				
				
				.setInteger("fileGroup", groupId);
			ArrayList<Object[]> objList = (ArrayList<Object[]>) query.list();
			for(Object[] val : objList){
				
				TblFileRes r=new TblFileRes();
				r.setPostDate((String)val[0]);
				r.setCount((String)val[1].toString());
				r.setGroupId(groupId);
		        fileList.add(r);
			}
		

	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;

	
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getStoreDateList(int groupId,int fileOwnerRole,int fileOwnerSite,String storeCode){
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	
	try {
			Query query = session.createSQLQuery("SELECT TO_CHAR(updated_date,'dd-MM-yyyy') AS mm, COUNT(*) AS cnt "
					+ "FROM  tbl_file_share f "
					+ "WHERE (:storeCode LIKE store_code|| '%') AND date(updated_date) between current_date-300 and current_date "
					+ "AND  file_group_id = :fileGroup AND store_code <> ''" + " GROUP BY mm order by TO_CHAR(updated_date,'dd-MM-yyyy')")
				
				.setString("storeCode", storeCode)
				.setInteger("fileGroup", groupId);
			ArrayList<Object[]> objList = (ArrayList<Object[]>) query.list();
			for(Object[] val : objList){
				
				TblFileRes r=new TblFileRes();
				r.setPostDate((String)val[0]);
				r.setCount((String)val[1].toString());
				r.setGroupId(groupId);
		        fileList.add(r);
			}
		

	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;

	
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getStoreFolderFileList(int groupId,String postDate,String storeCode,String userLogin) throws ParseException{
	
		ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT TO_CHAR(updated_date,'dd-MM-yyyy') AS mm, COUNT(*) AS cnt ,file_folder, TO_CHAR(updated_date,'yyyy-MM-dd') ymd FROM  tbl_file_share  "+
													"WHERE TO_CHAR(updated_date,'dd-MM-yyyy')=:updatedDate " +
													" AND file_group_id =:fileGroup AND ((:storeCode LIKE store_code||'%')   OR store_code = :userLogin) GROUP BY file_folder,mm, ymd ORDER BY ymd DESC")
										
				
					.setString("updatedDate", postDate)
					.setInteger("fileGroup", groupId)
					.setString("storeCode", storeCode)
					.setString("userLogin",userLogin);

			ArrayList<Object[]> objList = (ArrayList<Object[]>) query.list();
			for(Object[] val : objList){
				TblFileRes r=new TblFileRes();
				r.setPostDate((String)val[0]);
				r.setCount((String)val[1].toString());
				r.setFileFolderName((String)val[2]);
				r.setFileFolderName(r.getFileFolderName());
				r.setGroupId(groupId);
		        r.setCreatedDate((String)val[0]);
		        fileList.add(r);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return fileList;
	}
		
	

	
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getFolderFileList(int groupId,String postDate) throws ParseException{
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Date updateDate=(Date)formatter.parse(postDate);
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
		
		
		Query  query =  session.createSQLQuery("SELECT TO_CHAR(updated_date,'dd-MM-yyyy') AS mm, COUNT(*) AS cnt ,file_folder, TO_CHAR(updated_date,'yyyy-MM-dd') ymd " +
				"FROM  tbl_file_share f " +
				"WHERE TO_CHAR(updated_date,'dd-MM-yyyy')=:updatedDate " +
				"AND  file_group_id = :fileGroup " +
				"GROUP BY file_folder, mm, ymd ORDER BY ymd DESC" )
				
				.setString("updatedDate", postDate)
				.setInteger("fileGroup", groupId);
			ArrayList<Object[]> objList = (ArrayList<Object[]>) query.list();
			for(Object[] val : objList){
				
				TblFileRes r=new TblFileRes();
				r.setPostDate((String)val[0]);
				r.setCount((String)val[1].toString());
				r.setFileFolderName((String)val[2]);
				r.setFileFolderName(r.getFileFolderName());
				r.setGroupId(groupId);
		        r.setCreatedDate((String)val[0]);
		        fileList.add(r);
			}
			
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;
}
	

	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getExportFileList(int groupId,String postDate,String fileFolder) throws ParseException{
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Date updateDate=(Date)formatter.parse(postDate);
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
		Query  query =  session.createQuery("FROM  TblFileShare ta where  ta.tblFileGroup = :fileGroup  and TO_CHAR(updated_date,'dd-MM-yyyy')=:updatedDate "+
											" and ta.fileFolder =:fileFolder order by storeCode")
			    				.setEntity("fileGroup", g)
			    				.setString("updatedDate", postDate)
			    				.setString("fileFolder",fileFolder);

		resList = (ArrayList<TblFileShare>) query.list();
		
		
		
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			
			r.setFileName(f.getFileName());
			
			r.setStoreCode(f.getStoreCode());
		
		TblStore tblStore=null;
			
				Query storequery = session.createQuery("FROM TblStore AS st WHERE UPPER(st.storeCode) = :storeCode");
				
				storequery.setString("storeCode",f.getStoreCode().toUpperCase());
				tblStore = (TblStore) storequery.uniqueResult();
	       if(tblStore!=null){
	        r.setFileFolderName("Success");
	       } else{
	    	r.setFileFolderName("Failure");
	       }
			
			fileList.add(r);
			}
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;
}
	
	
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getDateFileList(int groupId,String postDate,String fileFolder) throws ParseException{
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Date updateDate=(Date)formatter.parse(postDate);
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
		Query  query =  session.createQuery("FROM  TblFileShare ta where  ta.tblFileGroup = :fileGroup  and TO_CHAR(updated_date,'dd-MM-yyyy')=:updatedDate "+
											" and ta.fileFolder =:fileFolder order by storeCode")
			    				.setEntity("fileGroup", g)
			    				.setString("updatedDate", postDate)
			    				.setString("fileFolder",fileFolder);

		resList = (ArrayList<TblFileShare>) query.list();
		
		
		
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			if(f.getStore().equals("Y")){
			r.setStore("Yes");
			}else{
			r.setStore("No");	
			}
			if(f.getFranchise().equals("Y")){
			r.setFranchise("Yes");
			}else{
			r.setFranchise("No");
			}
	        r.setCreatedDate(sformat.format(f.getCreatedDate()));
	        r.setFileFolderName(f.getFileFolder());
			
			r.setHits(f.getHits());
			fileList.add(r);
		}
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;
}
	//new on 20/05/2013
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getDateFileList(int groupId,String postDate,String flag,String storeCode,String userLogin) throws ParseException{
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Date updateDate=(Date)formatter.parse(postDate);
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	Query  query = null;
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
		
		if(flag.equals("S")){
			query =  session.createQuery("FROM  TblFileShare ta where (ta.storeCode=:storeCode OR ta.storeCode=:userLogin)  and  ta.store='Y' and ta.tblFileGroup = :fileGroup  and TO_CHAR(updated_date,'dd-MM-yyyy')=:updatedDate")
			    				.setString("storeCode", storeCode)
			    				.setString("userLogin",userLogin)
								.setEntity("fileGroup", g)
			    				.setString("updatedDate", postDate);
		}else{
			query =  session.createQuery("FROM  TblFileShare ta where (ta.storeCode=:storeCode OR ta.storeCode=:userLogin)  and  ta.franchise='Y' and ta.tblFileGroup = :fileGroup  and TO_CHAR(updated_date,'dd-MM-yyyy')=:updatedDate")
					.setString("storeCode", storeCode)
					.setString("userLogin",userLogin)
					.setEntity("fileGroup", g)
    				.setString("updatedDate", postDate);
		}

		resList = (ArrayList<TblFileShare>) query.list();
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			if(f.getStore().equals("Y")){
			r.setStore("Yes");
			}else{
			r.setStore("No");	
			}
			if(f.getFranchise().equals("Y")){
			r.setFranchise("Yes");
			}else{
			r.setFranchise("No");
			}
	        r.setCreatedDate(sformat.format(f.getCreatedDate()));
			fileList.add(r);
		}
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;
}	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getStoreFileList(int groupId,String storeCode){
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	ArrayList<TblFileShare> resList = null;
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
		Query  query =  session.createQuery("FROM  TblFileShare ta where  ta.storeCode=:storeCode and ta.store='Y' and ta.tblFileGroup = :fileGroup  ORDER BY ta.storeCode, ta.updatedDate DESC")
					    .setString("storeCode", storeCode)
					 	.setEntity("fileGroup", g);
		resList = (ArrayList<TblFileShare>) query.list();
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			r.setCreatedBy(f.getCreatedBy());
			r.setCreatedDate(sformat.format(f.getCreatedDate()));
			fileList.add(r);
		}
	 
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;
	}
	//new on 20/05/2013
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getStoreFileList(int groupId,String storeCode,String  userLogin){
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	ArrayList<TblFileShare> resList = null;
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
		Query  query =  session.createQuery("FROM  TblFileShare ta where  (ta.storeCode=:storeCode OR ta.storeCode=:userLogin)   and ta.store='Y' and ta.tblFileGroup = :fileGroup  ORDER BY ta.storeCode, ta.updatedDate DESC")
					    .setString("storeCode", storeCode)
					    .setString("userLogin",userLogin)
					 	.setEntity("fileGroup", g);
		resList = (ArrayList<TblFileShare>) query.list();
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			r.setCreatedBy(f.getCreatedBy());
			r.setCreatedDate(sformat.format(f.getCreatedDate()));
			fileList.add(r);
		}
	 
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getFranchiseList(int groupId,String storeCode){
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	ArrayList<TblFileShare> resList = null;
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
		Query  query =  session.createQuery("FROM  TblFileShare ta where  ta.storeCode=:storeCode and ta.franchise='Y' and ta.tblFileGroup = :fileGroup  ORDER BY ta.storeCode, ta.updatedDate DESC")
					    .setString("storeCode", storeCode)
					 	.setEntity("fileGroup", g);
		resList = (ArrayList<TblFileShare>) query.list();
	 
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			r.setCreatedBy(f.getCreatedBy());
			r.setCreatedDate(sformat.format(f.getCreatedDate()));
			fileList.add(r);
		}
	 
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;
	}
	
	//new on 20/05/2013
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getFranchiseList(int groupId,String storeCode,String  userLogin){
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	ArrayList<TblFileShare> resList = null;
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
		Query  query =  session.createQuery("FROM  TblFileShare ta where  (ta.storeCode=:storeCode OR ta.storeCode=:userLogin) and ta.franchise='Y' and ta.tblFileGroup = :fileGroup  ORDER BY ta.storeCode, ta.updatedDate DESC")
					    .setString("storeCode", storeCode)
					    .setString("userLogin",userLogin)
					 	.setEntity("fileGroup", g);
		resList = (ArrayList<TblFileShare>) query.list();
	 
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			r.setCreatedBy(f.getCreatedBy());
			r.setCreatedDate(sformat.format(f.getCreatedDate()));
			fileList.add(r);
		}
	 
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;
	}
	
	/**
	 * Save Topic Details
	 * @param f
	 * @param topicId TODO
	 * @return
	 */
	public boolean savePost(ArrayList<TblUploadRes> uploadList, int groupId,TblUser tblUser,String sm, String fm){
		String storeCodePrefix="";
		Properties prop = new Properties();

		try {
			// load a properties file from class path, inside static method
			prop.load(getClass().getClassLoader().getResourceAsStream(
					"config.properties"));
			storeCodePrefix=prop.getProperty("store.code.prefix");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
			
		
			for(TblUploadRes res:uploadList){
				TblFileShare f=new TblFileShare();
				f.setFileName(res.getAttachName());
				f.setFilePath(res.getAttachPath());
				String folder[]=res.getAttachPath().split("/");
				f.setFileFolder(folder[2]);
				f.setFileStatus("Y");
				f.setStore(sm);
				f.setFranchise(fm);
				f.setTblFileGroup(g);
				f.setFileOwnerRole(tblUser.getTblRole().getRoleId());
				f.setFileOwnerSite(tblUser.getUserId());
				// added by 10055
				//In this code I am checking the store code is with the predefined prefix or not by splitting the pdf file name in the file upload module
				String[] arr;
				String storeCode = " ";
				try {
					arr = res.getStoreCode().split(Constants.PDF_STORE_CODE_SPLIT_CODE);
					storeCode = arr[0].length() <= 3 ? storeCodePrefix + res.getStoreCode()
							: arr[0].charAt(0) == (storeCodePrefix.trim().equals("")?-1:storeCodePrefix.charAt(0)) ? res.getStoreCode()
									: storeCodePrefix + res.getStoreCode();
				} catch (Exception e) {
					e.printStackTrace();
				}
				f.setStoreCode(!(storeCode.equals(" ")) ? storeCode : res.getStoreCode());
				f.setCreatedBy(tblUser.getUserName());
				f.setCreatedDate(new Date());
				f.setUpdatedDate(new Date());
                                f.setHits(0);
			    session.saveOrUpdate(f);
			}
			// Add Task Store
			
			transaction.commit();
			HibernateUtil.closeSession();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		
		return retval;		
	}
	
	
	public boolean deleteFile(TblFileShare f){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			session.delete(f);
			
			transaction.commit();
			HibernateUtil.closeSession();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		
		return retval;		
	}
	
	
	public TblFileShare getFileShare(int fileId){
		TblFileShare fileshare=null;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			fileshare = (TblFileShare)session.get(TblFileShare.class, fileId);
			// Add Task Store
			
			transaction.commit();
			HibernateUtil.closeSession();
			
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		
		return fileshare;		
	}
	
	
	

	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getFileMonths(int fileGroup, String postYear)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT  TO_CHAR(updated_date,'Month') AS mmm, TO_CHAR(updated_date,'MM') AS mm, COUNT(*) AS cnt " +
												"FROM  tbl_file_share f " +
												"WHERE TO_CHAR(updated_date,'YYYY') = :yyyy " +
												"AND  file_group_id = :fileGroup " +
												"GROUP BY mmm, mm " +
												"ORDER BY mmm")
												.setString("yyyy", postYear)
												.setInteger("fileGroup", fileGroup);
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
	public ArrayList<TblMasterRes> getFileYears(int fileGroup)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT TO_CHAR(updated_date,'YYYY') AS yyyy, COUNT(*) AS cnt " +
												"FROM  tbl_file_share f " +
												"WHERE  file_group_id = :fileGroup " +
												"GROUP BY yyyy " +
												"ORDER BY yyyy")
												.setInteger("fileGroup", fileGroup);
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName("Browse File "+(String)val[0]);
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
	public ArrayList<TblMasterRes> getFileDate(int fileGroup, String postYear, String postMonth)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT TO_CHAR(updated_date,'dd-MM-yyyy'), COUNT(*) AS cnt FROM  tbl_file_share f WHERE TO_CHAR(updated_date,'YYYY')=:yyyy AND TO_CHAR(updated_date,'MM')=:mm AND file_group_id =:fileGroup GROUP BY TO_CHAR(updated_date,'dd-MM-yyyy') ORDER BY TO_CHAR(updated_date,'dd-MM-yyyy')")
													.setString("yyyy", postYear)
													.setString("mm", postMonth)
													.setInteger("fileGroup", fileGroup);
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName((String)val[0]);
				res.setParentName((String)val[0]);
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
	public ArrayList<TblFileRes> getFileList(int groupId,String storeCode){
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
	
		Query  query =  session.createQuery("FROM  TblFileShare ta where  ta.tblFileGroup = :fileGroup AND ta.storeCode = :storeCode and  date(updatedDate) between current_date-30 and current_date order by storeCode, updatedDate DESC")
			    				.setEntity("fileGroup", g)
			    				.setString("storeCode", storeCode);

		resList = (ArrayList<TblFileShare>) query.list();
		
		String folderNameStr[] = null;String folderName=null;
		
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			if(f.getStore().equals("Y")){
			r.setStore("Yes");
			}else{
			r.setStore("No");	
			}
			if(f.getFranchise().equals("Y")){
			r.setFranchise("Yes");
			}else{
			r.setFranchise("No");
			}
			r.setCreatedDate(sformat.format(f.getCreatedDate()));
	      
			folderNameStr = f.getFilePath().split("/");
			folderName = folderNameStr[1];
			r.setFileFolderName(folderName);
	      
			fileList.add(r);
		}
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;

	
	}	
	//new on 20/05/2013
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getFileList(int groupId,String storeCode, String userLogin){
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
	
			Query query = session
					.createQuery(
							"FROM  TblFileShare ta where  ta.tblFileGroup = :fileGroup AND (ta.storeCode = :storeCode OR ta.storeCode = :userLogin) and  date(updatedDate) between current_date-30 and current_date order by storeCode, updatedDate DESC")
			    				.setEntity("fileGroup", g)
			    				.setString("storeCode", storeCode)
			    				.setString("userLogin",userLogin);

		resList = (ArrayList<TblFileShare>) query.list();
		
		String folderNameStr[] = null;String folderName=null;
		
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			if(f.getStore().equals("Y")){
			r.setStore("Yes");
			}else{
			r.setStore("No");	
			}
			if(f.getFranchise().equals("Y")){
			r.setFranchise("Yes");
			}else{
			r.setFranchise("No");
			}
			r.setCreatedDate(sformat.format(f.getCreatedDate()));
	      
			folderNameStr = f.getFilePath().split("/");
			folderName = folderNameStr[1];
			System.out.println(">>>>folderName: " + folderName);
			r.setFileFolderName(folderName);
	      
			fileList.add(r);
		}
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;

	
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getFileListStore(int groupId,String storeCode){
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
	
		Query  query =  session.createQuery("FROM  TblFileShare ta where  ta.tblFileGroup = :fileGroup AND ta.storeCode = :storeCode and ta.store='Y' and  date(updatedDate) between current_date-30 and current_date order by storeCode, updatedDate DESC")
			    				.setEntity("fileGroup", g)
			    				.setString("storeCode", storeCode);

		resList = (ArrayList<TblFileShare>) query.list();
		
		String folderNameStr[] = null;String folderName=null;
		
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			if(f.getStore().equals("Y")){
			r.setStore("Yes");
			}else{
			r.setStore("No");	
			}
			if(f.getFranchise().equals("Y")){
			r.setFranchise("Yes");
			}else{
			r.setFranchise("No");
			}
			r.setCreatedDate(sformat.format(f.getCreatedDate()));
	      
			folderNameStr = f.getFilePath().split("/");
			folderName = folderNameStr[1];
			r.setFileFolderName(folderName);
	      
			fileList.add(r);
		}
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;

	
	}	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getFileListStore(int groupId,String storeCode,String userLogin){
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
	
		Query  query =  session.createQuery("FROM  TblFileShare ta where  ta.tblFileGroup = :fileGroup AND (ta.storeCode = :storeCode OR ta.storeCode = :userLogin) and ta.store='Y' and  date(updatedDate) between current_date-30 and current_date order by storeCode, updatedDate DESC")
			    				.setEntity("fileGroup", g)
			    				.setString("storeCode", storeCode)
			    				.setString("userLogin",userLogin);

		resList = (ArrayList<TblFileShare>) query.list();
		
		String folderNameStr[] = null;String folderName=null;
		
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			if(f.getStore().equals("Y")){
			r.setStore("Yes");
			}else{
			r.setStore("No");	
			}
			if(f.getFranchise().equals("Y")){
			r.setFranchise("Yes");
			}else{
			r.setFranchise("No");
			}
			r.setCreatedDate(sformat.format(f.getCreatedDate()));
	      
			folderNameStr = f.getFilePath().split("/");
			folderName = folderNameStr[1];
			r.setFileFolderName(folderName);
	      
			fileList.add(r);
		}
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;

	
	}	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getFileListFranchise(int groupId,String storeCode){
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
	
		Query  query =  session.createQuery("FROM  TblFileShare ta where  ta.tblFileGroup = :fileGroup AND ta.storeCode = :storeCode and ta.franchise='Y' and  date(updatedDate) between current_date-30 and current_date order by storeCode, updatedDate DESC")
			    				.setEntity("fileGroup", g)
			    				.setString("storeCode", storeCode);

		resList = (ArrayList<TblFileShare>) query.list();
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			if(f.getStore().equals("Y")){
			r.setStore("Yes");
			}else{
			r.setStore("No");	
			}
			if(f.getFranchise().equals("Y")){
			r.setFranchise("Yes");
			}else{
			r.setFranchise("No");
			}
	      r.setCreatedDate(sformat.format(f.getCreatedDate()));
			fileList.add(r);
		}
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;

	
	}	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getFileMonthsStore(int fileGroup, String postYear,String storeCode)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT  TO_CHAR(updated_date,'Month') AS mmm, TO_CHAR(updated_date,'MM') AS mm, COUNT(*) AS cnt " +
												"FROM  tbl_file_share f " +
												"WHERE TO_CHAR(updated_date,'YYYY') = :yyyy " +
												"AND  file_group_id = :fileGroup " +
												"AND store_code = :storeCode " +
												"GROUP BY mmm, mm " +
												"ORDER BY mmm")
												.setString("yyyy", postYear)
												.setInteger("fileGroup", fileGroup)
												.setString("storeCode", storeCode);
			
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
	
	//new on 20/05/2013
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getFileMonthsStore(int fileGroup, String postYear,String storeCode,String userLogin)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session
					.createSQLQuery(
							"SELECT  TO_CHAR(updated_date,'Month') AS mmm, TO_CHAR(updated_date,'MM') AS mm, COUNT(*) AS cnt "
									+ "FROM  tbl_file_share f " + "WHERE TO_CHAR(updated_date,'YYYY') = :yyyy "
									+ "AND  file_group_id = :fileGroup "
									+ "AND ((:storeCode LIKE store_code || '%') OR store_code = :userLogin) AND store_code <> ''" + " GROUP BY mmm, mm "
									+ "ORDER BY mmm")
												.setString("yyyy", postYear)
												.setInteger("fileGroup", fileGroup)
												.setString("storeCode", storeCode)
												.setString("userLogin",userLogin);
			
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
	
	//new on 20/05/2013
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getFileMonths(int fileGroup, String postYear,String storeCode, String flag,String userLogin)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		Query  query =null;
		try {
			if(flag.equals("S")){
			 query =  session.createSQLQuery("SELECT  TO_CHAR(updated_date,'Month') AS mmm, TO_CHAR(updated_date,'MM') AS mm, COUNT(*) AS cnt " +
												"FROM  tbl_file_share f " +
												"WHERE TO_CHAR(updated_date,'YYYY') = :yyyy " +
												"AND  file_group_id = :fileGroup " +
												"AND  (store_code = :storeCode OR store_code = :userLogin) and store='Y'" +
												"GROUP BY mmm, mm " +
												"ORDER BY mmm")
												.setString("yyyy", postYear)
												.setInteger("fileGroup", fileGroup)
												.setString("storeCode", storeCode)
												.setString("userLogin",userLogin);
			}else{
				 query =  session.createSQLQuery("SELECT  TO_CHAR(updated_date,'Month') AS mmm, TO_CHAR(updated_date,'MM') AS mm, COUNT(*) AS cnt " +
											"FROM  tbl_file_share f " +
											"WHERE TO_CHAR(updated_date,'YYYY') = :yyyy " +
											"AND  file_group_id = :fileGroup " +
											"AND   (store_code = :storeCode OR store_code = :userLogin) and franchise='Y'" +
											"GROUP BY mmm, mm " +
											"ORDER BY mmm")
											.setString("yyyy", postYear)
											.setInteger("fileGroup", fileGroup)
											.setString("storeCode", storeCode)
											.setString("userLogin",userLogin);
			}
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
	public ArrayList<TblMasterRes> getFileYearsStore(int fileGroup,String storeCode)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT TO_CHAR(updated_date,'YYYY') AS yyyy, COUNT(*) AS cnt " +
												"FROM  tbl_file_share f " +
												"WHERE  file_group_id = :fileGroup " +
												"AND store_code = :storeCode " +
												"GROUP BY yyyy " +
												"ORDER BY yyyy")
												.setInteger("fileGroup", fileGroup)
												.setString("storeCode", storeCode);
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName("Browse File "+(String)val[0]);
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
	
	//new on 20/05/2013
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getFileYearsStore(int fileGroup,String storeCode,String userLogin)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session
					.createSQLQuery("SELECT TO_CHAR(updated_date,'YYYY') AS yyyy, COUNT(*) AS cnt "
							+ "FROM  tbl_file_share f " + "WHERE  file_group_id = :fileGroup "
							+ "AND ((:storeCode LIKE store_code || '%') OR store_code = :userLogin) AND store_code <> ''" + " GROUP BY yyyy "
							+ "ORDER BY yyyy")
												.setInteger("fileGroup", fileGroup)
												.setString("storeCode", storeCode)
												.setString("userLogin",userLogin);
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName("Browse File "+(String)val[0]);
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
	
	//new on 20/05/2013
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getFileYears(int fileGroup,String storeCode,String flag,String userLogin)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		Query  query=null;
		try {
			if(flag.equals("S")){
			  query =  session.createSQLQuery("SELECT TO_CHAR(updated_date,'YYYY') AS yyyy, COUNT(*) AS cnt " +
												"FROM  tbl_file_share f " +
												"WHERE  file_group_id = :fileGroup and  (store_code = :storeCode OR store_code = :userLogin) and store='Y' " +
												"GROUP BY yyyy " +
												"ORDER BY yyyy")
												.setInteger("fileGroup", fileGroup)
												.setString("storeCode", storeCode)
												.setString("userLogin",userLogin);
			}else{
			query =  session.createSQLQuery("SELECT TO_CHAR(updated_date,'YYYY') AS yyyy, COUNT(*) AS cnt " +
						"FROM  tbl_file_share f " +
						"WHERE  file_group_id = :fileGroup and (store_code = :storeCode OR store_code = :userLogin) and franchise='Y' " +
						"GROUP BY yyyy " +
						"ORDER BY yyyy")
						.setInteger("fileGroup", fileGroup)
						.setString("storeCode", storeCode)
						.setString("userLogin",userLogin);
				
			}
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName("Browse File "+(String)val[0]);
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
	public ArrayList<TblMasterRes> getFileDateStore(int fileGroup, String postYear, String postMonth,String storeCode)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createSQLQuery("SELECT TO_CHAR(updated_date,'dd-MM-yyyy'), COUNT(*) AS cnt FROM  tbl_file_share f WHERE TO_CHAR(updated_date,'YYYY')=:yyyy AND TO_CHAR(updated_date,'MM')=:mm AND file_group_id =:fileGroup AND store_code = :storeCode GROUP BY TO_CHAR(updated_date,'dd-MM-yyyy') ORDER BY TO_CHAR(updated_date,'dd-MM-yyyy')")
													.setString("yyyy", postYear)
													.setString("mm", postMonth)
													.setInteger("fileGroup", fileGroup)
													.setString("storeCode", storeCode);
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName((String)val[0]);
				res.setParentName((String)val[0]);
				res.setDtlCount(((BigInteger)val[1]).intValue());
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	//new on 20/05/2013
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getFileDateStore(int fileGroup, String postYear, String postMonth,String storeCode,String userLogin)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session
					.createSQLQuery(
							"SELECT TO_CHAR(updated_date,'dd-MM-yyyy'), COUNT(*) AS cnt FROM  tbl_file_share f WHERE TO_CHAR(updated_date,'YYYY')=:yyyy AND TO_CHAR(updated_date,'MM')=:mm AND file_group_id =:fileGroup AND ((:storeCode LIKE store_code || '%') OR store_code = :userLogin) AND store_code <> '' GROUP BY TO_CHAR(updated_date,'dd-MM-yyyy') ORDER BY TO_CHAR(updated_date,'dd-MM-yyyy')")
													.setString("yyyy", postYear)
													.setString("mm", postMonth)
													.setInteger("fileGroup", fileGroup)
													.setString("storeCode", storeCode)
													.setString("userLogin",userLogin);
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName((String)val[0]);
				res.setParentName((String)val[0]);
				res.setDtlCount(((BigInteger)val[1]).intValue());
				retval.add(res);
			}
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	//new on 20/05/2013
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getFileDate(int fileGroup, String postYear, String postMonth, String storeCode,String flag,String userLogin)
	{
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		Query  query =null;
		try {
			if(flag.equals("S")){
			 query =  session.createSQLQuery("SELECT TO_CHAR(updated_date,'dd-MM-yyyy'), COUNT(*) AS cnt FROM  tbl_file_share f WHERE TO_CHAR(updated_date,'YYYY')=:yyyy AND TO_CHAR(updated_date,'MM')=:mm AND file_group_id =:fileGroup AND (store_code = :storeCode OR store_code = :userLogin) AND  store='Y' GROUP BY TO_CHAR(updated_date,'dd-MM-yyyy') ORDER BY TO_CHAR(updated_date,'dd-MM-yyyy')")
													.setString("yyyy", postYear)
													.setString("mm", postMonth)
													.setInteger("fileGroup", fileGroup)
													.setString("storeCode", storeCode)
													.setString("userLogin",userLogin);
			 									
			} else{
				query =  session.createSQLQuery("SELECT TO_CHAR(updated_date,'dd-MM-yyyy'), COUNT(*) AS cnt FROM  tbl_file_share f WHERE TO_CHAR(updated_date,'YYYY')=:yyyy AND TO_CHAR(updated_date,'MM')=:mm AND file_group_id =:fileGroup AND (store_code = :storeCode OR store_code = :userLogin) AND franchise='Y' GROUP BY TO_CHAR(updated_date,'dd-MM-yyyy') ORDER BY TO_CHAR(updated_date,'dd-MM-yyyy')")
						.setString("yyyy", postYear)
						.setString("mm", postMonth)
						.setInteger("fileGroup", fileGroup)
						.setString("storeCode", storeCode)
						.setString("userLogin",userLogin);
			 }
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for(Object[] val : resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName((String)val[0]);
				res.setParentName((String)val[0]);
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
	public ArrayList<TblFileRes> getDateFileListStore(int groupId,String postDate,String storeCode) throws ParseException{
	
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Date updateDate=(Date)formatter.parse(postDate);
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
		Query  query =  session.createQuery("FROM  TblFileShare ta where  ta.tblFileGroup = :fileGroup  AND ta.storeCode = :storeCode and TO_CHAR(updated_date,'dd-MM-yyyy')=:updatedDate order by storeCode")
			    				.setEntity("fileGroup", g)
			    				.setString("updatedDate", postDate)
			    				.setString("storeCode", storeCode);

		resList = (ArrayList<TblFileShare>) query.list();
		
		String folderNameStr[] = null;String folderName=null;
		
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			if(f.getStore().equals("Y")){
			r.setStore("Yes");
			}else{
			r.setStore("No");	
			}
			if(f.getFranchise().equals("Y")){
			r.setFranchise("Yes");
			}else{
			r.setFranchise("No");
			}
	        r.setCreatedDate(sformat.format(f.getCreatedDate()));
	        
			folderNameStr = f.getFilePath().split("/");
			folderName = folderNameStr[1];
			r.setFileFolderName(folderName);
			
			fileList.add(r);
		}
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;
}
	//new on 20/05/2013
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getDateFileListStore(int groupId,String postDate,String storeCode, String userLogin,String fileFolder) throws ParseException{
	Session session = HibernateUtil.currentSession();
	ArrayList<TblFileShare> resList = null;
	ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
	SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy");
	try {
		TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
		Query  query =  session.createQuery("FROM  TblFileShare ta WHERE ta.tblFileGroup =:fileGroup  AND ta.fileFolder =:fileFolder AND ((:storeCode LIKE ta.storeCode||'%') OR ta.storeCode =:userLogin) AND TO_CHAR(ta.updatedDate,'dd-MM-yyyy')=:updatedDate order by storeCode")
        .setEntity("fileGroup", g)
        						.setString("fileFolder", fileFolder)
        						.setString("updatedDate", postDate)
        						.setString("storeCode", storeCode)
        						.setString("userLogin", userLogin);

		resList = (ArrayList<TblFileShare>) query.list();
		
		String folderNameStr[] = null;String folderName=null;
		for(TblFileShare f:resList){
			TblFileRes r=new TblFileRes();
			r.setFileId(f.getFileId());
			r.setFileName(f.getFileName());
			r.setFilePath(f.getFilePath());
			r.setStoreCode(f.getStoreCode());
			if(f.getStore().equals("Y")){
			r.setStore("Yes");
			}else{
			r.setStore("No");	
			}
			if(f.getFranchise().equals("Y")){
			r.setFranchise("Yes");
			}else{
			r.setFranchise("No");
			}
	        r.setCreatedDate(sformat.format(f.getCreatedDate()));
	        
			folderNameStr = f.getFilePath().split("/");
			folderName = folderNameStr[1];
			r.setFileFolderName(folderName);
				if (f.getHits() != null) {
                        r.setHits(f.getHits()+1);
				}
			fileList.add(r);
            
			Transaction transaction = session.beginTransaction();
			try{	
				f.setHits(f.getHits()+1);
				session.saveOrUpdate(f);
				transaction.commit();
			}
			catch(Exception e){
				transaction.rollback();
			}
			
		}
	 
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return fileList;
}
	
	//new on 04/09/2015
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileShare> getStoreNotification(int storeId,int groupId){
		Session session = HibernateUtil.currentSession();
		ArrayList<TblFileShare> resList = null;		
		TblStore ts = (TblStore)session.get(TblStore.class, storeId);

		try {
			TblFileGroup fg = (TblFileGroup)session.get(TblFileGroup.class, groupId);
			Query  query =  session.createQuery("FROM  TblFileShare t WHERE ( :storeCd LIKE t.storeCode||'%' ) AND t.tblFileGroup = :groupId AND t.hits = 0")
					 		.setString("storeCd", ts.getStoreCode())
					 		.setEntity("groupId", fg);
			resList = (ArrayList<TblFileShare>) query.list();
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getFileListArea(int groupId, int fileOwnerRole, int fileOwnerSite, List<String> storeCodes) {

		Session session = HibernateUtil.currentSession();
		ArrayList<TblFileShare> resList = null;
		ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
		SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy");

		try {
			TblFileGroup g = (TblFileGroup) session.get(TblFileGroup.class, groupId);
			/*
			 * Query query = session.createQuery(
			 * "FROM  TblFileShare ta where ta.fileOwnerRole=:fileOwnerRole and ta.fileOwnerSite=:fileOwnerSite and ta.tblFileGroup = :fileGroup order by "
			 * ) .setInteger("fileOwnerRole", fileOwnerRole)
			 * .setInteger("fileOwnerSite", fileOwnerSite)
			 * .setEntity("fileGroup", g);
			 */

			Query query = session
					.createQuery(
							"FROM  TblFileShare ta where  ta.tblFileGroup = :fileGroup and  date(updatedDate) between current_date-30 and current_date AND storeCode IN (:storeCodes) order by storeCode, updatedDate DESC")
					.setEntity("fileGroup", g)
					.setParameterList("storeCodes", storeCodes);

			resList = (ArrayList<TblFileShare>) query.list();

			String folderNameStr[] = null;
			String folderName = null;

			for (TblFileShare f : resList) {
				TblFileRes r = new TblFileRes();
				r.setFileId(f.getFileId());
				r.setFileName(f.getFileName());
				r.setFilePath(f.getFilePath());
				r.setStoreCode(f.getStoreCode());
				if (f.getStore().equals("Y")) {
					r.setStore("Yes");
				} else {
					r.setStore("No");
				}
				if (f.getFranchise().equals("Y")) {
					r.setFranchise("Yes");
				} else {
					r.setFranchise("No");
				}
				r.setCreatedDate(sformat.format(f.getCreatedDate()));

				folderNameStr = f.getFilePath().split("/");
				folderName = folderNameStr[1];
				r.setFileFolderName(folderName);

				fileList.add(r);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return fileList;

	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getAreaFileDateList(int groupId, int fileOwnerRole, int fileOwnerSite, List<String> storeCodes) {

		Session session = HibernateUtil.currentSession();
		ArrayList<TblFileShare> resList = null;
		ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
		SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy");

		try {
			Query query = session.createSQLQuery(
					"SELECT TO_CHAR(updated_date,'dd-MM-yyyy') AS mm, COUNT(*) AS cnt, TO_CHAR(updated_date,'yyyyMMdd') ymd "
							+ "FROM  tbl_file_share  "
							+ "WHERE date(updated_date) between current_date-300 and current_date "
							+ "AND  file_group_id = :fileGroup AND store_code IN (:storeCodes)" + "GROUP BY mm, ymd ORDER BY ymd DESC ")

					.setInteger("fileGroup", groupId)
					.setParameterList("storeCodes", storeCodes);

			
/*			
			StringBuffer sbuff= new StringBuffer("#"); 
			for(String strCode : storeCodes){			
				sbuff.append(strCode);
				sbuff.append("#");
			}

			
			Query query = session.createSQLQuery(			
			"SELECT TO_CHAR(updated_date,'dd-MM-yyyy') AS mm, COUNT(*) AS cnt, TO_CHAR(updated_date,'yyyyMMdd') ymd "
			+ "FROM  tbl_file_share  "
			+ "WHERE date(updated_date) between current_date-300 and current_date "
			+ "AND  file_group_id = :fileGroup AND :storeCodes LIKE '%#'||store_code||'%' " + "GROUP BY mm, ymd ORDER BY ymd DESC ")
			

					.setInteger("fileGroup", groupId)
					.setString("storeCodes", sbuff.toString());

*/			
			ArrayList<Object[]> objList = (ArrayList<Object[]>) query.list();
			for (Object[] val : objList) {

				TblFileRes r = new TblFileRes();
				r.setPostDate((String) val[0]);
				r.setCount((String) val[1].toString());
				r.setGroupId(groupId);
				fileList.add(r);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return fileList;

	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getAreaFolderFileList(int groupId, String postDate, List<String> storeCodes) throws ParseException {

		Session session = HibernateUtil.currentSession();
		ArrayList<TblFileShare> resList = null;
		ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date updateDate = (Date) formatter.parse(postDate);
		SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			TblFileGroup g = (TblFileGroup) session.get(TblFileGroup.class, groupId);

			Query query = session.createSQLQuery(
					"SELECT TO_CHAR(updated_date,'dd-MM-yyyy') AS mm, COUNT(*) AS cnt ,file_folder, TO_CHAR(updated_date,'yyyy-MM-dd') ymd "
							+ "FROM  tbl_file_share f " + "WHERE TO_CHAR(updated_date,'dd-MM-yyyy')=:updatedDate "
							+ "AND  file_group_id = :fileGroup AND store_code IN (:storeCodes)" + "GROUP BY file_folder, mm, ymd ORDER BY ymd DESC")

					.setString("updatedDate", postDate)
					.setInteger("fileGroup", groupId)
					.setParameterList("storeCodes", storeCodes);
			
			ArrayList<Object[]> objList = (ArrayList<Object[]>) query.list();
			for (Object[] val : objList) {

				TblFileRes r = new TblFileRes();
				r.setPostDate((String) val[0]);
				r.setCount((String) val[1].toString());
				r.setFileFolderName((String) val[2]);
				r.setFileFolderName(r.getFileFolderName());
				r.setGroupId(groupId);
				r.setCreatedDate((String) val[0]);
				fileList.add(r);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return fileList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileRes> getDateFileListArea(int groupId, String postDate, String fileFolder, List<String> storeCodes)
			throws ParseException {

		Session session = HibernateUtil.currentSession();
		ArrayList<TblFileShare> resList = null;
		ArrayList<TblFileRes> fileList = new ArrayList<TblFileRes>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date updateDate = (Date) formatter.parse(postDate);
		SimpleDateFormat sformat = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			TblFileGroup g = (TblFileGroup) session.get(TblFileGroup.class, groupId);
			Query query = session
					.createQuery(
							"FROM  TblFileShare ta where  ta.tblFileGroup = :fileGroup  and TO_CHAR(updated_date,'dd-MM-yyyy')=:updatedDate "
									+ " and ta.fileFolder =:fileFolder and storeCode IN (:storeCodes) order by storeCode")
					.setEntity("fileGroup", g)
					.setString("updatedDate", postDate)
					.setString("fileFolder", fileFolder)
					.setParameterList("storeCodes", storeCodes);

			resList = (ArrayList<TblFileShare>) query.list();

			for (TblFileShare f : resList) {
				TblFileRes r = new TblFileRes();
				r.setFileId(f.getFileId());
				r.setFileName(f.getFileName());
				r.setFilePath(f.getFilePath());
				r.setStoreCode(f.getStoreCode());
				if (f.getStore().equals("Y")) {
					r.setStore("Yes");
				} else {
					r.setStore("No");
				}
				if (f.getFranchise().equals("Y")) {
					r.setFranchise("Yes");
				} else {
					r.setFranchise("No");
				}
				r.setCreatedDate(sformat.format(f.getCreatedDate()));
				r.setFileFolderName(f.getFileFolder());
				if (f.getHits() != null) {
					r.setHits(f.getHits());
				}
				fileList.add(r);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return fileList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getFileYearsArea(int fileGroup, List<String> storeCodes) {
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session
					.createSQLQuery(
							"SELECT TO_CHAR(updated_date,'YYYY') AS yyyy, COUNT(*) AS cnt " + "FROM  tbl_file_share f "
									+ "WHERE  file_group_id = :fileGroup AND store_code in (:storeCodes)" + "GROUP BY yyyy " + "ORDER BY yyyy")
					.setInteger("fileGroup", fileGroup)
					.setParameterList("storeCodes", storeCodes);
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for (Object[] val : resList) {
				TblMasterRes res = new TblMasterRes();
				res.setMstName("Browse File " + (String) val[0]);
				res.setDtlCount(((BigInteger) val[1]).intValue());
				res.setParentName((String) val[0]);
				retval.add(res);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getFileMonthsArea(int fileGroup, String postYear, List<String> storeCodes) {
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session
					.createSQLQuery(
							"SELECT  TO_CHAR(updated_date,'Month') AS mmm, TO_CHAR(updated_date,'MM') AS mm, COUNT(*) AS cnt "
									+ "FROM  tbl_file_share f " + "WHERE TO_CHAR(updated_date,'YYYY') = :yyyy "
									+ "AND  file_group_id = :fileGroup AND store_code IN (:storeCodes)" + "GROUP BY mmm, mm " + "ORDER BY mmm")
					.setString("yyyy", postYear).setInteger("fileGroup", fileGroup)
					.setParameterList("storeCodes", storeCodes);
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for (Object[] val : resList) {
				TblMasterRes res = new TblMasterRes();
				res.setMstName((String) val[0]);
				res.setParentName("" + (String) val[1]);
				res.setDtlCount(((BigInteger) val[2]).intValue());
				retval.add(res);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> getFileDateArea(int fileGroup, String postYear, String postMonth, List<String> storeCodes) {
		ArrayList<TblMasterRes> retval = new ArrayList<TblMasterRes>();
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session
					.createSQLQuery(
							"SELECT TO_CHAR(updated_date,'dd-MM-yyyy'), COUNT(*) AS cnt FROM  tbl_file_share f WHERE TO_CHAR(updated_date,'YYYY')=:yyyy AND TO_CHAR(updated_date,'MM')=:mm AND file_group_id =:fileGroup AND store_code IN (:storeCodes) GROUP BY TO_CHAR(updated_date,'dd-MM-yyyy') ORDER BY TO_CHAR(updated_date,'dd-MM-yyyy')")
					.setString("yyyy", postYear)
					.setString("mm", postMonth)
					.setInteger("fileGroup", fileGroup)
					.setParameterList("storeCodes", storeCodes);
			
			ArrayList<Object[]> resList = (ArrayList<Object[]>) query.list();
			for (Object[] val : resList) {
				TblMasterRes res = new TblMasterRes();
				res.setMstName((String) val[0]);
				res.setParentName((String) val[0]);
				res.setDtlCount(((BigInteger) val[1]).intValue());
				retval.add(res);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
}
