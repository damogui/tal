package com.gongsibao.taurus.util;

import java.text.DecimalFormat;
import java.text.Format;

public class NumberUtils {
    private static Format FORMAT = new DecimalFormat("#.##");

    /**
     * 数字格式化 #.##，
     *
     * @param number
     * @return
     */
    public static String numberFormat(Number number) {
        return numberFormat(number, null);
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
            if (StringManager.isNullOrEmpty(pattern)) {
                return FORMAT.format(number);
            }
            return FORMAT.format(pattern);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int toInt(Object object) {
        return toInt(object, 0);
    }

    public static int toInt(Object str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Integer.parseInt(String.valueOf(str));
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }

    public static long toLong(Object object) {
        return toLong(object, 0);
    }

    public static long toLong(Object object, long defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return Long.parseLong(String.valueOf(object));
        } catch (NumberFormatException var3) {
            return defaultValue;
        }
    }
}
