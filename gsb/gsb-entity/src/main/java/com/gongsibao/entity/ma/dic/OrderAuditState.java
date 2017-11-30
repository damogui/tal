package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderAuditState implements IEnum{

	UNAUDIT(1, "待审核"), 
	VERIFIED(2, "审核通过"), 
	AUDIT_FAILED(3, "未通过");
	
	private int value;
	private String text;

	OrderAuditState(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static OrderAuditState getItem(int value){
    	
        for(OrderAuditState item : values()){

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
