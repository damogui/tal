package com.gongsibao.ma.web.dto;

import java.util.ArrayList;
import java.util.List;

public class ComparisonModel {

	private String companyName;
	
	private List<ComparisonModelItem> items;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<ComparisonModelItem> getItems() {
		
		if(items == null){
			
			items = new ArrayList<ComparisonModelItem>();
		}
		return items;
	}

	public void setItems(List<ComparisonModelItem> items) {
		this.items = items;
	}
	
	
}
