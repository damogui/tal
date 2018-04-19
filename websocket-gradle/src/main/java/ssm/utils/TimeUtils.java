package ssm.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String getTimeNow() {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(new Date());
    }



    public static String getRealMoney(Integer num) {

        double d=num/100d;
        DecimalFormat df=new DecimalFormat("0.00");
        return df.format(d);
    }
}
