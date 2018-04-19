package com.gongsibao.rest.web.common.util;

import java.text.DecimalFormat;
import java.text.Format;

/**
 * Created by wk on 2016/4/22.
 */
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {
    private static Format FORMAT = new DecimalFormat("#.##");

    /**
     * 数字格式化 #.##，
     *
     * @param number
     * @return
     */
    public static String numberFormat (Number number) {
        return numberFormat(number, null);
    }

    /**
     * 数字格式化
     *
     * @param number
     * @param pattern (转化格式，默认#.##，其它的自己上网查)
     * @return
     */
    public static String numberFormat (Number number, String pattern) {
        try {
            if (StringUtils.isBlank(pattern)) {
                return FORMAT.format(number);
            }
            return FORMAT.format(pattern);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int toInt (Object object) {
        return toInt(object, 0);
    }

    public static int toInt (Object object, int defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        return toInt(StringUtils.trimToEmpty(object), defaultValue);
    }

    public static long toLong (Object object) {
        return toLong(object, 0);
    }

    public static long toLong (Object object, long defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        return toLong(StringUtils.trimToEmpty(object), defaultValue);
    }

    public static double getRealMoney(Integer money) {
        double m = toDouble(String.valueOf(money));
        if (m == 0) {
            return m;
        }
        return m / 100d;
    }

    public static int doubleRoundInt(String str) {
        return doubleRoundInt(toDouble(str));
    }

    public static int doubleRoundInt(double  d) {
        if (d == 0) {
            return 0;
        }
        return (int) AmountUtils.round(d, 0);
    }

    public static long doubleRoundLong(double  d) {
        if (d == 0) {
            return 0;
        }
        return (long) AmountUtils.round(d, 0);
    }
}

