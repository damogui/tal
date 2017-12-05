package com.gongsibao.entity.uc;

import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.organization.dic.Gender;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.uc.dic.LoginValid;
import com.gongsibao.entity.uc.dic.SupplyStatus;
import com.gongsibao.entity.uc.dic.SupplyType;

@Table(name="uc_user",header="员工信息")
public class User extends BaseEntity {

	private static final long serialVersionUID = 6562120272245433851L;
	
	@Column(name="passwd",header="密码")
	private String passwd;
	
	@Column(name="ticket",header="凭证")
    private String ticket;
    
    @Column(name="real_name",header="姓名")
    private String name;
    
    @Column(name="email",header="邮箱")
    private String email;
    
    @Column(name="qq",header="QQ")
    private String qq;
    
    @Column(name="weixin",header="微信号")
    private String weixin;
    
    @Column(name="mobile_phone",header="手机号")
    private String mobilePhone;
    
    @Column(name="sex",header="性别")
    private Gender sex;
    
    @Column(name="ability_id",header="服务能力 107 1071金牌，1072银牌，1073铜牌，1074普通")
    private Integer abilityId;
    
    @Reference(foreignKey="abilityId")
    private Dict abbility;
    
    @Column(name="priority_id",header="分配优先级 109 1091金牌，1092银牌，1073铜牌，1094普通")
    private Integer priorityId;
    
    @Reference(foreignKey="priorityId")
    private Dict priority;
    
    @Column(name="office_id",header="分公司id")
    private Integer officeId;
    
    @Reference(foreignKey="officeId")
    private Organization office;
    
    @Column(name="is_inner",header="是否内部员工 0否, 1是")
    private Boolean isInner = true;
    
    @Column(name="head_thumb_file_id",header="头像图片序号")
    private Integer headThumbFileId;
    
    @Column(name="user_type_id",header="用户类型序号，type=2")
    private Integer userTypeId;
    
    @Column(name="is_enabled",header="是否启用 0否 1是")
    private Boolean enabled;

    @Column(name="is_bbk",header="是否八百客")
    private String isBbk="0";

    @Column(name="is_accept_order",header="是否接单 0不接单 1接单")
    private Boolean isAcceptOrder = false;
    
    @Column(name="remark",header="备注")
    private String remark;
    
    @Column(name="supply_status",header="供应商审核状态 1、初次申请 2 等待审核 3审核驳回 4 审核通过 5审核通过后修改-等待审核, 6信息修改审核驳回")
    private SupplyStatus supplyStatus;
    
    @Column(name="supply_reject_reason",header="供应商审核驳回原因")
    private String supplyRejectReason;
    
    @Column(name="supply_type",header="供应商类型 0 无, 1 cp, 2 sp")
    private SupplyType supplyType;
    
    @Column(name="login_valid",header="登录验证 1ukey, 2短信, 3都验证")
    private LoginValid loginValid;
    
    @Column(name="ukey_pid",header="Ukey序列号")
    private String ukeyPid;
    
    @Column(name="pub_key",header="Ukey公钥")
    private String pubKey;
    
    @Subs(subType=UserBusiness.class,foreignKey="userId",header="归属事业部")
    private List<UserBusiness> business;

    @Subs(subType=UserRoleMap.class,foreignKey="userId",header="角色")
    private List<UserRoleMap> roles;
    
    @Subs(subType=UserOrganizationMap.class,foreignKey="userId",header="所属组织机构")
    private List<UserOrganizationMap> organizations;

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}

	public Integer getAbilityId() {
		return abilityId;
	}

	public void setAbilityId(Integer abilityId) {
		this.abilityId = abilityId;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public Organization getOffice() {
		return office;
	}

	public void setOffice(Organization office) {
		this.office = office;
	}

	public Boolean getIsInner() {
		return isInner;
	}

	public void setIsInner(Boolean isInner) {
		this.isInner = isInner;
	}

	public Integer getHeadThumbFileId() {
		return headThumbFileId;
	}

	public void setHeadThumbFileId(Integer headThumbFileId) {
		this.headThumbFileId = headThumbFileId;
	}

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getIsBbk() {
		return isBbk;
	}

	public void setIsBbk(String isBbk) {
		this.isBbk = isBbk;
	}

	public Boolean getIsAcceptOrder() {
		return isAcceptOrder;
	}

	public void setIsAcceptOrder(Boolean isAcceptOrder) {
		this.isAcceptOrder = isAcceptOrder;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public SupplyStatus getSupplyStatus() {
		return supplyStatus;
	}

	public void setSupplyStatus(SupplyStatus supplyStatus) {
		this.supplyStatus = supplyStatus;
	}

	public String getSupplyRejectReason() {
		return supplyRejectReason;
	}

	public void setSupplyRejectReason(String supplyRejectReason) {
		this.supplyRejectReason = supplyRejectReason;
	}

	public SupplyType getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(SupplyType supplyType) {
		this.supplyType = supplyType;
	}

	public LoginValid getLoginValid() {
		return loginValid;
	}

	public void setLoginValid(LoginValid loginValid) {
		this.loginValid = loginValid;
	}

	public String getUkeyPid() {
		return ukeyPid;
	}

	public void setUkeyPid(String ukeyPid) {
		this.ukeyPid = ukeyPid;
	}

	public String getPubKey() {
		return pubKey;
	}

	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}

	public Dict getAbbility() {
		return abbility;
	}

	public void setAbbility(Dict abbility) {
		this.abbility = abbility;
	}

	public Dict getPriority() {
		return priority;
	}

	public void setPriority(Dict priority) {
		this.priority = priority;
	}

	public List<UserBusiness> getBusiness() {
		return business;
	}

	public void setBusiness(List<UserBusiness> business) {
		this.business = business;
	}

	public List<UserRoleMap> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRoleMap> roles) {
		this.roles = roles;
	}

	public List<UserOrganizationMap> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<UserOrganizationMap> organizations) {
		this.organizations = organizations;
	}
}