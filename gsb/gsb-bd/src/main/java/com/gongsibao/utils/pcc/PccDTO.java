package com.gongsibao.utils.pcc;

import java.util.ArrayList;

public class PccDTO {
	
	private Integer id;
	private Integer parentId;
    private String name;
    private ArrayList<PccDTO> items = null;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<PccDTO> getItems() {
		return items;
	}
	public void setItems(ArrayList<PccDTO> items) {
		this.items = items;
	}
}
