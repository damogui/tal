package com.gongsibao.entity.uc.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**   
 * @ClassName:  SupplyType   
 * @Description:TODO 供应商类型
 * @author: 韩伟
 * @date:   2017年12月1日 下午7:37:38   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public enum SupplyType implements IEnum{
	
	SupplyType_01(0, "无"), 
	SupplyType_02(1, "cp"), 
	SupplyType_03(2, "sp");
	private int value;
	private String text;

	SupplyType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static SupplyType getItem(int value){
    	
        for(SupplyType item : values()){

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
