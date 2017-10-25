package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @author hw
 * 需求审核状态
 */
public enum DemandState  implements IEnum{

	UNSUBMIT(1, "未提交"), 
	UNAUDIT(2, "待审核"), 
	VERIFIED(3, "审核通过"), 
	AUDIT_FAILED(4, "未通过");
	private int value;
	private String text;

	DemandState(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static DemandState getItem(int value){
    	
        for(DemandState item : values()){

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
