package com.gongsibao.dingtalkrobot.action.broadcast;

import com.gongsibao.utils.DingTalkRobotUtils;
import com.gongsibao.redis.base.IRedisAliyunService;
import com.gongsibao.redis.dto.ConstantCache;
import org.apache.commons.lang3.StringUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.util.Map;

public class ActionBroadcastSend implements IAction {

    private IRedisAliyunService redisAliyunService = ServiceFactory.create(IRedisAliyunService.class);

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
