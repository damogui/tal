package com.gongsibao.trade.web.audit;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.bd.service.auditLog.AbstractAuditService;
import com.gongsibao.bd.service.auditLog.AuditServiceFactory;
import com.gongsibao.bd.service.auditLog.AuditStageService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.NOrderStage;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INOrderStageService;
import com.gongsibao.trade.web.dto.OrderStageDTO;
import com.gongsibao.u8.base.ISoOrderService;

public class AuditStageController extends AuditBaseController{

	@Override
	protected AbstractAuditService getAuditService() {

		return AuditServiceFactory.create(AuditStageService.class);
	}
	
	ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);

	INOrderStageService stageService = ServiceFactory.create(INOrderStageService.class);
	
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
			oql.setSelects("id,payablePrice,paidPrice,stageNum,stages.{id,instalmentIndex,percentage,amount}");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		SoOrder entity = orderService.queryFirst(oql);
		return entity;
	}
	/**
	 * 返回订单分期（由于分期关联订单主键，分期审核驳回后，再申请会把之前的驳回的分期信息查出来，目前解决方法是分组取最新的分期记录）
	 * @param id
	 * @return
	 */
	public OrderStageDTO getOrderStage(Integer orderId) {
		OrderStageDTO stageDTO = new OrderStageDTO();
		//1.获取订单信息
		Oql oql = new Oql(); 
		{
			oql.setType(SoOrder.class);
			oql.setSelects("id,payablePrice,paidPrice,stageNum");
			oql.setFilter("id=?");
			oql.getParameters().add("id", orderId, Types.INTEGER);
		}
		SoOrder entity = orderService.queryFirst(oql);
		stageDTO.setOrderId(entity.getId());
		stageDTO.setPayablePrice(entity.getPayablePrice());
		stageDTO.setPaidPrice(entity.getPaidPrice());
		
		//2.获取该订单的最近的审核信息（过滤之前驳回的）
		Oql oqlStage = new Oql(); 
		{
			oqlStage.setType(NOrderStage.class);
			oqlStage.setSelects("id,instalmentIndex,percentage,amount,createTime");
			oqlStage.setFilter("id in(SELECT max(id) from so_order_stage where order_id = ? GROUP BY instalment_index)");
			oqlStage.getParameters().add("order_id", orderId, Types.INTEGER);
		}
		List<NOrderStage> stageList = stageService.queryList(oqlStage);
		stageDTO.setStageNum(stageList.size());
		stageDTO.setStages(stageList);
		return stageDTO;
	}
	
	@Override
	public List<AuditLog> getAuditLogList(Integer id, AuditLogType type) {
		List<AuditLog> logList = new ArrayList<AuditLog>();
		Oql oql = new Oql();
		{
			oql.setType(AuditLog.class);
			oql.setSelects("auditLog.*,employee.{id,name}");
			oql.setFilter("pkid in(SELECT max(pkid) from bd_audit_log where form_id = ? and type_id = ? GROUP BY `level`)");
			oql.getParameters().add("form_id", id, Types.INTEGER);
			oql.getParameters().add("type_id", type.getValue(), Types.INTEGER);
		}
		logList = auditService.queryList(oql);
		return logList;
	}
}
