package com.gongsibao.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

public class DingTalkRobotUtils {

    //region 常量字段
    private static String ADVERTISEMENT = "http://gsb-public.oss-cn-beijing.aliyuncs.com/99a787bf6f235a37cb0fe8730e6954d1.png";

    //默认头像
    private static String HEAD = "http://gsb-public.oss-cn-beijing.aliyuncs.com/445e4d00a4befdcf45db64adbffd648b.png";

    //公司宝钉钉大群  排名
//    private static String GSB_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=9f8b7092c0103f6241cd54a5fd87168d7b7a7bdbe3693956ca3c4d6d30f9b365";

    //公司宝钉钉大群  无排名 跟进
    private static String GSB_FOLLOW_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=dfe03844c6df7439758fb464dc699dd5e8ca93db7729ef9940c665ad4f2fdbaa";

    //测试机器人
//    private static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=9d8d816d0defa568325c58b4ef42b6501130efb6043de0a284eb69a19901f546";

    //机器人测试小群    推荐
    private static String privacy = "https://oapi.dingtalk.com/robot/send?access_token=31a2422c60c9845119a27aa0c2d936d8a53199b608c198b3dcc4968e7de3d471";

    //机器人群-播报机器人
    private static String radio = "https://oapi.dingtalk.com/robot/send?access_token=89770f9b5a70bfffad5cdda33c61a900932688d68b75064498b31f0b05d85327";

    public static String getADVERTISEMENT() {
        return ADVERTISEMENT;
    }

    public static void setADVERTISEMENT(String ADVERTISEMENT) {
        DingTalkRobotUtils.ADVERTISEMENT = ADVERTISEMENT;
    }

    public static String getHEAD() {
        return HEAD;
    }

    public static void setHEAD(String HEAD) {
        DingTalkRobotUtils.HEAD = HEAD;
    }

    public static String getGsbFollowToken() {
        return GSB_FOLLOW_TOKEN;
    }

    public static void setGsbFollowToken(String gsbFollowToken) {
        GSB_FOLLOW_TOKEN = gsbFollowToken;
    }

    public static String getPrivacy() {
        return privacy;
    }

    public static void setPrivacy(String privacy) {
        DingTalkRobotUtils.privacy = privacy;
    }

    public static String getRadio() {
        return radio;
    }

    public static void setRadio(String radio) {
        DingTalkRobotUtils.radio = radio;
    }
    //endregion

    //region 共有方法
    //订单大群消息推送(//markdown格式)
    public static void postToRobot(String msg, String token) {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(token);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        StringEntity se = new StringEntity(msg, "utf-8");
        httppost.setEntity(se);
        try {
            httpclient.execute(httppost);
        } catch (Exception e) {

        }
    }

    //feedcard格式
    public static void postToRobot2(String msg, String token) {
        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(token);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = "{ \"msgtype\": \"feedCard\", \"feedCard\": {\"links\": [ " + msg + " ]}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            //httpclient.execute(httppost);
        } catch (Exception e) {

        }
    }

    //endregion


}
