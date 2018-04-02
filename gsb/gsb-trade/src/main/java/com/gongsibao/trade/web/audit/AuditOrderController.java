package com.gongsibao.trade.web.audit;

import java.util.List;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.bd.service.auditLog.ChangeOrderPriceAudit;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.trade.base.IOrderProdService;

public class AuditOrderController extends AuditBaseController{

    // 订单（改价）审核
    AbstractAuditLogService auditLogService = AuditFactory.getAudit(ChangeOrderPriceAudit.class);

	/**
	 * 审核通过 注：参数未定
	 * 
	 * @return
	 */
	public Boolean approved(Integer auditLogId, String remark) {
        return auditLogService.audit(AuditState.PASS, auditLogId, remark);
	}

	/**
	 * 驳回 注：参数未定
	 * 
	 * @return
	 */
	public Boolean rejected(Integer auditLogId, String remark) {
        return auditLogService.audit(AuditState.NOTPASS, auditLogId, remark);
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
}
