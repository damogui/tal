package com.gongsibao.tools.temp;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.wx.ea.base.IEaMessageService;

public class QyWeiXinTest {

	@Test
	public void run(){
		
//		IEaMessageService wxpa = ServiceFactory.create(IEaMessageService.class);
//		wxpa.send("SCRUM", "这是一条测试消息", "XuFangBo|HanWei|ChenHeng|LiLi|LiuYang|CuiXueQiang");
		

		BigDecimal value = new BigDecimal(0.00356);
		String v = "";
		if (value != null) {

			BigDecimal result = value.divide(new BigDecimal(1), 4, BigDecimal.ROUND_HALF_UP);
			DecimalFormat decimalFormat = new DecimalFormat("0.0000");
			v = decimalFormat.format(result);
		}
		System.out.println(v);
	}
	
}
