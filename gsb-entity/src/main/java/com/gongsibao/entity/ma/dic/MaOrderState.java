package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum MaOrderState implements IEnum{

	UNPAID(1, "待支付"), 
	PAID_DEPOSIT(2, "已付订金"),
	DOWN_PAYMENT(3, "已付首款"), 
	FINAL_PAYMENT(4, "已付尾款"), 
	FINISHED(5, "已完成");

	private int value;
	private String text;

	MaOrderState(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static MaOrderState getItem(int value){
    	
        for(MaOrderState item : values()){

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
