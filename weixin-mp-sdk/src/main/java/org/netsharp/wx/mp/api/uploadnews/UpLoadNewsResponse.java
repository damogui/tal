package org.netsharp.wx.mp.api.uploadnews;

import org.netsharp.wx.mp.api.Response;

public class UpLoadNewsResponse extends Response
{
	private String privateType;
	public final String getType()
	{
		return privateType;
	}
	public final void setType(String value)
	{
		privateType = value;
	}

	private String privateMedia_id;
	public final String getMedia_id()
	{
		return privateMedia_id;
	}
	public final void setMedia_id(String value)
	{
		privateMedia_id = value;
	}

	private String privateCreated_at;
	public final String getCreated_at()
	{
		return privateCreated_at;
	}
	public final void setCreated_at(String value)
	{
		privateCreated_at = value;
	}
}