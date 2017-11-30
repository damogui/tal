package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.PatentsApi;
import com.gongsibao.taurus.entity.Patents;
import com.gongsibao.taurus.message.ResponseMessage;

public class PatentsService implements ITaurusApiService<Patents> {

	@Override
	public List<Patents> get(String companyName) {

		PatentsApi api = ApiFactory.create(PatentsApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<Patents> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
