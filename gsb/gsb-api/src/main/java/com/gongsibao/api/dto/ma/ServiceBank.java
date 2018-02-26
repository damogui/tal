package com.gongsibao.api.dto.ma;

import java.util.HashMap;

/**
 * Created by win on 2018/2/26.
 */
/**/
public  class ServiceBank {

    public static HashMap<String,String> getBankTypes(){
        HashMap<String,String>bankTypes=new HashMap<String, String> (){};
        bankTypes.put ("基金","私募基金");
        bankTypes.put ("融资租赁","融资租赁");
        bankTypes.put ("商业保理","商业保理");
        bankTypes.put ("典当","典当行");
        bankTypes.put ("保险经纪","保险经纪");
        bankTypes.put ("保险代理","保险代理");
        bankTypes.put ("融资担保","融资担保");
        bankTypes.put ("小额贷款","小额贷款");
        bankTypes.put ("互联网支付","互联网支付");
        bankTypes.put ("互联网借贷","互联网借贷");
        return bankTypes;


    }





}
