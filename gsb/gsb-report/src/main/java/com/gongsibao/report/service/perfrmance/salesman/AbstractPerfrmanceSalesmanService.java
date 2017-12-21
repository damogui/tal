package com.gongsibao.report.service.perfrmance.salesman;

import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;

public abstract class AbstractPerfrmanceSalesmanService extends AbstractPerfrmanceService{

	protected ReportOrganizationType getReportOrganizationType(){
		
		return ReportOrganizationType.SALESMAN;
	}
	
}
