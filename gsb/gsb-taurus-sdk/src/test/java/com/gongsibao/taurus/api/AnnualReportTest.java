package com.gongsibao.taurus.api;

import org.junit.Test;

import com.gongsibao.taurus.api.AnnualReportApi;
import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.entity.AnnualReport;
import com.gongsibao.taurus.message.ResponseMessage;

public class AnnualReportTest  extends ApiTest{
	  
	@Test
	public void AnnualReport(){
		
		System.out.println("年报汇总：");
		AnnualReportApi api = ApiFactory.create(AnnualReportApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<AnnualReport> response = api.getResponse();
		System.out.println(response.getResult()+"："+response.getResultMsg());
		System.err.println("/**************************************************/");
	}
	 
}
