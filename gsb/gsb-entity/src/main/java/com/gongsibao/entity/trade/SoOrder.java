package com.gongsibao.entity.trade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.uc.User;

@Table(name="so_order")
public class SoOrder extends BaseEntity {
	
	private static final long serialVersionUID = 8766647536940983034L;
	
    @Column(header="编码")
    private String no;
    
	@Column(header="订单类型")
    private Integer type;
	
	@Reference(foreignKey="type",header="订单类型")
	private Dict typeDict;
    
    @Column(name="account_id",header="账户")
    private Integer accountId;
    
    @Column(name="account_name",header="账户名称")
    private String accountName;
    @Column(name="account_mobile",header="手机号")
    private String accountMobile;
    @Column(name="pay_status_id",header="支付状态")
    private Integer payStatusId;
    @Reference(foreignKey="payStatusId",header="支付状态")
	private Dict payStatus;
    @Column(name="pay_time",header="支付时间")
    private Date payTime;
    
    @Column(name="process_status_id",header="执行进度")
    private Integer processStatusId;
    
    @Reference(foreignKey="processStatusId",header="执行进度")
	private Dict processStatus;
    
    @Column(name="refund_status_id",header="退款状态")
    private Integer refundStatusId;
    
    @Reference(foreignKey="refundStatusId",header="退款状态")
  	private Dict refundStatus;
    @Column(name="total_price",header="总金额")
    private Integer totalPrice;
    @Column(name="payable_price",header="未支付金额")
    private Integer payablePrice;
    @Column(name="paid_price",header="已支付金额")
    private Integer paidPrice;
    @Column(name="source_type_id",header="来源类型")
    private Integer sourceTypeId;
    @Reference(foreignKey="sourceTypeId",header="来源类型")
  	private Dict sourceType;
    @Column(name="is_installment",header="多次支付")
    private Integer isInstallment;
    @Column(name="installment_mode",header="多次支付方式")
    private String installmentMode;
    @Column(name="installment_audit_status_id",header="多次支付状态")
    private Integer installmentAuditStatusId;
    @Reference(foreignKey="installmentAuditStatusId",header="多次支付状态")
  	private Dict installmentAuditStatus;
    @Column(name="is_change_price",header="改过价")
    private Integer isChangePrice;
    @Column(name="is_carry_over",header="IsCarryOver")
    private Integer isCarryOver;
    @Column(name="change_price_audit_status_id",header="改价审核状态")
    private Integer changePriceAuditStatusId;
    @Reference(foreignKey="changePriceAuditStatusId",header="多次支付状态")
  	private Dict changePriceAuditStatus;
    @Column(name="is_invoice",header="开票")
    private Integer isInvoice;
    @Column(header="description")
    private String description;
    @Column(name="is_package",header="套餐")
    private Integer isPackage;
    @Column(name="package_id",header="套餐")
    private Integer packageId;
    @Reference(foreignKey="packageId",header="套餐")
   	private Package packageProduct;
    
//    @Column(name="add_time",header="创建时间")
//    private Date addTime;
    @Column(name="is_bbk",header="IsBbk")
    private String isBbk="0";
    @Column(name="add_user_id",header="创建人")
    private Integer addUserId;
    @Reference(foreignKey="addUserId",header="创建人")
   	private User addUser;
    
    @Column(name="prod_name",header="产品名称")
    private String prodName;
    @Column(name="is_delete",header="已删除")
    private Integer isDelete;
    @Column(name="company_id",header="公司")
    private Integer companyId;
    @Column(header="备注")
    private String remark;
    @Column(name="platform_source",header="平台来源")
    private Integer platformSource;
    @Reference(foreignKey="platformSource",header="平台来源")
   	private Dict platformSourceDict;
    @Column(name="deliver_id",header="邮寄人")
    private Integer deliverId;
    @Reference(foreignKey="deliverId",header="创建人")
   	private User deliver;
    @Column(name="deliver_addr",header="邮寄地址")
    private String deliverAddr;
    @Column(name="account_type",header="账户类型")
    private Integer accountType;
    @Reference(foreignKey="accountType",header="账户类型")
   	private Dict accountTypeDict;
    @Column(name="is_expire_sms",header="过期短信提醒")
    private Integer isExpireSms;
    
    @Subs(subType=OrderProd.class,foreignKey="orderId",header="产品明细")
    private List<OrderProd> products = new ArrayList<OrderProd>();
    
    @Subs(subType=OrderPayMap.class,foreignKey="orderId",header="支付明细")
    private List<OrderPayMap> pays = new ArrayList<OrderPayMap>();
    
