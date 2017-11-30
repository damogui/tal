package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.JudgmentApi;
import com.gongsibao.taurus.entity.Judgment;
import com.gongsibao.taurus.message.ResponseMessage;

public class JudgmentService implements ITaurusApiService<Judgment> {

	@Override
	public List<Judgment> get(String companyName) {

		JudgmentApi api = ApiFactory.create(JudgmentApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<Judgment> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
