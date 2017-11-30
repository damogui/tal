package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum DemandSoldOutState implements IEnum{

	PUTAWAY(1, "上架"), 
	SOLDOUT(2, "下架");
	private int value;
	private String text;

	DemandSoldOutState(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static DemandSoldOutState getItem(int value){
    	
        for(DemandSoldOutState item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 
	public String getText() {
		return this.text;
	}

	@Override
	public Integer getValue() {

		return this.value;
	}
	
}
