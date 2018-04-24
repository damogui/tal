package com.gongsibao.rest.web.common.constant;

import java.io.Serializable;

/**
 * Created by wk on 2016/4/18.
 */
public class CacheTime implements Serializable {
    private static final long serialVersionUID = -2166245553603893963L;

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

}
