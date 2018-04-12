package com.gongsibao.entity.igirl.settle;

import com.gongsibao.entity.igirl.settle.dict.SettleHandleStatus;
import com.gongsibao.entity.supplier.Supplier;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(name = "ig_settle", header = "结算单列表")
public class Settle extends Entity {

    public static final BigDecimal TAX_RATE = new BigDecimal(6.72);

    @Column(name = "memo", header = "结算说明")
    private String memo;

    @Column(name = "handle_status", header = "结算单处理状态")
    private SettleHandleStatus handleStatus = SettleHandleStatus.PLATFORM_AUDITING;

    @Column(name = "total_amount", size = 10, precition = 2, header = "总金额")
    private BigDecimal totalAmount;

    @Column(name = "total_cost", size = 10, precition = 2, header = "成本")
    private BigDecimal totalCost;

    @Column(name = "total_charge", size = 10, precition = 2, header = "服务费")
    private BigDecimal totalCharge;

    @Column(name = "tax_rate", size = 10, precition = 2, header = "税率")
    private BigDecimal taxRate = new BigDecimal(TAX_RATE.doubleValue());

    @Column(name = "tax", size = 10, precition = 2, header = "税额")
    private BigDecimal tax;

    @Column(name = "commission", size = 10, precition = 2, header = "佣金")
    private BigDecimal commission;

    @Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId = -1;

    @JsonIgnore
    @Reference(foreignKey = "supplierId", header = "服务商")
    private Supplier supplier;

    @Subs(foreignKey = "settleId", header = "审核日志", subType = SettleHandleLog.class)
    private List<SettleHandleLog> handleLogList = new ArrayList<>();

    @Subs(foreignKey = "settleId", header = "审核日志", subType = SettleOrder.class)
    private List<SettleOrder> settleOrderList;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public SettleHandleStatus getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(SettleHandleStatus handleStatus) {
        this.handleStatus = handleStatus;
    }

    public BigDecimal getTotalAmount() {
        return null == totalAmount ? BigDecimal.ZERO : totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalCost() {
        return null == totalCost ? BigDecimal.ZERO : totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getTotalCharge() {
        return null == totalCharge ? BigDecimal.ZERO : totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    public BigDecimal getTaxRate() {
        return null == taxRate ? BigDecimal.ZERO : taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTax() {
        return null == tax ? BigDecimal.ZERO : tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getCommission() {
        return null == commission ? BigDecimal.ZERO : commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<SettleHandleLog> getHandleLogList() {
        return handleLogList;
    }

    public void setHandleLogList(List<SettleHandleLog> handleLogList) {
        this.handleLogList = handleLogList;
    }

    public List<SettleOrder> getSettleOrderList() {
        return settleOrderList;
    }

    public void setSettleOrderList(List<SettleOrder> settleOrderList) {
        this.settleOrderList = settleOrderList;
    }
}
