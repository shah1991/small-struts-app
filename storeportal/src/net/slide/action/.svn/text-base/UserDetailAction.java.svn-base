package net.slide.action;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import net.slide.dao.StoreDao;
import net.slide.dao.UserDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblArea;
import net.slide.pojo.TblRole;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.slide.dao.PassHistoryDao;
import net.slide.pojo.TblPassHistory;
import org.joda.time.DateTime;


@SuppressWarnings("serial")
public class UserDetailAction extends ActionSupport  {
	private boolean updateMode;

	private long id;
	private String userName;
	private String userLogin;
	private String userPwd;
	private String store;
	private String usradm;
	private Integer userRole;
	private String active;
	private String readOnly;
	private String userDob;
	private String nric;
	private int areaId;
	private int storeId;
	private String lgnpwdn = "";
	private String lgnpwdc = "";
	private String lgnpwdo = "";
	private String userPhone;
	private String userEmail;
	private String userCfmPwd;
	private ArrayList<TblStore> storeList=new ArrayList<TblStore>();
	private String franchiseeExpiryDate;	
	private String franchiseeStartDate;
	private String rcbName;
	private String rcbNo;
	private String gstNo;
	private String gender;
	private String userMobile;
	private String userRemarks;
	private String userDoj;
	private String franchiseeDuration;
	private String franchiseeScheme;
        
        private Date expiryDate;
	private Date loginDate;
	private Integer loginError;
        private String passwordForce;        
	
