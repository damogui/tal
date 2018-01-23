package com.gongsibao.panda.igirl;

import java.lang.reflect.Method;

import org.junit.Test;
import org.netsharp.panda.annotation.Authorization;
import org.netsharp.util.ReflectManager;
import com.gongsibao.igirl.web.TradeMarkCaseController;

public class AnnoTest {
	@Test
	public void anTest() {
		TradeMarkCaseController tmc=new TradeMarkCaseController();
		Method method = ReflectManager.getMethods(TradeMarkCaseController.class,"TradeMarkCaseController");
		method.getAnnotation(Authorization.class);
	}

}
