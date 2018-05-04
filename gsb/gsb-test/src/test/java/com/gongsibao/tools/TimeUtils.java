package com.gongsibao.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guojia on 2018/4/10.
 * 时间的帮助类
 */
public class TimeUtils {

    /*时间格式化*/
    public static String getDateFormat(Date date) {
//long timeStamp = 1495777335060;//直接是时间戳
        long timeStamp = System.currentTimeMillis ();  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        String sd = sdf.format (date);   // 时间戳转换成时间
        return sd;
    }

    /*字符串时间化 str 转化的字符串 type为类型0yyyy年MM月dd日 1 yyyy-MM-dd HH:mm:ss*/
    public static Date getDateByStr(String str, Integer type) {
        String strDate = "2005年04月22日";
        //注意：SimpleDateFormat构造函数的样式与strDate的样式必须相符
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ();
        SimpleDateFormat sDateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); //加上时间
        //必须捕获异常
        Date date = new Date ();
        try {
            if (type == 0) {
                date = simpleDateFormat.parse (str);
            } else {
                date = sDateFormat.parse (str);
            }

            System.out.println (date);
        } catch (ParseException px) {
            px.printStackTrace ();
        }
        return date;
    }

    /*时间格式化  处理这种错误时间 2017-03-27 16:56:14.0*/
    public static Date getDateTranFormt(Date date) {

//long timeStamp = 1495777335060;//直接是时间戳
        long timeStamp = System.currentTimeMillis ();  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        String sd = sdf.format (date);   // 时间戳转换成时间
        Date dateTran = getDateByStr (sd, 1);
        return dateTran;
    }

}
