package com.gongsibao.rest.dto.user;

import java.io.Serializable;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 我的 - 我的优惠券 - 优惠券使用情况汇总
 * @date 2018/4/17 11:51
 */
public class PreferentialUsageDTO implements Serializable {

    private int all;
    private int use;
    private int unUse;
    private int overdue;

    public int getAll() {
        return this.use + this.unUse + this.overdue;
    }

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = use;
    }

    public int getUnUse() {
        return unUse;
    }

    public void setUnUse(int unUse) {
        this.unUse = unUse;
    }

    public int getOverdue() {
        return overdue;
    }

    public void setOverdue(int overdue) {
        this.overdue = overdue;
    }
}
