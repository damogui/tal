package com.gongsibao.taurus.entity;

/**
 * Created by wk on 2018/2/6.
 * 统计数量专用
 */
public class Count implements IEntity {

    private static final long serialVersionUID = -2587367406185722641L;

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
