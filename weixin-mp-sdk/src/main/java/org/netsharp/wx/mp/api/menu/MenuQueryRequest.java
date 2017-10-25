package org.netsharp.wx.mp.api.menu;

import org.netsharp.core.NetsharpException;
import org.netsharp.wx.mp.api.Request;

//[Api("查询自定义菜单")]
public class MenuQueryRequest extends Request<MenuQueryResponse>
{
	@Override
	public String getUrl()
	{
		return String.format("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%1$s", this.getAccessToken());
	}

	@Override
	protected MenuQueryResponse doResponse() throws NetsharpException
	{
		return this.executeHttpGet();
	}
}