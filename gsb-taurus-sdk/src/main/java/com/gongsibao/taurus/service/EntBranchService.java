package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.EntBranchApi;
import com.gongsibao.taurus.entity.EntBranch;
import com.gongsibao.taurus.message.ResponseMessage;

public class EntBranchService implements ITaurusApiService<EntBranch> {

	@Override
	public List<EntBranch> get(String companyName) {

		EntBranchApi api = ApiFactory.create(EntBranchApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntBranch> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
