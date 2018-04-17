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


}
