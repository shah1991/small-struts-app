package net.slide.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import net.slide.dao.ChklstDao;
import net.slide.dao.DeptDao;
import net.slide.dao.PostDao;
import net.slide.dao.TaskDao;
import net.slide.dao.UserDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblChklstGroup;
import net.slide.pojo.TblDept;
import net.slide.pojo.TblPostForum;
import net.slide.pojo.TblRole;
import net.slide.pojo.TblTaskPortfolio;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;
import net.slide.util.TransException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DepartmentAction extends ActionSupport {

	private boolean editmode;

	private Integer deptid;
	private String deptcd;
	private String deptname;
	private String deptstatus;
	
	private Integer deptmngrid;
	private Integer taskpid;
	private Integer postfid;
	private Integer chklstgid;
	
	private Long deletelist[];
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
			deptstatus = "Y";
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
	
	@SkipValidation
	public String edit() throws SessionExpiredException {
		try {
			getUserBean();
			DeptDao deptDao = new DeptDao();

			TblDept tblDept = deptDao.getDeptById(deptid);
			deptcd = tblDept.getDeptCd();
			deptname = tblDept.getDeptName();
			deptstatus = tblDept.getDeptStatus();
			deptid = tblDept.getDeptId();
			deptmngrid = tblDept.getTblUser().getUserId();
			if(tblDept.getTblTaskPortfolio() != null)
				taskpid = tblDept.getTblTaskPortfolio().getPortfolioId();
			if(tblDept.getTblPostForum() != null)
				postfid = tblDept.getTblPostForum().getId();
			if(tblDept.getTblChklstGroup() != null) 
				chklstgid = tblDept.getTblChklstGroup().getChklstGroupId();
			editmode = true;

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

	public String update() throws SessionExpiredException {
		String retval = INPUT;
		
		try {
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = getUserBean();
			
			editmode = true;
			
			DeptDao deptDao = new DeptDao();
			//Get the store By ID
			TblDept tblDept = deptDao.getDeptById(deptid);
			long cnt = 0;
			if(!tblDept.getDeptCd().trim().equalsIgnoreCase(deptcd.trim())) {
				cnt = deptDao.getDeptCountByCode(deptcd);
				if(cnt > 0) {
					throw new Exception("Another Dept with same Code already exists, Choose New Code..!!!");
				}
			}
			
			TblTaskPortfolio tblTaskPortfolio = null;
			if(taskpid != null){
				TaskDao taskDao = new TaskDao();
				tblTaskPortfolio = taskDao.getPortfolioById(taskpid);
			}
			
			TblPostForum tblPostForum = null;
			if(postfid != null){
				PostDao postDao = new PostDao();
				tblPostForum = postDao.getForumById(postfid);
			}


			TblChklstGroup tblChklstGroup = null;
			if(chklstgid != null){
				ChklstDao chklstDao=new ChklstDao();
				tblChklstGroup=chklstDao.getGroupById(chklstgid);
			}
			
			//Copy New value to Dept Entity
			tblDept.setDeptCd(deptcd);
			tblDept.setDeptName(deptname);
			tblDept.setDeptStatus(deptstatus);
			
			tblDept.setTblTaskPortfolio(tblTaskPortfolio);
			tblDept.setTblPostForum(tblPostForum);
			tblDept.setTblChklstGroup(tblChklstGroup);
			// Assign Department manage
			UserDao userDao = new UserDao();
			tblDept.setTblUser(userDao.getUserById(deptmngrid));
			
			tblDept.setUpdatedDate(new Date());
			tblDept.setUpdatedBy(curusr.getUserLogin());
			
			boolean result = deptDao.updateDept(tblDept);

			if (!result) {
				addActionError("Update failed..!!!");
			} else {
				addActionMessage("Dept Updated Successfully !!!!");
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

			DeptDao deptDao = new DeptDao();
			result = deptDao.deleteDept(deptid);
			
			if (!result) {
				addActionError("Delete failed..!!!");
			} else {
				addActionMessage("Dept Deleted Successfully !!!!");
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

	public String create() throws SessionExpiredException {
		String retval = INPUT;
		try {
			
			TblUser curusr = getUserBean();
			long cnt = 0;
			DeptDao deptDao = new DeptDao();
			if(deptcd != null) {
				cnt = deptDao.getDeptCountByCode(deptcd);
			}
			
			if(cnt > 0) {
				addActionError("Another Dept with same Code already exists, Choose New Code..!!!");
				return retval;
			}
			
			TblTaskPortfolio tblTaskPortfolio = null;
			if(taskpid != null){
				TaskDao taskDao = new TaskDao();
				tblTaskPortfolio = taskDao.getPortfolioById(taskpid);
			}
			
			TblPostForum tblPostForum = null;
			if(postfid != null){
				PostDao postDao = new PostDao();
				tblPostForum = postDao.getForumById(postfid);
			}

			TblChklstGroup tblChklstGroup = null;
			if(chklstgid != null){
				ChklstDao chklstDao=new ChklstDao();
				tblChklstGroup=chklstDao.getGroupById(chklstgid);
			}

			TblDept tblDept = new TblDept();
			tblDept.setDeptCd(deptcd);
			tblDept.setDeptName(deptname);
			tblDept.setDeptStatus(deptstatus);
			
			tblDept.setTblTaskPortfolio(tblTaskPortfolio);
			tblDept.setTblPostForum(tblPostForum);
			tblDept.setTblChklstGroup(tblChklstGroup);
			// Assign Department manage
			UserDao userDao = new UserDao();
			tblDept.setTblUser(userDao.getUserById(deptmngrid));
			
			tblDept.setUpdatedDate(new Date());
			tblDept.setUpdatedBy(curusr.getUserLogin());
			
			boolean result = deptDao.addDept(tblDept);

			if (!result) {
				addActionError("Add failed..!!!");
			} else {
				addActionMessage("Add Successfully !!!!");
				retval= INPUT;
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
	
	public ArrayList<TblMasterRes> getDeptList() throws SessionExpiredException{
		ArrayList<TblMasterRes> areaList = null;
		try {
			getUserBean();
			DeptDao deptDao = new DeptDao();
			areaList = deptDao.deptList(searchTxt);
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}

		return areaList;
	}

	public ArrayList<TblTaskPortfolio> getTaskPortfolios(){
		ArrayList<TblTaskPortfolio> resList = null;
		TaskDao taskDao = new TaskDao();
		resList = taskDao.getTaskPortfolioList();
		return resList;
	}

	public ArrayList<TblPostForum> getPostForums(){
		ArrayList<TblPostForum> resList = null;
		PostDao postDao = new PostDao();
		resList = postDao.getForumList();
		return resList;
	}

	public ArrayList<TblChklstGroup> getChklstGroups(){
		ArrayList<TblChklstGroup> resList = new ArrayList<TblChklstGroup>();
		ChklstDao postDao = new ChklstDao();
		resList = postDao.getGroupList();
		return resList;
	}
	
	public ArrayList<TblUser> getDeptManagers(){
		ArrayList<TblUser> usrList = null;
		UserDao userDao = new UserDao();
		TblRole tblRole = userDao.getRoleByCode("hq");
		if(tblRole != null)
			usrList = userDao.listLookup(tblRole);
		return usrList;
	}


	public boolean isEditmode() {
		return editmode;
	}


	public void setEditmode(boolean editmode) {
		this.editmode = editmode;
	}


	public Integer getDeptid() {
		return deptid;
	}


	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}


	public String getDeptcd() {
		return deptcd;
	}


	public void setDeptcd(String deptcd) {
		this.deptcd = deptcd;
	}


	public String getDeptname() {
		return deptname;
	}


	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}


	public String getDeptstatus() {
		return deptstatus;
	}


	public void setDeptstatus(String deptstatus) {
		this.deptstatus = deptstatus;
	}


	public Integer getDeptmngrid() {
		return deptmngrid;
	}


	public void setDeptmngrid(Integer deptmngrid) {
		this.deptmngrid = deptmngrid;
	}


	public Integer getTaskpid() {
		return taskpid;
	}


	public void setTaskpid(Integer taskpid) {
		this.taskpid = taskpid;
	}


	public Integer getPostfid() {
		return postfid;
	}


	public void setPostfid(Integer postfid) {
		this.postfid = postfid;
	}


	public Integer getChklstgid() {
		return chklstgid;
	}


	public void setChklstgid(Integer chklstgid) {
		this.chklstgid = chklstgid;
	}


	public Long[] getDeletelist() {
		return deletelist;
	}


	public void setDeletelist(Long[] deletelist) {
		this.deletelist = deletelist;
	}


	public String getSearchTxt() {
		return searchTxt;
	}


	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}


	
}
