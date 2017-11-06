package com.gongsibao.taurus.api;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.EntMemberApi;
import com.gongsibao.taurus.entity.EntMember;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.util.json.JacksonObjectMapper;

public class MemberTest {

	String companyName = "北京百度网讯科技有限公司";

	ObjectMapper mapper = new JacksonObjectMapper();

	@Test
	public void memberApi(){
		
		System.out.println("主要人员：");
		EntMemberApi api = ApiFactory.create(EntMemberApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntMember> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
