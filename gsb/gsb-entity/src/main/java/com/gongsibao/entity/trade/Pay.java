package com.gongsibao.entity.trade;

import java.util.Date;
import java.util.List;

import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Bank;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.OfflineWayType;
import com.gongsibao.entity.trade.dic.PayForOrderCountType;
import com.gongsibao.entity.trade.dic.PayOfflineInstallmentType;
import com.gongsibao.entity.trade.dic.PayReceiptStatus;
import com.gongsibao.entity.trade.dic.PaySuccessStatus;
import com.gongsibao.entity.trade.dic.PayWayType;
import com.gongsibao.entity.uc.User;

@Table(name = "so_pay")
public class Pay extends BaseEntity {

    private static final long serialVersionUID = 2379304079106456618L;

    @Column(header = "编号")
    private String no;

    @Column(header = "金额")
    private Integer amount;

    @Column(name = "pay_way_type_id", header = "支付类型,type=310")
    private PayWayType payWayType;

    @Column(name = "success_status_id", header = "成功状态")
    private PaySuccessStatus successStatus;

    @Column(name = "confirm_time", header = "确认时间")
    private Date confirmTime;

    @Column(name = "pay_time", header = "支付时间")
    private Date payTime;

    @Column(name = "offline_way_type_id", header = "线下类型")
    private OfflineWayType offlineWayType;

    @Column(name = "offline_installment_type", header = "线下结算类型,线下分期类型序号，type= 全款为0，首款为1，尾款为-1，二期为2，三期为3，以此类推")
    private PayOfflineInstallmentType offlineInstallmentType = PayOfflineInstallmentType.sk;

    @Column(name = "offline_payer_name", header = "线下支付名称")
    private String offlinePayerName;

    @Column(name = "offline_bank_no", header = "线下银行卡号")
    private String offlineBankNo;

    @Column(name = "offline_remark", header = "线下备注")
    private String offlineRemark;

    @Column(name = "offline_audit_status_id", header = "审核状态")
    private AuditStatusType offlineAuditStatus = AuditStatusType.wu;

    @Column(name = "offline_add_user_id", header = "线下创建人")
    private Integer offlineAddUserId;

    @Reference(foreignKey = "offlineAddUserId", header = "线下创建人")
    private User offlineAddUser;

    @Column(name = "online_bank_code_id", header = "在线银行卡")
    private String onlineBankCodeId;

    @Column(name = "u8_voucher_id", header = "u8凭证id")
    private String u8VoucherId;

    @Subs(subType = File.class, foreignKey = "formId", header = "上传图片表（一个支付可以多个凭证）")
    private List<File> files;

    @Column(name = "set_of_books_id", header = "账套id")
    private Integer setOfBooksId;

    @Reference(foreignKey = "setOfBooksId")
    private SetOfBooks setOfBooks;

    @Column(name = "u8_bank_id", header = "银行科目编号序号")
    private Integer u8BankId;

    @Reference(foreignKey = "u8BankId")
    private U8Bank u8Bank;

    @Column(name = "online_trade_no", header = "在线交易号")
    private String onlineTradeNo;

    @Column(name = "receipt_no", header = "回单编号")
    private String receiptNo;

    @Column(name = "receipt_status", header = "回单处理状态（0：未完成 1已完成：）")
    private PayReceiptStatus receiptStatus = PayReceiptStatus.NotStarted;

    @Column(name = "pay_for_order_count", header = "支付订单数量（0:一笔单单 1:一笔多单）")
    private PayForOrderCountType payForOrderCount = PayForOrderCountType.Ybdd;

    @Column(name = "dep_pay_create_time", header = "回款业绩创建时间")
    private Date depPayCreateTime;

    @Column(name = "dep_pay_audit_pass_time", header = "回款业绩审核通过时间")
    private String depPayAuditPassTime;

    @Column(name = "dep_pay_create_employee_id", header = "回款业绩创建人id")
    private Integer depPayCreateEmployeeId;

    @Column(name = "dep_pay_create_employee_name", header = "回款业绩创建人")
    private Integer depPayCreateEmployeeName;

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public PayWayType getPayWayType() {
        return payWayType;
    }

