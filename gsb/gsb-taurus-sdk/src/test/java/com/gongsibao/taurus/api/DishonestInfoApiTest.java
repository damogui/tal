package com.gongsibao.taurus.api;

import org.junit.Test;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.DishonestInfoApi;
import com.gongsibao.taurus.entity.DishonestInfo;
import com.gongsibao.taurus.message.ResponseMessage;

public class DishonestInfoApiTest extends ApiTest{
	@Test
	public void DishonestInfoApi(){
		
		System.out.println("失信人信息 ：");
		DishonestInfoApi api = ApiFactory.create(DishonestInfoApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<DishonestInfo> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
}
