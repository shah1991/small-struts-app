package net.slide.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import net.slide.dao.ChklstDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblChklstGroup;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;
import net.slide.util.TransException;

@SuppressWarnings("serial")
public class ChklstGroupAction extends BaseAction {

	private int chklstgrpid;
	private String chklstgrpname;
	private String chklststatus;
	private boolean editmode = false;;
	private String searchTxt;

	private InputStream inputStream;
	public InputStream getInputStream() {
	    return inputStream;
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

	public ArrayList<TblMasterRes> getGroupList() {
		ChklstDao chklstDao = new ChklstDao(); 
		ArrayList<TblMasterRes> resList = chklstDao.groupList(searchTxt);
		return resList;
	}
	
	/**
	 *  Detail Actions
	 */
	
	public String detailadd() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao chklstDao = new ChklstDao();
			TblChklstGroup tblChklstGroup = chklstDao.getGroupById(chklstgrpid);
			if(tblChklstGroup != null) {
				setChklstgrpname(tblChklstGroup.getChklstGroupName());
				setChklstgrpid(tblChklstGroup.getChklstGroupId());
			}
			setChklststatus("Y");
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		
		setChklstgrpname("");
		return "detail";
	}

	
	public String detailedit() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao chklstDao = new ChklstDao();
			TblChklstGroup f = chklstDao.getGroupById(chklstgrpid);
			if(f != null) {
				setChklstgrpname(f.getChklstGroupName());
				setChklstgrpid(f.getChklstGroupId());
				setChklststatus(f.getStatus());
				editmode = true;
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		
		return "detail";
	}
	
	public String detaildelete() throws SessionExpiredException {
		boolean res=false;
		String ans = "###Fail###";
		String msg = "Delete failed..!!!";
		try {
			getUserBean();
			ChklstDao chklstDao = new ChklstDao();
			TblChklstGroup f = chklstDao.getGroupById(chklstgrpid);
			if(f != null) {
				res=chklstDao.deleteGroup(chklstgrpid);
			}
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (TransException e) {
			addActionError(e.getMessage());
		}  catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}
		
		
		if ( res == false) {
			addActionError("Delete failed..!!!");
			ans += msg;
		}else{
			addActionMessage( "Delete Successfully !!!!");
			//ans = chklstgrpname;
		}
		
		//inputStream = new ByteArrayInputStream(ans.getBytes());
		return INPUT;
	}
	

	
	public String detailcreate() {
		String retval = SUCCESS;
		String ans = "###Fail###";
		String msg = "Add failed..!!!";

		ChklstDao chklstDao = new ChklstDao();

		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		boolean res = false;
		TblChklstGroup f = chklstDao.getGroupByName(chklstgrpname);
		if(f == null){
			f = new TblChklstGroup();
			f.setChklstGroupName(chklstgrpname);
			f.setStatus(chklststatus);
			f.setUpdatedBy(curusr.getUserName());
			f.setUpdatedDate(new Date());
			res = chklstDao.saveGroup(f);
		}else{
			msg = "Group Name already found !!!";
		}

		if ( res == false) {
			addActionError("Add failed..!!!");
			ans += msg;
		}else{
			addActionMessage(chklstgrpname + "Added Successfully !!!!");
			ans = chklstgrpname;
		}
		
		inputStream = new ByteArrayInputStream(ans.getBytes());

		return retval;
	}
	
	

	public String detailupdate() {
		String retval = SUCCESS;
		String ans = "###Fail###";
		String msg = "Update failed..!!!";

		ChklstDao chklstDao = new ChklstDao();
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		TblChklstGroup f = chklstDao.getGroupById(chklstgrpid);
		
		TblChklstGroup ffind = chklstDao.getGroupByName(chklstgrpname);
		
		boolean res = false;		
		if(ffind != null && ffind.getChklstGroupId() != f.getChklstGroupId()){
			msg = "Group Name already found !!!";
		}else{
			f.setChklstGroupName(chklstgrpname);
			f.setStatus(chklststatus);
			f.setUpdatedBy(curusr.getUserName());
			f.setUpdatedDate(new Date());
			res = chklstDao.saveGroup(f);
		}

		if ( res == false) {
			addActionError("Update failed..!!!");
			ans += msg;
		}else{
			addActionMessage("Updated Successfully !!!!");
			ans = chklstgrpname;
		}
		
		inputStream = new ByteArrayInputStream(ans.getBytes());

		return retval;
	}
	/**
	 * 
	 * Detail Bean Get/Set
	 */


	public int getChklstgrpid() {
		return chklstgrpid;
	}


	public void setChklstgrpid(int chklstgrpid) {
		this.chklstgrpid = chklstgrpid;
	}


	public String getChklstgrpname() {
		return chklstgrpname;
	}


	public void setChklstgrpname(String chklstgrpname) {
		this.chklstgrpname = chklstgrpname;
	}


	public String getChklststatus() {
		return chklststatus;
	}


	public void setChklststatus(String chklststatus) {
		this.chklststatus = chklststatus;
	}


	public boolean isEditmode() {
		return editmode;
	}


	public void setEditmode(boolean editmode) {
		this.editmode = editmode;
	}

	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}


	
}
