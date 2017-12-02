package com.gongsibao.entity.product.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**   
 * @ClassName:  ServiceExtShowType   
 * @Description:TODO 前台显示1；后台定价显示2；结转显示4
 * @author: 韩伟
 * @date:   2017年12月2日 下午7:08:20   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public enum ServiceExtShowType implements IEnum{

	ServiceExtShowType_1(0, "前台显示"),
	ServiceExtShowType_2(1, "后台定价显示"),
	ServiceExtShowType_4(1, "结转显示");

	private int value;
	private String text;

	ServiceExtShowType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static ServiceExtShowType getItem(int value){
    	
        for(ServiceExtShowType item : values()){

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
