package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.EntInvestApi;
import com.gongsibao.taurus.entity.EntInvest;
import com.gongsibao.taurus.message.ResponseMessage;

public class EntInvestService implements ITaurusApiService<EntInvest> {

	@Override
	public List<EntInvest> get(String companyName) {

		EntInvestApi api = ApiFactory.create(EntInvestApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntInvest> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
