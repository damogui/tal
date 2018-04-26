package com.gongsibao.panda.action.trade.settle;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.settle.ActionApplySettleAudit;
import com.gongsibao.trade.service.action.order.settle.ActionApplySettleSave;
import com.gongsibao.trade.service.action.order.settle.ActionApplySettleUpdateOrderProd;
import com.gongsibao.trade.service.action.order.settle.ActionApplySettleVerify;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

public class SettleApplyActionTest extends BaseActionTest {

    @Before
    public void setup() {
        resourceNodeCode = "Gsb_Supplier_My_Unsettle";
        super.setup();
    }

    @Test
    public void save() {
        String pathName = "gsb/supplier/settle/apply";
        BeanPath beanPath = new BeanPath();
        {
            beanPath.toNew();
            beanPath.setPath(pathName);
            beanPath.setResourceNode(resourceNode);
            beanPath.setName("提交结算");
        }

        createBean(beanPath, "1.验证", ActionApplySettleVerify.class.getName(), resourceNode, 100);
        createBean(beanPath, "2.保存", ActionApplySettleSave.class.getName(), resourceNode, 200);
        createBean(beanPath, "3.审核", ActionApplySettleAudit.class.getName(), resourceNode, 300);
        createBean(beanPath, "4.回写", ActionApplySettleUpdateOrderProd.class.getName(), resourceNode, 400);
        beanPathService.save(beanPath);
    }

}
