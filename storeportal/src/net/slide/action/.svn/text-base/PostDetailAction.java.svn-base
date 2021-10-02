package net.slide.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.slide.dao.PostDao;
import net.slide.dao.StoreDao;
import net.slide.dao.UserDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblArea;
import net.slide.pojo.TblPost;
import net.slide.pojo.TblPostArea;
import net.slide.pojo.TblPostStore;
import net.slide.pojo.TblPostUpload;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblStoreRes;
import net.slide.pojores.TblUploadRes;
import net.slide.util.CommonUtil;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.slide.util.UrlBreadCrumb;

@SuppressWarnings("serial")
public class PostDetailAction extends ActionSupport  {

	private String searchTxt;
	private int deleteList[];
	private Integer forumId;
	private Integer topicId;   
        private String breadCrumbt1;    
        private String prevUrl11;        
	private int postId;
	private int id;
	private int delId;
	private String status;
	private String postSubject;
	private String postContents;
	private String postAttachPath;
	private String postAttchName;
	private List<File> postAttach = new ArrayList<File>();
	private List<String> postAttachContentType = new ArrayList<String>();
	private List<String> postAttachFileName = new ArrayList<String>();
	private int slist[];
	private int alist[];
	private int storeId;
	private int areaId;
	private String recType;
	private boolean actUpdate = false;;
	private String fromUser;
	private int forumOwnerRole;
	private int forumOwnerSite;
	ArrayList<TblUploadRes> uploadRes=new ArrayList<TblUploadRes>();
	ArrayList<TblPostUpload> uploadList=new ArrayList<TblPostUpload>();
	/**
	 *  Detail Actions
	 */
	

	
	

	public String areastore() throws SessionExpiredException {
		
		try {
			
		
			
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "areacreate";
		}
		
		
		return "areacreate";
	}
	
	public String storePopup() throws SessionExpiredException {
			
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
			
		
			return "storepopup";
	}
	
	public String storeList() throws SessionExpiredException {
		
		try {
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "areacreate";
		}
		
	
		return "storelist";
	}
	
