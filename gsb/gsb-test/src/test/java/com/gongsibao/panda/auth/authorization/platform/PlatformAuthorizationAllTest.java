package com.gongsibao.panda.auth.authorization.platform;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.auth.authorization.platform.finance.PlatformFinanceAdminAuditTest;
import com.gongsibao.panda.auth.authorization.platform.finance.PlatformFinanceLeaderAuditTest;
import com.gongsibao.panda.auth.authorization.platform.finance.PlatformFinanceSalesmanAuditTest;
import com.gongsibao.panda.auth.authorization.platform.franchisee.PlatformFranchiseeAdminAuthTest;
import com.gongsibao.panda.auth.authorization.platform.franchisee.PlatformFranchiseeLeaderAuthTest;
import com.gongsibao.panda.auth.authorization.platform.franchisee.PlatformFranchiseeSalesmanAuthTest;
import com.gongsibao.panda.auth.authorization.platform.ma.PlatformMaAdminAuthTest;
import com.gongsibao.panda.auth.authorization.platform.ma.PlatformMaLeaderAuthTest;
import com.gongsibao.panda.auth.authorization.platform.ma.PlatformMaSalesmanAuthTest;
import com.gongsibao.panda.auth.authorization.platform.operation.PlatformOperationAdminAuditTest;
import com.gongsibao.panda.auth.authorization.platform.operation.PlatformOperationLeaderAuditTest;
import com.gongsibao.panda.auth.authorization.platform.operation.PlatformOperationLegalAuditTest;
import com.gongsibao.panda.auth.authorization.platform.presales.PlatformPreSalesLeaderAuthTest;
import com.gongsibao.panda.auth.authorization.platform.presales.PlatformPreSalesServiceAuthTest;

@RunWith(Suite.class)
@SuiteClasses({
	
	PlatformPreSalesServiceAuthTest.class,
	PlatformPreSalesLeaderAuthTest.class,

	PlatformFranchiseeAdminAuthTest.class,
	PlatformFranchiseeLeaderAuthTest.class,
	PlatformFranchiseeSalesmanAuthTest.class,

	PlatformMaAdminAuthTest.class,
	PlatformMaLeaderAuthTest.class,
	PlatformMaSalesmanAuthTest.class,

	PlatformFinanceAdminAuditTest.class,
	PlatformFinanceLeaderAuditTest.class,
	PlatformFinanceSalesmanAuditTest.class,

	PlatformOperationAdminAuditTest.class,
	PlatformOperationLeaderAuditTest.class,
	PlatformOperationLegalAuditTest.class,
})
public class PlatformAuthorizationAllTest {

}
