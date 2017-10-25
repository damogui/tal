package org.netsharp.panda.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/*参照匹配模式*/
public enum IntelligentMode implements IEnum{
	
	EQUALS(0,"严格匹配"), LEFT(1,"左匹配"), RIGHT(2,"右匹配"), LIKE(3,"模糊匹配");
	private int value;
	private String text;

	IntelligentMode(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static IntelligentMode getItem(int value){
    	
        for(IntelligentMode item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 
	public Integer getValue() {
		return value;
	}
	public String getText() {
		return this.text;
	}
}
