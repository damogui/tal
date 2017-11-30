package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.CourtExecutiveResponseMessage;

public class CourtExecutiveApi extends AbstractApi<CourtExecutiveResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageCourtExecutive";
	}

	@Override
	public Class<?> getResponseType() {

		return CourtExecutiveResponseMessage.class;
	}

}
