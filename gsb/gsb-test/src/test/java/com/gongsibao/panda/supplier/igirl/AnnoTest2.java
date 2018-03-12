package com.gongsibao.panda.supplier.igirl;
import org.junit.Test;
import org.netsharp.cache.service.redis.RedisCacheCommand;
import org.netsharp.cache.service.redis.RedisConnection;
public class AnnoTest2 {
	@Test
	public void testcache() {
		RedisConnection con=new RedisConnection();
		con.setDatabase(0);
		con.setIp("123.57.156.212");
		con.setPort(8967);
		con.setPassword("Gongsibao2018");
		RedisCacheCommand cmd=new RedisCacheCommand();
		cmd.open(con);
		cmd.set("new_order_275070","新单测试通知：张三,李四，您的客户马寅已经下单! 下单内容为：, 测试1，测试1，测试1，请及时跟进");
		cmd.close();
		
//		String st=cmd.get("new_order_273962");
//		System.out.println(st);
//		for(String s :st) {
//			System.out.println(s);
//		}
	}

	

}
