package com.gongsibao.entity.uc.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**   
 * @ClassName:  DingtalkFileType   
 * @Description:TODO 0:企业档案、1：知产档案
 * @author: 韩伟
 * @date:   2017年12月2日 下午4:54:31   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public enum DingtalkFileType implements IEnum{


	DingtalkFileType_01(0, "企业档案"), 
	DingtalkFileType_02(1, "知产档案");

	private int value;
	private String text;

	DingtalkFileType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static DingtalkFileType getItem(int value){
    	
        for(DingtalkFileType item : values()){

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
