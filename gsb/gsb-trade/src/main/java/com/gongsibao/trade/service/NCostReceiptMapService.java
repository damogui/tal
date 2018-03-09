package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NCostReceiptMap;
import com.gongsibao.trade.base.INCostReceiptMapService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NCostReceiptMapService extends PersistableService<NCostReceiptMap> implements INCostReceiptMapService {

    public NCostReceiptMapService(){
        super();
        this.type=NCostReceiptMap.class;
    }
}
