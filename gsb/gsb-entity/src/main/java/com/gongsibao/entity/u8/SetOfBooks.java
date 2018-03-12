package com.gongsibao.entity.u8;

import java.math.BigDecimal;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.json.JsonTaxRateBigDecimalSerializer;
import com.gongsibao.entity.u8.dic.SetOfBooksType;

@Table(name = "u8_set_of_books")
public class SetOfBooks extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3718467057006336462L;

	@Column(name = "name", header = "名称")
	private String name;

	@Column(name = "sender_no", header = "外部系统编号")
	private String senderNo;

	@Column(name = "type", header = "类型（0：咨询 1：科技）")
	private SetOfBooksType type = SetOfBooksType.Zixun;

	@JsonSerialize(using = JsonTaxRateBigDecimalSerializer.class)
	@Column(name = "tax_rate", header = "税率")
	private BigDecimal taxRate;

	@Column(name = "sort", header = "排序编号")
	private Integer sort;

	@Column(name = "account_code", header = "科目编码")
	private String accountCode;

	@Column(name = "enter_name", header = "默认的制单人")
	private String enterName;

	@Column(name = "abbreviation", header = "简称")
	private String abbreviation;

	@Column(name = "is_enabled", header = "是否可用（0：不可用 1：可用）")
	private Boolean enabled = true;
	
	@Column(name = "is_create_voucher", header = "是否生成凭证")
	private Boolean isCreateVoucher = true;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSenderNo() {
		return senderNo;
	}

	public void setSenderNo(String senderNo) {
		this.senderNo = senderNo;
	}

	public SetOfBooksType getType() {
		return type;
	}

	public void setType(SetOfBooksType type) {
		this.type = type;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getEnterName() {
		return enterName;
	}

	public void setEnterName(String enterName) {
		this.enterName = enterName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getIsCreateVoucher() {
		return isCreateVoucher;
	}

	public void setIsCreateVoucher(Boolean isCreateVoucher) {
		this.isCreateVoucher = isCreateVoucher;
	}

}
