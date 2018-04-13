package com.gongsibao.trade.web.audit;

import java.sql.Types;
import java.util.List;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.bd.service.auditLog.RefundAudit;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.entity.trade.NDepRefund;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INDepRefundService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IRefundService;

public class AuditRefundController extends AuditBaseController{

	// 退款审核
	AbstractAuditLogService auditLogService = AuditFactory.getAudit(RefundAudit.class);

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
	
	/**
     * 根据退款Id获取退款实体
     * @param id 主键
     * @return
     */
    public Refund getRefundById(Integer id){
    	Oql oql = new Oql();
		{
			oql.setType(Refund.class);
			oql.setSelects("id,order_id,is_full_refund,payer_name,bank_no,amount,remark,setOfBooks.name");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		IRefundService refundService = ServiceFactory.create(IRefundService.class);
		Refund entity = refundService.queryFirst(oql);
		return entity;
    }
    /**
     * 根据退款Id获取退款业绩集合实体
     * @param id 主键
     * @return
     */
    public List<NDepRefund> getNDepRefund(Integer id){
    	Oql oql = new Oql();
		{
			oql.setType(NDepRefund.class);
			oql.setSelects("id,supplierId,supplier.name,departmentId,department.name,salesmanId,salesman.name,amount");
			oql.setFilter("refundId=?");
			oql.getParameters().add("refundId", id, Types.INTEGER);
		}
		INDepRefundService refundService = ServiceFactory.create(INDepRefundService.class);
		
		List<NDepRefund> refundList = refundService.queryList(oql);
		return refundList;
    }
}
