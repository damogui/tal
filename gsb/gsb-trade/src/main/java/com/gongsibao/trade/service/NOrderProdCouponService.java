package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NOrderProdCoupon;
import com.gongsibao.trade.base.INOrderProdCouponService;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NOrderProdCouponService extends PersistableService<NOrderProdCoupon> implements INOrderProdCouponService {

    public NOrderProdCouponService(){
        super();
        this.type=NOrderProdCoupon.class;
    }
}
