package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.EntChangeRecordResponseMessage;

public class EntChangeRecordApi extends AbstractApi<EntChangeRecordResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageEntChangeRecord";
	}

	@Override
	public Class<?> getResponseType() {

		return EntChangeRecordResponseMessage.class;
	}

}
