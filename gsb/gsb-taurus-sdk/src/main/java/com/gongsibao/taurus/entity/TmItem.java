package com.gongsibao.taurus.entity;

/**
 * Created by wk on 2018/1/31.
 */
public class TmItem implements IEntity {

    private static final long serialVersionUID = -7108004379765964861L;

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
