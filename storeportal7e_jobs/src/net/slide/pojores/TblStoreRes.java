package net.slide.pojores;

import java.util.ArrayList;

import net.slide.pojo.TblStore;

public class TblStoreRes {
	private int areaId;
	private String areaName;
	
	private ArrayList<TblStore> tblStoreList=new ArrayList<TblStore>();

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public ArrayList<TblStore> getTblStoreList() {
		return tblStoreList;
	}

	public void setTblStoreList(ArrayList<TblStore> tblStoreList) {
		this.tblStoreList = tblStoreList;
	}
	
	

}
