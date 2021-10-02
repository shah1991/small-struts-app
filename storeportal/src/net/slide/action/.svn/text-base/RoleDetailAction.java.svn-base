package net.slide.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import net.slide.dao.UserDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblRole;
import net.slide.pojo.TblUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RoleDetailAction extends ActionSupport  {

	private boolean updateMode;
	private int id;
	
	private String roleCd;
	private String roleName;
	
	public String add() throws SessionExpiredException {
		try {
			getUserBean();
			updateMode = false;
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

	public String view() throws SessionExpiredException {
		
		try {
			getUserBean();
			updateMode = true;
			UserDao usrDao = new UserDao();			
			TblRole r = usrDao.getRoleById(id);
			roleCd = r.getRoleCd();
			roleName = r.getRoleName();
			id = r.getRoleId();
			
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

	public String create() throws SessionExpiredException {
		String retval = INPUT;
		try {
				
			TblUser curusr = getUserBean();

			UserDao usrDao = new UserDao();
			long cnt = 0;
			if(roleCd != null) {
				cnt = usrDao.getRoleCountByCd(roleCd);
			}
			
			if(cnt > 0) {
				addActionError("User with same Role Code already exists, choose new Role Code..!!!");
				return retval;
			}

			TblRole r = new TblRole();
			r.setRoleCd(roleCd);
			r.setRoleName(roleName);
			
			boolean res = usrDao.saveRole(r);
			if ( res == false) {
				addActionError("Add failed..!!!");
			}else{
				addActionMessage("Add Successfully !!!!");
				retval = SUCCESS;
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
	
	public String update() throws SessionExpiredException {
		String retval = INPUT;
		try {
			UserDao usrDao = new UserDao();
			updateMode = true;
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = getUserBean();
			TblRole r = usrDao.getRoleById(id);
			
			if(!r.getRoleCd().trim().equalsIgnoreCase(roleCd.trim())) {
				long cnt = 0;

				if(roleCd != null) {
					cnt = usrDao.getRoleCountByCd(roleCd);
				}
				
				if(cnt > 0) {
					addActionError("User with same Role Code already exists, choose new role code..!!!");
					return retval;
			
				}
			}
			
			
			r.setRoleCd(roleCd);
			r.setRoleName(roleName);
			r.setUpdatedBy(curusr.getUserLogin());
			r.setUpdatedOn(new Date());
			 
			boolean res = usrDao.updateRole(r);

			if ( res == false) {
				addActionError("Update failed..!!!");
			}else{
				addActionMessage("Update Successfully !!!!");
				retval = SUCCESS;
				session.remove("update");
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

	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		
		if(curusr == null) {
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}

	
	public ArrayList<TblUser> getUserList(){
		UserDao c = new UserDao();
		ArrayList<TblUser>  userList = c.listLookup();
		return userList;
	}
	
	
	/**
	 * Getters / Setters
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleCd() {
		return roleCd;
	}


	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public boolean isUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}
		
	
}
