package net.slide.action;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import net.slide.dao.ChklstDao;
import net.slide.dao.TaskDao;
import net.slide.dao.TaskPostDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblChklst;
import net.slide.pojo.TblTask;
import net.slide.pojo.TblTaskArea;
import net.slide.pojo.TblTaskPortfolio;
import net.slide.pojo.TblTaskStore;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblChklstRes;
import net.slide.pojores.TblMasterRes;
import net.slide.pojores.TblTaskRes;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.slide.dao.LinkDao;
import net.slide.util.UrlBreadCrumb;

@SuppressWarnings("serial")
public class TaskPostMainAction extends ActionSupport {
	private Integer portfolioId;
	private int postId;
	private String status;
	private boolean actUpdate = false;
	private String searchTxt;
	private String postLevel;
	private String postYear;
	private String postMonth;
	private Integer postWeek;
	private int taskStoreId;
	private int taskAreaId;
	private Integer storeId;
    private int areaId;
	private int taskOwnerRole;
	private int taskOwnerSite;
	private String taskResponse;
	private String taskResponseType;
	private String taskSubject;
        private String breadCrumbtm0;
        private String breadCrumbtm1;
        private String breadCrumbtmy;
        private String breadCrumbtmm;
        private String breadCrumbtmw;
        private String breadCrumbtmp;
        private String prevUrltm0;
        private String prevUrltm1;
        private String prevUrltmy;
        private String prevUrltmm;
        private String prevUrltmw;
        private String prevUrltmp;
        

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String entry() throws SessionExpiredException {
		String retval = SUCCESS;
		try {
		TblUser curusr=	getUserBean();
		if(curusr.getTblRole().getRoleId()==1){
			retval="entrystore";
		}else if(curusr.getTblRole().getRoleId()==2){
			retval="entryarea";
		}else if(curusr.getTblRole().getRoleId()==3 || curusr.getTblRole().getRoleId()==8){
			retval="entryhq";
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
		return "detail";
	}
	
	

	
	public String taskpost() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();		
                String url=null;
		
		String retval = INPUT;
		try {
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return retval;
		}
		
		if(postLevel.equals("Y")){
			retval = "postyear";
                        if(session.get("curlvltm")!=null){
//                            curlvlx=(Integer)session.get("curlvltm");
                            prevUrltm1= (String)session.get("prevUrltm1");
                            breadCrumbtm1=(String)session.get("breadCrumbtm1");                                                
                        }else{
                            url=UrlBreadCrumb.getUrl();
                            session.put("prevUrltm1",url);
                            breadCrumbtm1=(String)session.get("breadCrumbtm1"); 
                            prevUrltm1= url;
                        }
                        session.remove("curlvltm");
                        session.remove("prevUrltmy");
                        session.remove("prevUrltmm");
                        session.remove("prevUrltmw");
                        session.remove("breadCrumbtmy");
                        session.remove("breadCrumbtmm");
                        session.remove("breadCrumbtmw");
                        session.put("curlvltm",1);
                }
		else if(postLevel.equals("M")){
			retval = "postmonth";
                        int curlvlx=(Integer)session.get("curlvltm");
                        if (curlvlx==1){
                            url=UrlBreadCrumb.getUrl();
                            session.put("prevUrltmy",url);
                            session.put("breadCrumbtmy",breadCrumbtmy);
                            prevUrltmy= url;
                        }else{
                            prevUrltmy= (String)session.get("prevUrltmy");
                            breadCrumbtmy=(String)session.get("breadCrumbtmy");
                        }
                        session.remove("curlvltm");
                        session.remove("prevUrltmm");
                        session.remove("prevUrltmw");
                        session.remove("breadCrumbtmm");
                        session.remove("breadCrumbtmw");
                        session.put("curlvltm",2);
                        prevUrltm1= (String)session.get("prevUrltm1");
                        breadCrumbtm1=(String)session.get("breadCrumbtm1");                        
                }
		else if(postLevel.equals("W")){
			retval = "postweek";
                        int curlvlx=(Integer)session.get("curlvltm");
                        if (curlvlx==2){
                            url=UrlBreadCrumb.getUrl();
                            session.put("prevUrltmm",url);
                            session.put("breadCrumbtmm",breadCrumbtmm);
                            prevUrltmm= url;
                        }else{
                            prevUrltmm= (String)session.get("prevUrltmm");
                            breadCrumbtmm=(String)session.get("breadCrumbtmm");
                        }
                        session.remove("curlvltm");
                        session.remove("prevUrltmw");
                        session.remove("breadCrumbtmw");
                        session.put("curlvltm",3);
                        prevUrltm1=(String)session.get("prevUrltm1");
                        prevUrltmy=(String)session.get("prevUrltmy");
                        breadCrumbtm1=(String)session.get("breadCrumbtm1");                        
                        breadCrumbtmy=(String)session.get("breadCrumbtmy");                        
                }
		else if(postLevel.equals("P")){
			retval = "post";
                        int curlvlx=(Integer)session.get("curlvltm");
                        if (curlvlx==3){
                            url=UrlBreadCrumb.getUrl();
                            session.put("prevUrltmw",url);
                            session.put("breadCrumbtmw",breadCrumbtmw);
                            prevUrltmw= url;
                        }else{
                            prevUrltmw= (String)session.get("prevUrltmw");
                            breadCrumbtmw=(String)session.get("breadCrumbtmw");
                        }
                        session.remove("curlvltm");
                        session.put("curlvltm",4);
                        prevUrltm1=(String)session.get("prevUrltm1");
                        prevUrltmy=(String)session.get("prevUrltmy");
                        prevUrltmm=(String)session.get("prevUrltmm");
                        breadCrumbtm1=(String)session.get("breadCrumbtm1");                        
                        breadCrumbtmy=(String)session.get("breadCrumbtmy");                        
                        breadCrumbtmm=(String)session.get("breadCrumbtmm");                        
                }

		return retval;
	}
	
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		if(portfolioId!=null){
				session.put("portfolio",portfolioId);
		}else if(session.get("portfolio")!=null){
				portfolioId=(Integer)session.get("portfolio");
			
		}
		

		if(storeId!=null){
			session.put("storeId",storeId);
			
		}else{
			if(session.get("storeId")!=null){
				storeId=(Integer)session.get("storeId");
			}
		}
		if(breadCrumbtm1!=null){
			session.put("breadCrumbtm1",breadCrumbtm1);
		}else if(session.get("breadCrumbtm1")!=null){
			breadCrumbtm1=(String)session.get("breadCrumbtm1");
		
		}
                
		if(curusr!=null){
			if(curusr.getTblRole().getRoleId()==1){
				
				setStoreId(curusr.getSiteId());
				setTaskOwnerSite(curusr.getUserId());
				setTaskOwnerRole(curusr.getTblRole().getRoleId());
			}else if(curusr.getTblRole().getRoleId()==2){
				setAreaId(curusr.getSiteId());
				
				setTaskOwnerSite(curusr.getUserId());
				setTaskOwnerRole(curusr.getTblRole().getRoleId());
			}else if(curusr.getTblRole().getRoleId()==3){
			
				setTaskOwnerSite(curusr.getUserId());
				setTaskOwnerRole(curusr.getTblRole().getRoleId());
			}
		}else{
		
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}
	
	public String hqtask() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvltm");
                session.remove("prevUrltmw");
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
		return "managehq";
	}
	
	

	
	
