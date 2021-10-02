package net.slide.bean;

public class SearchTO {
	
	private String storeCode;
	private String storeName;
	private String storeLocation;
	
	//Device Search
	private Long storeId;
	private String deviceName;
	private Long groupId;
	
	//Group Search
	private String groupName;
	private GallaryType groupType;
	private Long deviceId;
	
	
	public String getStoreCode() {
		if(storeCode != null) {
			storeCode = storeCode.trim();
		}
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getStoreName() {
		if(storeName != null) {
			storeName = storeName.trim();
		}
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreLocation() {
		if(storeLocation != null) {
			storeLocation = storeLocation.trim();
		}
		return storeLocation;
	}
	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
	public Long getStoreId() {
		if(storeId == null) {
			storeId = 0L;
		}
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getDeviceName() {
		if(deviceName != null) {
			deviceName = deviceName.trim();
		}
		return deviceName;
	}
	
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public GallaryType getGroupType() {
		return groupType;
	}
	public void setGroupType(GallaryType groupType) {
		this.groupType = groupType;
	}
	public Long getDeviceId() {
		if(deviceId == null) {
			deviceId = 0L;
		}
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	
	public Long getGroupId() {
		if(groupId == null) {
			groupId = 0L;
		}
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
}
