package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.TmResponseMessage;

public class TmApi extends AbstractApi<TmResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageTm";
	}

	@Override
	public Class<?> getResponseType() {

		return TmResponseMessage.class;
	}

}
