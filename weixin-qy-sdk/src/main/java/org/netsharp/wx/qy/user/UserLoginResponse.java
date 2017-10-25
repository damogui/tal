package org.netsharp.wx.qy.user;

import org.netsharp.wx.qy.Response;

public class UserLoginResponse extends Response {
	private Integer usertype;
	private User user_info;

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public User getUser_info() {
		return user_info;
	}

	public void setUser_info(User user_info) {
		this.user_info = user_info;
	}
}
