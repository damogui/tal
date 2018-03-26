package com.gongsibao.panda.supplier.order.action.audit;

import com.gongsibao.panda.supplier.crm.action.BaseActionTest;
import com.gongsibao.trade.service.action.audit.pay.ActionAuditPaySendMessage;
import com.gongsibao.trade.service.action.audit.pay.ActionAuditPayVerify;
import com.gongsibao.trade.service.action.audit.pay.ActionAuditPayWriteBack;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

/**
 * Created by win on 2018/3/26.
 */
/*回款业绩审核*/
public class AuditPayPerformanceActionTest extends BaseActionTest {
    @Before
    public void setup() {

        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Add";
        super.setup();
    }

    @Test
    public void save() {

        String pathName = "gsb/crm/audit/payper";
        BeanPath beanPath = new BeanPath();
        {
            beanPath.toNew();
            beanPath.setPath(pathName);
            beanPath.setResourceNode(resourceNode);
            beanPath.setName("回款业绩审核");
        }

        createBean(beanPath, "1.验证", ActionAuditPayVerify.class.getName(), resourceNode, 100);
        createBean(beanPath, "2.回写", ActionAuditPayWriteBack.class.getName(), resourceNode, 200);
        createBean(beanPath, "4.通知", ActionAuditPaySendMessage.class.getName(), resourceNode, 300);
        beanPathService.save(beanPath);
    }
}
