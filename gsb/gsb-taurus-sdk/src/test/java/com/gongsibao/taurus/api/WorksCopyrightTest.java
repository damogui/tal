package com.gongsibao.taurus.api;

import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.WorksCopyrightApi;
import com.gongsibao.taurus.entity.WorksCopyright;
import com.gongsibao.taurus.message.ResponseMessage;

public class WorksCopyrightTest  extends ApiTest{
	
	@Test
	public void WorksCopyright(){
		
		System.out.println("作品著作权：");
		WorksCopyrightApi api = ApiFactory.create(WorksCopyrightApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<WorksCopyright> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
