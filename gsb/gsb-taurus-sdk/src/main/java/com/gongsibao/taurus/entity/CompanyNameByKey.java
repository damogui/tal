package com.gongsibao.taurus.entity;

/**
 * Created by wk on 2018/1/30.
 */
public class CompanyNameByKey implements IEntity {
    private static final long serialVersionUID = 535651551155596466L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
