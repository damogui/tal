package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.EntMemberResponseMessage;

public class EntMemberApi extends AbstractApi<EntMemberResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageEntMember";
	}

	@Override
	public Class<?> getResponseType() {
		
		return EntMemberResponseMessage.class;
	}

}
