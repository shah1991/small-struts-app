package net.slide.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.slide.dao.ChklstDao;
import net.slide.dao.DeptDao;
import net.slide.dao.FileGroupDao;
import net.slide.dao.GradeDao;
import net.slide.dao.PostDao;
import net.slide.dao.TaskDao;
import net.slide.dao.UserDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblChklstGroup;
import net.slide.pojo.TblDept;
import net.slide.pojo.TblFileGroup;
import net.slide.pojo.TblGrade;
import net.slide.pojo.TblPostForum;
import net.slide.pojo.TblRole;
import net.slide.pojo.TblTaskPortfolio;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;
import net.slide.util.HibernateUtil;
import net.slide.util.TransException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FileGroupAction extends BaseAction{

	private String active;
	private boolean editmode;
	private int groupId;
	private String groupName;
	private String status;
	private Long deleteList[];
	private String searchTxt;
	@SkipValidation
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
	@SkipValidation
	public String add() throws SessionExpiredException {
		
		try {
			getUserBean();
			editmode = false;
			status = "Y";
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "add";
		}
		return "add";
	}
	
	
public String create() throws SessionExpiredException {
		
		String retval = SUCCESS;
		
		try {
			
			TblUser curusr = getUserBean();
			
			long cnt = 0;
			FileGroupDao groupDao = new FileGroupDao();
			if(groupName != null) {
				cnt = groupDao.getgroupCountBygroupname(groupName);
			}
			
			if(cnt > 0) {
				addActionError("Another Grade with same Name already exists, Choose New Grade..!!!");
				return retval;
			}
			

			TblFileGroup filegroup = new TblFileGroup();
		   filegroup.setGroupName(groupName);
		   filegroup.setStatus(status);
		   filegroup.setCreatedBy(curusr.getUserName());
		   filegroup.setCreatedDate(new Date());
		   filegroup.setUpdatedBy(curusr.getUserName());
		   filegroup.setUpdatedDate(new Date());
		   			
			boolean result = groupDao.addGroup(filegroup);

			if (!result) {
				addActionError("Add failed..!!!");
			} else {
				filegroup=groupDao.getGroupBygroupname(groupName);
				
				addActionMessage("Add Successfully !!!!");
				retval= SUCCESS;
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

	public String edit() throws SessionExpiredException {
		String retval = "edit";
		try {
			getUserBean();
			FileGroupDao filegroupDao = new FileGroupDao();
	
			TblFileGroup tblfilegroup = filegroupDao.getGroupById(groupId);
			if(tblfilegroup!=null){
			groupName=tblfilegroup.getGroupName();
			groupId=tblfilegroup.getGroupId();
			status=tblfilegroup.getStatus();
			
			editmode = true;
	
			}else{
				retval = INPUT;
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
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = getUserBean();
			
			editmode = true;
			
			FileGroupDao filegroupDao = new FileGroupDao();
			//Get the store By ID
			TblFileGroup tblfilegroup = filegroupDao.getGroupById(groupId);
			long cnt = 0;
			if(!tblfilegroup.getGroupName().trim().equalsIgnoreCase(groupName.trim())) {
				cnt = filegroupDao.getgroupCountBygroup(groupName);
				if(cnt > 0) {
					throw new Exception("Another Group with same Name already exists, Choose New Name..!!!");
				}
			}
			
		
			tblfilegroup.setGroupName(groupName);
			tblfilegroup.setStatus(status);
			tblfilegroup.setUpdatedBy(curusr.getUserName());
			tblfilegroup.setUpdatedDate(new Date());
			
			
			boolean result = filegroupDao.updateGroup(tblfilegroup);
	
			if (!result) {
				addActionError("Update failed..!!!");
			} else {
				addActionMessage("File Group Updated Successfully !!!!");
				retval= SUCCESS;
				session.remove("update");
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!"+e.getMessage());
			e.printStackTrace();
			return INPUT;
		}
		return retval;
	}



	public String delete() throws SessionExpiredException {
		
		try {
			getUserBean();
			FileGroupDao filegroupDao = new FileGroupDao();
			filegroupDao.delete(groupId);
			editmode = false;
			active = "Y";
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
	
	
public ArrayList<TblMasterRes> getGroupList() throws SessionExpiredException{
	ArrayList<TblMasterRes> groupList = null;
	try {
		getUserBean();
		FileGroupDao filegroupDao = new FileGroupDao();
		groupList = filegroupDao.groupList(searchTxt);
	} catch (SessionExpiredException se) {
		addActionError("Session Expired..!!!");
		throw se;
	} catch (Exception e) {
		addActionError("Technical Error Happned..!!!");
		e.printStackTrace();
	}

	return groupList;
}
public Long[] getDeleteList() {
	return deleteList;
}
public void setDeleteList(Long[] deleteList) {
	this.deleteList = deleteList;
}
public String getActive() {
	return active;
}
public void setActive(String active) {
	this.active = active;
}
public boolean isEditmode() {
	return editmode;
}
public void setEditmode(boolean editmode) {
	this.editmode = editmode;
}
public int getGroupId() {
	return groupId;
}
public void setGroupId(int groupId) {
	this.groupId = groupId;
}
public String getGroupName() {
	return groupName;
}
public void setGroupName(String groupName) {
	this.groupName = groupName;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getSearchTxt() {
	return searchTxt;
}
public void setSearchTxt(String searchTxt) {
	this.searchTxt = searchTxt;
}


}