	public String hqfail() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvltm");
                session.remove("prevUrltmw");
            
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
		return "hqfail";
	}
	
	public String hqcomplete() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvltm");
                session.remove("prevUrltmw");
            
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
		return "hqcomplete";
	}
	
	public String arearesponse() throws SessionExpiredException {
		try {
			getUserBean();
			ArrayList<TblTaskRes> resList = getAreaResponseList();
			if(resList!=null){
			for(TblTaskRes res:resList){
				setTaskSubject(res.getTaskSubject());
				break;
			}
				
			}	
				
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return "area";
	}
	
	public String storeresponse() throws SessionExpiredException {
		try {
			getUserBean();
	
			ArrayList<TblTaskRes> resList = getStoreResponseList();
			if(resList!=null){
			for(TblTaskRes res:resList){
				setTaskSubject(res.getTaskSubject());
				break;
			}
				
			}	
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return "store";
	}
	
	
	public String storetask() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvltm");
                session.remove("prevUrltmw");
            
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
		return "manageuser";
	}
	
	public String storecomplete() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvltm");
                session.remove("prevUrltmw");
            
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
		return "storecomplete";
	}
	
	
	public String storefail() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvltm");
                session.remove("prevUrltmw");
            
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
		return "storefail";
	}
	
	public String areafail() throws SessionExpiredException {
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
		return "areafail";
	}
	
	public String areacomplete() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvltm");
                session.remove("prevUrltmw");
            
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
		return "areacomplete";
	}
	
	public String areapending() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvltm");
                session.remove("prevUrltmw");
            
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
		return "areapending";
	}
	
	
	

	public String areatask() throws Exception {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvltm");
                session.remove("prevUrltmw");
            
		try {
			
			getUserBean();
		}
		catch (SessionExpiredException se) {
		addActionError("Session Expired..!!!");
		throw se;
		}catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return "managearea";
	}
	
	
	
	//----------------export------------------start
		public void hqAreaExcel(){
			
			HttpServletResponse response = ServletActionContext.getResponse();
			WritableFont bold = new WritableFont(WritableFont.ARIAL, 
	                WritableFont.DEFAULT_POINT_SIZE, 
	                WritableFont.BOLD);
			WritableFont header = new WritableFont(WritableFont.ARIAL, 
	                16, 
	                WritableFont.BOLD);
			WritableCellFormat headCell = new WritableCellFormat(header);
		  	WritableCellFormat boldCell = new WritableCellFormat(bold);
			 try{	
				 	TaskPostDao postDao=new TaskPostDao();
				 	getUserBean();
				 	response.setContentType("application/vnd.ms-excel");
				  	response.setHeader("Content-Disposition", "attachment; filename=excelreport.xls");
				  	WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
				  	ArrayList<TblTaskRes> taskRes=getTaskAllList();
			       for(TblTaskRes res:taskRes){
			    	   ArrayList<TblTaskArea> areaList=postDao.getAreaTaskList(res.getTaskId());
				    	 if(areaList.size()!=0){
			    	   ArrayList<TblTaskRes> resList = postDao.getAreaResponse(res.getTaskId());
			    	  
			    	  	WritableSheet s = w.createSheet(res.getTaskSubject(), 0);
					  	s.addCell(new Label(1, 0,res.getTaskSubject(), headCell));	
					    int colcnt=0;
						   // boldCell.setWrap(true);
						       s.addCell(new Label(colcnt++, 2, "No", boldCell));	
					  		   s.addCell(new Label(colcnt++, 2, "Area", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Published Date", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Status", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Response", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Response Date", boldCell));
					  		 
					  		 
					  		   
			    	
			    	    int colCnt = 0;	
						int itemCnt = 2;
						int seq = 0;
			    	  for(TblTaskRes result:resList){
			    		 
			    		
			    			colCnt = 0;
			 				itemCnt++;
			 				seq++;
			 				s.addCell(new jxl.write.Number(colCnt++, itemCnt, seq));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getRecipient()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getUpdateDate()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getTaskResponseType()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getTaskRemark()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getResponseDate()));
			 				
			 				
			    		 
			    		  
			    	  }
			     }}
			     w.write();
			 	  	w.close();
			    }catch(Exception e){
			        System.out.println(e.getMessage());
			    }
			 }
	
		public void areaexcel(){
			HttpServletResponse response = ServletActionContext.getResponse();
			WritableFont bold = new WritableFont(WritableFont.ARIAL, 
	                WritableFont.DEFAULT_POINT_SIZE, 
	                WritableFont.BOLD);
			WritableFont header = new WritableFont(WritableFont.ARIAL, 
	                16, 
	                WritableFont.BOLD);
			WritableCellFormat headCell = new WritableCellFormat(header);
		  	WritableCellFormat boldCell = new WritableCellFormat(bold);
			 try{	
				 	TaskPostDao postDao=new TaskPostDao();
				 	
				 	response.setContentType("application/vnd.ms-excel");
				  	response.setHeader("Content-Disposition", "attachment; filename=excelreport.xls");
				  	WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
				  	
				  		getUserBean();
				  		TblTask res=postDao.getPostById(taskId);
			    	    WritableSheet s = w.createSheet(res.getTaskSubject(), 0);
					  	s.addCell(new Label(1, 0,res.getTaskSubject(), headCell));	
					    int colcnt=0;
						   // boldCell.setWrap(true);
						       s.addCell(new Label(colcnt++, 2, "No", boldCell));	
					  		   s.addCell(new Label(colcnt++, 2, "Area", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Published Date", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Status", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Response", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Response Date", boldCell));
					  		 
					  		   
			    	 ArrayList<TblTaskRes> resList = postDao.getAreaResponse(taskId);
			    	    int colCnt = 0;	
						int itemCnt = 2;
						int seq = 0;
			    	  for(TblTaskRes result:resList){
			    		 
			    		
			    			colCnt = 0;
			 				itemCnt++;
			 				seq++;
			 				s.addCell(new jxl.write.Number(colCnt++, itemCnt, seq));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getRecipient()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getUpdateDate()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getTaskResponseType()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getTaskRemark()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getResponseDate()));
			 				
			 				
			    		 
			    		  
			    	  }
			     
			    	w.write();
			 	  	w.close();
			    }catch(Exception e){
			        System.out.println(e.getMessage());
			    }
		       
		   
		}

		

		public void storeexcel(){
			HttpServletResponse response = ServletActionContext.getResponse();
			WritableFont bold = new WritableFont(WritableFont.ARIAL, 
	                WritableFont.DEFAULT_POINT_SIZE, 
	                WritableFont.BOLD);
			WritableFont header = new WritableFont(WritableFont.ARIAL, 
	                16, 
	                WritableFont.BOLD);
			WritableCellFormat headCell = new WritableCellFormat(header);
		  	WritableCellFormat boldCell = new WritableCellFormat(bold);
			 try{	
				 	TaskPostDao postDao=new TaskPostDao();
				 	
				 	response.setContentType("application/vnd.ms-excel");
				  	response.setHeader("Content-Disposition", "attachment; filename=excelreport.xls");
				  	WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
				  	
				  		getUserBean();
				  		TblTask res=postDao.getPostById(taskId);
				  		
				  		WritableSheet s = w.createSheet(res.getTaskSubject(), 0);
					  	s.addCell(new Label(1, 0,res.getTaskSubject(), headCell));	
					    int colcnt=0;
						   // boldCell.setWrap(true);
						       s.addCell(new Label(colcnt++, 2, "No", boldCell));	
					  		   s.addCell(new Label(colcnt++, 2, "Store", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Published Date", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Status", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Response", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Response Date", boldCell));
					  		 
					  		   
			    	 ArrayList<TblTaskRes> resList = postDao.getStoreResponse(taskId);
			    	    int colCnt = 0;	
						int itemCnt = 2;
						int seq = 0;
			    	  for(TblTaskRes result:resList){
			    		 
			    		
			    			colCnt = 0;
			 				itemCnt++;
			 				seq++;
			 				s.addCell(new jxl.write.Number(colCnt++, itemCnt, seq));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getRecipient()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getUpdateDate()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getTaskResponseType()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getTaskRemark()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getResponseDate()));
			 				
			 				
			    		 
			    		  
			    	  }
			     
			    	w.write();
			 	  	w.close();
			    }catch(Exception e){
			        System.out.println(e.getMessage());
			    }
		       
		   
		}

		
		

		//----------------Storeexport------------------start
			public void hqStoreExcel(){
				
				HttpServletResponse response = ServletActionContext.getResponse();
				WritableFont bold = new WritableFont(WritableFont.ARIAL, 
		                WritableFont.DEFAULT_POINT_SIZE, 
		                WritableFont.BOLD);
				WritableFont header = new WritableFont(WritableFont.ARIAL, 
		                16, 
		                WritableFont.BOLD);
				WritableCellFormat headCell = new WritableCellFormat(header);
			  	WritableCellFormat boldCell = new WritableCellFormat(bold);
				 try{	
					 	TaskPostDao postDao=new TaskPostDao();
					 	
					 	response.setContentType("application/vnd.ms-excel");
					  	response.setHeader("Content-Disposition", "attachment; filename=excelreport.xls");
					  	WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
					  	
				    	getUserBean();
				    	ArrayList<TblTaskRes> taskRes=getTaskAllList();
				     	
				    
				     for(TblTaskRes res:taskRes){
				    	 ArrayList<TblTaskStore> storeList=postDao.getStoreTaskList(res.getTaskId());
				    	 if(storeList.size()!=0){
				    	  	WritableSheet s = w.createSheet(res.getTaskSubject(), 0);
						  	s.addCell(new Label(1, 0,res.getTaskSubject(), headCell));	
						    int colcnt=0;
							   // boldCell.setWrap(true);
							       s.addCell(new Label(colcnt++, 2, "No", boldCell));	
						  		   s.addCell(new Label(colcnt++, 2, "Store", boldCell));
						  		   s.addCell(new Label(colcnt++, 2, "Published Date", boldCell));
						  		   s.addCell(new Label(colcnt++, 2, "Status", boldCell));
						  		   s.addCell(new Label(colcnt++, 2, "Response", boldCell));
						  		   s.addCell(new Label(colcnt++, 2, "Response Date", boldCell));
						  		 
						  		   
				    	 ArrayList<TblTaskRes> resList = postDao.getStoreResponse(res.getTaskId());
				    	    int colCnt = 0;	
							int itemCnt = 2;
							int seq = 0;
				    	  for(TblTaskRes result:resList){
				    		 
				    		
				    			colCnt = 0;
				 				itemCnt++;
				 				seq++;
				 				s.addCell(new jxl.write.Number(colCnt++, itemCnt, seq));
				 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getRecipient()));
				 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getUpdateDate()));
				 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getTaskResponseType()));
				 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getTaskRemark()));
				 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, result.getResponseDate()));
				 				
				 				
				    		 
				    		  
				    	  }}
				     }
				     w.write();
				 	  	w.close();
				    }catch(Exception e){
				        System.out.println(e.getMessage());
				    }
				 }
		 
	public ArrayList<TblTaskRes> getAreaList() {
		TaskPostDao taskDao = new TaskPostDao(); 
		ArrayList<TblTaskRes> resList = taskDao.getAreaManageTask(portfolioId,taskOwnerRole,taskOwnerSite);
		return resList;
	}

	public ArrayList<TblTaskPortfolio> getTaskList() {
		TaskDao taskDao = new TaskDao(); 
		ArrayList<TblTaskPortfolio> resList = taskDao.getTaskPortfolioList();
		return resList;
	}
	
	public ArrayList<TblTaskRes> getTaskAllList() {
		TaskPostDao taskDao = new TaskPostDao(); 
		ArrayList<TblTaskRes> resList = taskDao.getTaskAll(portfolioId,taskOwnerRole,taskOwnerSite);
		return resList;
	}
	
	public ArrayList<TblTaskRes> getCompleteTaskList() {
		TaskPostDao taskDao = new TaskPostDao(); 
		ArrayList<TblTaskRes> resList = taskDao.getCompleteTask(portfolioId,taskOwnerRole,taskOwnerSite);
		return resList;
	}
	
	public ArrayList<TblTaskRes> getFailTaskList() {
		TaskPostDao taskDao = new TaskPostDao(); 
		ArrayList<TblTaskRes> resList = taskDao.getFailedTask(portfolioId,taskOwnerRole,taskOwnerSite);
		return resList;
	}
	
	public ArrayList<TblTask> getTaskPostList() {
		TaskPostDao taskDao = new TaskPostDao(); 
		ArrayList<TblTask> resList = taskDao.getPostAll(portfolioId);
		return resList;
	}
	
	
	public ArrayList<TblTaskRes> getAreaResponseList() {
		TaskPostDao taskDao = new TaskPostDao(); 
		ArrayList<TblTaskRes> resList = taskDao.getAreaResponse(taskId);

		return resList;
	}
	
	public ArrayList<TblTaskRes> getStoreResponseList() {
		TaskPostDao taskDao = new TaskPostDao(); 
		ArrayList<TblTaskRes> resList = taskDao.getStoreResponse(taskId);
		return resList;
	}
	
	public ArrayList<TblTaskRes> getStorePendingList() {
		TaskPostDao taskDao = new TaskPostDao(); 
		ArrayList<TblTaskRes> resList = taskDao.getStorePendingTask(storeId,portfolioId);
		return resList;
	}
	
	public ArrayList<TblTaskRes> getStoreCompletedList() {
		TaskPostDao taskDao = new TaskPostDao(); 
		ArrayList<TblTaskRes> resList = taskDao.getStoreCompletedTask(storeId,portfolioId);
		return resList;
	}
	
	public ArrayList<TblTaskRes> getStoreFailedList() {
		TaskPostDao taskDao = new TaskPostDao(); 
		ArrayList<TblTaskRes> resList = taskDao.getStoreFailedTask(storeId,portfolioId);
		
		return resList;
	}
	

	
	public ArrayList<TblTaskRes> getAreaPendingList() {
		TaskPostDao taskDao = new TaskPostDao(); 
		ArrayList<TblTaskRes> resList = taskDao.getAreaPendingTask(areaId,portfolioId);
		return resList;
	}
	
	public ArrayList<TblTaskRes> getAreaCompletedList() {
		TaskPostDao taskDao = new TaskPostDao(); 
		ArrayList<TblTaskRes> resList = taskDao.getAreaCompletedTask(areaId, portfolioId);
		return resList;
	}
	
	public ArrayList<TblTaskRes> getAreaFailedList() {
		TaskPostDao taskDao = new TaskPostDao(); 
		ArrayList<TblTaskRes> resList = taskDao.getAreaFailedTask(areaId,portfolioId);
		
		return resList;
	}

	public ArrayList<TblMasterRes> getPostCategory() {
		TaskPostDao postDao = new TaskPostDao(); 
		ArrayList<TblMasterRes> resList = null;
		
		if(postLevel.equals("Y")){
			resList = postDao.getPostYears(portfolioId,taskOwnerRole,taskOwnerSite);
			postLevel = "M";
		}else if(postLevel.equals("M")){
			resList = postDao.getPostMonths(portfolioId,taskOwnerRole,taskOwnerSite, postYear);
			postLevel = "W";
		}else if(postLevel.equals("W")){
			resList = postDao.getPostWeek(portfolioId,taskOwnerRole,taskOwnerSite, postYear, postMonth);
			postLevel = "P";
		}
		return resList;
	}
	
	public ArrayList<TblTask> getPostList() {
		TaskPostDao postDao = new TaskPostDao(); 
		ArrayList<TblTask> resList =  postDao.getPosts(portfolioId,taskOwnerRole,taskOwnerSite, postYear, postMonth, postWeek);
		return resList;
	}

	public ArrayList<TblTaskRes> getChooseStoreList() {
		TaskPostDao postDao = new TaskPostDao(); 
		ArrayList<TblTaskRes> resList =postDao.getAreaStores(taskId, areaId,taskAreaId);
		
		return resList;
	}

	
	private int taskId;
	
	public int getTaskId() {
		return taskId;
	}


	public int getPostId() {
		return postId;
	}




	public Integer getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
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


	public String getSearchTxt() {
		return searchTxt;
	}


	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}


	public String getPostLevel() {
		return postLevel;
	}


	public void setPostLevel(String postLevel) {
		this.postLevel = postLevel;
	}


	public String getPostYear() {
		return postYear;
	}


	public void setPostYear(String postYear) {
		this.postYear = postYear;
	}


	public String getPostMonth() {
		return postMonth;
	}


	public void setPostMonth(String postMonth) {
		this.postMonth = postMonth;
	}


	public Integer getPostWeek() {
		return postWeek;
	}


	public void setPostWeek(Integer postWeek) {
		this.postWeek = postWeek;
	}


	public int getTaskStoreId() {
		return taskStoreId;
	}


	public void setTaskStoreId(int taskStoreId) {
		this.taskStoreId = taskStoreId;
	}


	public int getTaskAreaId() {
		return taskAreaId;
	}


	public void setTaskAreaId(int taskAreaId) {
		this.taskAreaId = taskAreaId;
	}



	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public int getAreaId() {
		return areaId;
	}


	public void setAreaId(int areaId) {
		this.areaId = areaId;
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

	public String getTaskSubject() {
		return taskSubject;
	}

	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}
	
	public String getBreadCrumbtm0(){
            Map<String, Object> session = ActionContext.getContext().getSession();
            String header = "";
            LinkDao linkDao = new LinkDao();
            header = linkDao.getTitleByUrlId(portfolioId);
            session.put("breadCrumbtm0",header);            
            System.out.println(header);
            return header;
        }
        
        public String getPrevUrltm0(){
            Map<String, Object> session = ActionContext.getContext().getSession();
            String url = "";
            LinkDao linkDao = new LinkDao();
            url = linkDao.getLinkByUrlId(portfolioId);
            session.put("prevUrltm0",url);    
            System.out.println(url);
            return url;
        }        
	
       public String getBreadCrumbtm1() {
            return breadCrumbtm1;
        }

        public void setBreadCrumbtm1(String breadCrumbtm1) {
            this.breadCrumbtm1 = breadCrumbtm1;
        }
 
       public String getPrevUrltm1() {
            return prevUrltm1;
        }

        public void setPrevUrltm1(String prevUrltm1) {
            this.prevUrltm1 = prevUrltm1;
        }

    public String getBreadCrumbtmy() {
        return breadCrumbtmy;
    }

    public void setBreadCrumbtmy(String breadCrumbtmy) {
        this.breadCrumbtmy = breadCrumbtmy;
    }

    public String getBreadCrumbtmm() {
        return breadCrumbtmm;
    }

    public void setBreadCrumbtmm(String breadCrumbtmm) {
        this.breadCrumbtmm = breadCrumbtmm;
    }

    public String getBreadCrumbtmw() {
        return breadCrumbtmw;
    }

    public void setBreadCrumbtmw(String breadCrumbtmw) {
        this.breadCrumbtmw = breadCrumbtmw;
    }

    public String getPrevUrltmy() {
        return prevUrltmy;
    }

    public void setPrevUrltmy(String prevUrltmy) {
        this.prevUrltmy = prevUrltmy;
    }


    public String getPrevUrltmm() {
        return prevUrltmm;
    }

    public void setPrevUrltmm(String prevUrltmm) {
        this.prevUrltmm = prevUrltmm;
    }


    public String getPrevUrltmw() {
        return prevUrltmw;
    }

    public void setPrevUrltmw(String prevUrltmw) {
        this.prevUrltmw = prevUrltmw;
    }


    public String getPrevUrltmp() {
        return prevUrltmp;
    }

    public void setPrevUrltmp(String prevUrltmp) {
        this.prevUrltmp = prevUrltmp;
    }

    void checkBreadCrumb(){
        
    }
}
