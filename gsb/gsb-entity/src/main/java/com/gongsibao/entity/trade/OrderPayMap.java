package com.gongsibao.entity.trade;

import com.gongsibao.entity.trade.dic.PayOfflineInstallmentType;
import com.gongsibao.entity.u8.U8Bank;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.*;

import com.gongsibao.entity.BaseEntity;

import java.util.List;

@Table(name = "so_order_pay_map", header = "订单和支付关系")
public class OrderPayMap extends BaseEntity {

    private static final long serialVersionUID = -9139177565018673953L;

    @Column(name = "order_id", header = "订单序号")
    private Integer orderId;

    @JsonIgnore
    @Reference(header = "订单", foreignKey = "orderId", primaryKey = "pkid")
    private SoOrder soOrder;

    @Column(name = "pay_id", header = "支付序号")
    private Integer payId;

    @Reference(header = "支付", foreignKey = "payId", primaryKey = "pkid")
    private Pay pay;
    /*new beg*/
    @Column(name = "offline_installment_type", header = "线下结算类型,线下分期类型序号，type= 全款为0，首款为1，尾款为-1，二期为2，三期为3，以此类推")
    private PayOfflineInstallmentType offlineInstallmentType = PayOfflineInstallmentType.sk;
    @Column(name = "u8_bank_id", header = "银行科目编号序号")
    private Integer u8BankId;
    @Column(name = "order_price", header = "订单额（一笔多单时，u8生成凭证时，每单的金额）")
    private Integer orderPrice;

    @Reference(foreignKey = "u8BankId", header = "支付方式")
    private U8Bank u8Bank;


    //部门回款业绩表
    @Subs(subType = NDepPay.class, foreignKey = "orderPayMapId", header = "回款业绩")
    private List<NDepPay> depPays;
    @Exclusive
    private Integer orderCutAmount;//订单分配金额
    private Integer payType;//付款类别
    private String supperName;//回款业绩分配服务商
    private String depName;//回款业绩分配部门
    private String cutMan;//回款业绩分配业务员
    private Integer cutAmount;//回款业绩分配金额


    //我的订单回款额
    @Exclusive
    private Integer myOrderCutAmount;

    /*new end*/
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public SoOrder getSoOrder() {
        return soOrder;
    }

    public void setSoOrder(SoOrder soOrder) {
        this.soOrder = soOrder;
    }

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }

    public List<NDepPay> getDepPays() {
        return depPays;
    }

    public void setDepPays(List<NDepPay> depPays) {
        this.depPays = depPays;
    }

    public PayOfflineInstallmentType getOfflineInstallmentType() {
        return offlineInstallmentType;
    }

    public void setOfflineInstallmentType(PayOfflineInstallmentType offlineInstallmentType) {
        this.offlineInstallmentType = offlineInstallmentType;
    }

    public Integer getU8BankId() {
        return u8BankId;
    }

    public void setU8BankId(Integer u8BankId) {
        this.u8BankId = u8BankId;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }


    public U8Bank getU8Bank() {
        return u8Bank;
    }

    public void setU8Bank(U8Bank u8Bank) {
        this.u8Bank = u8Bank;
    }

    public Integer getOrderCutAmount() {
        return orderCutAmount;
    }

    public void setOrderCutAmount(Integer orderCutAmount) {
        this.orderCutAmount = orderCutAmount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getSupperName() {
        return supperName;
    }

    public void setSupperName(String supperName) {
        this.supperName = supperName;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getCutMan() {
        return cutMan;
    }

    public void setCutMan(String cutMan) {
        this.cutMan = cutMan;
    }

    public Integer getCutAmount() {
        return cutAmount;
    }

    public void setCutAmount(Integer cutAmount) {
        this.cutAmount = cutAmount;
    }

    public Integer getMyOrderCutAmount() {
        return myOrderCutAmount;
    }

    public void setMyOrderCutAmount(Integer myOrderCutAmount) {
        this.myOrderCutAmount = myOrderCutAmount;
    }
}
