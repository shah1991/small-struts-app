package net.slide.pojo;

// default package
// Generated Sep 10, 2012 12:13:08 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblFileShare generated by hbm2java
 */
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblFileShare generated by hbm2java
 */
@Entity
@Table(name = "tbl_file_share", schema = "public")
public class TblFileShare implements java.io.Serializable {

	private int fileId;
	private TblFileGroup tblFileGroup;
	private String fileName;
	private String filePath;
	private String fileStatus;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	private Integer fileOwnerRole;
	private Integer fileOwnerSite;
	private String storeCode;
	private String store;
	private String franchise;
	private String fileFolder;
	private Integer hits;
	public TblFileShare() {
	}

	public TblFileShare(int fileId) {
		this.fileId = fileId;
	}

	public TblFileShare(int fileId, TblFileGroup tblFileGroup, String fileName,
			String filePath, String fileStatus, Date createdDate,
			String createdBy, Date updatedDate, String updatedBy,
			Integer fileOwnerRole, Integer fileOwnerSite, String storeCode,
			String store, String franchise, Integer hits) {
		this.fileId = fileId;
		this.tblFileGroup = tblFileGroup;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileStatus = fileStatus;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.fileOwnerRole = fileOwnerRole;
		this.fileOwnerSite = fileOwnerSite;
		this.storeCode = storeCode;
		this.store = store;
		this.franchise = franchise;
                this.hits = hits;
	}

	@Id
	@Column(name = "file_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getFileId() {
		return this.fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "file_group_id")
	public TblFileGroup getTblFileGroup() {
		return this.tblFileGroup;
	}

	public void setTblFileGroup(TblFileGroup tblFileGroup) {
		this.tblFileGroup = tblFileGroup;
	}

	@Column(name = "file_name", length = 100)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "file_path")
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "file_status", length = 1)
	public String getFileStatus() {
		return this.fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 29)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "created_by", length = 30)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", length = 29)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "updated_by", length = 30)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "file_owner_role")
	public Integer getFileOwnerRole() {
		return this.fileOwnerRole;
	}

	public void setFileOwnerRole(Integer fileOwnerRole) {
		this.fileOwnerRole = fileOwnerRole;
	}

	@Column(name = "file_owner_site")
	public Integer getFileOwnerSite() {
		return this.fileOwnerSite;
	}

	public void setFileOwnerSite(Integer fileOwnerSite) {
		this.fileOwnerSite = fileOwnerSite;
	}

	@Column(name = "store_code", length = 25)
	public String getStoreCode() {
		return this.storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@Column(name = "store", length = 1)
	public String getStore() {
		return this.store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	@Column(name = "franchise", length = 1)
	public String getFranchise() {
		return this.franchise;
	}

	public void setFranchise(String franchise) {
		this.franchise = franchise;
	}
	@Column(name = "file_folder", length = 100)
	public String getFileFolder() {
		return fileFolder;
	}

	public void setFileFolder(String fileFolder) {
		this.fileFolder = fileFolder;
	}
	@Column(name = "hits")
	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}
	
	

}
