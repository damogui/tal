package com.gongsibao.entity.taurus;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.taurus.dic.DingtalkKeywordStatus;

@Table(name = "jnz_user_dingtalk_keyword",orderBy="pkid DESC")
public class UserDingtalkKeyword extends BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1766348187971203598L;
	
	@JsonIgnore
	@Reference(foreignKey="userId")
    private User user;
	
	@Column(name="user_id",header="用户Id")
	private Integer userId;
	
	private String keyword;
	
	//0:正常 1:删除
	private DingtalkKeywordStatus status = DingtalkKeywordStatus.NORMAL;
	
	@Column(name = "company_name", header = "消费公司名称")
	private String companyName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
