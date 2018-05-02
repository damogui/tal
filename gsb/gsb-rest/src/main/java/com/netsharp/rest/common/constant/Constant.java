package com.netsharp.rest.common.constant;


import com.gongsibao.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: Constant
 * @Description: TODO 常量
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/4/16 11:18
 */
public class Constant {
    //微信支付成功提醒
    public static final String ORDER_BUY_SUCCESS="您的订单( %s ) 已支付成功，我们将立即为您办理。%s";
    public static final String ORDER_CHANGE_STATE_MSG="您购买的服务\"%s\" 办理进度变更为 \"%s\" \n\r" +"<a href=\"%s\">点此查看详情>></a>";
    /**
     * 微信回调url前缀
     */
    public final static String SYSINQUIRY_CONTINUE_CALLBACK_URL_PREFIX = "https://open.weixin.qq.com/connect/oauth2/authorize?from=weixin&appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";

    public static String PAY_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";

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
    /**
     * 缓存前缀枚举
     */
    public enum Cache{
        /** 激活优惠码尝试次数 **/
        ACCOUNT_COUPON_TIMES;

        /**
         * 拼接前缀
         * @param serializable *注意入参是可以自动序列换为String的参数*
         * @return
         */
        public String prefix(Serializable serializable){
            return this.name().concat("#").concat(serializable.toString());
        }

        /** 12小时自动失效 **/
        public String prefixToDay(Serializable serializable){
            return prefix(serializable).concat("#").concat(DateUtils.formatDate(new Date(),"yyyyMMdd"));
        }
    }

    public static final String SUBSCRIBE ="1";
    public static final String UNSUBSCRIBE ="0";
}
