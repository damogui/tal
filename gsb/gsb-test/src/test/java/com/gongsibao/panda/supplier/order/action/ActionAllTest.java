package com.gongsibao.panda.supplier.order.action;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.supplier.order.action.audit.AuditCarryoverActionTest;
import com.gongsibao.panda.supplier.order.action.audit.AuditContractActionTest;
import com.gongsibao.panda.supplier.order.action.audit.AuditCostActionTest;
import com.gongsibao.panda.supplier.order.action.audit.AuditInvoiceActionTest;
import com.gongsibao.panda.supplier.order.action.audit.AuditPayActionTest;
import com.gongsibao.panda.supplier.order.action.audit.AuditPerformanceActionTest;
import com.gongsibao.panda.supplier.order.action.audit.AuditRefundActionTest;
import com.gongsibao.panda.supplier.order.action.audit.AuditStageActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderApplyInvoiceActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderApplyStageActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderCarryoverActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderNewSaveActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderPerformanceActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderRefundActionTest;
import com.gongsibao.panda.supplier.order.action.order.OrderTransformActionTest;
import com.gongsibao.panda.supplier.order.action.order.PayPerformanceActionTest;

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
        
        AuditCarryoverActionTest.class,
        AuditContractActionTest.class,
        AuditCostActionTest.class,
        AuditInvoiceActionTest.class,
        AuditPayActionTest.class,
        AuditPerformanceActionTest.class,
        AuditRefundActionTest.class,
        AuditStageActionTest.class
})

public class ActionAllTest {

}
