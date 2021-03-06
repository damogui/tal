package com.gongsibao.entity.trade;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Preferential;
import com.gongsibao.entity.trade.dic.PreferentiaType;

@Table(name="so_order_discount",header="优惠劵信息")
public class OrderDiscount extends BaseEntity {

    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1894864532185900021L;
	@Column(name="order_id",header="订单序号")
    private Integer orderId;
	
	@JsonIgnore
	@Reference(foreignKey = "orderId", primaryKey = "pkid")
	private SoOrder soOrder;
	
//	3091	后台优惠
//	3092	优惠券
    @Column(name="type_id",header="优惠类型序号，type=309")
    private PreferentiaType type = PreferentiaType.COUPON;
    
    @Column(name="preferential_id",header="优惠券ID")
    private Integer preferentialId;
    
	@Reference(foreignKey = "preferentialId")
	private Preferential preferential;
    
    @Column(name="amount",header="优惠金额，优惠前-优惠后")
    private Integer amount;
    
    @Column(name="remark",header="说明")
    private String remark;
    
    @Column(name="sqlid",header="对应SQL库的优惠券ID")
    private String sqlid;
    
    @Column(name="no",header="优惠券号码")
    private String no;

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    public SoOrder getSoOrder() {
		return soOrder;
	}
	public void setSoOrder(SoOrder soOrder) {
		this.soOrder = soOrder;
	}
	
    public PreferentiaType getType() {
		return type;
	}
	public void setType(PreferentiaType type) {
		this.type = type;
	}
	public Preferential getPreferential() {
		return preferential;
	}
	public void setPreferential(Preferential preferential) {
		this.preferential = preferential;
	}
	public Integer getPreferentialId() {
        return preferentialId;
    }
    public void setPreferentialId(Integer preferentialId) {
        this.preferentialId = preferentialId;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getSqlid() {
        return sqlid;
    }
    public void setSqlid(String sqlid) {
        this.sqlid = sqlid;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }

    
}