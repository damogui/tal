package com.gongsibao.panda.auth.authorization.platform.finance;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;
import org.junit.Before;

/**
 * Created by win on 2018/3/27.
 */
/*合同采购专员*/
public class PlatformFinanceHTCGZYAuditTest extends AuthBaseTest {

    @Before
    public void setup() {
        roleCode = "Platform_Finance_HTCGZY";
        super.setup ();

    }

    protected void getResourceCodeList() {
//审核中心（合同审核）
        this.resourceNodeCodeList.add ("GSB_Trade_Audit_Contract");//合同审核


    }
}
