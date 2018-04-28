package com.gongsibao.panda.action;

import com.gongsibao.panda.action.bd.BdActionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.action.crm.CrmActionTest;
import com.gongsibao.panda.action.supplier.SupplierActionTest;
import com.gongsibao.panda.action.trade.TradeActionAllTest;

@RunWith(Suite.class)
@SuiteClasses({
        SupplierActionTest.class,
        TradeActionAllTest.class,
        CrmActionTest.class,
        BdActionTest.class
})
public class ActionAllTest {

}