    public void setPayWayType(PayWayType payWayType) {
        this.payWayType = payWayType;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getOfflinePayerName() {
        return offlinePayerName;
    }

    public void setOfflinePayerName(String offlinePayerName) {
        this.offlinePayerName = offlinePayerName;
    }

    public String getOfflineBankNo() {
        return offlineBankNo;
    }

    public void setOfflineBankNo(String offlineBankNo) {
        this.offlineBankNo = offlineBankNo;
    }

    public String getOfflineRemark() {
        return offlineRemark;
    }

    public void setOfflineRemark(String offlineRemark) {
        this.offlineRemark = offlineRemark;
    }

    public Integer getOfflineAddUserId() {
        return offlineAddUserId;
    }

    public void setOfflineAddUserId(Integer offlineAddUserId) {
        this.offlineAddUserId = offlineAddUserId;
    }

    public User getOfflineAddUser() {
        return offlineAddUser;
    }

    public void setOfflineAddUser(User offlineAddUser) {
        this.offlineAddUser = offlineAddUser;
    }

    public String getOnlineBankCodeId() {
        return onlineBankCodeId;
    }

    public void setOnlineBankCodeId(String onlineBankCodeId) {
        this.onlineBankCodeId = onlineBankCodeId;
    }

    public String getOnlineTradeNo() {
        return onlineTradeNo;
    }

    public void setOnlineTradeNo(String onlineTradeNo) {
        this.onlineTradeNo = onlineTradeNo;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public PayReceiptStatus getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(PayReceiptStatus receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public PayForOrderCountType getPayForOrderCount() {
        return payForOrderCount;
    }

    public void setPayForOrderCount(PayForOrderCountType payForOrderCount) {
        this.payForOrderCount = payForOrderCount;
    }

    public String getU8VoucherId() {
        return u8VoucherId;
    }

    public void setU8VoucherId(String u8VoucherId) {
        this.u8VoucherId = u8VoucherId;
    }

    public PaySuccessStatus getSuccessStatus() {
        return successStatus;
    }

    public void setSuccessStatus(PaySuccessStatus successStatus) {
        this.successStatus = successStatus;
    }

    public OfflineWayType getOfflineWayType() {
        return offlineWayType;
    }

    public void setOfflineWayType(OfflineWayType offlineWayType) {
        this.offlineWayType = offlineWayType;
    }

    public PayOfflineInstallmentType getOfflineInstallmentType() {
        return offlineInstallmentType;
    }

    public void setOfflineInstallmentType(PayOfflineInstallmentType offlineInstallmentType) {
        this.offlineInstallmentType = offlineInstallmentType;
    }

    public AuditStatusType getOfflineAuditStatus() {
        return offlineAuditStatus;
    }

    public void setOfflineAuditStatus(AuditStatusType offlineAuditStatus) {
        this.offlineAuditStatus = offlineAuditStatus;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Date getDepPayCreateTime() {
        return depPayCreateTime;
    }

    public void setDepPayCreateTime(Date depPayCreateTime) {
        this.depPayCreateTime = depPayCreateTime;
    }

    public String getDepPayAuditPassTime() {
        return depPayAuditPassTime;
    }

    public void setDepPayAuditPassTime(String depPayAuditPassTime) {
        this.depPayAuditPassTime = depPayAuditPassTime;
    }

    public Integer getDepPayCreateEmployeeId() {
        return depPayCreateEmployeeId;
    }

    public void setDepPayCreateEmployeeId(Integer depPayCreateEmployeeId) {
        this.depPayCreateEmployeeId = depPayCreateEmployeeId;
    }

    public Integer getDepPayCreateEmployeeName() {
        return depPayCreateEmployeeName;
    }

    public void setDepPayCreateEmployeeName(Integer depPayCreateEmployeeName) {
        this.depPayCreateEmployeeName = depPayCreateEmployeeName;
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

    public Integer getU8BankId() {
        return u8BankId;
    }

    public void setU8BankId(Integer u8BankId) {
        this.u8BankId = u8BankId;
    }

    public U8Bank getU8Bank() {
        return u8Bank;
    }

    public void setU8Bank(U8Bank u8Bank) {
        this.u8Bank = u8Bank;
    }
}