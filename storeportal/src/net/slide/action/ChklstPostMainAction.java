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
import net.slide.dao.ChklstDao;
import net.slide.dao.StoreDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblChklst;
import net.slide.pojo.TblChklstTopic;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblChklstRes;
import net.slide.pojores.TblMasterRes;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.slide.dao.LinkDao;
import net.slide.util.UrlBreadCrumb;

@SuppressWarnings("serial")
public class ChklstPostMainAction extends ActionSupport {

	private int chklstId; 
	private int postId;
	private String status;
	private boolean actUpdate = false;;
	private String searchTxt;
	private String postLevel;
	private String postYear;
	private String postMonth;
	private Integer postWeek;
	private int chklstOwnerRole;
	private int chklstOwnerSite;
	private int chklstStoreId;
	private int chklstAreaId;
	private Integer storeId;
    private int areaId;
    private Integer topicId;
    private Integer chklstGroupId;
	private String chklstSubject;
	private String topicName;
        private String breadCrumbcl0;
        private String breadCrumbcl1;
        private String breadCrumbcly;
        private String breadCrumbclm;
        private String breadCrumbclw;
        private String breadCrumbclp;
        private String prevUrlcl0;
        private String prevUrlcl1;
        private String prevUrlcly;
        private String prevUrlclm;
        private String prevUrlclw;
        private String prevUrlclp; 
        
	public String selectstore() throws SessionExpiredException {
		try {
		getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "search";
		}
		
		return  "selectstore";
	}
	
	public String directstore() throws SessionExpiredException {
		try {
		getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "directstore";
		}
		
		return  "directstore";
	}
	
	
	public String storeforarea() throws SessionExpiredException {
		try {
		getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "search";
		}
		
		return  "storeforarea";
	}
	
	
	