	public String areaPopup() throws SessionExpiredException {
			
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
			
		
			return "areapopup";
		}
	public String detailstore() throws SessionExpiredException {
		
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
	
	
	



	public String detailedit() throws SessionExpiredException {
		
		try {
			ArrayList<TblPostStore> storeList=new ArrayList<TblPostStore>();
			ArrayList<TblPostArea> areaList=new ArrayList<TblPostArea>();
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("uploadRes",null);
			PostDao postDao = new PostDao();
			TblPost p = postDao.getPostById(id);
			if(p != null) {
				setPostSubject(p.getPostSubject());
				setPostContents(p.getPostContents());
				setStatus(p.getPostStatus());
				setPostAttchName(p.getPostAttachFilename());
				setPostAttachPath(p.getPostAttachPath());
				storeList=postDao.getStores(p);
				areaList=postDao.getAreas(p);
				int i=0;
				int j=0;
				int[] storeArray = new int[storeList.size()];
				int[] areaArray = new int[areaList.size()];
				for(TblPostStore tps:storeList){
					storeArray[i]=tps.getTblStore().getStoreId();
					i++;
				}
				if(storeList.size()!=0){
					slist=storeArray;
				}
				for(TblPostArea tps:areaList){
					areaArray[j]=tps.getTblArea().getAreaId();
					j++;
				}
				if(areaList.size()!=0){
					alist=areaArray;
				}
			
				actUpdate = true;
				setUploadList(postDao.getPostUpload(p));
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
	
	//shan start
	
	public String postview() throws SessionExpiredException {
			
			try {
                                ArrayList<TblPostStore> storeList=new ArrayList<TblPostStore>();
                                ArrayList<TblPostArea> areaList=new ArrayList<TblPostArea>();                            
				getUserBean();
				Map<String, Object> session = ActionContext.getContext().getSession();
				 session.put("uploadRes",null);
				PostDao postDao = new PostDao();
				TblPost p = postDao.getPostById(id);
				UserDao userDao=new UserDao();
				if(p != null) {
					TblUser u = userDao.getUserById(p.getPostOwnerSite());
					setFromUser(u.getUserName());
					setPostSubject(p.getPostSubject());
					setPostContents(p.getPostContents());
					setStatus(p.getPostStatus());
				//	setPostAttachPath(p.getPostAttachPath());
				//	setPostAttchName(p.getPostAttachFilename());
					if(forumOwnerRole==1){
						postDao.getUpdateStorePost(p,storeId);
					}else if(forumOwnerRole==2){
						postDao.getUpdateAreaPost(p,areaId);
					}
				storeList=postDao.getStores(p);
				areaList=postDao.getAreas(p);
				int i=0;
				int j=0;
				int[] storeArray = new int[storeList.size()];
				int[] areaArray = new int[areaList.size()];
				for(TblPostStore tps:storeList){
					storeArray[i]=tps.getTblStore().getStoreId();
					i++;
				}
				if(storeList.size()!=0){
					slist=storeArray;
				}
				for(TblPostArea tps:areaList){
					areaArray[j]=tps.getTblArea().getAreaId();
					j++;
				}
				if(areaList.size()!=0){
					alist=areaArray;
				}
                                        
					setUploadList(postDao.getPostUpload(p));
					
				}
			} catch (SessionExpiredException se) {
				addActionError("Session Expired..!!!");
				throw se;
			} catch (Exception e) {
				addActionError("Technical Error Happned..!!!");
				e.printStackTrace();
				return INPUT;
			}
			
			return "view";
	}
	//shan end
	
	public String detailcreate() throws SessionExpiredException {
		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			 session.put("uploadRes",null);
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		
		setPostSubject("");
		setPostContents("");
		return INPUT;
	}
	public String detailadd() throws SessionExpiredException {
		String retval = "managehq";
		
			
		try {
			getUserBean();
			
			// Get User Name
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
			boolean res = false;
			PostDao postDao = new PostDao();
			TblPost p = new TblPost();
			
			if(session.get("uploadRes")!=null){
				  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
			}
			// Set Post detail
			p.setPostSubject(postSubject);
			p.setPostContents(postContents);
			p.setPostStatus(status);
		
			p.setUpdatedBy(curusr.getUserName());
			p.setUpdatedDate(new Date());
			p.setPostOwnerRole(forumOwnerRole);
			p.setPostOwnerSite(curusr.getUserId());
	    	res = postDao.savePost(p, topicId,alist,slist,uploadRes);
			
			if ( res == false) {
				addActionError("Add failed..!!!");
				retval = INPUT;
			}else{
				addActionMessage(postSubject + "Added Successfully !!!!");
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
	 	
	
	public String detailareacreate() throws SessionExpiredException {
		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			 session.put("uploadRes",null);
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "areacreate";
		}
		
		setPostSubject("");
		setPostContents("");
		return "areacreate";
	}

	public String detailareaadd() throws SessionExpiredException {
		String retval = "managearea";
		
		try {
			getUserBean();
			
			// Get User Name
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
			
			boolean res = false;
			PostDao postDao = new PostDao();
			TblPost p = new TblPost();
			if(session.get("uploadRes")!=null){
				  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
			}
//			// get file path
//			if(postAttach.size()!=0){
//				for(int i=0;i<postAttach.size();i++){
//					TblUploadRes u=new TblUploadRes();
//					String postAttachPath = importFile(postAttach.get(i), postAttachFileName.get(i));
//					u.setAttachName(postAttachFileName.get(i));
//					u.setAttachPath(postAttachPath);
//					ures.add(u);
//			}
			p.setPostSubject(postSubject);
			p.setPostContents(postContents);
			p.setPostStatus(status);
			p.setUpdatedBy(curusr.getUserName());
			p.setUpdatedDate(new Date());
			p.setPostOwnerRole(forumOwnerRole);
			p.setPostOwnerSite(curusr.getUserId());
	   
	    	res = postDao.savePost(p, topicId,alist,slist,uploadRes);
			
			if ( res == false) {
				addActionError("Add failed..!!!");
				retval = "areacreate";
			}else{
				addActionMessage(postSubject + "Added Successfully !!!!");
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "areacreate";
		}
		return retval;
	}
	 	
	@SuppressWarnings("unchecked")
	public String uploadEditDoc() throws SessionExpiredException {
		String retval = INPUT;
		actUpdate = true;
		Map<String, Object> session = ActionContext.getContext().getSession();
		PostDao postDao = new PostDao();
		TblPost p = postDao.getPostById(id); 
		TblUser curusr = (TblUser)session.get("loginUser");
		if(curusr.getTblRole().getRoleId()==2){
			retval="areacreate";
		}
		try {
			if(session.get("uploadRes")!=null){
			  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
			}
			if(postAttach.size()!=0){
				for(int i=0;i<postAttach.size();i++){
					TblUploadRes u=new TblUploadRes();
					String postAttachPath = importFile(postAttach.get(i), postAttachFileName.get(i));
					u.setAttachName(postAttachFileName.get(i));
					u.setAttachPath(postAttachPath);
					uploadRes.add(u);
				
				}
				session.put("uploadRes",uploadRes);
			}
			
			setUploadList(postDao.getPostUpload(p));
		
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return retval;
		}
		
		
		return retval;
	}
	
	
	
public String deleteEditDoc() throws SessionExpiredException {
		String retval=INPUT;
		actUpdate = true;
		PostDao postDao = new PostDao();
		TblPost p = postDao.getPostById(id); 
	
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			
			TblUser curusr = (TblUser)session.get("loginUser");
			if(curusr.getTblRole().getRoleId()==2){
				retval="areacreate";
			}
			if(session.get("uploadRes")!=null){
				  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
				  uploadRes.remove(delId);
			      session.put("uploadRes",uploadRes);
			}
			setUploadList(postDao.getPostUpload(p));
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return retval;
		}
		
		
		return retval;
	}
	
	@SuppressWarnings("unchecked")
	public String uploadDoc() throws SessionExpiredException {
		String retval = INPUT;
		Map<String, Object> session = ActionContext.getContext().getSession();
	
		
		TblUser curusr = (TblUser)session.get("loginUser");
		if(curusr.getTblRole().getRoleId()==2){
			retval="areacreate";
		}
		try {
			if(session.get("uploadRes")!=null){
			  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
			}
			if(postAttach.size()!=0){
				for(int i=0;i<postAttach.size();i++){
					TblUploadRes u=new TblUploadRes();
					String postAttachPath = importFile(postAttach.get(i), postAttachFileName.get(i));
					u.setAttachName(postAttachFileName.get(i));
					u.setAttachPath(postAttachPath);
					uploadRes.add(u);
				
				}
				session.put("uploadRes",uploadRes);
			}
			
			
		
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return retval;
		}
		
		
		return retval;
	}
	
	
	
public String deleteDoc() throws SessionExpiredException {
		String retval=INPUT;
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			
			TblUser curusr = (TblUser)session.get("loginUser");
			if(curusr.getTblRole().getRoleId()==2){
				retval="areacreate";
			}
			if(session.get("uploadRes")!=null){
				  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
				  uploadRes.remove(delId);
			      session.put("uploadRes",uploadRes);
			}
	    	
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return retval;
		}
		
		
		return retval;
	}
	
	
	public String importFile(File mfile, String filename) throws Exception{
		String retval = null;
		
		try{
			
			String rootpath = ((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
			String path = "/attachments";
			String curdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
			String timestamp = new SimpleDateFormat("mm24hhss").format(new Date());
			
			if(mfile.isFile() == false)
				return retval;
				
			File rp = new File(rootpath+path);
			if(rp == null || rp.exists() == false)
				rp.mkdir();
			
			path=path+"/posts";
			File rp1 = new File(rootpath+path);
			if(rp1 == null || rp1.exists() == false)
				rp1.mkdir();
			
			File importpath= new File(rootpath+path+"/"+curdate);
			if(importpath == null || importpath.exists() == false)
				importpath.mkdir();
			
			File newpath = new File(rootpath+path+"/"+curdate+"/"+timestamp+"_"+filename);
			FileUtils.copyFile(mfile, newpath);
			if(newpath != null && mfile.isFile()){
				//retval = newpath.getPath();
				retval = path+"/"+curdate+"/"+timestamp+"_"+filename;
			}

		}catch (Exception e) {
			throw e;
		}
		return retval;
	}
	

	public String detailupdate() throws SessionExpiredException {
		String retval = SUCCESS;
		
		// Get User Name
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		boolean res = false;
		try{
	
		getUserBean();
		//Set Post detail
		PostDao postDao = new PostDao();
		TblPost p = postDao.getPostById(id); 
		if(p != null){
			p.setPostSubject(postSubject);
			p.setPostContents(postContents);
			p.setPostOwnerRole(forumOwnerRole);
			p.setPostOwnerSite(curusr.getUserId());
	   
			p.setPostStatus(status);
			p.setUpdatedBy(curusr.getUserName());
			p.setUpdatedDate(new Date());
			if(session.get("uploadRes")!=null){
				  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
			}
//			if(postAttach.size()!=0){
//				for(int i=0;i<postAttach.size();i++){
//					TblUploadRes u=new TblUploadRes();
//					String postAttachPath = importFile(postAttach.get(i), postAttachFileName.get(i));
//					u.setAttachName(postAttachFileName.get(i));
//					u.setAttachPath(postAttachPath);
//					ures.add(u);
//			}
//				
//		}
			res = postDao.savePost(p, topicId,alist,slist,uploadRes);	
		}
		
		if ( res == false) {
			addActionError("Update failed..!!!");
			retval = INPUT;
		}else{
			addActionMessage(postSubject + "Updated Successfully !!!!");
		}
	}catch (SessionExpiredException se) {
		addActionError("Session Expired..!!!");
		throw se;
	} catch (Exception e) {
		addActionError("Technical Error Happned..!!!");
		e.printStackTrace();
		return "INPUT";
	}
		return retval;
	}
	
	
	public String detailareaupdate() throws SessionExpiredException {
		String retval = "managearea";
		
		// Get User Name
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		boolean res = false;
		try{
		getUserBean();
	
		
		//Set Post detail
		PostDao postDao = new PostDao();
		TblPost p = postDao.getPostById(id); 
		if(p != null){
			p.setPostSubject(postSubject);
			p.setPostContents(postContents);
			p.setPostOwnerRole(forumOwnerRole);
			p.setPostOwnerSite(curusr.getUserId());
	   
			p.setPostStatus(status);
			p.setUpdatedBy(curusr.getUserName());
			p.setUpdatedDate(new Date());
			if(session.get("uploadRes")!=null){
				  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
			}
//		
//			if(postAttach.size()!=0){
//				for(int i=0;i<postAttach.size();i++){
//					TblUploadRes u=new TblUploadRes();
//					String postAttachPath = importFile(postAttach.get(i), postAttachFileName.get(i));
//					u.setAttachName(postAttachFileName.get(i));
//					u.setAttachPath(postAttachPath);
//					ures.add(u);
//			}
//				
//			}
			res = postDao.savePost(p, topicId,alist,slist,uploadRes);
		}
		
		if ( res == false) {
			addActionError("Update failed..!!!");
			retval = "areacreate";
		}else{
			addActionMessage(postSubject + "Updated Successfully !!!!");
		}}
		catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "areacreate";
		}
		return retval;
	}
	
	
	public String detailareaedit() throws SessionExpiredException {
		ArrayList<TblPostStore> storeList=new ArrayList<TblPostStore>();
			try {
				
			
				getUserBean();
				Map<String, Object> session = ActionContext.getContext().getSession();
				 session.put("uploadRes",null);
				PostDao postDao = new PostDao();
				TblPost p = postDao.getPostById(id);
				if(p != null) {
					setPostSubject(p.getPostSubject());
					setPostContents(p.getPostContents());
					setStatus(p.getPostStatus());
					setPostAttchName(p.getPostAttachFilename());
					setPostAttachPath(p.getPostAttachPath());
					storeList=postDao.getStores(p);
					int i=0;
					int[] storeArray = new int[storeList.size()];
					for(TblPostStore tps:storeList){
						storeArray[i]=tps.getTblStore().getStoreId();
						i++;
					}
					if(storeList.size()!=0){
						slist=storeArray;
					}
					actUpdate = true;
					setUploadList(postDao.getPostUpload(p));
				}
			} catch (SessionExpiredException se) {
				addActionError("Session Expired..!!!");
				throw se;
			} catch (Exception e) {
				addActionError("Technical Error Happned..!!!");
				e.printStackTrace();
				return "areacreate";
			}
			
			return "areacreate";
		}
	
	public String detaildelete() throws SessionExpiredException {
		
		try {
			Map<String, Object> session = ActionContext.getContext().getSession();
			 session.put("uploadRes",null);
			getUserBean();
			//Set Post detail
			PostDao postDao = new PostDao();
			TblPost p = postDao.getPostById(id); 
			if(p != null) {
				postDao.deletePost(id);
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
public String areadelete() throws SessionExpiredException {
		
		try {
			Map<String, Object> session = ActionContext.getContext().getSession();
			 session.put("uploadRes",null);
			getUserBean();
			//Set Post detail
			PostDao postDao = new PostDao();
			TblPost p = postDao.getPostById(id); 
			if(p != null) {
				postDao.deletePost(id);
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}
		return "managearea";
	}
	
	private TblUser getUserBean() throws SessionExpiredException, Exception {
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
			
			if(forumId!=null){
				session.put("forumId",forumId);
			}else if(session.get("forumId")!=null){
				forumId=(Integer)session.get("forumId");
			
			}
			if(topicId!=null){
				session.put("topicId",topicId);
			}else if(session.get("topicId")!=null){
				topicId=(Integer)session.get("topicId");
			
			}
                        
                            breadCrumbt1=(String)session.get("breadCrumbt1");
                            String url=UrlBreadCrumb.getUrl();
                            session.put("prevUrl11",url);
                            prevUrl11=url;
		if(curusr!=null){
			if(curusr.getTblRole().getRoleId()==1){
				
				setStoreId(curusr.getSiteId());
				setForumOwnerSite(curusr.getSiteId());
				setForumOwnerRole(curusr.getTblRole().getRoleId());
			}else if(curusr.getTblRole().getRoleId()==2){
				setAreaId(curusr.getSiteId());
				
				setForumOwnerSite(curusr.getSiteId());
				setForumOwnerRole(curusr.getTblRole().getRoleId());
			}else if(curusr.getTblRole().getRoleId()==3){
			
				setForumOwnerSite(curusr.getSiteId());
				setForumOwnerRole(curusr.getTblRole().getRoleId());
			}
			}
			if(curusr == null) {
				throw new SessionExpiredException("Session Expired");
			}
			return curusr;
		}

	public ArrayList<TblStoreRes> getAreaList() {
		StoreDao storeDao = new StoreDao(); 
		ArrayList<TblStoreRes> resList = storeDao.listAreaStore();
		return resList;
	}
	public ArrayList<TblArea> getStoreGrpList() {
		StoreDao storeDao = new StoreDao(); 
		ArrayList<TblArea> resList = storeDao.listArea();
		return resList;
	}
	
	public ArrayList<TblStore> getStoreList() {
		StoreDao storeDao = new StoreDao(); 
		ArrayList<TblStore> resList = storeDao.getAllStores();
		return resList;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isActUpdate() {
		return actUpdate;
	}

	public void setActUpdate(boolean actUpdate) {
		this.actUpdate = actUpdate;
	}

	public String getPostSubject() {
		return postSubject;
	}

	public void setPostSubject(String postSubject) {
		this.postSubject = postSubject;
	}

	public List<File> getPostAttach() {
		return postAttach;
	}

	public void setPostAttach(List<File> postAttach) {
		this.postAttach = postAttach;
	}

	public List<String> getPostAttachContentType() {
		return postAttachContentType;
	}

	public void setPostAttachContentType(List<String> postAttachContentType) {
		this.postAttachContentType = postAttachContentType;
	}

	public List<String> getPostAttachFileName() {
		return postAttachFileName;
	}

	public void setPostAttachFileName(List<String> postAttachFileName) {
		this.postAttachFileName = postAttachFileName;
	}

	public String getPostContents() {
		return postContents;
	}

	public void setPostContents(String postContents) {
		this.postContents = postContents;
	}
	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	
	public String getRecType() {
		return recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}
	
	public int[] getSlist() {
		return slist;
	}

	public void setSlist(String sList) {
		this.slist = CommonUtil.convertInt(sList);
	}
	public String getPostAttachPath() {
		return postAttachPath;
	}


	public void setPostAttachPath(String postAttachPath) {
		this.postAttachPath = postAttachPath;
	}


	public String getPostAttchName() {
		return postAttchName;
	}


	public void setPostAttchName(String postAttchName) {
		this.postAttchName = postAttchName;
	}

	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getForumOwnerRole() {
		return forumOwnerRole;
	}

	public void setForumOwnerRole(int forumOwnerRole) {
		this.forumOwnerRole = forumOwnerRole;
	}

	public int getForumOwnerSite() {
		return forumOwnerSite;
	}

	public void setForumOwnerSite(int forumOwnerSite) {
		this.forumOwnerSite = forumOwnerSite;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public int[] getAlist() {
		return alist;
	}


	public void setAlist(String aList) {
		this.alist = CommonUtil.convertInt(aList);
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public ArrayList<TblPostUpload> getUploadList() {
		return uploadList;
	}

	public void setUploadList(ArrayList<TblPostUpload> uploadList) {
		this.uploadList = uploadList;
	}

	public ArrayList<TblUploadRes> getUploadRes() {
		return uploadRes;
	}

	public void setUploadRes(ArrayList<TblUploadRes> uploadRes) {
		this.uploadRes = uploadRes;
	}

	public int getDelId() {
		return delId;
	}

	public void setDelId(int delId) {
		this.delId = delId;
	}

        public String getBreadCrumbt0() {
            Map<String, Object> session = ActionContext.getContext().getSession();
            String breadCrumbt0=(String)session.get("breadCrumbt0");
            return breadCrumbt0;
        }

       public String getBreadCrumbt1() {
            return breadCrumbt1;
        }

        public void setBreadCrumbt1(String breadCrumbt1) {
            this.breadCrumbt1 = breadCrumbt1;
        }        
        
        public String getPrevUrl0() {
               Map<String, Object> session = ActionContext.getContext().getSession();
               String prevUrl0=(String)session.get("prevUrl0");

            return prevUrl0;
        }


        public String getPrevUrl11() {
            return prevUrl11;
        }

        public void setPrevUrl11(String prevUrl11) {
            this.prevUrl11 = prevUrl11;
        }
        

}