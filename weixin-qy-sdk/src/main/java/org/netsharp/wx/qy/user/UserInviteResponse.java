package org.netsharp.wx.qy.user;

import org.netsharp.wx.qy.Response;

public class UserInviteResponse extends Response {
	
	private Integer type;//1:微信邀请 2.邮件邀请

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
