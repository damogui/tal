package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.RefundItem;
import com.gongsibao.trade.base.IRefundItemService;

@Service
public class RefundItemService extends PersistableService<RefundItem> implements IRefundItemService {

    public RefundItemService(){
        super();
        this.type=RefundItem.class;
    }

	@Override
	public List<RefundItem> queryByRefundId(Integer refundId) {
		List<RefundItem> itemList = new ArrayList<RefundItem>();
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("RefundItem.*,orderProd.*");
			oql.setFilter("refundId=?");
			oql.getParameters().add("refundId", refundId, Types.INTEGER);
		}
		itemList = this.queryList(oql);
		return itemList;
	}
}