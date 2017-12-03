package com.gongsibao.entity.uc.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum PriorityType implements IEnum{

	PriorityType_01(1091, "金牌"), 
	PriorityType_02(1092, "银牌"), 
	PriorityType_03(1073, "铜牌"), 
	PriorityType_04(1094, "普通");

	private int value;
	private String text;

	PriorityType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static PriorityType getItem(int value){
    	
        for(PriorityType item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 
	@Override
	public Integer getValue() {
		return this.value;
	}
	public String getText() {
		return this.text;
	}
}
