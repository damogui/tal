package com.gongsibao.panda.auth.authorization.platform;

import com.gongsibao.panda.auth.authorization.platform.finance.*;
import com.gongsibao.panda.auth.authorization.platform.law.PlatformLawFWZYAuthTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

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

        PlatformPreSalesServiceAuthTest.class,//售前客服
        PlatformPreSalesLeaderAuthTest.class,//售前经理

        PlatformFranchiseeAdminAuthTest.class,
        PlatformFranchiseeLeaderAuthTest.class,
        PlatformFranchiseeSalesmanAuthTest.class,

        PlatformMaAdminAuthTest.class,
        PlatformMaLeaderAuthTest.class,
        PlatformMaSalesmanAuthTest.class,

        PlatformFinanceAdminAuditTest.class,
        PlatformFinanceLeaderAuditTest.class,
        //PlatformFinanceSalesmanAuditTest.class,//对应的角色不存在

        PlatformOperationAdminAuditTest.class,
        PlatformOperationLeaderAuditTest.class,
        PlatformOperationLegalAuditTest.class,
        PlatformFinanceSTKZYAuditTest.class,//收退款专员
        PlatformLawFWZYAuthTest.class,//法务专员帐号
        PlatformFinanceHTCGZYAuditTest.class,//合同采购专员
        PlatformFinanceFPZYAuditTest.class,//发票专员

})
public class PlatformAuthorizationAllTest {

}
