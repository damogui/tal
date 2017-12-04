package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_cps",header="cps平台带单记录表")
public class OrderCps extends BaseEntity {
	
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -1972721035897121635L;
	@Column(name="order_id",header="订单序号")
    private Integer orderId;
	
    @Column(name="track_code",header="track_code")
    private String trackCode;
    
    @Column(name="type",header="cps平台类型")
    private String type;
    
    @Column(name="status",header="0:未推送1:已推送")
    private Integer status;

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
}