package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_user")
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
    
    @Column(name="mobilePhone",header="手机号")
    private String mobilePhone;
    
    @Column(name="sex",header="性别")
    private Integer sex;
    
    @Column(name="ability_id",header="业务员类型 107 1071金牌，1072银牌，1073铜牌，1074普通")
    private Integer abilityId;
    
    @Column(name="priority_id",header="业务员分配优先级 109 1091金牌，1092银牌，1073铜牌，1094普通")
    private Integer priorityId;
    
    @Column(name="office_id",header="分公司id")
    private Integer officeId;
    
    @Column(name="is_inner",header="是否内部员工 0否, 1是")
    private Integer isInner;
    
    @Column(name="head_thumb_file_id",header="头像图片序号")
    private Integer headThumbFileId;
    
    @Column(name="user_type_id",header="用户类型序号，type=2")
    private Integer userTypeId;
    
    @Column(name="is_enabled",header="是否启用 0否 1是")
    private Integer isEnabled;

    @Column(name="is_bbk",header="是否八百客")
    private String isBbk="0";

    @Column(name="is_accept_order",header="是否接单 0不接单 1接单")
    private Integer isAcceptOrder;
    
    @Column(name="remark",header="备注")
    private String remark;
    
    @Column(name="supply_status",header="供应商审核状态 1、初次申请 2 等待审核 3审核驳回 4 审核通过 5审核通过后修改-等待审核, 6信息修改审核驳回")
    private Integer supplyStatus;
    
    @Column(name="supply_reject_reason",header="供应商审核驳回原因")
    private String supplyRejectReason;
    
    @Column(name="supply_type",header="供应商类型 0 无, 1 cp, 2 sp")
    private Integer supplyType;
    
    @Column(name="login_valid",header="登录验证 1ukey, 2短信, 3都验证")
    private Integer loginValid;
    
    @Column(name="ukey_pid",header="ukey的pid")
    private String ukeyPid;
    
    @Column(name="pub_key",header="公钥")
    private String pubKey;

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
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
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
    public Integer getIsInner() {
        return isInner;
    }
    public void setIsInner(Integer isInner) {
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
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getIsBbk() {
        return isBbk;
    }
    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }

    public Integer getIsAcceptOrder() {
        return isAcceptOrder;
    }
    public void setIsAcceptOrder(Integer isAcceptOrder) {
        this.isAcceptOrder = isAcceptOrder;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getSupplyStatus() {
        return supplyStatus;
    }
    public void setSupplyStatus(Integer supplyStatus) {
        this.supplyStatus = supplyStatus;
    }
    public String getSupplyRejectReason() {
        return supplyRejectReason;
    }
    public void setSupplyRejectReason(String supplyRejectReason) {
        this.supplyRejectReason = supplyRejectReason;
    }
    public Integer getSupplyType() {
        return supplyType;
    }
    public void setSupplyType(Integer supplyType) {
        this.supplyType = supplyType;
    }
    public Integer getLoginValid() {
        return loginValid;
    }
    public void setLoginValid(Integer loginValid) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}