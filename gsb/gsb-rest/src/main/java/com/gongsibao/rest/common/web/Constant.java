package com.gongsibao.rest.common.web;
/**
 * ClassName: Constant
 * @Description: TODO 常量
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/4/16 11:18
 */
public class Constant {
    //微信支付成功提醒
    public static final String ORDER_BUY_SUCCESS="您的商品 %s 已支付成功，我们将立刻为您办理。";

    /**
     * 微信回调url前缀
     */
    public final static String SYSINQUIRY_CONTINUE_CALLBACK_URL_PREFIX = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=";
    /**
     * 微信回调url后缀（snsapi_base）
     */
    public final static String SYSINQUIRY_CONTINUE_CALLBACK_URL_AFTERFIX = "&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";

    public static final String COOKIE_ACCOUNT_LOGIN_TICKET = "COOKIE_ACCOUNT_LOGIN_TICKET";

    public static final String ICOMPANY_ACCOUNT_COMPANY = "ICOMPANY_ACCOUNT_COMPANY_";
    /*秒*/
    public static final int TIME_ONE_SECOND = 1;                    //1秒
    public static final int TIME_TEN_SECOND = 10 * TIME_ONE_SECOND;      //10秒

    /*分*/
    public static final int TIME_HALF_MINUTE = 30 * TIME_ONE_SECOND;     //半分钟
    public static final int TIME_ONE_MINUTE = 60 * TIME_ONE_SECOND;      //1分钟
    public static final int TIME_TWO_MINUTE = 2 * TIME_ONE_MINUTE;      //2分钟
    public static final int TIME_THREE_MINUTE = 3 * TIME_ONE_MINUTE;      //3分钟
    public static final int TIME_FIVE_MINUTES = 5 * TIME_ONE_MINUTE;     //5分钟
    public static final int TIME_TEN_MINUTES = 10 * TIME_ONE_MINUTE;     //10分钟

    /*小时*/
    public static final int TIME_HALF_HOUR = 30 * TIME_ONE_MINUTE;   //半小时
    public static final int TIME_ONEHALF_HOUR = 3 * TIME_HALF_HOUR;   //一个半小时
    public static final int TIME_ONE_HOUR = 60 * TIME_ONE_MINUTE;    //1小时
    public static final int TIME_THREE_HOURS = 3 * TIME_ONE_HOUR;    //3小时
    /*天*/
    public static final int TIME_HALF_DAY = 12 * TIME_ONE_HOUR;      //半天
    public static final int TIME_ONE_DAY = 24 * TIME_ONE_HOUR;       //1天
    public static final int TIME_TWO_DAYS = 2 * TIME_ONE_DAY;        //2天
    public static final int TIME_ONE_WEEK = 7 * TIME_ONE_DAY;        // 7天
    public static final int TIME_ONE_MONTH = 30 * TIME_ONE_DAY;      //30天
    public static final int TIME_ONE_YEAR = 365 * TIME_ONE_DAY;      //365天
}