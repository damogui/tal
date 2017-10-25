package com.gongsibao.taurus.api;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.EntShareholderApi;
import com.gongsibao.taurus.entity.EntShareholder;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.util.json.JacksonObjectMapper;

public class EntshareholderTest {
	
	String companyName = "北京百度网讯科技有限公司";
	ObjectMapper mapper = new JacksonObjectMapper();
	@Test
	public void entshareholder(){
		
		System.out.println("股东信息：");
		EntShareholderApi api = ApiFactory.create(EntShareholderApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntShareholder> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
