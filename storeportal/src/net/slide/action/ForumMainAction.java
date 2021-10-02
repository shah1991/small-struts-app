package net.slide.action;

import java.util.ArrayList;
import java.util.Map;

import net.slide.dao.PostDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblPostForum;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;
import net.slide.util.TransException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ForumMainAction extends ActionSupport {

	private int forumId;
	private String forumName;
	private String status;
	private boolean actUpdate = false;;
	private String searchTxt;
	
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

	public ArrayList<TblMasterRes> getGroupList() {
		PostDao postDao = new PostDao(); 
		ArrayList<TblMasterRes> resList = postDao.forumList(searchTxt);
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
			PostDao postDao = new PostDao();
			setStatus("Y");
			TblPostForum f = postDao.getForumById(forumId);
			if(f != null) {
				setForumName(f.getForumName());
				setForumId(f.getId());
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		
		setForumName("");
		return "detail";
	}

	
	public String detailedit() throws SessionExpiredException {
		
		try {
			getUserBean();
			PostDao postDao = new PostDao();
			TblPostForum f = postDao.getForumById(forumId);
			if(f != null) {
				setForumName(f.getForumName());
				setForumId(f.getId());
				setStatus(f.getStatus());
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
			PostDao postDao = new PostDao();
			TblPostForum f = postDao.getForumById(forumId);
			if(f != null) {
				postDao.deleteForum(forumId);
			}
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		}  catch (TransException e) {
			addActionError(e.getMessage());
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

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
