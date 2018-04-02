package com.gongsibao.entity.igirl;

import com.gongsibao.entity.igirl.tm.TradeMark;
import com.gongsibao.entity.igirl.tm.dict.CaseType;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.uc.Account;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.math.BigDecimal;

/**
 * 明细订单与方案关联
 */
@Table(name = "so_order_prod_case", header = "明细订单与方案关联表")
public class OrderProdCase extends Entity {

    @Column(name = "order_prod_id", header = "明细订单id")
    private Integer orderProdId;

    @JsonIgnore
    @Reference(foreignKey = "orderProdId", header = "明细订单")
    private OrderProd orderProd;

    @Column(name = "order_id", header = "订单id")
    private Integer orderId;

    @JsonIgnore
    @Reference(foreignKey = "orderId", header = "订单")
    private SoOrder soOrder;

    @Column(name = "account_id", header = "会员id")
    private Integer accountId;

    @JsonIgnore
    @Reference(foreignKey = "accountId", header = "会员")
    private Account account;

    @Column(name = "case_type", header = "方案类型（1商标注册...）")
    private CaseType caseType = CaseType.TRADEMARK_REG;

    @Column(name = "case_item_id", header = "子方案id")
    private Integer caseItemId;

    @Column(name = "case_id", header = "方案id")
    private Integer caseId;

    @Column(name = "memo", size = 1024, header = "冗余字段-商品说明")
    private String memo;

    @Column(name = "cost", size = 10, precition = 2, header = "冗余字段-成本")
    private BigDecimal cost;

    @Column(name = "charge", size = 10, precition = 2, header = "冗余字段-服务费")
    private BigDecimal charge;

    public Integer getOrderProdId() {
        return orderProdId;
    }

    public void setOrderProdId(Integer orderProdId) {
        this.orderProdId = orderProdId;
    }

    public OrderProd getOrderProd() {
        return orderProd;
    }

    public void setOrderProd(OrderProd orderProd) {
        this.orderProd = orderProd;
    }

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

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public CaseType getCaseType() {
        return caseType;
    }

    public void setCaseType(CaseType caseType) {
        this.caseType = caseType;
    }

    public Integer getCaseItemId() {
        return caseItemId;
    }

    public void setCaseItemId(Integer caseItemId) {
        this.caseItemId = caseItemId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getCost() {
        return null == cost ? new BigDecimal(0) : cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getCharge() {
        return null == charge ? new BigDecimal(0) : charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }
}
