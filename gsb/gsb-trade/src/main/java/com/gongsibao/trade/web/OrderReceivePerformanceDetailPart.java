package com.gongsibao.trade.web;

import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import com.gongsibao.trade.base.INDepReceivableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;

/**
 * Created by win on 2018/3/12.
 */
/*创建回款业绩的js控制器*/
public class OrderReceivePerformanceDetailPart extends DetailPart {

    /*NDepReceivable 业绩实体类 prodTraceService*/
    INDepReceivableService nDepReceivableService= ServiceFactory.create(INDepReceivableService.class);


    //根据订单号获取订单的支付信息针对线上支付
    public int getOnlinePayInfoBySoderOId(Integer orderId) {
        //NDepReceivable obj  = nDepReceivableService.save (null);
        return 1;
    }









}
