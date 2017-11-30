package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.PatentsResponseMessage;

public class PatentsApi extends AbstractApi<PatentsResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pagePatents";
	}

	@Override
	public Class<?> getResponseType() {

		return PatentsResponseMessage.class;
	}

}