    @Subs(subType=Refund.class,foreignKey="orderId",header="退款明细")
    private List<Refund> redunds = new ArrayList<Refund>();

    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getAccountMobile() {
        return accountMobile;
    }
    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }
    public Integer getPayStatusId() {
        return payStatusId;
    }
    public void setPayStatusId(Integer payStatusId) {
        this.payStatusId = payStatusId;
    }
    public Date getPayTime() {
        return payTime;
    }
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    public Integer getProcessStatusId() {
        return processStatusId;
    }
    public void setProcessStatusId(Integer processStatusId) {
        this.processStatusId = processStatusId;
    }
    public Integer getRefundStatusId() {
        return refundStatusId;
    }
    public void setRefundStatusId(Integer refundStatusId) {
        this.refundStatusId = refundStatusId;
    }
    public Integer getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Integer getPayablePrice() {
        return payablePrice;
    }
    public void setPayablePrice(Integer payablePrice) {
        this.payablePrice = payablePrice;
    }
    public Integer getPaidPrice() {
        return paidPrice;
    }
    public void setPaidPrice(Integer paidPrice) {
        this.paidPrice = paidPrice;
    }
    public Integer getSourceTypeId() {
        return sourceTypeId;
    }
    public void setSourceTypeId(Integer sourceTypeId) {
        this.sourceTypeId = sourceTypeId;
    }
    public Integer getIsInstallment() {
        return isInstallment;
    }
    public void setIsInstallment(Integer isInstallment) {
        this.isInstallment = isInstallment;
    }
    public String getInstallmentMode() {
        return installmentMode;
    }
    public void setInstallmentMode(String installmentMode) {
        this.installmentMode = installmentMode;
    }
    public Integer getInstallmentAuditStatusId() {
        return installmentAuditStatusId;
    }
    public void setInstallmentAuditStatusId(Integer installmentAuditStatusId) {
        this.installmentAuditStatusId = installmentAuditStatusId;
    }
    public Integer getIsChangePrice() {
        return isChangePrice;
    }
    public void setIsChangePrice(Integer isChangePrice) {
        this.isChangePrice = isChangePrice;
    }
    public Integer getIsCarryOver() {
        return isCarryOver;
    }
    public void setIsCarryOver(Integer isCarryOver) {
        this.isCarryOver = isCarryOver;
    }
    public Integer getChangePriceAuditStatusId() {
        return changePriceAuditStatusId;
    }
    public void setChangePriceAuditStatusId(Integer changePriceAuditStatusId) {
        this.changePriceAuditStatusId = changePriceAuditStatusId;
    }
    public Integer getIsInvoice() {
        return isInvoice;
    }
    public void setIsInvoice(Integer isInvoice) {
        this.isInvoice = isInvoice;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getIsPackage() {
        return isPackage;
    }
    public void setIsPackage(Integer isPackage) {
        this.isPackage = isPackage;
    }
    public Integer getPackageId() {
        return packageId;
    }
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }
//    public Date getAddTime() {
//        return addTime;
//    }
//    public void setAddTime(Date addTime) {
//        this.addTime = addTime;
//    }
    public String getIsBbk() {
        return isBbk;
    }
    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public String getProdName() {
        return prodName;
    }
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getPlatformSource() {
        return platformSource;
    }
    public void setPlatformSource(Integer platformSource) {
        this.platformSource = platformSource;
    }
    public Integer getDeliverId() {
        return deliverId;
    }
    public void setDeliverId(Integer deliverId) {
        this.deliverId = deliverId;
    }
    public String getDeliverAddr() {
        return deliverAddr;
    }
    public void setDeliverAddr(String deliverAddr) {
        this.deliverAddr = deliverAddr;
    }
    public Integer getAccountType() {
        return accountType;
    }
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
    public Integer getIsExpireSms() {
        return isExpireSms;
    }
    public void setIsExpireSms(Integer isExpireSms) {
        this.isExpireSms = isExpireSms;
    }
	public List<OrderPayMap> getPays() {
		return pays;
	}
	public void setPays(List<OrderPayMap> pays) {
		this.pays = pays;
	}
	public List<Refund> getRedunds() {
		return redunds;
	}
	public void setRedunds(List<Refund> redunds) {
		this.redunds = redunds;
	}
	public List<OrderProd> getProducts() {
		return products;
	}
	public void setProducts(List<OrderProd> products) {
		this.products = products;
	}
	public Dict getTypeDict() {
		return typeDict;
	}
	public void setTypeDict(Dict typeDict) {
		this.typeDict = typeDict;
	}
	public Dict getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Dict payStatus) {
		this.payStatus = payStatus;
	}
	public Dict getAccountTypeDict() {
		return accountTypeDict;
	}
	public void setAccountTypeDict(Dict accountTypeDict) {
		this.accountTypeDict = accountTypeDict;
	}
	public Dict getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(Dict processStatus) {
		this.processStatus = processStatus;
	}
	public Dict getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(Dict refundStatus) {
		this.refundStatus = refundStatus;
	}
	public Dict getSourceType() {
		return sourceType;
	}
	public void setSourceType(Dict sourceType) {
		this.sourceType = sourceType;
	}
	public Dict getInstallmentAuditStatus() {
		return installmentAuditStatus;
	}
	public void setInstallmentAuditStatus(Dict installmentAuditStatus) {
		this.installmentAuditStatus = installmentAuditStatus;
	}
	public Dict getChangePriceAuditStatus() {
		return changePriceAuditStatus;
	}
	public void setChangePriceAuditStatus(Dict changePriceAuditStatus) {
		this.changePriceAuditStatus = changePriceAuditStatus;
	}
	public Dict getPlatformSourceDict() {
		return platformSourceDict;
	}
	public void setPlatformSourceDict(Dict platformSourceDict) {
		this.platformSourceDict = platformSourceDict;
	}
	public User getAddUser() {
		return addUser;
	}
	public void setAddUser(User addUser) {
		this.addUser = addUser;
	}
	public User getDeliver() {
		return deliver;
	}
	public void setDeliver(User deliver) {
		this.deliver = deliver;
	}
	public Package getPackageProduct() {
		return packageProduct;
	}
	public void setPackageProduct(Package packageProduct) {
		this.packageProduct = packageProduct;
	}
}