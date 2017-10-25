package com.gongsibao.taurus.api;

import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.EntChangeRecordApi;
import com.gongsibao.taurus.entity.EntChangeRecord;
import com.gongsibao.taurus.message.ResponseMessage;

public class EntChangeRecordTest extends ApiTest{

	
	@Test
	public void entChangeRecord(){
		
		System.out.println("企业变更记录：");
		EntChangeRecordApi api = ApiFactory.create(EntChangeRecordApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntChangeRecord> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
