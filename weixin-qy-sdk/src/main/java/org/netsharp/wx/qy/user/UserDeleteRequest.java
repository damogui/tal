package org.netsharp.wx.qy.user;

import org.netsharp.wx.qy.Request;

public class UserDeleteRequest extends Request<UserDeleteResponse> {

	private Integer userid;

	public UserDeleteRequest() {
		super();
		this.responseType = UserDeleteResponse.class;
	}

	@Override
	protected UserDeleteResponse doResponse() {

		UserDeleteResponse response = this.executeHttpGet();

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=" + this.getAccessToken() + "&userid=" + this.getUserid();
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}
