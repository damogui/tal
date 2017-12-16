package com.gongsibao.panda;

import org.junit.Test;

import com.gongsibao.franchisee.job.FranchiseeReportJob;

public class MyTest {
	@Test
	public void run() {
		FranchiseeReportJob myJob =new FranchiseeReportJob();
		myJob.execute(null);
		System.out.println("ok......");
	}
}
