package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.EntChangeRecordApi;
import com.gongsibao.taurus.entity.EntChangeRecord;
import com.gongsibao.taurus.message.ResponseMessage;

public class EntChangeRecordService implements ITaurusApiService<EntChangeRecord> {

	@Override
	public List<EntChangeRecord> get(String companyName) {

		EntChangeRecordApi api = ApiFactory.create(EntChangeRecordApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntChangeRecord> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
