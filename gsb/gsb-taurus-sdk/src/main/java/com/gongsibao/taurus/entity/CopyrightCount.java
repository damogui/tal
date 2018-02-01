package com.gongsibao.taurus.entity;

import java.util.List;

/**
 * Created by wk on 2018/1/31.
 */
public class CopyrightCount implements IEntity {
    private static final long serialVersionUID = -6795372611093706416L;

    /* 公司名称 */
    private String name;
    /* 著作权总数 */
    private int sumCount;
    /* 作品著作权数量 */
    private int copyrightCount;
    /* 软件著作权数量 */
    private int softwareCopyrightCount;
    /* 业务机会 */
    private int businessChance;
    /* 经营范围 */
    private List<String> businessScope;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSumCount() {
        return sumCount;
    }

    public void setSumCount(int sumCount) {
        this.sumCount = sumCount;
    }

    public int getCopyrightCount() {
        return copyrightCount;
    }

    public void setCopyrightCount(int copyrightCount) {
        this.copyrightCount = copyrightCount;
    }

    public int getSoftwareCopyrightCount() {
        return softwareCopyrightCount;
    }

    public void setSoftwareCopyrightCount(int softwareCopyrightCount) {
        this.softwareCopyrightCount = softwareCopyrightCount;
    }

    public int getBusinessChance() {
        return businessChance;
    }

    public void setBusinessChance(int businessChance) {
        this.businessChance = businessChance;
    }

    public List<String> getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(List<String> businessScope) {
        this.businessScope = businessScope;
    }
}
