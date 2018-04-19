package com.gongsibao.trade.web;

import java.sql.Types;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;

public class OrderCarryoverController {

	IOrderService orderService = ServiceFactory.create(IOrderService.class);

	/**
	 * @Title: getSoOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param id
	 * @param: @return
	 * @return: SoOrder
	 * @throws
	 */
	public SoOrder getSoOrder(Integer id) {

		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects("id,no,payablePrice,paidPrice,refundPrice,carryAmount,carryIntoAmount");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		SoOrder entity = orderService.queryFirst(oql);
		return entity;
	}
	
	/**
	 * 根据订单编号获取订单实体
	 * @param orderNo
	 * @return 0-通过;1-去向订单号输入有误;2-去向订单待审核状态;3-去向订单号已创建订单业绩审核状态;4-去向订单号改价审核状态
	 */
	public Integer getSoOrderByNo(String orderNo) {
		Integer resultValueInteger = 0;
		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects("*");
			oql.setFilter("no=?");
			oql.getParameters().add("no", orderNo, Types.VARCHAR);
		}
		SoOrder entity = orderService.queryFirst(oql);
		if(entity == null){
			return 1;
		}else{
			//1.判断去向订单的审核状态
			Integer carryStatus = entity.getCarryStatus().getValue();
			if(!carryStatus.equals(0) && !carryStatus.equals(1054) && !carryStatus.equals(1053)){
				return 2;
			}
			//2.判断去向订单的业绩审核状态
			Integer depReceivableAuditStatus = entity.getDepReceivableAuditStatusId().getValue();
			if(depReceivableAuditStatus.equals(1051) || depReceivableAuditStatus.equals(1054)){
				return 3;
			}
			//3.判断去向订单的改价审核状态
			Integer changePriceAuditStatus = entity.getChangePriceAuditStatus().getValue();
			if(!changePriceAuditStatus.equals(0) && !changePriceAuditStatus.equals(1054) && !changePriceAuditStatus.equals(1053)){
				return 4;
			}
		}
		return resultValueInteger;
	}
	
	/**
	 * @Title: applyStage
	 * @Description: TODO(申请结转)
	 * @param: @param soOrder
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean applyCarryover(NOrderCarryover orderCarryover) {

		return orderService.applyCarryover(orderCarryover);
	}
}
