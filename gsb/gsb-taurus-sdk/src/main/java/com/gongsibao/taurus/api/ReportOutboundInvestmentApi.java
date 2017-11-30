package com.gongsibao.taurus.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gongsibao.taurus.message.response.ReportOutboundInvestmentResponseMessage;
import com.gongsibao.taurus.util.ConfigHelper;
import com.gongsibao.taurus.util.MD5Util;

public class ReportOutboundInvestmentApi extends AbstractApi<ReportOutboundInvestmentResponseMessage> {
	private int annualReportId;

	public int getAnnualReportId() {
		return annualReportId;
	}

	public void setAnnualReportId(int annualReportId) {
		this.annualReportId = annualReportId;
	}

	@Override
	protected List<String> getParameters() {

		String nowTime = shortFormate.format(new Date());
		List<String> parameters = new ArrayList<String>();
		parameters.add("annualReportId=" + annualReportId);
		int pageIndex = this.getCurrentPage()>0?this.getCurrentPage()-1:this.getCurrentPage();
		parameters.add("currentPage=" + pageIndex);
		parameters.add("pageSize=" + this.getPageSize());
		parameters.add("currentTime=" + nowTime );
		parameters.add("appKey=" + ConfigHelper.APP_KEY);

		String origin = nowTime + ConfigHelper.APP_KEY + ConfigHelper.APP_SIGN + annualReportId+ pageIndex + this.getPageSize() ;
		String token = MD5Util.MD5Encode(origin, "UTF-8");
		parameters.add("token=" + token);
		return parameters;
	}

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageReportOutboundInvestment";
	}

	@Override
	public Class<?> getResponseType() {

		return ReportOutboundInvestmentResponseMessage.class;
	}
}
