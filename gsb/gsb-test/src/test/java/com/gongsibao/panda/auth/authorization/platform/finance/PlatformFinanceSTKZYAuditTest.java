package com.gongsibao.panda.auth.authorization.platform.finance;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;
import org.junit.Before;

/**
 * Created by win on 2018/3/27.
 */
/*收退款专员*/
public class PlatformFinanceSTKZYAuditTest extends AuthBaseTest {
    @Before
    public void setup() {
        roleCode = "Platform_Finance_STKZY";
        super.setup ();

    }

    protected void getResourceCodeList() {
//审核中心（订单业绩审核、回款审核、回款业绩审核、退款审核）
        this.resourceNodeCodeList.add ("GSB_Trade_Audit_Performance");//订单业绩审核
        this.resourceNodeCodeList.add ("GSB_Trade_Audit_Received");//回款审核
        this.resourceNodeCodeList.add ("GSB_Trade_Audit_ReceivedPer");//回款业绩审核
        this.resourceNodeCodeList.add ("GSB_Trade_Audit_Refund");//退款审核

    }
}
