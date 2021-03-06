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
 * TblChklstGroup generated by hbm2java
 */
@Entity
@Table(name = "tbl_chklst_group", schema = "public")
public class TblChklstGroup implements java.io.Serializable {

	private int chklstGroupId;
	private String chklstGroupName;
	private String updatedBy;
	private Date updatedDate;
	private String status;
	private Set<TblChklst> tblChklsts = new HashSet<TblChklst>(0);
	private Set<TblDept> tblDepts = new HashSet<TblDept>(0);
	private Set<TblChklstTopic> tblChklstTopics = new HashSet<TblChklstTopic>(0);

	public TblChklstGroup() {
	}

	public TblChklstGroup(int chklstGroupId) {
		this.chklstGroupId = chklstGroupId;
	}

	public TblChklstGroup(int chklstGroupId, String chklstGroupName,
			String updatedBy, Date updatedDate, String status,
			Set<TblChklst> tblChklsts, Set<TblDept> tblDepts,
			Set<TblChklstTopic> tblChklstTopics) {
		this.chklstGroupId = chklstGroupId;
		this.chklstGroupName = chklstGroupName;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
		this.tblChklsts = tblChklsts;
		this.tblDepts = tblDepts;
		this.tblChklstTopics = tblChklstTopics;
	}

	@Id
	@Column(name = "chklst_group_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getChklstGroupId() {
		return this.chklstGroupId;
	}

	public void setChklstGroupId(int chklstGroupId) {
		this.chklstGroupId = chklstGroupId;
	}

	@Column(name = "chklst_group_name", length = 100)
	public String getChklstGroupName() {
		return this.chklstGroupName;
	}

	public void setChklstGroupName(String chklstGroupName) {
		this.chklstGroupName = chklstGroupName;
	}

	@Column(name = "updated_by", length = 30)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", length = 29)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblChklstGroup")
	public Set<TblChklst> getTblChklsts() {
		return this.tblChklsts;
	}

	public void setTblChklsts(Set<TblChklst> tblChklsts) {
		this.tblChklsts = tblChklsts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblChklstGroup")
	public Set<TblDept> getTblDepts() {
		return this.tblDepts;
	}

	public void setTblDepts(Set<TblDept> tblDepts) {
		this.tblDepts = tblDepts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblChklstGroup")
	public Set<TblChklstTopic> getTblChklstTopics() {
		return this.tblChklstTopics;
	}

	public void setTblChklstTopics(Set<TblChklstTopic> tblChklstTopics) {
		this.tblChklstTopics = tblChklstTopics;
	}

}
