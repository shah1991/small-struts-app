package net.slide.pojo;

// Generated Jun 6, 2012 12:49:10 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblBanner generated by hbm2java
 */
@Entity
@Table(name = "tbl_banner", schema = "public")
public class TblBanner implements java.io.Serializable {

	private int bannerId;
	private String bannerCd;
	private String bannerName;
	private String bannerStatus;
	private Date updatedDate;
	private String updatedBy;
	private Set<TblStore> tblStores = new HashSet<TblStore>(0);
	private Set<TblDept> tblDepts = new HashSet<TblDept>(0);
	private Set<TblArea> tblAreas = new HashSet<TblArea>(0);

	public TblBanner() {
	}

	public TblBanner(int bannerId, String bannerCd) {
		this.bannerId = bannerId;
		this.bannerCd = bannerCd;
	}

	public TblBanner(int bannerId, String bannerCd, String bannerName,
			String bannerStatus, Date updatedDate, String updatedBy,
			Set<TblStore> tblStores, Set<TblDept> tblDepts,
			Set<TblArea> tblAreas) {
		this.bannerId = bannerId;
		this.bannerCd = bannerCd;
		this.bannerName = bannerName;
		this.bannerStatus = bannerStatus;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.tblStores = tblStores;
		this.tblDepts = tblDepts;
		this.tblAreas = tblAreas;
	}

	@Id
	@Column(name = "banner_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getBannerId() {
		return this.bannerId;
	}

	public void setBannerId(int bannerId) {
		this.bannerId = bannerId;
	}

	@Column(name = "banner_cd", nullable = false, length = 25)
	public String getBannerCd() {
		return this.bannerCd;
	}

	public void setBannerCd(String bannerCd) {
		this.bannerCd = bannerCd;
	}

	@Column(name = "banner_name", length = 100)
	public String getBannerName() {
		return this.bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	@Column(name = "banner_status", length = 1)
	public String getBannerStatus() {
		return this.bannerStatus;
	}

	public void setBannerStatus(String bannerStatus) {
		this.bannerStatus = bannerStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", length = 29)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "updated_by", length = 20)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblBanner")
	public Set<TblStore> getTblStores() {
		return this.tblStores;
	}

	public void setTblStores(Set<TblStore> tblStores) {
		this.tblStores = tblStores;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblBanner")
	public Set<TblDept> getTblDepts() {
		return this.tblDepts;
	}

	public void setTblDepts(Set<TblDept> tblDepts) {
		this.tblDepts = tblDepts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblBanner")
	public Set<TblArea> getTblAreas() {
		return this.tblAreas;
	}

	public void setTblAreas(Set<TblArea> tblAreas) {
		this.tblAreas = tblAreas;
	}

}
