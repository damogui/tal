package com.gongsibao.panda.supplier.order.action;

import com.gongsibao.panda.supplier.order.action.audit.*;
import com.gongsibao.panda.supplier.order.action.order.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        OrderNewSaveActionTest.class,
        OrderTransformActionTest.class,
        OrderApplyStageActionTest.class,
        OrderApplyInvoiceActionTest.class,
        OrderCarryoverActionTest.class,
        OrderPerformanceActionTest.class,
        OrderRefundActionTest.class,
        OrderPayActionTest.class,
        PayPerformanceActionTest.class,
        
        AuditCarryoverActionTest.class,
        AuditContractActionTest.class,
        AuditCostActionTest.class,
        AuditInvoiceActionTest.class,
        AuditOrderNewSaveActionTest.class,
        OrderApplyContractActionTest.class,
        AuditPayActionTest.class,
        AuditPayPerformanceActionTest.class,//回款业绩审核
        AuditPerformanceActionTest.class,
        AuditRefundActionTest.class,
        AuditStageActionTest.class
})

public class ActionAllTest {

}
