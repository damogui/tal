package com.gongsibao.panda.ncl;

import com.gongsibao.panda.ncl.workspace.NclBatchWorkspaceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ResourceTest.class,
        NclBatchWorkspaceTest.class,
        NavigationTest.class
})
public class AllTest {
}
