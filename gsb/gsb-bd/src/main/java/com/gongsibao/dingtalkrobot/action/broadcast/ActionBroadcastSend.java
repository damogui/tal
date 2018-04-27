package com.gongsibao.dingtalkrobot.action.broadcast;

import com.gongsibao.utils.DingTalkRobotUtils;
import com.gongsibao.redis.base.IRedisAliyunService;
import com.gongsibao.redis.dto.ConstantCache;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ActionBroadcastSend implements IAction {

    private IRedisAliyunService redisAliyunService = ServiceFactory.create(IRedisAliyunService.class);

    private static String ADVERTISEMENT = "http://gsb-public.oss-cn-beijing.aliyuncs.com/34108af34caab67c1ee7cf81abd3d77b.png";

    //推送消息
    @Override
    public void execute(ActionContext ctx) {
        Map<String, Object> ctxStatus = ctx.getStatus();
        if (ctxStatus == null) return;
        String textMsg = StringUtils.trimToEmpty((String) ctxStatus.get("textMsg"));
        String readMsg = StringUtils.trimToEmpty((String) ctxStatus.get("readMsg"));
        Integer orderId = (Integer) ctx.getItem();
        //钉钉大群信息推送
        DingTalkRobotUtils.postToRobot(textMsg, DingTalkRobotUtils.getGsbFollowToken());
        try {
            boolean bool = redisAliyunService.put("new_order_" + orderId, readMsg, ConstantCache.TIME_ONE_DAY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
