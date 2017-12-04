package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.uc.dic.UserLoginLogType;

@Table(name = "uc_login_log", header = "用户登录日志")
public class LoginLog extends BaseEntity {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1197593555885954676L;
	@Column(name = "user_id", header = "")
	private Integer userId;

	@Reference(foreignKey = "userId")
	private User user;

	@Column(name = "type", header = "0 前端用户, 1 后端用户 2 钉钉用户登录")
	private UserLoginLogType type;

	@Column(name = "status", header = "状态：0 成功 1失败")
	private Integer status;

	@Column(name = "ip", header = "ip")
	private String ip;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserLoginLogType getType() {
		return type;
	}

	public void setType(UserLoginLogType type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}