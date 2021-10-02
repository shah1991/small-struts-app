package net.slide.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
import net.slide.dao.FileShareDao;
import net.slide.dao.StoreDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblChklst;
import net.slide.pojo.TblChklstStore;
import net.slide.pojo.TblFileShare;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblChklstDetailRes;
import net.slide.pojores.TblFileRes;
import net.slide.pojores.TblMasterRes;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.slide.dao.LinkDao;
import net.slide.util.UrlBreadCrumb;

@SuppressWarnings("serial")
public class FilePostMainAction extends ActionSupport {
	private String postLevel;
	private int storeId;
    private int areaId;
	private Integer groupId;
	private int fileOwnerRole;
	private int fileOwnerSite;
	private String storeCode;
	private String postYear;
	private String postMonth;
	private String postDate;
	private String fileFolder;
	private ArrayList<TblFileRes> storeFileList;
	private ArrayList<TblFileRes> storeFileListUser;
        private String breadCrumbfs0;
        private String breadCrumbfs1;
        private String breadCrumbfsy;
        private String breadCrumbfsm;
        private String breadCrumbfsw;
        private String breadCrumbfsp;
        private String prevUrlfs0;
        private String prevUrlfs1;
        private String prevUrlfsy;
        private String prevUrlfsm;
        private String prevUrlfsw;
        private String prevUrlfsp;        
        
	public String folder() throws SessionExpiredException {
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
		return "folder";
	}
	
