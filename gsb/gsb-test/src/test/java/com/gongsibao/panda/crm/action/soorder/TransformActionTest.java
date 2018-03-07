package com.gongsibao.panda.crm.action.soorder;

import com.gongsibao.crm.service.action.task.follow.ActionFollowPersist;
import com.gongsibao.crm.service.action.task.follow.ActionFollowRecordLog;
import com.gongsibao.crm.service.action.task.follow.ActionFollowVerify;
import com.gongsibao.crm.service.action.task.follow.ActionFollowWriteBack;
import com.gongsibao.panda.crm.action.BaseActionTest;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

/**
 * Created by zhangchao on 2018/3/7.
 */
public class TransformActionTest extends BaseActionTest {

    @Before
    public void setup() {

        resourceNodeCode = "CRM_ORDER_TRANSFORM";
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

        createBean(beanPath, "验证", ActionFollowVerify.class.getName(), resourceNode, 100);
        createBean(beanPath, "保存", ActionFollowPersist.class.getName(), resourceNode, 200);
        createBean(beanPath, "回写", ActionFollowWriteBack.class.getName(), resourceNode, 300);
        createBean(beanPath, "日志", ActionFollowRecordLog.class.getName(), resourceNode, 400);
        beanPathService.save(beanPath);
    }
}
