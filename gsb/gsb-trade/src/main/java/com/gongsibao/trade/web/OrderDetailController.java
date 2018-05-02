package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.gongsibao.bd.base.IFileService;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.trade.*;
import com.gongsibao.entity.trade.dto.SoOrderDTO;
import com.gongsibao.trade.base.*;
import com.gongsibao.trade.web.dto.AuditLogDTO;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.trade.web.dto.ChangePriceDTO;

public class OrderDetailController {

	/**
	 * 根据订单Id获取合同实体
	 *
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
	 *
	 * @param id
	 * @return
	 */
	public NCustomerTask queryTaskInfo(Integer id) {
		// 1.获取商机Id
		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects("*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		IOrderService orderService = ServiceFactory.create(IOrderService.class);
		SoOrder orderEntity = orderService.queryFirst(oql);
		// 2.根据商机Id,获取商机实体
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
	 * @throws
	 * @Title: getSoOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param id
	 * @param: @return
	 * @return: SoOrder
	 */
	public SoOrder getSoOrder(Integer id) {

		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects("SoOrder.*,customer.{pkid,real_name,email}");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		IOrderService orderService = ServiceFactory.create(IOrderService.class);
		SoOrder entity = orderService.queryFirst(oql);
		return entity;
	}

	/**
	 * @throws
	 * @Title: queryProductList
	 * @Description: TODO(查询产品明细)
	 * @param: @param orderId
	 * @param: @return
	 * @return: List<OrderProd>
	 */
	public List<OrderProd> queryProductList(Integer orderId) {

		IOrderProdService prodService = ServiceFactory.create(IOrderProdService.class);
		return prodService.queryByOrderId(orderId);
	}

	/**
	 * @throws
	 * @Title: queryPaymentList
	 * @Description: TODO(查询支付记录)
	 * @param: @param orderId
	 * @param: @return
	 * @return: List<OrderPayMap>
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
	 * @throws
	 * @Title: queryRefundList
	 * @Description: TODO(查询退款日志)
	 * @param: @param orderId
	 * @param: @return
	 * @return: List<Refund>
	 */
	public List<Refund> queryRefundList(Integer orderId) {

		IRefundService refundService = ServiceFactory.create(IRefundService.class);
		return refundService.queryByOrderId(orderId);
	}

	/**
	 * @throws
	 * @Title: queryChangePriceList
	 * @Description: TODO(查询改价审核日志)
	 * @param: @param orderId
	 * @param: @return
	 * @return: List<AuditLog>
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
	 * @throws
	 * @Title: queryDiscountList
	 * @Description: TODO(查询优惠明细)
	 * @param: @param orderId
	 * @param: @return
	 * @return: List<OrderDiscount>
	 */
	public List<OrderDiscount> queryDiscountList(Integer orderId) {

		IOrderDiscountService discountService = ServiceFactory.create(IOrderDiscountService.class);
		return discountService.queryByOrderId(orderId);
	}

	public List<NOrderExchangeLog> queryExchangeLogList(Integer orderId) {

		INOrderExchangeLogService exchangeLogService = ServiceFactory.create(INOrderExchangeLogService.class);
		return exchangeLogService.queryByOrderId(orderId);
	}

	/* 订单业绩详情 */
	public List<AuditLogDTO> queryOrderPerList(Integer orderId) {

		List<AuditLogDTO> auditLogDTOList = new ArrayList<AuditLogDTO>();
		INDepReceivableService nDepReceivableService = ServiceFactory.create(INDepReceivableService.class);
		Oql oql = new Oql();
		oql.setType(NDepReceivable.class);
		StringBuilder sb = new StringBuilder();
		sb.append("NDepReceivable.*,");
		sb.append("NDepReceivable.soOrder.*,");
		// sb.append ("NDepReceivable.soOrder.companyIntention.name,");
		sb.append("NDepReceivable.soOrder.owner.name");
		oql.setSelects(sb.toString());
		// oql.setSelects (selects.toString ());
		// oql.setFilter(String.format("salesman_id=%s  ",
		// SessionManager.getUserId()));
		oql.setFilter("order_id=?");

		oql.getParameters().add("@order_id", orderId, Types.INTEGER);

		List<NDepReceivable> ndepRevs = nDepReceivableService.queryList(oql);
		for (NDepReceivable item : ndepRevs) {
			AuditLogDTO auditLogDTO = new AuditLogDTO();
			auditLogDTO.setOrderId(item.getOrderId());
			// auditLogDTO.setAuditNo (item.getId ().toString ());
			auditLogDTO.setTotalPrice(item.getSoOrder().getTotalPrice());
			auditLogDTO.setPayablePrice(item.getSoOrder().getPayablePrice());
			auditLogDTO.setAmount(item.getSoOrder().getPayablePrice());
			auditLogDTO.setStatusType(item.getStatusType().getText());
			auditLogDTO.setCreateTime(item.getCreateTime());
			if (item.getStatusType().equals(AuditLogStatusType.AUDITPASS)) {
				auditLogDTO.setConfirmTime(item.getAuditTime().toString());
			} else {
				auditLogDTO.setConfirmTime("");
			}

			auditLogDTO.setCreator(item.getCreator());
			auditLogDTOList.add(auditLogDTO);
		}

		return auditLogDTOList;
	}

	/* 回款业绩详情 */
	public List<AuditLogDTO> queryOrderPayPerList(Integer orderId) {

		List<AuditLogDTO> auditLogDTOList = new ArrayList<AuditLogDTO>();
		INDepPayService nDepPayService = ServiceFactory.create(INDepPayService.class);
		Oql oql = new Oql();
		oql.setType(NDepPay.class);
		StringBuilder sb = new StringBuilder();
		sb.append("NDepPay.*,");
		sb.append("NDepPay.soOrder.*,");
		// sb.append ("NDepReceivable.soOrder.companyIntention.name,");
		sb.append("NDepPay.soOrder.owner.name");
		oql.setSelects(sb.toString());
		// oql.setSelects (selects.toString ());
		// oql.setFilter(String.format("salesman_id=%s or creator_id =%s ",
		// SessionManager.getUserId(), SessionManager.getUserId()));

		oql.setFilter("order_id=?");
		oql.getParameters().add("@order_id", orderId, Types.INTEGER);
		List<NDepPay> nDepPays = nDepPayService.queryList(oql);
		for (NDepPay item : nDepPays) {
			AuditLogDTO auditLogDTO = new AuditLogDTO();
			auditLogDTO.setOrderId(item.getOrderId());
			// auditLogDTO.setAuditNo (item.getId ().toString ());
			auditLogDTO.setTotalPrice(item.getSoOrder().getTotalPrice());
			auditLogDTO.setPayablePrice(item.getSoOrder().getPayablePrice());
			auditLogDTO.setAmount(item.getSoOrder().getPayablePrice());
			auditLogDTO.setStatusType(item.getStatusType().getText());
			auditLogDTO.setCreateTime(item.getCreateTime());
			if (item.getStatusType().equals(AuditLogStatusType.AUDITPASS)) {
				auditLogDTO.setConfirmTime(item.getAuditTime().toString());
			} else {
				auditLogDTO.setConfirmTime("");
			}

			auditLogDTO.setCreator(item.getCreator());
			auditLogDTOList.add(auditLogDTO);
		}

		return auditLogDTOList;
	}

	/* 获取订单的公司信息 */
	public List<SoOrderDTO> getCompanyInfo(Integer orderId) {

		IOrderService orderService = ServiceFactory.create(IOrderService.class);
		String sels = "SoOrder.{pkid,companyId},SoOrder.companyIntention.*";
		SoOrder soOrder = orderService.getSoOrderById(orderId, sels);
		List<SoOrderDTO> list = new ArrayList<>();
		if (soOrder != null && soOrder.getCompanyId() != 0) {
			SoOrderDTO soOrderDTO = new SoOrderDTO();
			soOrderDTO.setId(soOrder.getId());
			soOrderDTO.setCompanyId(soOrder.getCompanyId());
			if (soOrder.getCompanyIntention() != null) {
				if (soOrder.getCompanyIntention().getCompanyName() != null) {
					soOrderDTO.setCompanyName(soOrder.getCompanyIntention().getCompanyName());
				} else {
					soOrderDTO.setCompanyName("");
				}

			} else {

				soOrderDTO.setCompanyName("");
			}

			list.add(soOrderDTO);
		}
		return list;

	}

	/**   
	 * @Title: optCompanyInfoByorderId   
	 * @Description: TODO(操作公司信息orderId 订单id comId >0添加0删除 )   
	 * @param: @param orderId
	 * @param: @param comId
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	public int optCompanyInfoByorderId(Integer orderId, Integer comId) {

		IPersister<SoOrder> orderService = PersisterFactory.create();
		String sql = "UPDATE  so_order  SET  company_id=?  WHERE  pkid=?";
		QueryParameters qps = new QueryParameters();

		qps.add("@company_id", comId, Types.INTEGER);
		qps.add("@pkid", orderId, Types.INTEGER);
		int num = orderService.executeNonQuery(sql, qps);
		return num;

	}

	/**   
	 * @Title: queryStageList   
	 * @Description: TODO(根据订单Id获取分期信息)   
	 * @param: @param orderId
	 * @param: @return      
	 * @return: List<NOrderStage>      
	 * @throws   
	 */
	public List<NOrderStage> queryStageList(Integer orderId) {

		INOrderStageService stageService = ServiceFactory.create(INOrderStageService.class);
		return stageService.getListByOrderId(orderId);
	}
	
	/**   
	 * @Title: queryOrderFileList   
	 * @Description: TODO(查询订单附件)   
	 * @param: @param orderId
	 * @param: @return      
	 * @return: List<File>      
	 * @throws   
	 */
	public List<File> queryOrderFileList(Integer orderId) {

		IFileService fileService = ServiceFactory.create(IFileService.class);
		return fileService.getByTabNameFormId("so_order",orderId);
	}
	
}
