package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.IcpInfoApi;
import com.gongsibao.taurus.entity.IcpInfo;
import com.gongsibao.taurus.message.ResponseMessage;

public class IcpInfoService implements ITaurusApiService<IcpInfo> {

	@Override
	public List<IcpInfo> get(String companyName) {

		IcpInfoApi api = ApiFactory.create(IcpInfoApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<IcpInfo> response = api.getResponse();
		if(response == null){
			
			return null;
		}
		return response.getList();
	}

}
