package org.netsharp.wx.mp.message.response;

import org.netsharp.wx.mp.message.RequestMessage;
import org.netsharp.wx.mp.message.ResponseMessage;

/** 
 多客服回复
*/
public class McsResponse extends ResponseMessage
{
	public McsResponse()
	{
		super("transfer_customer_service");

	}

	public McsResponse(RequestMessage message)
	{
		super("transfer_customer_service", message);

	}
}