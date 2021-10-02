package net.slide.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import net.slide.dao.ChklstDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblChklstTemplate;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;
import net.slide.util.TransException;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class ChklstTemplateAction extends BaseAction {

	private int templateId;
	private String templateName;
	private String templateHeaders;
	private String responseType;
	private String updatedBy;
	private boolean actUpdate = false;;
	private String searchTxt;
	private String tempHeaders[]=null;
	private String active;
	private int sno;
	

	public ArrayList<TblMasterRes> getHeadersList() {
		ChklstDao chklstDao = new ChklstDao(); 
		ArrayList<TblMasterRes> resList=new ArrayList<TblMasterRes>();
		TblChklstTemplate t = chklstDao.getTemplateById(templateId);
		int i=1;
		StringTokenizer st=new StringTokenizer(t.getTemplateHeaders(), "|");
     	while(st.hasMoreTokens()){
     		TblMasterRes master=new TblMasterRes();
     		master.setId(i);
     		master.setMstName(st.nextElement().toString());
     		resList.add(master);
     		i++;
    		}
		return resList;
	}
	
	public String detaildelete() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao chklstDao = new ChklstDao();
			TblChklstTemplate t = chklstDao.getTemplateById(templateId);
			if(t != null) {
				chklstDao.deleteTemplate(templateId);
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
	
	

	/**
	 *  Detail Actions
	 */
	
	public String detailadd() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao chklstDao = new ChklstDao();
			TblChklstTemplate t = chklstDao.getTemplateById(templateId);
			if(t != null) {
				setTemplateName(t.getTemplateName());
				setTemplateHeaders(t.getTemplateHeaders());
			}
			setActive("Y");
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

	
	
	public String searchhead() throws SessionExpiredException {
		try {
			getUserBean();
			ChklstDao chklstDao = new ChklstDao();
			TblChklstTemplate t = chklstDao.getTemplateById(templateId);
			if(t != null) {
				setTemplateName(t.getTemplateName());
				
			}
			
	} catch (SessionExpiredException se) {
		addActionError("Session Expired..!!!");
		throw se;
	} catch (Exception e) {
		addActionError("Technical Error Happned..!!!");
		e.printStackTrace();
		return INPUT;
	}
	
	
	return "header";
}

	/**
	 * Header Actions
	 */
	
	public String headeradd() throws SessionExpiredException {
		
		try {
			getUserBean();
			setTemplateHeaders("");
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		
		
		return "headercreate";
	}

	
	

	


	/**
	 * Add Header Actions
	 */
	
	public String createheader() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao chklstDao = new ChklstDao();
			TblChklstTemplate t = chklstDao.getTemplateById(templateId);
			StringBuilder sb=new StringBuilder();
			
			if(t != null) {
				if(t.getTemplateHeaders()!=null){
				sb.append(t.getTemplateHeaders()).append("|");
				}
				sb.append(templateHeaders);
				chklstDao.updateTemplateHeader(sb.toString(), templateId);
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		
		
		return "header";
	}

	
	public String headeredit() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao chklstDao = new ChklstDao();
	
			TblChklstTemplate t = chklstDao.getTemplateById(templateId);
			
			if(t!=null){
			int i=1;
			StringTokenizer st=new StringTokenizer(t.getTemplateHeaders(), "|");
	     	while(st.hasMoreTokens()){
	     		if(sno==i){
	     			setTemplateHeaders(st.nextElement().toString());
	     		}else{
	     			st.nextElement();
	     		}
	     		i++;
	    		}
		}
	     	actUpdate=true;
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		
		
		return "headercreate";
	}

	
	public String headerupdate() throws SessionExpiredException {
		
		try {
			
			getUserBean();
			ChklstDao chklstDao = new ChklstDao();
			TblChklstTemplate t = chklstDao.getTemplateById(templateId);
			if(t!=null){			
			
			String res[] = StringUtils.split(t.getTemplateHeaders(), "|");
			res[sno-1]=templateHeaders;
			
			String sb=null;
			for(String val : res){
				if(sb==null) 
					sb = val;
				else
					sb=sb+"|"+val;
			}

			chklstDao.updateTemplateHeader(sb, templateId);
			}
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		
		
		return "header";
	}

	
	
	
	private InputStream inputStream;
	public InputStream getInputStream() {
	    return inputStream;
	} 
	
	public String detailcreate() {
		String retval = INPUT;
		String ans = "###Fail###";
		String msg = "Add failed..!!!";

		ChklstDao chklstDao = new ChklstDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		boolean res = false;
		TblChklstTemplate f = chklstDao.getTemplateByName(templateName);
		if(f == null){
			f = new TblChklstTemplate();
			f.setResponseType(active);
			f.setTemplateName(templateName);
			f.setUpdatedBy(curusr.getUserName());
			f.setUpdatedDate(new Date());
	    	res = chklstDao.saveTemplate(f);
		}else{
			msg = "Template Name already found !!!";
		}

		if ( res == false) {
			addActionError("Add failed..!!!");
			ans += msg;
		}else{
			addActionMessage(templateName + " Added Successfully !!!!");
			ans = templateName;
		}
		
		inputStream = new ByteArrayInputStream(ans.getBytes());

		return retval;
	}
	
	
	public String detailedit() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao chklstDao = new ChklstDao();
			TblChklstTemplate t = chklstDao.getTemplateById(templateId);
	
			
			if(t!=null){
				setTemplateId(t.getTemplateId());
				setTemplateName(t.getTemplateName());
				setActive(t.getResponseType());
				setTemplateHeaders(t.getTemplateHeaders());
				actUpdate=true;
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "detail";
		}
		
		return "detail";
	}
	
	

	public String detailupdate() {
		String retval = INPUT;
		String ans = "###Fail###";
		String msg = "Update failed..!!!";
		
		ChklstDao chklstDao = new ChklstDao();
		TblChklstTemplate t=new TblChklstTemplate();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
						
		boolean res = false;		
		
			t.setResponseType("Y");
			t.setTemplateId(templateId);
			t.setResponseType(active);
			t.setTemplateName(templateName);
			t.setUpdatedBy(curusr.getUserName());
			t.setUpdatedDate(new Date());
	    	t.setTemplateHeaders(templateHeaders);
			res = chklstDao.saveTemplate(t);
		

		if ( res == false) {
			addActionError("Update failed..!!!");
			ans += msg;
		}else{
			addActionMessage("Updated Successfully !!!!");
			ans = templateName;
		}
		
		inputStream = new ByteArrayInputStream(ans.getBytes());

		return retval;
	}

	public ArrayList<TblMasterRes> getTemplateList() {
		ChklstDao chklstDao = new ChklstDao(); 
		ArrayList<TblMasterRes> resList = chklstDao.getTemplateList(searchTxt);
		return resList;
	}
	
	public ArrayList<TblChklstTemplate> getTemplateAll() {
		ChklstDao chklstDao = new ChklstDao(); 
		ArrayList<TblChklstTemplate> resList = chklstDao.getTemplateAll();
		return resList;
	}
	
	
	
	public int getTemplateId() {
		return templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplateHeaders() {
		return templateHeaders;
	}
	public void setTemplateHeaders(String templateHeaders) {
		this.templateHeaders = templateHeaders;
	}
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public boolean isActUpdate() {
		return actUpdate;
	}

	public void setActUpdate(boolean actUpdate) {
		this.actUpdate = actUpdate;
	}
	

	public String getSearchTxt() {
		return searchTxt;
	}



	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}



	public String[] getTempHeaders() {
		return tempHeaders;
	}

	public void setTempHeaders(String[] tempHeaders) {
		this.tempHeaders = tempHeaders;
	}
	
	
	public String getActive() {
		return active;
	}



	public void setActive(String active) {
		this.active = active;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}


	
}
