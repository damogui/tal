package com.gongsibao.taurus.entity;

/**
 * Created by wk on 2018/1/31.
 */
public class CompanyPotentialCount implements IEntity {

    private static final long serialVersionUID = -775278495178162373L;

    /* 公司名称 */
    private String name;
    /* 商标总数 */
    private int tmSum;
    /* 商标潜在变更数量 */
    private int tmChangeCount;
    /* 商标潜在续展数量 */
    private int tmRenewalCount;

    /* 专利总数 */
    private int patentSum;
    /* 专利潜在注册数量 */
    private int patentNewCount;
    /* 潜在变更数量 */
    private int patentChangeCount;
    /* 专利变更数 */
    private int patentRenewalCount;

    /* 著作权数量 */
    private int copyright;

    // TODO 以下机会未给出
    // 年报
    // 增值电信
    // 娱乐牌照
    // 高新企业
    // 税收筹划
    // 影视审批
    // 食品流通

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTmSum() {
        return tmSum;
    }

    public void setTmSum(int tmSum) {
        this.tmSum = tmSum;
    }

    public int getTmChangeCount() {
        return tmChangeCount;
    }

    public void setTmChangeCount(int tmChangeCount) {
        this.tmChangeCount = tmChangeCount;
    }

    public int getTmRenewalCount() {
        return tmRenewalCount;
    }

    public void setTmRenewalCount(int tmRenewalCount) {
        this.tmRenewalCount = tmRenewalCount;
    }

    public int getPatentSum() {
        return patentSum;
    }

    public void setPatentSum(int patentSum) {
        this.patentSum = patentSum;
    }

    public int getPatentNewCount() {
        return patentNewCount;
    }

    public void setPatentNewCount(int patentNewCount) {
        this.patentNewCount = patentNewCount;
    }

    public int getPatentChangeCount() {
        return patentChangeCount;
    }

    public void setPatentChangeCount(int patentChangeCount) {
        this.patentChangeCount = patentChangeCount;
    }

    public int getPatentRenewalCount() {
        return patentRenewalCount;
    }

    public void setPatentRenewalCount(int patentRenewalCount) {
        this.patentRenewalCount = patentRenewalCount;
    }

    public int getCopyright() {
        return copyright;
    }

    public void setCopyright(int copyright) {
        this.copyright = copyright;
    }
}
