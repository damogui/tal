package com.gongsibao.taurus.entity;



/**
 * Created by cxq on 2017/11/1.
 */
// 经营异常
public class CompanyAbnormal implements IEntity{
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6284455504643525015L;

	private Long id;

    // 列入日期
    private String putDate;

    // 移除日期
    private String removeDate;

    // 做出决定机关
    private String putDepartment;

    // 移除决定机关
    private String removeDepartment;

    // 列入经营异常名录原因
    private String putReason;

    // 移除经营异常名录原因
    private String removeReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPutDate() {
        return putDate;
    }

    public void setPutDate(String putDate) {
        this.putDate = putDate;
    }

    public String getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(String removeDate) {
        this.removeDate = removeDate;
    }

    public String getPutDepartment() {
        return putDepartment;
    }

    public void setPutDepartment(String putDepartment) {
        this.putDepartment = putDepartment;
    }

    public String getRemoveDepartment() {
        return removeDepartment;
    }

    public void setRemoveDepartment(String removeDepartment) {
        this.removeDepartment = removeDepartment;
    }

    public String getPutReason() {
        return putReason;
    }

    public void setPutReason(String putReason) {
        this.putReason = putReason;
    }

    public String getRemoveReason() {
        return removeReason;
    }

    public void setRemoveReason(String removeReason) {
        this.removeReason = removeReason;
    }
}
