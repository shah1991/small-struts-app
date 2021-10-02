package net.slide.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import net.slide.bean.SearchTO;
import net.slide.dao.StoreDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;
import net.slide.util.TransException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class StoreMainAction extends ActionSupport {

	private String storeCode;
	private String storeName;
	private String storeLocation;
	private String searchTxt;
	private ArrayList<Object[]> storeList = null;
	private int id;
	private Long deleteList[];
	
	public String add() {
		return "doadd";
	}

	public String edit() {
		return "doedit";
	}

	public String search() throws SessionExpiredException {
		try {
			getUserBean();
			storeCode = "";
			storeName = "";
			storeLocation = "";
			storeList = null;
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
	
	public ArrayList<Object[]> getStoreList(){
		
		SearchTO search = new SearchTO();
		StoreDao storeDao = new StoreDao();
		
		search.setStoreCode(storeCode);
		search.setStoreLocation(storeLocation);
		search.setStoreName(storeName);
		
		storeList = storeDao.search(search);
		
		return storeList;
	}
	
	public ArrayList<TblStore> getStores(){
		StoreDao storeDao = new StoreDao();
		ArrayList<TblStore> resList = storeDao.search(searchTxt);
		return resList;
	}
	
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		
		if(curusr == null) {
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}
	

	@SkipValidation
	public String delete() throws SessionExpiredException {
		String retval = SUCCESS;
		
		try {
			getUserBean();
			boolean result = false;

			StoreDao storeDao = new StoreDao();
			result = storeDao.deleteStore(id);
			
			if (!result) {
				addActionError("Delete failed..!!!");
			} else {
				addActionMessage("Store Deleted Successfully !!!!");
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
			return SUCCESS;
		}
		
		return retval;
	}

	public Long[] getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(Long[] deleteList) {
		this.deleteList = deleteList;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
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
