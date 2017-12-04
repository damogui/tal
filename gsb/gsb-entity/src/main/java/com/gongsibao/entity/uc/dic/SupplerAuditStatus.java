package com.gongsibao.entity.uc.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**   
 * @ClassName:  SupplerAuditStatus   
 * @Description:TODO 供应商审核状态
 * @author: 韩伟
 * @date:   2017年12月2日 下午3:50:41   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public enum SupplerAuditStatus implements IEnum{

	//1、初次申请 2 等待审核 3审核驳回 4 审核通过 5信息修改等待审核 6信息修改审核驳回

	SupplerAuditStatus_1(1, "初次申请"), 
	SupplerAuditStatus_2(2, "等待审核"), 
	SupplerAuditStatus_3(3, "审核驳回"), 
	SupplerAuditStatus_4(4, "审核通过"),
	SupplerAuditStatus_5(5, "信息修改等待审核"),
	SupplerAuditStatus_6(6, "信息修改审核驳回");

	private int value;
	private String text;

	SupplerAuditStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static SupplerAuditStatus getItem(int value){
    	
        for(SupplerAuditStatus item : values()){

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
