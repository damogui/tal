package org.netsharp.wx.qy.message.send;

import org.netsharp.wx.qy.Response;

public class SendMessageResponse extends Response {
	
	private String invaliduser;
	private String invalidparty;
	private String invalidtag;
	
	public String getInvaliduser() {
		return invaliduser;
	}
	public void setInvaliduser(String invaliduser) {
		this.invaliduser = invaliduser;
	}
	public String getInvalidparty() {
		return invalidparty;
	}
	public void setInvalidparty(String invalidparty) {
		this.invalidparty = invalidparty;
	}
	public String getInvalidtag() {
		return invalidtag;
	}
	public void setInvalidtag(String invalidtag) {
		this.invalidtag = invalidtag;
	}
	
	
}