	public String entry() throws SessionExpiredException {
		String retval = SUCCESS;
		try {
		TblUser curusr=	getUserBean();
		if(curusr.getTblRole().getRoleId()==1){
			retval="entrystore";
		}else if(curusr.getTblRole().getRoleId()==2){
			retval="entryarea";
		}else if(curusr.getTblRole().getRoleId()==3){
			retval="entryhq";
		}	
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "search";
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
	
	
	
	

	
	public String chklstpost() throws SessionExpiredException {
		
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
		
		if(postLevel.equals("Y"))
			retval = "postyear";
		else if(postLevel.equals("M"))
			retval = "postmonth";
		else if(postLevel.equals("W"))
			retval = "postweek";
		else if(postLevel.equals("P"))
			retval = "post";
		checkBreadCrumb(postLevel);				
		return retval;
	}
	
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		if(chklstGroupId!=null){
			session.put("chklstGroupId",chklstGroupId);
			
		}else{
			if(session.get("chklstGroupId")!=null){
				chklstGroupId=(Integer)session.get("chklstGroupId");
			}
		}
		
		if(topicId!=null){
			session.put("topicId",topicId);
			ChklstDao chklstDao = new ChklstDao();
			TblChklstTopic f = chklstDao.getTopicById(topicId);
			setTopicName(f.getTopicName());
		}else{
			if(session.get("topicId")!=null){
			topicId=(Integer)session.get("topicId");
			ChklstDao chklstDao = new ChklstDao();
			TblChklstTopic f = chklstDao.getTopicById(topicId);
			setTopicName(f.getTopicName());
			}
		}
		
		
		if(storeId!=null){
			session.put("storeId",storeId);
			
		}else{
			if(session.get("storeId")!=null){
				storeId=(Integer)session.get("storeId");
			}
		}
                
		if(breadCrumbcl1!=null){
			session.put("breadCrumbcl1",breadCrumbcl1);
		}else if(session.get("breadCrumbcl1")!=null){
			breadCrumbcl1=(String)session.get("breadCrumbcl1");
		
		}
                
		if(curusr!=null){
			if(curusr.getTblRole().getRoleId()==1){
				
				setStoreId(curusr.getSiteId());
				setChklstOwnerRole(curusr.getTblRole().getRoleId());
				setChklstOwnerSite(curusr.getUserId());
			}else if(curusr.getTblRole().getRoleId()==2){
				
				setAreaId(curusr.getSiteId());
				setChklstOwnerRole(curusr.getTblRole().getRoleId());
				setChklstOwnerSite(curusr.getUserId());
				
			}else if(curusr.getTblRole().getRoleId()==3){
				setChklstOwnerRole(curusr.getTblRole().getRoleId());
				setChklstOwnerSite(curusr.getUserId());
				
			}
		}else{
		
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}
	public String hqchklst() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvlcl");
                session.remove("prevUrlclw");
            
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
	
	
	public String hqcomplete() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvlcl");
                session.remove("prevUrlclw");
            
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
	
	
	public String hqfail() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvlcl");
                session.remove("prevUrlclw");
            
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
	
	
	public String arearesponse() throws SessionExpiredException {
		try {
			getUserBean();
			ChklstDao cDao=new ChklstDao();
			TblChklst c=cDao.getChklstById(chklstId);
			setChklstSubject(c.getChklstSubject());
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
	
	
	public String storechklst() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvlcl");
                session.remove("prevUrlclw");
            
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
                session.remove("curlvlcl");
                session.remove("prevUrlclw");
            
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
	
	public String storeresponseam() throws SessionExpiredException {
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
		return "responseam";
	}
	
	public String topic() throws SessionExpiredException {
                    String retval = "topic";
		try {
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("topicId",null);
			getUserBean();
                          TblUser curusr=	getUserBean();
                       //if(curusr.getTblRole().getRoleId()==2 && forumId==2){
                        if(chklstGroupId==7 && (curusr.getTblRole().getRoleId()==6)){
                              retval="accessdeny";
                        }
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		//return "topic";
                return retval;
	}
	
	public String storefail() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvlcl");
                session.remove("prevUrlclw");
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
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvlcl");
                session.remove("prevUrlclw");
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
                session.remove("curlvlcl");
                session.remove("prevUrlclw");
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
                session.remove("curlvlcl");
                session.remove("prevUrlclw");
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
	
	
	public String areastorepending() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvlcl");
                session.remove("prevUrlclw");
		try {
			getUserBean();
			
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "areastorepending";
		}
		return "areastorepending";
	}
	
	
	
	public String choosestore()throws SessionExpiredException {
		try {
			getUserBean();
			ChklstDao cDao=new ChklstDao();
			TblChklst c=cDao.getChklstById(chklstId);
			setChklstSubject(c.getChklstSubject());
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "choosestore";
		}
		return "choosestore";
	}
	
	

	public String chooseareastore()throws SessionExpiredException {
		try {
			getUserBean();
			ChklstDao cDao=new ChklstDao();
			TblChklst c=cDao.getChklstById(chklstId);
			setChklstSubject(c.getChklstSubject());
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "chooseareastore";
		}
		return "chooseareastore";
	}
	
	
	
	public String choosestorefail()throws SessionExpiredException {
		try {
			getUserBean();
			ChklstDao cDao=new ChklstDao();
			TblChklst c=cDao.getChklstById(chklstId);
			setChklstSubject(c.getChklstSubject());
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "choosestorefail";
		}
		return "choosestorefail";
}
	
	public String choosearea()throws SessionExpiredException {
		try {
			getUserBean();
			ChklstDao cDao=new ChklstDao();
			TblChklst c=cDao.getChklstById(chklstId);
			setChklstSubject(c.getChklstSubject());
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "choosearea";
		}
		return "choosearea";
}

	public String areachklst() throws Exception {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvlcl");
                session.remove("prevUrlclw");
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
	public void exportExcel(){
		
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
	    	getUserBean();
	     	ChklstDao cDao=new ChklstDao();
		  	TblChklst c=cDao.getChklstById(chklstId);
	    	response.setContentType("application/vnd.ms-excel");
		  	response.setHeader("Content-Disposition", "attachment; filename=excelreport.xls");
		  	WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
		  	WritableSheet s = w.createSheet("newsheet", 0);
		  	s.addCell(new Label(1, 0,c.getChklstSubject(), headCell));	
		    int colcnt=0;
		   // boldCell.setWrap(true);
		       s.addCell(new Label(colcnt++, 2, "No", boldCell));	
	  		   s.addCell(new Label(colcnt++, 2, "Store", boldCell));
	  		   s.addCell(new Label(colcnt++, 2, "Published Date", boldCell));
	  		   s.addCell(new Label(colcnt++, 2, "Area", boldCell));
	  		   s.addCell(new Label(colcnt++, 2, "Percentage", boldCell));
	  		   s.addCell(new Label(colcnt++, 2, "Grade", boldCell));
	  		   s.addCell(new Label(colcnt++, 2, "Response Date", boldCell));

			ArrayList<TblChklstRes> resList = getChooseStoreList();
			int colCnt = 0;	
			int itemCnt = 2;
			 int seq = 0;
			for(TblChklstRes res : resList){
				
				colCnt = 0;
				itemCnt++;
				seq++;
				s.addCell(new jxl.write.Number(colCnt++, itemCnt, seq));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getRecipient()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getUpdateDate()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getAreaName()));
				
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getPercentage()));
				
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getGrade()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getResponseDate() ));
				
				
			}
			
