package com.gongsibao.taurus.entity;

/**
 * Created by wk on 2018/1/31.
 */
public class HasCount implements IEntity {
    private static final long serialVersionUID = -8953399065180360646L;

    /* 推荐数量 */
    private int recommendCount;

    /* 公司业务项数量 */
    private int hasCount;

    public int getRecommendCount() {
        return recommendCount;
    }

    public void setRecommendCount(int recommendCount) {
        this.recommendCount = recommendCount;
    }

    public int getHasCount() {
        return hasCount;
    }

    public void setHasCount(int hasCount) {
        this.hasCount = hasCount;
    }
}
