package net.slide.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import net.slide.dao.ChklstDao;
import net.slide.dao.DeptDao;
import net.slide.dao.GradeDao;
import net.slide.dao.PostDao;
import net.slide.dao.StoreDao;
import net.slide.dao.TaskDao;
import net.slide.dao.UserDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblArea;
import net.slide.pojo.TblChklstGroup;
import net.slide.pojo.TblDept;
import net.slide.pojo.TblGrade;
import net.slide.pojo.TblPostForum;
import net.slide.pojo.TblTaskPortfolio;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;
import net.slide.util.TransException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class GradeMainAction extends BaseAction{

	private String active;
	private boolean editmode;
	private int gradeId;
	private String gradeName;
	private String gradeValue;
	
	
	
	private Long deleteList[];
	private String searchTxt;

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
			active = "Y";
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
			GradeDao gradeDao = new GradeDao();
			if(gradeName != null) {
				cnt = gradeDao.getgradeCountBygrade(gradeName);
			}
			
			if(cnt > 0) {
				addActionError("Another Grade with same Name already exists, Choose New Grade..!!!");
				return retval;
			}
			

			TblGrade grade = new TblGrade();
		   
			grade.setGradeName(gradeName);
		    grade.setGradeValue(gradeValue);
		
			boolean result = gradeDao.addGrade(grade);

			if (!result) {
				addActionError("Add failed..!!!");
			} else {
				grade=gradeDao.getGradeBygradename(gradeName);
				
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
	String retval = SUCCESS;
	try {
		getUserBean();
		GradeDao gradeDao = new GradeDao();

		TblGrade tblgrade = gradeDao.getGradeById(gradeId);
		gradeName=tblgrade.getGradeName();
		gradeValue=tblgrade.getGradeValue();
		gradeId=tblgrade.getGradeId();
		
		
		editmode = true;

	} catch (SessionExpiredException se) {
		addActionError("Session Expired..!!!");
		throw se;
	} catch (Exception e) {
		addActionError("Technical Error Happned..!!!");
		e.printStackTrace();
		return INPUT;
	}
	return "edit";
}

public String update() throws SessionExpiredException {
	String retval = INPUT;
	
	try {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = getUserBean();
		
		editmode = true;
		
		GradeDao gradeDao = new GradeDao();
		//Get the store By ID
		TblGrade tblgrade = gradeDao.getGradeById(gradeId);
		long cnt = 0;
		if(!tblgrade.getGradeName().trim().equalsIgnoreCase(gradeName.trim())) {
			cnt = gradeDao.getgradeCountBygrade(gradeName);
			if(cnt > 0) {
				throw new Exception("Another Grade with same Name already exists, Choose New Name..!!!");
			}
		}
		
	
		//Copy New value to grade Entity
		tblgrade.setGradeName(gradeName);
		tblgrade.setGradeValue(gradeValue);
		
		
		boolean result = gradeDao.updateGrade(tblgrade);

		if (!result) {
			addActionError("Update failed..!!!");
		} else {
			addActionMessage("Grade Updated Successfully !!!!");
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


@SkipValidation
public String delete() throws SessionExpiredException {
	String retval = INPUT;
	
	try {
		getUserBean();
		boolean result = false;

		GradeDao gradeDao = new GradeDao();
		result = gradeDao.deleteGrade(gradeId);
		
		if (!result) {
			addActionError("Delete failed..!!!");
		} else {
			addActionMessage("Grade Deleted Successfully !!!!");
			retval= SUCCESS;
		}
	} catch (SessionExpiredException se) {
		addActionError("Session Expired..!!!");
		throw se;
	}  catch (TransException e) {
		addActionError(e.getMessage());
	} catch (Exception e) {
		addActionError("Technical Error Happned..!!!");
		e.printStackTrace();
		return INPUT;
	}
	
	return retval;
}


	public ArrayList<TblMasterRes> getGradeList() throws SessionExpiredException{

		ArrayList<TblMasterRes> gradeList = null;
		try {
			getUserBean();
			GradeDao gradeDao = new GradeDao();
			gradeList = gradeDao.gradeList(searchTxt);
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}

		return gradeList;
	}
	
	
	
	
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getGradeValue() {
		return gradeValue;
	}
	public void setGradeValue(String gradeValue) {
		this.gradeValue = gradeValue;
	}
	public boolean isEditmode() {
		return editmode;
	}
	public void setEditmode(boolean editmode) {
		this.editmode = editmode;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public Long[] getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(Long[] deleteList) {
		this.deleteList = deleteList;
	}
	public String getSearchTxt() {
		return searchTxt;
	}
	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}

	
	
}