package com.gongsibao.igirl.tm.dto.TradeMark;

import java.util.List;

//商品
public class Goods {
    //类别
    private String classes;

    //类似群
    private String group;

    //商品名称
    private List<String> nameList;

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }
}
