package com.gongsibao.panda.action.bd.dingtalkrobot;

import com.gongsibao.dingtalkrobot.action.broadcast.ActionBroadcastPrepare;
import com.gongsibao.dingtalkrobot.action.broadcast.ActionBroadcastSend;
import com.gongsibao.dingtalkrobot.action.broadcast.ActionBroadcastVerify;
import com.gongsibao.panda.action.BaseActionTest;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

public class BroadcastActionTest extends BaseActionTest {

    @Before
    public void setup() {

        resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
        super.setup();
    }

    @Test
    public void save() {

        String pathName = "gsb/bd/dingtalk/broadcast";
        BeanPath beanPath = new BeanPath();
        {
            beanPath.toNew();
            beanPath.setPath(pathName);
            beanPath.setResourceNode(resourceNode);
            beanPath.setName("钉钉群和大喇叭播报");
        }

        createBean(beanPath, "1.验证", ActionBroadcastVerify.class.getName(), resourceNode, 100);
        createBean(beanPath, "2.准备消息", ActionBroadcastPrepare.class.getName(), resourceNode, 200);
        createBean(beanPath, "3.发送", ActionBroadcastSend.class.getName(), resourceNode, 300);
        beanPathService.save(beanPath);
    }
}