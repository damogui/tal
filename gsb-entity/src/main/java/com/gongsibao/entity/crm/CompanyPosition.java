package com.gongsibao.entity.crm;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_company_position")
public class CompanyPosition extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5789648367055571062L;
	private String name;
    @Column(name="mutex_group_no")
    private Integer mutexGroupNo;
    private String description;
    @Column(name="is_must")
    private String isMust;
    private Integer sort;
    @Column(name="add_user_id")
    private Integer addUserId;
    @Column(name="add_time")
    private Date addTime;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getMutexGroupNo() {
        return mutexGroupNo;
    }
    public void setMutexGroupNo(Integer mutexGroupNo) {
        this.mutexGroupNo = mutexGroupNo;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIsMust() {
        return isMust;
    }
    public void setIsMust(String isMust) {
        this.isMust = isMust;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}