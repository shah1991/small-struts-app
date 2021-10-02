package net.slide.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import net.slide.pojo.TblChklst;
import net.slide.pojo.TblChklstTopic;
import net.slide.pojo.TblRole;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblChklstRes;
import net.slide.pojores.TblMasterRes;
import net.slide.pojores.TblUserRes;
import net.slide.util.HibernateUtil;
import net.slide.util.TransException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;



public class UserDao extends BaseDao {
	
	/**
	 * User list to display based without search string
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<TblUser> listLookup()
	{
		ArrayList<TblUser> lookupList = null;
		Session session = HibernateUtil.currentSession();
		try{
			lookupList =(ArrayList<TblUser>) session.createQuery("FROM TblUser AS u INNER JOIN FETCH u.tblRole WHERE u.id <> 0 order by u.userLogin ").list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return lookupList;
	}
	
	/**
	 * User list to display based role id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<TblUser> listLookup(TblRole tblRole)
	{
		ArrayList<TblUser> lookupList = null;
		Session session = HibernateUtil.currentSession();
		try{
			lookupList =(ArrayList<TblUser>) session.createQuery("FROM TblUser AS u INNER JOIN FETCH u.tblRole WHERE u.tblRole = :role")
												.setEntity("role", tblRole)								
												.list();
		}catch (HibernateException e) {
			e.printStackTrace();
			
		}
		HibernateUtil.closeSession();
		return lookupList;
	}
	
	
	/**
	 * User list based on search string
	 * @param search
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<TblUser> listLookup(String search)
	{
		ArrayList<TblUser> lookupList = null;
		Session session = HibernateUtil.currentSession();
		try{
			lookupList =(ArrayList<TblUser>) session.createQuery("FROM TblUser AS u " +
					"INNER JOIN FETCH u.tblRole " +
					"WHERE u.id <> 1 AND UPPER(u.userName||u.userLogin) LIKE '%" + search.toUpperCase() + "%' " +
					"ORDER BY u.userLogin")
					.list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return lookupList;
	}

	/**
	 * Login authentication for the Store portal
	 * @param loginId
	 * @param loginPwd
	 * @return
	 */
	public TblUser authUser(String loginId, String loginPwd){
		TblUser retval = null;
		Session session = HibernateUtil.currentSession();
		try{
			//new UserDao().saveUser(new User());
			retval = (TblUser)session.createQuery("FROM TblUser AS u " +
													"INNER JOIN FETCH u.tblRole " +
													" WHERE UPPER(u.userLogin)=? AND u.userPwd=md5(?) ")
						.setString(0,loginId.toUpperCase())
						.setString(1, loginPwd)
						.uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	/**
	 * Login authentication for the Store portal
	 * @param loginId
	 * @param loginPwd
	 * @return
	 */
	public TblUser authUser(String loginId, String loginPwd,String dob){
		TblUser retval = null;
		Session session = HibernateUtil.currentSession();
		try{
			//new UserDao().saveUser(new User());
			retval = (TblUser)session.createQuery("FROM TblUser AS u " +
													"INNER JOIN FETCH u.tblRole " +
													" WHERE UPPER(u.userLogin)=? AND u.userPwd=md5(?) AND TO_CHAR(u.userDob,'DDMMYYYY')=? ")
						.setString(0,loginId.toUpperCase())
						.setString(1, loginPwd)
						.setString(2, dob)
						.uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	/**
	 * Get user record by user id
	 * @param id
	 * @return
	 */
	public TblUser getUserById(long id){
		TblUser retval = null;
		Session session = HibernateUtil.currentSession();
		try{
			retval = (TblUser)session.createQuery("FROM TblUser AS u " +
												  "INNER JOIN FETCH u.tblRole " +
												  "WHERE u.id=:id")
								.setLong("id", id)
								.uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}

	/**
	 * Get User count for given login id for duplicate check
	 * @param userLogin
	 * @return
	 */
	public long getUserCountByUserId(String userLogin) {
		long cnt = 0;
		StringBuffer st = new StringBuffer();
		
		st.append("SELECT count(g.id) FROM TblUser AS g WHERE UPPER(g.userLogin) = :userLogin ");
		
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());
			
			query.setString("userLogin", userLogin.toUpperCase());
			cnt = (Long) query.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return cnt;
	}
	
	/**
	 * Get User record by user name
	 * @param lgnid
	 * @return
	 */
	public TblUser getUserByName(String lgnid){
		TblUser retval = null;
		Session session = HibernateUtil.currentSession();
		try{
			retval = (TblUser)session.createQuery("FROM TblUser AS u " +
							"INNER JOIN FETCH u.tblRole " +
							"WHERE UPPER(u.userLogin)=? ")
							.setString(0,lgnid.toUpperCase())
							.uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}

	/**
	 * Check for duplicate user on modify
	 * @param usrid
	 * @param lgnid
	 * @return
	 */
	public TblUser getSameUser(Long usrid, String lgnid){
		TblUser retval = null;
		Session session = HibernateUtil.currentSession();
		try{
			retval = (TblUser)session.createQuery("FROM TblUser AS u WHERE UPPER(u.lgnid)=:lgnid AND u.usrid <> :usrid ")
							.setString("lgnid",lgnid.toUpperCase())
							.setLong("usrid",usrid)
							.uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}


	/**
	 * Save new record
	 * @param c
	 * @return
	 */
	public boolean saveUser(TblUser c){
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

	/**
	 * User merge on update to avoid the problem of existing record issue
	 * @param c
	 * @return
	 */
	public boolean updateUser(TblUser c){
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
	
	/**
	 * Delete list of users record except root
	 * @param usridList
	 * @return
	 * @throws TransException 
	 */
	public boolean deleteUsers(Long usridList[]) throws TransException{
		boolean retval = false;

		if(usridList == null || usridList.length == 0)
			return retval;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			for(Long cid : usridList){
				
				TblUser c = (TblUser)session.get(TblUser.class, cid.intValue());
				session.delete(c);
			}
			transaction.commit();
			retval = true;
		}  catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			throw new TransException("User already in use ....!!!");
		} 
		HibernateUtil.closeSession();
		return retval;		
	}

	/**
	 * Delete user by given user record
	 * @param c
	 * @return
	 */
	public boolean deleteUser(TblUser c){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			c = (TblUser)session.get(TblUser.class, c.getUserLogin());
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
	 * Change User password
	 * @param userId
	 * @param lgnpwd
	 * @return
	 */
	public boolean changePassword(int userId, String lgnpwd){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			TblUser c = (TblUser)session.get(TblUser.class, userId);
			c.setUserPwd(lgnpwd);
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

	public boolean forceChangePassword(int userId, String lgnpwd){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			TblUser c = (TblUser)session.get(TblUser.class, userId);
			c.setUserPwd(lgnpwd);
                        c.setPasswordForce("N");
                        DateTime now = new DateTime();
                        DateTime tomorrow = now.plusDays( 90 );
                        Date expirydate = tomorrow.toDate();    
                        c.setExpiryDate(expirydate);
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
        
	 /**
	* Get Roles list for main with search string
	* @param searchTxt
	* @return
	*/

	@SuppressWarnings("unchecked")
	public ArrayList<TblMasterRes> roleList(String searchTxt)
	{
	ArrayList<TblMasterRes> retval = null;
	ArrayList<TblRole> resList = null;
	Session session = HibernateUtil.currentSession();
	try {
	Query query = session.createQuery("FROM TblRole AS f ORDER BY roleCd");
	if(searchTxt != null && searchTxt.length() > 0){
	searchTxt = searchTxt.replace(' ', '%');
	query = session.createQuery("FROM TblRole AS f WHERE UPPER (roleCd||roleName) LIKE :searchTxt ORDER BY roleCd").setText("searchTxt", "%"+searchTxt.toUpperCase()+"%");
	}
	resList = (ArrayList<TblRole>) query.list();

	retval = new ArrayList<TblMasterRes>();
	for(TblRole f: resList){
	TblMasterRes res = new TblMasterRes();
	res.setMstName(f.getRoleName());
	res.setParentName(f.getRoleCd());
	res.setId(f.getRoleId());
	if(f.getTblUsers() != null)
	res.setDtlCount(f.getTblUsers().size());
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
	
	
	/**
	 * Get Role by role id
	 * @param id
	 * @return
	 */
	public TblRole getRoleById(long id){
		TblRole retval = null;
		Session session = HibernateUtil.currentSession();
		try{
			retval = (TblRole)session.createQuery("FROM TblRole AS r WHERE r.id=:id")
								.setLong("id", id)
								.uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	public TblRole getRoleByCode(String roleCd){
		TblRole retval = null;
		Session session = HibernateUtil.currentSession();
		try{
			retval = (TblRole)session.createQuery("FROM TblRole AS r WHERE r.roleCd=:roleCd")
								.setString("roleCd", roleCd)
								.uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	
	/**
	 * Get Role count for given login id for duplicate check
	 * @param userLogin
	 * @return
	 */
	public long getRoleCountByCd(String roleCd) {
		long cnt = 0;
		StringBuffer st = new StringBuffer();
		
		st.append("SELECT count(r.id) FROM TblRole AS r WHERE UPPER(r.roleCd) = :roleCd");
		
		Session session = HibernateUtil.currentSession();
		try {
			Query query = session.createQuery(st.toString());
			
			query.setString("roleCd", roleCd.toUpperCase());
			cnt = (Long) query.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return cnt;
	}

	/**
	 * Save new record
	 * @param r
	 * @return
	 */
	public boolean saveRole(TblRole r){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(r);
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
	 * User merge on update to avoid the problem of existing record issue
	 * @param r
	 * @return
	 */
	public boolean updateRole(TblRole r){
		boolean retval = false;
		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			session.merge(r);
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
	 * List all roles in the system
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<TblRole> listRoles()
	{
		ArrayList<TblRole> lookupList = null;
		Session session = HibernateUtil.currentSession();
		try{
			lookupList =(ArrayList<TblRole>) session.createQuery("FROM TblRole AS r ORDER BY r.roleCd").list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return lookupList;
	}
	
	
	
	/**
	 * List all roles in the system
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<TblRole> roleList()
	{
		ArrayList<TblRole> lookupList = null;
		Session session = HibernateUtil.currentSession();
		try{
			lookupList =(ArrayList<TblRole>) session.createQuery("FROM TblRole AS r WHERE roleId!=0 ORDER BY r.roleCd").list();
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return lookupList;
	}
	/**
	 * Delete list of users record except root
	 * @param roleidList
	 * @return
	 * @throws TransException 
	 */
	public boolean deleteRoles(int roleidList[]) throws TransException{
		boolean retval = false;

		if(roleidList == null || roleidList.length == 0)
			return retval;

		Transaction transaction = null;
		Session session = HibernateUtil.currentSession();
		try {
			transaction = session.beginTransaction();
			for(int cid : roleidList){
				if(cid == 1) continue;
				TblRole c = (TblRole)session.get(TblRole.class, cid);
				session.delete(c);
			}
			transaction.commit();
			retval = true;
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			throw new TransException("Role already in use ....!!!");
		} 
		HibernateUtil.closeSession();
		return retval;		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<TblUserRes> getUserDetailsAll_old()
	{	
		ArrayList<TblUserRes> retval = null;
		Session session = HibernateUtil.currentSession();
		ArrayList<TblUser> resList = null;
		try {
	
			Query  query =  session.createQuery("FROM TblUser u where UPPER(u.userLogin) LIKE 'FM%' ORDER BY u.siteId ");
			
			resList = (ArrayList<TblUser>) query.list();
		    retval = new ArrayList<TblUserRes>();
		    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		
		    for(TblUser u: resList){

		    	TblUserRes res=new TblUserRes();
		    	
		    	int storeId=u.getSiteId();
		    	if (storeId != 0) {
					StoreDao storeDao=new StoreDao();
					TblStore s=storeDao.getStore(storeId);				
					res.setStoreCode(s.getStoreCode()==null ? "" : s.getStoreCode());		    		
		    	} else {
		    		res.setStoreCode("0");
		    	}
		    	
		    	res.setUserName(u.getUserName()==null ? "" : u.getUserName());
		    	res.setNric(u.getNric()==null ? "" : u.getNric());
		    	res.setUserMobile(u.getUserMobile()==null ? "" : u.getUserMobile());
		    	res.setUserEmail(u.getUserEmail()==null ? "" : u.getUserEmail());
		    	res.setFranchiseeStartDate(u.getFranchiseeStartDate()==null ? "" : formatter.format(u.getFranchiseeStartDate()));
		    	res.setFranchiseeExpiryDate(u.getFranchiseeExpiryDate()==null ? "" : formatter.format(u.getFranchiseeExpiryDate()));
		    	res.setFranchiseeDuration(u.getFranchiseeDuration()==null ? "" : u.getFranchiseeDuration());
		    	res.setFranchiseeScheme(u.getFranchiseeScheme()==null ? "" : u.getFranchiseeScheme());
		    	res.setGender(u.getGender()==null ? "" : u.getGender());
		    	res.setRcbName(u.getRcbName()==null ? "" : u.getRcbName());
		    	res.setRcbNo(u.getRcbNo()==null ? "" : u.getRcbNo());
		    	res.setGstNo(u.getGstNo()==null ? "" : u.getGstNo());
		    	res.setUserDoj(u.getUserDoj()==null ? "" : formatter.format(u.getUserDoj()));
		    	res.setUserDob(u.getUserDob()==null ? "" : formatter.format(u.getUserDob()));			
		    	res.setUserRemarks(u.getUserRemarks()==null ? "" : u.getUserRemarks());
			
		    	retval.add(res);
		    }
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TblUserRes> getUserDetailsAll()
	{	
		ArrayList<TblUserRes> retval = null;
		Session session = HibernateUtil.currentSession();		
		ArrayList<Object[]> resList = null;
		
		try {
			Query query = session.createSQLQuery("SELECT store.store_code AS store_code, user_name, nric, user_mobile, user_email, franchisee_start_date," +
					"franchisee_expiry_date, franchisee_duration, franchisee_scheme, gender, rcb_name, rcb_no, gst_no," +
					"user_doj, user_dob, user_remarks" +
					" FROM tbl_user AS usr" +
					" LEFT JOIN tbl_store AS store ON (store.store_id = usr.site_id)" +
					" ORDER BY store_code");
			
			resList = (ArrayList<Object[]>) query.list();
			
		    retval = new ArrayList<TblUserRes>();
		    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		    
			for(Object[] val : resList){
				TblUserRes res=new TblUserRes();
				
				res.setStoreCode((String)val[0]);//store_code
				res.setUserName((String)val[1]);//user_name
				res.setNric((String)val[2]);//NRIC
				res.setUserMobile((String)val[3]);//user_mobile
				res.setUserEmail((String)val[4]);//user_email
				res.setFranchiseeStartDate(val[5]==null ? "" : formatter.format(val[5]));//franchisee_start_date				
				res.setFranchiseeExpiryDate(val[6]==null ? "" : formatter.format(val[6]));//franchisee_end_date
				res.setFranchiseeDuration((String)val[7]);//franchisee_duration
				res.setFranchiseeScheme((String)val[8]);//franchisee_scheme
				res.setGender((String)val[9]);//gender
				res.setRcbName((String)val[10]);//rcb_name
				res.setRcbNo((String)val[11]);//rcb_no
				res.setGstNo((String)val[12]);//gst_no
				res.setUserDoj(val[13]==null ? "" : formatter.format(val[13]));//user_doj
				res.setUserDob(val[14]==null ? "" : formatter.format(val[14]));//user_dob
				res.setUserRemarks((String)val[15]);//user_remarks
				
				retval.add(res);
			}
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return retval;
	}	
}
