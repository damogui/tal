package com.gongsibao.trade.web;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.trade.base.IOrderProdTraceService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;
import org.netsharp.panda.commerce.FormDetailPart;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.panda.commerce.ListPart;

import java.util.List;

/**
 * Created by win on 2018/3/6.
 */
public class OrderPerformanceDetailPart extends DetailPart {


    /*NDepReceivable 业绩实体类 prodTraceService*/
    INDepReceivableService   nDepReceivableService= ServiceFactory.create(INDepReceivableService.class);

    public   int  saveOption(Integer soOrderId,Integer soOrderId2,Integer soOrderId3,Integer soOrderId4) {
        //NDepReceivable obj  = nDepReceivableService.save (null);
        return 1;
    }
}
