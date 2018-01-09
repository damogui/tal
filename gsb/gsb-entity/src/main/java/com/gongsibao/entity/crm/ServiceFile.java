package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.crm.dic.NotifiedType;

@Table(name = "service_file", header = "服务商档案")
public class ServiceFile extends BaseEntity{

	private static final long serialVersionUID = 6562120272245433851L;
	@Column(name = "service_name", header = "服务商名称")
	private String serviceName;

	@Column(name = "address", header = "地址")
	private String address;

	@Column(name = "mobile_phone", header = "手机号（账号）")
	private String mobilePhone;

	@Column(name = "is_proprietary", header = "是否自营  0否, 1是")
	private Boolean isProprietary = true;

	@Column(name = "customer_pool_number", header = "客户池数量")
	private Integer customerPoolNumber;

	@Column(name = "is_push_report", header = "是否推送报表  0否, 1是")
	private Boolean isPushReport = true;
	
	@Column(name = "message_notified_type", header = "消息通知类型 ")
	private NotifiedType messageNotifiedType=NotifiedType.Wx;
	
	@Column(name = "is_auto_assign", header = "是否推自动分配  0否, 1是")
	private Boolean isAutoAssign = true;
	
	@Column(name = "is_auto_release", header = "是否推自动释放  0否, 1是")
	private Boolean isAutoRelease = true;
	
	@Column(name = "no_follow_days", header = "未跟进天数释放")
	private Integer noFollowDays;
	
	@Column(name = "is_enable_depart", header = "是否启用部门  0否, 1是")
	private Boolean isEnableDepart = true;

	@Column(name = "depart_level", header = "部门级次")
	private Integer departLevel;

	
	
	
	
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Boolean getIsProprietary() {
		return isProprietary;
	}

	public void setIsProprietary(Boolean isProprietary) {
		this.isProprietary = isProprietary;
	}

	public Integer getCustomerPoolNumber() {
		return customerPoolNumber;
	}

	public void setCustomerPoolNumber(Integer customerPoolNumber) {
		this.customerPoolNumber = customerPoolNumber;
	}

	public Boolean getIsPushReport() {
		return isPushReport;
	}

	public void setIsPushReport(Boolean isPushReport) {
		this.isPushReport = isPushReport;
	}

	
	public NotifiedType getMessageNotifiedType() {
		return messageNotifiedType;
	}

	public void setMessageNotifiedType(NotifiedType messageNotifiedType) {
		this.messageNotifiedType = messageNotifiedType;
	}

	public Boolean getIsAutoAssign() {
		return isAutoAssign;
	}

	public void setIsAutoAssign(Boolean isAutoAssign) {
		this.isAutoAssign = isAutoAssign;
	}

	public Boolean getIsAutoRelease() {
		return isAutoRelease;
	}

	public void setIsAutoRelease(Boolean isAutoRelease) {
		this.isAutoRelease = isAutoRelease;
	}

	public Integer getNoFollowDays() {
		return noFollowDays;
	}

	public void setNoFollowDays(Integer noFollowDays) {
		this.noFollowDays = noFollowDays;
	}

	public Boolean getIsEnableDepart() {
		return isEnableDepart;
	}

	public void setIsEnableDepart(Boolean isEnableDepart) {
		this.isEnableDepart = isEnableDepart;
	}

	public Integer getDepartLevel() {
		return departLevel;
	}

	public void setDepartLevel(Integer departLevel) {
		this.departLevel = departLevel;
	}
}
