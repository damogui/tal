package com.gongsibao.entity.product.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**   
 * @ClassName:  IncomeType   
 * @Description:TODO 分成方式: 0 比例分成, 1 定额分成
 * @author: 韩伟
 * @date:   2017年12月2日 下午6:32:36   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public enum IncomeType implements IEnum{

	IncomeType_0(0, "比例分成"),
	IncomeType_1(1, "定额分成");

	private int value;
	private String text;

	IncomeType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static IncomeType getItem(int value){
    	
        for(IncomeType item : values()){

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
