package com.gongsibao.igirl.dto.TradeMark;

//优先权信息
public class Step5 {
    //优先权信息
    private String priorityType; //优先权声明，priorityType1=无，priorityType2=在先优先权，priorityType3展会优先权

    //private String priorityType2;

    //private String priorityType3;


    public String getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(String priorityType) {
        this.priorityType = priorityType;
    }
}
