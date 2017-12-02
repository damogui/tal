package com.gongsibao.entity.other.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**   
 * @ClassName:  LoginValid   
 * @Description:TODO 登录验证 1ukey, 2短信, 3都验证
 * @author: 韩伟
 * @date:   2017年12月1日 下午7:38:51   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public enum LoginValid implements IEnum{


	LoginValid_01(1, "ukey"), 
	LoginValid_02(2, "短信"), 
	LoginValid_03(3, "都验证");
	private int value;
	private String text;

	LoginValid(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static LoginValid getItem(int value){
    	
        for(LoginValid item : values()){

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
