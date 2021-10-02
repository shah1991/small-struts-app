package net.slide.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_link", schema = "public")
public class TblLink implements java.io.Serializable {

	private int linkId;
	private String title;
	private String url;
	private String iconPath;
	private String active;
	private Integer orderNo;
	private Date updatedDate;
	private String updatedBy;
	private String urlOption;
	private Integer urlId;

	public TblLink() {
	}

	public TblLink(int linkId) {
		this.linkId = linkId;
	}

	public TblLink(int linkId, String title, String url, String iconPath,
			String active, Integer orderNo, Date updatedDate, String updatedBy,
			String urlOption, Integer urlId) {
		this.linkId = linkId;
		this.title = title;
		this.url = url;
		this.iconPath = iconPath;
		this.active = active;
		this.orderNo = orderNo;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.urlOption = urlOption;
		this.urlId = urlId;
	}

	@Id
	@Column(name = "link_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getLinkId() {
		return this.linkId;
	}

	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}

	@Column(name = "title", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "url", length = 512)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "icon_path", length = 512)
	public String getIconPath() {
		return this.iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	@Column(name = "active", length = 1)
	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "order_no")
	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "updated_date", length = 21)
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

	@Column(name = "url_option", length = 1)
	public String getUrlOption() {
		return this.urlOption;
	}

	public void setUrlOption(String urlOption) {
		this.urlOption = urlOption;
	}

	@Column(name = "url_id")
	public Integer getUrlId() {
		return this.urlId;
	}

	public void setUrlId(Integer urlId) {
		this.urlId = urlId;
	}

}
