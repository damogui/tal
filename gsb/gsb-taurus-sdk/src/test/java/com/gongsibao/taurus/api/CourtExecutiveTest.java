package com.gongsibao.taurus.api;

import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.CourtExecutiveApi;
import com.gongsibao.taurus.entity.CourtExecutive;
import com.gongsibao.taurus.message.ResponseMessage;

public class CourtExecutiveTest extends ApiTest{

	@Test
	public void CourtExecutive(){
		
		System.out.println("被执行人信息：");
		CourtExecutiveApi api = ApiFactory.create(CourtExecutiveApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<CourtExecutive> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
