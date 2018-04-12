package com.gongsibao.panda.supplier.settle;

import com.gongsibao.panda.supplier.settle.workspace.MySettleWorkspaceTest;
import com.gongsibao.panda.supplier.settle.workspace.SettleFormWorkspaceTest;
import com.gongsibao.panda.supplier.settle.workspace.UnSettleWorkspaceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        SettleResourceTest.class,
        SettleNavigationTest.class,
        UnSettleWorkspaceTest.class,
        MySettleWorkspaceTest.class,
})

public class SettleAllTest {
}
