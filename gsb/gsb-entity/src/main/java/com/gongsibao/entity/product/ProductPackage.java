package com.gongsibao.entity.product;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="prod_package")
public class ProductPackage extends BaseEntity {

	private static final long serialVersionUID = 2084809012877696348L;
	
	private String name;
	@Exclusive//这个字段可能是mysql关键字，查询就会报错
    private String desc;
    private Integer sort;
    @Column(name="is_enabled")
    private Integer isEnabled;
    @Column(name="add_user_id")
    private Integer addUserId;
    @Column(name="add_time")
    private Date addTime;
    @Column(name="upd_user_id")
    private Integer updUserId;
    @Column(name="upd_time")
    private Date updTime;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
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
    public Integer getUpdUserId() {
        return updUserId;
    }
    public void setUpdUserId(Integer updUserId) {
        this.updUserId = updUserId;
    }
    public Date getUpdTime() {
        return updTime;
    }
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
}