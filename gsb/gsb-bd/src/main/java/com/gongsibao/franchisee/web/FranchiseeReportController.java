package com.gongsibao.franchisee.web;

import java.util.Date;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.TreegridPart;

import com.gongsibao.franchisee.base.IFranchiseeReportService;

public class FranchiseeReportController extends TreegridPart{

	public void execute(){
		
		IFranchiseeReportService service = ServiceFactory.create(IFranchiseeReportService.class);
		Date date = new Date();
		service.generate(date);
	}
}
