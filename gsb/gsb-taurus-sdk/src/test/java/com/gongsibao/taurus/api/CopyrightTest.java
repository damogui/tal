package com.gongsibao.taurus.api;

import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.CopyrightApi;
import com.gongsibao.taurus.entity.Copyright;
import com.gongsibao.taurus.message.ResponseMessage;

public class CopyrightTest extends ApiTest{
	
	@Test
	public void Copyright(){
		
		System.out.println("软件著作权  ：");
		CopyrightApi api = ApiFactory.create(CopyrightApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<Copyright> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
