package net.slide.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import net.slide.dao.PostDao;
import net.slide.dao.TopicDao;
import net.slide.pojo.TblPostForum;
import net.slide.pojo.TblPostTopic;
import net.slide.pojo.TblUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TopicDetailAction extends ActionSupport  {

	private String searchTxt;
	private int deleteList[];
	
	private int forumId;
	private int topicId;
	private String topicName;
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

		
		// Get User Name
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		boolean res = false;
		
		// Get Forum Info
		PostDao postDao = new PostDao();
		TblPostForum f = postDao.getForumById(forumId);
		
		// Set Topic detail
		TopicDao topicDao = new TopicDao();
		TblPostTopic t = topicDao.getTopicByName(f, topicName);
		if(t == null){
			t = new TblPostTopic();
			t.setTblPostForum(f);
			t.setTopicName(topicName);
			t.setTopicStatus(status);
			t.setUpdatedBy(curusr.getUserName());
			t.setUpdatedDate(new Date());
			res = topicDao.saveTopic(t);
		}else{
			msg = "Topic Name already found !!!";
		}

		if ( res == false) {
			addActionError("Add failed..!!!");
			ans += msg;
		}else{
			addActionMessage(topicName + "Added Successfully !!!!");
			ans = topicName;
		}
		
		inputStream = new ByteArrayInputStream(ans.getBytes());

		return retval;
	}
	
	

	public String detailupdate() {
		String retval = SUCCESS;
		String ans = "###Fail###";
		String msg = "Update failed..!!!";

		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		
		// Get Forum Info
		PostDao postDao = new PostDao();
		TblPostForum f = postDao.getForumById(forumId);
		
		// Set Topic detail
		TopicDao topicDao = new TopicDao();
		TblPostTopic t = topicDao.getTopicById(topicId);
		TblPostTopic tfind = topicDao.getTopicByName(f, topicName);
		
		boolean res = false;		
		if(tfind != null && tfind.getId() != t.getId()){
			msg = "Topic Name already found !!!";
		}else{
			t.setTblPostForum(f);
			t.setTopicName(topicName);
			t.setTopicStatus(status);
			t.setUpdatedBy(curusr.getUserName());
			t.setUpdatedDate(new Date());
			res = topicDao.saveTopic(t);
		}

		if ( res == false) {
			addActionError("Update failed..!!!");
			ans += msg;
		}else{
			addActionMessage("Updated Successfully !!!!");
			ans = topicName;
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
	
	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
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