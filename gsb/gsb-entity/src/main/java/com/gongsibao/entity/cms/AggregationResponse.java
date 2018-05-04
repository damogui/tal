package com.gongsibao.entity.cms;

import java.io.Serializable;
import java.util.List;


public class AggregationResponse implements Serializable {

    private static final long serialVersionUID = -4036824722867393514L;

    // 类型 0无 1两级聚合 2一级聚合
    private int type;

    // 一级聚合标题
    private String firstAggregationName;

    // 二级聚合标题
    private String secondAggregationName;

    private List<ProductAggregation> cmsProductAggregationList;

    private List<ProductAggregationMap> cmsProductAggregationMapList;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFirstAggregationName() {
        return firstAggregationName;
    }

    public void setFirstAggregationName(String firstAggregationName) {
        this.firstAggregationName = firstAggregationName;
    }

    public String getSecondAggregationName() {
        return secondAggregationName;
    }

    public void setSecondAggregationName(String secondAggregationName) {
        this.secondAggregationName = secondAggregationName;
    }

    public List<ProductAggregation> getProductAggregationList() {
        return cmsProductAggregationList;
    }

    public void setProductAggregationList(List<ProductAggregation> cmsProductAggregationList) {
        this.cmsProductAggregationList = cmsProductAggregationList;
    }

    public List<ProductAggregationMap> getProductAggregationMapList() {
        return cmsProductAggregationMapList;
    }

    public void setProductAggregationMapList(List<ProductAggregationMap> cmsProductAggregationMapList) {
        this.cmsProductAggregationMapList = cmsProductAggregationMapList;
    }
}
