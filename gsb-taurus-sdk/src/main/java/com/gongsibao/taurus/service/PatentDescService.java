package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.PatentDescApi;
import com.gongsibao.taurus.entity.PatentDesc;
import com.gongsibao.taurus.message.ResponseMessage;

public class PatentDescService implements ITaurusApiService<PatentDesc> {

	@Override
	public List<PatentDesc> get(String companyName) {

		PatentDescApi api = ApiFactory.create(PatentDescApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<PatentDesc> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
