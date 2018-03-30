package com.gongsibao.entity.igirl;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.igirl.tm.dict.CaseType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

/**
 * 明细订单与方案关联
 */
@Table(name = "so_order_prod_case")
public class OrderProdCase extends BaseEntity {

    @Column(name = "order_prod_id", header = "明细订单id")
    private Integer orderProdId;

    @Column(name = "order_id", header = "订单id")
    private Integer orderId;

    @Column(name = "account_id", header = "会员id")
    private Integer accountId;

    @Column(name = "case_type", header = "方案类型（1商标注册...）")
    private CaseType caseType = CaseType.TRADEMARK_REG;

    @Column(name = "case_item_id", header = "子方案id")
    private Integer caseItemId;

    @Column(name = "case_id", header = "方案id")
    private Integer caseId;

    public Integer getOrderProdId() {
        return orderProdId;
    }

    public void setOrderProdId(Integer orderProdId) {
        this.orderProdId = orderProdId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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
}
