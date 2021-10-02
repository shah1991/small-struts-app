package net.slide.action;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.slide.dao.PostDao;
import net.slide.dao.StoreDao;
import net.slide.dao.TaskPostDao;
import net.slide.dao.UserDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblArea;
import net.slide.pojo.TblChklstArea;
import net.slide.pojo.TblPost;
import net.slide.pojo.TblPostArea;
import net.slide.pojo.TblPostStore;
import net.slide.pojo.TblPostUpload;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblTask;
import net.slide.pojo.TblTaskArea;
import net.slide.pojo.TblTaskStore;
import net.slide.pojo.TblTaskUpload;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblStoreRes;
import net.slide.pojores.TblUploadRes;
import net.slide.util.CommonUtil;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.slide.pojo.TblTaskResponse;
import net.slide.pojo.TblTaskResponseArea;
import net.slide.util.UrlBreadCrumb;

@SuppressWarnings("serial")
public class TaskPostDetailAction extends ActionSupport  {
		private String searchTxt;
		private int deleteList[];
		private Integer portfolioId;
		private int id;
		private int taskId;
		private String taskStatus;
		private String taskSubject;
		private String taskContents;
		private String taskStartDate;
		private String taskEndDate;
		private boolean actUpdate = false;
		private List<File> taskAttach = new ArrayList<File>();
		private List<String> taskAttachContentType = new ArrayList<String>();
		private List<String> taskAttachFileName = new ArrayList<String>();
                
		private List<File> taskProof = new ArrayList<File>();
		private List<String> taskProofFileName = new ArrayList<String>();

                //added by hafizd on january 21st 2014
                
                private List<File> taskApprove = new ArrayList<File>();
		private List<String> taskApproveContentType = new ArrayList<String>();
		private List<String> taskApproveFileName = new ArrayList<String>();
                
                //end added
                
		private int slist[];
		private int alist[];
		private String taskCode;
		private String taskFileName;
		private String taskFilePath;
		
                private String taskApproveName;
		private String taskApprovePath;
		
                private String recType;
		private String taskRemark;
		private String taskType;
		private int taskAreaId;
		private int taskStoreId;
		private String taskResponse;
		private String taskResponseType;
		private int taskOwnerRole;
		private int taskOwnerSite;
		private int storeId;
		private int areaId;
		private int delId;
		private String fromUser;
		ArrayList<TblUploadRes> uploadRes=new ArrayList<TblUploadRes>();
		ArrayList<TblTaskUpload> uploadList=new ArrayList<TblTaskUpload>();
                ArrayList<TblTaskResponse> taskResponseList=new ArrayList<TblTaskResponse>();
                ArrayList<TblTaskResponseArea> taskResponseAreaList=new ArrayList<TblTaskResponseArea>();
                ArrayList<TblTaskArea> areaResponseList=new ArrayList<TblTaskArea>();
                //added by hafizd
                
                ArrayList<TblTaskArea> uploadListArea=new ArrayList<TblTaskArea>();
	
                //end added
                
        private String attachProof;
        private String attachProofName;
        private String prevUrltm11;        
        private String breadCrumbtm11;            
                
		/**
		 *  Detail Actions
		 */
		
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
		
		 	
	
