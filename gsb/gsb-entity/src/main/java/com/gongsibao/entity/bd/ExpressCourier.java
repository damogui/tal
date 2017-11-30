package com.gongsibao.entity.bd;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="bd_express_courier")
public class ExpressCourier extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 4500931449664591923L;
	@Column(header="name")
    private String name;
    @Column(header="code")
    private String code;
    @Column(name="first_letter",header="FirstLetter")
    private String firstLetter;
    @Column(name="is_hot",header="IsHot")
    private Integer isHot;
    @Column(header="sort")
    private Double sort;
    @Column(name="is_enabled",header="IsEnabled")
    private Integer isEnabled;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(header="remark")
    private String remark;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getFirstLetter() {
        return firstLetter;
    }
    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }
    public Integer getIsHot() {
        return isHot;
    }
    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }
    public Double getSort() {
        return sort;
    }
    public void setSort(Double sort) {
        this.sort = sort;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}