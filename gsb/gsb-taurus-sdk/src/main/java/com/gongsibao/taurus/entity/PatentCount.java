package com.gongsibao.taurus.entity;

/**
 * Created by wk on 2018/1/31.
 */
public class PatentCount implements IEntity {
    private static final long serialVersionUID = 7208114311048439877L;

    /* 已有专利数量 */
    private int hasCount;
    /* 潜在注册数量 */
    private int newCount;
    /* 潜在变更数量 */
    private String name;
    /* 潜在续费数量 */
    private int changeCount;
    /* 专利信息列表 */
    private int renewalCount;

    public int getHasCount() {
        return hasCount;
    }

    public void setHasCount(int hasCount) {
        this.hasCount = hasCount;
    }

    public int getNewCount() {
        return newCount;
    }

    public void setNewCount(int newCount) {
        this.newCount = newCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(int changeCount) {
        this.changeCount = changeCount;
    }

    public int getRenewalCount() {
        return renewalCount;
    }

    public void setRenewalCount(int renewalCount) {
        this.renewalCount = renewalCount;
    }
}
