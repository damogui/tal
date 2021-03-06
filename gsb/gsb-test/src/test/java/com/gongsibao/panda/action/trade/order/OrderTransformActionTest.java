package com.gongsibao.panda.action.trade.order;

import com.gongsibao.trade.service.action.order.refund.ActionApplyRefundSendMessage;
import com.gongsibao.trade.service.action.order.transform.ActionTransformSendMessage;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.transform.ActionTransformPersist;
import com.gongsibao.trade.service.action.order.transform.ActionTransformRecordLog;
import com.gongsibao.trade.service.action.order.transform.ActionTransformVerify;

/**
 * Created by zhangchao on 2018/3/7.
 * 订单转移/分配
 */
public class OrderTransformActionTest extends BaseActionTest {

    @Before
    public void setup() {

        resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
        super.setup();
    }

    @Test
    public void transform() {

        String pathName = "gsb/crm/order/transform";
        BeanPath beanPath = new BeanPath();
        {
            beanPath.toNew();
            beanPath.setPath(pathName);
            beanPath.setResourceNode(resourceNode);
            beanPath.setName("订单转移/分配");
        }

        createBean(beanPath, "1.验证", ActionTransformVerify.class.getName(), resourceNode, 100);
        createBean(beanPath, "2.保存订单业务员id", ActionTransformPersist.class.getName(), resourceNode, 200);
        createBean(beanPath, "3.通知", ActionTransformSendMessage.class.getName(), resourceNode, 300);
        createBean(beanPath, "4.日志", ActionTransformRecordLog.class.getName(), resourceNode, 400);
        beanPathService.save(beanPath);
    }
}
