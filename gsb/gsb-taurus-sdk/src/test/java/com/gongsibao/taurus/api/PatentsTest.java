package com.gongsibao.taurus.api;

import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.PatentsApi;
import com.gongsibao.taurus.entity.Patents;
import com.gongsibao.taurus.message.ResponseMessage;

public class PatentsTest extends ApiTest{
	@Test
	public void Patents(){
		
		System.out.println("专利信息  ：");
		PatentsApi api = ApiFactory.create(PatentsApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<Patents> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
