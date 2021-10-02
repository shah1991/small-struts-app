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
import net.slide.pojo.TblChklstTemplate;
import net.slide.pojo.TblChklstTopic;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;
import net.slide.util.TransException;

@SuppressWarnings("serial")
public class ChklstTopicAction extends BaseAction {

	private int chklsttopicid;
	private String chklsttopicname;
	private String chklststatus;
	private boolean editmode = false;;
	private String searchTxt;
	private int groupid;
	private int templateId;
	private int chklstGroupId;
	

   public ArrayList<TblMasterRes> getTopicList() {
		ChklstDao chklstDao = new ChklstDao(); 
		ArrayList<TblMasterRes> resList = chklstDao.topicList(searchTxt);
		return resList;
	}
	
	public String search() throws SessionExpiredException {
		try {
			getUserBean();
			//String ans="Search";
		//	inputStream = new ByteArrayInputStream(ans.getBytes());
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

	
	
	public ArrayList<TblChklstGroup> getGroupAll(){
		ChklstDao chklstDao = new ChklstDao(); 
		ArrayList<TblChklstGroup> resList = chklstDao.getGroupList();
		return resList;
	}
	
	public ArrayList<TblChklstTemplate> getTemplateAll() {
		ChklstDao chklstDao = new ChklstDao(); 
		ArrayList<TblChklstTemplate> resList = chklstDao.getTemplateAll();
		return resList;
	}
	/**
	 *  Detail Actions
	 */
	
	public String detailadd() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao chklstDao = new ChklstDao();
			TblChklstGroup tblChklstGroup = chklstDao.getGroupById(chklsttopicid);
			if(tblChklstGroup != null) {
				setChklsttopicname(tblChklstGroup.getChklstGroupName());
				setChklsttopicid(tblChklstGroup.getChklstGroupId());
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
		
		setChklsttopicname("");
		return "detail";
	}

	
	public String detailedit() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao chklstDao = new ChklstDao();
			TblChklstTopic f = chklstDao.getTopicById(chklsttopicid);
			if(f != null) {
				
				setChklsttopicname(f.getTopicName());
				setChklsttopicid(f.getTopicId());
				setChklststatus(f.getTopicStatus());
				setTemplateId(f.getTblChklstTemplate().getTemplateId());
				setGroupid(f.getTblChklstGroup().getChklstGroupId());
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
		
		try {
			getUserBean();
			ChklstDao chklstDao = new ChklstDao();
			TblChklstTopic t=chklstDao.getTopicById(chklsttopicid);
			if(t != null) {
				chklstDao.deleteTopic(chklsttopicid);
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		}catch (TransException e) {
			addActionError(e.getMessage());
		}  catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}
		
		return INPUT;
	}
	

	private InputStream inputStream;
	public InputStream getInputStream() {
	    return inputStream;
	} 
	
	public String detailcreate() {
	
		String ans = "###Fail###";
		String msg = "Add failed..!!!";
		
		ChklstDao chklstDao = new ChklstDao();

		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		boolean res = false;
		TblChklstGroup f = chklstDao.getGroupById(chklstGroupId);
		TblChklstTemplate ct=chklstDao.getTemplateById(templateId);

		TblChklstTopic t = chklstDao.getTopicByName(f, chklsttopicname);
		if(t == null){
			t = new TblChklstTopic();
			t.setTblChklstGroup(f);
			t.setTblChklstTemplate(ct);
			t.setTopicName(chklsttopicname);
			t.setTopicStatus(chklststatus);
		
			t.setUpdatedBy(curusr.getUserName());
			t.setUpdatedDate(new Date());
			res = chklstDao.saveTopic(t);
		}else{
			msg = "Topic Name already found !!!";
		}

		if ( res == false) {
			addActionError("Add failed..!!!");
			ans += msg;
		}else{
			addActionMessage(chklsttopicname + "Added Successfully !!!!");
			ans = chklsttopicname;
		}
		
		inputStream = new ByteArrayInputStream(ans.getBytes());

		return INPUT;
	}
	
	

	public String detailupdate() {
		String retval = INPUT;
		String ans = "###Fail###";
		String msg = "Update failed..!!!";

		ChklstDao chklstDao = new ChklstDao();
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		TblChklstGroup f = chklstDao.getGroupById(chklstGroupId);
		
		TblChklstTopic t=chklstDao.getTopicById(chklsttopicid);
		

		TblChklstTemplate tm=chklstDao.getTemplateById(templateId);

		TblChklstTopic tt = chklstDao.getTopicByName(f, chklsttopicname);
		
		boolean res = false;		
		if(tt != null && tt.getTopicId() != t.getTopicId()){
			msg = "Topic Name already found !!!";
		}else{
			
			t.setTblChklstTemplate(tm);
			t.setTblChklstGroup(f);
			t.setTopicName(chklsttopicname);
			t.setTopicStatus(chklststatus);
			t.setUpdatedBy(curusr.getUserName());
			t.setUpdatedDate(new Date());
			res = chklstDao.saveTopic(t);
		}

		if ( res == false) {
			addActionError("Update failed..!!!");
			ans += msg;
		}else{
			addActionMessage("Updated Successfully !!!!");
			ans = chklsttopicname;
		}
		
		inputStream = new ByteArrayInputStream(ans.getBytes());

		return retval;
	}
	/**
	 * 
	 * Detail Bean Get/Set
	 */

	public int getChklsttopicid() {
		return chklsttopicid;
	}

	public void setChklsttopicid(int chklsttopicid) {
		this.chklsttopicid = chklsttopicid;
	}

	public String getChklsttopicname() {
		return chklsttopicname;
	}

	public void setChklsttopicname(String chklsttopicname) {
		this.chklsttopicname = chklsttopicname;
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

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public int getChklstGroupId() {
		return chklstGroupId;
	}

	public void setChklstGroupId(int chklstGroupId) {
		this.chklstGroupId = chklstGroupId;
	}

	

	
	
}
