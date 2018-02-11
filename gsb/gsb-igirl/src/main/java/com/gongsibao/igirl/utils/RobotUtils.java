package com.gongsibao.igirl.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RobotUtils {
    private static String ADVERTISEMENT = "http://gsb-public.oss-cn-beijing.aliyuncs.com/34108af34caab67c1ee7cf81abd3d77b.png";
//    private static String token="https://oapi.dingtalk.com/robot/send?access_token=70fcd1c17057ff2b9737d48ef167d9fee6736db7a2e9ae20de7ebc46cb15835e";
//    private static String token_err="https://oapi.dingtalk.com/robot/send?access_token=70fcd1c17057ff2b9737d48ef167d9fee6736db7a2e9ae20de7ebc46cb15835e";
    public static void postToRobot(String msg,String token) {
        token = "https://oapi.dingtalk.com/robot/send?access_token=34a67632e6ad189b8f5d7512b3c0e07826707c44a8e91fcc1e3d21389a36f7ff";
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(token);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        String textMsg = "{ \"msgtype\": \"markdown\", \"markdown\": {\"title\": \"最新进度\",\"text\": \" **最新进度** \\n > ![screenshot]("+ADVERTISEMENT+" )\\n "+ msg +" \\n "+ dateStr(new Date(),"yyyy-MM-dd HH:mm:ss")+" 发布 [公司宝](http://www.gongsibao.com/) \\n \"}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        try {
            httpclient.execute(httppost);
        }
        catch (Exception e){
        }
    }
    public static void postToRobotErrorMsg(String msg,String token_err) {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(token_err);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        String textMsg = "{ \"msgtype\": \"markdown\", \"markdown\": {\"title\": \"异常信息：\",\"text\": \" **如下产品订单有异常** \\n > ![screenshot]("+ADVERTISEMENT+" )\\n "+msg +" \\n > ######"+ dateStr(new Date(),"yyyy-MM-dd HH:mm:ss")+" 发布 [公司宝](http://www.gongsibao.com/) \\n \"}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        try {
            httpclient.execute(httppost);
        }
        catch (Exception e){
        }
    }

    /**
     * 转换日期格式字符串
     *
     * @param obj
     * @param pattern
     * @return
     */
    public static String dateStr(Object obj, String pattern) {
        if (null == obj) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(obj);
    }
}