package com.gongsibao.trade.web.audit;

import java.util.List;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.bd.base.IFileService;
import com.gongsibao.bd.service.auditLog.AbstractAuditService;
import com.gongsibao.bd.service.auditLog.AuditServiceFactory;
import com.gongsibao.bd.service.auditLog.AuditOrderService;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.trade.NOrderStage;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.trade.base.INOrderStageService;
import com.gongsibao.trade.base.IOrderProdService;

public class AuditOrderController extends AuditBaseController{


	@Override
	protected AbstractAuditService getAuditService() {

		return AuditServiceFactory.create(AuditOrderService.class);
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
