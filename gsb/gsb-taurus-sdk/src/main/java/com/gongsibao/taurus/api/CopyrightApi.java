package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.CopyrightResponseMessage;

public class CopyrightApi extends AbstractApi<CopyrightResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageCopyright";
	}

	@Override
	public Class<?> getResponseType() {

		return CopyrightResponseMessage.class;
	}

}
