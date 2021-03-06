package net.slide.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.struts2.interceptor.validation.SkipValidation;

import net.slide.dao.StoreDao;
import net.slide.dao.UserDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblArea;
import net.slide.pojo.TblRole;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblUser;
import net.slide.util.Constants;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@SuppressWarnings("serial")

public class StoreDetailAction extends ActionSupport {
	
	private boolean updateMode;
	private String storeStatus;
	private int id;
	private String storeCode;
	private String storeName;
	private String storeLocation;
	private String storeAddressLine01;
	private String storeAddressLine02;
	private String state;
	private String postalCode;
	private String telNo01;
	private String faxNo01;
	private String emailAddress;
	private String country;
	private int areaId;
	private int smId;
	
	@SkipValidation
	public String add() throws SessionExpiredException {
		
		try {
			getUserBean();
			updateMode = false;
			storeStatus = "Y";
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

			TblStore tblStore = storeDao.getStoreById(id);
			storeCode = tblStore.getStoreCode();
			storeName = tblStore.getStoreName();
			storeLocation = tblStore.getStoreLocation();
			storeAddressLine01 = tblStore.getStoreAdrLine1();
			storeAddressLine02 = tblStore.getStoreAdrLine2();
			postalCode = tblStore.getPostalCode();
			telNo01 = tblStore.getStoreTelNo();
			faxNo01 = tblStore.getStoreFaxNo();
			emailAddress = tblStore.getStoreEmailId();
			country = tblStore.getCountry();
			id = tblStore.getStoreId();
			storeStatus = tblStore.getStoreStatus();
			areaId=tblStore.getTblArea().getAreaId();
		//	smId=tblStore.getTblUser().getUserId();
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
		String retval = INPUT;
		
		try {
			
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = getUserBean();
			
			
			updateMode = true;
			
			StoreDao storeDao = new StoreDao();
			//Get the store By ID
			TblStore tblStore = storeDao.getStoreById(id);
			long cnt = 0;
			if(!tblStore.getStoreCode().trim().equalsIgnoreCase(storeCode.trim())) {
				cnt = storeDao.getStoreCountByCode(storeCode);
				
				if(cnt > 0) {
					addActionError("Another Store with same Code already exists, Choose New Code..!!!");
					return retval;
				}
			}
			
			//Copy New value to Store Entity
			tblStore.setStoreAdrLine1(storeAddressLine01);
			tblStore.setStoreAdrLine2(storeAddressLine02);
			tblStore.setPostalCode(postalCode);
			tblStore.setStoreTelNo(telNo01);
			tblStore.setStoreFaxNo(faxNo01);
			tblStore.setStoreEmailId(emailAddress);
			tblStore.setCountry(country);
			tblStore.setStoreCode(storeCode);
			tblStore.setStoreLocation(storeLocation);
			tblStore.setStoreName(storeName);
			tblStore.setStoreStatus(storeStatus);
			tblStore.setUpdatedDate(new Date());
			tblStore.setUpdatedBy(curusr.getUserLogin());
		
			tblStore.setTblArea(storeDao.getAreaById(areaId));
		//	tblStore.setTblUser(userDao.getUserById(smId));
			boolean result = storeDao.updateStore(tblStore);

			if (!result) {
				addActionError("Update failed..!!!");
			} else {
				addActionMessage("Store Updated Successfully !!!!");
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

		String retval = INPUT;
		String storeCodePrefix="";
		Properties prop = new Properties();

		try {
			// load a properties file from class path, inside static method
			prop.load(getClass().getClassLoader().getResourceAsStream(
					"config.properties"));
			storeCodePrefix=prop.getProperty("store.code.prefix");
		}catch(Exception e){
			e.printStackTrace();
		}

		try {

			TblUser curusr = getUserBean();

			long cnt = 0;
			StoreDao storeDao = new StoreDao();
			// changes by 10055
			//In this code when admin user is adding store at that I am checking the store code is provided by the admin user is With the prefix character or not and I am also checking the length of store code
			boolean flag = false;
			if (storeCode != null) {
				if (storeCode.length() >= 4 && !(storeCode.charAt(0) == (storeCodePrefix.trim().equals("")?-1:storeCodePrefix.charAt(0)))) {
					addActionError("Store Code is not correct..!!!");
					return retval;

				} else if (storeCode.length() <= 3 && !(storeCode.charAt(0) == (storeCodePrefix.trim().equals("")?-1:storeCodePrefix.charAt(0)))) {
					storeCode = storeCodePrefix + storeCode;
					flag = true;
				}
				cnt = storeDao.getStoreCountByCode(storeCode);
			}

			if (cnt > 0) {
				if (flag)
					storeCode = storeCode.substring(1);
				addActionError("Another Store with same Code already exists, Choose New Code..!!!");
				return retval;
			}

			TblStore tblStore = new TblStore();
			
			tblStore.setStoreAdrLine1(storeAddressLine01);
			tblStore.setStoreAdrLine2(storeAddressLine02);
			tblStore.setPostalCode(postalCode);
			tblStore.setStoreTelNo(telNo01);
			tblStore.setStoreFaxNo(faxNo01);
			tblStore.setStoreEmailId(emailAddress);
			tblStore.setCountry(country);
			tblStore.setStoreCode(storeCode);
			tblStore.setStoreLocation(storeLocation);
			tblStore.setStoreName(storeName);
			tblStore.setTblArea(storeDao.getAreaById(areaId));
			tblStore.setStoreStatus(storeStatus);
			
			tblStore.setUpdatedDate(new Date());
			tblStore.setUpdatedBy(curusr.getUserLogin());

			
		
		
			
			boolean result = storeDao.addStore(tblStore);

			if (!result) {
				addActionError("Add failed..!!!");
			} else {
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
		
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		
		if(curusr == null) {
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}
	
	public ArrayList<TblArea> getAreas(){
		StoreDao storeDao = new StoreDao();
		return storeDao.listArea();
	}

	public ArrayList<TblUser> getStoreManagers(){
		ArrayList<TblUser> usrList = null;
		UserDao userDao = new UserDao();
		TblRole tblRole = userDao.getRoleByCode("sm");
		if(tblRole != null)
			usrList = userDao.listLookup(tblRole);
		return usrList;
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

		
	public String getStoreName() {
		return storeName;
	}
	
	@RequiredStringValidator(message="Store Name is required.")
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}

	public String getStoreCode() {
		return storeCode;
	}

	@RequiredStringValidator(message="Store Code is required.")
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreAddressLine01() {
		return storeAddressLine01;
	}

	public void setStoreAddressLine01(String storeAddressLine01) {
		this.storeAddressLine01 = storeAddressLine01;
	}

	public String getStoreAddressLine02() {
		return storeAddressLine02;
	}

	public void setStoreAddressLine02(String storeAddressLine02) {
		this.storeAddressLine02 = storeAddressLine02;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTelNo01() {
		return telNo01;
	}

	public void setTelNo01(String telNo01) {
		this.telNo01 = telNo01;
	}

	public String getFaxNo01() {
		return faxNo01;
	}

	public void setFaxNo01(String faxNo01) {
		this.faxNo01 = faxNo01;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getSmId() {
		return smId;
	}

	public void setSmId(int smId) {
		this.smId = smId;
	}

	public String getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}
}
