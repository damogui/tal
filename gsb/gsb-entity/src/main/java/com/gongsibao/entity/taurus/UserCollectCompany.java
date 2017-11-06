package com.gongsibao.entity.taurus;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.taurus.dic.AttentionStatus;

@Table(name="jnz_user_opt_company_map",orderBy="pkid DESC",header="用户和公司的操作记录表")
public class UserCollectCompany extends BaseEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4591296237420533150L;

	@JsonIgnore
	@Reference(foreignKey="userId")
    private User user;
    
    @Column(name="user_id",header="用户Id")
    private Integer userId;
    
	@Column(name="sign_status",header="消费公司是否标记:0关闭没有标记1打开已标记")
	private AttentionStatus status = AttentionStatus.UNATTENTION;

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

	public AttentionStatus getStatus() {
		return status;
	}

	public void setStatus(AttentionStatus status) {
		this.status = status;
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
