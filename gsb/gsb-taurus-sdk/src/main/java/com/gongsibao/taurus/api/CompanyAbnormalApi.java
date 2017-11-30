package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.CompanyAbnormalResponseMessage;

public class CompanyAbnormalApi  extends AbstractApi<CompanyAbnormalResponseMessage>{

	@Override
	public String getUrl() {

		return "/tele/companyabnormal";
	}

	@Override
	public Class<?> getResponseType() {

		return CompanyAbnormalResponseMessage.class;
	}

}
