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
    private List<TmCls> recommendClassList;

    /* 被占用类别 */
    private List<TmCls> occupiedClassList;

    /* 已注册类别 */
    private List<TmCls> hasClassList;

    private List<TmCls> clsList;

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

    public List<TmCls> getRecommendClassList() {
        return recommendClassList;
    }

    public void setRecommendClassList(List<TmCls> recommendClassList) {
        this.recommendClassList = recommendClassList;
    }

    public List<TmCls> getOccupiedClassList() {
        return occupiedClassList;
    }

    public void setOccupiedClassList(List<TmCls> occupiedClassList) {
        this.occupiedClassList = occupiedClassList;
    }

    public List<TmCls> getHasClassList() {
        return hasClassList;
    }

    public void setHasClassList(List<TmCls> hasClassList) {
        this.hasClassList = hasClassList;
    }

    public List<TmCls> getClsList() {
        return clsList;
    }

    public void setClsList(List<TmCls> clsList) {
        this.clsList = clsList;
    }
}
