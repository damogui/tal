package org.netsharp.organization.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @ClassName:OperationTypes   
 * @Description:操作类别
 * @author:hanwei
 * @date:2017年8月16日 上午11:01:14
 */  
public enum OperationTypes implements IEnum{
	operation(0, "操作"),
	view(1, "查看"),
	add(2, "新增"),
	update(3, "修改"),
	delete(4, "删除"),
	audit(5, "审核"),
	unaudit(6, "弃审"),
	print(7, "打印"),
	exportExcel(8, "导出"),
	importExcel(9, "导入"),
	attachment(10, "附件");
	
	OperationTypes(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static OperationTypes getItem(int value){
    	
        for(OperationTypes item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 
	private int value;
	private String text;
	
	@Override
	public Integer getValue() {
		return this.value;
	}

	@Override
	public String getText() {

		return this.text;
	}
}
