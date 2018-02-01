package com.gongsibao.taurus.entity;

/**
 * Created by wk on 2018/1/31.
 */
public class ItemCount implements IEntity {
    private static final long serialVersionUID = -8953399065180360646L;

    /* 公司名称 */
    private String name;

    /* 推荐数量 */
    private int recommendCount;

    /* 公司业务项数量 */
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRecommendCount() {
        return recommendCount;
    }

    public void setRecommendCount(int recommendCount) {
        this.recommendCount = recommendCount;
    }
}
