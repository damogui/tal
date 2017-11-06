package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.AnnualReportApi;
import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.entity.AnnualReport;
import com.gongsibao.taurus.message.ResponseMessage;

public class AnnualReportService implements ITaurusApiService<AnnualReport>{

	@Override
	public List<AnnualReport> get(String companyName) {
		
		AnnualReportApi api = ApiFactory.create(AnnualReportApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<AnnualReport> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}
}
