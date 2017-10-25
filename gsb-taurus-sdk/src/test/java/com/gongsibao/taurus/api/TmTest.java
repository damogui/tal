package com.gongsibao.taurus.api;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.TmApi;
import com.gongsibao.taurus.entity.Tm;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.util.json.JacksonObjectMapper;

public class TmTest {
	

	String companyName = "北京百度网讯科技有限公司";

	ObjectMapper mapper = new JacksonObjectMapper();

	String json = "";
	@Test
	public void Tm(){
		
		System.out.println("商标：");
		TmApi api = ApiFactory.create(TmApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<Tm> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
