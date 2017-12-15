package com.gongsibao.u8.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.SoOrderVoucherFollow;
import com.gongsibao.u8.base.ISoOrderVoucherFollowService;

@Service
public class SoOrderVoucherFollowService extends PersistableService<SoOrderVoucherFollow> implements ISoOrderVoucherFollowService {

	public SoOrderVoucherFollowService() {
		super();
		this.type = SoOrderVoucherFollow.class;
	}

	//添加跟进记录
	@Override
	public Boolean addOrderVoucherFollowLog(int orderId, String content) {
		SoOrderVoucherFollow soOrderVoucherFollow = new SoOrderVoucherFollow();
		soOrderVoucherFollow.toNew();
		soOrderVoucherFollow.setOrderId(orderId);
		soOrderVoucherFollow.setContent(content);
		soOrderVoucherFollow = this.save(soOrderVoucherFollow);
		return soOrderVoucherFollow.getId() > 0;
	}

	//获取当前登录人的跟进记录
	@Override
	public List<SoOrderVoucherFollow> getOrderVoucherFollowLogByUserId() {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("creator_id='{userId}'");
		}

		return this.queryList(oql);
	}

}
