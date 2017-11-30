package com.gongsibao.panda;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.wx.ea.base.IEaMessageService;

public class QyWeiXinTest {

	@Test
	public void run(){
		
		IEaMessageService wxpa = ServiceFactory.create(IEaMessageService.class);
		wxpa.send("SCRUM", "这是一条测试消息", "XuFangBo|HanWei|ChenHeng|LiLi|LiuYang|CuiXueQiang");
	}
}
