package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.CompanyResponseMessage;

public class EntSearchApi  extends AbstractApi<CompanyResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageEntSearch";
	}

	@Override
	public Class<?> getResponseType() {

		return CompanyResponseMessage.class;
	}
}
