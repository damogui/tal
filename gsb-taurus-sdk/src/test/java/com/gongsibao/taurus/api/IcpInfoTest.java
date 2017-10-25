package com.gongsibao.taurus.api;

import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.IcpInfoApi;
import com.gongsibao.taurus.entity.IcpInfo;
import com.gongsibao.taurus.message.ResponseMessage;

public class IcpInfoTest extends ApiTest{
	
	@Test
	public void IcpInfo(){
		
		System.out.println("ICP 信息  ：");
		IcpInfoApi api = ApiFactory.create(IcpInfoApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<IcpInfo> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
