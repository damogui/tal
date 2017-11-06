package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.WorksCopyrightResponseMessage;

public class WorksCopyrightApi extends AbstractApi<WorksCopyrightResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageWorksCopyright";
	}

	@Override
	public Class<?> getResponseType() {

		return WorksCopyrightResponseMessage.class;
	}

}
