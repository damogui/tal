package com.gongsibao.entity.taurus;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.taurus.dic.PaymentType;
import com.gongsibao.entity.taurus.dic.WalletType;

@Table(name="jnz_user_wallet_log",orderBy="pkid DESC",header="用户钱包日志")
public class UserWalletLog extends BaseEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -1129870239055750903L;

	@JsonIgnore
	@Reference(foreignKey="userId")
    private User user;
	
	@Column(name="user_id",header="用户Id")
	private Integer userId;
	
	@Column(name="payment_no",header="支付流水号")
	private String paymentNo;
	
	@Column(name="code",header="编号")
	private String code;
	
	@Column(name="price",header="金额")
	private Integer price;
	
	@Column(name="type",header="类型0充值1消费2活动优惠")
	private WalletType type;
	
	@Column(name="payment_type",header="支付类型:0未知, 1微信支付,2支付宝,3现金")
	private PaymentType paymentType;
	
	@Column(name = "company_name", header = "消费公司名称")
	private String companyName;
	
	@Column(name = "company_id", header = "消费公司ID")
	private String company_Id;
	
	@Column(name = "remark",size=200, header = "说明")
	private String remark;
	
	@Column(name = "undone",size=200, header = "是否已撤销")
	private Boolean undone;
	
	@Exclusive
	@Column(name="discount_amount",header="赠送金额")
	private Integer discountAmount;
	
	
	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public WalletType getType() {
		return type;
	}

	public void setType(WalletType type) {
		this.type = type;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompany_Id() {
		return company_Id;
	}

	public void setCompany_Id(String company_Id) {
		this.company_Id = company_Id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Boolean getUndone() {
		return undone;
	}

	public void setUndone(Boolean undone) {
		this.undone = undone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}
}
