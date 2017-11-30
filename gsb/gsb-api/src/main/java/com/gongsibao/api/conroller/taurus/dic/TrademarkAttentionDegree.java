package com.gongsibao.api.conroller.taurus.dic;


public enum TrademarkAttentionDegree {

	ATTENTIONDEGREE_1(0,1, "不重视"), 
	ATTENTIONDEGREE_2(2,5, "开始重视"), 
	ATTENTIONDEGREE_3(6,10, "相对重视"), 
	ATTENTIONDEGREE_4(11,20, "重视"), 
	ATTENTIONDEGREE_5(21,30, "特别重视"), 
	ATTENTIONDEGREE_6(31,40, "极端重视"),
	ATTENTIONDEGREE_7(41,50, "核心壁垒"),
	ATTENTIONDEGREE_8(51,10000, "商标VIP大客户");
	private int startCount;
	private int endCount;
	private String text;

	TrademarkAttentionDegree(int startCount,int endCount, String text) {
		this.startCount = startCount;
		this.endCount = endCount;
		this.text = text;
	}

	public int getStartCount() {
		return startCount;
	}

	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}

	public int getEndCount() {
		return endCount;
	}

	public void setEndCount(int endCount) {
		this.endCount = endCount;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public static TrademarkAttentionDegree getItem(int count) {

		for (TrademarkAttentionDegree item : values()) {

			if (count<=item.getEndCount()) {
				return item;
			}
		}
		return null;
	}
	
//    attentionDegree = trademarkCount <= 1 ? "不重视" : trademarkCount <= 5 ? "开始重视" : trademarkCount <= 10 ? "相对重视" : trademarkCount <= 20 ? "重视" :
//        trademarkCount <= 30 ? "特别重视" : trademarkCount <= 40 ? "极端重视" : trademarkCount <= 50 ? "核心壁垒" : "商标VIP大客户";
}
