package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;


import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.NOrderExchangeLog;
import com.gongsibao.entity.trade.OrderDiscount;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IContractService;
import com.gongsibao.trade.base.INOrderExchangeLogService;
import com.gongsibao.trade.base.IOrderDiscountService;
import com.gongsibao.trade.base.IOrderPayMapService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.trade.base.IRefundService;
import com.gongsibao.trade.web.dto.ChangePriceDTO;

public class OrderDetailController {

	/**
	 * 根据订单Id获取合同实体
	 * @param id
	 * @return
	 */
	public Contract queryContractFirst(Integer id) {

		Oql oql = new Oql();
		{
			oql.setType(Contract.class);
			oql.setSelects("Contract.*");
			oql.setFilter("orderId=?");
			oql.setOrderby("createTime desc");
			oql.getParameters().add("orderId", id, Types.INTEGER);
		}
		IContractService contractService = ServiceFactory.create(IContractService.class);
		Contract entity = contractService.queryFirst(oql);
		return entity;
	}
	/**
	 * 根据订单Id获取关联的商机
	 * @param id
	 * @return
	 */
	public NCustomerTask queryTaskInfo(Integer id) {
		//1.获取商机Id
		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects("*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		IOrderService orderService = ServiceFactory.create(IOrderService.class);
		SoOrder orderEntity = orderService.queryFirst(oql);
		//2.根据商机Id,获取商机实体
		Oql oqlTask = new Oql();
		{
			oqlTask.setType(NCustomerTask.class);
			oqlTask.setSelects("NCustomerTask.id,NCustomerTask.customerId");
			oqlTask.setFilter("id=?");
			oqlTask.getParameters().add("id", orderEntity.getTaskId(), Types.INTEGER);
		}
		IPersister<NCustomerTask> pm = PersisterFactory.create();
		NCustomerTask taskEntity = pm.queryFirst(oqlTask);
		return taskEntity;
	}
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
			oql.setSelects("SoOrder.*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		IOrderService orderService = ServiceFactory.create(IOrderService.class);
		SoOrder entity = orderService.queryFirst(oql);
		return entity;
	}

	/**
	 * @Title: queryProductList
	 * @Description: TODO(查询产品明细)
	 * @param: @param orderId
	 * @param: @return
	 * @return: List<OrderProd>
	 * @throws
	 */
	public List<OrderProd> queryProductList(Integer orderId) {

		IOrderProdService prodService = ServiceFactory.create(IOrderProdService.class);
		return prodService.queryByOrderId(orderId);
	}

	/**
	 * @Title: queryPaymentList
	 * @Description: TODO(查询支付记录)
	 * @param: @param orderId
	 * @param: @return
	 * @return: List<OrderPayMap>
	 * @throws
	 */
	public List<Pay> queryPayList(Integer orderId) {

		List<Pay> payList = new ArrayList<Pay>();
		IOrderPayMapService payService = ServiceFactory.create(IOrderPayMapService.class);
		List<OrderPayMap> payMapList = payService.queryByOrderId(orderId);
		for (OrderPayMap orderPayMap : payMapList) {

			payList.add(orderPayMap.getPay());
		}
		return payList;
	}

	/**
	 * @Title: queryRefundList
	 * @Description: TODO(查询退款日志)
	 * @param: @param orderId
	 * @param: @return
	 * @return: List<Refund>
	 * @throws
	 */
	public List<Refund> queryRefundList(Integer orderId) {

		IRefundService refundService = ServiceFactory.create(IRefundService.class);
		return refundService.queryByOrderId(orderId);
	}

	/**
	 * @Title: queryChangePriceList
	 * @Description: TODO(查询改价审核日志)
	 * @param: @param orderId
	 * @param: @return
	 * @return: List<AuditLog>
	 * @throws
	 */
	public List<ChangePriceDTO> queryChangePriceList(Integer orderId) {

		// 根据主订单的属性判断创建DTO,需要按需查询字段
		List<ChangePriceDTO> list = new ArrayList<ChangePriceDTO>();
		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects("*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", orderId, Types.INTEGER);
		}
		IOrderService orderService = ServiceFactory.create(IOrderService.class);
		SoOrder entity = orderService.queryFirst(oql);
		if (entity != null && entity.getIsChangePrice()) {

			ChangePriceDTO dto = new ChangePriceDTO();
			dto.setId(entity.getId());
			dto.setNo(entity.getNo());
			dto.setOriginalPrice(entity.getTotalPrice());
			dto.setPayablePrice(entity.getPayablePrice());
			Integer differencePrice = entity.getTotalPrice() - entity.getPayablePrice();
			dto.setDifferencePrice(differencePrice);
			dto.setCreator(entity.getCreator());
			dto.setCreateTime(entity.getCreateTime());
			dto.setStatus(entity.getChangePriceAuditStatus());
			list.add(dto);
		}
		return list;
	}

	/**
	 * @Title: queryDiscountList
	 * @Description: TODO(查询优惠明细)
	 * @param: @param orderId
	 * @param: @return
	 * @return: List<OrderDiscount>
	 * @throws
	 */
	public List<OrderDiscount> queryDiscountList(Integer orderId) {

		IOrderDiscountService discountService = ServiceFactory.create(IOrderDiscountService.class);
		return discountService.queryByOrderId(orderId);
	}
	
	public List<NOrderExchangeLog> queryExchangeLogList(Integer orderId) {

		INOrderExchangeLogService exchangeLogService = ServiceFactory.create(INOrderExchangeLogService.class);
		return exchangeLogService.queryByOrderId(orderId);
	}
	
}
