package com.gongsibao.tools.db;

import java.util.Random;

import org.junit.Test;

public class AuditTest {

	@Test
	public void run() {
//		String content = String.format("您好，公司宝业务员“%s”已为您开通会员，账号是您当前手机号，密码：“%s”。后续所有服务通知会发送给您，此消息为系统发息为系统发送，请勿回复，有问题请联系您对应的业务员。", "韩伟","123456");
//		SmsHelper.send("13301503086", content);
		
		int randomPwd = Math.abs(new Random(7).nextInt());
		System.out.println(randomPwd);
	}

}
