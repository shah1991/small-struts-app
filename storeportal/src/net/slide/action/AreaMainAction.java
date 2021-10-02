package net.slide.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import net.slide.dao.StoreDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;
import net.slide.util.TransException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AreaMainAction extends BaseAction {

	private Long deleteList[];
	private String searchTxt;
	private int id;
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
	
	@SkipValidation
	public String delete() throws SessionExpiredException {
		String retval = INPUT;
		
		try {
			getUserBean();
			boolean result = false;

			StoreDao storeDao = new StoreDao();
			result = storeDao.deleteArea(id);
			
			if (!result) {
				addActionError("Delete failed..!!!");
			} else {
				addActionMessage("Area Deleted Successfully !!!!");
				retval= SUCCESS;
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (TransException e) {
			addActionError(e.getMessage());
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}
		
		return retval;
	}

	
	public ArrayList<TblMasterRes> getAreaList() throws SessionExpiredException{

		ArrayList<TblMasterRes> areaList = null;
		try {
			getUserBean();
			StoreDao storeDao = new StoreDao();
			areaList = storeDao.areaList(searchTxt);
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}

		return areaList;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
