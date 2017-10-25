package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.EntRegistryApi;
import com.gongsibao.taurus.entity.EntRegistry;
import com.gongsibao.taurus.message.ResponseMessage;

public class EntRegistryService implements ITaurusApiService<EntRegistry> {

	@Override
	public List<EntRegistry> get(String companyName) {

		EntRegistryApi api = ApiFactory.create(EntRegistryApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntRegistry> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
