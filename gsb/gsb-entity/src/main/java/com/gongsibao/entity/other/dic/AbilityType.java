package com.gongsibao.entity.other.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum AbilityType implements IEnum{

	AbilityType_01(1071, "金牌"), 
	AbilityType_02(1072, "银牌"), 
	AbilityType_03(1073, "铜牌"), 
	AbilityType_04(1074, "普通");

	private int value;
	private String text;

	AbilityType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static AbilityType getItem(int value){
    	
        for(AbilityType item : values()){

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
