package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.TmdescApi;
import com.gongsibao.taurus.entity.Tmdesc;
import com.gongsibao.taurus.message.ResponseMessage;

public class TmdescService implements ITaurusApiService<Tmdesc> {

	@Override
	public List<Tmdesc> get(String companyName) {

		TmdescApi api = ApiFactory.create(TmdescApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<Tmdesc> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
