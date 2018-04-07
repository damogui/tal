package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.trade.dic.OrderProdUserMapStatusType;

@Table(name="so_order_prod_user_map")
public class OrderProdUserMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5651659159306865523L;
//	@Column(name="user_id",header="负责人")
//    private Integer userId;
//	
//    @Reference(foreignKey="userId",header="负责人")
//    private User user;
    
	@Column(name="user_id",header="负责人")
    private Integer principalId;
	
    @Reference(foreignKey="principalId",header="负责人")
    private Employee principal;

    @Column(name="order_prod_id",header="订单明细")
    private Integer orderProdId;
    
    @Reference(foreignKey="orderProdId",header="订单明细")
    private OrderProd orderProd;

    @Column(name="type_id",header="人员类型")
    private Integer typeId;
//    业务：好像是销售人员 3061业务员
//    操作：非销售人员，比如外勤和材料编写
//关系类型序号，type=306，3061业务、3062客服（关注）、3063操作
    @Reference(foreignKey="typeId",header="人员类型")
    private Dict type;

    @Column(name="status_id",header="办理状态")
    private OrderProdUserMapStatusType status;

//    public Integer getUserId() {
//        return userId;
//    }
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

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

	public Integer getPrincipalId() {
		return principalId;
	}
	public void setPrincipalId(Integer principalId) {
		this.principalId = principalId;
	}
	public Employee getPrincipal() {
		return principal;
	}
	public void setPrincipal(Employee principal) {
		this.principal = principal;
	}
	public OrderProdUserMapStatusType getStatus() {
		return status;
	}
	public void setStatus(OrderProdUserMapStatusType status) {
		this.status = status;
	}
	
	
}