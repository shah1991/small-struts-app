package net.slide.pojo;
// default package
// Generated Sep 7, 2012 10:23:36 AM by Hibernate Tools 3.4.0.CR1

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
 * TblFileGroup generated by hbm2java
 */
@Entity
@Table(name = "tbl_file_group", schema = "public")
public class TblFileGroup implements java.io.Serializable {

	private int groupId;
	private String groupName;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String status;
	private Set<TblFileShare> tblFileShares = new HashSet<TblFileShare>(0);

	public TblFileGroup() {
	}

	public TblFileGroup(int groupId) {
		this.groupId = groupId;
	}

	public TblFileGroup(int groupId, String groupName, String createdBy,
			Date createdDate, String updatedBy, Date updatedDate,
			String status, Set<TblFileShare> tblFileShares) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
		this.tblFileShares = tblFileShares;
	}

	@Id
	@Column(name = "group_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Column(name = "group_name", length = 100)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "created_by", length = 30)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "created_date", length = 15)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_by", length = 30)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "updated_date", length = 15)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "status", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblFileGroup")
	public Set<TblFileShare> getTblFileShares() {
		return this.tblFileShares;
	}

	public void setTblFileShares(Set<TblFileShare> tblFileShares) {
		this.tblFileShares = tblFileShares;
	}

}
