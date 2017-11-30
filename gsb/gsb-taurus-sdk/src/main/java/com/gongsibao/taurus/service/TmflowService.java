package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.TmflowApi;
import com.gongsibao.taurus.entity.Tmflow;
import com.gongsibao.taurus.message.ResponseMessage;

public class TmflowService implements ITaurusApiService<Tmflow> {

	@Override
	public List<Tmflow> get(String companyName) {

		TmflowApi api = ApiFactory.create(TmflowApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<Tmflow> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
