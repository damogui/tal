package com.gongsibao.tools.hw;

import org.junit.Test;
import org.netsharp.util.EncrypUtil;

public class Md5Test {

	@Test
	public void run(){
		
		//gongsibao
		//18f274f4c8388ff3
		
		String pwdMD5 = EncrypUtil.md5("gongsibao" + "abc!@#123");
        pwdMD5 = pwdMD5.substring(8);
        pwdMD5 = pwdMD5.substring(0, 16);
		System.out.println(pwdMD5);
	}
}
