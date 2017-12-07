package com.gongsibao.entity.u8;

import java.math.BigDecimal;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.u8.dic.U8BankType;

@Table(name = "u8_bank")
public class U8Bank extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5583771741613667323L;

	@Column(name = "name", header = "银行/科目名称")
	private String name;

	@Column(name = "no", header = "卡号")
	private String no;

	@Column(name = "code", header = "科目编号")
	private String code;

	@Column(name = "abbreviation", header = "简称")
	private String abbreviation;

	@Column(name = "set_of_books_id", header = "账套id")
	private Integer setOfBooksId;

	@Reference(foreignKey = "setOfBooksId")
	private SetOfBooks setOfBooks;

	@Column(name = "type", header = "0支付方式科目 1业务项科目")
	private U8BankType type = U8BankType.Zhifu;

	@Column(name = "sort", header = "排序编号")
	private Integer sort;

	@Column(name = "is_enabled", header = "是否可用（0：否 1：是）")
	private Boolean enabled = true;

	@Column(name = "supplier_id", header = "u8供应商id")
	private String supplierId;

	//自连id
	@Column(name = "prepay_subject_id", header = "预付科目id")
	private Integer prepaySubjectId;

	@Reference(foreignKey = "prepaySubjectId")
	private U8Bank prepaySubject;
	
	@Column(name = "personnel_id", header = "u8人员id(个人id)")
	private String personnelId;

	@Column(name = "dept_id", header = "u8部门id")
	private String deptId;

	@Column(name = "tax_rate", header = "供应商税率")
	private BigDecimal taxRate;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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

	public U8BankType getType() {
		return type;
	}

	public void setType(U8BankType type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getPrepaySubjectId() {
		return prepaySubjectId;
	}

	public void setPrepaySubjectId(Integer prepaySubjectId) {
		this.prepaySubjectId = prepaySubjectId;
	}

	public U8Bank getPrepaySubject() {
		return prepaySubject;
	}

	public void setPrepaySubject(U8Bank prepaySubject) {
		this.prepaySubject = prepaySubject;
	}

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
}
