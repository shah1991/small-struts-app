package net.slide.pojo;


// Generated Aug 24, 2012 7:03:57 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblUser generated by hbm2java
 */
@Entity
@Table(name = "tbl_user", schema = "public")
public class TblUser implements java.io.Serializable {

	private int userId;
	private TblRole tblRole;
	private String userLogin;
	private String userName;
	private String userStatus;
	private String userAdmin;
	private String userPwd;
	private int siteId;
	private String updatedBy;
	private Date updatedOn;
	private Date userDob;
	private String nric;
	private String userPhone;
	private String userEmail;
	private Set<TblStore> tblStores = new HashSet<TblStore>(0);
	private Set<TblDept> tblDepts = new HashSet<TblDept>(0);
	private Set<TblArea> tblAreas = new HashSet<TblArea>(0);
	private Date franchiseeExpiryDate;
	private Date franchiseeStartDate;
	private String rcbName;
	private String rcbNo;
	private String gstNo;
	private String gender;
	private String userMobile;
	private String userRemarks;
	private Date userDoj;
	private String franchiseeDuration;
	private String franchiseeScheme;
        
        private Date expiryDate;
        private Date loginDate;        
        private Integer loginError;        
        private String passwordForce;              
	
	public TblUser() {
	}

	public TblUser(int userId, TblRole tblRole, int siteId) {
		this.userId = userId;
		this.tblRole = tblRole;
		this.siteId = siteId;
	}

	public TblUser(int userId, TblRole tblRole, String userLogin,
			String userName, String userStatus, String userAdmin,
			String userPwd, int siteId, String updatedBy, Date updatedOn,
			Date userDob, String nric, String userPhone, String userEmail, Date franchiseeExpiryDate,
			Date franchiseeStartDate, String rcbName, String rcbNo, String gstNo, String gender, Date userDoj, 
			String userMobile, String userRemarks, String franchiseeDuration, String franchiseeScheme, Set<TblStore> tblStores,
			Set<TblDept> tblDepts, Set<TblArea> tblAreas, Date expiryDate, Date loginDate,
                        Integer loginError, String passwordForce     ) {
		this.userId = userId;
		this.tblRole = tblRole;
		this.userLogin = userLogin;
		this.userName = userName;
		this.userStatus = userStatus;
		this.userAdmin = userAdmin;
		this.userPwd = userPwd;
		this.siteId = siteId;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.userDob = userDob;
		this.nric = nric;
		this.tblStores = tblStores;
		this.tblDepts = tblDepts;
		this.tblAreas = tblAreas;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.franchiseeExpiryDate = franchiseeExpiryDate;
		this.franchiseeStartDate = franchiseeStartDate;
		this.rcbName = rcbName;
		this.rcbNo = rcbNo;
		this.gstNo = gstNo;
		this.gender = gender;
		this.userMobile = userMobile;
		this.userRemarks = userRemarks;
		this.userDoj = userDoj;
		this.franchiseeDuration = franchiseeDuration;
		this.franchiseeScheme = franchiseeScheme;
                this.expiryDate = expiryDate;
                this.loginDate = loginDate;
                this.loginError = loginError;
                this.passwordForce = passwordForce;
                
	}

	
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	public TblRole getTblRole() {
		return this.tblRole;
	}

	public void setTblRole(TblRole tblRole) {
		this.tblRole = tblRole;
	}

	@Column(name = "user_login", length = 15)
	public String getUserLogin() {
		return this.userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	@Column(name = "user_name", length = 150)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_status", length = 1)
	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Column(name = "user_admin", length = 1)
	public String getUserAdmin() {
		return this.userAdmin;
	}

	public void setUserAdmin(String userAdmin) {
		this.userAdmin = userAdmin;
	}

	@Column(name = "user_pwd", length = 50)
	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Column(name = "site_id", nullable = false)
	public int getSiteId() {
		return this.siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	@Column(name = "updated_by", length = 25)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on", length = 29)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "user_dob", length = 29)
	public Date getUserDob() {
		return this.userDob;
	}

	public void setUserDob(Date userDob) {
		this.userDob = userDob;
	}

	@Column(name = "nric")
	public String getNric() {
		return this.nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}
	
	@Column(name = "user_phone", length = 15)
	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Column(name = "user_email", length = 50)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "franchisee_expiry_date", length = 29)
	public Date getFranchiseeExpiryDate() {
		return franchiseeExpiryDate;
	}

	public void setFranchiseeExpiryDate(Date franchiseeExpiryDate) {
		this.franchiseeExpiryDate = franchiseeExpiryDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "franchisee_start_date", length = 29)
	public Date getFranchiseeStartDate() {
		return franchiseeStartDate;
	}

	public void setFranchiseeStartDate(Date franchiseeStartDate) {
		this.franchiseeStartDate = franchiseeStartDate;
	}

	@Column(name = "rcb_name", length = 250)
	public String getRcbName() {
		return rcbName;
	}

	public void setRcbName(String rcbName) {
		this.rcbName = rcbName;
	}

	@Column(name = "rcb_no", length = 50)
	public String getRcbNo() {
		return rcbNo;
	}

	public void setRcbNo(String rcbNo) {
		this.rcbNo = rcbNo;
	}

	@Column(name = "gst_no", length = 50)
	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	@Column(name = "gender", length = 15)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "user_mobile", length = 50)
	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	
	@Column(name = "user_remarks", length = 2500)	
	public String getUserRemarks() {
		return userRemarks;
	}

	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblUser")
	public Set<TblStore> getTblStores() {
		return this.tblStores;
	}

	public void setTblStores(Set<TblStore> tblStores) {
		this.tblStores = tblStores;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblUser")
	public Set<TblDept> getTblDepts() {
		return this.tblDepts;
	}

	public void setTblDepts(Set<TblDept> tblDepts) {
		this.tblDepts = tblDepts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblUser")
	public Set<TblArea> getTblAreas() {
		return this.tblAreas;
	}

	public void setTblAreas(Set<TblArea> tblAreas) {
		this.tblAreas = tblAreas;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "user_doj", length = 29)
	public Date getUserDoj() {
		return userDoj;
	}

	public void setUserDoj(Date userDoj) {
		this.userDoj = userDoj;
	}

	@Column(name = "franchisee_duration", length = 3)	
	public String getFranchiseeDuration() {
		return franchiseeDuration;
	}

	public void setFranchiseeDuration(String franchiseeDuration) {
		this.franchiseeDuration = franchiseeDuration;
	}

	@Column(name = "franchisee_scheme", length = 2500)
	public String getFranchiseeScheme() {
		return franchiseeScheme;
	}

	public void setFranchiseeScheme(String franchiseeScheme) {
		this.franchiseeScheme = franchiseeScheme;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "expiry_date")
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}        
        
	@Temporal(TemporalType.DATE)
	@Column(name = "login_date")
	public Date getLoginDate() {
		return this.loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}                
        
	@Column(name = "login_error")
	public Integer getLoginError() {
		return this.loginError;
	}

	public void setLoginError(Integer loginError) {
		this.loginError = loginError;
	}
        
	@Column(name = "password_force", length = 1)	
	public String getPasswordForce() {
		return passwordForce;
	}

	public void setPasswordForce(String passwordForce) {
		this.passwordForce = passwordForce;
	}
}