	public String entry() throws SessionExpiredException {
		String retval = SUCCESS;
		try {
		TblUser curusr=	getUserBean();
		if(curusr.getTblRole().getRoleId()==4){
			retval="entrystore";
		}else if(curusr.getTblRole().getRoleId()==1){
			retval="entrystore";
		}else if(curusr.getTblRole().getRoleId()==2){
			retval="entryarea";
		}else if(curusr.getTblRole().getRoleId()==3 || curusr.getTblRole().getRoleId()==13){
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

	
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		

		if(curusr!=null){
			if(groupId!=null){
				session.put("groupId",groupId);
			}else if(session.get("groupId")!=null){
				groupId=(Integer)session.get("groupId");
			
			}
			
			if(curusr.getTblRole().getRoleId()==1){
				
				setStoreId(curusr.getSiteId());
				StoreDao storeDao=new StoreDao();
				TblStore s=storeDao.getStoreById(storeId);
				setStoreCode(s.getStoreCode());
				setFileOwnerSite(curusr.getUserId());
				setFileOwnerRole(curusr.getTblRole().getRoleId());
			}else if(curusr.getTblRole().getRoleId()==2){
				setAreaId(curusr.getSiteId());
				
				setFileOwnerSite(curusr.getUserId());
				setFileOwnerRole(curusr.getTblRole().getRoleId());
			}else if(curusr.getTblRole().getRoleId()==3 || curusr.getTblRole().getRoleId()==13){
			
				setFileOwnerSite(curusr.getUserId());
				setFileOwnerRole(curusr.getTblRole().getRoleId());
			}else if(curusr.getTblRole().getRoleId()==4){
				
				setStoreId(curusr.getSiteId());
				StoreDao storeDao=new StoreDao();
				TblStore s=storeDao.getStoreById(storeId);
				setStoreCode(s.getStoreCode());
				setFileOwnerSite(curusr.getUserId());
				setFileOwnerRole(curusr.getTblRole().getRoleId());
			}
		}else{
		
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}
	
	public String hq() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvlfs");
                session.remove("prevUrlfsw");            
                session.remove("bypassbreadCrumbfs");
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
	

	public String area() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvlfs");
                session.remove("prevUrlfsw");            
                session.remove("bypassbreadCrumbfs");
            
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
		return "managearea";
	}
	

	
	public String areapending() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvlfs");
                session.remove("prevUrlfsw");            
                session.remove("bypassbreadCrumbfs");
            
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
	

	public String store() throws SessionExpiredException {
		try {
			Map<String, Object> session = ActionContext.getContext().getSession();
                        session.remove("curlvlfs");
                        session.remove("prevUrlfsw");            
                        session.remove("bypassbreadCrumbfs");
                
			TblUser curusr = (TblUser)session.get("loginUser");
			getUserBean();
			FileShareDao fDao=new FileShareDao();
			if(curusr.getTblRole().getRoleId()==1){
				setStoreFileList(fDao.getStoreFileList(groupId, storeCode, curusr.getUserLogin()));
			}else if(curusr.getTblRole().getRoleId()==4){
				setStoreFileList(fDao.getFranchiseList(groupId, storeCode, curusr.getUserLogin()));
			}
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return "managestore";
	}
	
	public String storeuser() throws SessionExpiredException {
		try {
			Map<String, Object> session = ActionContext.getContext().getSession();
                        session.remove("curlvlfs");
                        session.remove("prevUrlfsw");            
                        session.remove("bypassbreadCrumbfs");
                        
			TblUser curusr = (TblUser)session.get("loginUser");
			getUserBean();
			FileShareDao fDao=new FileShareDao();
			if(curusr.getTblRole().getRoleId()==1){
				setStoreFileListUser(fDao.getFileListStore(groupId, storeCode, curusr.getUserLogin()));
			}else if(curusr.getTblRole().getRoleId()==4){
				setStoreFileListUser(fDao.getFileListFranchise(groupId, storeCode));
			}
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return "managestoreuser";
	}
	
	public String hqcomplete() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvlfs");
                session.remove("prevUrlfsw");            
                session.remove("bypassbreadCrumbfs");
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
	


	
	public String filepost() throws SessionExpiredException {
		
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
		else if(postLevel.equals("D"))
				retval = "postdate";
		else if(postLevel.equals("P"))
			retval = "userpost";
		else if(postLevel.equals("F"))
			retval = "hqfilepost";
		checkBreadCrumb(postLevel);	
		return retval;
	}
	
	public String filepoststore() throws SessionExpiredException {
		
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
			retval = "postyearstore";
		else if(postLevel.equals("M"))
			retval = "postmonthstore";
		else if(postLevel.equals("D"))
				retval = "postdatestore";
		else if(postLevel.equals("P"))
			retval = "userpoststore";
		else if(postLevel.equals("F"))
			retval = "storefilepost";		
		checkBreadCrumb(postLevel);	
		return retval;
	}
	
	// TODO 
	public String filepostarea() throws SessionExpiredException { 
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
			retval = "postyeararea";
		else if(postLevel.equals("M"))
			retval = "postmontharea";
		else if(postLevel.equals("D"))
				retval = "postdatearea";
		else if(postLevel.equals("P"))
			retval = "userpostarea";
		else if(postLevel.equals("F"))
			retval = "areafilepost";		
		checkBreadCrumb(postLevel);	
		return retval;
	}
	
	//----------------export------------------start
	public void exportExcel(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		WritableFont bold = new WritableFont(WritableFont.ARIAL, 
                WritableFont.DEFAULT_POINT_SIZE, 
                WritableFont.BOLD);
		WritableFont head = new WritableFont(WritableFont.ARIAL, 
                11, 
                WritableFont.BOLD);
		WritableCellFormat headCell = new WritableCellFormat(head);
	  	WritableCellFormat boldCell = new WritableCellFormat(bold);
	    try{
	    	getUserBean();
	    	FileShareDao fDao=new FileShareDao();
	    	ArrayList<TblFileRes> fileResList = fDao.getExportFileList(groupId, postDate, fileFolder);
	     	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
		//	chklstStartDate=formatter.format(p.getChklstStartDate());
		//	chklstEndDate=formatter.format(p.getChklstEndDate());
		
	    	response.setContentType("application/vnd.ms-excel");
		  	response.setHeader("Content-Disposition", "attachment; filename="+fileFolder+".xls");
		  	WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
		  	WritableSheet s = w.createSheet("newsheet", 0);
		  	s.addCell(new Label(1, 0,"Date :", headCell));	
		  	s.addCell(new Label(2, 0,postDate, boldCell));	
			s.addCell(new Label(1, 1,"Folder Name :", headCell));	
		  	s.addCell(new Label(2, 1,fileFolder, boldCell));	
		  
		
		    //boldCell.setWrap(true);
		     
	  		
			int colCnt = 0;	
			int itemCnt = 4;
			
			/*SHAN for(String str:getHeader()){
			s.addCell(new jxl.write.Label(colCnt++, itemCnt, str, headCell));
			
			}*/
			s.addCell(new jxl.write.Label(colCnt++, itemCnt, "File Name", headCell));
		
			s.addCell(new jxl.write.Label(colCnt++, itemCnt, "Status", headCell));
			colCnt = 0;
			itemCnt = 5;
			for(TblFileRes res : fileResList){
				colCnt = 0;
				
					s.addCell(new jxl.write.Label(0, itemCnt, res.getFileName()));
					s.addCell(new jxl.write.Label(1, itemCnt, res.getFileFolderName()));
					
				
				
				
				itemCnt++;
			}
			
	        w.write();
	 	  	w.close();
	    }catch(Exception e){
	        System.out.println(e.getMessage());
	    }
	   
	}


	
	
	public ArrayList<TblFileRes> getFileList() {
		FileShareDao fDao=new FileShareDao();
		ArrayList<TblFileRes> resList = fDao.getFileList(groupId,fileOwnerRole,fileOwnerSite);
		return resList;
	}
	
	
	public ArrayList<TblFileRes> getFileDateList(){
		FileShareDao fDao=new FileShareDao();
		ArrayList<TblFileRes> resList = fDao.getHQDateList(groupId,fileOwnerRole,fileOwnerSite);
		return resList;
	}
	
	
	
	public ArrayList<TblFileRes> getStoreFileDateList(){
		FileShareDao fDao=new FileShareDao();
		ArrayList<TblFileRes> resList = fDao.getStoreDateList(groupId,fileOwnerRole,fileOwnerSite,storeCode);
		return resList;
	}
	
	// TODO 
	public List<TblFileRes> getAreaFileDateList() {
		List<TblFileRes> resList = Collections.emptyList();
		FileShareDao fDao=new FileShareDao();
		StoreDao storeDao = new StoreDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		List<String> storeCodes = storeDao.getListOfStoreCodesUnderAreaManager(curusr.getUserId());
		if (!storeCodes.isEmpty()) {
			resList = fDao.getAreaFileDateList(groupId,fileOwnerRole,fileOwnerSite, storeCodes);
		}
		return resList;
	}
	
	public ArrayList<TblFileRes> getFileListStore() {
		FileShareDao fDao=new FileShareDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		ArrayList<TblFileRes> resList = fDao.getFileList(groupId,storeCode, curusr.getUserLogin());
		return resList;
	}
	
	public ArrayList<TblFileRes> getFolderFileList() throws ParseException{
 		FileShareDao fDao=new FileShareDao();
		ArrayList<TblFileRes> resList = fDao.getFolderFileList(groupId,postDate);
		return resList;
	}
	
	public ArrayList<TblFileRes> getStoreFolderFileList() throws ParseException{
		FileShareDao fDao=new FileShareDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		ArrayList<TblFileRes> resList = fDao.getStoreFolderFileList(groupId,postDate,storeCode, curusr.getUserLogin());
		return resList;
	}
	
	// TODO 
	public List<TblFileRes> getAreaFolderFileList() throws ParseException { 
		List<TblFileRes> resList = Collections.emptyList();
		FileShareDao fDao=new FileShareDao();
		StoreDao storeDao = new StoreDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		List<String> storeCodes = storeDao.getListOfStoreCodesUnderAreaManager(curusr.getUserId());
		if (!storeCodes.isEmpty()) {
			resList = fDao.getAreaFolderFileList(groupId, postDate, storeCodes);
		}
		return resList;
	}
	
	public ArrayList<TblFileRes> getDateFileList() throws ParseException{
		FileShareDao fDao=new FileShareDao();
		ArrayList<TblFileRes> resList = fDao.getDateFileList(groupId,postDate,fileFolder);
		return resList;
	}
	
	public ArrayList<TblFileRes> getDateFileListStore() throws ParseException{
		FileShareDao fDao=new FileShareDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		ArrayList<TblFileRes> resList = fDao.getDateFileListStore(groupId,postDate,storeCode, curusr.getUserLogin(),fileFolder);
		return resList;
	}
	
	// TODO 
	public List<TblFileRes> getDateFileListArea() throws ParseException { 
		List<TblFileRes> resList = Collections.emptyList();
		FileShareDao fDao=new FileShareDao();
		StoreDao storeDao = new StoreDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		List<String> storeCodes = storeDao.getListOfStoreCodesUnderAreaManager(curusr.getUserId());
		if (!storeCodes.isEmpty()) {
			resList = fDao.getDateFileListArea(groupId,postDate,fileFolder, storeCodes);
		}
		return resList;
	}
	
	// TODO
	public List<TblFileRes> getFileListArea() {
		List<TblFileRes> resList = Collections.emptyList();
		FileShareDao fDao=new FileShareDao();
		StoreDao storeDao = new StoreDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		List<String> storeCodes = storeDao.getListOfStoreCodesUnderAreaManager(curusr.getUserId());
		if (!storeCodes.isEmpty()) {
			resList = fDao.getFileListArea(groupId,fileOwnerRole,fileOwnerSite, storeCodes);
		}
		return resList;
	}
	
	public ArrayList<TblMasterRes> getPostCategory() {
		FileShareDao postDao=new FileShareDao();
		ArrayList<TblMasterRes> resList = null;
		
		if(postLevel.equals("Y")){
			resList = postDao.getFileYears(groupId);
			postLevel = "M";
		}else if(postLevel.equals("M")){
			resList = postDao.getFileMonths(groupId,postYear);
			postLevel = "D";
		}else if(postLevel.equals("D")){
			resList = postDao.getFileDate(groupId, postYear, postMonth);
			postLevel = "P";
		}
		return resList;
	}
	
	public ArrayList<TblMasterRes> getPostCategoryStore() {
		FileShareDao postDao=new FileShareDao();
		ArrayList<TblMasterRes> resList = null;
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		
		if(postLevel.equals("Y")){
			resList = postDao.getFileYearsStore(groupId, storeCode, curusr.getUserLogin());
			postLevel = "M";
		}else if(postLevel.equals("M")){
			resList = postDao.getFileMonthsStore(groupId,postYear, storeCode, curusr.getUserLogin());
			postLevel = "D";
		}else if(postLevel.equals("D")){
			resList = postDao.getFileDateStore(groupId, postYear, postMonth, storeCode, curusr.getUserLogin());
			postLevel = "P";
		}
		return resList;
	}
	
	// TODO 
	public List<TblMasterRes> getPostCategoryArea() {
		FileShareDao postDao=new FileShareDao();
		StoreDao storeDao = new StoreDao();
		List<TblMasterRes> resList = Collections.emptyList();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		List<String> storeCodes = storeDao.getListOfStoreCodesUnderAreaManager(curusr.getUserId());
		if (!storeCodes.isEmpty()) {
			if(postLevel.equals("Y")){
				resList = postDao.getFileYearsArea(groupId, storeCodes);
				postLevel = "M";
			}else if(postLevel.equals("M")){
				resList = postDao.getFileMonthsArea(groupId,postYear, storeCodes);
				postLevel = "D";
			}else if(postLevel.equals("D")){
				resList = postDao.getFileDateArea(groupId, postYear, postMonth, storeCodes);
				postLevel = "P";
			}
		}
		return resList;
	}
	
	public ArrayList<TblFileRes> getStoreFileList() {
		return storeFileList;
	}

	public void setStoreFileList(ArrayList<TblFileRes> storeFileList) {
		this.storeFileList = storeFileList;
	}

	public ArrayList<TblFileRes> getStoreFileListUser() {
		return storeFileListUser;
	}

	public void setStoreFileListUser(ArrayList<TblFileRes> storeFileListUser) {
		this.storeFileListUser = storeFileListUser;
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

	
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public int getFileOwnerRole() {
		return fileOwnerRole;
	}

	public void setFileOwnerRole(int fileOwnerRole) {
		this.fileOwnerRole = fileOwnerRole;
	}

	public int getFileOwnerSite() {
		return fileOwnerSite;
	}

	public void setFileOwnerSite(int fileOwnerSite) {
		this.fileOwnerSite = fileOwnerSite;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
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

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getFileFolder() {
		return fileFolder;
	}

	public void setFileFolder(String fileFolder) {
		this.fileFolder = fileFolder;
	}

	public String getBreadCrumbfs0(){
            Map<String, Object> session = ActionContext.getContext().getSession();
            String header = "";
            LinkDao linkDao = new LinkDao();
            header = linkDao.getTitleByUrlId(groupId);
            session.put("breadCrumbfs0",header);            
            System.out.println(header);
            return header;
        }
        
        public String getPrevUrlfs0(){
            Map<String, Object> session = ActionContext.getContext().getSession();
            String url = "";
            LinkDao linkDao = new LinkDao();
            url = linkDao.getLinkByUrlId(groupId);
            session.put("prevUrlfs0",url);    
            System.out.println(url);
            return url;
        }        
	
       public String getBreadCrumbfs1() {
            return breadCrumbfs1;
        }

        public void setBreadCrumbfs1(String breadCrumbfs1) {
            this.breadCrumbfs1 = breadCrumbfs1;
        }
 
       public String getPrevUrlfs1() {
            return prevUrlfs1;
        }

        public void setPrevUrlfs1(String prevUrlfs1) {
            this.prevUrlfs1 = prevUrlfs1;
        }

    public String getBreadCrumbfsy() {
        return breadCrumbfsy;
    }

    public void setBreadCrumbfsy(String breadCrumbfsy) {
        this.breadCrumbfsy = breadCrumbfsy;
    }

    public String getBreadCrumbfsm() {
        return breadCrumbfsm;
    }

    public void setBreadCrumbfsm(String breadCrumbfsm) {
        this.breadCrumbfsm = breadCrumbfsm;
    }

    public String getBreadCrumbfsw() {
        return breadCrumbfsw;
    }

    public void setBreadCrumbfsw(String breadCrumbfsw) {
        this.breadCrumbfsw = breadCrumbfsw;
    }


    public String getBreadCrumbfsp() {
        return breadCrumbfsp;
    }

    public void setBreadCrumbfsp(String breadCrumbfsp) {
        this.breadCrumbfsp = breadCrumbfsp;
    }
    
    public String getPrevUrlfsy() {
        return prevUrlfsy;
    }

    public void setPrevUrlfsy(String prevUrlfsy) {
        this.prevUrlfsy = prevUrlfsy;
    }


    public String getPrevUrlfsm() {
        return prevUrlfsm;
    }

    public void setPrevUrlfsm(String prevUrlfsm) {
        this.prevUrlfsm = prevUrlfsm;
    }


    public String getPrevUrlfsw() {
        return prevUrlfsw;
    }

    public void setPrevUrlfsw(String prevUrlfsw) {
        this.prevUrlfsw = prevUrlfsw;
    }


    public String getPrevUrlfsp() {
        return prevUrlfsp;
    }

    public void setPrevUrlfsp(String prevUrlfsp) {
        this.prevUrlfsp = prevUrlfsp;
    }

    void checkBreadCrumb(String period){
            String url=null;
            int bypassbreadCrumbfs;
            Map<String, Object> session = ActionContext.getContext().getSession();
            if(period.equals("Y")){
                if(session.get("curlvlfs")!=null){
//                    curlvlx=(Integer)session.get("curlvlfs");
                    prevUrlfs1= (String)session.get("prevUrlfs1");
                    breadCrumbfs1=(String)session.get("breadCrumbfs1");                                                
                }else{
                    url=UrlBreadCrumb.getUrl();
                    session.put("prevUrlfs1",url);
                    breadCrumbfs1=(String)session.get("breadCrumbfs1"); 
                    prevUrlfs1= url;
                }
                session.remove("curlvlfs");
                session.remove("prevUrlfsy");
                session.remove("prevUrlfsm");
                session.remove("prevUrlfsw");
                session.remove("breadCrumbfsy");
                session.remove("breadCrumbfsm");
                session.remove("breadCrumbfsw");
                session.put("curlvlfs",1);
            }
            else if(period.equals("M")){
                int curlvlx=(Integer)session.get("curlvlfs");
                if (curlvlx==1){
                    url=UrlBreadCrumb.getUrl();
                    session.put("prevUrlfsy",url);
                    session.put("breadCrumbfsy",breadCrumbfsy);
                    prevUrlfsy= url;
                }else{
                    prevUrlfsy= (String)session.get("prevUrlfsy");
                    breadCrumbfsy=(String)session.get("breadCrumbfsy");
                }
                session.remove("curlvlfs");
                session.remove("prevUrlfsm");
                session.remove("prevUrlfsw");
                session.remove("breadCrumbfsm");
                session.remove("breadCrumbfsw");
                session.put("curlvlfs",2);
                prevUrlfs1= (String)session.get("prevUrlfs1");
                breadCrumbfs1=(String)session.get("breadCrumbfs1");                        
            }
            else if(period.equals("D")){
                    int curlvlx=(Integer)session.get("curlvlfs");
                    if (curlvlx==2){
                        url=UrlBreadCrumb.getUrl();
                        session.put("prevUrlfsm",url);
                        session.put("breadCrumbfsm",breadCrumbfsm);
                        prevUrlfsm= url;
                    }else{
                        prevUrlfsm= (String)session.get("prevUrlfsm");
                        breadCrumbfsm=(String)session.get("breadCrumbfsm");
                    }
                    session.remove("curlvlfs");
                    session.remove("prevUrlfsw");
                    session.remove("breadCrumbfsw");
                    session.put("curlvlfs",3);
                    prevUrlfs1=(String)session.get("prevUrlfs1");
                    prevUrlfsy=(String)session.get("prevUrlfsy");
                    breadCrumbfs1=(String)session.get("breadCrumbfs1");                        
                    breadCrumbfsy=(String)session.get("breadCrumbfsy");                        
            }
            else if(period.equals("F")) {
                    int curlvlfs;
                    if(session.get("curlvlfs")!=null){
                        curlvlfs=(Integer)session.get("curlvlfs");
                        bypassbreadCrumbfs = 0;
                        session.put("bypassbreadCrumbfs", bypassbreadCrumbfs);
                    }else{
                        curlvlfs=3;
                        session.put("curlvlfs", 3);
                        bypassbreadCrumbfs = 1;                        
                        session.put("bypassbreadCrumbfs", bypassbreadCrumbfs);
                    }
               //     int curlvlx=(Integer)session.get("curlvlfs");
                    if (curlvlfs==3){
                        url=UrlBreadCrumb.getUrl();
                        session.put("prevUrlfsw",url);
                        session.put("breadCrumbfsw",breadCrumbfsw);
                        prevUrlfsw= url;
                    }else{
                        prevUrlfsw= (String)session.get("prevUrlfsw");
                        breadCrumbfsw=(String)session.get("breadCrumbfsw");
                    }
                    session.put("curlvlfs",4);
                    if (bypassbreadCrumbfs==0){
                        prevUrlfs1=(String)session.get("prevUrlfs1");
                        prevUrlfsy=(String)session.get("prevUrlfsy");
                        prevUrlfsm=(String)session.get("prevUrlfsm");
                        breadCrumbfs1=(String)session.get("breadCrumbfs1");                        
                        breadCrumbfsy=(String)session.get("breadCrumbfsy");                        
                        breadCrumbfsm=(String)session.get("breadCrumbfsm");                        
                    }
            }
            else if(period.equals("P")){
                    int curlvlfs=(Integer)session.get("curlvlfs");
                    bypassbreadCrumbfs=(Integer)session.get("bypassbreadCrumbfs");                    
                    if (curlvlfs==4){
                        url=UrlBreadCrumb.getUrl();
                        session.put("prevUrlfsp",url);
                        session.put("breadCrumbfsp",breadCrumbfsp);
                        prevUrlfsp= url;
                    }else{
                        prevUrlfsp= (String)session.get("prevUrlfsp");
                        breadCrumbfsp=(String)session.get("breadCrumbfsp");
                    }
                    session.put("curlvlfs",5);
                    if (bypassbreadCrumbfs==0){                    
                        prevUrlfs1=(String)session.get("prevUrlfs1");
                        prevUrlfsy=(String)session.get("prevUrlfsy");
                        prevUrlfsm=(String)session.get("prevUrlfsm");
                        breadCrumbfs1=(String)session.get("breadCrumbfs1");                        
                        breadCrumbfsy=(String)session.get("breadCrumbfsy");                        
                        breadCrumbfsm=(String)session.get("breadCrumbfsm");                        
                        breadCrumbfsw=(String)session.get("breadCrumbfsw");                        
                    }
            }
            
    }
	

}
