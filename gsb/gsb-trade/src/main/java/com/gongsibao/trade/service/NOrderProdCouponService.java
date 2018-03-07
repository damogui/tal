package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NOrderProdCoupon;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INOrderProdCouponService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

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
