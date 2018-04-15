package com.gongsibao.trade.web.audit;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.bd.service.auditLog.RefundAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NDepRefund;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.RefundItem;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.trade.base.INDepRefundService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IRefundItemService;
import com.gongsibao.trade.base.IRefundService;

public class AuditRefundController extends AuditBaseController {

	// 退款审核
	AbstractAuditLogService auditLogService = AuditFactory.getAudit(RefundAudit.class);
	ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
	
	/**
	 * 判断当前登录人的角色是否是收银员专员
	 * @return
	 */
	public Boolean isFinancialRole(){
		List<Integer> stkzyIds = salesmanService.getEmployeeIdListByRoleCodes(Arrays.asList("Platform_Finance_STKZY"));
        for (Integer stkzyId : stkzyIds) {
        	if(stkzyId.equals(SessionManager.getUserId())){
        		return true;
        	}
        }
        return false;
	}
	/**
	 * 审核通过 注：参数未定
	 * 
	 * @return
	 */
	public Boolean approvedRefund(Integer auditLogId, String remark,Integer settlementMethod) {
		Boolean getResult = approved(auditLogId,remark);
		// 不管审核是否成功，都修改选择的结算方式
		updateRefundSettle(auditLogId,settlementMethod);
		return getResult;
	}
	
	/**
	 * 驳回 注：参数未定
	 * 
	 * @return
	 */
	public Boolean rejectedRefund(Integer auditLogId, String remark,Integer settlementMethod) {
		Boolean getResult = rejected(auditLogId, remark);
		// 不管审核是否成功，都修改选择的结算方式
		updateRefundSettle(auditLogId,settlementMethod);
		return getResult;
	}
	/**
	 * 不管审核是否成功，都修改选择的结算方式
	 * @param auditLogId
	 * @param settlementMethod
	 */
	private void updateRefundSettle(Integer auditLogId,Integer settlementMethod){
		AuditLog auditLog = auditService.byId(auditLogId);
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_refund");
			updateSql.set("settlement_method", settlementMethod);
			updateSql.where("pkid =" + auditLog.getFormId());
		}
		String cmdText = updateSql.toSQL();
		IPersister<Refund> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
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
	 * @Title: queryProductList
	 * @Description: TODO(查询退单项列表)
	 * @param: @param orderId
	 * @param: @return
	 * @return: List<OrderProd>
	 * @throws
	 */
	public List<RefundItem> querySoRefundItemList(Integer refundId) {
		IRefundItemService refundItemService = ServiceFactory.create(IRefundItemService.class);
		return refundItemService.queryByRefundId(refundId);
	}

	/**
	 * 根据退款Id获取退款实体
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public Refund getRefundById(Integer id) {
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
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public List<NDepRefund> getNDepRefund(Integer id) {
		Oql oql = new Oql();
		{
			oql.setType(NDepRefund.class);
			oql.setSelects("id,supplierId,supplier.name,departmentId,department.name,salesmanId,salesman.name,amount");
			oql.setFilter("refundId=?");
			oql.getParameters().add("refundId", id, Types.INTEGER);
		}
		INDepRefundService refundService = ServiceFactory
				.create(INDepRefundService.class);

		List<NDepRefund> refundList = refundService.queryList(oql);
		return refundList;
	}

	@Override
	public Boolean approved(Integer auditLogId, String remark) {
		return auditLogService.audit(AuditState.PASS, auditLogId,remark);
	}

	@Override
	public Boolean rejected(Integer auditLogId, String remark) {		
		return auditLogService.audit(AuditState.NOTPASS, auditLogId, remark);
	}
}
