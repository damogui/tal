package com.gongsibao.u8.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;
import com.gongsibao.u8.base.ISoOrderService;

@Service
public class SoOrderService extends PersistableService<SoOrder> implements ISoOrderService {

	public SoOrderService() {
		super();
		this.type = SoOrder.class;
	}

	@Override
	public Boolean updateManuaVoucherStatus(Integer orderId, OrderManualVoucherStatus status) {

		UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
		{
			updateBuilder.update("so_order");
			updateBuilder.set("manual_voucher_status", status.getValue());
			updateBuilder.where("pkid=" + orderId);
		}

		String cmdText = updateBuilder.toSQL();
		return this.pm.executeNonQuery(cmdText, null) > 0;
	}
	
	
}
