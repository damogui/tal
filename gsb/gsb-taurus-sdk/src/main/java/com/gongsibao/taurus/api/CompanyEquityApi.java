package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.CompanyEquityResponseMessage;

public class CompanyEquityApi  extends AbstractApi<CompanyEquityResponseMessage>{

	@Override
	public String getUrl() {

		return "/tele/companyequity";
	}

	@Override
	public Class<?> getResponseType() {

		return CompanyEquityResponseMessage.class;
	}

}
