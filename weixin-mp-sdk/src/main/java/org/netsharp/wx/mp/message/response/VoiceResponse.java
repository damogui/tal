package org.netsharp.wx.mp.message.response;

import org.netsharp.wx.mp.message.RequestMessage;
import org.netsharp.wx.mp.message.ResponseMessage;

public class VoiceResponse extends ResponseMessage
{
	private String mediaId;
	
	public VoiceResponse()
	{
		super("voice");
	}
	
	public final String getMediaId()
	{
		return mediaId;
	}
	public final void setMediaId(String value)
	{
		mediaId = value;
	}

	public VoiceResponse(RequestMessage message)
	{
		super("voice", message);

	}

	@Override
	protected String InnerToxml()
	{
		String baseXml = super.InnerToxml();

		return baseXml + "<Voice><MediaId>" + this.getMediaId() + "</MediaId></Voice>";
	}
}