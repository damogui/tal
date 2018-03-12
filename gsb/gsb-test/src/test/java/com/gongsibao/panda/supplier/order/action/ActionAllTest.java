package com.gongsibao.panda.supplier.order.action;

import com.gongsibao.panda.supplier.order.action.order.OrderApplyStageActionTest;
import com.gongsibao.panda.supplier.order.action.order.TransformActionTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.supplier.order.action.order.SaveOrderActionTest;

@RunWith(Suite.class)
@SuiteClasses({
        SaveOrderActionTest.class,
        TransformActionTest.class,
        OrderApplyStageActionTest.class
})

public class ActionAllTest {

}
