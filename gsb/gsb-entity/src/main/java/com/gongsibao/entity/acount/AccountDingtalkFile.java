package com.gongsibao.entity.acount;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.uc.dic.DingtalkFileType;
import com.gongsibao.entity.yj.Company;

@Table(name="uc_account_dingtalk_file",header="钉钉用户文件")
public class AccountDingtalkFile extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4437525361302235641L;
	@Column(name="file_id",header="文件id")
    private Integer fileId;
	
    @Column(name="account_id",header="会员id")
    private Integer accountId;
    
	@Reference(foreignKey="accountId",header="合作帐号")
	private Account account;
    
    @Column(name="trademark_id",header="商标id")
    private Integer trademarkId;
    
    @Column(name="int_cls",header="商标大类编号")
    private Integer intCls;
    
    @Column(name="name",header="名称")
    private String name;
    
    @Column(name="company_id",header="企业id")
    private Integer companyId;
    
	@Reference(foreignKey="companyId",header="云聚企业")
	private Company company;
    
    @Column(name="type",header="0:企业档案、1：知产档案")
    private DingtalkFileType type;
    
    @Column(name="category",header="档案类型，字典id")
    private Integer categoryId;

	@Reference(foreignKey="categoryId",header="档案类型")
	private Dict category;
	
	

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Integer getTrademarkId() {
		return trademarkId;
	}

	public void setTrademarkId(Integer trademarkId) {
		this.trademarkId = trademarkId;
	}

	public Integer getIntCls() {
		return intCls;
	}

	public void setIntCls(Integer intCls) {
		this.intCls = intCls;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public DingtalkFileType getType() {
		return type;
	}

	public void setType(DingtalkFileType type) {
		this.type = type;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Dict getCategory() {
		return category;
	}

	public void setCategory(Dict category) {
		this.category = category;
	}
}