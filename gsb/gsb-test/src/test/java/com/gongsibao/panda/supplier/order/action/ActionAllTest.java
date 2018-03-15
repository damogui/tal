package com.gongsibao.panda.supplier.order.action;

import com.gongsibao.panda.supplier.order.action.order.OrderApplyInvoiceActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderCarryoverActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderApplyStageActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderPerformanceActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderRefundActionTest;
import com.gongsibao.panda.supplier.order.action.order.PayPerformanceActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderTransformActionTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.supplier.order.action.order.OrderNewSaveActionTest;

@RunWith(Suite.class)
@SuiteClasses({
        OrderNewSaveActionTest.class,
        OrderTransformActionTest.class,
        OrderApplyStageActionTest.class,
        OrderApplyInvoiceActionTest.class,
        OrderCarryoverActionTest.class,
        OrderPerformanceActionTest.class,
        OrderRefundActionTest.class,
        PayPerformanceActionTest.class,
})

public class ActionAllTest {

}
