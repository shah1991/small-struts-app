package net.slide.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.slide.pojores.TblChklstDetailRes;

public class Response implements Serializable{
	private List<TblChklstDetailRes> items = new ArrayList<TblChklstDetailRes>();

	public List<TblChklstDetailRes> getItems() {
		return items;
	}

	public void setItems(List<TblChklstDetailRes> items) {
		this.items = items;
	}
	
}