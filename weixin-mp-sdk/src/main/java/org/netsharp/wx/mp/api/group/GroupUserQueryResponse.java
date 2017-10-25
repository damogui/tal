package org.netsharp.wx.mp.api.group;

import org.netsharp.wx.mp.api.Response;

public class GroupUserQueryResponse extends Response
{
	private String privateGroupId;
	public final String getGroupId()
	{
		return privateGroupId;
	}
	public final void setGroupId(String value)
	{
		privateGroupId = value;
	}
}