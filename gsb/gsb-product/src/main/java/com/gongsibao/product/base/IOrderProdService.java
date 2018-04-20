package com.gongsibao.product.base;

import com.gongsibao.entity.product.IncomeSettle;
import com.gongsibao.entity.trade.OrderProd;
import org.netsharp.base.IPersistableService;

public interface IOrderProdService extends IPersistableService<OrderProd> {
    OrderProd getById(Integer Id);
}
