package com.gongsibao.entity.taurus;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name="jnz_user_company_industry",header="用户不同的公司之间选择的行业之间的关系")
public class UserCompanyIndustry extends BaseEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -2054133744661138418L;

	@Reference(foreignKey="userId")
    private User user;
    
    @Column(name="user_id",header="用户Id")
    private Integer userId;
    
	@Column(name="industr_id")
	private Integer industrId;
	
	@Reference(foreignKey="industrId",header="行业")
	private Dict industr;
	
	@Column(name = "company_name", header = "消费公司名称")
	private String companyName;
	
	@Column(name = "company_id", header = "消费公司ID")
	private String company_Id;
	
	@Column(name = "remark",size=200, header = "说明")
	private String remark;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIndustrId() {
		return industrId;
	}

	public void setIndustrId(Integer industrId) {
		this.industrId = industrId;
	}

	public Dict getIndustr() {
		return industr;
	}

	public void setIndustr(Dict industr) {
		this.industr = industr;
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
}
