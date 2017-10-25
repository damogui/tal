package org.netsharp.wx.mp.message;

import org.netsharp.core.NetsharpException;
import org.netsharp.wx.mp.api.accesstoken.AccessToken;

public interface IMessageListener{
	
	/*处理请求，得到回复*/
	ResponseMessage processRequest(RequestMessage request) throws NetsharpException;
	
	/*得到公众号的Token*/
	String getToken(String oid);
	
	AccessToken getAccessToken(String oid);
}