	public String add() throws SessionExpiredException {
		try {
			getUserBean();
			readOnly = "N";
			active = "Y";
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happened..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return INPUT;
	}

	public String view() throws SessionExpiredException {
		
		try {
			UserDao usrDao = new UserDao();
			updateMode = true;
			getUserBean();
			
			TblUser u = usrDao.getUserById(id);
			
			nric=u.getNric();
			userPhone=u.getUserPhone();
			userEmail=u.getUserEmail();
			userLogin =u.getUserLogin();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			userDob = (u.getUserDob()==null) ? "" : formatter.format(u.getUserDob());
			userName = u.getUserName();
			userPwd = u.getUserPwd();
			userCfmPwd=u.getUserPwd();
			userRole = u.getTblRole().getRoleId();
			active = u.getUserStatus();
			readOnly=u.getUserAdmin();
			id = u.getUserId();
			if(userRole==2){
				areaId=u.getSiteId();
			}else if((userRole==1)||(userRole==4)){
				storeId=u.getSiteId();
				StoreDao storeDao=new StoreDao();
				TblStore s=storeDao.getStore(storeId);
				areaId=s.getTblArea().getAreaId();
				setStoreList(storeDao.getStoresList(areaId));
			}
			
			rcbName = u.getRcbName();
			rcbNo = u.getRcbNo();
			gstNo = u.getGstNo();
			gender = u.getGender();
			userMobile = u.getUserMobile();
			userRemarks = u.getUserRemarks();
			userDoj = (u.getUserDoj()==null) ? "" : formatter.format(u.getUserDoj());

			formatter = new SimpleDateFormat("yyyy-MM-dd");			
			franchiseeExpiryDate = (u.getFranchiseeExpiryDate()==null) ? "" : formatter.format(u.getFranchiseeExpiryDate());			
			franchiseeStartDate = (u.getFranchiseeStartDate() == null) ? "" : formatter.format(u.getFranchiseeStartDate());
			franchiseeDuration = u.getFranchiseeDuration();
			franchiseeScheme = u.getFranchiseeScheme();
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		
		return INPUT;
	}

	
	

	public String userview() throws SessionExpiredException {
		
		try {
			UserDao usrDao = new UserDao();
			updateMode = true;
			TblUser curusr = getUserBean();
			TblUser u = usrDao.getUserById(curusr.getUserId());
			
			nric=u.getNric();
			userPhone=u.getUserPhone();
			userEmail=u.getUserEmail();
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			userDob=(u.getUserDob()==null) ? "" : formatter.format(u.getUserDob());
			userName = u.getUserName();
			id = u.getUserId();
			
			franchiseeExpiryDate = (u.getFranchiseeExpiryDate()==null) ? "" : formatter.format(u.getFranchiseeExpiryDate());
			
			userDoj=(u.getUserDoj()==null) ? "" : formatter.format(u.getUserDoj());
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "userview";
		}
		
		return "userview";
	}
	
	
	
	public String useredit() throws SessionExpiredException {
		
		
		try {
			UserDao usrDao = new UserDao();
				
			updateMode = true;
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = getUserBean();
			TblUser u = usrDao.getUserById(id);
			u.setNric(nric);
			u.setUserPhone(userPhone);
			u.setUserEmail(userEmail);
			
			try {
				u.setUserDob(new SimpleDateFormat("dd-MMM-yyyy").parse(userDob));
				u.setFranchiseeExpiryDate(new SimpleDateFormat("dd-MMM-yyyy").parse(franchiseeExpiryDate));
				u.setFranchiseeStartDate(new SimpleDateFormat("dd-MM-yyyy").parse(franchiseeStartDate));
				u.setUserDoj(new SimpleDateFormat("dd-MMM-yyyy").parse(userDoj));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			u.setUserName(userName);
			
			u.setRcbName(rcbName);
			u.setRcbNo(rcbNo);
			u.setGstNo(gstNo);
			u.setGender(gender);
			u.setUserMobile(userMobile);
			u.setUserRemarks(userRemarks);
			u.setFranchiseeDuration(franchiseeDuration);
			u.setFranchiseeScheme(franchiseeScheme);
			
			u.setUpdatedBy(curusr.getUserLogin());
			u.setUpdatedOn(new Date());
			 
			boolean res = usrDao.updateUser(u);
		
			if ( res == false) {
				addActionError("Update failed..!!!");
			}else{

				addActionMessage("Update Successfully !!!!");
				
				session.remove("update");
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "userhome";
		}
		return "userhome";
	}
	
	public String create() throws SessionExpiredException {
	
		String retval = INPUT;
		DateFormat formatter = null;
		
		try {
			UserDao usrDao = new UserDao();
				
			TblUser curusr = getUserBean();
			TblUser u = new TblUser();
			
			long cnt = 0;
			
			if(userLogin != null) {
				cnt = usrDao.getUserCountByUserId(userLogin);
			}
			
			if(cnt > 0) {
				addActionError("User with same User ID already exists, choose new user ID..!!!");
				return retval;
			}
                       // check history
                        PassHistoryDao passHistoryDao = new PassHistoryDao();
                        TblPassHistory tblPassHistory = new TblPassHistory();
                        String nlgnpwdmd5 = MD5(lgnpwdn);
                        Boolean result = passHistoryDao.getSearchList(userLogin,nlgnpwdmd5);
                        if (result){                                                
                                        tblPassHistory.setUsername(userLogin);
                                        tblPassHistory.setPassword(nlgnpwdmd5);
                                        tblPassHistory.setCreatedDate(new Date());
                                        passHistoryDao.saveTblPassHistory(tblPassHistory);
                        }else{
                            addActionError("Unable to reuse last 4 used passwords.");
                            return retval;                                
                        }
                        // end check
                        		
			TblRole tblRole = null;
			if(userRole != null ){
				tblRole = usrDao.getRoleById(userRole);
			}
			// getStoresByArea(int areaId) {
			u.setUserLogin(userLogin);
			u.setUserName(userName);
			
			String md5userPwd = MD5(userPwd);
			u.setUserPwd(md5userPwd);
			
			u.setTblRole(tblRole);
			u.setUserStatus(active);
			u.setNric(nric);
			u.setUserPhone(userPhone);
			u.setUserEmail(userEmail);
			try {
				u.setUserDob(new SimpleDateFormat("dd-MMM-yyyy hh:mm").parse(userDob));
				//u.setFranchiseeExpiryDate(new SimpleDateFormat("dd-MMM-yyyy hh:mm").parse(franchiseeExpiryDate));
				u.setUserDoj(new SimpleDateFormat("dd-MMM-yyyy hh:mm").parse(userDoj));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			//formatter = new SimpleDateFormat("yyyy-MM-dd");
			//u.setFranchiseeExpiryDate((Date) formatter.parse(franchiseeExpiryDate));
					
			if(userRole != null && userRole == 0) {
				u.setSiteId(0);
				u.setUserAdmin("Y");
			} else if(userRole != null && userRole == 1) {
				u.setSiteId(storeId);
				u.setUserAdmin("N");
			}else if(userRole != null && userRole == 4) {
				u.setSiteId(storeId);
				u.setUserAdmin("N");
			}else if(userRole != null && userRole == 2) {
				u.setSiteId(areaId);
				u.setUserAdmin("N");
			}else if(userRole != null && userRole == 3) {
				u.setSiteId(1);
				u.setUserAdmin("N");
			}else{
			
				u.setUserAdmin("N");
			}
			
                        if(userRole == 4){
                            formatter = new SimpleDateFormat("yyyy-MM-dd");
                            u.setFranchiseeExpiryDate((Date) formatter.parse(franchiseeExpiryDate));
                            u.setFranchiseeStartDate((Date) formatter.parse(franchiseeStartDate));
                            u.setFranchiseeDuration(franchiseeDuration);
			
                        }			
			u.setRcbName(rcbName);
			u.setRcbNo(rcbNo);
			u.setGstNo(gstNo);
			u.setGender(gender);
			u.setUserMobile(userMobile);
			u.setUserRemarks(userRemarks);
			u.setFranchiseeScheme(franchiseeScheme);
                        // forche change pass
                        u.setLoginError(0);                        
			u.setPasswordForce("Y");
			
			boolean res= usrDao.saveUser(u);
			StoreDao storeDao=new StoreDao();
			if ( res == false) {
				addActionError("Add failed..!!!");
			}else{
			
				if(userRole==2){
					storeDao.addAreaUpdate(areaId,u);
				}else if(userRole==1){
					storeDao.addStoreUpdate(storeId,u);
					
				}
			//	area=storeDao.getAreaByCode(areaCode);
				//storeDao.addUpdate(area);
				addActionMessage("Add Successfully !!!!");
				retval = SUCCESS;
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happened..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return retval;
	}
	
	
	
	public String edit() throws SessionExpiredException {
		
		String retval = INPUT;
		DateFormat formatter = null;
		
		try {
			UserDao usrDao = new UserDao();
				
			updateMode = true;
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = getUserBean();
			TblUser u = usrDao.getUserById(id);
			
			if(!u.getUserLogin().trim().equalsIgnoreCase(userLogin.trim())) {
				long cnt = 0;
				
				if(userLogin != null) {
					cnt = usrDao.getUserCountByUserId(userLogin);
				}
				
				if(cnt > 0) {
					addActionError("User with same User ID already exists, choose new user ID..!!!");
					return retval;
			
				}
			}

                /*        String md5userPwd = userPwd; 
                        if(!u.getUserPwd().trim().equalsIgnoreCase(userPwd.trim())) {
                        md5userPwd = MD5(userPwd);
                        }
                        u.setUserPwd(md5userPwd);
                */
			TblRole tblRole = null;
			if(userRole != null ){
				tblRole = usrDao.getRoleById(userRole);
			}

			u.setNric(nric);
			u.setUserPhone(userPhone);
			u.setUserEmail(userEmail);
			u.setUserLogin(userLogin);
			try {
				u.setUserDob(new SimpleDateFormat("dd-MMM-yyyy").parse(userDob));
				u.setUserDoj(new SimpleDateFormat("dd-MMM-yyyy").parse(userDoj));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			//formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			//u.setFranchiseeExpiryDate((Date) formatter.parse(franchiseeExpiryDate));
			
			//u.setFranchiseeExpiryDate(new SimpleDateFormat("dd-MMM-yyyy").parse(franchiseeExpiryDate));			
			
			
			u.setUserName(userName);
			
			//String md5userPwd = MD5(userPwd);
			//u.setUserPwd(md5userPwd);
			
			u.setTblRole(tblRole);
			u.setUserStatus(active);
                        // reset lock
                        if (active.equalsIgnoreCase("Y")){
                            u.setLoginError(0);
                            DateTime now = new DateTime();
                            Date jnow = now.toDate();
                            u.setLoginDate(jnow);
                        }			
			
			if(userRole != null && userRole == 3 && userLogin=="Y") {
				u.setUserAdmin("Y");
			} else if(userRole != null && userRole == 1) {
				u.setSiteId(storeId);
				u.setUserAdmin("N");
			} else if(userRole != null && userRole == 4) {
				u.setSiteId(storeId);
				u.setUserAdmin("N");
			}else if(userRole != null && userRole == 2) {
				u.setSiteId(areaId);
				u.setUserAdmin("N");
			}else if(userRole != null && userRole == 3) {
				u.setSiteId(1);
				u.setUserAdmin("N");
			}else{
			
				u.setUserAdmin("N");
			}
			
			//u.setFranchiseeStartDate(new SimpleDateFormat("dd-MMM-yyyy").parse(franchiseeStartDate));
			if(userRole == 4){
                            formatter = new SimpleDateFormat("yyyy-MM-dd");
                            u.setFranchiseeExpiryDate((Date) formatter.parse(franchiseeExpiryDate));
			
                            u.setFranchiseeStartDate((Date) formatter.parse(franchiseeStartDate));
                            u.setFranchiseeDuration(franchiseeDuration);	
                            u.setFranchiseeScheme(franchiseeScheme);
                        }
                        u.setRcbName(rcbName);
			u.setRcbNo(rcbNo);
			u.setGstNo(gstNo);
			u.setGender(gender);
			u.setUserMobile(userMobile);
			u.setUserRemarks(userRemarks);
			
			u.setUpdatedBy(curusr.getUserLogin());
			u.setUpdatedOn(new Date());
			 
			boolean res = usrDao.updateUser(u);
			StoreDao storeDao=new StoreDao();
			if ( res == false) {
				addActionError("Update failed..!!!");
			}else{

				if(userRole==2){
					storeDao.addAreaUpdate(areaId,u);
				}else if(userRole==1){
					storeDao.addStoreUpdate(storeId,u);
					
				}
				addActionMessage("Update Successfully !!!!");
				retval = SUCCESS;
				session.remove("update");
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happened..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return retval;
	}
	
	
	public String userPwd(){
		 return "userchgPwd";
		}
		
	
	@SkipValidation
	public String changePwd() throws SessionExpiredException {
		
		String retval = "userchgPwd";
		UserDao userDao = new UserDao();
		try {
                    
                        PassHistoryDao passHistoryDao = new PassHistoryDao();
                        TblPassHistory tblPassHistory = new TblPassHistory();
			TblUser user1 = getUserBean();
                        String userlogin = user1.getUserLogin();
			String olgnpwdmd5 = MD5(lgnpwdo);
                        String nlgnpwdmd5 = MD5(lgnpwdn);
			if(!user1.getUserPwd().trim().equals(olgnpwdmd5)) {
				addActionError("Old Password is wrong, Try Again!!!");
				return retval;
			} else if(lgnpwdn.trim().equals(lgnpwdo)) {
				addActionError("Old Password and New Password should not be same, Try Again!!!");
				return retval;
			} else if(lgnpwdn.trim().equals(lgnpwdc.trim())) {
                             Boolean result = passHistoryDao.getSearchList(userlogin,nlgnpwdmd5);
                             if (result){
				boolean res = userDao.changePassword(user1.getUserId(), nlgnpwdmd5);
				
				if(res) {
                                        tblPassHistory.setUsername(userlogin);
                                        tblPassHistory.setPassword(nlgnpwdmd5);
                                        tblPassHistory.setCreatedDate(new Date());
                                        passHistoryDao.saveTblPassHistory(tblPassHistory);  
                                        Map<String, Object> session = ActionContext.getContext().getSession();
                                        TblUser user= userDao.getUserById(user1.getUserId());
                                        session.put("loginUser", user);
					addActionMessage("Password changed successfully..!!!");                                        
					//retval = SUCCESS;
				} else {
					addActionError("Change Password Failed..!!!");
				}
                             }else {
                                    addActionError("Unable to reuse last 4 used passwords..!!!");
                                    return retval;
                            }
			} else {
				addActionError("New Password and Confirm Password must be same..!!!");
				return retval;
			}
				
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "userchgPwd";
		}
		
		
		
		return retval;
		
	}
        
	public String doChangePwd() throws SessionExpiredException {
                String retval = "userdetailchgPwd";
		try {
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happened..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return retval;
	}        
	
	@SkipValidation
	public String detailChangePwd() throws SessionExpiredException {
		
		String retval = "userdetailchgPwd";
		UserDao userDao = new UserDao();
		try {
                        PassHistoryDao passHistoryDao = new PassHistoryDao();
                        TblPassHistory tblPassHistory = new TblPassHistory();
                        TblUser curusr = getUserBean();
			TblUser user1 = userDao.getUserById(id);                        
                        String userlogin = user1.getUserLogin();
                        String nlgnpwdmd5 = MD5(lgnpwdn);
                        Boolean result = passHistoryDao.getSearchList(userlogin,nlgnpwdmd5);
                        if (result){   
                            if(lgnpwdn.trim().equals(lgnpwdc.trim())) {
                                    boolean res = userDao.changePassword(user1.getUserId(), nlgnpwdmd5);

                                    if(res) {
                                            tblPassHistory.setUsername(userlogin);
                                            tblPassHistory.setPassword(nlgnpwdmd5);
                                            tblPassHistory.setCreatedDate(new Date());
                                            passHistoryDao.saveTblPassHistory(tblPassHistory);                                        
                                            addActionMessage("Password changed successfully..!!!");
                                            retval = "view";
                                    } else {
                                            addActionError("Change Password Failed..!!!");
                                    }

                            } else {
                                    addActionError("New Password and Confirm Password must be same..!!!");
                                    return retval;
                            }
                        }else {
                                    addActionError("Unable to reuse last 4 used passwords..!!!");
                                    return retval;
                        }
				
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "userdetailchgPwd";
		}
		
		
		
		return retval;
		
	}		
	
	@SkipValidation
	public String forceChangePwd() throws SessionExpiredException {
		
		String retval = "login";
		UserDao userDao = new UserDao();
		try {   
                        PassHistoryDao passHistoryDao = new PassHistoryDao();
                        TblPassHistory tblPassHistory = new TblPassHistory();
			TblUser user1 = getUserBean();
                        String userlogin = user1.getUserLogin();
                        String nlgnpwdmd5 = MD5(lgnpwdn);
                        Boolean result = passHistoryDao.getSearchList(userlogin,nlgnpwdmd5);
                        if (result){                        
                            if(lgnpwdn.trim().equals(lgnpwdc.trim())) {
                                
				boolean res = userDao.forceChangePassword(user1.getUserId(), nlgnpwdmd5);
				
				if(res) {
                                        tblPassHistory.setUsername(userlogin);
                                        tblPassHistory.setPassword(nlgnpwdmd5);
                                        tblPassHistory.setCreatedDate(new Date());
                                        passHistoryDao.saveTblPassHistory(tblPassHistory);
                                        Map<String, Object> session = ActionContext.getContext().getSession();
                                        TblUser user= userDao.getUserById(user1.getUserId());
                                        session.put("loginUser", user);                                        
					addActionMessage("Password changed successfully..!!!");
					//retval = SUCCESS;
				} else {
					addActionError("Change Password Failed..!!!");
				}
				
                            } else {
                                    addActionError("New Password and Confirm Password must be same..!!!");
                                    return retval;
                            }
                        }else{
                            addActionError("Unable to reuse last 4 used passwords.");
                            return "forceChgPwd";                            
                        }
				
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "login";
		}
		
		
		
		return retval;
		
	}        
	
	public String selectstore(){

		try {
			StoreDao storeDao = new StoreDao();
			setStoreList(storeDao.getStoresList(areaId));
		}catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}
	
	
	public String delete() {
		return INPUT;
	}

	public String search() {
		return INPUT;
	}
	
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		
		if(curusr == null) {
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}
	
		
	public ArrayList<TblArea> getAreas(){
		StoreDao storeDao = new StoreDao();
		return storeDao.listArea();
	}

	public ArrayList<TblStore> getStores() {		
		StoreDao storeDao = new StoreDao();
		return storeDao.getStoresByArea(getAreaId());
	}
	
	/**
	 * Getters / Setters
	 */
	
	public boolean isUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getUsradm() {
		return usradm;
	}

	public void setUsradm(String usradm) {
		this.usradm = usradm;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public ArrayList<TblRole> getRoles(){
		UserDao c = new UserDao();
		return c.roleList();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserDob() {
		return userDob;
	}

	public void setUserDob(String userDob) {
		this.userDob = userDob;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public ArrayList<TblStore> getStoreList() {
		return storeList;
	}

	public void setStoreList(ArrayList<TblStore> storeList) {
		this.storeList = storeList;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getLgnpwdn() {
		return lgnpwdn;
	}

	public void setLgnpwdn(String lgnpwdn) {
		this.lgnpwdn = lgnpwdn;
	}

	public String getLgnpwdc() {
		return lgnpwdc;
	}

	public void setLgnpwdc(String lgnpwdc) {
		this.lgnpwdc = lgnpwdc;
	}

	public String getLgnpwdo() {
		return lgnpwdo;
	}

	public void setLgnpwdo(String lgnpwdo) {
		this.lgnpwdo = lgnpwdo;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserCfmPwd() {
		return userCfmPwd;
	}

	public void setUserCfmPwd(String userCfmPwd) {
		this.userCfmPwd = userCfmPwd;
	}

	public String getFranchiseeExpiryDate() {
		return franchiseeExpiryDate;
	}

	public void setFranchiseeExpiryDate(String franchiseeExpiryDate) {
		this.franchiseeExpiryDate = franchiseeExpiryDate;
	}

	public String getFranchiseeStartDate() {
		return franchiseeStartDate;
	}

	public void setFranchiseeStartDate(String franchiseeStartDate) {
		this.franchiseeStartDate = franchiseeStartDate;
	}

	public String getRcbName() {
		return rcbName;
	}

	public void setRcbName(String rcbName) {
		this.rcbName = rcbName;
	}

	public String getRcbNo() {
		return rcbNo;
	}

	public void setRcbNo(String rcbNo) {
		this.rcbNo = rcbNo;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserRemarks() {
		return userRemarks;
	}

	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}

	public String getUserDoj() {
		return userDoj;
	}

	public void setUserDoj(String userDoj) {
		this.userDoj = userDoj;
	}

	public String getFranchiseeDuration() {
		return franchiseeDuration;
	}

	public void setFranchiseeDuration(String franchiseeDuration) {
		this.franchiseeDuration = franchiseeDuration;
	}
	
	public String getFranchiseeScheme() {
		return franchiseeScheme;
	}

	public void setFranchiseeScheme(String franchiseeScheme) {
		this.franchiseeScheme = franchiseeScheme;
	}

	public String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
	}	
        
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getLoginError() {
        return loginError;
    }

    public void setLoginError(Integer loginError) {
        this.loginError = loginError;
    }

    public String getPasswordForce() {
        return passwordForce;
    }

    public void setPasswordForce(String passwordForce) {
        this.passwordForce = passwordForce;
    }
                
}