		public String detailadd() throws SessionExpiredException {
			String retval = "manage";
			
			try {
				getUserBean();
				// Get User Name
				Map<String, Object> session = ActionContext.getContext().getSession();
				TaskPostDao postDao = new TaskPostDao();
				ArrayList<TblUploadRes> ures=new ArrayList<TblUploadRes>();
				TblUser curusr = (TblUser)session.get("loginUser");
				TblTask p = new TblTask();	
				// get file path
//				if(taskAttach.size()!=0){
//				String taskAttachPath = importFile(taskAttach.get(0), taskAttachFileName.get(0));
//				p.setTaskAttachPath(taskAttachPath);
//				p.setTaskAttachFilename(taskAttachFileName.get(0));
//				}
//				
//				if(taskAttach.size()!=0){
//					for(int i=0;i<taskAttach.size();i++){
//						TblUploadRes u=new TblUploadRes();
//						String postAttachPath = importFile(taskAttach.get(i), taskAttachFileName.get(i));
//						u.setAttachName(taskAttachFileName.get(i));
//						u.setAttachPath(postAttachPath);
//						ures.add(u);
//					}
//				}
				// Set Post detail
				if(session.get("uploadRes")!=null){
					  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
				}
				p.setTaskOwnerRole(curusr.getTblRole().getRoleId());
				p.setTaskOwnerSite(curusr.getUserId());
				p.setTaskSubject(taskSubject);
				p.setTaskContents(taskContents); 
				p.setTaskStatus("N");
				p.setUpdatedBy(curusr.getUserName());
				p.setUpdatedDate(new Date());
				p.setTaskType("S");
				try {
					p.setTaskStartDate(new SimpleDateFormat("dd-MMM-yyyy hh:mm").parse(taskStartDate));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				try {
					p.setTaskEndDate(new SimpleDateFormat("dd-MMM-yyyy hh:mm").parse(taskEndDate));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
						
				taskCode = postDao.savePost(p, portfolioId, alist, slist,uploadRes);
				if ( taskCode == null) {
					addActionError("Add failed..!!!");
					retval = INPUT;
				}else{
					addActionMessage(taskSubject + "Added Successfully !!!!");
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
				if(taskAttach.size()!=0){
					for(int i=0;i<taskAttach.size();i++){
						TblUploadRes u=new TblUploadRes();
						String postAttachPath = importFile(taskAttach.get(i), taskAttachFileName.get(i));
						u.setAttachName(taskAttachFileName.get(i));
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

        //added by hafizd on january 21st 2014
                
        @SuppressWarnings("unchecked")
	public String uploadDocApprove() throws SessionExpiredException {
		String retval = "view";
		actUpdate = true;
		Map<String, Object> session = ActionContext.getContext().getSession();
		TaskPostDao postDao = new TaskPostDao();
		TblTask p = postDao.getPostById(taskId); 
		TblUser curusr = (TblUser)session.get("loginUser");
		/*if(curusr.getTblRole().getRoleId()==2){
			retval="areacreate";
		}*/
		try {
                        UserDao userDao=new UserDao();
				if(p != null) {
					TblUser u = userDao.getUserById(p.getTaskOwnerSite());
					setFromUser(u.getUserName());
					setTaskSubject(p.getTaskSubject());
					setTaskContents(p.getTaskContents());
					if(p.getTaskStatus()!=null){
					setTaskStatus(p.getTaskStatus());
					}else{
					setTaskStatus("A");
					}
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					taskStartDate=formatter.format(p.getTaskStartDate());
					taskEndDate=formatter.format(p.getTaskEndDate());
					setTaskFileName(p.getTaskAttachFilename());
                                       // setApproveFileName(taskApprove);
					setTaskFilePath(p.getTaskAttachPath());
					
					setTaskType(p.getTaskType());
					setUploadList(postDao.getPostUpload(p));
					setTaskResponse("Y");
				}
                                
			if(session.get("uploadRes")!=null){
			  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
			}
			if(taskApprove.size()!=0){
				for(int i=0;i<taskApprove.size();i++){
					TblUploadRes u=new TblUploadRes();
					String postApprovePath = importFile(taskApprove.get(i), taskApproveFileName.get(i));
					u.setApproveName(taskApproveFileName.get(i));
					u.setApprovePath(postApprovePath);
					uploadRes.add(u);
				
				}
				session.put("uploadRes",uploadRes);
			}
			
			//setUploadList(postDao.getPostUpload(p));
		
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
        //end added        
		
		
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
	
	@SuppressWarnings("unchecked")
	public String uploadEditDoc() throws SessionExpiredException {
		String retval = INPUT;
		actUpdate = true;
		Map<String, Object> session = ActionContext.getContext().getSession();
		TaskPostDao postDao = new TaskPostDao();
		TblTask p = postDao.getPostById(taskId); 
		TblUser curusr = (TblUser)session.get("loginUser");
		if(curusr.getTblRole().getRoleId()==2){
			retval="areacreate";
		}
		try {
			if(session.get("uploadRes")!=null){
			  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
			}
			if(taskAttach.size()!=0){
				for(int i=0;i<taskAttach.size();i++){
					TblUploadRes u=new TblUploadRes();
					String postAttachPath = importFile(taskAttach.get(i), taskAttachFileName.get(i));
					u.setAttachName(taskAttachFileName.get(i));
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
		TaskPostDao postDao = new TaskPostDao();
		TblTask p = postDao.getPostById(taskId);  
	
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
	
	
		public String detailcreate() throws SessionExpiredException {
			
			try {
				Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("uploadRes",null);
				
				getUserBean();
			} catch (SessionExpiredException se) {
				addActionError("Session Expired..!!!");
				throw se;
			} catch (Exception e) {
				addActionError("Technical Error Happned..!!!");
				e.printStackTrace();
				return INPUT;
			}
			
			setTaskSubject("");
			setTaskContents("");
			return INPUT;
		}
		
		// Area manager task post

		public String areapost() throws SessionExpiredException {
			String retval = "manageareatask";
			
			try {
				getUserBean();
				// Get User Name
				Map<String, Object> session = ActionContext.getContext().getSession();
				
				TaskPostDao postDao = new TaskPostDao();
				TblUser curusr = (TblUser)session.get("loginUser");
				TblTask p = new TblTask();	
				// get file path
//				if(taskAttach.size()!=0){
//				String taskAttachPath = importFile(taskAttach.get(0), taskAttachFileName.get(0));
//				p.setTaskAttachPath(taskAttachPath);
//				p.setTaskAttachFilename(taskAttachFileName.get(0));
//				}
				// Set Post detail
				if(session.get("uploadRes")!=null){
					  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
				}
		
				
				p.setTaskOwnerRole(curusr.getTblRole().getRoleId());
				p.setTaskOwnerSite(curusr.getUserId());
				p.setTaskSubject(taskSubject);
				p.setTaskContents(taskContents); 
				p.setTaskStatus("N");
				
				p.setUpdatedBy(curusr.getUserName());
				p.setUpdatedDate(new Date());
				p.setTaskType("S");
				try {
					p.setTaskStartDate(new SimpleDateFormat("dd-MMM-yyyy hh:mm").parse(taskStartDate));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				try {
					p.setTaskEndDate(new SimpleDateFormat("dd-MMM-yyyy hh:mm").parse(taskEndDate));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
						
				taskCode = postDao.savePost(p, portfolioId, alist, slist,uploadRes);
				if ( taskCode == null) {
					addActionError("Add failed..!!!");
					retval = "areacreate";
				}else{
					addActionMessage(taskSubject + "Added Successfully !!!!");
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
		
		
		public String areacreate() throws SessionExpiredException {
			
			try {
				Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("uploadRes",null);
				getUserBean();
			} catch (SessionExpiredException se) {
				addActionError("Session Expired..!!!");
				throw se;
			} catch (Exception e) {
				addActionError("Technical Error Happned..!!!");
				e.printStackTrace();
				return "areacreate";
			}
			
			setTaskSubject("");
			setTaskContents("");
			return "areacreate";
		}
		public String adminview() throws SessionExpiredException, ParseException {
			
			try {
				getUserBean();
				TaskPostDao postDao = new TaskPostDao();
				TblTask p = postDao.getPostById(taskId);
				UserDao userDao=new UserDao();
				if(p != null) {
					TblUser u = userDao.getUserById(p.getTaskOwnerSite());
					setFromUser(u.getUserName());
					setTaskSubject(p.getTaskSubject());
					setTaskContents(p.getTaskContents());
					setTaskStatus(p.getTaskStatus());
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					taskStartDate=formatter.format(p.getTaskStartDate());
					taskEndDate=formatter.format(p.getTaskEndDate());
					setTaskFileName(p.getTaskAttachFilename());
					setTaskFilePath(p.getTaskAttachPath());
					setTaskCode(p.getTaskCd());
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
			
			return "adminview";
		}
		
		
		
		public String areaview() throws SessionExpiredException, ParseException {
			
			try {
				getUserBean();
				TaskPostDao postDao = new TaskPostDao();
				TblTask p = postDao.getAreaTask(taskId, taskAreaId);
				TblTaskArea area=null;
				UserDao userDao=new UserDao();
				if(p != null) {
					TblUser u = userDao.getUserById(p.getTaskOwnerSite());
					setFromUser(u.getUserName());
					setTaskSubject(p.getTaskSubject());
					setTaskContents(p.getTaskContents());
					setTaskStatus(p.getTaskStatus());
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					taskStartDate=formatter.format(p.getTaskStartDate());
					taskEndDate=formatter.format(p.getTaskEndDate());
					setTaskFileName(p.getTaskAttachFilename());
					//setApproveFileName(p.getTaskApproveFileName());
					
                                        setTaskFilePath(p.getTaskAttachPath());
					setTaskCode(p.getTaskCd());
					Iterator<TblTaskArea> ta=p.getTblTaskAreas().iterator();
					while(ta.hasNext()){
						 area=ta.next();
					}
					setTaskRemark(area.getTaskRemark());
					
					if(area.getTaskResponse().equals("A")){
						setTaskResponse("N/A");
					}else if(area.getTaskResponse().equals("Y")){
						setTaskResponse("Yes");
					}else if(area.getTaskResponse().equals("N")){
						setTaskResponse("No");	
					}
					setUploadList(postDao.getPostUpload(p));
                                        setTaskResponseAreaList(postDao.getResponseArea(area));
					
					/*AM: upload image cannot see by HQ on Task magmt*/
//					if(area.getTaskProofPath()!=null)
//					{
//						setAttachProof(area.getTaskProofPath());						
//						setAttachProofName(area.getTaskProofFilename());
//					}
					/*AM: upload image cannot see by HQ on Task magmt*/

					
				}
			} catch (SessionExpiredException se) {
				addActionError("Session Expired..!!!");
				throw se;
			} catch (Exception e) {
				
				addActionError("Technical Error Happned..!!!");
				e.printStackTrace();
				return "areaview";
			}
			
			return "areaview";
		}
		
		
		
		public String storeview() throws SessionExpiredException, ParseException {
			
			try {
				getUserBean();
				TaskPostDao postDao = new TaskPostDao();
				TblTask p = postDao.getStoreTask(taskId, taskStoreId);
				TblTaskStore store=null;
                                TblTaskResponse tblTaskResponse=null;
				UserDao userDao=new UserDao();
				if(p != null) {
					TblUser u = userDao.getUserById(p.getTaskOwnerSite());
					setFromUser(u.getUserName());
					setTaskSubject(p.getTaskSubject());
					setTaskContents(p.getTaskContents());
					setTaskStatus(p.getTaskStatus());
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					taskStartDate=formatter.format(p.getTaskStartDate());
					taskEndDate=formatter.format(p.getTaskEndDate());
					setTaskFileName(p.getTaskAttachFilename());
					setTaskFilePath(p.getTaskAttachPath());
					setTaskCode(p.getTaskCd());
					Iterator<TblTaskStore> ta=p.getTblTaskStores().iterator();
					while(ta.hasNext()){
						 store=ta.next();
					}
					
					 if(store.getTaskResponse().equals("A")){
							setTaskResponse("N/A");
						}else if(store.getTaskResponse().equals("N")){
							setTaskResponse("No");	
						}else{
							setTaskResponse("Yes");
						}
					setTaskRemark(store.getTaskRemark());
					setUploadList(postDao.getPostUpload(p));
					
                                        setTaskResponseList(postDao.getResponse(store));
					
					/*AM: upload image cannot see by HQ on Task magmt*/
				/*	if(store.getTaskProofPath()!=null)
					{
						setAttachProof(store.getTaskProofPath());						
						setAttachProofName(store.getTaskProofFilename());
					}
					/*AM: upload image cannot see by HQ on Task magmt*/
					
				}
			} catch (SessionExpiredException se) {
				addActionError("Session Expired..!!!");
				throw se;
			} catch (Exception e) {
				addActionError("Technical Error Happned..!!!");
				e.printStackTrace();
				return "storeview";
			}
			
			return "storeview";
		}
	
		
		public String admindelete() throws SessionExpiredException {
			String retval = "manage";
			try {
				getUserBean();
				Map<String, Object> session = ActionContext.getContext().getSession();
				TaskPostDao postDao = new TaskPostDao();
				TblTask p = postDao.getPostById(taskId); 
				TblUser curusr = (TblUser)session.get("loginUser");
				if(curusr.getTblRole().getRoleId()==2){
					retval="manageareatask";
				}
				//Set Post detail
				
				if(p != null) {
					postDao.deletePost(taskId);
				}
			} catch (SessionExpiredException se) {
				addActionError("Session Expired..!!!");
				throw se;
			} catch (Exception e) {
				addActionError("Technical Error Happned..!!!");
				e.printStackTrace();
			}
			return retval;
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
		
		
		public String areastore() throws SessionExpiredException {
			
			try {
				
				Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("uploadRes",null);
				
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
				return INPUT;
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
	
			
		public String postview() throws SessionExpiredException {
			
			try {
                                Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("uploadRes",null);
				getUserBean();
				TaskPostDao postDao = new TaskPostDao();
				TblTask p = postDao.getPostById(taskId);
				UserDao userDao=new UserDao();
				if(p != null) {
					TblUser u = userDao.getUserById(p.getTaskOwnerSite());
					setFromUser(u.getUserName());
					setTaskSubject(p.getTaskSubject());
					setTaskContents(p.getTaskContents());
					if(p.getTaskStatus()!=null){
					setTaskStatus(p.getTaskStatus());
					}else{
					setTaskStatus("A");
					}
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					taskStartDate=formatter.format(p.getTaskStartDate());
					taskEndDate=formatter.format(p.getTaskEndDate());
					setTaskFileName(p.getTaskAttachFilename());
					setTaskFilePath(p.getTaskAttachPath());
					
					setTaskType(p.getTaskType());
					setUploadList(postDao.getPostUpload(p));
					//setTaskResponse("Y");
                                        
                                       // setUploadList(postDao.getPostUpload(p));
				
					actUpdate = true;
				}
			} catch (SessionExpiredException se) {
				addActionError("Session Expired..!!!");
				throw se;
			} catch (Exception e) {
				addActionError("Technical Error Happned..!!!");
				e.printStackTrace();
				return "view";
			}
			
			return "view";
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
				
				path=path+"/tasks";
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
		
		public String detailedit() throws SessionExpiredException {
			
			try {
				Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("uploadRes",null);
				ArrayList<TblTaskStore> storeList=new ArrayList<TblTaskStore>();
				ArrayList<TblTaskArea> areaList=new ArrayList<TblTaskArea>();
				getUserBean();
				TaskPostDao postDao = new TaskPostDao();
				TblTask p = postDao.getPostById(taskId);
				if(p != null) {
					setTaskId(taskId);
					setTaskSubject(p.getTaskSubject());
					setTaskContents(p.getTaskContents());
					setTaskStatus(p.getTaskStatus());
					setTaskFileName(p.getTaskAttachFilename());
					setTaskFilePath(p.getTaskAttachPath());

					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					taskStartDate=formatter.format(p.getTaskStartDate());
					taskEndDate=formatter.format(p.getTaskEndDate());
					
					storeList=postDao.getStores(p);
					areaList=postDao.getAreas(p);
					int i=0;
					int j=0;
					int[] storeArray = new int[storeList.size()];
					int[] areaArray = new int[areaList.size()];
					for(TblTaskStore tps:storeList){
						storeArray[i]=tps.getTblStore().getStoreId();
						i++;
					}
					if(storeList.size()!=0){
						slist=storeArray;
					}
					for(TblTaskArea tps:areaList){
						areaArray[j]=tps.getTblArea().getAreaId();
						j++;
					}
					if(areaList.size()!=0){
						alist=areaArray;
					}
					
					setUploadList(postDao.getPostUpload(p));
				
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
			
			return INPUT;
		}
		
	
		
		

		public String detailupdate() {
			String retval = "manage";
			try {
			// Get User Name
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
			
			String res =null;
			//Set Post detail
			TaskPostDao postDao = new TaskPostDao();
			TblTask p = postDao.getPostById(taskId); 
			// get file path
//			if(taskAttach.size()!=0){
//			String taskAttachPath = importFile(taskAttach.get(0), taskAttachFileName.get(0));
//			p.setTaskAttachPath(taskAttachPath);
//			p.setTaskAttachFilename(taskAttachFileName.get(0));
//
//			}
			// Set Post detail
			if(session.get("uploadRes")!=null){
				  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
			}
			if(p != null){
				p.setTaskSubject(taskSubject);
				p.setTaskContents(taskContents);
				try {
					p.setTaskStartDate(new SimpleDateFormat("dd-MMM-yyyy").parse(taskStartDate));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				try {
					p.setTaskEndDate(new SimpleDateFormat("dd-MMM-yyyy").parse(taskEndDate));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
					
				p.setUpdatedBy(curusr.getUserName());
				p.setUpdatedDate(new Date());
				res = postDao.savePost(p, portfolioId, alist, slist,uploadRes);
				
			}
			
			if ( res !=null) {
				addActionMessage(taskSubject + "Updated Successfully !!!!");
				
			}else{
				addActionError("Update failed..!!!");
				retval = INPUT;
			}
			}catch (Exception e) {
				// TODO: handle exception
			}
			return retval;
		}
		
		

		public String detailareaedit() throws SessionExpiredException {
			
			try {
				Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("uploadRes",null);
				actUpdate = true;
				ArrayList<TblTaskStore> storeList=new ArrayList<TblTaskStore>();
				getUserBean();
				TaskPostDao postDao = new TaskPostDao();
				TblTask p = postDao.getPostById(taskId);
				if(p != null) {
					setTaskId(taskId);
					setTaskSubject(p.getTaskSubject());
					setTaskContents(p.getTaskContents());
					setTaskStatus(p.getTaskStatus());
					setTaskFileName(p.getTaskAttachFilename());
					setTaskFilePath(p.getTaskAttachPath());

					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					taskStartDate=formatter.format(p.getTaskStartDate());
					taskEndDate=formatter.format(p.getTaskEndDate());
					storeList=postDao.getStores(p);
					int i=0;
					int[] storeArray = new int[storeList.size()];
					for(TblTaskStore tps:storeList){
						storeArray[i]=tps.getTblStore().getStoreId();
						i++;
					}
					if(storeList.size()!=0){
						slist=storeArray;
					}
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
		
	
		
		

		public String detailareaupdate() {
			String retval = "manageareatask";
			try {
			// Get User Name
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
		
			String res =null;
			//Set Post detail
			TaskPostDao postDao = new TaskPostDao();
			TblTask p = postDao.getPostById(taskId); 
			// get file path
//			if(taskAttach.size()!=0){
//			String taskAttachPath = importFile(taskAttach.get(0), taskAttachFileName.get(0));
//			p.setTaskAttachPath(taskAttachPath);
//			p.setTaskAttachFilename(taskAttachFileName.get(0));
//
//			}
			// Set Post detail
			if(session.get("uploadRes")!=null){
				  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
			}
			if(p != null){
				p.setTaskSubject(taskSubject);
				p.setTaskContents(taskContents);
				try {
					p.setTaskStartDate(new SimpleDateFormat("dd-MMM-yyyy").parse(taskStartDate));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				try {
					p.setTaskEndDate(new SimpleDateFormat("dd-MMM-yyyy").parse(taskEndDate));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
					
				p.setUpdatedBy(curusr.getUserName());
				p.setUpdatedDate(new Date());
				res = postDao.savePost(p, portfolioId, alist, slist,uploadRes);
				
			}
			
			if ( res !=null) {
				addActionMessage(taskSubject + "Updated Successfully !!!!");
				
			}else{
				addActionError("Update failed..!!!");
				retval = "areacreate";
			}
			}catch (Exception e) {
				// TODO: handle exception
			}
			return retval;
		}
		public String detaildelete() throws SessionExpiredException {
			
			try {
				getUserBean();
				//Set Post detail
				TaskPostDao postDao = new TaskPostDao();
				TblTask p = postDao.getPostById(id); 
				if(p != null) {
//					postDao.deletePost(id);
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
                
                //change class of update response by hafizd on January 22nd 2014
                public String updateresponse() throws SessionExpiredException, ParseException {
				String retval = SUCCESS;
			try {
				getUserBean();
				Map<String, Object> session = ActionContext.getContext().getSession();
				
				TblUser curusr = (TblUser)session.get("loginUser");
				TaskPostDao postDao = new TaskPostDao();
                           
                           //added by hafizd     
                           if(session.get("uploadRes")!=null){
				  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
                           }     
                           
                           //end added
			   if(taskType.equals("S")){
				 retval="manageuser";
				 TblTaskStore a= postDao.getTaskStore(taskId, taskStoreId);
				 a.setTaskResponse(taskResponse);
				 a.setTaskRemark(taskRemark);
				 a.setUpdatedBy(curusr.getUserName());
				 a.setUpdatedDate(new Date());
				 a.setTaskStatus("R");
				 //postDao.updateStoreResponse(a,taskStoreId);
                                 postDao.updateTaskStoreResponse(a,uploadRes);
			   }else{
				retval="managearea";
				TblTaskArea a= postDao.getTaskArea(taskId, taskAreaId);                                
                                a.setTaskResponse(taskResponse);
				a.setTaskRemark(taskRemark);
				a.setUpdatedBy(curusr.getUserName());
				a.setUpdatedDate(new Date());
				a.setTaskStatus("R");
				//postDao.updateAreaResponse(a,taskAreaId);
                                postDao.updateTaskAreaResponse(a,uploadRes);
                               // postDao.updateAreaResponse(a,taskAreaId,uploadRes);
			   }
			} catch (SessionExpiredException se) {
				addActionError("Session Expired..!!!");
				throw se;
			} catch (Exception e) {
				addActionError("Technical Error Happned..!!!");
				e.printStackTrace();
				return "view";
			}
			
			return retval;
		}
		
		/*public String updateresponse() throws SessionExpiredException, ParseException {
				String retval = SUCCESS;
			try {
				getUserBean();
				Map<String, Object> session = ActionContext.getContext().getSession();
				
				TblUser curusr = (TblUser)session.get("loginUser");
				TaskPostDao postDao = new TaskPostDao();
			   if(taskType.equals("S")){
				 retval="manageuser";
				 TblTaskStore a=new TblTaskStore();  
				 a.setTaskResponse(taskResponse);
				 a.setTaskRemark(taskRemark);
				 a.setUpdatedBy(curusr.getUserName());
				 a.setUpdatedDate(new Date());
				 a.setTaskStatus("R");
				 postDao.updateStoreResponse(a,taskStoreId);
			   }else{
				retval="managearea";
				TblTaskArea a=new TblTaskArea();
			    a.setTaskResponse(taskResponse);
				a.setTaskRemark(taskRemark);
				a.setUpdatedBy(curusr.getUserName());
				a.setUpdatedDate(new Date());
				a.setTaskStatus("R");
				postDao.updateAreaResponse(a,taskAreaId);
			   }
			} catch (SessionExpiredException se) {
				addActionError("Session Expired..!!!");
				throw se;
			} catch (Exception e) {
				addActionError("Technical Error Happned..!!!");
				e.printStackTrace();
				return "view";
			}
			
			return retval;
		}*/
		
		private TblUser getUserBean() throws SessionExpiredException, Exception {
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
			if(portfolioId!=null){
				session.put("portfolio",portfolioId);
			}else if(session.get("portfolio")!=null){
				portfolioId=(Integer)session.get("portfolio");
			
			}
                        breadCrumbtm11=(String)session.get("breadCrumbtm0");
                        String url=UrlBreadCrumb.getUrl();
                        session.put("prevUrltm11",url);
                        prevUrltm11=url;
			if(curusr!=null){
				if(curusr.getTblRole().getRoleId()==1){
					setStoreId(curusr.getSiteId());
				
					setTaskType("S");
				}else if(curusr.getTblRole().getRoleId()==2){
					
					setAreaId(curusr.getSiteId());
					setTaskType("A");
					
				}else if(curusr.getTblRole().getRoleId()==3){
					
				}
			}else{
			
				throw new SessionExpiredException("Session Expired");
			}
			return curusr;
		}
		
		public String getRecType() {
			return recType;
		}

		public void setRecType(String recType) {
			this.recType = recType;
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
		
		
		public boolean isActUpdate() {
			return actUpdate;
		}
		public void setActUpdate(boolean actUpdate) {
			this.actUpdate = actUpdate;
		}


		public String getTaskStatus() {
			return taskStatus;
		}


		public void setTaskStatus(String taskStatus) {
			this.taskStatus = taskStatus;
		}


		public String getTaskSubject() {
			return taskSubject;
		}


		public void setTaskSubject(String taskSubject) {
			this.taskSubject = taskSubject;
		}


		public String getTaskContents() {
			return taskContents;
		}


		public void setTaskContents(String taskContents) {
			this.taskContents = taskContents;
		}


		public String getTaskStartDate() {
			return taskStartDate;
		}


		public void setTaskStartDate(String taskStartDate) {
			this.taskStartDate = taskStartDate;
		}


		public String getTaskEndDate() {
			return taskEndDate;
		}


		public void setTaskEndDate(String taskEndDate) {
			this.taskEndDate = taskEndDate;
		}


		public List<File> getTaskAttach() {
			return taskAttach;
		}


		public void setTaskAttach(List<File> taskAttach) {
			this.taskAttach = taskAttach;
		}
                
                //added first
                
                public List<File> getTaskApprove() {
			return taskApprove;
		}


		public void setTaskApprove(List<File> taskApprove) {
			this.taskApprove = taskApprove;
		}
                
                //end added


		public List<String> getTaskAttachContentType() {
			return taskAttachContentType;
		}


		public void setTaskAttachContentType(List<String> taskAttachContentType) {
			this.taskAttachContentType = taskAttachContentType;
		}
                
                //added second
                
                public List<String> getTaskApproveContentType() {
			return taskApproveContentType;
		}


		public void setTaskApproveContentType(List<String> taskApproveContentType) {
			this.taskApproveContentType = taskApproveContentType;
		}
                
                //end added


		public List<String> getTaskAttachFileName() {
			return taskAttachFileName;
		}


		public void setTaskAttachFileName(List<String> taskAttachFileName) {
			this.taskAttachFileName = taskAttachFileName;
		}

                // added by hafizd
                
                public List<String> getTaskApproveFileName() {
			return taskApproveFileName;
		}


		public void setTaskApproveFileName(List<String> taskApproveFileName) {
			this.taskApproveFileName = taskApproveFileName;
		}
                
                //end added
		
		public Integer getPortfolioId() {
			return portfolioId;
		}
		public void setPortfolioId(Integer portfolioId) {
			this.portfolioId = portfolioId;
		}
		
		public String getTaskCode() {
			return taskCode;
		}

		public void setTaskCode(String taskCode) {
			this.taskCode = taskCode;
		}

	
		public String getTaskFileName() {
			return taskFileName;
		}

		public void setTaskFileName(String taskFileName) {
			this.taskFileName = taskFileName;
		}
                
                //added
                
                public String getApproveFileName() {
			return taskApproveName;
		}

		public void setApproveFileName(String taskApproveName) {
			this.taskApproveName = taskApproveName;
		}
                
                //end added

		public String getTaskFilePath() {
			return taskFilePath;
		}

		public void setTaskFilePath(String taskFilePath) {
			this.taskFilePath = taskFilePath;
		}

		public int getTaskId() {
			return taskId;
		}

		public void setTaskId(int taskId) {
			this.taskId = taskId;
		}

		public String getTaskRemark() {
			return taskRemark;
		}

		public void setTaskRemark(String taskRemark) {
			this.taskRemark = taskRemark;
		}

		public int getTaskAreaId() {
			return taskAreaId;
		}

		public void setTaskAreaId(int taskAreaId) {
			this.taskAreaId = taskAreaId;
		}

		public int getTaskStoreId() {
			return taskStoreId;
		}

		public void setTaskStoreId(int taskStoreId) {
			this.taskStoreId = taskStoreId;
		}

		public String getTaskType() {
			return taskType;
		}

		public void setTaskType(String taskType) {
			this.taskType = taskType;
		}

		public String getTaskResponse() {
			return taskResponse;
		}

		public void setTaskResponse(String taskResponse) {
			this.taskResponse = taskResponse;
		}

		public String getTaskResponseType() {
			return taskResponseType;
		}

		public void setTaskResponseType(String taskResponseType) {
			this.taskResponseType = taskResponseType;
		}

		public int getTaskOwnerRole() {
			return taskOwnerRole;
		}

		public void setTaskOwnerRole(int taskOwnerRole) {
			this.taskOwnerRole = taskOwnerRole;
		}

		public int getTaskOwnerSite() {
			return taskOwnerSite;
		}

		public void setTaskOwnerSite(int taskOwnerSite) {
			this.taskOwnerSite = taskOwnerSite;
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
		
	
		public int[] getAlist() {
			return alist;
		}

	
		public void setAlist(String aList) {
			this.alist = CommonUtil.convertInt(aList);
		}

		
		
		public int[] getSlist() {
			return slist;
		}

		public void setSlist(String sList) {
			this.slist = CommonUtil.convertInt(sList);
		}
		public String getFromUser() {
			return fromUser;
		}
		public void setFromUser(String fromUser) {
			this.fromUser = fromUser;
		}
		
		public ArrayList<TblTaskUpload> getUploadList() {
			return uploadList;
		}
		public void setUploadList(ArrayList<TblTaskUpload> uploadList) {
			this.uploadList = uploadList;
		}
                
		public ArrayList<TblTaskResponse> getTaskResponseList() {
			return taskResponseList;
		}
		public void setTaskResponseList(ArrayList<TblTaskResponse> taskResponseList) {
			this.taskResponseList = taskResponseList;
		}
                
		public ArrayList<TblTaskResponseArea> getTaskResponseAreaList() {
			return taskResponseAreaList;
		}
		public void setTaskResponseAreaList(ArrayList<TblTaskResponseArea> taskResponseAreaList) {
			this.taskResponseAreaList = taskResponseAreaList;
		}
                
                
		public ArrayList<TblTaskArea> getAreaResponseList() {
			return areaResponseList;
		}
		public void setAreaResponseList(ArrayList<TblTaskArea> areaResponseList) {
			this.areaResponseList = areaResponseList;
		}
                
                //added by hafizd on februari 5th 2014
                
                /*public ArrayList<TblTaskArea> getUploadListArea() {
			return uploadListArea;
		}
		public void setUploadListArea(ArrayList<TblTaskArea> uploadListArea) {
			this.uploadListArea = uploadListArea;
		}*/
                
                //end added
                
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
		
		public String getAttachProof() {
			return attachProof;
		}
		public void setAttachProof(String attachProof) {
			this.attachProof = attachProof;
		}
		public String getAttachProofName() {
			return attachProofName;
		}
		public void setAttachProofName(String attachProofName) {
			this.attachProofName = attachProofName;
		}

        public String getBreadCrumbtm0() {
            Map<String, Object> session = ActionContext.getContext().getSession();
            String breadCrumbtm0=(String)session.get("breadCrumbtm0");
            return breadCrumbtm0;
        }                
                
        public String getPrevUrltm0() {
               Map<String, Object> session = ActionContext.getContext().getSession();
               String prevUrltm0=(String)session.get("prevUrltm0");

            return prevUrltm0;
        }

       public String getBreadCrumbtm11() {
            return breadCrumbtm11;
        }

        public void setBreadCrumbtm11(String breadCrumbtm11) {
            this.breadCrumbtm11 = breadCrumbtm11;
        }        
        
    public String getPrevUrltm11() {
        return prevUrltm11;
    }

    public void setPrevUrltm11(String prevUrltm11) {
        this.prevUrltm11 = prevUrltm11;
    }
              
        
}
