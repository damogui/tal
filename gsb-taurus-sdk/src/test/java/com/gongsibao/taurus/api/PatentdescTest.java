package com.gongsibao.taurus.api;

import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.PatentDescApi;
import com.gongsibao.taurus.entity.PatentDesc;
import com.gongsibao.taurus.message.ResponseMessage;

public class PatentdescTest  extends ApiTest{
	
	@Test
	public void Patentdesc(){
		
		System.out.println("专利详情：");
		PatentDescApi api = ApiFactory.create(PatentDescApi.class);
		api.setCompanyName(companyName);
		api.setPatentId("02124d88087b456e953a595d6512229c");
		ResponseMessage<PatentDesc> response = api.getResponse();
		if(response != null){

			System.out.println(response.getResult()+"："+response.getResultMsg());
		}
		System.err.println("/**************************************************/");
	} 
}
