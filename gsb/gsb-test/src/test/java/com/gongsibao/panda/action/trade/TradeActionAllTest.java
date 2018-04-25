package com.gongsibao.panda.action.trade;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.action.trade.audit.AuditCarryoverActionTest;
import com.gongsibao.panda.action.trade.audit.AuditContractActionTest;
import com.gongsibao.panda.action.trade.audit.AuditCostActionTest;
import com.gongsibao.panda.action.trade.audit.AuditInvoiceActionTest;
import com.gongsibao.panda.action.trade.audit.AuditOrderNewSaveActionTest;
import com.gongsibao.panda.action.trade.audit.AuditPayActionTest;
import com.gongsibao.panda.action.trade.audit.AuditPayPerformanceActionTest;
import com.gongsibao.panda.action.trade.audit.AuditPerformanceActionTest;
import com.gongsibao.panda.action.trade.audit.AuditRefundActionTest;
import com.gongsibao.panda.action.trade.audit.AuditSettleActionTest;
import com.gongsibao.panda.action.trade.audit.AuditStageActionTest;
import com.gongsibao.panda.action.trade.order.OrderApplyContractActionTest;
import com.gongsibao.panda.action.trade.order.OrderApplyInvoiceActionTest;
import com.gongsibao.panda.action.trade.order.OrderApplyStageActionTest;
import com.gongsibao.panda.action.trade.order.OrderCarryoverActionTest;
import com.gongsibao.panda.action.trade.order.OrderNewSaveActionTest;
import com.gongsibao.panda.action.trade.order.OrderPayActionTest;
import com.gongsibao.panda.action.trade.order.OrderPerformanceActionTest;
import com.gongsibao.panda.action.trade.order.OrderRefundActionTest;
import com.gongsibao.panda.action.trade.order.OrderTransformActionTest;
import com.gongsibao.panda.action.trade.order.PayPerformanceActionTest;
import com.gongsibao.panda.action.trade.settle.SettleApplyActionTest;

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
        AuditStageActionTest.class,
        AuditSettleActionTest.class,
        SettleApplyActionTest.class     // 结算申请
})

public class TradeActionAllTest {

}
