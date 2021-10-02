package net.slide.pojores;

public class TblUploadRes {
	private int id;
	private String attachPath;
	private String attachName;
        private String approvePath;
	private String approveName;
	private String storeCode;
	private int fileGroupId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
        
        //added by hafizd on January 21st 2014
        
        public String getApprovePath() {
		return approvePath;
	}
	public void setApprovePath(String approvePath) {
		this.approvePath = approvePath;
	}
	public String getApproveName() {
		return approveName;
	}
	public void setApproveName(String approveName) {
		this.approveName = approveName;
	}
        
        //end added
        
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public int getFileGroupId() {
		return fileGroupId;
	}
	public void setFileGroupId(int fileGroupId) {
		this.fileGroupId = fileGroupId;
	}
	
	
}
