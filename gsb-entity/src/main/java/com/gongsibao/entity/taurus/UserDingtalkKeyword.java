package com.gongsibao.entity.taurus;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.taurus.dic.DingtalkKeywordStatus;

@Table(name = "uc_account_dingtalk_keyword")
public class UserDingtalkKeyword extends BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1766348187971203598L;
	
	@Column(name = "account_id")
	private Integer accountId;
	
	private String keyword;
	
	//0:正常 1:删除
	private DingtalkKeywordStatus status = DingtalkKeywordStatus.NORMAL;
	
	@Column(name = "company_name", header = "消费公司名称")
	private String companyName;
	
    //用户类别（0：uc_account（会员用户），1:jnz_user（金牛座用户））

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public DingtalkKeywordStatus getStatus() {
		return status;
	}

	public void setStatus(DingtalkKeywordStatus status) {
		this.status = status;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