	        w.write();
	 	  	w.close();
	    }catch(Exception e){
	        System.out.println(e.getMessage());
	    }
	   
	}


	//----------------------export --------------end
	
	
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
				 	ChklstDao postDao=new ChklstDao();
				 	
				 	response.setContentType("application/vnd.ms-excel");
				  	response.setHeader("Content-Disposition", "attachment; filename=excelreport.xls");
				  	WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
				  	
			    	getUserBean();
			    	ArrayList<TblChklstRes> chklstRes=getChklstAllList();
			     	
			    
			     for(TblChklstRes res:chklstRes){
			    	  	WritableSheet s = w.createSheet(res.getChklstSubject(), 0);
					  	s.addCell(new Label(1, 0,res.getChklstSubject(), headCell));	
					    int colcnt=0;
						   // boldCell.setWrap(true);
						       s.addCell(new Label(colcnt++, 2, "No", boldCell));	
					  		   s.addCell(new Label(colcnt++, 2, "Store", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Published Date", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Area", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Percentage", boldCell));
					  		   s.addCell(new Label(colcnt++, 2, "Grade", boldCell));
			    	 ArrayList<TblChklstRes> resList = postDao.getAreaResponse(res.getChklstId());
			    	    int colCnt = 0;	
						int itemCnt = 2;
						int seq = 0;
			    	  for(TblChklstRes result:resList){
			    		 
			    		  ArrayList<TblChklstRes> allList =postDao.getAreaStores(result.getChklstId(), result.getAreaId(),result.getChklstAreaId());
			    		 for(TblChklstRes all:allList){
			    			colCnt = 0;
			 				itemCnt++;
			 				seq++;
			 				s.addCell(new jxl.write.Number(colCnt++, itemCnt, seq));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, all.getRecipient()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, all.getUpdateDate()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, all.getAreaName()));
			 				
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, all.getPercentage()));
			 				
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, all.getGrade()));
			    		 }
			    		  
			    	  }
			     }
			     w.write();
			 	  	w.close();
			    }catch(Exception e){
			        System.out.println(e.getMessage());
			    }
			 }
		
		
		
		
		//----------------export------------------start
		public void storeexportExcel(){
			
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
		    	getUserBean();
		  
		    	ArrayList<TblChklstRes> resList = getStorePendingList();
		    	response.setContentType("application/vnd.ms-excel");
			  	response.setHeader("Content-Disposition", "attachment; filename=excelreport.xls");
			  	WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
			  	WritableSheet s = w.createSheet("newsheet", 0);
			  	s.addCell(new Label(1, 0,"Store  CheckList", headCell));	
			    int colcnt=0;
			   // boldCell.setWrap(true);
			       s.addCell(new Label(colcnt++, 2, "No", boldCell));	
		  		   s.addCell(new Label(colcnt++, 2, "Check List", boldCell));
		  		   s.addCell(new Label(colcnt++, 2, "Start Date", boldCell));
		  		   s.addCell(new Label(colcnt++, 2, "End Date", boldCell));
	
		  		   s.addCell(new Label(colcnt++, 2, "Percentage", boldCell));
		  		   s.addCell(new Label(colcnt++, 2, "Grade", boldCell));
		  		   s.addCell(new Label(colcnt++, 2, "Response Date", boldCell));

			
				int colCnt = 0;	
				int itemCnt = 2;
				 int seq = 0;
				for(TblChklstRes res : resList){
					colCnt = 0;
					itemCnt++;
					seq++;
				
					s.addCell(new jxl.write.Number(colCnt++, itemCnt, seq));
					s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getChklstSubject()));
					s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getChklstStartDate()));
					s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getChklstEndDate()));
					s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getPercentage()));
			    	s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getGrade()));
			    	s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getResponseDate()));
				}
				
		        w.write();
		 	  	w.close();
		    }catch(Exception e){
		        System.out.println(e.getMessage());
		    }
		   
		}
                
		public void areaExcel(){
			
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
				 	ChklstDao postDao=new ChklstDao();
				 	
				 	response.setContentType("application/vnd.ms-excel");
				  	response.setHeader("Content-Disposition", "attachment; filename=excelreport.xls");
				  	WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
            			    	getUserBean();
                                        ArrayList<TblChklstRes> resList = postDao.getAreaResponse(chklstId);

			    	  	WritableSheet s = w.createSheet("newsheet", 0);
                                        s.addCell(new Label(1, 0,"Store  CheckList", headCell));	
                                         int colcnt=0;
                                    // boldCell.setWrap(true);
                                        s.addCell(new Label(colcnt++, 2, "No", boldCell));	
                                        s.addCell(new Label(colcnt++, 2, "Area", boldCell));	
                                        s.addCell(new Label(colcnt++, 2, "Published Date", boldCell));
                                        s.addCell(new Label(colcnt++, 2, "Percentage", boldCell));
                                        s.addCell(new Label(colcnt++, 2, "Grade", boldCell));
                                        s.addCell(new Label(colcnt++, 2, "Response Date", boldCell));
                                         

			    	    int colCnt = 0;	
						int itemCnt = 2;
						int seq = 0;
			    	  for(TblChklstRes result:resList){
			    		 
			    		  ArrayList<TblChklstRes> allList =postDao.getAreaStores(result.getChklstId(), result.getAreaId(),result.getChklstAreaId());
			    		 for(TblChklstRes all:allList){
			    			colCnt = 0;
			 				itemCnt++;
			 				seq++;
			 				s.addCell(new jxl.write.Number(colCnt++, itemCnt, seq));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, all.getRecipient()));
                                                        s.addCell(new jxl.write.Label(colCnt++, itemCnt, all.getChklstStartDate()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, all.getPercentage()));
			 				s.addCell(new jxl.write.Label(colCnt++, itemCnt, all.getGrade()));
                                                        s.addCell(new jxl.write.Label(colCnt++, itemCnt, all.getResponseDate()));
			    		 }
			    		  
			     }
			     w.write();
			 	  	w.close();
			    }catch(Exception e){
			        System.out.println(e.getMessage());
			    }
			 }
                
		public void storeExcel(){
                        System.out.println(chklstId);
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
		    	getUserBean();
                        ChklstDao postDao=new ChklstDao();
		    	ArrayList<TblChklstRes> resList = postDao.getStoreResponse(chklstId);
		    	response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "attachment; filename=excelreport.xls");
                        WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
                        WritableSheet s = w.createSheet("newsheet", 0);
                        s.addCell(new Label(1, 0,"Store  CheckList", headCell));	
                        int colcnt=0;
                        // boldCell.setWrap(true);
                        s.addCell(new Label(colcnt++, 2, "No", boldCell));	
                        s.addCell(new Label(colcnt++, 2, "Store", boldCell));	
                        s.addCell(new Label(colcnt++, 2, "Store Name", boldCell));	
                        s.addCell(new Label(colcnt++, 2, "Published Date", boldCell));
                        s.addCell(new Label(colcnt++, 2, "Area", boldCell));	
                        s.addCell(new Label(colcnt++, 2, "Percentage", boldCell));
                        s.addCell(new Label(colcnt++, 2, "Grade", boldCell));
                        s.addCell(new Label(colcnt++, 2, "Response Date", boldCell));


                        int colCnt = 0;	
                        int itemCnt = 2;
                        int seq = 0;
                        for(TblChklstRes res : resList){
                            colCnt = 0;
                            itemCnt++;
                            seq++;
                            StoreDao storeDao = new StoreDao();
                            TblStore tblStore = storeDao.getStoreById(res.getStoreId());
                            String storeAddr = tblStore.getStoreLocation();
                            s.addCell(new jxl.write.Number(colCnt++, itemCnt, seq));
                            s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getRecipient()));
                            s.addCell(new jxl.write.Label(colCnt++, itemCnt, storeAddr));
                            s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getChklstStartDate()));
                            s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getAreaName()));
                            s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getPercentage()));
                            s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getGrade()));
                            s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getResponseDate()));
                        }
				
		        w.write();
		 	  	w.close();
		    }catch(Exception e){
		        System.out.println(e.getMessage());
		    }
		   
		}

	public ArrayList<TblChklstTopic> getTopicList() {
		ChklstDao postDao=new ChklstDao();
	
		ArrayList<TblChklstTopic> resList = postDao.topicList(postDao.getGroupById(chklstGroupId));
		
		return resList;
	}
	public ArrayList<TblChklstRes> getAreaList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getAreaManageChklst(topicId,chklstOwnerRole,chklstOwnerSite);
		return resList;
	}

	
	
	public ArrayList<TblChklstRes> getChklstAllList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getChklstAll(topicId,chklstOwnerRole,chklstOwnerSite);
		return resList;
	}
	
	public ArrayList<TblChklstRes> getCompleteTaskList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getCompleteChklst(topicId,chklstOwnerRole,chklstOwnerSite);
		return resList;
	}
	
	
	public ArrayList<TblChklstRes> getCompleteChklstList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getCompleteChklst(topicId,chklstOwnerRole,chklstOwnerSite);
		return resList;
	}
	
	
	
	public ArrayList<TblChklstRes> getFailedChklstList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getCompleteChklst(topicId,chklstOwnerRole,chklstOwnerSite);
		return resList;
	}

