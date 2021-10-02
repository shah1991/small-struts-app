package net.slide.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import net.slide.dao.PostDao;
import net.slide.pojo.TblPostForum;
import net.slide.pojo.TblUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ForumDetailAction extends ActionSupport  {

	private String searchTxt;
	private int deleteList[];
	
	private int forumId;
	private String forumName;
	private String status;
	
	private boolean actUpdate = false;;

	private InputStream inputStream;
	public InputStream getInputStream() {
	    return inputStream;
	} 
	
	public String detailadd() {
		String retval = SUCCESS;
		String ans = "###Fail###";
		String msg = "Add failed..!!!";

		PostDao postDao = new PostDao();

		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		boolean res = false;
		TblPostForum f = postDao.getForumByName(forumName);
		if(f == null){
			f = new TblPostForum();
			f.setForumName(forumName);
			f.setStatus(status);
			f.setUpdatedBy(curusr.getUserName());
			f.setUpdatedDate(new Date());
			res = postDao.saveForum(f);
		}else{
			msg = "Forum Name already found !!!";
		}

		if ( res == false) {
			addActionError("Add failed..!!!");
			ans += msg;
		}else{
			addActionMessage(forumName + "Added Successfully !!!!");
			ans = forumName;
		}
		
		inputStream = new ByteArrayInputStream(ans.getBytes());

		return retval;
	}
	
	

	public String detailupdate() {
		String retval = SUCCESS;
		String ans = "###Fail###";
		String msg = "Update failed..!!!";

		PostDao postDao = new PostDao();
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		TblPostForum f = postDao.getForumById(forumId);
		
		TblPostForum ffind = postDao.getForumByName(forumName);
		
		boolean res = false;		
		if(ffind != null && ffind.getId() != f.getId()){
			msg = "Forum Name already found !!!";
		}else{
			f.setForumName(forumName);
			f.setStatus(status);
			f.setUpdatedBy(curusr.getUserName());
			f.setUpdatedDate(new Date());
			res = postDao.saveForum(f);
		}

		if ( res == false) {
			addActionError("Update failed..!!!");
			ans += msg;
		}else{
			addActionMessage("Updated Successfully !!!!");
			ans = forumName;
		}
		
		inputStream = new ByteArrayInputStream(ans.getBytes());

		return retval;
	}
	
	

	public String detailsearch() {
		return INPUT;
	}

	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}

	public int[] getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(int[] deleteList) {
		this.deleteList = deleteList;
	}

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isActUpdate() {
		return actUpdate;
	}

	public void setActUpdate(boolean actUpdate) {
		this.actUpdate = actUpdate;
	}
}