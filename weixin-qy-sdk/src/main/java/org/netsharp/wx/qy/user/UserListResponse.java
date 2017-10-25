package org.netsharp.wx.qy.user;

import java.util.List;

import org.netsharp.wx.qy.Response;

public class UserListResponse extends Response {
	
	private List<User> userlist;

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
}
