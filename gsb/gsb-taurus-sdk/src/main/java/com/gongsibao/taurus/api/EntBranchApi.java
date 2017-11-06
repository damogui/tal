package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.EntBranchResponseMessage;

public class EntBranchApi extends AbstractApi<EntBranchResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageEntBranch";
	}

	@Override
	public Class<?> getResponseType() {

		return EntBranchResponseMessage.class;
	}

}
