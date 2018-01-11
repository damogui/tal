package com.gongsibao.bd.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;

@Service
public class AuditLogService extends PersistableService<AuditLog> implements IAuditLogService {

	public AuditLogService() {
		super();
		this.type = AuditLog.class;
	}

	@Override
	public List<AuditLog> queryList(Oql oql) {

		StringBuilder selects = new StringBuilder();
		selects.append("AuditLog.*,");
		selects.append("AuditLog.soOrder.*,");
		selects.append("AuditLog.contract.*,");
		selects.append("AuditLog.contract.soOrder.*,");
		selects.append("AuditLog.contract.soOrder.products.*,");		
		selects.append("AuditLog.invoice.*,");
		selects.append("AuditLog.invoice.orderInvoiceMaps.*,");
		selects.append("AuditLog.invoice.orderInvoiceMaps.soOrder.*");		
//		selects.append("AuditLog.pay.*,");
//		selects.append("AuditLog.pay.orderPayMaps.*,");
//		selects.append("AuditLog.pay.orderPayMaps.soOrder.*");
//		oql.setSelects(selects.toString());

		List<AuditLog> resList = super.queryList(oql);

		if (CollectionUtils.isNotEmpty(resList)) {
			AuditLog auditLog = resList.get(0);
			// 合同审核时
			if (auditLog.getTypeId().equals(AuditLogType.Htsq)) {
				for (AuditLog auditItem : resList) {
					SoOrder order = auditItem.getSoOrder();
					Integer contractPrice = 0;
					if (order != null) {
						List<OrderProd> orderProducts = auditItem.getSoOrder().getProducts();
						if (CollectionUtils.isNotEmpty(orderProducts)) {
							for (OrderProd orderProd : orderProducts) {
								contractPrice = contractPrice + orderProd.getPrice();
							}
							Contract contract = auditItem.getContract();
							if (contract != null) {
								contract.setContractPrice(contractPrice);
							}
						}
					}
				}
			}
		}

		//return super.queryList(oql);
		return resList;
	}

	@Override
	public int updateStatus(Integer id, Integer status, Integer oldStatus, String remark) {
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("bd_audit_log");
			updateSql.set("status_id", status);
			updateSql.set("add_time", new Date());
			updateSql.set("remark", remark);
			updateSql.where("pkid=" + id + " AND status_id=" + oldStatus);
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null);
	}

	@Override
	public int updateStatusByFidTidLev(Integer formId, Integer typeId, Integer level, Integer status, String levelLogic, Integer exceptId) {
		String exceptIdWhereString = exceptId.equals(0) ? " " : " AND pkid !=" + exceptId + " ";
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("bd_audit_log");
			updateSql.set("status_id", status);
			updateSql.where("form_id=" + formId + " AND type_id=" + typeId + " AND level " + levelLogic + " " + level + " " + exceptIdWhereString + "");
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null);
	}
}