package com.gongsibao.trade.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderProd;

public interface IOrderProdService extends IPersistableService<OrderProd> {

	Map<Integer, String> getProductCityNameByOrderIds(List<Integer> orderIdList);
}