package com.gongsibao.taurus.api;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.EntBranchApi;
import com.gongsibao.taurus.entity.EntBranch;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.util.json.JacksonObjectMapper;

public class EntBranchTest {
	
	String companyName = "北京百度网讯科技有限公司";

	ObjectMapper mapper = new JacksonObjectMapper();

	String json = "";
	@Test
	public void entBranch(){
		
		System.out.println("分支机构：");
		EntBranchApi api = ApiFactory.create(EntBranchApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntBranch> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
