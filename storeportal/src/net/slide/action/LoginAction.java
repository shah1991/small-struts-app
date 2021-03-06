package net.slide.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.slide.dao.ChklstDao;
import net.slide.dao.FileShareDao;
import net.slide.dao.LinkDao;
import net.slide.dao.PostDao;
import net.slide.dao.StoreDao;
import net.slide.dao.TaskPostDao;
import net.slide.dao.UserDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblArea;
import net.slide.pojo.TblFileShare;
import net.slide.pojo.TblLink;
import net.slide.pojo.TblPostArea;
import net.slide.pojo.TblPostStore;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblChklstRes;
import net.slide.pojores.TblHomeRes;
import net.slide.pojores.TblTaskRes;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;




import com.dfsg.sso.filter.SSOFilter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;



// captcha
import java.util.Date;

import javax.servlet.http.*;
import javax.servlet.*;

import net.slide.captcha.*;

import org.joda.time.Days;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {

	private String userName;
	private String passWord;
	private TblUser loginUser;
	private String lgnpwdn = "";
	private String lgnpwdc = "";
	private String lgnpwdo = "";
	private int roleId;
	private String userDob;
	private int storeId;
	private int areaId;
	private int ownerSite;
	private int ownerRole;
	private String searchTxt;
        private String captchaText;
        private String forceChangePassword = "";
        

	@SkipValidation
	public String cancel()
    {
		setUserName("");
    	return INPUT;
    }

	@SkipValidation
	public String login()
    {
		// SSO Changes :: START
    	if ( SSOFilter.IS_ENABLED ) {
			Map<String, Object> session = ActionContext.getContext().getSession();
			if ( session.get(com.dfsg.sso.util.Constants.SSO_USER) != null ) {
				loginUser = (TblUser) session.get("loginUser");
				if ( loginUser.getUserAdmin().equals("Y") ) {
					return "admin";
				} else {
					return SUCCESS;
				}
			}
		}
    	// SSO Changes :: END
		
    	return INPUT;
    }                
        
	@SkipValidation
	public String home(){
		return SUCCESS;
	}
	
	 public String process()
	 {
		String retval = SUCCESS;
		Map<String, Object> session = ActionContext.getContext().getSession();
                   UserDao userObj = new UserDao();
               
                session.put("loginUser", loginUser);
                try{
                getUserBean();
                    TblUser user = loginUser;
                    user = userObj.getUserByName(userName);    
                    if (user != null){
                        Integer loginerror = user.getLoginError();
                        Date logindate = user.getLoginDate();
                        Date currdate2 = new Date();
                        int difday = Days.daysBetween(new DateTime(logindate), new DateTime(currdate2)).getDays();
                        Date loginexpiry = user.getExpiryDate();
                        String forceChangePwd = user.getPasswordForce();
                        String userstatus = user.getUserStatus();
                        
                        
                        if (loginerror!=null && loginerror>=3 ){
                              addFieldError("errormsg","Account locked !!!");     
                              return INPUT;
                         }
                        
                         if (loginexpiry!=null && loginexpiry.before(currdate2)){
                              addFieldError("errormsg","Account expired !!!");                                                   
                              return "forceChgPwd";
                         }
                         if (difday>90){
                              addFieldError("errormsg","Account suspended !!!");                                                   
                              return INPUT;
                         }  
                         if (forceChangePwd != null && forceChangePwd.equalsIgnoreCase("Y")){
                            addFieldError("errormsg","Forche to change password !!!"); 
                             return "forceChgPwd";
                         }
                         
                    }
                }catch (Exception e) {
                        // TODO: handle exception
                }
 
                         
                if(loginUser.getUserAdmin().equals("Y"))
                        retval = "admin";
                return retval;
                    
	 }
	    
	@Override
	public void validate() {
		super.validate();
		
		if(getFieldErrors().isEmpty()== false){
			return;
		}
               HttpServletRequest request  = (HttpServletRequest)
                ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
                Boolean isResponseCorrect = Boolean.FALSE;
                javax.servlet.http.HttpSession session = request.getSession();
                String parm = request.getParameter("captchaText");
                String c= (String)session.getAttribute(CaptchaServlet.CAPTCHA_KEY) ;
                if(!parm.equals(c) ){
                    addFieldError("errormsg", "Invalid Captcha.");
                    // dont count as error login 
                    return;
                }                
                		
		UserDao userObj = new UserDao();
		//TblUser user = userObj.authUser(userName, passWord,userDob);
		TblUser user = userObj.authUser(userName, passWord);
		
		if ( (user != null)) {
                    boolean res = true;
                    loginUser = user;
                    Integer loginerror = user.getLoginError();
                    Date logindate = user.getLoginDate();
                    Date currdate2 = new Date();
                    int difday = Days.daysBetween(new DateTime(logindate), new DateTime(currdate2)).getDays();
                    Date loginexpiry = user.getExpiryDate();
                    String forceChangePwd = user.getPasswordForce();
                    if (loginexpiry!=null && loginexpiry.before(currdate2)){
               //         addFieldError("errormsg","Account expired !!!");                                                   
                        res = false;
                    }
                    if (loginerror!=null && loginerror>=3 ){
                        res = false;
                         }                    
                    if (difday>90){
              //          addFieldError("errormsg","Account suspended !!!");      
                        res = false;
                    }  
                    if (forceChangePwd != null && forceChangePwd.equalsIgnoreCase("Y")){
              //              addFieldError("errormsg","Forche to change password !!!"); 
                            res = false;
                    }                    
                    if (res){
                        user.setLoginError(0);
                        DateTime now = new DateTime();
                        Date jnow = now.toDate();
                        user.setLoginDate(jnow);
                        userObj.updateUser(user);
                    }
                            
		} else {
                    user = new TblUser();
                    user = userObj.getUserByName(userName);    
                    if (user != null){
                        Integer loginerror = user.getLoginError();
                        if (loginerror!=null){
                            loginerror++;
                        }else{
                            loginerror=1;
                        }
                        user.setLoginError(loginerror);
                        if (loginerror>=3){
                            user.setUserStatus("N");
                              addFieldError("errormsg","Account locked !!!");   
                        }else{
                            addFieldError("errormsg", "Invalid Credentials.");                            
                        }
                        userObj.updateUser(user);                            
                    }else{
                            addFieldError("errormsg", "Invalid Credentials."); 
                    }                    
		}
		
		return;
	}
	
	

	

	@SkipValidation
	public String changePwd() throws SessionExpiredException {
		
		String retval = "chgPwd";
		UserDao userDao = new UserDao();
		try {
			TblUser user1 = getUserBean();
			
			if(!user1.getUserPwd().trim().equals(lgnpwdo)) {
				addActionError("Old Password is wrong, Try Again!!!");
				return retval;
			} else if(lgnpwdn.trim().equals(lgnpwdo)) {
				addActionError("Old Password and New Password should not be same, Try Again!!!");
				return retval;
			} else if(lgnpwdn.trim().equals(lgnpwdc.trim())) {
				boolean res = userDao.changePassword(user1.getUserId(), lgnpwdn);
				
				if(res) {
					addActionMessage("Password changed successfully..!!!");
					//retval = SUCCESS;
				} else {
					addActionError("Change Password Failed..!!!");
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
			return INPUT;
		}
		
		
		
		return retval;
		
	}
	
	public String cancelChgPwd() {
		return SUCCESS;
	}
	public ArrayList<TblHomeRes> getLinkList() throws SessionExpiredException, Exception{
		 getUserBean();
		
		LinkDao linkDao=new LinkDao();
		ArrayList<TblHomeRes> homeList=new ArrayList<TblHomeRes>() ;
		ArrayList<TblLink> linkList=null;
		linkList=linkDao.linkList(searchTxt);
		boolean check=true;
		for(TblLink link:linkList){
			if(ownerRole==4 || ownerRole==13){
				check=false;
			}else{
				check=true;
			}
			TblHomeRes res=new TblHomeRes();
			res.setActive(link.getActive());
			res.setIconPath(link.getIconPath());
			res.setLinkId(link.getLinkId());
			res.setTitle(link.getTitle());
			res.setUrl(link.getUrl());
			String url=link.getUrl();
			
			if(url.contains("TaskPostMain_entry.do")){
				TaskPostDao taskDao = new TaskPostDao(); 
				res.setType("Task");
				url=url.replace("TaskPostMain_entry.do?portfolioId=", "");
				if(url.length()>0){
				int portfolioId=Integer.parseInt(url);
				if(ownerRole==2){
					ArrayList<TblTaskRes> resList =new ArrayList<TblTaskRes>();
					try{
					resList= taskDao.getAreaPendingTask(areaId, portfolioId);
					}catch (Exception e) {
						// TODO: handle exception
					}
					if(resList.size()!=0){
						res.setCount(String.valueOf(resList.size()));	
						}
				}
				if(ownerRole==1){
					ArrayList<TblTaskRes> resList =new ArrayList<TblTaskRes>();
					try{
					resList = taskDao.getStorePendingTask(storeId, portfolioId);
					}catch (Exception e) {
						// TODO: handle exception
					}
					if(resList.size()!=0){
					res.setCount(String.valueOf(resList.size()));	
					}
				}}
			}else if(url.contains("ChklstPostMain_topic.do")){
					url=url.replace("ChklstPostMain_topic.do?chklstGroupId=", "");
					if(url.length()>0){
					int chklstGroupId=Integer.parseInt(url);
				if(ownerRole==2){
					ChklstDao postDao=new ChklstDao();
					ArrayList<TblChklstRes> resList =new ArrayList<TblChklstRes>();
					try{
					resList = postDao.getAreaPendingList(areaId,chklstGroupId);
					}catch (Exception e) {
						// TODO: handle exception
					}
					if(resList.size()!=0){
						res.setCount(String.valueOf(resList.size()));	
						}
				}
				if(ownerRole==1){
					ChklstDao postDao=new ChklstDao();
					ArrayList<TblChklstRes> resList=new ArrayList<TblChklstRes>();
					try{
					resList = postDao.getStoreNotification(storeId,chklstGroupId);
					//resList = postDao.getAreaPendingList(areaId,chklstGroupId);
					
                                        }catch (Exception e) {
						// TODO: handle exception
					}
					if(resList.size()!=0){
						res.setCount(String.valueOf(resList.size()));	
					}
				}
                                if(ownerRole==3 && chklstGroupId==7){
					ChklstDao postDao=new ChklstDao();
					ArrayList<TblChklstRes> resListStore=new ArrayList<TblChklstRes>();
                                        ArrayList<TblChklstRes> resListArea=new ArrayList<TblChklstRes>();
					try{
					resListStore = postDao.getStoreHqNotification(chklstGroupId);
					resListArea = postDao.getAreaHqNotification(chklstGroupId);
					
                                        }catch (Exception e) {
						// TODO: handle exception
					}
					if(resListStore.size()!=0 || resListArea.size()!=0){
						res.setCount(String.valueOf(resListStore.size()+resListArea.size()));	
					}
                                        /*if(resList.size()!=0){
						res.setCount(String.valueOf(resList.size()));	
                                        }*/
				}}
				res.setType("Check");	
			}else if(url.contains("PostMain_topic.do")){
				url=url.replace("PostMain_topic.do?forumId=", "");
				if(url.length()>0){
				int forumId=Integer.parseInt(url);
				
			if(ownerRole==2){
				PostDao postDao=new PostDao();
				ArrayList<TblPostArea> resList=new 	ArrayList<TblPostArea>();
		      try{
				resList = postDao.getAreaNotification(areaId,forumId);
		      }catch (Exception e) {
				// TODO: handle exception
			}
				if(resList.size()!=0){
					res.setCount(String.valueOf(resList.size()));	
					}
			}
			if(ownerRole==1){
				PostDao postDao=new PostDao();
				ArrayList<TblPostStore> resList=new ArrayList<TblPostStore>();
			   try{
				resList = postDao.getStoreNotification(storeId,forumId);
			   }catch (Exception e) {
				// TODO: handle exception
			}
					if(resList.size()!=0){
					res.setCount(String.valueOf(resList.size()));	
				}
			}}
			res.setType("Post");	
		}else if(url.contains("FilePostMain_entry.do")){
			url=url.replace("FilePostMain_entry.do?groupId=", "");
			if(url.length()>0){
			int groupId=Integer.parseInt(url);
			
			if(ownerRole==1){
			FileShareDao fileShareDao = new FileShareDao();
			ArrayList<TblFileShare> resList = null;	
			   try{
					resList = fileShareDao.getStoreNotification(storeId,groupId);
				   }catch (Exception e) {
					// TODO: handle exception
				}
				if(resList.size()!=0){
						res.setCount(String.valueOf(resList.size()));	
				}
			
			}
			}
			
			if ( ownerRole == 13 ) { // display EDO Files only for WH users
				if ( StringUtils.contains(res.getTitle(), "EDO Files") ) {
					check = true;
				}
			} else if(ownerRole==2){
				check = true;
			} else {
				check=true;
			}
		}else{
				res.setType("Other");
			}
			if(check){
			homeList.add(res);
			}
		}
		
		return homeList;
	}
	
	

	
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		 session.put("forumId",null);
		 session.put("topicId",null);
		 session.put("chklstGroupId",null);
		 session.put("portfolio",null);
		 session.put("groupId",null);
		if(curusr!=null){
			if(curusr.getTblRole().getRoleId()==1){
				//sm
				setStoreId(curusr.getSiteId());
				StoreDao storeDao=new StoreDao();
				TblStore tblStore=storeDao.getStore(curusr.getSiteId());
				session.put("tblStore", tblStore);
				setOwnerSite(curusr.getUserId());
				setOwnerRole(curusr.getTblRole().getRoleId());
			}else if(curusr.getTblRole().getRoleId()==2){
				//am
				setAreaId(curusr.getSiteId());
				StoreDao storeDao=new StoreDao();
				TblArea tblArea=storeDao.getAreaById(curusr.getSiteId());
				session.put("tblArea", tblArea);
				setOwnerSite(curusr.getUserId());
				setOwnerRole(curusr.getTblRole().getRoleId());
			}else if(curusr.getTblRole().getRoleId()==3 || curusr.getTblRole().getRoleId()==8
					 || curusr.getTblRole().getRoleId()==13){
			
				setOwnerSite(curusr.getUserId());
				setOwnerRole(curusr.getTblRole().getRoleId());
			}else if(curusr.getTblRole().getRoleId()==4){
				//fm
				setStoreId(curusr.getSiteId());
				StoreDao storeDao=new StoreDao();
				TblStore tblStore=storeDao.getStore(curusr.getSiteId());
				session.put("tblStore", tblStore);
				setOwnerSite(curusr.getUserId());
				setOwnerRole(curusr.getTblRole().getRoleId());
			}
		}else{
		
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}
	@RequiredStringValidator(message="User name is required.")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@RequiredStringValidator(message="Password is required.")
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public TblUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(TblUser loginUser) {
		this.loginUser = loginUser;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getUserDob() {
		return userDob;
	}

	public void setUserDob(String userDob) {
		this.userDob = userDob;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getOwnerSite() {
		return ownerSite;
	}

	public void setOwnerSite(int ownerSite) {
		this.ownerSite = ownerSite;
	}

	public int getOwnerRole() {
		return ownerRole;
	}

	public void setOwnerRole(int ownerRole) {
		this.ownerRole = ownerRole;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}
	
	
}
