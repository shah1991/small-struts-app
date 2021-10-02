package net.slide.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import net.slide.dao.StoreDao;
import net.slide.dao.UserDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblArea;
import net.slide.pojo.TblRole;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblUser;
import net.slide.util.TransException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")

public class AreaDetailAction extends BaseAction {
	
	private boolean updateMode;
	private String active;

	private int id;
	private String areaCode;
	private String areaName;
	
	private int amId;
	
	@SkipValidation
	public String add() throws SessionExpiredException {
		
		try {
			getUserBean();
			updateMode = false;
			active = "Y";
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
	
	@SkipValidation
	public String view() throws SessionExpiredException {
		try {
			getUserBean();
			StoreDao storeDao = new StoreDao();

			TblArea area = storeDao.getAreaById(id);
			areaCode = area.getAreaCd();
			areaName = area.getAreaName();
			active = area.getAreaStatus();
			id = area.getAreaId();
		//	amId=area.getTblUser().getUserId();
			updateMode = true;
			
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

	public String edit() throws SessionExpiredException {
		String retval = SUCCESS;
		
		try {
			
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = getUserBean();
			
			
			updateMode = true;
			
			StoreDao storeDao = new StoreDao();
			//Get the store By ID
			TblArea area = storeDao.getAreaById(id);
			long cnt = 0;
			if(!area.getAreaCd().trim().equalsIgnoreCase(areaCode.trim())) {
				cnt = storeDao.getAreaCountByCode(areaCode);
				
				if(cnt > 0) {
					addActionError("Another Area with same Code already exists, Choose New Code..!!!");
					return retval;
				}
			}
			
			//Copy New value to Area Entity
			area.setAreaCd(areaCode);
			area.setAreaName(areaName);
			area.setAreaStatus(active);
			area.setUpdatedDate(new Date());
			area.setUpdatedBy(curusr.getUserLogin());
		//	UserDao userDao = new UserDao();
		//	area.setTblUser(userDao.getUserById(amId));
			boolean result = storeDao.updateArea(area);

			if (!result) {
				addActionError("Update failed..!!!");
			} else {
				addActionMessage("Area Updated Successfully !!!!");
				retval= SUCCESS;
				session.remove("update");
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


	public String create() throws SessionExpiredException {
		
		String retval = SUCCESS;
		
		try {
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = getUserBean();
			
			
			long cnt = 0;
			StoreDao storeDao = new StoreDao();
			if(areaCode != null) {
				cnt = storeDao.getAreaCountByCode(areaCode);
			}
			
			if(cnt > 0) {
				addActionError("Another Area with same Code already exists, Choose New Code..!!!");
				return INPUT;
			}
			

			TblArea area = new TblArea();
			area.setAreaCd(areaCode);
			area.setAreaName(areaName);
			area.setAreaStatus(active);
			area.setUpdatedDate(new Date());
			area.setUpdatedBy(curusr.getUserLogin());
			// Assign Area manage
		//	UserDao userDao = new UserDao();
			//area.setTblUser(userDao.getUserById(amId));
			
			boolean result = storeDao.addArea(area);

			if (!result) {
				addActionError("Add failed..!!!");
			} else {
				//area=storeDao.getAreaByCode(areaCode);
			//storeDao.addUpdate(area);
				addActionMessage("Add Successfully !!!!");
				retval= SUCCESS;
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
	
	public ArrayList<TblArea> getAreas(){
		StoreDao storeDao = new StoreDao();
		return storeDao.listArea();
	}

	public ArrayList<TblUser> getAreaManagers(){
		ArrayList<TblUser> usrList = null;
		UserDao userDao = new UserDao();
		TblRole tblRole = userDao.getRoleByCode("am");
		if(tblRole != null)
			usrList = userDao.listLookup(tblRole);
		return usrList;
	}

	
	public ArrayList<TblStore> getStoreList(){
		
		StoreDao storeDao = new StoreDao();
		ArrayList<TblStore> storeList = storeDao.getStoresByArea(id);
		return storeList;
	}
	
	/**
	 *Getter & Setter start here
	 */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getAmId() {
		return amId;
	}

	public void setAmId(int amId) {
		this.amId = amId;
	}

	public boolean isUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
}
