package com.gongsibao.product.service;

import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.Price;
import com.gongsibao.product.base.IPriceService;

@Service
public class PriceService extends PersistableService<Price> implements IPriceService {

    public PriceService(){
        super();
        this.type=Price.class;
    }

	@Override
	public List<Price> queryServicePriceItem(Integer productId, Integer cityId) {
		// TODO Auto-generated method stub
		return null;
	}
}