//	public ArrayList<TblTask> getChklstPostList() {
//		ChklstDao postDao=new ChklstDao();
//		ArrayList<TblTask> resList = postDao.getPostAll(topicId);
//		return resList;
//	}
	
	
	public ArrayList<TblChklstRes> getAreaResponseList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getAreaResponse(chklstId);
		return resList;
	}
	
	public ArrayList<TblChklstRes> getStoreResponseList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getStoreResponse(chklstId);
		return resList;
	}
	
	public ArrayList<TblChklstRes> getStorePendingList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getStorePendingChklst(storeId,topicId);
		return resList;
	}
	
	
	public ArrayList<TblChklstRes> getDirectStoreList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getDirectStoreChklst(storeId,topicId);
		return resList;
	}
	
	
	
	
	
	public ArrayList<TblChklstRes> getStoreCompletedList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getStoreCompletedChklst(storeId,topicId);
		return resList;
	}
	
	public ArrayList<TblChklstRes> getStoreFailedList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getStoreFailedChklst(storeId,topicId);
		
		return resList;
	}
	
	
	public ArrayList<TblChklstRes> getChooseStoreList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList =postDao.getAreaStores(chklstId, areaId,chklstAreaId);
		
		return resList;
	}
	
	

	public ArrayList<TblChklstRes> getChooseAreaStoreList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList =postDao.getAreaStore(chklstId, areaId,chklstAreaId);
		
		return resList;
	}
	
	
	public ArrayList<TblStore> getSelectStoreList() {
		StoreDao postDao=new StoreDao();
		ArrayList<TblStore> resList = postDao.getStoresList(areaId);
		return resList;
	}
	
	
	public ArrayList<TblChklstRes>getStoreForAreaList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getStoreForAreaChklst(storeId,topicId);
		return resList;
	}
	

	public ArrayList<TblChklstRes> getAreaPendingList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getAreaPendingChklst(areaId,topicId);
		return resList;
	}
	
	public ArrayList<TblChklstRes> getAreaStorePendingList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getAreaStorePendingChklst(areaId,topicId);
		return resList;
	}
	
	public ArrayList<TblChklstRes> getAreaCompletedList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getAreaCompletedChklst(areaId, topicId);
		return resList;
	}
	
	public ArrayList<TblChklstRes> getAreaFailedList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList = postDao.getAreaFailedChklst(areaId,topicId);
		
		return resList;
	}

	public ArrayList<TblMasterRes> getPostCategory() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblMasterRes> resList = null;
		
		if(postLevel.equals("Y")){
			resList = postDao.getChklstYears(topicId,chklstOwnerRole,chklstOwnerSite);
			postLevel = "M";
		}else if(postLevel.equals("M")){
			resList = postDao.getChklstMonths(topicId,chklstOwnerRole,chklstOwnerSite, postYear);
			postLevel = "W";
		}else if(postLevel.equals("W")){
			resList = postDao.getChklstWeek(topicId,chklstOwnerRole,chklstOwnerSite, postYear, postMonth);
			postLevel = "P";
		}
		return resList;
	}
	
	public ArrayList<TblChklst> getPostList() {
		ChklstDao cDao=new ChklstDao();
		
		ArrayList<TblChklst> resList =  cDao.getChklsts(topicId,chklstOwnerRole,chklstOwnerSite, postYear, postMonth, postWeek);
		return resList;
	}


	public int getChklstId() {
		return chklstId;
	}


	public void setChklstId(int chklstId) {
		this.chklstId = chklstId;
	}


	public int getPostId() {
		return postId;
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


	public int getChklstOwnerRole() {
		return chklstOwnerRole;
	}


	public void setChklstOwnerRole(int chklstOwnerRole) {
		this.chklstOwnerRole = chklstOwnerRole;
	}


	public int getChklstOwnerSite() {
		return chklstOwnerSite;
	}


	public void setChklstOwnerSite(int chklstOwnerSite) {
		this.chklstOwnerSite = chklstOwnerSite;
	}


	public int getChklstStoreId() {
		return chklstStoreId;
	}


	public void setChklstStoreId(int chklstStoreId) {
		this.chklstStoreId = chklstStoreId;
	}


	public int getChklstAreaId() {
		return chklstAreaId;
	}


	public void setChklstAreaId(int chklstAreaId) {
		this.chklstAreaId = chklstAreaId;
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



	public Integer getTopicId() {
		return topicId;
	}


	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}


	public Integer getChklstGroupId() {
		return chklstGroupId;
	}


	public void setChklstGroupId(Integer chklstGroupId) {
		this.chklstGroupId = chklstGroupId;
	}

	public String getChklstSubject() {
		return chklstSubject;
	}

	public void setChklstSubject(String chklstSubject) {
		this.chklstSubject = chklstSubject;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}


	public String getBreadCrumbcl0(){
            Map<String, Object> session = ActionContext.getContext().getSession();
            String header = "";
            LinkDao linkDao = new LinkDao();
            header = linkDao.getTitleByUrlId(chklstGroupId);
            session.put("breadCrumbcl0",header);            
            System.out.println(header);
            return header;
        }
        
        public String getPrevUrlcl0(){
            Map<String, Object> session = ActionContext.getContext().getSession();
            String url = "";
            LinkDao linkDao = new LinkDao();
            url = linkDao.getLinkByUrlId(chklstGroupId);
            session.put("prevUrlcl0",url);    
            System.out.println(url);
            return url;
        }        
	
       public String getBreadCrumbcl1() {
            return breadCrumbcl1;
        }

        public void setBreadCrumbcl1(String breadCrumbcl1) {
            this.breadCrumbcl1 = breadCrumbcl1;
        }
 
       public String getPrevUrlcl1() {
            return prevUrlcl1;
        }

        public void setPrevUrlcl1(String prevUrlcl1) {
            this.prevUrlcl1 = prevUrlcl1;
        }

    public String getBreadCrumbcly() {
        return breadCrumbcly;
    }

    public void setBreadCrumbcly(String breadCrumbcly) {
        this.breadCrumbcly = breadCrumbcly;
    }

    public String getBreadCrumbclm() {
        return breadCrumbclm;
    }

    public void setBreadCrumbclm(String breadCrumbclm) {
        this.breadCrumbclm = breadCrumbclm;
    }

    public String getBreadCrumbclw() {
        return breadCrumbclw;
    }

    public void setBreadCrumbclw(String breadCrumbclw) {
        this.breadCrumbclw = breadCrumbclw;
    }

    public String getPrevUrlcly() {
        return prevUrlcly;
    }

    public void setPrevUrlcly(String prevUrlcly) {
        this.prevUrlcly = prevUrlcly;
    }


    public String getPrevUrlclm() {
        return prevUrlclm;
    }

    public void setPrevUrlclm(String prevUrlclm) {
        this.prevUrlclm = prevUrlclm;
    }


    public String getPrevUrlclw() {
        return prevUrlclw;
    }

    public void setPrevUrlclw(String prevUrlclw) {
        this.prevUrlclw = prevUrlclw;
    }


    public String getPrevUrlclp() {
        return prevUrlclp;
    }

    public void setPrevUrlclp(String prevUrlclp) {
        this.prevUrlclp = prevUrlclp;
    }

  void checkBreadCrumb(String period){
            String url=null;
            int bypassbreadCrumbcl;
            Map<String, Object> session = ActionContext.getContext().getSession();
            if(period.equals("Y")){
                if(session.get("curlvlcl")!=null){
//                    curlvlx=(Integer)session.get("curlvlcl");
                    prevUrlcl1= (String)session.get("prevUrlcl1");
                    breadCrumbcl1=(String)session.get("breadCrumbcl1");                                                
                }else{
                    url=UrlBreadCrumb.getUrl();
                    session.put("prevUrlcl1",url);
                    breadCrumbcl1=(String)session.get("breadCrumbcl1"); 
                    prevUrlcl1= url;
                }
                session.remove("curlvlcl");
                session.remove("prevUrlcly");
                session.remove("prevUrlclm");
                session.remove("prevUrlclw");
                session.remove("breadCrumbcly");
                session.remove("breadCrumbclm");
                session.remove("breadCrumbclw");
                session.put("curlvlcl",1);
            }
            else if(period.equals("M")){
                int curlvlx=(Integer)session.get("curlvlcl");
                if (curlvlx==1){
                    url=UrlBreadCrumb.getUrl();
                    session.put("prevUrlcly",url);
                    session.put("breadCrumbcly",breadCrumbcly);
                    prevUrlcly= url;
                }else{
                    prevUrlcly= (String)session.get("prevUrlcly");
                    breadCrumbcly=(String)session.get("breadCrumbcly");
                }
                session.remove("curlvlcl");
                session.remove("prevUrlclm");
                session.remove("prevUrlclw");
                session.remove("breadCrumbclm");
                session.remove("breadCrumbclw");
                session.put("curlvlcl",2);
                prevUrlcl1= (String)session.get("prevUrlcl1");
                breadCrumbcl1=(String)session.get("breadCrumbcl1");                        
            }
            else if(period.equals("D")){
                    int curlvlx=(Integer)session.get("curlvlcl");
                    if (curlvlx==2){
                        url=UrlBreadCrumb.getUrl();
                        session.put("prevUrlclm",url);
                        session.put("breadCrumbclm",breadCrumbclm);
                        prevUrlclm= url;
                    }else{
                        prevUrlclm= (String)session.get("prevUrlclm");
                        breadCrumbclm=(String)session.get("breadCrumbclm");
                    }
                    session.remove("curlvlcl");
                    session.remove("prevUrlclw");
                    session.remove("breadCrumbclw");
                    session.put("curlvlcl",3);
                    prevUrlcl1=(String)session.get("prevUrlcl1");
                    prevUrlcly=(String)session.get("prevUrlcly");
                    breadCrumbcl1=(String)session.get("breadCrumbcl1");                        
                    breadCrumbcly=(String)session.get("breadCrumbcly");                        
            }
            else if(period.equals("F")) {
                    int curlvlcl;
                    if(session.get("curlvlcl")!=null){
                        curlvlcl=(Integer)session.get("curlvlcl");
                        bypassbreadCrumbcl = 0;
                        session.put("bypassbreadCrumbcl", bypassbreadCrumbcl);
                    }else{
                        curlvlcl=3;
                        session.put("curlvlcl", 3);
                        bypassbreadCrumbcl = 1;                        
                        session.put("bypassbreadCrumbcl", bypassbreadCrumbcl);
                    }
               //     int curlvlx=(Integer)session.get("curlvlcl");
                    if (curlvlcl==3){
                        url=UrlBreadCrumb.getUrl();
                        session.put("prevUrlclw",url);
                        session.put("breadCrumbclw",breadCrumbclw);
                        prevUrlclw= url;
                    }else{
                        prevUrlclw= (String)session.get("prevUrlclw");
                        breadCrumbclw=(String)session.get("breadCrumbclw");
                    }
                    session.put("curlvlcl",4);
                    if (bypassbreadCrumbcl==0){
                        prevUrlcl1=(String)session.get("prevUrlcl1");
                        prevUrlcly=(String)session.get("prevUrlcly");
                        prevUrlclm=(String)session.get("prevUrlclm");
                        breadCrumbcl1=(String)session.get("breadCrumbcl1");                        
                        breadCrumbcly=(String)session.get("breadCrumbcly");                        
                        breadCrumbclm=(String)session.get("breadCrumbclm");                        
                    }
            }
            else if(period.equals("P")){
                    int curlvlcl=(Integer)session.get("curlvlcl");
                    if(session.get("bypassbreadCrumpcl")==null){
                        bypassbreadCrumbcl = 1;
                        session.put("bypassbreadCrumbcl", bypassbreadCrumbcl);
                    }else{
                        bypassbreadCrumbcl=0; // (Integer)session.get("bypassbreadCrumbcl");                    
                        session.put("bypassbreadCrumbcl", bypassbreadCrumbcl);
                    }                    
                    if (curlvlcl==4){
                        url=UrlBreadCrumb.getUrl();
                        session.put("prevUrlclp",url);
                        session.put("breadCrumbclp",breadCrumbclp);
                        prevUrlclp= url;
                    }else{
                        prevUrlclp= (String)session.get("prevUrlclp");
                        breadCrumbclp=(String)session.get("breadCrumbclp");
                    }
                    session.put("curlvlcl",5);
                    if (bypassbreadCrumbcl==0){                    
                        prevUrlcl1=(String)session.get("prevUrlcl1");
                        prevUrlcly=(String)session.get("prevUrlcly");
                        prevUrlclm=(String)session.get("prevUrlclm");
                        breadCrumbcl1=(String)session.get("breadCrumbcl1");                        
                        breadCrumbcly=(String)session.get("breadCrumbcly");                        
                        breadCrumbclm=(String)session.get("breadCrumbclm");                        
                        breadCrumbclw=(String)session.get("breadCrumbclw");                        
                    }
            }
            
            
    }
	
	
}
