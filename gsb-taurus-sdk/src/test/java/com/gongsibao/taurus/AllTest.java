package com.gongsibao.taurus;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.taurus.api.AnnualReportTest;
import com.gongsibao.taurus.api.CopyrightTest;
import com.gongsibao.taurus.api.CourtExecutiveTest;
import com.gongsibao.taurus.api.DishonestInfoApiTest;
import com.gongsibao.taurus.api.EntBranchTest;
import com.gongsibao.taurus.api.EntChangeRecordTest;
import com.gongsibao.taurus.api.EntInvestTest;
import com.gongsibao.taurus.api.EntshareholderTest;
import com.gongsibao.taurus.api.IcpInfoTest;
import com.gongsibao.taurus.api.JudgmentTest;
import com.gongsibao.taurus.api.MemberTest;
import com.gongsibao.taurus.api.PatentdescTest;
import com.gongsibao.taurus.api.PatentsTest;
import com.gongsibao.taurus.api.ReportWebInfoTest;
import com.gongsibao.taurus.api.RntRegistryTest;
import com.gongsibao.taurus.api.TmTest;
import com.gongsibao.taurus.api.TmdescTest;
import com.gongsibao.taurus.api.WorksCopyrightTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	RntRegistryTest.class,
	AnnualReportTest.class, 
	CopyrightTest.class,
	CourtExecutiveTest.class,
	DishonestInfoApiTest.class,
	EntBranchTest.class,
	EntChangeRecordTest.class,
	EntInvestTest.class,
	EntshareholderTest.class,
	IcpInfoTest.class,
	JudgmentTest.class,
	MemberTest.class,
	ReportWebInfoTest.class,
	WorksCopyrightTest.class,
	TmTest.class,
	TmdescTest.class,
	PatentsTest.class,
	PatentdescTest.class,
})
public class AllTest {

}