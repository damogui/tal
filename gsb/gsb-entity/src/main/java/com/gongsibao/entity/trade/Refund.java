package com.gongsibao.entity.trade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gongsibao.entity.trade.dic.RefundSettlementMethodType;
import com.gongsibao.entity.u8.U8Bank;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.*;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.RefundType;
import com.gongsibao.entity.trade.dic.RefundWayType;
import com.gongsibao.entity.u8.SetOfBooks;

@Table(name = "so_refund", header = "退款")
public class Refund extends BaseEntity {

    private static final long serialVersionUID = 9015009742333617920L;

    @Column(name = "order_id", header = "订单")
    private Integer orderId;

    // 订单
    @JsonIgnore
    @Reference(foreignKey = "orderId")
    private SoOrder soOrder;


    @Column(name = "set_of_books_id", header = "账套")
    private Integer setOfBooksId;

    @Reference(foreignKey = "setOfBooksId")
    private SetOfBooks setOfBooks;

    @Column(name = "u8_bank_id", header = "支付方式")
    private Integer u8BankId;

    @Reference(foreignKey = "u8BankId")
    private U8Bank u8Bank;

    //审核状态序号，type=105，1051待审核、1052通过、1053不通过
    @Column(name = "audit_status_id", header = "审核状态")
    private AuditStatusType auditStatus = AuditStatusType.wu;

    //退款方式序号，type=313，3131线上、3132线下
    @Column(name = "way_type_id", header = "退款方式")
    private RefundWayType wayType;

    @Column(name = "is_full_refund", header = "是否全额退款")
    private RefundType refundType;

    @Column(header = "编号")
    private String no;

    @Column(name = "payer_name", header = "对方姓名")
    private String payerName;

    @Column(name = "bank_no", header = "银行账号")
    private String bankNo;

    @Column(header = "金额")
    private Integer amount;

    @Column(header = "成本")
    private Integer cost;

    @Column(name = "settlement_method", header = "结算方式（0：无；01：已结算；07：未结算）")
    private RefundSettlementMethodType settlementMethod = RefundSettlementMethodType.Yjs;

    @Column(header = "备注")
    private String remark;

    @Column(name = "refund_time", header = "退款时间")
    private Date refundTime;

    @Column(name = "dep_refund_audit_pass_time", header = "退款业绩审核通过时间")
    private Date depRefundAuditPassTime;

    @Subs(subType = RefundItem.class, foreignKey = "refundId", header = "退款明细")
    private List<RefundItem> refunds = new ArrayList<RefundItem>();

    @Subs(subType = RefundItemPrice.class, foreignKey = "refundId", header = "退款价格")
    private List<RefundItemPrice> prices = new ArrayList<RefundItemPrice>();

    @Subs(subType = NDepRefund.class, foreignKey = "refundId", header = "部门退款业绩")
    private List<NDepRefund> depRefunds = new ArrayList<NDepRefund>();

    //我的退款业绩
    @Exclusive
    private Integer myDepRefundAmount;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public RefundType getRefundType() {
        return refundType;
    }

    public void setRefundType(RefundType refundType) {
        this.refundType = refundType;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<RefundItem> getRefunds() {
        return refunds;
    }

    public void setRefunds(List<RefundItem> refunds) {
        this.refunds = refunds;
    }

    public List<RefundItemPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<RefundItemPrice> prices) {
        this.prices = prices;
    }

    public SoOrder getSoOrder() {
        return soOrder;
    }

    public void setSoOrder(SoOrder soOrder) {
        this.soOrder = soOrder;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public AuditStatusType getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatusType auditStatus) {
        this.auditStatus = auditStatus;
    }

    public RefundWayType getWayType() {
        return wayType;
    }

    public void setWayType(RefundWayType wayType) {
        this.wayType = wayType;
    }

    public Integer getSetOfBooksId() {
        return setOfBooksId;
    }

    public void setSetOfBooksId(Integer setOfBooksId) {
        this.setOfBooksId = setOfBooksId;
    }

    public SetOfBooks getSetOfBooks() {
        return setOfBooks;
    }

    public void setSetOfBooks(SetOfBooks setOfBooks) {
        this.setOfBooks = setOfBooks;
    }

    public List<NDepRefund> getDepRefunds() {
        return depRefunds;
    }

    public void setDepRefunds(List<NDepRefund> depRefunds) {
        this.depRefunds = depRefunds;
    }

    public U8Bank getU8Bank() {
        return u8Bank;
    }

    public void setU8Bank(U8Bank u8Bank) {
        this.u8Bank = u8Bank;
    }

    public Integer getU8BankId() {
        return u8BankId;
    }

    public void setU8BankId(Integer u8BankId) {
        this.u8BankId = u8BankId;
    }

    public Integer getMyDepRefundAmount() {
        return myDepRefundAmount;
    }

    public void setMyDepRefundAmount(Integer myDepRefundAmount) {
        this.myDepRefundAmount = myDepRefundAmount;
    }

    public Date getDepRefundAuditPassTime() {
        return depRefundAuditPassTime;
    }

    public void setDepRefundAuditPassTime(Date depRefundAuditPassTime) {
        this.depRefundAuditPassTime = depRefundAuditPassTime;
    }

    public RefundSettlementMethodType getSettlementMethod() {
        return settlementMethod;
    }

    public void setSettlementMethod(RefundSettlementMethodType settlementMethod) {
        this.settlementMethod = settlementMethod;
    }
}