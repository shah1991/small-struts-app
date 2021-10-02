package net.slide.dao;

import java.util.ArrayList;


import net.slide.pojo.TblFileGroup;
import net.slide.pojores.TblMasterRes;
import net.slide.util.HibernateUtil;
import net.slide.util.TransException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FileGroupDao {
	
//	/**
//	 * Get Folder by Name
//	 * @param f TODO
//	 * @param folderName
//	 * @return
//	 */
//	public TblFileFolder getFolderByName(TblFileGroup g, String folderName) {
//		TblFileFolder retval = null;
//		Session session = HibernateUtil.currentSession();
//		try {
//			Query query = session.createQuery("FROM TblFileFolder AS f WHERE f.tblFileGroup = :group AND UPPER(f.folderName) = :folderName ");
//			query.setString("folderName", folderName.toUpperCase());
//			query.setEntity("group", g);
//			retval = (TblFileFolder) query.uniqueResult();
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		}
//		HibernateUtil.closeSession();
//		return retval;
//	}
//	
	
//	/**
//	 * Save Folder
//	 * @param f
//	 * @return
//	 */
//	public boolean saveFolder(TblFileFolder f){
//		boolean retval = false;
//		Transaction transaction = null;
//		Session session = HibernateUtil.currentSession();
//		try {
//			transaction = session.beginTransaction();
//			session.saveOrUpdate(f);
//			transaction.commit();
//			HibernateUtil.closeSession();
//			retval = true;
//		} catch (Exception e) {
//			transaction.rollback();
//			e.printStackTrace();
//		} 
//		
//		return retval;		
//	}
//	
	
	/**
	 * Get Group By ID
	 * @param id
	 * @return
	 */
	public TblFileGroup getGroupById(int id){
		TblFileGroup f = null;
		Session session = HibernateUtil.currentSession();
		try{
			f = (TblFileGroup)session.get(TblFileGroup.class, id);
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return f;
	}

	
//	/**
//	 * Get Folder By ID
//	 * @param id
//	 * @return
//	 */
//	public TblFileFolder getFolderById(int folderId){
//		TblFileFolder f = null;
//		Session session = HibernateUtil.currentSession();
//		try{
//			f = (TblFileFolder)session.get(TblFileFolder.class, folderId);
//			
//		}catch (HibernateException e) {
//			e.printStackTrace();
//		}
//		HibernateUtil.closeSession();
//		return f;
//	}
	
	
//	/**
//	 * Delete folder
//	 * @param grpId
//	 * @return
//	 */
//	public boolean deleteFolder(int id){
//		boolean retval = false;
//
//		Transaction transaction = null;
//		Session session = HibernateUtil.currentSession();
//		try {
//			transaction = session.beginTransaction();
//
//			TblFileFolder c = (TblFileFolder)session.get(TblFileFolder.class, id);
//			session.delete(c);
//			
//			transaction.commit();
//			retval = true;
//		} catch (Exception e) {
//			transaction.rollback();
//			e.printStackTrace();
//		} 
//		HibernateUtil.closeSession();
//		return retval;		
//	}
	
//	@SuppressWarnings("unchecked")
//	public ArrayList<TblMasterRes> folderList(String searchTxt)
//	{
//		ArrayList<TblMasterRes> retval = null;
//		ArrayList<TblFileFolder> resList = null;
//		Session session = HibernateUtil.currentSession();
//		try {
//			Query  query =  session.createQuery("FROM TblFileFolder AS f ORDER BY folderName");
//			if(searchTxt != null && searchTxt.length() > 0){
//				searchTxt = searchTxt.replace(' ', '%');
//				query =  session.createQuery("FROM TblFileFolder AS f WHERE folderName LIKE :searchTxt ORDER BY folderName").setText("searchTxt", "%"+searchTxt+"%");
//			}
//			resList = (ArrayList<TblFileFolder>) query.list();
//
//			retval = new ArrayList<TblMasterRes>();
//			for(TblFileFolder f: resList){
//				TblMasterRes res = new TblMasterRes();
//				res.setMstName(f.getFolderName());
//				res.setParentName(f.getTblFileGroup().getGroupName());
//				res.setId(f.getFolderId());
////				if(f.getTblFileGroup() != null)
////					res.setDtlCount(f.getTblFileGroup().);
////				else
////					res.setDtlCount(0);
//				retval.add(res);
//			}
//		}catch (HibernateException e) {
//			e.printStackTrace();
//		}
//		HibernateUtil.closeSession();
//		return retval;
//	}
//	

	
	@SuppressWarnings("unchecked")
	public ArrayList<TblFileGroup> getGroupList()
	{
		ArrayList<TblFileGroup> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblFileGroup AS f ORDER BY groupName");
			resList = (ArrayList<TblFileGroup>) query.list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return resList;
	}
	
	
		@SuppressWarnings("unchecked")
		public ArrayList<TblFileGroup> search(String searchTxt) {
			ArrayList<TblFileGroup> groupList = null;
			Session session = HibernateUtil.currentSession();
			try {
				
				Query query = session.createQuery("FROM TblFileGroup AS gd WHERE 1 = 1 ");
				if (searchTxt != null && !searchTxt.equals("")) {
					searchTxt = searchTxt.replace(' ', '%');
				
					query=session.createQuery("FROM TblFileGroup AS gd WHERE upper(gd.groupName) LIKE :searchTxt ")
										.setString("searchTxt", "%"+searchTxt.toUpperCase()+"%");
				}
				groupList = (ArrayList<TblFileGroup>) query.list();
	
			} catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return groupList;
		}
	
		public long getgroupCountBygroupname(String groupName) {
			long cnt = 0;
			StringBuffer st = new StringBuffer();
			
			st.append("SELECT count(*) FROM TblFileGroup AS g WHERE UPPER(g.groupName) = :groupName");
			
			Session session = HibernateUtil.currentSession();
			try {
				Query query = session.createQuery(st.toString());
				
				query.setString("groupName", groupName.toUpperCase());
				cnt = (Long) query.uniqueResult();
	
			} catch (HibernateException e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return cnt;
		}
		
	public boolean addGroup(TblFileGroup filegroup){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
	
			transaction = session.beginTransaction();
	
			
			session.saveOrUpdate(filegroup);
			
			transaction.commit();
			
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	public TblFileGroup getGroupBygroupname(String groupName) {
		TblFileGroup filegroup = null;
		StringBuffer st = new StringBuffer();
		st.append("FROM TblFileGroup AS st WHERE UPPER(st.groupName) = :groupName ");
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());
			
			query.setString("groupName", groupName.toUpperCase());
			filegroup = (TblFileGroup) query.uniqueResult();
	
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return filegroup;
	}
	
	
	
	public long getgroupCountBygroup(String groupName) {
		long cnt = 0;
		StringBuffer st = new StringBuffer();
		
		st.append("SELECT count(*) FROM TblFileGroup AS g WHERE UPPER(g.groupName) = :groupName");
		
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());
			
			query.setString("groupName", groupName.toUpperCase());
			cnt = (Long) query.uniqueResult();
	
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return cnt;
	}
	
	public boolean updateGroup(TblFileGroup filegroup){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
	
			transaction = session.beginTransaction();
			session.merge(filegroup);
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
	ArrayList<TblFileGroup> resList = null;
	Session session = HibernateUtil.currentSession();
	try {
		Query  query =  session.createQuery("FROM TblFileGroup AS f ORDER BY groupId");
		if(searchTxt != null && searchTxt.length() > 0){
			searchTxt = searchTxt.replace(' ', '%');
			query =  session.createQuery("FROM TblFileGroup AS f WHERE upper(groupName) LIKE :searchTxt ORDER BY groupId").setText("searchTxt", "%"+searchTxt.toUpperCase()+"%");
		}
		resList = (ArrayList<TblFileGroup>) query.list();

		retval = new ArrayList<TblMasterRes>();
		for(TblFileGroup f: resList){
			TblMasterRes res = new TblMasterRes();
			
			res.setMstName(f.getGroupName());
		
			res.setId(f.getGroupId());
			
			retval.add(res);
		}
		
			
		
	}catch (HibernateException e) {
		e.printStackTrace();
	}
	HibernateUtil.closeSession();
	return retval;
}



public boolean delete(int groupId) throws TransException{
	boolean retval = false;

	Transaction transaction = null;
	Session session = HibernateUtil.currentSession();
	try {
		transaction = session.beginTransaction();
		
		TblFileGroup a = (TblFileGroup)session.get(TblFileGroup.class, groupId);
		session.delete(a);
		
		transaction.commit();
		retval = true;
	}catch (HibernateException e) {
		transaction.rollback();
		e.printStackTrace();
		throw new TransException("FileGroup already in use ....!!!");
	} 
	HibernateUtil.closeSession();
	return retval;		
}

}
