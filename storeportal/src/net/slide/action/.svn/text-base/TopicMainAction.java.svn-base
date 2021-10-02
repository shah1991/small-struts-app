package net.slide.action;

import java.util.ArrayList;
import java.util.Map;

import net.slide.dao.PostDao;
import net.slide.dao.TopicDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblPostForum;
import net.slide.pojo.TblPostTopic;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TopicMainAction extends ActionSupport {

	private int forumId;
	private int topicId;
	private String topicName;
	private String status;
	private boolean actUpdate = false;;
	private String searchTxt;
	private int id;
	public String delete() {
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
	
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		
		if(curusr == null) {
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}

	public ArrayList<TblMasterRes> getTopicList() {
		TopicDao topicDao = new TopicDao(); 
		ArrayList<TblMasterRes> resList = topicDao.topicList(searchTxt);
		return resList;
	}

	public ArrayList<TblPostForum> getForumList() {
		PostDao postDao = new PostDao(); 
		ArrayList<TblPostForum> resList = postDao.getForumList();
		return resList;
	}
	

	
	/**
	 * 
	 * Master Bean Get/Set
	 */
	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}
	
	
	/**
	 *  Detail Actions
	 */
	
	public String detailadd() throws SessionExpiredException {
		
		try {
			getUserBean();
			TopicDao topicDao = new TopicDao();
			setStatus("Y");
			TblPostTopic f = topicDao.getTopicById(topicId);
			if(f != null) {
				setTopicName(f.getTopicName());
				setTopicId(f.getId());
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		
		setTopicName("");
		return "detail";
	}

	
	public String detailedit() throws SessionExpiredException {
		
		try {
			getUserBean();
			TopicDao topicDao = new TopicDao();
			TblPostTopic f = topicDao.getTopicById(topicId);
			if(f != null) {
				setTopicName(f.getTopicName());
				setTopicId(f.getId());
				setStatus(f.getTopicStatus());
				setForumId(f.getTblPostForum().getId());
				actUpdate = true;
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
		
		try {
			getUserBean();
			TopicDao topicDao = new TopicDao();
			TblPostTopic f = topicDao.getTopicById(topicId);
			if(f != null) {
				topicDao.deleteTopic(topicId);
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}
		
		return INPUT;
	}
	/**
	 * 
	 * Detail Bean Get/Set
	 */
	
	public boolean isActUpdate() {
		return actUpdate;
	}

	public void setActUpdate(boolean actUpdate) {
		this.actUpdate = actUpdate;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
