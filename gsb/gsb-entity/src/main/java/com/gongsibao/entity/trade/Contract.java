package com.gongsibao.entity.trade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.BreachType;
import com.gongsibao.entity.trade.dic.ContractType;
import com.gongsibao.entity.trade.dic.CustomerType;
import com.gongsibao.entity.trade.dic.DataFeeCountType;
import com.gongsibao.entity.trade.dic.SginingCompanyType;

@Table(name = "so_contract", header = "合同")
public class Contract extends BaseEntity {

	private static final long serialVersionUID = -802740307282932651L;
	@Column(name = "order_id", header = "订单序号")
	private Integer orderId;

	// 订单
	@Reference(foreignKey = "orderId", header = "订单")
	private SoOrder soOrder;

	@Column(name = "sgining_time", header = "签约日期")
	private Date sginingTime;

	@Column(name = "sgining_company_id", header = "签单公司，type=316，3161汉唐信通（北京）咨询股份有限公司、3162汉唐信通（北京）科技有限公司")
	private SginingCompanyType sginingCompanyId = SginingCompanyType.CONSULT;

	@Column(name = "is_urgeney", header = "是否加急")
	private Boolean urgeney;

	@Column(name = "sgining_user_id", header = "签单业务员序号")
	private Integer sginingUserId;

	@Column(name = "customer_id", header = "客户序号")
	private Integer customerId;

	@Column(name = "real_amount", header = "实际合同额")
	private Integer realAmount;

	@Column(name = "has_data_fee", header = "是否有材料撰写情况")
	private BreachType hasDataFee = BreachType.YOU;

	@Column(name = "data_fee_count_type_id", header = "材料撰写次数类型序号，type=317，3171无、3172首期一次、3173末期一次、3174首期一次末期一次")
	private DataFeeCountType dataFeeCountTypeId = DataFeeCountType.WU;

	@Column(name = "first_payment", header = "首期付款")
	private Integer firstPayment = 0;

	@Column(name = "final_payment", header = "末期付款")
	private Integer finalPayment = 0;

	@Column(name = "has_liquidated_damages", header = "是否有违约金")
	private BreachType hasLiquidatedDamages = BreachType.YOU;

	@Column(name = "has_breach", header = "是否有违约责任事项")
	private BreachType hasBreach =BreachType.YOU;

	@Column(name = "liquidated_damages", header = "违约金额")
	private Integer liquidatedDamages;

	@Column(name = "breach_info", header = "违约责任")
	private String breachInfo;

	@Column(name = "file_id", header = "附件序号")
	private Integer fileId = 0;

	@Column(name = "audit_status_id", header = "审核状态序号，type=105，1051待审核、1052通过、1053不通过")
	private AuditStatusType auditStatusId = AuditStatusType.wu;

	@Column(name = "is_bbk", header = "IsBbk")
	private String isBbk = "0";

	@Column(name = "remark", header = "说明")
	private String remark;

	@Column(name = "license_no", header = "营业执照号")
	private String licenseNo;

	@Column(name = "id_number", header = "身份证号")
	private String idNumber ;

	@Column(name = "contract_title", header = "合同标题")
	private String contractTitle;

	@Column(name = "company_name", header = "公司名称")
	private String companyName;

	@Column(name = "contract_type", header = "客户类型  1：个人；2：企业")
	private CustomerType contractType = CustomerType.QY ;

	@Column(name = "contract_sign", header = "合同签署状态0：平台没签署；1：平台签署")
	private Integer contractSign = 0;

	@Column(name = "is_electronics", header = "合同类型   0：纸质；1：电子")
	private ContractType electronics = ContractType.DZ;
	
	
	@Column(name = "supplier_id", header = "服务商Id")
	private Integer supplierId;

	@Reference(foreignKey = "supplierId", header = "服务商")
	private Supplier supplier;

	@Column(name = "department_id", header = "部门Id")
	private Integer departmentId;

	@Reference(foreignKey = "departmentId", header = "部门")
	private SupplierDepartment department;
	
	@Column(name = "owner_id", header = "业务员Id")
	private Integer ownerId;

	@Reference(foreignKey = "ownerId", header = "业务员")
	private Employee owner;
	//======================================================非持久化字段======================================================//
    
	@Exclusive
    @Subs(subType = OrderProd.class, foreignKey = "orderId", header = "产品明细")
    private List<OrderProd> products = new ArrayList<OrderProd>();
	// 材料撰写费
	@Exclusive
	private double dataFee = 0;
	// 合同业绩总额（不生成数据库字段）
	@Exclusive
	private Integer contractPrice;
	//订单编号
	@Exclusive
	private String soOrderNo ;
	//渠道编号
	@Exclusive
	private String channelOrderNo;
	//客户姓名
	@Exclusive
	private String customerName;
	//客户邮箱
	@Exclusive
	private String customerEmail;
	//客户手机号
	@Exclusive
	private String customerMobile;
	//合同来源（平台来源）
	@Exclusive
	private String platformSource;
	//新/老客户
	@Exclusive
	private String accountType ;
	//部门名称
	@Exclusive
	private String departmentName;
	//业务员名称
	@Exclusive
	private String ownerName;
    
	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPlatformSource() {
		return platformSource;
	}

