package com.gongsibao.taurus.api;

import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.TmdescApi;
import com.gongsibao.taurus.entity.Tmdesc;
import com.gongsibao.taurus.message.ResponseMessage;

public class TmdescTest extends ApiTest{

	
	@Test
	public void Tmdesc(){
		
		System.out.println("商标详情：");
		TmdescApi api = ApiFactory.create(TmdescApi.class);
		api.setCompanyName(companyName);
		api.setTmId(13792973);
		ResponseMessage<Tmdesc> response = api.getResponse();
		if(response != null){

			System.out.println(response.getResult()+"："+response.getResultMsg());
		}
		System.err.println("/**************************************************/");
	} 
}
