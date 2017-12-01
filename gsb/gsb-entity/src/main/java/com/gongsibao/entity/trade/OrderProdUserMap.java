package com.gongsibao.entity.trade;

import java.sql.Date;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.uc.User;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_prod_user_map")
public class OrderProdUserMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5651659159306865523L;
	@Column(name="user_id",header="负责人")
    private Integer userId;
    @Reference(foreignKey="userId",header="负责人")
    private User user;

    @Column(name="order_prod_id",header="订单明细")
    private Integer orderProdId;
    @Reference(foreignKey="orderProdId",header="订单明细")
    private OrderProd orderProd;

    @Column(name="type_id",header="人员类型")
    private Integer typeId;
//    业务：好像是销售人员 3061业务员
//    操作：非销售人员，比如外勤和材料编写
    @Reference(foreignKey="typeId",header="人员类型")
    private Dict type;

    @Column(name="status_id",header="办理状态")
    private Integer statusId;
//    3141正在负责 3142 曾经负责
    @Reference(foreignKey="statusId",header="办理状态")
    private Dict status;

    @Column(name="add_time",header="AddTime")
    private java.sql.Timestamp addTime;
    @Column(name="is_bbk",header="IsBbk")
    private String isBbk="0";

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getOrderProdId() {
        return orderProdId;
    }
    public void setOrderProdId(Integer orderProdId) {
        this.orderProdId = orderProdId;
    }
    public Integer getTypeId() {
        return typeId;
    }
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public Integer getStatusId() {
        return statusId;
    }
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
    public java.sql.Timestamp getAddTime() {
        return addTime;
    }
    public void setAddTime(java.sql.Timestamp addTime) {
        this.addTime = addTime;
    }
    public String getIsBbk() {
        return isBbk;
    }
    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderProd getOrderProd() {
        return orderProd;
    }

    public void setOrderProd(OrderProd orderProd) {
        this.orderProd = orderProd;
    }

    public Dict getType() {
        return type;
    }

    public void setType(Dict type) {
        this.type = type;
    }

    public Dict getStatus() {
        return status;
    }

    public void setStatus(Dict status) {
        this.status = status;
    }
}