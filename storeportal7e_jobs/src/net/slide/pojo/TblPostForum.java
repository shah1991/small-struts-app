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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TblPostForum generated by hbm2java
 */
@Entity
@Table(name = "tbl_post_forum", schema = "public")
public class TblPostForum implements java.io.Serializable {

	private int id;
	private String forumName;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String status;
	private Set<TblPost> tblPosts = new HashSet<TblPost>(0);
	private Set<TblPostTopic> tblPostTopics = new HashSet<TblPostTopic>(0);
	private Set<TblDept> tblDepts = new HashSet<TblDept>(0);

	public TblPostForum() {
	}

	public TblPostForum(int id) {
		this.id = id;
	}

	public TblPostForum(int id, String forumName, String createdBy,
			Date createdDate, String updatedBy, Date updatedDate,
			String status, Set<TblPost> tblPosts,
			Set<TblPostTopic> tblPostTopics, Set<TblDept> tblDepts) {
		this.id = id;
		this.forumName = forumName;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
		this.tblPosts = tblPosts;
		this.tblPostTopics = tblPostTopics;
		this.tblDepts = tblDepts;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "forum_name", length = 100)
	public String getForumName() {
		return this.forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	@Column(name = "created_by", length = 30)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 29)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblPostForum")
	public Set<TblPost> getTblPosts() {
		return this.tblPosts;
	}

	public void setTblPosts(Set<TblPost> tblPosts) {
		this.tblPosts = tblPosts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblPostForum")
	public Set<TblPostTopic> getTblPostTopics() {
		return this.tblPostTopics;
	}

	public void setTblPostTopics(Set<TblPostTopic> tblPostTopics) {
		this.tblPostTopics = tblPostTopics;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblPostForum")
	public Set<TblDept> getTblDepts() {
		return this.tblDepts;
	}

	public void setTblDepts(Set<TblDept> tblDepts) {
		this.tblDepts = tblDepts;
	}

}
