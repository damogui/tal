package com.gongsibao.taurus.api;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.TmdescApi;
import com.gongsibao.taurus.entity.Tmdesc;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.util.json.JacksonObjectMapper;

public class TmdescTest{

	String companyName = "北京百度网讯科技有限公司";

	ObjectMapper mapper = new JacksonObjectMapper();

	String json = "";
	
	@Test
	public void Tmdesc(){
		
		System.out.println("商标详情：");
		TmdescApi api = ApiFactory.create(TmdescApi.class);
		api.setCompanyName(companyName);
		api.setTmId(5487532);
		ResponseMessage<Tmdesc> response = api.getResponse();
		if(response != null){

			System.out.println(response.getResult()+"："+response.getResultMsg());
		}
		System.err.println("/**************************************************/");
	} 
}