	public void setPlatformSource(String platformSource) {
		this.platformSource = platformSource;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getSoOrderNo() {
		return soOrderNo;
	}

	public void setSoOrderNo(String soOrderNo) {
		this.soOrderNo = soOrderNo;
	}

	public String getChannelOrderNo() {
		return channelOrderNo;
	}

	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}

	public List<OrderProd> getProducts() {
		return products;
	}

	public void setProducts(List<OrderProd> products) {
		this.products = products;
	}

	

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getSginingTime() {
		return sginingTime;
	}

	public void setSginingTime(Date sginingTime) {
		this.sginingTime = sginingTime;
	}

	public SginingCompanyType getSginingCompanyId() {
		return sginingCompanyId;
	}

	public void setSginingCompanyId(SginingCompanyType sginingCompanyId) {
		this.sginingCompanyId = sginingCompanyId;
	}

	public double getDataFee() {
//		if (getHasDataFee().getValue()==0 || null == getRealAmount()) {
//			return 0;
//		}
//		dataFee = getRealAmount() - getContractPrice();
//		if (dataFee < 0) {
//			return 0;
//		}
		return dataFee;
	}

	public void setDataFee(double dataFee) {
		this.dataFee = dataFee;
	}

	public Integer getSginingUserId() {
		return sginingUserId;
	}

	public void setSginingUserId(Integer sginingUserId) {
		this.sginingUserId = sginingUserId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(Integer realAmount) {
		this.realAmount = realAmount;
	}

	public DataFeeCountType getDataFeeCountTypeId() {
		return dataFeeCountTypeId;
	}

	public void setDataFeeCountTypeId(DataFeeCountType dataFeeCountTypeId) {
		this.dataFeeCountTypeId = dataFeeCountTypeId;
	}

	public Integer getFirstPayment() {
		return firstPayment;
	}

	public void setFirstPayment(Integer firstPayment) {
		this.firstPayment = firstPayment;
	}

	public Integer getFinalPayment() {
		return finalPayment;
	}

	public void setFinalPayment(Integer finalPayment) {
		this.finalPayment = finalPayment;
	}

	public BreachType getHasDataFee() {
		return hasDataFee;
	}

	public void setHasDataFee(BreachType hasDataFee) {
		this.hasDataFee = hasDataFee;
	}

	public BreachType getHasLiquidatedDamages() {
		return hasLiquidatedDamages;
	}

	public void setHasLiquidatedDamages(BreachType hasLiquidatedDamages) {
		this.hasLiquidatedDamages = hasLiquidatedDamages;
	}

	public BreachType getHasBreach() {
		return hasBreach;
	}

	public void setHasBreach(BreachType hasBreach) {
		this.hasBreach = hasBreach;
	}

	public Integer getLiquidatedDamages() {
		return liquidatedDamages;
	}

	public void setLiquidatedDamages(Integer liquidatedDamages) {
		this.liquidatedDamages = liquidatedDamages;
	}

	public String getBreachInfo() {
		return breachInfo;
	}

	public void setBreachInfo(String breachInfo) {
		this.breachInfo = breachInfo;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public AuditStatusType getAuditStatusId() {
		return auditStatusId;
	}

	public void setAuditStatusId(AuditStatusType auditStatusId) {
		this.auditStatusId = auditStatusId;
	}

	public String getIsBbk() {
		return isBbk;
	}

	public void setIsBbk(String isBbk) {
		this.isBbk = isBbk;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getContractTitle() {
		return contractTitle;
	}

	public void setContractTitle(String contractTitle) {
		this.contractTitle = contractTitle;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public CustomerType getContractType() {
		return contractType;
	}

	public void setContractType(CustomerType contractType) {
		this.contractType = contractType;
	}

	public Integer getContractSign() {
		return contractSign;
	}

	public void setContractSign(Integer contractSign) {
		this.contractSign = contractSign;
	}

	public SoOrder getSoOrder() {
		return soOrder;
	}

	public void setSoOrder(SoOrder soOrder) {
		this.soOrder = soOrder;
	}

	public Integer getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(Integer contractPrice) {
		this.contractPrice = contractPrice;
	}

	public Boolean getUrgeney() {
		return urgeney;
	}

	public void setUrgeney(Boolean urgeney) {
		this.urgeney = urgeney;
	}

	public ContractType getElectronics() {
		return electronics;
	}

	public void setElectronics(ContractType electronics) {
		this.electronics = electronics;
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

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public SupplierDepartment getDepartment() {
		return department;
	}

	public void setDepartment(SupplierDepartment department) {
		this.department = department;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}
}