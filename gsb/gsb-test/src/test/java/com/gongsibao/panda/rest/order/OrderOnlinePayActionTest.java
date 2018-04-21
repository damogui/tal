package com.gongsibao.panda.rest.order;

import com.gongsibao.panda.supplier.crm.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.pay.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

/**
 * ClassName: OrderOnlinePayActionTest
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/20 14:17
 */
public class OrderOnlinePayActionTest extends BaseActionTest {

    @Before
    public void setup() {
        resourceNodeCode = "Gsb_Rest_Interface_Order_Pay";
        super.setup();
    }

    @Test
    public void save() {
        this.resourceNode = this.resourceNodeService.byCode(this.resourceNodeCode);
        Assert.assertNotNull(this.resourceNode);

        String pathName = "gsb/rest/order/onlinePay";
        BeanPath beanPath = new BeanPath();
        {
            beanPath.toNew();
            beanPath.setPath(pathName);
            beanPath.setResourceNode(resourceNode);
            beanPath.setName("线上支付回写");
        }

        createBean(beanPath, "1.验证", ActionOnlinePayVerify.class.getName(), resourceNode, 100);
        createBean(beanPath, "2.状态", ActionOnlinePayPersist.class.getName(), resourceNode, 200);
        createBean(beanPath, "3.U8", ActionOnlinePayU8.class.getName(), resourceNode, 300);
        createBean(beanPath, "4.进度", ActionOnlinePayTrace.class.getName(), resourceNode, 400);
        createBean(beanPath, "5.消息", ActionOnlinePaySendMessage.class.getName(), resourceNode, 500);
        beanPathService.save(beanPath);
    }
}
