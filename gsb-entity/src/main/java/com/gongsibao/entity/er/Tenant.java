package com.gongsibao.entity.er;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_tenant")
public class Tenant extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -2992732235832408575L;
	@Column(name="company_name",header="CompanyName")
    private String companyName;
    @Column(name="max_account",header="MaxAccount")
    private Integer maxAccount;
    @Column(name="manager_id",header="ManagerId")
    private Integer managerId;
    @Column(header="domain")
    private String domain;
    @Column(name="is_enabled",header="IsEnabled")
    private Integer isEnabled;
    @Column(name="use_role_group",header="UseRoleGroup")
    private Integer useRoleGroup;
    @Column(name="use_order_group",header="UseOrderGroup")
    private Integer useOrderGroup;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(name="add_time",header="AddTime")
    private Date addTime;

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public Integer getMaxAccount() {
        return maxAccount;
    }
    public void setMaxAccount(Integer maxAccount) {
        this.maxAccount = maxAccount;
    }
    public Integer getManagerId() {
        return managerId;
    }
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
    public Integer getUseRoleGroup() {
        return useRoleGroup;
    }
    public void setUseRoleGroup(Integer useRoleGroup) {
        this.useRoleGroup = useRoleGroup;
    }
    public Integer getUseOrderGroup() {
        return useOrderGroup;
    }
    public void setUseOrderGroup(Integer useOrderGroup) {
        this.useOrderGroup = useOrderGroup;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}