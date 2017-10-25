package org.netsharp.wx.mp.api.sendmessage;

import org.netsharp.wx.mp.api.Response;

public class SendMessageResponse extends Response
{
	private String privateMsg_Id;
	public final String getMsg_Id()
	{
		return privateMsg_Id;
	}
	public final void setMsg_Id(String value)
	{
		privateMsg_Id = value;
	}
}