package com.gongsibao.panda.auth.authorization.platform.finance;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;
import org.junit.Before;

/**
 * Created by win on 2018/3/27.
 */
/*发票专员*/
public class PlatformFinanceFPZYAuditTest extends AuthBaseTest {
    @Before
    public void setup() {
        roleCode = "Platform_Finance_FPZY";
        super.setup();

    }
    protected void getResourceCodeList() {
//        审核中心（发票审核）
        this.resourceNodeCodeList.add("GSB_Trade_Audit_Invoice");
        //发票审核
        this.resourceNodeCodeList.add("GSB_Trade_Audit_Form_Invoice");

        this.resourceNodeCodeList.add ("ChangePassword");//密码权限
    }
}
