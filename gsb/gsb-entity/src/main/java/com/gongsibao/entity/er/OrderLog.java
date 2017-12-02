package com.gongsibao.entity.er;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_order_log")
public class OrderLog extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5610018336198931405L;
	@Column(name="",header="type")
    private Integer type;
    @Column(name="order_id",header="OrderId")
    private Integer orderId;
    @Column(name="order_task_id",header="OrderTaskId")
    private Integer orderTaskId;
    @Column(name="",header="title")
    private String title;
    @Column(name="",header="log")
    private String log;
    @Column(name="",header="enclosure")
    private String enclosure;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(name="add_time",header="AddTime")
    private Date addTime;

    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getOrderTaskId() {
        return orderTaskId;
    }
    public void setOrderTaskId(Integer orderTaskId) {
        this.orderTaskId = orderTaskId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLog() {
        return log;
    }
    public void setLog(String log) {
        this.log = log;
    }
    public String getEnclosure() {
        return enclosure;
    }
    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
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