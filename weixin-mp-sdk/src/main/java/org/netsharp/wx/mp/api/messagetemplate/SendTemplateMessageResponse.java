package org.netsharp.wx.mp.api.messagetemplate;

import org.netsharp.wx.mp.api.Response;

public class SendTemplateMessageResponse extends Response {
	
	private String msgid;

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	
}
