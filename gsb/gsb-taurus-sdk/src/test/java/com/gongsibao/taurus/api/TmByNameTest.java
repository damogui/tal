package com.gongsibao.taurus.api;

import com.gongsibao.taurus.entity.Tm;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.util.json.JacksonObjectMapper;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class TmByNameTest {
	

	String companyName = "汉唐信通（北京）咨询股份有限公司";

	ObjectMapper mapper = new JacksonObjectMapper();

	String json = "";
	@Test
	public void TmByName(){
		System.out.println("商标：");
		TmByNameApi api = ApiFactory.create(TmByNameApi.class);
		api.setName("支付宝");
		ResponseMessage<Tm> response = api.getResponse();
		
		try {
			json = mapper.writeValueAsString(response);
			System.err.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
