package com.gongsibao.taurus.api;

import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.EntInvestApi;
import com.gongsibao.taurus.entity.EntInvest;
import com.gongsibao.taurus.message.ResponseMessage;

public class EntInvestTest extends ApiTest{

	@Test
	public void entInvest(){
		
		System.out.println("对外投资：");
		EntInvestApi api = ApiFactory.create(EntInvestApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntInvest> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
	
}
