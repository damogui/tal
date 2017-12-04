package com.gongsibao.entity.uc.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum UserLoginLogType implements IEnum{

	//0 前端用户, 1 后端用户 2 钉钉用户登录
	BdDictType_01(1, "BdDictType_01"), 
	BdDictType_02(2, "后端用户"),
	BdDictType_03(3, "钉钉用户登录");

	private int value;
	private String text;

	UserLoginLogType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static UserLoginLogType getItem(int value){
    	
        for(UserLoginLogType item : values()){

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
