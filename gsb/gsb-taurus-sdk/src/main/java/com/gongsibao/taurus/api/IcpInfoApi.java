package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.IcpInfoResponseMessage;

public class IcpInfoApi extends AbstractApi<IcpInfoResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageIcpInfo";
	}

	@Override
	public Class<?> getResponseType() {

		return IcpInfoResponseMessage.class;
	}
}
