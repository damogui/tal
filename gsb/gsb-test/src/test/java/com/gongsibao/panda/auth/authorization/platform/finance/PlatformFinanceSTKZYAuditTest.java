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

        super.setup();
        roleCode = "Platform_Finance_STKZY";
    }

    protected void getResourceCodeList() {

        this.resourceNodeCodeList.add("Gsb_Supplier_Order_Salesman");
    }
}
