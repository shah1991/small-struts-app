package net.slide.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import net.slide.dao.TaskDao;
import net.slide.pojo.TblTaskPortfolio;
import net.slide.pojo.TblUser;
import net.slide.util.HibernateUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TaskDetailAction extends ActionSupport  {

	private String searchTxt;
	private int deleteList[];
	private int portfolioId;
	private String portfolioCd;
	private String portfolioName;
	private String status;
	private boolean actUpdate = false;;
	private InputStream inputStream;
	public InputStream getInputStream() {
	    return inputStream;
	} 
	
	public String detailadd() {
		String retval = SUCCESS;
		String ans = "###Fail###";
		String msg = "Add failed..!!!";

		TaskDao taskDao = new TaskDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		boolean res = false;
		TblTaskPortfolio f = taskDao.getPortfolioByName(portfolioName);
		if(f == null){
			f = new TblTaskPortfolio();
			f.setPortfolioName(portfolioName);
			f.setStatus(status);
			f.setUpdatedBy(curusr.getUserLogin());
			f.setUpdatedDate(new Date());
			portfolioCd = taskDao.saveTaskPortfoloio(f);
		}else{
			msg = "Task Name already found !!!";
		}

		if ( portfolioCd!=null) {
			addActionMessage(portfolioName + "Added Successfully !!!!");
			ans = portfolioName;
		
		}else{
			
			addActionError("Add failed..!!!");
			ans += msg;
		}
		
		inputStream = new ByteArrayInputStream(ans.getBytes());

		return retval;
	}
	

	public String detailupdate() {
		String retval = SUCCESS;
		String ans = "###Fail###";
		String msg = "Update failed..!!!";

		TaskDao taskDao = new TaskDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		boolean res = false;
		TblTaskPortfolio f=taskDao.getPortfolioById(portfolioId);
		TblTaskPortfolio ffind = taskDao.getPortfolioByName(portfolioName);
		
		
		if(ffind != null && ffind.getPortfolioId() != f.getPortfolioId()){
			msg = "Task Name already found !!!";
		}else{
			f.setPortfolioName(portfolioName);
			f.setStatus(status);
			f.setUpdatedBy(curusr.getUserName());
			f.setUpdatedDate(new Date());
			res = taskDao.updatePortfoloio(f);
		}

		if ( res == false) {
			addActionError("Update failed..!!!");
			ans += msg;
		}else{
			addActionMessage("Updated Successfully !!!!");
			ans = portfolioName;
		}
		
		inputStream = new ByteArrayInputStream(ans.getBytes());

		return retval;
	}
	
	
	public TblTaskPortfolio getTaskById(int id){
		TblTaskPortfolio f = null;
		Session session = HibernateUtil.currentSession();
		try{
			f = (TblTaskPortfolio)session.get(TblTaskPortfolio.class, id);
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
		return f;
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

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioCd() {
		return portfolioCd;
	}

	public void setPortfolioCd(String portfolioCd) {
		this.portfolioCd = portfolioCd;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	
	

}
