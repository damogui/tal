package org.netsharp.wx.qy.user;

import org.netsharp.wx.qy.Request;

public class UserGetRequest extends Request<UserGetResponse> {

	private Integer userid;

	public UserGetRequest() {
		super();
		this.responseType = UserGetResponse.class;
	}

	@Override
	protected UserGetResponse doResponse() {

		UserGetResponse response = this.executeHttpGet();

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + this.getAccessToken() + "&userid=" + this.userid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}
