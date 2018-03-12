package com.gongsibao.panda.supplier.order.action;

import com.gongsibao.panda.supplier.order.action.order.ApplyInvoiceActionTest;
import com.gongsibao.panda.supplier.order.action.order.CarryoverActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderApplyStageActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderPerformanceActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderRefundActionTest;
import com.gongsibao.panda.supplier.order.action.order.PayPerformanceActionTest;
import com.gongsibao.panda.supplier.order.action.order.TransformActionTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.supplier.order.action.order.OrderNewSaveActionTest;

@RunWith(Suite.class)
@SuiteClasses({
        OrderNewSaveActionTest.class,
        TransformActionTest.class,
        OrderApplyStageActionTest.class,
        ApplyInvoiceActionTest.class,
        CarryoverActionTest.class,
        OrderPerformanceActionTest.class,
        OrderRefundActionTest.class,
        PayPerformanceActionTest.class,
})

public class ActionAllTest {

}
