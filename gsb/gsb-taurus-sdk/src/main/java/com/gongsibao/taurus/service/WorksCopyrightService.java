package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.WorksCopyrightApi;
import com.gongsibao.taurus.entity.WorksCopyright;
import com.gongsibao.taurus.message.ResponseMessage;

public class WorksCopyrightService implements ITaurusApiService<WorksCopyright> {

	@Override
	public List<WorksCopyright> get(String companyName) {

		WorksCopyrightApi api = ApiFactory.create(WorksCopyrightApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<WorksCopyright> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
