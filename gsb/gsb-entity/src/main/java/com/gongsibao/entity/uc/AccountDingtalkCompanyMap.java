package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_account_dingtalk_company_map")
public class AccountDingtalkCompanyMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -2069091657920880209L;
	@Column(name="account_dingtalk_id")
    private Integer accountDingtalkId;
    @Column(name="account_dingtalk_company_id")
    private Integer accountDingtalkCompanyId;
    @Column(name="corp_id")
    private String corpId;
    @Column(name="corp_name")
    private String corpName;
    @Column(name="is_current_login")
    private Integer isCurrentLogin;
    @Column(name="company_id")
    private Integer companyId;

    public Integer getAccountDingtalkId() {
        return accountDingtalkId;
    }
    public void setAccountDingtalkId(Integer accountDingtalkId) {
        this.accountDingtalkId = accountDingtalkId;
    }
    public Integer getAccountDingtalkCompanyId() {
        return accountDingtalkCompanyId;
    }
    public void setAccountDingtalkCompanyId(Integer accountDingtalkCompanyId) {
        this.accountDingtalkCompanyId = accountDingtalkCompanyId;
    }
    public String getCorpId() {
        return corpId;
    }
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }
    public String getCorpName() {
        return corpName;
    }
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }
    public Integer getIsCurrentLogin() {
        return isCurrentLogin;
    }
    public void setIsCurrentLogin(Integer isCurrentLogin) {
        this.isCurrentLogin = isCurrentLogin;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}