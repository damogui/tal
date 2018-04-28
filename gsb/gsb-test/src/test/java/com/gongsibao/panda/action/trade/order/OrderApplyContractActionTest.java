package com.gongsibao.panda.action.trade.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.contract.ActionApplyContactLog;
import com.gongsibao.trade.service.action.order.contract.ActionApplyContractAudit;
import com.gongsibao.trade.service.action.order.contract.ActionApplyContractPersist;
import com.gongsibao.trade.service.action.order.contract.ActionApplyContractSendMessage;
import com.gongsibao.trade.service.action.order.contract.ActionApplyContractUpdateOrder;
import com.gongsibao.trade.service.action.order.contract.ActionApplyContractVerify;

/**
 * Created by zhangchao on 2018/3/21.
 */
public class OrderApplyContractActionTest extends BaseActionTest {

    @Before
    public void setup() {

        resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
        super.setup();
    }

    @Test
    public void save() {

        String pathName = "gsb/crm/contract/save";
        BeanPath beanPath = new BeanPath();
        {
            beanPath.toNew();
            beanPath.setPath(pathName);
            beanPath.setResourceNode(resourceNode);
            beanPath.setName("合同");
        }

        createBean(beanPath, "1.验证", ActionApplyContractVerify.class.getName(), resourceNode, 100);
        createBean(beanPath, "2.保存", ActionApplyContractPersist.class.getName(), resourceNode, 200);
        createBean(beanPath, "3.回写", ActionApplyContractUpdateOrder.class.getName(), resourceNode, 210);
        createBean(beanPath, "4.审核", ActionApplyContractAudit.class.getName(), resourceNode, 300);
        createBean(beanPath, "5.通知", ActionApplyContractSendMessage.class.getName(), resourceNode, 400);
        createBean(beanPath, "6.日志", ActionApplyContactLog.class.getName(), resourceNode, 500);
        beanPathService.save(beanPath);
    }
}