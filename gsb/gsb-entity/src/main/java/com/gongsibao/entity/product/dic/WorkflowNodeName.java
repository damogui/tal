package com.gongsibao.entity.product.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**   
 * @ClassName:  WorkflowNodeName   
 * @Description:TODO 名称编号（1：核名、2：网提、3：下载、打印、4：预约、5：交件、6：取证、7：备案刻章、8：结束(已结算，type=2064)））
 * @author: 韩伟
 * @date:   2017年12月2日 下午7:18:02   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public enum WorkflowNodeName implements IEnum{

	WorkflowNodeName_1(1, "核名"),
	WorkflowNodeName_2(2, "网提"),
	WorkflowNodeName_3(3, "下载、打印"),
	WorkflowNodeName_4(4, "预约"),
	WorkflowNodeName_5(5, "交件"),
	WorkflowNodeName_6(6, "取证"),
	WorkflowNodeName_7(7, "备案刻章"),
	WorkflowNodeName_8(8, "结束");

	private int value;
	private String text;

	WorkflowNodeName(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static WorkflowNodeName getItem(int value){
    	
        for(WorkflowNodeName item : values()){

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
