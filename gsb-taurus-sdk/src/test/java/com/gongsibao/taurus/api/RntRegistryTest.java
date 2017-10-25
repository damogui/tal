package com.gongsibao.taurus.api;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.EntRegistryApi;
import com.gongsibao.taurus.entity.EntRegistry;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.util.json.JacksonObjectMapper;

public class RntRegistryTest {

	String companyName = "";

	ObjectMapper mapper = new JacksonObjectMapper();

	String json = "";
	@Test
	public void rntRegistry(){
		
		System.out.println("注册信息：");
		EntRegistryApi api = ApiFactory.create(EntRegistryApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntRegistry> response = api.getResponse();
		System.out.println();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
