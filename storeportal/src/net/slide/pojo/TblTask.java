package net.slide.pojo;

// Generated Jun 18, 2012 7:42:08 PM by Hibernate Tools 3.4.0.CR1

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
 * TblTask generated by hbm2java
 */
@Entity
@Table(name = "tbl_task", schema = "public")
public class TblTask implements java.io.Serializable {

	private int taskId;
	private TblTaskPortfolio tblTaskPortfolio;
	private String taskCd;
	private String taskType;
	private String taskSubject;
	private String taskContents;
	private String taskAttachPath;
	private String taskAttachFilename;
        private String taskApproveFilename;
	private String taskStatus;
	private int taskOwnerRole;
	private int taskOwnerSite;
	private Date taskStartDate;
	private Date taskEndDate;
	private String updatedBy;
	private Date updatedDate;
	private Set<TblTaskUpload> tblTaskUploads = new HashSet<TblTaskUpload>(0);
	private Set<TblTaskStore> tblTaskStores = new HashSet<TblTaskStore>(0);
	private Set<TblTaskArea> tblTaskAreas = new HashSet<TblTaskArea>(0);

	public TblTask() {
	}

	public TblTask(int taskId, TblTaskPortfolio tblTaskPortfolio,
			String taskCd, String taskType, int taskOwnerRole, int taskOwnerSite) {
		this.taskId = taskId;
		this.tblTaskPortfolio = tblTaskPortfolio;
		this.taskCd = taskCd;
		this.taskType = taskType;
		this.taskOwnerRole = taskOwnerRole;
		this.taskOwnerSite = taskOwnerSite;
	}

	public TblTask(int taskId, TblTaskPortfolio tblTaskPortfolio,
			String taskCd, String taskType, String taskSubject,
			String taskContents, String taskAttachPath,
			String taskAttachFilename, String taskStatus, int taskOwnerRole,
			int taskOwnerSite, Date taskStartDate, Date taskEndDate,
			String updatedBy, Date updatedDate,
			Set<TblTaskUpload> tblTaskUploads, Set<TblTaskStore> tblTaskStores,
			Set<TblTaskArea> tblTaskAreas,String taskApproveFilename) {
		this.taskId = taskId;
		this.tblTaskPortfolio = tblTaskPortfolio;
		this.taskCd = taskCd;
		this.taskType = taskType;
		this.taskSubject = taskSubject;
		this.taskContents = taskContents;
		this.taskAttachPath = taskAttachPath;
		this.taskAttachFilename = taskAttachFilename;
                this.taskApproveFilename = taskApproveFilename;
		this.taskStatus = taskStatus;
		this.taskOwnerRole = taskOwnerRole;
		this.taskOwnerSite = taskOwnerSite;
		this.taskStartDate = taskStartDate;
		this.taskEndDate = taskEndDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.tblTaskUploads = tblTaskUploads;
		this.tblTaskStores = tblTaskStores;
		this.tblTaskAreas = tblTaskAreas;
	}

	@Id
	@Column(name = "task_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "portfolio_id", nullable = false)
	public TblTaskPortfolio getTblTaskPortfolio() {
		return this.tblTaskPortfolio;
	}

	public void setTblTaskPortfolio(TblTaskPortfolio tblTaskPortfolio) {
		this.tblTaskPortfolio = tblTaskPortfolio;
	}

	@Column(name = "task_cd", nullable = false, length = 25)
	public String getTaskCd() {
		return this.taskCd;
	}

	public void setTaskCd(String taskCd) {
		this.taskCd = taskCd;
	}

	@Column(name = "task_type", nullable = false, length = 11)
	public String getTaskType() {
		return this.taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	@Column(name = "task_subject", length = 100)
	public String getTaskSubject() {
		return this.taskSubject;
	}

	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}

	@Column(name = "task_contents", length = 1024)
	public String getTaskContents() {
		return this.taskContents;
	}

	public void setTaskContents(String taskContents) {
		this.taskContents = taskContents;
	}

	@Column(name = "task_attach_path", length = 1024)
	public String getTaskAttachPath() {
		return this.taskAttachPath;
	}

	public void setTaskAttachPath(String taskAttachPath) {
		this.taskAttachPath = taskAttachPath;
	}

	@Column(name = "task_attach_filename", length = 512)
	public String getTaskAttachFilename() {
		return this.taskAttachFilename;
	}

	public void setTaskAttachFilename(String taskAttachFilename) {
		this.taskAttachFilename = taskAttachFilename;
	}
        
        //added
        
        @Column(name = "task_approve_filename", length = 512)
	public String getTaskApproveFilename() {
		return this.taskApproveFilename;
	}

	public void setTaskApproveFilename(String taskApproveFilename) {
		this.taskApproveFilename = taskApproveFilename;
	}
        
        //end added

	@Column(name = "task_status", length = 1)
	public String getTaskStatus() {
		return this.taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Column(name = "task_owner_role", nullable = false)
	public int getTaskOwnerRole() {
		return this.taskOwnerRole;
	}

	public void setTaskOwnerRole(int taskOwnerRole) {
		this.taskOwnerRole = taskOwnerRole;
	}

	@Column(name = "task_owner_site", nullable = false)
	public int getTaskOwnerSite() {
		return this.taskOwnerSite;
	}

	public void setTaskOwnerSite(int taskOwnerSite) {
		this.taskOwnerSite = taskOwnerSite;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "task_start_date", length = 29)
	public Date getTaskStartDate() {
		return this.taskStartDate;
	}

	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "task_end_date", length = 29)
	public Date getTaskEndDate() {
		return this.taskEndDate;
	}

	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblTask")
	public Set<TblTaskUpload> getTblTaskUploads() {
		return this.tblTaskUploads;
	}

	public void setTblTaskUploads(Set<TblTaskUpload> tblTaskUploads) {
		this.tblTaskUploads = tblTaskUploads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblTask")
	public Set<TblTaskStore> getTblTaskStores() {
		return this.tblTaskStores;
	}

	public void setTblTaskStores(Set<TblTaskStore> tblTaskStores) {
		this.tblTaskStores = tblTaskStores;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblTask")
	public Set<TblTaskArea> getTblTaskAreas() {
		return this.tblTaskAreas;
	}

	public void setTblTaskAreas(Set<TblTaskArea> tblTaskAreas) {
		this.tblTaskAreas = tblTaskAreas;
	}

}
