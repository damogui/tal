package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.entity.trade.NOrderExchangeLog;
import com.gongsibao.trade.base.INOrderExchangeLogService;

/**
 * Created by zhangchao on 2018/3/8.
 */
@Service
public class NOrderExchangeLogService extends SupplierPersistableService<NOrderExchangeLog> implements INOrderExchangeLogService {

    public NOrderExchangeLogService() {
        super();
        this.type = NOrderExchangeLog.class;
    }

	@Override
	public List<NOrderExchangeLog> queryByOrderId(Integer orderId) {
	
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("orderId=?");
			oql.getParameters().add("orderId", orderId, Types.INTEGER);
		}
		return this.queryList(oql);
	}

}
