package com.gongsibao.entity.uc.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum BdDictType implements IEnum{


	BdDictType_01(1, "服务产品分类"), 
	BdDictType_02(2, "服务地区");

	private int value;
	private String text;

	BdDictType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static BdDictType getItem(int value){
    	
        for(BdDictType item : values()){

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
