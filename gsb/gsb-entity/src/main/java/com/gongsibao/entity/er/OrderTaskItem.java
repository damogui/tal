package com.gongsibao.entity.er;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_order_task_item")
public class OrderTaskItem extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -2876062094125812582L;
	@Column(name="order_task_id",header="OrderTaskId")
    private Integer orderTaskId;
    @Column(name="name",header="name")
    private String name;
    @Column(name="is_must",header="IsMust")
    private Integer isMust;
    @Column(name="type",header="type")
    private Integer type;
    @Column(name="content",header="content")
    private String content;
    @Column(name="sort",header="sort")
    private Integer sort;
    @Column(name="upd_time",header="UpdTime")
    private Date updTime;
    @Column(name="v",header="v")
    private Integer v;

    public Integer getOrderTaskId() {
        return orderTaskId;
    }
    public void setOrderTaskId(Integer orderTaskId) {
        this.orderTaskId = orderTaskId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getIsMust() {
        return isMust;
    }
    public void setIsMust(Integer isMust) {
        this.isMust = isMust;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Date getUpdTime() {
        return updTime;
    }
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
    public Integer getV() {
        return v;
    }
    public void setV(Integer v) {
        this.v = v;
    }
}