package com.gongsibao.redis.dto;

public class ConstantCache {
    /**
     * 缓存时间常量
     */
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

    public static final int TIME_FOUR_HOURS = 4 * TIME_ONE_DAY;     //4小时

    public static final String LOGIN_KEY = "login_";

    public static final String LOGIN_ACCOUNT_KEY = "login_account_";

    public static final String LOGIN_ERUSER_TICKET = "login_er_ticket_";

    public static final String LOGIN_USER_KEY = "login_user_";

    public static final String LOGIN_ERUSER_KEY = "login_eruser_";

    public static final String LOGIN_JNZUSER_KEY = "login_jnzuser_";

    /* 字典缓存key */
    public static final String DIC_KEY = "GSB_DIC_KEY";

    /* 字典: 子对父id缓存 */
    public static final String DIC_FIND_PARENT_KEY = "GSB_DIC_FIND_PARENT_KEY_";

    /* 字典: type缓存 */
    public static final String GSB_DIC_TYPE_KEY = "GSB_DIC_TYPE_KEY_";

    /* 字典: type和ID缓存 */
    public static final String GSB_DIC_TYPE_ID_KEY = "GSB_DIC_TYPE_ID_KEY_";

    /* 组织机构缓存key*/
    public static final String ORGANIZATION_KEY = "GSB_ORGANIZATION_KEY";

    /* 登录次数 */
    public static final String LOGIN_CAPTCHA_TIMES = "LOGIN_CAPTCHA_TIMES_";
    public static final String LOGIN_SENDCODE_TIMES = "LOGIN_SENDCODE_TIMES_";

    public static final String LOGIN_SMS_PREV_KEY = "LOGIN_SMS_PREV_KEY_";
    public static final String LOGIN_SMS_TIMES = "LOGIN_SMS_TIMES_";

    /* 登录次数 金牛座 */
    public static final String JNZ_LOGIN_SMS_PREV_KEY = "JNZ_LOGIN_SMS_PREV_KEY_";
    public static final String JNZ_LOGIN_SMS_TIMES = "JNZ_LOGIN_SMS_TIMES_";

    /* 组织机构 */
    public static final String ALL_UC_ORGANIZATION = "ALL_UC_ORGANIZATION";

    public static final String ACCOUNT_COUPON_TIMES = "ACCOUNT_COUPON_TIMES_";
    /* 畅捷支付支持银行列表 */
    public static final String BANKLIST_KEY = "CHANPAY_BANKLIST_KEY";

    /* 磐石每小时访问次数 */
    public static final String COOKIE_PSCPS_REQS = "COOKIE_PSCPS_REQS";

    /* 渠道cookie */
    public static final String COOKIE_CHANNEL_KEY ="ICOMPANY_CHANNEL";

    //region 商标
    /* 行业和关键字分类 MAP */
    public static final String INDUSTRY_KEYS_MAP = "INDUSTRY_KEYS_MAP_";
    /* 行业关键词*/
    public static final String INDUSTRY_STR_LIST = "INDUSTRY_STR_LIST_";
    /* 抓取工信部，文化局BdDict对照表 */
    public static final String WEB_BUSINESS_MESSAGE = "WEB_BUSINESS_MESSAGE";
    /* 抓取网页信息 */
    public static final String WEB_MESSAGE = "WEB_MESSAGE_";
    /* 行业字典和关键字 */
    public static final String INDUSTRY_DICT_KEYS = "INDUSTRY_DICT_KEYS_";
    /* 505字典MAP */
    public static final String BUSINESS_TYPE_KEY_MAP = "BUSINESS_TYPE_KEY_MAP";
    //endregion


    /*  易注册domain */
    public static final String ER_DOMAIN = "ER_DOMAIN_";
    public static final String ER_LINK_PARAM = "ER_LINK_PARAM_";
    public static final String ER_LINK_ID = "ER_LINK_ID_";
    public static final String ER_LINK_WEB_PARAM = "ER_LINK_WEB_PARAM_";
    public static final String ER_LINK_WEB_ID = "ER_LINK_WEB_ID_";

    public static final String ER_USER_SELECTED_PRODUCTIDS = "ER_USER_SELECTED_PRODUCTIDS_";

    public static final String UKEY_RANDOM = "UKEY_RANDOM_";


    public static final String ICOMPANY_ACCOUNT_YJCOMPANY = "ICOMPANY_ACCOUNT_YJCOMPANY_";
    public static final String ICOMPANY_MONTH_COUNT = "ICOMPANY_MONTH_COUNT_";

    /* 金牛座公司详情页面 分支机构計算缓存 */
    public static final String JNZ_COMPANY_BRANCH_KEY = "JNZ_COMPANY_BRANCH_KEY_";
    /* 金牛座公司详情页面 增值电信牌照計算缓存 */
    public static final String JNZ_COMPANY_TELECOM_KEY = "JNZ_COMPANY_TELECOM_KEY_";
    /* 金牛座公司详情页面 娱乐类牌照計算缓存 */
    public static final String JNZ_COMPANY_ENTERTAINMENT_KEY = "JNZ_COMPANY_ENTERTAINMENT_KEY_";
    /* 金牛座公司详情页面 底部统计計算缓存 */
    public static final String JNZ_COMPANY_STATISTICS_KEY = "JNZ_COMPANY_STATISTICS_KEY_";


    /* 金牛座公司详情页面 分析页基本信息 */
    public static final String JNZ_COMPANY_BASCIINFO_KEY = "JNZ_COMPANY_BASCIINFO_KEY_";
    /* 金牛座公司详情页面 商标信息 */
    public static final String JNZ_COMPANY_TRADEMARK_KEY = "JNZ_COMPANY_TRADEMARK_KEY_";
    /* 金牛座公司详情页面 著作权信息 */
    public static final String JNZ_COMPANY_COPYRIGHT_KEY = "JNZ_COMPANY_COPYRIGHT_KEY_";
    /* 金牛座公司详情页面 年报信息 */
    public static final String JNZ_COMPANY_YEARREPORT_KEY = "JNZ_COMPANY_YEARREPORT_KEY_";
    /* 钉钉公司详情页面 年报信息 */
    public static final String DD_COMPANY_YEARREPORT_KEY = "DD_COMPANY_YEARREPORT_KEY_";

    /* 万达注册手机验证码前缀 */
    public static final String WD_REGISTER_VERIFY_CODE_PREV = "WD_REGISTER_VERIFY_CODE_PREV_";
    /* 万达忘记密码手机验证码前缀 */
    public static final String WD_FORGET_VERIFY_CODE_PREV = "WD_FORGET_VERIFY_CODE_PREV_";


    public static final String ICOMPANY_LOGIN_ACCOUNT_TIMES = "LOGIN_ACCOUNT_TIMES_";
    public static final String ICOMPANY_SMS_TIMES = "ICOMPANY_SMS_TIMES_";
    public static final String ICOMPANY_ACCOUNT_COMPANY = "ICOMPANY_ACCOUNT_COMPANY_";

    public static final String JNZ_JUMP_COMPLETE = "JNZ_JUMP_COMPLETE_";
}
