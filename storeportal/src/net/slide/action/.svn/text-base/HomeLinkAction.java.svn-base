package net.slide.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.StrutsStatics;

import net.slide.dao.LinkDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblLink;
import net.slide.pojo.TblTask;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class HomeLinkAction  extends BaseAction {

	private int linkId;
	private int id;
	private String urlOption;
	private String url;
	private String active;
	private String title;
	private String iconPathName;
	private String iconPath;
	private Integer orderNo;
	private String searchTxt;
	private boolean updateMode;
	private List<File> iconAttach = new ArrayList<File>();
	private List<String> iconAttachContentType = new ArrayList<String>();
	private List<String> iconAttachFileName = new ArrayList<String>();
    private List<TblMasterRes> listUrl= new ArrayList<TblMasterRes>();	

	

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
	
	public String view() throws SessionExpiredException {
		
		try {
			getUserBean();
			LinkDao linkDao = new LinkDao();
			TblLink t = linkDao.getLinkById(linkId);
			if(t != null) {
				setLinkId(t.getLinkId());
				setOrderNo(t.getOrderNo());
				setIconPath(t.getIconPath());
				setUrl(t.getUrl());
				setTitle(t.getTitle());
				setActive(t.getActive());
				setUrlOption(t.getUrlOption());
				
				if(urlOption.equals("O")){
					
				}else{
				setId(t.getUrlId());
				setListUrl(linkDao.listURL(urlOption));
				}
				
				updateMode=true;
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}
		
		return "detail";
	}
	

	public String selecturl() throws SessionExpiredException {
		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
			LinkDao linkDao = new LinkDao();
			setListUrl(linkDao.listURL(urlOption));
			
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
	
	
	
  public String selectupdateurl() throws SessionExpiredException {
		
		try {
			updateMode=true;
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
			LinkDao linkDao = new LinkDao();
			setListUrl(linkDao.listURL(urlOption));
			TblLink t = linkDao.getLinkById(linkId);
			if(t != null) {
				setLinkId(t.getLinkId());
				
				setIconPath(t.getIconPath());
						
				if(urlOption.equals("O")){
					
				}else{
				setId(t.getUrlId());
				setListUrl(linkDao.listURL(urlOption));
				}}
				
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
	

	/**
	 *  Detail Actions
	 */
	
	public String detailadd() throws SessionExpiredException {
		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
			LinkDao linkDao = new LinkDao();
			TblLink t = linkDao.getLinkById(linkId);
			if(t != null) {
				t.setActive(active);
				t.setIconPath(iconPath);
				t.setOrderNo(orderNo);
				t.setTitle(title);
				t.setUrl(url);
				t.setUpdatedBy(curusr.getUserName());
				t.setUpdatedDate(new Date());
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
	
	
	

	/**
	 *  Detail Actions
	 */
	
	public String create() throws SessionExpiredException {
		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
			LinkDao linkDao = new LinkDao();
			TblLink t = new TblLink();
			
				t.setActive(active);
			//	
				if(iconAttach.size()!=0){
				iconPathName = importFile(iconAttach.get(0), iconAttachFileName.get(0));
				t.setIconPath(iconPathName);
				}

				// Set Post detail
				if(urlOption.equals("O")){
					  if(url.toLowerCase().contains("http://")){
				    	  
				      }else{
				    	  url="http://"+url;
				      }
					  t.setUrl(url);
				}
				
				t.setOrderNo(orderNo);
				t.setTitle(title);				
				t.setUrlId(id);
				t.setUrlOption(urlOption);
				t.setUpdatedBy(curusr.getUserName());
				t.setUpdatedDate(new Date());
				linkDao.addLink(t);
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
	
	
	public String importFile(File mfile, String filename) throws Exception{
		String retval = null;
		
		try{
			
			String rootpath = ((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
		
			String path = "/icons";
				if(mfile.isFile() == false)
				return retval;
				
			File rp = new File(rootpath+path);
			if(rp == null || rp.exists() == false)
				rp.mkdir();
			
			File importpath= new File(rootpath+path+"/");
			if(importpath == null || importpath.exists() == false)
				importpath.mkdir();
			
			File newpath = new File(rootpath+path+"/"+filename);
			FileUtils.copyFile(mfile, newpath);
			if(newpath != null && mfile.isFile()){
				
				retval = path+"/"+filename;
			}

		}catch (Exception e) {
			throw e;
		}
		return retval;
	}
	
	

	public String detailupdate() throws SessionExpiredException {
		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
			LinkDao linkDao = new LinkDao();
			TblLink t = linkDao.getLinkById(linkId);
			if(t != null) {
				t.setLinkId(linkId);
				t.setActive(active);
				//t.setIconPath(iconPath);
				t.setOrderNo(orderNo);
				t.setTitle(title);
				t.setUrl(url);
				t.setUrlId(id);
				t.setUrlOption(urlOption);
				t.setUpdatedBy(curusr.getUserName());
				t.setUpdatedDate(new Date());
				if(iconAttach.size()!=0){
				iconPathName = importFile(iconAttach.get(0), iconAttachFileName.get(0));
				t.setIconPath(iconPathName);
				}
				linkDao.addLink(t);
			}
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
	
	
	public String delete() throws SessionExpiredException {
		
		try {
			getUserBean();
			LinkDao linkDao = new LinkDao();
			TblLink t = linkDao.getLinkById(linkId);
			if(t != null) {
				linkDao.deleteLink(linkId);
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
	
	
	public ArrayList<TblLink> getLinkList(){
		LinkDao linkDao=new LinkDao();
		
		ArrayList<TblLink> aList=null;
		aList=linkDao.linkList(searchTxt);
		return aList;
	}
	


	public int getLinkId() {
		return linkId;
	}
	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}

	public boolean isUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	public String getIconPathName() {
		return iconPathName;
	}

	public void setIconPathName(String iconPathName) {
		this.iconPathName = iconPathName;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public List<File> getIconAttach() {
		return iconAttach;
	}

	public void setIconAttach(List<File> iconAttach) {
		this.iconAttach = iconAttach;
	}

	public List<String> getIconAttachContentType() {
		return iconAttachContentType;
	}

	public void setIconAttachContentType(List<String> iconAttachContentType) {
		this.iconAttachContentType = iconAttachContentType;
	}

	public List<String> getIconAttachFileName() {
		return iconAttachFileName;
	}

	public void setIconAttachFileName(List<String> iconAttachFileName) {
		this.iconAttachFileName = iconAttachFileName;
	}

	public List<TblMasterRes> getListUrl() {
		return listUrl;
	}

	public void setListUrl(List<TblMasterRes> listUrl) {
		this.listUrl = listUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrlOption() {
		return urlOption;
	}

	public void setUrlOption(String urlOption) {
		this.urlOption = urlOption;
	}

	
	

	
	
	
}
