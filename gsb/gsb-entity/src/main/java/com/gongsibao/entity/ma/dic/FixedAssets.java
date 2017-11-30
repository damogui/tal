package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @author hw
 * 固定资产
 */
public enum FixedAssets implements IEnum{

	PLATENUMBER(1, "车牌"), 
	CAR(2, "车辆"), 
	OTHER(3, "其它");
	private int value;
	private String text;

	FixedAssets(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static FixedAssets getItem(int value){
    	
        for(FixedAssets item : values()){

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
