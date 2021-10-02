package net.slide.dao;

import java.util.ArrayList;
import java.util.List;

import net.slide.bean.SearchTO;
import net.slide.pojo.TblArea;
import net.slide.pojo.TblBanner;
import net.slide.pojo.TblRole;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;
import net.slide.pojores.TblStoreRes;
import net.slide.util.HibernateUtil;
import net.slide.util.TransException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StoreDao {
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> search(SearchTO search) {
		ArrayList<Object[]> lookupList = null;
		boolean present = false;
		StringBuffer st = new StringBuffer();
		
		//st.append("FROM TblStore AS st WHERE 1 = 1 ");
		st.append("FROM TblStore AS st INNER JOIN FETCH st.tblArea WHERE 1 = 1 ");
		
		if (search.getStoreCode() != null && !search.getStoreCode().equals("")) {
			st.append("AND UPPER(st.storeCode) LIKE :storeCode ");
			present = true;
		}
		if (present && search.getStoreName() != null && !search.getStoreName().equals("")) {
			st.append("OR UPPER(st.storeName) LIKE :storeName ");
		} else if (search.getStoreName() != null && !search.getStoreName().equals("")) {
			st.append("AND UPPER(st.storeName) LIKE :storeName ");
			present = true;
		}

		if (present && search.getStoreLocation() != null && !search.getStoreLocation().equals("")) {
			st.append("OR UPPER(st.storeLocation) LIKE :storeLocation ");
		} else if (search.getStoreLocation() != null && !search.getStoreLocation().equals("")) {
			st.append("AND UPPER(st.storeLocation) LIKE :storeLocation ");
		}

		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());

			if (search.getStoreCode() != null && !search.getStoreCode().equals("")) {
				query.setString("storeCode", search.getStoreCode().toUpperCase());
			}
			if (search.getStoreName() != null && !search.getStoreName().equals("")) {
				query.setString("storeName", search.getStoreName().toUpperCase());
			}
			if (search.getStoreLocation() != null && !search.getStoreLocation().equals("")) {
				query.setString("storeLocation", search.getStoreLocation().toUpperCase());
			}
			lookupList = (ArrayList<Object[]>) query.setMaxResults(250).list();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return lookupList;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblStore> getAllStores() {
		ArrayList<TblStore> storeList = null;
		StringBuffer st = new StringBuffer();
		
		st.append("FROM TblStore AS st WHERE 1 = 1 ORDER BY storeCode");
		
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());

			storeList = (ArrayList<TblStore>) query.list();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return storeList;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblStore> search(String searchTxt) {
		ArrayList<TblStore> storeList = null;
		Session session = HibernateUtil.currentSession();
		try {
			
			Query query = session.createQuery("FROM TblStore AS st INNER JOIN FETCH st.tblArea WHERE 1 = 1  ORDER BY st.storeCode");
			if (searchTxt != null && !searchTxt.equals("")) {
				searchTxt = searchTxt.replace(' ', '%');
/*			
				query=session.createQuery("FROM TblStore AS st WHERE upper(st.storeName||st.storeCode) LIKE :searchTxt ORDER BY storeCode")
									.setString("searchTxt", "%"+searchTxt.toUpperCase()+"%");
*/				
				query=session.createQuery("FROM TblStore AS st INNER JOIN FETCH st.tblArea WHERE upper(st.storeName||st.storeCode) LIKE :searchTxt ORDER BY st.storeCode")
				.setString("searchTxt", "%"+searchTxt.toUpperCase()+"%");				
				
			}
			storeList = (ArrayList<TblStore>) query.list();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return storeList;
	}
	
	//Satheesh changed on 10/06/2013 (change the condition tblArea.areaId = :areaId instead of id= :areaId)
	@SuppressWarnings("unchecked")
	public ArrayList<TblStore> getStoresByArea(int areaId) {
		ArrayList<TblStore> storeList = null;
		Session session = HibernateUtil.currentSession();
		try {
			
			Query query = session.createQuery("FROM TblStore AS st WHERE tblArea.areaId = :areaId").setInteger("areaId", areaId);
			storeList = (ArrayList<TblStore>) query.list();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return storeList;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblStore> getStoresList(int areaId) {
		ArrayList<TblStore> storeList = null;
		Session session = HibernateUtil.currentSession();
		try {
			TblArea a = (TblArea)session.get(TblArea.class, areaId);
			Query query = session.createQuery("FROM TblStore AS st WHERE st.tblArea = :areaId").setEntity("areaId", a);
			storeList = (ArrayList<TblStore>) query.list();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return storeList;
	}
	
	
	public TblStore getStoreById(int id){
		TblStore store = null;
		Session session = HibernateUtil.currentSession();
		try{
			store = (TblStore)session.get(TblStore.class, id);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return store;
	}
	
	
	
	public TblStore getStore(int id){
		
		TblStore store=null;
		Session session = HibernateUtil.currentSession();
		try{
			
		Query query=session.createQuery("FROM TblStore s INNER JOIN FETCH s.tblArea WHERE s.storeId=:storeId");
		query.setInteger("storeId", id);
		store=(TblStore) query.uniqueResult();
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return store;
	}
	
	
	public TblStore getStoreByCode(String storeCode) {
		TblStore store = null;
		StringBuffer st = new StringBuffer();
		
		st.append("FROM TblStore AS st WHERE UPPER(st.storeCode) = :storeCode");
		
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());
			
			query.setString("storeCode", storeCode.toUpperCase());
			store = (TblStore) query.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return store;
	}
	
	public long getStoreCountByCode(String storeCode) {
		long cnt = 0;
		StringBuffer st = new StringBuffer();
		
		st.append("SELECT count(g.id) FROM TblStore AS g WHERE UPPER(g.storeCode) = :storeCode ");
		
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());
			
			query.setString("storeCode", storeCode.toUpperCase());
			cnt = (Long) query.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return cnt;
	}
	
	
	public boolean addStore(TblStore store){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {

			transaction = session.beginTransaction();

			TblBanner tblBanner = (TblBanner)session.get(TblBanner.class, 1);
			store.setTblBanner(tblBanner);
			session.saveOrUpdate(store);
			
			transaction.commit();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	
	public boolean updateStore(TblStore store){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {

			transaction = session.beginTransaction();
			session.merge(store);
		//	updateUserStore(session,store);
			transaction.commit();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	public void updateUserStore(Session session, TblStore store){

		String hql = "update tbl_user set site_id="+store.getStoreId()+" where user_id="+store.getTblUser().getUserId();
		SQLQuery query = session.createSQLQuery(hql);
	  
	    query.executeUpdate();
	}
	
	public boolean deleteStore(int storeId) throws TransException{
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			
			TblStore c = (TblStore)session.get(TblStore.class, storeId);
			session.delete(c);
			
			transaction.commit();
			retval = true;
		}  catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			throw new TransException("Store already in use ....!!!");
		} 
		HibernateUtil.closeSession();
		return retval;		
	}

	/**
	 * Get Area list for main with search string
	 * @param searchTxt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> areaList(String searchTxt)
	{
		ArrayList<TblMasterRes> retval = null;
		ArrayList<TblArea> resList = null;
		Session session = HibernateUtil.currentSession();
		try {
			Query  query =  session.createQuery("FROM TblArea AS f ORDER BY areaCd");
			if(searchTxt != null && searchTxt.length() > 0){
				searchTxt = searchTxt.replace(' ', '%');
				query = session.createQuery("FROM TblArea AS f WHERE upper(areaCd||areaName) LIKE :searchTxt").setText("searchTxt", "%"+searchTxt.toUpperCase()+"%");

			}
			resList = (ArrayList<TblArea>) query.list();

			retval = new ArrayList<TblMasterRes>();
			for(TblArea f: resList){
				TblMasterRes res = new TblMasterRes();
				res.setMstName(f.getAreaName());
				res.setParentName(f.getAreaCd());
				res.setId(f.getAreaId());
				if(f.getTblStores() != null)
					res.setDtlCount(f.getTblStores().size());
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

	//satheesh
	@SuppressWarnings("unchecked")
	public ArrayList<TblArea> listArea() {
		ArrayList<TblArea> areaList = null;
		Session session = HibernateUtil.currentSession();
		try {
			
			Query query = session.createQuery("FROM TblArea AS a ORDER BY areaCd");
			areaList = (ArrayList<TblArea>) query.list();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return areaList;
	}
	
	

	@SuppressWarnings("unchecked")
	public ArrayList<TblStoreRes> listAreaStore() {
		ArrayList<TblArea> areaList = null;
		ArrayList<TblStoreRes> retval = new ArrayList<TblStoreRes>();
		Session session = HibernateUtil.currentSession();

		ArrayList<TblStore> storeList= null;
		try {
			
			Query query = session.createQuery("FROM TblArea AS a ORDER BY a.areaCd ");
			areaList = (ArrayList<TblArea>) query.list();
			for(TblArea area:areaList){
				TblStoreRes res=new TblStoreRes();
				res.setAreaId(area.getAreaId());
				res.setAreaName(area.getAreaName());
				storeList=new ArrayList<TblStore>();
				Query queryStore = session.createQuery("FROM TblStore AS a where a.tblArea=:area AND a.storeStatus='Y' ORDER BY a.storeCode ").setEntity("area", area);
				storeList=(ArrayList<TblStore>)queryStore.list();
				res.setTblStoreList(storeList);
				retval.add(res);
			}
		
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	
	public TblArea getAreaById(int id){
		TblArea tblArea = null;
		Session session = HibernateUtil.currentSession();
		try{
			tblArea = (TblArea)session.get(TblArea.class, id);
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return tblArea;
	}
	
	public TblArea getAreaByCode(String areaCode) {
		TblArea store = null;
		StringBuffer st = new StringBuffer();
		st.append("FROM TblArea AS st WHERE UPPER(st.areaCd) = :areaCode ");
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());
			
			query.setString("areaCode", areaCode.toUpperCase());
			store = (TblArea) query.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return store;
	}
	
	public long getAreaCountByCode(String areaCode) {
		long cnt = 0;
		StringBuffer st = new StringBuffer();
		
		st.append("SELECT count(g.id) FROM TblArea AS g WHERE UPPER(g.areaCd) = :areaCode");
		
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());
			
			query.setString("areaCode", areaCode.toUpperCase());
			cnt = (Long) query.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return cnt;
	}
	
	
	public boolean updateArea(TblArea area){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {

			transaction = session.beginTransaction();
			session.merge(area);
		//	updateUserArea(session,area);
			transaction.commit();
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	
	
	public void updateUserArea(Session session, TblArea area){

		String hql = "update tbl_user set site_id="+area.getAreaId()+" where user_id="+area.getTblUser().getUserId();
		SQLQuery query = session.createSQLQuery(hql);
	    query.executeUpdate();
	}
	public boolean deleteArea(int areaId) throws TransException{
		boolean retval = false;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {

			transaction = session.beginTransaction();
			
			TblArea a = (TblArea)session.get(TblArea.class, areaId);
			session.delete(a);
			
			transaction.commit();
			retval = true;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			throw new TransException("Area already in use ....!!!");
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	public boolean addArea(TblArea area){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {

			transaction = session.beginTransaction();

			TblBanner tblBanner = (TblBanner)session.get(TblBanner.class, 1);
			area.setTblBanner(tblBanner);
			session.saveOrUpdate(area);
			
			transaction.commit();
			
			retval = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
		return retval;		
	}
	
	
	public void addStoreUpdate(int storeId, TblUser t){
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {

			transaction = session.beginTransaction();
			String hql = "update tbl_store set store_manager_id="+t.getUserId()+" where store_id="+storeId;
			
			SQLQuery query = session.createSQLQuery(hql);
		  
		    query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
	}
	public void addAreaUpdate(int areaId, TblUser t){
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {

			transaction = session.beginTransaction();

			String hql = "update tbl_area set area_manager_id="+t.getUserId()+" where area_id="+areaId;
			SQLQuery query = session.createSQLQuery(hql);
		    query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		HibernateUtil.closeSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getListOfStoreCodesUnderAreaManager(int user_id) {
		Session session = HibernateUtil.currentSession();
		//String sql = "SELECT s.store_code FROM tbl_store s INNER JOIN tbl_area a ON s.area_id = a.area_id INNER JOIN tbl_user u ON u.user_id = a.area_manager_id AND u.user_id = :user_id";
		String sql = "SELECT s.store_code FROM tbl_store s INNER JOIN tbl_area a ON s.area_id = a.area_id INNER JOIN tbl_user u ON u.site_id = a.area_id AND u.user_id = :user_id";

		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("user_id", user_id);
		
		List<String> allStoreCodes = new ArrayList<String>();
		
		List<String> origStoreCodes = query.list();
		
		for (String string : origStoreCodes) {
			String concatStoreCode = StringUtils.substring(string, 0, 3);
			String sqlToGetStoreCodes = "SELECT DISTINCT(store_code) FROM tbl_file_share WHERE store_code LIKE '" + concatStoreCode + "%'";
			SQLQuery queryGetStoreCodes = session.createSQLQuery(sqlToGetStoreCodes);
			allStoreCodes.addAll(queryGetStoreCodes.list());
		}
		allStoreCodes.addAll(origStoreCodes);
		return allStoreCodes;
	}
	
}



