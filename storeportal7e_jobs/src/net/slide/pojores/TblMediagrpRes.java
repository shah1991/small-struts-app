package net.slide.pojores;

import java.util.Date;


public class TblMediagrpRes implements java.io.Serializable {

	private int id;
	private String groupName;
	private int mediaCount;
	private Date updatedDate;
	private String updatedBy;

	public TblMediagrpRes() {
		super();
	}

	public TblMediagrpRes(int id, String groupName, int mediaCount,
			Date updatedDate, String updatedBy) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.mediaCount = mediaCount;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getMediaCount() {
		return mediaCount;
	}

	public void setMediaCount(int mediaCount) {
		this.mediaCount = mediaCount;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
