package com.gongsibao.franchisee.web;

import org.netsharp.panda.commerce.TreegridPart;

import com.gongsibao.franchisee.job.FranchiseeReportJob;

public class FranchiseeDayReportController extends TreegridPart{

	public void execute(){
		FranchiseeReportJob job =new FranchiseeReportJob();
		job.execute(null);
	}
}
