package com.gongsibao.api.dto.ma;

import java.util.HashMap;

/**
 * Created by win on 2018/2/26.
 */
/**/
public class ServiceBank {

    public static HashMap<Integer, String> getBankTypes() {
        HashMap<Integer, String> bankTypes = new HashMap<Integer, String> () {
        };
        bankTypes.put (1, "私募基金");
        bankTypes.put (2, "融资租赁");
        bankTypes.put (3, "商业保理");
        bankTypes.put (4, "典当行");
        bankTypes.put (5, "保险经纪");
        bankTypes.put (6, "保险代理");
        bankTypes.put (7, "融资担保");
        bankTypes.put (8, "小额贷款");
        bankTypes.put (9, "互联网支付");
        bankTypes.put (10, "互联网借贷");
        return bankTypes;
    }

    /*根据k返回对应的关键字*/
    public static String getBankTypesValByKey(Object key) {
        HashMap<Integer, String> bankTypes = new HashMap<Integer, String> () {
        };
        bankTypes.put (1, "基金");
        bankTypes.put (2, "融资租赁");
        bankTypes.put (3, "商业保理");
        bankTypes.put (4, "典当");
        bankTypes.put (5, "保险经纪");
        bankTypes.put (6, "保险代理");
        bankTypes.put (7, "融资担保");
        bankTypes.put (8, "小额贷款");
        bankTypes.put (9, "互联网支付");
        bankTypes.put (10, "互联网借贷");
        return bankTypes.get (key);
    }


}
