package com.gongsibao.franchisee.service;

import java.util.Date;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.franchisee.FranchiseeReport;
import com.gongsibao.franchisee.base.IFranchiseeReportService;
import com.gongsibao.franchisee.service.report.AbstractReportHandler;
import com.gongsibao.franchisee.service.report.ReportContext;
import com.gongsibao.franchisee.service.report.salesman.SalesmanDayHandler;

@Service
public class FranchiseeReportService extends PersistableService<FranchiseeReport> implements IFranchiseeReportService {

	IOrganizationService organizaService = ServiceFactory.create(IOrganizationService.class);

	public FranchiseeReportService() {
		super();
		this.type = FranchiseeReport.class;
	}

	@Override
	public Boolean generate(Date date) {

		AbstractReportHandler hander = new SalesmanDayHandler();
		ReportContext context = new ReportContext();
		context.setDate(date);
		hander.setContext(context);
		hander.execute();
		return true;
	}
}