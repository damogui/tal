package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.EntInvestResponseMessage;

public class EntInvestApi extends AbstractApi<EntInvestResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageEntInvest";
	}

	@Override
	public Class<?> getResponseType() {
		
		return EntInvestResponseMessage.class;
	}

}
