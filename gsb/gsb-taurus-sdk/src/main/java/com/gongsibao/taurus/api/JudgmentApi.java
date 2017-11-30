package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.JudgmentResponseMessage;

public class JudgmentApi extends AbstractApi<JudgmentResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageJudgment";
	}

	@Override
	public Class<?> getResponseType() {

		return JudgmentResponseMessage.class;
	}

}
