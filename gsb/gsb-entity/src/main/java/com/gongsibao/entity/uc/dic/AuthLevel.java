package com.gongsibao.entity.uc.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**   
 * @ClassName:  AuthLevel   
 * @Description:TODO 企业认证等级，0：未认证，1：高级认证，2：中级认证，3：初级认证
 * @author: 韩伟
 * @date:   2017年12月2日 下午4:46:13   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public enum AuthLevel implements IEnum{

	AuthLevel(1, "未认证"),
	AuthLevel_02(2, "高级认证"), 
	AuthLevel_03(3, "中级认证"),
	AuthLevel_04(4, "初级认证");

	private int value;
	private String text;

	AuthLevel(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static AuthLevel getItem(int value){
    	
        for(AuthLevel item : values()){

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
