package com.gongsibao.panda.supplier.order.action;

import com.gongsibao.panda.supplier.order.action.order.OrderApplyStageActionTest;
import com.gongsibao.panda.supplier.order.action.order.TransformActionTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.supplier.order.action.order.OrderNewSaveActionTest;

@RunWith(Suite.class)
@SuiteClasses({
        OrderNewSaveActionTest.class,
        TransformActionTest.class,
        OrderApplyStageActionTest.class
})

public class ActionAllTest {

}
