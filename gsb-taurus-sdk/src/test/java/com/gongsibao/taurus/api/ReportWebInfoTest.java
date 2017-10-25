package com.gongsibao.taurus.api;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.TmflowApi;
import com.gongsibao.taurus.entity.Tmflow;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.util.json.JacksonObjectMapper;

public class ReportWebInfoTest {

	String companyName = "北京百度网讯科技有限公司";

	ObjectMapper mapper = new JacksonObjectMapper();

	String json = "";
	
	@Test
	public void ReportWebInfo(){
		
		System.out.println("商标流程：");
		TmflowApi api = ApiFactory.create(TmflowApi.class);
		api.setTmId(18088745);
		ResponseMessage<Tmflow> response = api.getResponse();
		
		if(response != null){

			System.out.println(response.getResult()+"："+response.getResultMsg());
		}
		System.err.println("/**************************************************/");
	} 
}
