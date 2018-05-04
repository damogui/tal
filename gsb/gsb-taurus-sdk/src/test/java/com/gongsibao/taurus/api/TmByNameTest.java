package com.gongsibao.taurus.api;

import com.gongsibao.taurus.entity.Tm;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.service.TaurusApiService;
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

		try {
			ResponseMessage<Tm> response = TaurusApiService.getTmByName("支付宝", 1, 10);
			System.err.println(response);
			System.out.println(response.getResult()+"："+response.getResultMsg());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("/**************************************************/");

	}
}
