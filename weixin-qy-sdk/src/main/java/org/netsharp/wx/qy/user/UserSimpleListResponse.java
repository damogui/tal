package org.netsharp.wx.qy.user;

import java.util.List;

import org.netsharp.wx.qy.Response;

public class UserSimpleListResponse extends Response {
	
	private List<UserSimple> userlist;

	public List<UserSimple> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<UserSimple> userlist) {
		this.userlist = userlist;
	}
}

