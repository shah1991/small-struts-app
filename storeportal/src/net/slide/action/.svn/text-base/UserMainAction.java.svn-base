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
import net.slide.dao.LinkDao;
import net.slide.dao.UserDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblChklst;
import net.slide.pojo.TblLink;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblChklstRes;
import net.slide.pojores.TblUserRes;
import net.slide.util.TransException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserMainAction extends ActionSupport  {

	private String searchTxt;
	private Long deleteList[];
	ArrayList<TblUser> userList;
	
	public String add() {
		return "doadd";
	}

	public String edit() {
		return "doedit";
	}

	public String delete() throws SessionExpiredException {

		try {
			UserDao usrDao = new UserDao();
			getUserBean();
			boolean res = usrDao.deleteUsers(getDeleteList());	
			if ( res == false) {
			    addActionError("Failed to delete.");
			}else{
			    addActionMessage("Record(s) successfully removed.");
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		}catch (TransException e) {
			addActionError(e.getMessage());
		}  catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
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
	
	
	public ArrayList<TblUser> getUserList(){
		UserDao c = new UserDao();
		if(searchTxt != null && searchTxt.length() > 0){
			userList = c.listLookup(searchTxt);
		}else {
			userList = c.listLookup();
		}
		
		return userList;
	}
	
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		
		if(curusr == null) {
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}
	
	//----------------export------------------start
	public void exportUserListExcel(){
		
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
	     	UserDao uDao=new UserDao();
		  	
	    	response.setContentType("application/vnd.ms-excel");
		  	response.setHeader("Content-Disposition", "attachment; filename=franchiseeuserlist.xls");
		  	WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
		  	WritableSheet s = w.createSheet("Franchisee User List", 0);
		  	s.addCell(new Label(1, 0,"Franchisee User List", headCell));
		  	
		    int colcnt=0;
		   // boldCell.setWrap(true);
		    s.addCell(new Label(colcnt++, 2, "No", boldCell));	
		    s.addCell(new Label(colcnt++, 2, "Store", boldCell));		    
	  		s.addCell(new Label(colcnt++, 2, "User Name", boldCell));	  		
	  		s.addCell(new Label(colcnt++, 2, "NRIC", boldCell));
	  		s.addCell(new Label(colcnt++, 2, "User Mobile", boldCell));
	  		s.addCell(new Label(colcnt++, 2, "User Email", boldCell));
	  		s.addCell(new Label(colcnt++, 2, "Franchisee Start Date", boldCell));
	  		s.addCell(new Label(colcnt++, 2, "Franchisee End Date", boldCell));
	  		s.addCell(new Label(colcnt++, 2, "Franchisee Duration By Month", boldCell));
	  		s.addCell(new Label(colcnt++, 2, "Scheme", boldCell));
	  		s.addCell(new Label(colcnt++, 2, "Gender", boldCell));
	  		s.addCell(new Label(colcnt++, 2, "RCB Name", boldCell));
	  		s.addCell(new Label(colcnt++, 2, "RCB No", boldCell));
	  		s.addCell(new Label(colcnt++, 2, "GST No", boldCell));
	  		s.addCell(new Label(colcnt++, 2, "User DOJ", boldCell));	  		
	  		s.addCell(new Label(colcnt++, 2, "DOB", boldCell));
	  		s.addCell(new Label(colcnt++, 2, "User Remarks", boldCell));

			ArrayList<TblUserRes> resList = getUserAllList();
			int colCnt = 0;	
			int itemCnt = 2;
			 int seq = 0;
			for(TblUserRes res : resList){
				
				colCnt = 0;
				itemCnt++;
				seq++;
				s.addCell(new jxl.write.Number(colCnt++, itemCnt, seq));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getStoreCode()==null ? "" : res.getStoreCode()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getUserName()==null ? "" : res.getUserName()));				
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getNric()==null ? "" : res.getNric()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getUserMobile()==null ? "" : res.getUserMobile()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getUserEmail()==null ? "" : res.getUserEmail()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getFranchiseeStartDate()==null ? "" : res.getFranchiseeStartDate()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getFranchiseeExpiryDate()==null ? "" : res.getFranchiseeExpiryDate()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getFranchiseeDuration()==null ? "" : res.getFranchiseeDuration()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getFranchiseeScheme()==null ? "" : res.getFranchiseeScheme()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getGender()==null ? "" : res.getGender()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getRcbName()==null ? "" : res.getRcbName()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getRcbNo()==null ? "" : res.getRcbNo()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getGstNo()==null ? "" : res.getGstNo()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getUserDoj()==null ? "" : res.getUserDoj()));				
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getUserDob()==null ? "" : res.getUserDob()));
				s.addCell(new jxl.write.Label(colCnt++, itemCnt, res.getUserRemarks()==null ? "" : res.getUserRemarks()));
								
			}
			
	        w.write();
	 	  	w.close();
	    }catch(Exception e){
	        System.out.println(e.getMessage());
	    }
	   
	}
	
	//----------------export------------------end

	public ArrayList<TblUserRes> getUserAllList() {
		UserDao userDao=new UserDao();
		ArrayList<TblUserRes> resList = userDao.getUserDetailsAll();
		return resList;
	}
	
	public Long[] getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(Long[] deleteList) {
		this.deleteList = deleteList;
	}

	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}

	public void setUserList(ArrayList<TblUser> userList) {
		this.userList = userList;
	}
	
}
