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

        super.setup();
        roleCode = "Platform_Law_FWZY";
    }
}
