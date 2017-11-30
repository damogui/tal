package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.CourtExecutiveApi;
import com.gongsibao.taurus.entity.CourtExecutive;
import com.gongsibao.taurus.message.ResponseMessage;

public class CourtExecutiveService implements ITaurusApiService<CourtExecutive> {

	@Override
	public List<CourtExecutive> get(String companyName) {

		CourtExecutiveApi api = ApiFactory.create(CourtExecutiveApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<CourtExecutive> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
