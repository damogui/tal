package com.gongsibao.panda.action.trade.audit;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.audit.payperformance.ActionAuditPayPerSendMessage;
import com.gongsibao.trade.service.action.audit.payperformance.ActionAuditPayPerVerify;
import com.gongsibao.trade.service.action.audit.payperformance.ActionAuditPayPerWriteBack;

/**
 * Created by win on 2018/3/26.
 */
/*回款业绩审核*/
public class AuditPayPerformanceActionTest extends BaseActionTest {
    @Before
    public void setup() {

        resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
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

        createBean(beanPath, "1.验证", ActionAuditPayPerVerify.class.getName(), resourceNode, 100);
        createBean(beanPath, "2.回写", ActionAuditPayPerWriteBack.class.getName(), resourceNode, 200);
        createBean(beanPath, "4.通知", ActionAuditPayPerSendMessage.class.getName(), resourceNode, 300);
        beanPathService.save(beanPath);
    }
}
