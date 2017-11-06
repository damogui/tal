package com.gongsibao.ma.web.dto;

public class ComparisonModelItem {


	private String title;
	
	private String acquisitionDemandText;
	
	private String sellingDemandText;
	
	private int matchingRate = 0;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAcquisitionDemandText() {
		return acquisitionDemandText;
	}

	public void setAcquisitionDemandText(String acquisitionDemandText) {
		this.acquisitionDemandText = acquisitionDemandText;
	}

	public String getSellingDemandText() {
		return sellingDemandText;
	}

	public void setSellingDemandText(String sellingDemandText) {
		this.sellingDemandText = sellingDemandText;
	}

	public int getMatchingRate() {
		return matchingRate;
	}

	public void setMatchingRate(int matchingRate) {
		this.matchingRate = matchingRate;
	}
	
}
