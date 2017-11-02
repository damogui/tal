package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.CompanyMortgageResponseMessage;

public class CompanyMortgageApi  extends AbstractApi<CompanyMortgageResponseMessage>{

	@Override
	public String getUrl() {

		return "/tele/companymortgage";
	}

	@Override
	public Class<?> getResponseType() {

		return CompanyMortgageResponseMessage.class;
	}

}
