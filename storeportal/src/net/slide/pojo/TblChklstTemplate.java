package net.slide.pojo;

// Generated Jun 1, 2012 1:26:16 AM by Hibernate Tools 3.4.0.CR1

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
 * TblChklstTemplate generated by hbm2java
 */
@Entity
@Table(name = "tbl_chklst_template", schema = "public")
public class TblChklstTemplate implements java.io.Serializable {

	private int templateId;
	private String templateName;
	private String templateHeaders;
	private String responseType;
	private String updatedBy;
	private Date updatedDate;
	private Set<TblChklstTopic> tblChklstTopics = new HashSet<TblChklstTopic>(0);

	public TblChklstTemplate() {
	}

	public TblChklstTemplate(int templateId, String templateName,
			String templateHeaders, String responseType) {
		this.templateId = templateId;
		this.templateName = templateName;
		this.templateHeaders = templateHeaders;
		this.responseType = responseType;
	}

	public TblChklstTemplate(int templateId, String templateName,
			String templateHeaders, String responseType, String updatedBy,
			Date updatedDate, Set<TblChklstTopic> tblChklstTopics) {
		this.templateId = templateId;
		this.templateName = templateName;
		this.templateHeaders = templateHeaders;
		this.responseType = responseType;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.tblChklstTopics = tblChklstTopics;
	}

	@Id
	@Column(name = "template_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	@Column(name = "template_name", nullable = false, length = 50)
	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Column(name = "template_headers", length = 1024)
	public String getTemplateHeaders() {
		return this.templateHeaders;
	}

	public void setTemplateHeaders(String templateHeaders) {
		this.templateHeaders = templateHeaders;
	}

	@Column(name = "response_type", nullable = false, length = 1)
	public String getResponseType() {
		return this.responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tblChklstTemplate")
	public Set<TblChklstTopic> getTblChklstTopics() {
		return this.tblChklstTopics;
	}

	public void setTblChklstTopics(Set<TblChklstTopic> tblChklstTopics) {
		this.tblChklstTopics = tblChklstTopics;
	}

}
