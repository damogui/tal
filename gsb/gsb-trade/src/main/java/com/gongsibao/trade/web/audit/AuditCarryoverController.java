package com.gongsibao.trade.web.audit;

import java.sql.Types;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.bd.service.auditLog.AbstractAuditService;
import com.gongsibao.bd.service.auditLog.AuditServiceFactory;
import com.gongsibao.bd.service.auditLog.AuditCarryoverService;
import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.trade.base.INOrderCarryoverService;

public class AuditCarryoverController extends AuditBaseController {
	

	@Override
	protected AbstractAuditService getAuditService() {

		return AuditServiceFactory.create(AuditCarryoverService.class);
	}
	
	/**
	 * @Title: getNOrderCarryover
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param id
	 * @param: @return
	 * @return: NOrderCarryover
	 * @throws
	 */
	public NOrderCarryover getNOrderCarryover(Integer id) {
		
		Oql oql = new Oql();
		{
			oql.setType(NOrderCarryover.class);
			oql.setSelects("*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}

		INOrderCarryoverService carryoverService = ServiceFactory.create(INOrderCarryoverService.class);
		NOrderCarryover entity = carryoverService.queryFirst(oql);
		return entity;
	}
}
