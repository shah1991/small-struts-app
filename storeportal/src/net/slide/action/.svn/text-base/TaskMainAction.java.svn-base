package net.slide.action;

import java.util.ArrayList;
import java.util.Map;

import net.slide.dao.TaskDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblTaskPortfolio;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;
import net.slide.util.TransException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TaskMainAction  extends ActionSupport {


	private int portfolioId;
	private String portfolioCd;
	private String portfolioName;
	private String status;
	private boolean actUpdate = false;;
	private String searchTxt;
	
	public String delete() {
		return INPUT;
	}
	
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		
		if(curusr == null) {
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}

	public ArrayList<TblMasterRes> getGroupList() {
		TaskDao taskDao = new TaskDao(); 
		ArrayList<TblMasterRes> resList = taskDao.taskPortfoloioList(searchTxt);
		return resList;
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
	
	
	
	public String detailadd() throws SessionExpiredException {
			
		try {
				getUserBean();
				TaskDao taskDAO=new TaskDao();
				TblTaskPortfolio f = taskDAO.getPortfolioById(portfolioId);
			if(f != null) {
				setPortfolioName(f.getPortfolioName());
				setPortfolioId(f.getPortfolioId());
				}
				setStatus("Y");
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
	
	public String detailedit() throws SessionExpiredException {
		
		try {
			getUserBean();
			TaskDao taskDAO=new TaskDao();
			TblTaskPortfolio f = taskDAO.getPortfolioById(portfolioId);
			
			if(f != null) {
				setPortfolioName(f.getPortfolioName());
				setPortfolioId(f.getPortfolioId());
				setStatus(f.getStatus());
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
		
		return "detail";
	}
	
	public String detaildelete() throws SessionExpiredException {
		
		try {
			getUserBean();
			TaskDao t=new TaskDao();
			TblTaskPortfolio f = t.getPortfolioById(portfolioId);
				if(f != null) {
					t.deleteTaskPortfoloio(portfolioId);
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		}catch (TransException e) {
			addActionError(e.getMessage());
		}  catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}
		
		return INPUT;
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
	
	



}