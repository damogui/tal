package com.gongsibao.entity.trade;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_cps")
public class OrderCps extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -1972721035897121635L;
	@Column(name="order_id",header="OrderId")
    private Integer orderId;
    @Column(name="track_code",header="TrackCode")
    private String trackCode;
    @Column(header="type")
    private String type;
    @Column(header="status")
    private Integer status;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(name="update_time",header="UpdateTime")
    private Date updateTime;

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public String getTrackCode() {
        return trackCode;
    }
    public void setTrackCode(String trackCode) {
        this.trackCode = trackCode;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}