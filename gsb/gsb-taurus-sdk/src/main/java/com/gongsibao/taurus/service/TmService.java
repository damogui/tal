package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.TmApi;
import com.gongsibao.taurus.entity.Tm;
import com.gongsibao.taurus.message.ResponseMessage;

public class TmService implements ITaurusApiService<Tm> {

	@Override
	public List<Tm> get(String companyName) {

		TmApi api = ApiFactory.create(TmApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<Tm> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
