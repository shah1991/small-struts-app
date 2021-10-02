package net.slide.action;

import java.util.ArrayList;
import java.util.Map;

import net.slide.dao.UserDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblRole;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;
import net.slide.util.CommonUtil;
import net.slide.util.TransException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RoleMainAction extends ActionSupport  {

	private String searchTxt;
	private int deleteList[];
	ArrayList<TblRole> roleList;

	public String delete() throws SessionExpiredException {

		try {
			UserDao usrDao = new UserDao();
			getUserBean();
			boolean res = usrDao.deleteRoles(getDeleteList());	
			if ( res == false) {
			    addActionError("Failed to delete.");
			}else{
			    addActionMessage("Record(s) successfully removed.");
			}
			deleteList = null;
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (TransException e) {
			addActionError(e.getMessage());
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			deleteList = null;
			return INPUT;
		}

		return INPUT;
	}

	public String search() throws SessionExpiredException {
		try {
			getUserBean();
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
	
	
	public ArrayList<TblMasterRes> getRoleList(){
		UserDao c = new UserDao();
		ArrayList<TblMasterRes> roleList = c.roleList(searchTxt);
		return roleList;
	}
	
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		
		if(curusr == null) {
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}
	

	public int[] getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(String deleteList) {
		this.deleteList = CommonUtil.convertInt(deleteList);
	}

	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}

	public void setUserList(ArrayList<TblRole> userList) {
		this.roleList = userList;
	}
	
}
