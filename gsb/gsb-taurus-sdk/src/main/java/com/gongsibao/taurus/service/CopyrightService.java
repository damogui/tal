package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.CopyrightApi;
import com.gongsibao.taurus.entity.Copyright;
import com.gongsibao.taurus.message.ResponseMessage;

public class CopyrightService implements ITaurusApiService<Copyright>{

	@Override
	public List<Copyright> get(String companyName) {

		CopyrightApi api = ApiFactory.create(CopyrightApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<Copyright> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
