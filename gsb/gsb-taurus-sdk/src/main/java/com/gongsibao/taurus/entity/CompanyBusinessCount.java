package com.gongsibao.taurus.entity;

/**
 * Created by wk on 2018/1/31.
 */
public class CompanyBusinessCount implements IEntity {
    private static final long serialVersionUID = 6774348250882882240L;

    /* 公司名称 */
    private String name;

    /* 著作权数量 */
    private int copyright;

    /* 专利数量 */
    private int patent;

    /* 商标数量 */
    private int tm;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCopyright() {
        return copyright;
    }

    public void setCopyright(int copyright) {
        this.copyright = copyright;
    }

    public int getPatent() {
        return patent;
    }

    public void setPatent(int patent) {
        this.patent = patent;
    }

    public int getTm() {
        return tm;
    }

    public void setTm(int tm) {
        this.tm = tm;
    }
}
