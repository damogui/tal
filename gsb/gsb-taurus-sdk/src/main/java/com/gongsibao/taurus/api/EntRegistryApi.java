package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.EntRegistryResponseMessage;

public class EntRegistryApi extends AbstractApi<EntRegistryResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/entregistry";
	}

	@Override
	public Class<?> getResponseType() {

		return EntRegistryResponseMessage.class;
	}
}
