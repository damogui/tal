package com.gongsibao.utils;

import java.text.DecimalFormat;
import java.text.Format;

import org.apache.commons.lang.StringUtils;
import org.netsharp.util.StringManager;

import static org.apache.commons.lang.math.NumberUtils.toDouble;

/**
 * Created by zhangchao on 2018/2/6.
 */
public class NumberUtils {
    private static Format FORMAT = new DecimalFormat ("#.##");

    /**
     * 数字格式化 #.##，
     *
     * @param number
     * @return
     */
    public static String numberFormat(Number number) {
        return numberFormat (number, null);
    }

    /**
     * 数字格式化
     *
     * @param number
     * @param pattern (转化格式，默认#.##，其它的自己上网查)
     * @return
     */
    public static String numberFormat(Number number, String pattern) {
        try {
            if (StringManager.isNullOrEmpty (pattern)) {
                return FORMAT.format (number);
            }
            return FORMAT.format (pattern);
        } catch (Exception e) {
            e.printStackTrace ();
            return "";
        }
    }

    public static int toInt(Object object) {
        return toInt (object, 0);
    }

    public static int toInt(Object str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Integer.parseInt (StringUtils.trimToEmpty (str.toString ()));
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }

    public static long toLong(Object object) {
        return toLong (object, 0);
    }

    public static long toLong(Object object, long defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return Long.parseLong (StringUtils.trimToEmpty (object.toString ()));
        } catch (NumberFormatException var3) {
            return defaultValue;
        }
    }

    public static int doubleRoundInt(double d) {
        if (d == 0) {
            return 0;
        }
        return (int) AmountUtils.round (d, 0);
    }

    public static long doubleRoundLong(double d) {
        if (d == 0) {
            return 0;
        }
        return (long) AmountUtils.round (d, 0);
    }

    /*进行除以100的操作并且两位小数*/
    public static String getRealMoney(Integer money) {
        Double m = (money / 100d);
        if (money == 0) {
            return "0";
        }
        DecimalFormat df = new DecimalFormat ("0.00");//######0.00
        String st = df.format (m);
        return st;
    }
}
