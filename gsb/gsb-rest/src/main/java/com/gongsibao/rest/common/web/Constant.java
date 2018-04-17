package com.gongsibao.rest.common.web;


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
    public static final String ORDER_BUY_SUCCESS="您的商品 %s 已支付成功，我们将立刻为您办理。";

    /**
     * 微信回调url前缀
     */
    public final static String SYSINQUIRY_CONTINUE_CALLBACK_URL_PREFIX = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=";
    /**
     * 微信回调url后缀（snsapi_base）
     */
    public final static String SYSINQUIRY_CONTINUE_CALLBACK_URL_AFTERFIX = "&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";


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
}
