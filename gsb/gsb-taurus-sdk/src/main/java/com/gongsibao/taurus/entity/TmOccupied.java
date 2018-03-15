package com.gongsibao.taurus.entity;

/**
 * Created by wk on 2018/3/15.
 */
public class TmOccupied implements IEntity {

    private static final long serialVersionUID = -6055580496978227707L;

    /* 商标名称 */
    private String name;
    /* 被占用数量 */
    private int occupiedCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOccupiedCount() {
        return occupiedCount;
    }

    public void setOccupiedCount(int occupiedCount) {
        this.occupiedCount = occupiedCount;
    }
}
