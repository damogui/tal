/**
 * 用户行为日志表
 */
package com.gongsibao.entity.bd;

import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name = "bd_user_behavior_log ", header = "用户行为日志表")
public class BdUserBehaviorLog extends Persistable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Auto
	@Column(name = "pkid", header = "主键Id")
	private Integer id;

	@Column(name = "user_id", header = "用户Id")
	private Integer userId;

	@Column(name = "ip", header = "ip地址")
	private String ip;

	@Column(name = "behavior_type", header = "行为类别（登录注册、商机挖掘、订单支付、其他等）")
	private String behaviorType;

	@Column(name = "behavior_act", header = "行为动作（例如：用户检索）")
	private String behaviorAct;

	@Column(name = "behavior_position", header = "行为发生位置（例如url或类名，方法名）")
	private String behaviorPosition;

	@Column(name = "behavior_data", header = "行为数据（用户搜索，应保存搜索关键字）")
	private String behaviorData;

	@Column(name = "behavior_source_type", header = "行为来源类型（0-金牛座，1-双子座，2-钉钉 等）")
	private String behaviorSourceType;

	@Column(name = "creator", header = "创建人")
	private String creator;

	@Column(name = "add_time", header = "创建时间")
	private Date addTime;

	@Column(name = "updator", header = "修改人")
	private String updator;

	@Column(name = "upd_time", header = "修改时间")
	private Date updTime;

	@Column(name = "ext1", header = "预留字段1")
	private String ext1;

	@Column(name = "ext2", header = "预留字段2")
	private String ext2;

	@Column(name = "ext3", header = "预留字段3")
	private String ext3;
	
	@Column(name = "ext4", header = "预留字段4")
	private String ext4;

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBehaviorType() {
		return behaviorType;
	}

	public void setBehaviorType(String behaviorType) {
		this.behaviorType = behaviorType;
	}

	public String getBehaviorAct() {
		return behaviorAct;
	}

	public void setBehaviorAct(String behaviorAct) {
		this.behaviorAct = behaviorAct;
	}

	public String getBehaviorPosition() {
		return behaviorPosition;
	}

	public void setBehaviorPosition(String behaviorPosition) {
		this.behaviorPosition = behaviorPosition;
	}

	public String getBehaviorData() {
		return behaviorData;
	}

	public void setBehaviorData(String behaviorData) {
		this.behaviorData = behaviorData;
	}

	public String getBehaviorSourceType() {
		return behaviorSourceType;
	}

	public void setBehaviorSourceType(String behaviorSourceType) {
		this.behaviorSourceType = behaviorSourceType;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	
}
