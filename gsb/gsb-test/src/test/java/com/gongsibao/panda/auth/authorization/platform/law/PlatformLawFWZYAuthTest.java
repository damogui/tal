package com.gongsibao.panda.auth.authorization.platform.law;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;
import org.junit.Before;

/**
 * Created by win on 2018/3/27.
 */
/*平台法务专员*/
public class PlatformLawFWZYAuthTest extends AuthBaseTest {
    @Before
    public void setup() {
        roleCode = "Platform_Law_FWZY";
        super.setup();

    }
    protected void getResourceCodeList() {
//        审核中心（合同审核、退款审核）
        this.resourceNodeCodeList.add("Gsb_Supplier_Order_Audit_Contract");
        this.resourceNodeCodeList.add("Gsb_Supplier_Order_Audit_Refund");

    }
}
