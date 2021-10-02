package net.slide.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import net.slide.dao.StoreDao;
import net.slide.dao.UserDao;
import net.slide.pojo.TblArea;
import net.slide.pojo.TblRole;
import net.slide.pojo.TblStore;
import net.slide.pojo.TblUser;

import com.dfsg.sso.bean.TblClientApplicationRole;
import com.dfsg.sso.bean.TblUserApplicationDetails;
import com.dfsg.sso.filter.SSOFilter;
import com.dfsg.sso.util.SSOToClientUserConverter;

public class SSOToStorePortalUserConverter extends SSOToClientUserConverter {
	
	private UserDao userDao = new UserDao();
	private StoreDao storeDao = new StoreDao();
	
	@Override
	public Object convert(HttpServletRequest req, com.dfsg.sso.bean.TblUser ssoUser) {
		TblUser spUser = new TblUser();
		TblUser origUser = userDao.getUserByName(ssoUser.getUsername());
		
		spUser.setUserId(ssoUser.getUserId());
		
		TblRole role = new TblRole();
		String clientCd = SSOFilter.getClientCode();
		for ( TblUserApplicationDetails uad : ssoUser.getTblUserApplicationDetails() ) {
			if ( StringUtils.equals(clientCd, uad.getTblClientApplication().getClientCd()) ) {
				spUser.setUserId(uad.getUserReferenceId());
				
				TblClientApplicationRole appRole = uad.getTblClientApplicationRole();
				role.setRoleId(appRole.getRoleReferenceId());
				role.setRoleCd(appRole.getRoleCd());
				role.setRoleName(appRole.getRoleName());
				spUser.setTblRole(role);
				
				if ( "SM".equals(role.getRoleCd()) || "FM".equals(role.getRoleCd()) ) {
					TblStore spStore = storeDao.getStoreByCode(uad.getStoreCd());
					if ( spStore != null ) {
						spUser.setSiteId(spStore.getStoreId());
					}
				} else if ( "AM".equals(role.getRoleCd()) ) {
					TblArea spArea = storeDao.getAreaByCode(uad.getAreaCd());
					if ( spArea != null ) {
						spUser.setSiteId(spArea.getAreaId());
					}
				} else if ( "admin".equals(role.getRoleCd()) ) {
					spUser.setSiteId(1);
				}
				
				break;
			}
		}
		
		if ( origUser != null ) {
			// Special handling for Store Portal Admin
			spUser.setUserAdmin(origUser.getUserAdmin());
		} else {
			spUser.setUserAdmin("N");
		}
		
		spUser.setUserLogin(ssoUser.getUsername());
		spUser.setUserName(ssoUser.getTblUserDetails().getFullName());
		
		spUser.setUserDob(ssoUser.getTblUserDetails().getDateOfBirth());
		spUser.setNric(ssoUser.getTblUserDetails().getNric());
		spUser.setUserPhone(ssoUser.getTblUserDetails().getPhone());
		spUser.setUserEmail(ssoUser.getTblUserDetails().getEmail());
		spUser.setTblStores(null);
		spUser.setTblDepts(null);
		spUser.setTblAreas(null);
		spUser.setFranchiseeExpiryDate(ssoUser.getTblFranchiseeDetails().getExpiryDate());
		spUser.setFranchiseeStartDate(ssoUser.getTblFranchiseeDetails().getStartDate());
		spUser.setRcbName(ssoUser.getTblUserDetails().getRcbName());
		spUser.setRcbNo(ssoUser.getTblUserDetails().getRcbNo());
		spUser.setGstNo(ssoUser.getTblUserDetails().getGstNo());
		spUser.setGender(ssoUser.getTblUserDetails().getGender());
		spUser.setUserMobile(ssoUser.getTblUserDetails().getPhone());
		spUser.setUserRemarks(ssoUser.getTblUserDetails().getRemarks());
		spUser.setUserDoj(ssoUser.getTblUserDetails().getDateOfJoin());
		spUser.setFranchiseeDuration(String.valueOf(ssoUser.getTblFranchiseeDetails().getDuration()));
		spUser.setFranchiseeScheme(ssoUser.getTblFranchiseeDetails().getScheme());
		
		return spUser;
	}
	
}
