package com.gongsibao.entity.other.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum SupplyStatus implements IEnum{

	SupplyStatus_01(1, "初次申请"), 
	SupplyStatus_02(2, "等待审核"), 
	SupplyStatus_03(3, "审核驳回"), 
	SupplyStatus_04(4, "审核通过"),
	SupplyStatus_05(5, "审核通过后修改-等待审核"),
	SupplyStatus_06(6, "信息修改审核驳回");
	private int value;
	private String text;

	SupplyStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static SupplyStatus getItem(int value){
    	
        for(SupplyStatus item : values()){

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
