package com.gongsibao.dingtalkrobot.base;


import java.util.Map;

public interface IDingTalkRobotService {

    int postRobotMsg(Map<String, Object> condition);

    //付款时候发送给大群的
    void postIndividualMsg(Integer pkid);
}
