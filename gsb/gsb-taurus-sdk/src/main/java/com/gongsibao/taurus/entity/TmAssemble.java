package com.gongsibao.taurus.entity;

import java.util.List;

/**
 * Created by wk on 2018/1/31.
 * 商标聚合实体
 */
public class TmAssemble implements IEntity{

    private static final long serialVersionUID = 8616902577846491138L;

    /* 商标名称 */
    private String name;

    /* 商标图片 */
    private String path;

    /* 推荐商标类别 */
    private List<Integer> recommendList;

    /* 被占用类别 */
    private List<Integer> occupiedList;

    /* 已注册类别 */
    private List<Integer> regList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Integer> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(List<Integer> recommendList) {
        this.recommendList = recommendList;
    }

    public List<Integer> getOccupiedList() {
        return occupiedList;
    }

    public void setOccupiedList(List<Integer> occupiedList) {
        this.occupiedList = occupiedList;
    }

    public List<Integer> getRegList() {
        return regList;
    }

    public void setRegList(List<Integer> regList) {
        this.regList = regList;
    }
}
