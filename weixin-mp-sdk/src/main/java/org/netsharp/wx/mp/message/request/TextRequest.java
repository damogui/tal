package org.netsharp.wx.mp.message.request;

import org.netsharp.wx.mp.message.RequestMessage;

public class TextRequest extends RequestMessage
{
	private String content;
	
	public final String getContent()
	{
		return content;
	}
	public final void setContent(String value)
	{
		content = value;
	}

	@Override
	public String toString()
	{
		String baseString = super.toString();

		return baseString + ";content" + this.getContent();
	}
}