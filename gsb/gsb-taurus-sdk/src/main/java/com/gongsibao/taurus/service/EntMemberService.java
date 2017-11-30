package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.EntMemberApi;
import com.gongsibao.taurus.entity.EntMember;
import com.gongsibao.taurus.message.ResponseMessage;

public class EntMemberService implements ITaurusApiService<EntMember> {

	@Override
	public List<EntMember> get(String companyName) {

		EntMemberApi api = ApiFactory.create(EntMemberApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntMember> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
