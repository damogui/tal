package com.gongsibao.entity.ma;

import java.math.BigDecimal;
import java.util.Date;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.ma.dic.MaOrderState;
import com.gongsibao.entity.ma.dic.OrderAuditState;
@BizCode(bizType="MO")
@Table(name="ma_order",header="订单信息")
public class MaOrder extends BizEntity {

    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8985587495477997814L;

	@Reference(foreignKey="sellingDemandId")
    private SellingDemand sellingDemand;
    
    @Column(name="selling_demand_id",header="出售需求")
    private Integer sellingDemandId;
    
    @Reference(foreignKey="acquisitionDemandId")
    private AcquisitionDemand acquisitionDemand;
    
    @Column(name="acquisition_demand_id",header="收购需求")
    private Integer acquisitionDemandId;

	@Column(name="company_name",size=200,header="公司名称")
	private String companyName;
    
    @Reference(foreignKey="sellerId")
    private Employee seller;
    
    @Column(name="seller_id",header="出售业务员")
    private Integer sellerId;

    @Reference(foreignKey="buyerId")
    private Employee buyer;
    
    @Column(name="buyer_id",header="收购业务员")
    private Integer buyerId;

	@Column(name="cost", header="成本")
	private BigDecimal cost;
    
	@Column(name="sign_price", header="签单价")
	private BigDecimal signPrice;
	
	@Column(name="has_expense", header="是否有招待费")
	private Boolean hasExpense = false;
	
	@Column(name="expense", header="招待费")
	private BigDecimal expense;

	@Column(name="has_penalty", header="是否有违约金")
	private Boolean hasPenalty = false;
	
	@Column(name="performance", header="业绩额")
	private BigDecimal performance;

	@Column(name="sign_date", header="签单时间")
	private Date signDate;

	@Column(name="consignment_sell_agreement",size=200,header="委托出售协议(附件)")
	private String consignmentSellAgreement;
	
	@Column(name="consignment_acquisitions_agreement",size=200,header="委托收购协议(附件)")
	private String consignmentAcquisitionsAgreement;
	
	@Column(name="equity_ttransfer_agreement",size=200,header="股权转让协议(附件)")
	private String equityTransferAgreement;
	
	@Column(name="deposit_receipt",size=200,header="订金收据(附件)")
	private String depositReceipt;
	
	@Column(name="down_payment_receipt",size=200,header="首款收据(附件)")
	private String downPaymentReceipt;
	
	@Column(name="final_payment_receipt",size=200,header="尾款收据(附件)")
	private String finalPaymentReceipt;

	@Column(name="finish_date", header="完成时间")
	private Date finishDate;

	@Column(name="handover_date", header="交接时间")
	private Date handoverDate;	

    @Reference(foreignKey="handoverId")
    private Employee handover;
    
    @Column(name="handover_id",header="交接人")
    private Integer handoverId;

    @Column(name="state",header="订单状态")
    private MaOrderState state = MaOrderState.UNPAID;

	@Column(name="finish_voucher",size=200,header="完成凭证(附件)")
	private String finishVoucher;
    
    @Reference(foreignKey="customerServiceId")
    private Employee customerService;
    
    @Column(name="customer_service_id",header="收购客服")
    private Integer customerServiceId;
    
    @Column(name="group_leader_audit_state",header="组长审核状态")
    private OrderAuditState groupLeaderAuditState;
	
    @Reference(foreignKey="leaderId")
    private Employee leader;
    
    @Column(name="leader_id",header="收购组长")
    private Integer leaderId;
    
    @Column(name="vp_audit_state",header="分管副总审核状态")
    private OrderAuditState vpAuditState;
    
    @Reference(foreignKey="vicePresidentId")
    private Employee vicePresident;
    
    @Column(name="vice_president_id",header="收购分管副总")
    private Integer vicePresidentId;



	public SellingDemand getSellingDemand() {
		return sellingDemand;
	}

	public void setSellingDemand(SellingDemand sellingDemand) {
		this.sellingDemand = sellingDemand;
	}

	public AcquisitionDemand getAcquisitionDemand() {
		return acquisitionDemand;
	}

	public void setAcquisitionDemand(AcquisitionDemand acquisitionDemand) {
		this.acquisitionDemand = acquisitionDemand;
	}

	public Integer getSellingDemandId() {
		return sellingDemandId;
	}

	public void setSellingDemandId(Integer sellingDemandId) {
		this.sellingDemandId = sellingDemandId;
	}

	public Integer getAcquisitionDemandId() {
		return acquisitionDemandId;
	}

