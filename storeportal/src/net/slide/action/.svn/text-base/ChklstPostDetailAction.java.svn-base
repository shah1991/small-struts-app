package net.slide.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.slide.dao.ChklstDao;
import net.slide.dao.StoreDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblChklst;
import net.slide.pojo.TblChklstArea;
import net.slide.pojo.TblChklstDetail;
import net.slide.pojo.TblChklstStore;
import net.slide.pojo.TblChklstTemplate;
import net.slide.pojo.TblChklstTopic;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblChklstDetailRes;
import net.slide.pojores.TblChklstRes;
import net.slide.pojores.TblStoreRes;
import net.slide.util.CommonUtil;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ChklstPostDetailAction extends ActionSupport {
	private int id;
	private String chklstSubject;
	private String chklstContents;
	private String chklstAttachPath;
	private String chklstAttachFilename;
	private String chklstStatus;
	private String chklstStartDate;
	private String chklstEndDate;
	private boolean actUpdate = false;
	private List<File> chklstAttach = new ArrayList<File>();
	private List<String> chklstAttachContentType = new ArrayList<String>();
	private List<String> chklstAttachFileName = new ArrayList<String>();
	private int slist[];
	private int alist[];
	private int aslist[];
	private int chklstOwnerRole;
	private int chklstOwnerSite;
	private int chklstStoreId;
	private int chklstAreaId;
	private Integer storeId;
    private int areaId;
    private Integer topicId;
    private int chklstId;
    private String recType;
	private String chklstFileName;
	private String chklstFilePath;
	private String chklstRemark;
	private String chklstResponse;
	private String chklstResponseType;
	private String headercol[];
	private String templateHeaders;
	private ArrayList<TblChklstDetailRes> tempHeadList;
	private ArrayList<TblChklstDetailRes> sessTemp;
	private ArrayList<String> headdesc;
	private ArrayList<TblChklstDetailRes> chklstDetailList=new ArrayList<TblChklstDetailRes>();
	private String responseType[];
	private String storeid[];
	private String areaid[];
	private String flag;
	private String topicName;
    private Integer chklstGroupId;
	/**
	 *  Detail Actions
	 */
	public ArrayList<TblStoreRes> getAreaList() {
		StoreDao storeDao = new StoreDao(); 
		ArrayList<TblStoreRes> resList = storeDao.listAreaStore();
		return resList;
	}
	
	public ArrayList<TblStore> getStoreList() {
		StoreDao storeDao = new StoreDao(); 
		ArrayList<TblStore> resList = storeDao.getAllStores();
		return resList;
	}
	
	
	public String tempPopup() throws SessionExpiredException {
		
		try {
			Map<String, Object> session = ActionContext.getContext().getSession();
			
			if(getChklstSubject()!=null){
				session.put("chklstSubject",chklstSubject);
			}
			if(chklstContents!=null){
				session.put("chklstContents",chklstContents);
			}
			if(chklstStartDate!=null){
				session.put("chklstStartDate",chklstStartDate);
			}
			if(chklstEndDate!=null){
				session.put("chklstEndDate",chklstEndDate);
			}
			
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		
	
		return "temppopup";
	}
	
	public String areatempPopup() throws SessionExpiredException {
		
		try {
			Map<String, Object> session = ActionContext.getContext().getSession();
		
			if(chklstSubject!=null){
				session.put("chklstSubject",chklstSubject);
			}
			if(chklstContents!=null){
				session.put("chklstContents",chklstContents);
			}
			if(chklstStartDate!=null){
				session.put("chklstStartDate",chklstStartDate);
			}
			if(chklstEndDate!=null){
				session.put("chklstEndDate",chklstEndDate);
			}
			
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "area";
		}
		
	
		return "areatemppopup";
	}
	
	public ArrayList<String> getHeader(){
		
		ChklstDao postDao = new ChklstDao();
		TblChklstTemplate tem= postDao.getTemplate(topicId);
		setTemplateHeaders(tem.getTemplateHeaders());
		StringTokenizer st=new StringTokenizer(templateHeaders, "|");
	ArrayList<String>	headList=new ArrayList<String>();
		while(st.hasMoreTokens()){
			
			headList.add(st.nextElement().toString());
		    
		}
		
		return headList;
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
	
	public String areastorePopup() throws SessionExpiredException {
		
		try {
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "area";
		}
		
	
		return "areastorepopup";
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
	
	
	
	public String admindelete() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao postDao = new ChklstDao();
			TblChklst p = postDao.getChklstById(chklstId);
			if(p != null) {
				postDao.deleteChklst(chklstId);
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}
		return "manage";
	}
	
	
	public String areadelete() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao postDao = new ChklstDao();
			TblChklst p = postDao.getChklstById(chklstId);
			if(p != null) {
				postDao.deleteChklst(chklstId);
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


	public String detailtemp() throws SessionExpiredException {

		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			sessTemp=new ArrayList<TblChklstDetailRes>();
		  
		    if(session.get("head")!=null)
		    	 sessTemp=(ArrayList<TblChklstDetailRes>)session.get("head");
		    int i=0;
		    StringBuilder sb=new StringBuilder();
			for(String temp:headercol){
				if(temp.equals("")){}else{
				if(i==0){
					sb.append(temp);
					}else{
					if(temp!=null && temp.length() > 0){	
					sb.append("|");
					sb.append(temp);
					}
					}
				i++;
				}
			}
			if(sb.toString().equals("")){}else{
			TblChklstDetailRes res=new TblChklstDetailRes();
			res.setChkStatus("I");
			res.setChklstValue(sb.toString());
		    sessTemp.add(res);
			}
		    session.put("head",sessTemp);
		    headercol=null;
			
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


	public String edittemp() throws SessionExpiredException {

		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			sessTemp=new ArrayList<TblChklstDetailRes>();
			
			actUpdate=true;
		    if(session.get("head")!=null)
		    sessTemp=(ArrayList<TblChklstDetailRes>)session.get("head");
		    int i=0;
		    StringBuilder sb=new StringBuilder();
			for(String temp:headercol){
				if(temp.equals("")){}else{
				if(i==0){
					sb.append(temp);
					}else{
					if(temp!=null && temp.length() > 0){	
					sb.append("|");
					sb.append(temp);
					}
					}
				i++;
				}
			}
			if(sb.toString().equals("")){}else{
			TblChklstDetailRes res=new TblChklstDetailRes();
			res.setChkStatus("I");
			res.setChklstValue(sb.toString());
		    sessTemp.add(res);
			}
		    session.put("head",sessTemp);
		    headercol=null;
			
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
	
	public String editareatemp() throws SessionExpiredException {

		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			sessTemp=new ArrayList<TblChklstDetailRes>();
			
			actUpdate=true;
		    if(session.get("head")!=null)
		    sessTemp=(ArrayList<TblChklstDetailRes>)session.get("head");
		    int i=0;
		    StringBuilder sb=new StringBuilder();
			for(String temp:headercol){
				if(temp.equals("")){}else{
				if(i==0){
					sb.append(temp);
					}else{
					if(temp!=null && temp.length() > 0){	
					sb.append("|");
					sb.append(temp);
					}
					}
				i++;
				}
			}
			if(sb.toString().equals("")){}else{
			TblChklstDetailRes res=new TblChklstDetailRes();
			res.setChkStatus("I");
			res.setChklstValue(sb.toString());
		    sessTemp.add(res);
			}
		    session.put("head",sessTemp);
		    headercol=null;
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "area";
		}
		
		
		return "area";
	}
	
	
	
	
	
	public String uploadexcel() throws SessionExpiredException {

		try {
			getUserBean();
		if(chklstAttach.size()!=0){
			try {
				String chklstAttachPath = importFile(chklstAttach.get(0), chklstAttachFileName.get(0));
				String rootpath = ((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
				readExcelFile(rootpath+chklstAttachPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		 headercol=null;
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
	
	
	
	public String uploadeditexcel() throws SessionExpiredException {

		try {
			getUserBean();
			actUpdate=true;
		if(chklstAttach.size()!=0){
			try {
				String chklstAttachPath = importFile(chklstAttach.get(0), chklstAttachFileName.get(0));
				String rootpath = ((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
				readExcelFile(rootpath+chklstAttachPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		 headercol=null;
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
	
	
	public String uploadareaexcel() throws SessionExpiredException {

		try {
			getUserBean();
		if(chklstAttach.size()!=0){
			try {
				String chklstAttachPath = importFile(chklstAttach.get(0), chklstAttachFileName.get(0));
				String rootpath = ((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
				readExcelFile(rootpath+chklstAttachPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		 headercol=null;
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "area";
		}
		
		
		return "area";
	}
	
	public String uploadareaeditexcel() throws SessionExpiredException {

		try {
			actUpdate=true;
			getUserBean();
		if(chklstAttach.size()!=0){
			try {
				String chklstAttachPath = importFile(chklstAttach.get(0), chklstAttachFileName.get(0));
				String rootpath = ((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
				readExcelFile(rootpath+chklstAttachPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		 headercol=null;
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "area";
		}
		
		
		return "area";
	}
	public void readExcelFile(String filePath) {
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(filePath));
			contentReading(fs);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public void getHeadingFromXlsFile(Sheet sheet) {
		int columnCount = sheet.getColumns();
		for (int i = 0; i < columnCount; i++) {
			sheet.getCell(i, 0).getContents();
		}
	}
	
	public void contentReading(InputStream fileInputStream) {
		WorkbookSettings ws = null;
		Workbook workbook = null;
		Sheet s = null;
		Cell rowData[] = null;
		int rowCount = 0;
		int columnCount = 0;
		int totalSheets = 0;

		try {
			ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			workbook = Workbook.getWorkbook(fileInputStream, ws);

			totalSheets = workbook.getNumberOfSheets();
			if (totalSheets > 0) {
			//	System.out.println("Total Sheets Found:" + totalSheets);
				for (int j = 0; j < totalSheets; j++) {
			//		System.out.println("Sheet Name:"+ workbook.getSheet(j).getName());
				}
			}

			// Getting Default Sheet i.e. 0
			s = workbook.getSheet(0);

			// Reading Individual Cell
			getHeadingFromXlsFile(s);

			// Total Total No Of Rows in Sheet, will return you no of rows
			// that
			// are occupied with some data
			//System.out.println("Total Rows inside Sheet:" + s.getRows());
			rowCount = s.getRows();

			// Total Total No Of Columns in Sheet
		//	System.out.println("Total Column inside Sheet:" + s.getColumns());
			columnCount = s.getColumns();
		
			//session create
			Map<String, Object> session = ActionContext.getContext().getSession();
			sessTemp=new ArrayList<TblChklstDetailRes>();
			  
		
			if(session.get("head")!=null){
		    sessTemp=(ArrayList<TblChklstDetailRes>)session.get("head");
			}
			String emptyCheck="Y";
			// Reading Individual Row Content
			for (int i = 1; i < rowCount; i++) {
				// Get Individual Row
				emptyCheck="Y";
				rowData = s.getRow(i);
				int k=0;
				StringBuilder sb=new StringBuilder();
				if (rowData.length > 1) {
					for(int e=0;e<columnCount;e++){
						if(e<headercol.length){
							if(e<rowData.length){
							
						if (rowData[e] != null){
							if(((rowData[e].getContents()!=null )||(rowData[e].getContents()!="")) && rowData[e].getContents().length() > 0 ){	
							emptyCheck="N";
							}
						}}}
					}
					if(emptyCheck.equals("N")){
					for (int j = 0; j <columnCount; j++) {
						if(j<headercol.length){
						if(j<rowData.length){
							
						
						if (rowData[j] != null){
							if(k==0){
								if(((rowData[j].getContents()!=null )||(rowData[j].getContents()!="")) && rowData[j].getContents().length() > 0 ){	
									sb.append(rowData[j].getContents());
								}else{
									sb.append("-");
								}
								}else{
								if(((rowData[j].getContents()!=null )||(rowData[j].getContents()!="")) && rowData[j].getContents().length() > 0 ){	
									sb.append("|");
									sb.append(rowData[j].getContents());
								}else{
									sb.append("|");
									sb.append("-");
								}
								}
							k++;
						//	System.out.println(rowData[j].getContents());
						}else{
							if(k==0){
								sb.append("-");
							}else{
								sb.append("|");
								sb.append("-");
							}
						}
						}
					 }
					}
				
				if(rowData.length<headercol.length){
					int rowlen=rowData.length;
					for(;rowlen<=headercol.length-1;rowlen++){
						sb.append("|");
						sb.append("-");
					}
				}}
				emptyCheck="E";
				}
				if(sb.toString().equals("")){}else{
					TblChklstDetailRes res=new TblChklstDetailRes();
					res.setChklstValue(sb.toString());
					res.setChkStatus("I");
					sessTemp.add(res);
				} 
				   
				
			}
			 session.put("head",sessTemp);
			
			

			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}

	}
	public String detailareatemp() throws SessionExpiredException {

		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			sessTemp=new ArrayList<TblChklstDetailRes>();
			
			actUpdate=true;
		    if(session.get("head")!=null)
		    sessTemp=(ArrayList<TblChklstDetailRes>)session.get("head");
		    int i=0;
		    StringBuilder sb=new StringBuilder();
			for(String temp:headercol){
				if(temp.equals("")){}else{
				if(i==0){
					sb.append(temp);
					}else{
					if(temp!=null && temp.length() > 0){	
					sb.append("|");
					sb.append(temp);
					}
					}
				i++;
				}
			}
			if(sb.toString().equals("")){}else{
			TblChklstDetailRes res=new TblChklstDetailRes();
			res.setChklstValue(sb.toString());
			res.setChkStatus("I");
		    sessTemp.add(res);
			}
		    session.put("head",sessTemp);
		    headercol=null;
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "area";
		}
		
		
		return "area";
	}
	
	 public String areatemp() throws SessionExpiredException {

		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			sessTemp=new ArrayList<TblChklstDetailRes>();
		  
		    if(session.get("head")!=null)
		    sessTemp=(ArrayList<TblChklstDetailRes>)session.get("head");
		    
		    if(session.get("chklstSubject")!=null){
				chklstSubject=session.get("chklstSubject").toString();
			}
		    if(session.get("chklstContents")!=null){
		    	chklstContents=session.get("chklstSubject").toString();
			}
		    if(session.get("chklstStartDate")!=null){
		    	chklstStartDate=session.get("chklstSubject").toString();
			}
		    if(session.get("chklstEndDate")!=null){
		    	chklstEndDate=session.get("chklstSubject").toString();
			}
			
		
			
		    int i=0;
		    StringBuilder sb=new StringBuilder();
			for(String temp:headercol){
				if(i==0){
					sb.append(temp);
					}else{
					if(temp!=null && temp.length() > 0){	
					sb.append("|");
					sb.append(temp);
					}
					}
				i++;
			}
			TblChklstDetailRes res=new TblChklstDetailRes();
			res.setChklstValue(sb.toString());
		    sessTemp.add(res);
		   
		    session.put("head",sessTemp);
		  
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "area";
		}
		
		
		return "area";
	}
	
	public String detailarea() throws SessionExpiredException {
		
		try {
						
			getUserBean();
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "area";
		}
		
		
		return "area";
	}

	
	public String hqcreate() throws SessionExpiredException {
		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("head",null);
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		
		setChklstSubject("");
		setChklstContents("");
		
		return INPUT;
	}
	
	
	
	
	public String postview() throws SessionExpiredException {
			
			try {
				getUserBean();
				ChklstDao postDao = new ChklstDao();
				TblChklst p = postDao.getChklstById(chklstId);
				if(p != null) {
					ActionContext.getContext().getSession().put("chklstid", (Integer)chklstId );
					setChklstSubject(p.getChklstSubject());
					setChklstContents(p.getChklstContents());
					setChklstStatus(p.getChklstStatus());
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
					chklstStartDate=formatter.format(p.getChklstStartDate());
					chklstEndDate=formatter.format(p.getChklstEndDate());
					setChklstFileName(p.getChklstAttachFilename());
					
					
					if(chklstDetailList== null || chklstDetailList.size() == 0){
						chklstId = (Integer)ActionContext.getContext().getSession().get("chklstid");
						
						int store=0;
						if(storeId!=null){
							store=storeId;
						}
						setChklstDetailList(postDao.chklstDetailList(chklstId,recType,areaId,store));
					}
					
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
	
	
	public String postareaview() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao postDao = new ChklstDao();
			TblChklst p = postDao.getChklstById(chklstId);
			if(p != null) {
				ActionContext.getContext().getSession().put("chklstid", (Integer)chklstId );
				setChklstSubject(p.getChklstSubject());
				setChklstContents(p.getChklstContents());
				setChklstStatus(p.getChklstStatus());
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
				chklstStartDate=formatter.format(p.getChklstStartDate());
				chklstEndDate=formatter.format(p.getChklstEndDate());
				setChklstFileName(p.getChklstAttachFilename());
				if(chklstDetailList== null || chklstDetailList.size() == 0){
					chklstId = (Integer)ActionContext.getContext().getSession().get("chklstid");
					
					int store=0;
					if(storeId!=null){
						store=storeId;
					}
					setChklstDetailList(postDao.chklstStoreDetailList(chklstId,recType,store));
				}
				
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "responseam";
		}
		
		return "responseam";
}

	
	//after depavali
	public String arearemark() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao postDao = new ChklstDao();
			TblChklst p = postDao.getChklstById(chklstId);
			if(p != null) {
				ActionContext.getContext().getSession().put("chklstid", (Integer)chklstId );
				setChklstSubject(p.getChklstSubject());
				setChklstContents(p.getChklstContents());
				setChklstStatus(p.getChklstStatus());
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
				chklstStartDate=formatter.format(p.getChklstStartDate());
				chklstEndDate=formatter.format(p.getChklstEndDate());
				setChklstFileName(p.getChklstAttachFilename());
				
				
				if(chklstDetailList== null || chklstDetailList.size() == 0){
					chklstId = (Integer)ActionContext.getContext().getSession().get("chklstid");
					
					int store=0;
					if(storeId!=null){
						store=storeId;
					}
					setChklstDetailList(postDao.chklstStoreDetailList(chklstId,recType,store));
				}
				
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "arearemark";
		}
		
		return "arearemark";
}
	
	
	public String updateresponsesm() throws SessionExpiredException, ParseException {
		String retval = SUCCESS;
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
			ChklstDao postDao = new ChklstDao();
		 
			 retval="manageuser";
			 TblChklstStore a=new TblChklstStore();  
			 a.setChklstRemark(chklstRemark);
			 a.setUpdatedBy(curusr.getUserName());
			 a.setUpdatedDate(new Date());
			 a.setChklstStatus("R");
			 postDao.updateResponsesm(storeId,chklstId,a,chklstStoreId, chklstDetailList);
		   
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
	

	public String updateAMRemark() throws SessionExpiredException, ParseException {
		String retval = "achklst";
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
			ChklstDao postDao = new ChklstDao();
		 	 TblChklstStore a=new TblChklstStore();  
			 a.setChklstRemark(chklstRemark);
			 a.setUpdatedBy(curusr.getUserName());
			 a.setUpdatedDate(new Date());
			 a.setChklstStatus("R");
			 postDao.updateAMRemark(chklstStoreId, chklstDetailList);
		   
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
	
	
	public String responsesm() throws SessionExpiredException, ParseException {
		try {
			getUserBean();
			ChklstDao postDao = new ChklstDao();
			TblChklst p = postDao.getStoreChklst(chklstId, chklstStoreId);
			TblChklstStore store=null;
			
			if(p != null) {
				setChklstSubject(p.getChklstSubject());
				setChklstContents(p.getChklstContents());
				setChklstStatus(p.getChklstStatus());
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
				chklstStartDate=formatter.format(p.getChklstStartDate());
				chklstEndDate=formatter.format(p.getChklstEndDate());
				setChklstFileName(p.getChklstAttachFilename());
				setChklstFilePath(p.getChklstAttachPath());
				Iterator<TblChklstStore> ta=p.getTblChklstStores().iterator();
				while(ta.hasNext()){
					store=ta.next();
					setChklstRemark(store.getChklstRemark());
					setStoreId(store.getTblStore().getStoreId());
				}
		
				setChklstDetailList(postDao.chklstStoreDetail(chklstId,storeId));
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "responsesm";
		}
		return "responsesm";
	}
	
	
	public String adminview() throws SessionExpiredException, ParseException {
		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();                        
			ChklstDao postDao = new ChklstDao();
			TblChklst p = postDao.getChklstById(chklstId);
			ArrayList<TblChklstStore> storeList=new ArrayList<TblChklstStore>();
			ArrayList<TblChklstArea> areaList=new ArrayList<TblChklstArea>();
			session.put("head",null);
			if(p != null) {
			
				setChklstSubject(p.getChklstSubject());
				setChklstContents(p.getChklstContents());
				setChklstStatus(p.getChklstStatus());
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
				chklstStartDate=formatter.format(p.getChklstStartDate());
				chklstEndDate=formatter.format(p.getChklstEndDate());
				setChklstFileName(p.getChklstAttachFilename());
				setChklstFilePath(p.getChklstAttachPath());
				
				setChklstDetailList(postDao.chklstDetail(chklstId));
				storeList=postDao.getStores(p);
				areaList=postDao.getAreas(p);
				setFlag(p.getChklstAssign());
				int i=0;
				int j=0;
				int[] storeArray = new int[storeList.size()];
				int[] areaArray = new int[areaList.size()];
				for(TblChklstStore tps:storeList){
					storeArray[i]=tps.getTblStore().getStoreId();
					i++;
			}
				if(storeList.size()!=0){
					slist=storeArray;
				}
				for(TblChklstArea tps:areaList){
					areaArray[j]=tps.getTblArea().getAreaId();
					j++;
				}
				if(areaList.size()!=0){
					alist=areaArray;
				}
                        }
			ArrayList<TblChklstDetail> resList = postDao.chklstResDetail(chklstId);
			ArrayList<TblChklstDetailRes> KList=new ArrayList<TblChklstDetailRes>();
			for(TblChklstDetail res:resList) {
				TblChklstDetailRes resCheck=new TblChklstDetailRes();
				resCheck.setChklstDetailId(res.getChklstDetailId());
				resCheck.setChklstValue(res.getChklstValue());
				resCheck.setChkStatus("U");
				KList.add(resCheck);
			}                                
                        session.put("head",KList);
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "adminview";
		}
		
		return "adminview";
	}
	
	public String areaview() throws SessionExpiredException, ParseException {
		
		try {
			getUserBean();
			ChklstDao postDao = new ChklstDao();
			TblChklst p = postDao.getAreaChklst(chklstId, chklstAreaId);
			TblChklstArea area=null;
			
			if(p != null) {
				setChklstSubject(p.getChklstSubject());
				setChklstContents(p.getChklstContents());
				setChklstStatus(p.getChklstStatus());
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
				chklstStartDate=formatter.format(p.getChklstStartDate());
				chklstEndDate=formatter.format(p.getChklstEndDate());
				setChklstFileName(p.getChklstAttachFilename());
				setChklstFilePath(p.getChklstAttachPath());
				Iterator<TblChklstArea> ta=p.getTblChklstAreas().iterator();
				while(ta.hasNext()){
					 area=ta.next();
					 setChklstRemark(area.getChklstRemark());
					 setAreaId(area.getTblArea().getAreaId());
				}
				setChklstDetailList(postDao.chklstAreaDetail(chklstId,areaId));

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
	
	
	public String smview() throws SessionExpiredException, ParseException {
		
		try {
			getUserBean();
			ChklstDao postDao = new ChklstDao();
			TblChklst p = postDao.getStoreChklst(chklstId, chklstStoreId);
			TblChklstStore store=null;
			
			if(p != null) {
				setChklstSubject(p.getChklstSubject());
				setChklstContents(p.getChklstContents());
				setChklstStatus(p.getChklstStatus());
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
				chklstStartDate=formatter.format(p.getChklstStartDate());
				chklstEndDate=formatter.format(p.getChklstEndDate());
				setChklstFileName(p.getChklstAttachFilename());
				setChklstFilePath(p.getChklstAttachPath());
				Iterator<TblChklstStore> ta=p.getTblChklstStores().iterator();
				while(ta.hasNext()){
					store=ta.next();
					setChklstRemark(store.getChklstRemark());
					setStoreId(store.getTblStore().getStoreId());
				}
		
				setChklstDetailList(postDao.chklstStoreDetail(chklstId,storeId));
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "smview";
		}
		
		return "smview";
	}
	
	
	public String storeview() throws SessionExpiredException, ParseException {
		
		try {
			getUserBean();
			ChklstDao postDao = new ChklstDao();
			TblChklst p = postDao.getStoreChklst(chklstId, chklstStoreId);
			TblChklstStore store=null;
			
			if(p != null) {
				setChklstSubject(p.getChklstSubject());
				setChklstContents(p.getChklstContents());
				setChklstStatus(p.getChklstStatus());
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
				chklstStartDate=formatter.format(p.getChklstStartDate());
				chklstEndDate=formatter.format(p.getChklstEndDate());
				setChklstFileName(p.getChklstAttachFilename());
				setChklstFilePath(p.getChklstAttachPath());
				Iterator<TblChklstStore> ta=p.getTblChklstStores().iterator();
				while(ta.hasNext()){
					store=ta.next();
					setChklstRemark(store.getChklstRemark());
					setStoreId(store.getTblStore().getStoreId());
				}
		
				setChklstDetailList(postDao.chklstStoreDetail(chklstId,storeId));
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
	
	
	
	
//after depavali
	public String storeareaview() throws SessionExpiredException, ParseException {
		
		try {
			getUserBean();
			ChklstDao postDao = new ChklstDao();
			TblChklst p = postDao.getStoreChklst(chklstId, chklstStoreId);
			TblChklstStore store=null;
			
			if(p != null) {
				setChklstSubject(p.getChklstSubject());
				setChklstContents(p.getChklstContents());
				setChklstStatus(p.getChklstStatus());
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
				chklstStartDate=formatter.format(p.getChklstStartDate());
				chklstEndDate=formatter.format(p.getChklstEndDate());
				setChklstFileName(p.getChklstAttachFilename());
				setChklstFilePath(p.getChklstAttachPath());
				Iterator<TblChklstStore> ta=p.getTblChklstStores().iterator();
				while(ta.hasNext()){
					store=ta.next();
					setChklstRemark(store.getChklstRemark());
					setStoreId(store.getTblStore().getStoreId());
				}
		
				setChklstDetailList(postDao.chklstStoreDetail(chklstId,storeId));
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "storeareaview";
		}
		
		return "storeareaview";
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
	     	ChklstDao postDao=new ChklstDao();
	     	TblChklst p = postDao.getStoreChklst(chklstId, chklstStoreId);
	     	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			chklstStartDate=formatter.format(p.getChklstStartDate());
			chklstEndDate=formatter.format(p.getChklstEndDate());
		
	    	response.setContentType("application/vnd.ms-excel");
		  	response.setHeader("Content-Disposition", "attachment; filename=excelreport.xls");
		  	WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
		  	WritableSheet s = w.createSheet("newsheet", 0);
		  	s.addCell(new Label(1, 0,"CheckList :", headCell));	
		  	s.addCell(new Label(2, 0,p.getChklstSubject(), boldCell));	
			s.addCell(new Label(1, 1,"Start Date :", headCell));	
		  	s.addCell(new Label(2, 1,chklstStartDate, boldCell));	
		  	s.addCell(new Label(1, 2,"End Date :", headCell));	
		  	s.addCell(new Label(2, 2,chklstEndDate, boldCell));	
		
		   // boldCell.setWrap(true);
		     
	  		TblChklstStore store=null;
	  		ArrayList<TblChklstDetailRes> resDetList=null;
				if(p != null) {
					
					Iterator<TblChklstStore> ta=p.getTblChklstStores().iterator();
					while(ta.hasNext()){
						store=ta.next();
						setChklstRemark(store.getChklstRemark());
						setStoreId(store.getTblStore().getStoreId());
					}
			
					resDetList=(ArrayList<TblChklstDetailRes>)postDao.chklstStoreDetail(chklstId,storeId);
				}
			int colCnt = 0;	
			int itemCnt = 4;
			
			/*SHAN for(String str:getHeader()){
			s.addCell(new jxl.write.Label(colCnt++, itemCnt, str, headCell));
			
			}*/
			s.addCell(new jxl.write.Label(colCnt++, itemCnt, "Response AM", headCell));
			s.addCell(new jxl.write.Label(colCnt++, itemCnt, "Response Type", headCell));
			s.addCell(new jxl.write.Label(colCnt++, itemCnt, "Response SM", headCell));
			colCnt = 0;
			itemCnt = 5;
			for(TblChklstDetailRes res : resDetList){
				colCnt = 0;
				for(String str:res.getTempList()){
					s.addCell(new jxl.write.Label(colCnt++, itemCnt, str));
				}
				
				if(res.getChkChoice().equals("A")){
					s.addCell(new jxl.write.Label(colCnt++, itemCnt, "N/A"));
				}else if(res.getChkChoice().equals("N")){
					s.addCell(new jxl.write.Label(colCnt++, itemCnt, "No"));
				}else{
					s.addCell(new jxl.write.Label(colCnt++, itemCnt, "Yes"));
				}
				
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getChkResponseSm()));
				itemCnt++;
			}
			
	        w.write();
	 	  	w.close();
	    }catch(Exception e){
	        System.out.println(e.getMessage());
	    }
	   
	}


	//----------------------export --------------end
	
	
	public String hqadd() throws SessionExpiredException {
		String retval = "manage";
		boolean res=false;
		try {
			getUserBean();
			// Get User Name
			Map<String, Object> session = ActionContext.getContext().getSession();
			ChklstDao postDao = new ChklstDao();
			TblUser curusr = (TblUser)session.get("loginUser");
			TblChklst c = new TblChklst();	
			// get file path
			if(chklstAttach.size()!=0){
			String chklstAttachPath = importFile(chklstAttach.get(0), chklstAttachFileName.get(0));
			c.setChklstAttachPath(chklstAttachPath);
			c.setChklstAttachFilename(chklstAttachFileName.get(0));

			}
			// Set Post detail
			c.setChklstOwnerRole(curusr.getTblRole().getRoleId());
			c.setChklstOwnerSite(curusr.getUserId());
			c.setChklstAttachFilename(chklstAttachPath);
			c.setChklstSubject(chklstSubject);
			c.setChklstContents(chklstContents); 
			c.setChklstStatus("N");
			c.setUpdatedBy(curusr.getUserName());
			c.setUpdatedDate(new Date());
			c.setChklstAssign(flag);
			try {
				c.setChklstStartDate(new SimpleDateFormat("dd-MMM-yyyy hh:mm").parse(chklstStartDate));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			try {
				c.setChklstEndDate(new SimpleDateFormat("dd-MMM-yyyy hh:mm").parse(chklstEndDate));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			ArrayList<TblChklstDetailRes> finalHead=new ArrayList<TblChklstDetailRes>();		
		    finalHead=(ArrayList<TblChklstDetailRes>)session.get("head");
		    if(flag.equals("A")){
		    if(alist!=null){
		   ArrayList<Integer> storeIdList=new ArrayList<Integer>();
		 
		    	
			    for(TblStoreRes sres:getAreaList()){
			    
			    	for(int aid:alist){
		    		if(sres.getAreaId()==aid){
		    		for(TblStore s:sres.getTblStoreList()){
		    			storeIdList.add(s.getStoreId());
		    				    			
		    		}
		    		}
			    	}
		    	}
			 int[] storeArray=new int[storeIdList.size()];
			 int i=0;
			  for(int s:storeIdList){
				  storeArray[i]=s;
				  i++;
			  }
			  if(storeArray!=null){
				  slist=storeArray;	
			    }
		    }
		    
		 //Area
			res = postDao.savePost(c, topicId, alist, slist,finalHead);
		    }else{
	     //Store 
		    res = postDao.saveStorePost(c, topicId, aslist, slist, finalHead);	
		    }
			
			if (res == false) {
				addActionError("Add failed..!!!");
				retval = INPUT;
			}else{
				addActionMessage(chklstSubject + "Added Successfully !!!!");
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

	
	public String detailedit() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao postDao = new ChklstDao();
			TblChklst p = postDao.getChklstById(chklstId);
			ArrayList<TblChklstStore> storeList=new ArrayList<TblChklstStore>();
			ArrayList<TblChklstArea> areaList=new ArrayList<TblChklstArea>();
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("head",null);
			actUpdate=true;
			if(p != null) {
				setChklstId(p.getChklstId());
				setChklstSubject(p.getChklstSubject());
				setChklstContents(p.getChklstContents());
				setChklstStatus(p.getChklstStatus());
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
				chklstStartDate=formatter.format(p.getChklstStartDate());
				chklstEndDate=formatter.format(p.getChklstEndDate());
				setChklstFileName(p.getChklstAttachFilename());
				setChklstFilePath(p.getChklstAttachPath());
				storeList=postDao.getStores(p);
				areaList=postDao.getAreas(p);
				setFlag(p.getChklstAssign());
				int i=0;
				int j=0;
				int[] storeArray = new int[storeList.size()];
				int[] areaArray = new int[areaList.size()];
				for(TblChklstStore tps:storeList){
					storeArray[i]=tps.getTblStore().getStoreId();
					i++;
				}
				if(storeList.size()!=0){
					slist=storeArray;
				}
				for(TblChklstArea tps:areaList){
					areaArray[j]=tps.getTblArea().getAreaId();
					j++;
				}
				if(areaList.size()!=0){
					alist=areaArray;
				}
			}
			ArrayList<TblChklstDetail> resList = postDao.chklstResDetail(chklstId);
			ArrayList<TblChklstDetailRes> KList=new ArrayList<TblChklstDetailRes>();
			for(TblChklstDetail res:resList) {
				TblChklstDetailRes resCheck=new TblChklstDetailRes();
				resCheck.setChklstDetailId(res.getChklstDetailId());
				resCheck.setChklstValue(res.getChklstValue());
				resCheck.setChkStatus("U");
				KList.add(resCheck);
			}
			
	    	 session.put("head",KList);
		
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
	

	//copy 
	

	public String detailcopy() throws SessionExpiredException {
		
		try {
			getUserBean();
			ChklstDao postDao = new ChklstDao();
			TblChklst p = postDao.getChklstById(chklstId);
			ArrayList<TblChklstStore> storeList=new ArrayList<TblChklstStore>();
			ArrayList<TblChklstArea> areaList=new ArrayList<TblChklstArea>();
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("head",null);
			
			if(p != null) {
				setChklstId(p.getChklstId());
				setChklstSubject(p.getChklstSubject());
				setChklstContents(p.getChklstContents());
				setChklstStatus(p.getChklstStatus());
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
				chklstStartDate=formatter.format(p.getChklstStartDate());
				chklstEndDate=formatter.format(p.getChklstEndDate());
				setChklstFileName(p.getChklstAttachFilename());
				setChklstFilePath(p.getChklstAttachPath());
				storeList=postDao.getStores(p);
				areaList=postDao.getAreas(p);
				setFlag(p.getChklstAssign());
				int i=0;
				int j=0;
				int[] storeArray = new int[storeList.size()];
				int[] areaArray = new int[areaList.size()];
				for(TblChklstStore tps:storeList){
					storeArray[i]=tps.getTblStore().getStoreId();
					i++;
				}
				if(storeList.size()!=0){
					slist=storeArray;
				}
				for(TblChklstArea tps:areaList){
					areaArray[j]=tps.getTblArea().getAreaId();
					j++;
				}
				if(areaList.size()!=0){
					alist=areaArray;
				}
			}
			ArrayList<TblChklstDetail> resList = postDao.chklstResDetail(chklstId);
			ArrayList<TblChklstDetailRes> KList=new ArrayList<TblChklstDetailRes>();
			for(TblChklstDetail res:resList) {
				TblChklstDetailRes resCheck=new TblChklstDetailRes();
				resCheck.setChklstDetailId(res.getChklstDetailId());
				resCheck.setChklstValue(res.getChklstValue());
				resCheck.setChkStatus("I");
				KList.add(resCheck);
			}
			
	    	 session.put("head",KList);
		
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
	

	@SuppressWarnings("unchecked")
	public String detailupdate() throws SessionExpiredException {
		String retval = "manage";
		actUpdate=true;
		boolean res;
		try {
			getUserBean();
			// Get User Name
			Map<String, Object> session = ActionContext.getContext().getSession();
			ChklstDao postDao = new ChklstDao();
			TblUser curusr = (TblUser)session.get("loginUser");
			TblChklst c = postDao.getChklstById(chklstId);
			if(chklstAttach.size()!=0){
			// get file path
			String chklstAttachPath = importFile(chklstAttach.get(0), chklstAttachFileName.get(0));
			c.setChklstAttachPath(chklstAttachPath);
			c.setChklstAttachFilename(chklstAttachFileName.get(0));
	
			}
			// Set Post detail
	
			
			c.setChklstOwnerRole(curusr.getTblRole().getRoleId());
			c.setChklstOwnerSite(curusr.getUserId());
			c.setChklstSubject(chklstSubject);
			c.setChklstContents(chklstContents); 
			c.setChklstStatus("N");
			c.setUpdatedBy(curusr.getUserName());
			c.setUpdatedDate(new Date());
			c.setChklstAssign(flag);
			try {
				c.setChklstStartDate(new SimpleDateFormat("dd-MMM-yyyy").parse(chklstStartDate));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			try {
				c.setChklstEndDate(new SimpleDateFormat("dd-MMM-yyyy").parse(chklstEndDate));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			 ArrayList<TblChklstDetailRes> finalHead=new ArrayList<TblChklstDetailRes>();		
			    finalHead=(ArrayList<TblChklstDetailRes>)session.get("head");
			    if(flag.equals("A")){
			    if(alist!=null){
			   ArrayList<Integer> storeIdList=new ArrayList<Integer>();
			 
			    	
				    for(TblStoreRes sres:getAreaList()){
				    
				    	for(int aid:alist){
			    		if(sres.getAreaId()==aid){
			    		for(TblStore s:sres.getTblStoreList()){
			    			storeIdList.add(s.getStoreId());
			    				    			
			    		}
			    		}
				    	}
			    	}
				 int[] storeArray=new int[storeIdList.size()];
				 int i=0;
				  for(int s:storeIdList){
					  storeArray[i]=s;
					  i++;
				  }
				  if(storeArray!=null){
					  slist=storeArray;	
				    }
			    }
			    
			 //Area
				res = postDao.saveUpdatePost(c, topicId, alist, slist,finalHead);
			    }else{
		     //Store 
			    res = postDao.saveUpdateStorePost(c, topicId, aslist, slist, finalHead);	
			    }
			if (res == false) {
				addActionError("Add failed..!!!");
				retval = INPUT;
			}else{
				addActionMessage(chklstSubject + "Added Successfully !!!!");
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
	
	
	public String detailareaedit() throws SessionExpiredException {
		
		try {
			getUserBean();
			ArrayList<TblChklstStore> storeList=new ArrayList<TblChklstStore>();
			ChklstDao postDao = new ChklstDao();
			TblChklst p = postDao.getChklstById(chklstId);
			Map<String, Object> session = ActionContext.getContext().getSession();
			actUpdate=true;
			session.put("head",null);
			if(p != null) {
				setChklstId(p.getChklstId());
				setChklstSubject(p.getChklstSubject());
				setChklstContents(p.getChklstContents());
				setChklstStatus(p.getChklstStatus());
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
				chklstStartDate=formatter.format(p.getChklstStartDate());
				chklstEndDate=formatter.format(p.getChklstEndDate());
				setChklstFileName(p.getChklstAttachFilename());
				setChklstFilePath(p.getChklstAttachPath());
				storeList=postDao.getStores(p);
				setFlag(p.getChklstAssign());
				int i=0;
				int[] storeArray = new int[storeList.size()];
				for(TblChklstStore tps:storeList){
					storeArray[i]=tps.getTblStore().getStoreId();
					i++;
				}
				if(storeList.size()!=0){
					slist=storeArray;
				}
				ArrayList<TblChklstDetail> resList = postDao.chklstResDetail(chklstId);
				ArrayList<TblChklstDetailRes> KList=new ArrayList<TblChklstDetailRes>();
				for(TblChklstDetail res:resList) {
					TblChklstDetailRes resCheck=new TblChklstDetailRes();
					resCheck.setChklstDetailId(res.getChklstDetailId());
					resCheck.setChklstValue(res.getChklstValue());
					resCheck.setChkStatus("U");
					KList.add(resCheck);
				}
				
		    	 session.put("head",KList);
			}
			
		
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "area";
		}
		
		return "area";
	}
	

	
	

	public String detailareaupdate() throws SessionExpiredException {
		String retval = "managearea";
		actUpdate=true;
		boolean res;
		try {
			getUserBean();
			// Get User Name
			Map<String, Object> session = ActionContext.getContext().getSession();
			ChklstDao postDao = new ChklstDao();
			TblUser curusr = (TblUser)session.get("loginUser");
			TblChklst c = postDao.getChklstById(chklstId);
			if(chklstAttach.size()!=0){
			// get file path
			String chklstAttachPath = importFile(chklstAttach.get(0), chklstAttachFileName.get(0));
			c.setChklstAttachPath(chklstAttachPath);
			c.setChklstAttachFilename(chklstAttachFileName.get(0));
	
			}
			// Set Post detail
	
			
			c.setChklstOwnerRole(curusr.getTblRole().getRoleId());
			c.setChklstOwnerSite(curusr.getUserId());
			c.setChklstSubject(chklstSubject);
			c.setChklstContents(chklstContents); 
			c.setChklstStatus("N");
			c.setUpdatedBy(curusr.getUserName());
			c.setUpdatedDate(new Date());
			c.setChklstAssign(flag);
			try {
				c.setChklstStartDate(new SimpleDateFormat("dd-MMM-yyyy").parse(chklstStartDate));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			try {
				c.setChklstEndDate(new SimpleDateFormat("dd-MMM-yyyy").parse(chklstEndDate));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		    ArrayList<TblChklstDetailRes> finalHead=new ArrayList<TblChklstDetailRes>();		
		

		    finalHead=(ArrayList<TblChklstDetailRes>)session.get("head");
			res = postDao.saveUpdateStorePost(c, topicId, alist, slist,finalHead);
			if (res == false) {
				addActionError("Add failed..!!!");
				retval = "area";
			}else{
				addActionMessage(chklstSubject + "Added Successfully !!!!");
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "area";
		}
		return retval;
	}
	
	public String updateresponse() throws SessionExpiredException, ParseException {
		String retval = SUCCESS;
	try {
		getUserBean();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		ChklstDao postDao = new ChklstDao();
	   if(chklstOwnerRole==1){
		if(flag.equals("S")){
		 retval="schklst";	
		}else{
		  retval="manageuser";
		 
		}
		 TblChklstStore a=new TblChklstStore();  
		 a.setChklstRemark(chklstRemark);
		 a.setUpdatedBy(curusr.getUserName());
		 a.setUpdatedDate(new Date());
		 a.setChklstStatus("R");
		 postDao.updateStoreResponse(storeId,chklstId,a,chklstStoreId, chklstDetailList);
	   }else{
		   
		retval="choosestore";
		
		 TblChklstStore s=new TblChklstStore();  
		 s.setChklstRemark(chklstRemark);
		 s.setUpdatedBy(curusr.getUserName());
		 s.setUpdatedDate(new Date());
		 s.setChklstStatus("I");
		 postDao.updateStoreResponseFromArea(storeId,chklstId,s,chklstStoreId, chklstDetailList);
		 
		 TblChklstArea a=new TblChklstArea();  
		 a.setChklstRemark(chklstRemark);
		 a.setUpdatedBy(curusr.getUserName());
		 a.setUpdatedDate(new Date());
		 a.setChklstStatus("R");
		 postDao.updateAreaResponse(areaId,chklstId,a,chklstAreaId, chklstDetailList);
		
		 

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
	
	//Save Store response
	
	public String savetemp() throws SessionExpiredException, ParseException {
		String retval = SUCCESS;
	try {
		getUserBean();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		ChklstDao postDao = new ChklstDao();
	   if(chklstOwnerRole==1){
		if(flag.equals("S")){
		 retval="schklst";	
		}else{
		  retval="manageuser";
		 
		}
		 TblChklstStore a=new TblChklstStore();  
		 a.setChklstRemark(chklstRemark);
		 a.setUpdatedBy(curusr.getUserName());
		 a.setUpdatedDate(new Date());
		 a.setChklstStatus("P");
		 postDao.tempSaveStoreResponse(storeId,chklstId,a,chklstStoreId, chklstDetailList);
	   }else{
		   
		retval="choosestore";
		
		 TblChklstStore s=new TblChklstStore();  
		 s.setChklstRemark(chklstRemark);
		 s.setUpdatedBy(curusr.getUserName());
		 s.setUpdatedDate(new Date());
		 s.setChklstStatus("P");
		 postDao.tempSaveStoreResponse(storeId,chklstId,s,chklstStoreId, chklstDetailList);
		 
		 TblChklstArea a=new TblChklstArea();  
		 a.setChklstRemark(chklstRemark);
		 a.setUpdatedBy(curusr.getUserName());
		 a.setUpdatedDate(new Date());
		 a.setChklstStatus("R");
		 postDao.tempSaveAreaResponse(areaId,chklstId,a,chklstAreaId, chklstDetailList);
		
		 

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
	
	// Area manager chklst post
	
	public String areacreate() throws SessionExpiredException {
			
			try {
				
				getUserBean();
				Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("head",null);
			} catch (SessionExpiredException se) {
				addActionError("Session Expired..!!!");
				throw se;
			} catch (Exception e) {
				addActionError("Technical Error Happned..!!!");
				e.printStackTrace();
				return "area";
			}
			
			
			setChklstSubject("");
			setChklstContents("");
			return "area";
		}
	
	
	public String areaadd() throws SessionExpiredException {
		String retval = "managearea";
		boolean res=false;
		try {
			getUserBean();
			// Get User Name
			Map<String, Object> session = ActionContext.getContext().getSession();
			ChklstDao postDao = new ChklstDao();
			TblUser curusr = (TblUser)session.get("loginUser");
			TblChklst c = new TblChklst();
			// get file path
			if(chklstAttach.size()!=0){
			String chklstAttachPath = importFile(chklstAttach.get(0), chklstAttachFileName.get(0));
			c.setChklstAttachPath(chklstAttachPath);
			c.setChklstAttachFilename(chklstAttachFileName.get(0));
		
			}
			// Set Post detail
	
			
			c.setChklstOwnerRole(curusr.getTblRole().getRoleId());
			c.setChklstOwnerSite(curusr.getUserId());
			c.setChklstAttachFilename(chklstAttachPath);
			c.setChklstSubject(chklstSubject);
			c.setChklstContents(chklstContents); 
			c.setChklstStatus("N");
			c.setUpdatedBy(curusr.getUserName());
			c.setUpdatedDate(new Date());
			
			try {
				c.setChklstStartDate(new SimpleDateFormat("dd-MMM-yyyy hh:mm").parse(chklstStartDate));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			try {
				c.setChklstEndDate(new SimpleDateFormat("dd-MMM-yyyy hh:mm").parse(chklstEndDate));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
					
			ArrayList<TblChklstDetailRes> finalHead=new ArrayList<TblChklstDetailRes>();		
			
			if(session.get("head")!=null){
		    finalHead=(ArrayList<TblChklstDetailRes>)session.get("head");
			}
			res = postDao.saveStorePost(c, topicId, alist, slist,finalHead);
			if (res == false) {
				addActionError("Add failed..!!!");
				retval = "area";
			}else{
				addActionMessage(chklstSubject + "Added Successfully !!!!");
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			retval = "area";
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
			
			path=path+"/checklists";
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

	
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
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
		
		
		if(chklstGroupId!=null){
			session.put("chklstGroupId",chklstGroupId);
			
		}else{
			if(session.get("chklstGroupId")!=null){
				chklstGroupId=(Integer)session.get("chklstGroupId");
			}
		}
		if(storeId!=null){
			session.put("storeId",storeId);
			
		}else{
			if(session.get("storeId")!=null){
				storeId=(Integer)session.get("storeId");
			}
		}
		if(curusr!=null){
			if(curusr.getTblRole().getRoleId()==1){
				setRecType("S");
				setStoreId(curusr.getSiteId());
				setChklstOwnerRole(curusr.getTblRole().getRoleId());
				setChklstOwnerSite(curusr.getUserId());
			}else if(curusr.getTblRole().getRoleId()==2){
				setRecType("A");
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
	
	
	public String deletetemp() throws SessionExpiredException {
		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
	    	sessTemp=(ArrayList<TblChklstDetailRes>)session.get("head");
	    	sessTemp.remove(id);
	    	session.put("head",sessTemp);
			
	    	headercol=null;
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
	
	

	public String deleteedittemp() throws SessionExpiredException {
		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
	    	sessTemp=(ArrayList<TblChklstDetailRes>)session.get("head");
	    	sessTemp.remove(id);
	    	session.put("head",sessTemp);
	    	actUpdate=true;
	    	headercol=null;
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
	
	public String deletearea() throws SessionExpiredException {
		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
	    	sessTemp=(ArrayList<TblChklstDetailRes>)session.get("head");
	    	sessTemp.remove(id);
	    	session.put("head",sessTemp);
	    	headercol=null;
			
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "area";
		}
		
		
		return "area";
	}
	
	
	public String deleteeditarea() throws SessionExpiredException {
		
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
	    	sessTemp=(ArrayList<TblChklstDetailRes>)session.get("head");
	    	sessTemp.remove(id);
	    	session.put("head",sessTemp);
	    	headercol=null;
	    	actUpdate=true;
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return "area";
		}
		
		
		return "area";
	}
	

public String exportAreaExcel() throws SessionExpiredException {
	

	try {
		getUserBean();
		ChklstDao postDao = new ChklstDao();
		TblChklst p = postDao.getAreaChklst(chklstId, chklstAreaId);
		TblChklstArea area=null;
		
		if(p != null) {
			setChklstSubject(p.getChklstSubject());
			setChklstContents(p.getChklstContents());
			setChklstStatus(p.getChklstStatus());
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			chklstStartDate=formatter.format(p.getChklstStartDate());
			chklstEndDate=formatter.format(p.getChklstEndDate());
			setChklstFileName(p.getChklstAttachFilename());
			setChklstFilePath(p.getChklstAttachPath());
			Iterator<TblChklstArea> ta=p.getTblChklstAreas().iterator();
			while(ta.hasNext()){
				 area=ta.next();
				 setChklstRemark(area.getChklstRemark());
				 setAreaId(area.getTblArea().getAreaId());
			}
			setChklstDetailList(postDao.chklstAreaDetail(chklstId,areaId));
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


	
	
   
	public ArrayList<TblChklstDetailRes> getFinalList(){
    	ArrayList<TblChklstDetailRes> fList=new ArrayList<TblChklstDetailRes>();
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	sessTemp=(ArrayList<TblChklstDetailRes>)session.get("head");
    	int i=1;
    	
    	for(TblChklstDetailRes ses:sessTemp){
    		TblChklstDetailRes res=new TblChklstDetailRes();
		    	StringTokenizer st=new StringTokenizer(ses.getChklstValue(), "|");
		    	headdesc=new ArrayList<String>();
		    	while(st.hasMoreTokens()){
				
					headdesc.add(st.nextElement().toString());
				    i++;
				}
		    	/*for(;i<=getHeader().size();i++){
		    		headdesc.add("");
		    	}*/
		    	i=1;
		    	
		   	res.setChklstDetailId(ses.getChklstDetailId());
		    	
		    	res.setTempList(headdesc);
		    	fList.add(res);
		    }
    	return fList;
    }
    
	public ArrayList<TblChklstDetailRes> getChklstDetail() {
		ChklstDao postDao=new ChklstDao();
		int store=0;
		if(storeId!=null){
			store=storeId;
		}
		ArrayList<TblChklstDetailRes> resList = postDao.chklstDetailList(chklstId,recType,areaId,store);
		return resList;
	}
	
	public ArrayList<TblChklstDetailRes> getDetailList() {
		ChklstDao postDao=new ChklstDao();
		int store=0;
		if(storeId!=null){
			store=storeId;
		}
		ArrayList<TblChklstDetailRes> resList = postDao.chklstDetail(chklstId);
		return resList;
	}
	
	
	
	public ArrayList<TblChklstDetail> getDetailResList() {
		ChklstDao postDao=new ChklstDao();
		int store=0;
		if(storeId!=null){
			store=storeId;
		}
		ArrayList<TblChklstDetail> resList = postDao.chklstResDetail(chklstId);
		return resList;
	}
	
	
	
	
	public ArrayList<TblChklstRes> getChooseStoreList() {
		ChklstDao postDao=new ChklstDao();
		ArrayList<TblChklstRes> resList =postDao.getAreaStores(chklstId, areaId,chklstAreaId);
		
		return resList;
	}
	
	public String getChklstSubject() {
		return chklstSubject;
	}

	public void setChklstSubject(String chklstSubject) {
		this.chklstSubject = chklstSubject;
	}

	public String getChklstContents() {
		return chklstContents;
	}

	public void setChklstContents(String chklstContents) {
		this.chklstContents = chklstContents;
	}

	public String getChklstAttachPath() {
		return chklstAttachPath;
	}

	public void setChklstAttachPath(String chklstAttachPath) {
		this.chklstAttachPath = chklstAttachPath;
	}

	public String getChklstAttachFilename() {
		return chklstAttachFilename;
	}

	public void setChklstAttachFilename(String chklstAttachFilename) {
		this.chklstAttachFilename = chklstAttachFilename;
	}

	public String getChklstStatus() {
		return chklstStatus;
	}

	public void setChklstStatus(String chklstStatus) {
		this.chklstStatus = chklstStatus;
	}

	public String getChklstStartDate() {
		return chklstStartDate;
	}

	public void setChklstStartDate(String chklstStartDate) {
		this.chklstStartDate = chklstStartDate;
	}

	public String getChklstEndDate() {
		return chklstEndDate;
	}

	public void setChklstEndDate(String chklstEndDate) {
		this.chklstEndDate = chklstEndDate;
	}

	public boolean isActUpdate() {
		return actUpdate;
	}

	public void setActUpdate(boolean actUpdate) {
		this.actUpdate = actUpdate;
	}

	public List<File> getChklstAttach() {
		return chklstAttach;
	}

	public void setChklstAttach(List<File> chklstAttach) {
		this.chklstAttach = chklstAttach;
	}

	public List<String> getChklstAttachContentType() {
		return chklstAttachContentType;
	}

	public void setChklstAttachContentType(List<String> chklstAttachContentType) {
		this.chklstAttachContentType = chklstAttachContentType;
	}

	public List<String> getChklstAttachFileName() {
		return chklstAttachFileName;
	}

	public void setChklstAttachFileName(List<String> chklstAttachFileName) {
		this.chklstAttachFileName = chklstAttachFileName;
	}

	public int[] getSlist() {
		return slist;
	}

	public void setSlist(String sList) {
		this.slist = CommonUtil.convertInt(sList);
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

	public String getRecType() {
		return recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

	public int getChklstId() {
		return chklstId;
	}

	public void setChklstId(int chklstId) {
		this.chklstId = chklstId;
	}

	public String getChklstFileName() {
		return chklstFileName;
	}

	public void setChklstFileName(String chklstFileName) {
		this.chklstFileName = chklstFileName;
	}

	public String getChklstFilePath() {
		return chklstFilePath;
	}

	public void setChklstFilePath(String chklstFilePath) {
		this.chklstFilePath = chklstFilePath;
	}

	public String getChklstRemark() {
		return chklstRemark;
	}

	public void setChklstRemark(String chklstRemark) {
		this.chklstRemark = chklstRemark;
	}

	public String getChklstResponse() {
		return chklstResponse;
	}

	public void setChklstResponse(String chklstResponse) {
		this.chklstResponse = chklstResponse;
	}

	public String getChklstResponseType() {
		return chklstResponseType;
	}

	public void setChklstResponseType(String chklstResponseType) {
		this.chklstResponseType = chklstResponseType;
	}

	
	public String getTemplateHeaders() {
		return templateHeaders;
	}

	public void setTemplateHeaders(String templateHeaders) {
		this.templateHeaders = templateHeaders;
	}


		
	public ArrayList<TblChklstDetailRes> getTempHeadList() {
		return tempHeadList;
	}

	public void setTempHeadList(ArrayList<TblChklstDetailRes> tempHeadList) {
		this.tempHeadList = tempHeadList;
	}

	public String[] getHeadercol() {
		return headercol;
	}

	public void setHeadercol(String[] headercol) {
		this.headercol = headercol;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setChklstDetailList(ArrayList<TblChklstDetailRes> chklstDetailList) {
		this.chklstDetailList = chklstDetailList;
	}

//	public ArrayList<TblChklstDetailRes> getChklstDetailList() {
//		if(chklstDetailList== null || chklstDetailList.size() == 0){
//			chklstId = (Integer)ActionContext.getContext().getSession().get("chklstid");
//			ChklstDao postDao = new ChklstDao();
//			int store=0;
//			if(storeId!=null){
//				store=storeId;
//			}
//			chklstDetailList = postDao.chklstDetailList(chklstId,recType,areaId,store);
//		}
//		return chklstDetailList;
//	}
	public ArrayList<TblChklstDetailRes> getChklstDetailList() {
		return chklstDetailList;
	}
	public int[] getAlist() {
		return alist;
	}


	public void setAlist(String aList) {
		this.alist = CommonUtil.convertInt(aList);
	}

	
	
	public int[] getAslist() {
		return aslist;
	}

	public void setAslist(String aslist) {
		this.aslist = CommonUtil.convertInt(aslist);
	}

	public String[] getStoreid() {
		return storeid;
	}

	public void setStoreid(String[] storeid) {
		this.storeid = storeid;
	}

	public String[] getAreaid() {
		return areaid;
	}

	public void setAreaid(String[] areaid) {
		this.areaid = areaid;
	}

	public String[] getResponseType() {
		return responseType;
	}

	public void setResponseType(String[] responseType) {
		this.responseType = responseType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public ArrayList<TblChklstDetailRes> getSessTemp() {
		return sessTemp;
	}

	public void setSessTemp(ArrayList<TblChklstDetailRes> sessTemp) {
		this.sessTemp = sessTemp;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public Integer getChklstGroupId() {
		return chklstGroupId;
	}

	public void setChklstGroupId(Integer chklstGroupId) {
		this.chklstGroupId = chklstGroupId;
	}

	
	
	
}
