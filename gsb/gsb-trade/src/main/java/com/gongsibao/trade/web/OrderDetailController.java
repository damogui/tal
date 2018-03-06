package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.OrderDiscount;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderDiscountService;
import com.gongsibao.trade.base.IOrderPayMapService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.trade.base.IRefundService;

public class OrderDetailController {

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

		IOrderProdService prodService =  ServiceFactory.create(IOrderProdService.class);
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
	public List<OrderPayMap> queryPaymentList(Integer orderId) {

		IOrderPayMapService payService =  ServiceFactory.create(IOrderPayMapService.class);
		return payService.queryByOrderId(orderId);
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

		IRefundService refundService =  ServiceFactory.create(IRefundService.class);
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
	public List<AuditLog> queryChangePriceList(Integer orderId) {

		IAuditLogService auditLogService =  ServiceFactory.create(IAuditLogService.class);
		return auditLogService.queryByFormId(orderId, AuditLogType.Ddgj);
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

		IOrderDiscountService discountService =  ServiceFactory.create(IOrderDiscountService.class);
		return discountService.queryByOrderId(orderId);
	}
}