	public void setAcquisitionDemandId(Integer acquisitionDemandId) {
		this.acquisitionDemandId = acquisitionDemandId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Employee getSeller() {
		return seller;
	}

	public void setSeller(Employee seller) {
		this.seller = seller;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Employee getBuyer() {
		return buyer;
	}

	public void setBuyer(Employee buyer) {
		this.buyer = buyer;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getSignPrice() {
		return signPrice;
	}

	public void setSignPrice(BigDecimal signPrice) {
		this.signPrice = signPrice;
	}

	public Boolean getHasExpense() {
		return hasExpense;
	}

	public void setHasExpense(Boolean hasExpense) {
		this.hasExpense = hasExpense;
	}

	public BigDecimal getExpense() {
		return expense;
	}

	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}

	public Boolean getHasPenalty() {
		return hasPenalty;
	}

	public void setHasPenalty(Boolean hasPenalty) {
		this.hasPenalty = hasPenalty;
	}

	public BigDecimal getPerformance() {
		return performance;
	}

	public void setPerformance(BigDecimal performance) {
		this.performance = performance;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getConsignmentSellAgreement() {
		return consignmentSellAgreement;
	}

	public void setConsignmentSellAgreement(String consignmentSellAgreement) {
		this.consignmentSellAgreement = consignmentSellAgreement;
	}

	public String getConsignmentAcquisitionsAgreement() {
		return consignmentAcquisitionsAgreement;
	}

	public void setConsignmentAcquisitionsAgreement(
			String consignmentAcquisitionsAgreement) {
		this.consignmentAcquisitionsAgreement = consignmentAcquisitionsAgreement;
	}

	public String getEquityTransferAgreement() {
		return equityTransferAgreement;
	}

	public void setEquityTransferAgreement(String equityTransferAgreement) {
		this.equityTransferAgreement = equityTransferAgreement;
	}

	public String getDepositReceipt() {
		return depositReceipt;
	}

	public void setDepositReceipt(String depositReceipt) {
		this.depositReceipt = depositReceipt;
	}

	public String getDownPaymentReceipt() {
		return downPaymentReceipt;
	}

	public void setDownPaymentReceipt(String downPaymentReceipt) {
		this.downPaymentReceipt = downPaymentReceipt;
	}

	public String getFinalPaymentReceipt() {
		return finalPaymentReceipt;
	}

	public void setFinalPaymentReceipt(String finalPaymentReceipt) {
		this.finalPaymentReceipt = finalPaymentReceipt;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Date getHandoverDate() {
		return handoverDate;
	}

	public void setHandoverDate(Date handoverDate) {
		this.handoverDate = handoverDate;
	}

	public Employee getHandover() {
		return handover;
	}

	public void setHandover(Employee handover) {
		this.handover = handover;
	}

	public Integer getHandoverId() {
		return handoverId;
	}

	public void setHandoverId(Integer handoverId) {
		this.handoverId = handoverId;
	}

	public MaOrderState getState() {
		return state;
	}

	public void setState(MaOrderState state) {
		this.state = state;
	}

	public String getFinishVoucher() {
		return finishVoucher;
	}

	public void setFinishVoucher(String finishVoucher) {
		this.finishVoucher = finishVoucher;
	}

	public Employee getCustomerService() {
		return customerService;
	}

	public void setCustomerService(Employee customerService) {
		this.customerService = customerService;
	}

	public Integer getCustomerServiceId() {
		return customerServiceId;
	}

	public void setCustomerServiceId(Integer customerServiceId) {
		this.customerServiceId = customerServiceId;
	}

	public OrderAuditState getGroupLeaderAuditState() {
		return groupLeaderAuditState;
	}

	public void setGroupLeaderAuditState(OrderAuditState groupLeaderAuditState) {
		this.groupLeaderAuditState = groupLeaderAuditState;
	}

	public Employee getLeader() {
		return leader;
	}

	public void setLeader(Employee leader) {
		this.leader = leader;
	}

	public Integer getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Integer leaderId) {
		this.leaderId = leaderId;
	}

	public OrderAuditState getVpAuditState() {
		return vpAuditState;
	}

	public void setVpAuditState(OrderAuditState vpAuditState) {
		this.vpAuditState = vpAuditState;
	}

	public Employee getVicePresident() {
		return vicePresident;
	}

	public void setVicePresident(Employee vicePresident) {
		this.vicePresident = vicePresident;
	}

	public Integer getVicePresidentId() {
		return vicePresidentId;
	}

	public void setVicePresidentId(Integer vicePresidentId) {
		this.vicePresidentId = vicePresidentId;
	}
}
