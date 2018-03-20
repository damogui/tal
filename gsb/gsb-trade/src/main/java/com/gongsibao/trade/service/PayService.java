package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.trade.base.IPayService;
import org.netsharp.util.StringManager;

import java.util.List;

@Service
public class PayService extends PersistableService<Pay> implements IPayService {

    public PayService(){
        super();
        this.type=Pay.class;
    }
//    @Override
//    public List<Pay> queryList(Oql oql) {
//        oql.setSelects ("orderIds,id,payForOrderCount,payWayType,amount,u8BankId,offlineAuditStatus,createTime,creator,orderPayMaps.*,u8Bank.*");
////        oql.setSelects ("u8Bank.*,");
//       // oql.setSelects ("orderPayMaps.*");
//        //List<Pay>  list= super.queryList (oql);
//
//
//        List<Pay> list = super.queryList (oql);
//
//       return list;
//    }



}