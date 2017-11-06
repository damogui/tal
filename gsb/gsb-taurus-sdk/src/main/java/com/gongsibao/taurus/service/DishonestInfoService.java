package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.DishonestInfoApi;
import com.gongsibao.taurus.entity.DishonestInfo;
import com.gongsibao.taurus.message.ResponseMessage;

public class DishonestInfoService implements ITaurusApiService<DishonestInfo> {

	@Override
	public List<DishonestInfo> get(String companyName) {

		DishonestInfoApi api = ApiFactory.create(DishonestInfoApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<DishonestInfo> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
