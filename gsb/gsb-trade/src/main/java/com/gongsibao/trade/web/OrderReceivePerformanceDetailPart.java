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
//    INDepReceivableService nDepReceivableService= ServiceFactory.create(INDepReceivableService.class);
//
//    public   int  saveOption(Integer soOrderId,Integer soOrderId2,Integer soOrderId3,Integer soOrderId4) {
//        //NDepReceivable obj  = nDepReceivableService.save (null);
//        return 1;
//    }


    /*回款业绩保存*/
    public int saveNDepReceivableBySoder(DepPayMapDTO entity) {

        return 1;
    }




}
