package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @author hw
 * 无形资产
 */
public enum IntangibleAssets implements IEnum{
	
	BRAND(1, "商标"), 
	PATENT(2, "专利"), 
	DOMAIN(3, "域名"), 
	COPYRIGHT(4,"著作权"), 
	OTHER(5, "其它");
	private int value;
	private String text;

	IntangibleAssets(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static IntangibleAssets getItem(int value){
    	
        for(IntangibleAssets item : values()){

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
