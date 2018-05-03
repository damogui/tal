package com.gongsibao.taurus.entity;

/**
 * Created by wk on 2018/2/6.
 */
public class TmCls implements IEntity {
    private static final long serialVersionUID = -3809530995383316096L;

    private long id;
    private int intCls;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIntCls() {
        return intCls;
    }

    public void setIntCls(int intCls) {
        this.intCls = intCls;
    }
}
