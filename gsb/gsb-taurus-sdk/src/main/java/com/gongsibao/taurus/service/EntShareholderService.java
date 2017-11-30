package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.EntShareholderApi;
import com.gongsibao.taurus.entity.EntShareholder;
import com.gongsibao.taurus.message.ResponseMessage;

public class EntShareholderService implements ITaurusApiService<EntShareholder> {

	@Override
	public List<EntShareholder> get(String companyName) {

		EntShareholderApi api = ApiFactory.create(EntShareholderApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntShareholder> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
