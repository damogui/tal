package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.EntShareholderResponseMessage;

public class EntShareholderApi extends AbstractApi<EntShareholderResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageEntshareholder";
	}

	@Override
	public Class<?> getResponseType() {

		return EntShareholderResponseMessage.class;
	}

}
