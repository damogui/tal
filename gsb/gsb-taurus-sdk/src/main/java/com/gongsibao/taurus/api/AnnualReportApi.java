package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.AnnualReportResponseMessage;

public class AnnualReportApi extends AbstractApi<AnnualReportResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageAnnualReport";
	}

	@Override
	public Class<?> getResponseType() {

		return AnnualReportResponseMessage.class;
	}
}
