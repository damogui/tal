package com.gongsibao.taurus.api;

import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.JudgmentApi;
import com.gongsibao.taurus.entity.Judgment;
import com.gongsibao.taurus.message.ResponseMessage;

public class JudgmentTest extends ApiTest{

	
	@Test
	public void judgment(){
		
		System.out.println("法院判决：");
		JudgmentApi api = ApiFactory.create(JudgmentApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<Judgment> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
