package com.gongsibao.panda.igirl;
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
		String s=cmd.get("new_order_273962");
		System.out.println(s);
		cmd.close();
		
//		String st=cmd.get("new_order_273962");
//		System.out.println(st);
//		for(String s :st) {
//			System.out.println(s);
//		}
	}


}
