package com.gongsibao.u8.service;

import java.sql.Types;

import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.dic.PayOfflineInstallmentType;
import com.gongsibao.u8.base.IOrderPayMapService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.PayReceiptStatus;
import com.gongsibao.u8.base.IPayService;

@Service
public class PayService extends PersistableService<Pay> implements IPayService {

	public PayService() {
		super();
		this.type = Pay.class;
	}

	@Override
	public Boolean changeReceiptStatus(int payId, PayReceiptStatus receiptStatus) {
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_pay");
			updateSql.set("receipt_status", receiptStatus.getValue());
			updateSql.where("pkid=" + payId);
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null) > 0;
	}

	@Override
	public int updateAuditStatus(int payId, int auditStatusId, int oldStatusId, int successStatusId) {
		String oldStatusIdWhereString = oldStatusId == 0 ? "" : " AND offline_audit_status_id=" + oldStatusId + "";
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_pay");
			updateSql.set("offline_audit_status_id", auditStatusId);
			updateSql.where("pkid=" + payId + "" + oldStatusIdWhereString + "");
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null);
	}

	@Override
	public Pay getById(int payId) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", payId, Types.INTEGER);
		}
		return this.pm.queryFirst(oql);
	}

	@Override
	public Integer addPay(Pay soPay, Integer orderId, String uploadPayVoucher) {
		Integer payId = this.save(soPay).getId();
		IOrderPayMapService soOrderPayMapService=ServiceFactory.create(IOrderPayMapService.class);
		OrderPayMap soOrderPayMap = new OrderPayMap();{
			soOrderPayMap.toNew();
			soOrderPayMap.setOrderId(orderId);
			soOrderPayMap.setPayId(payId);
			soOrderPayMap.setOfflineInstallmentType(PayOfflineInstallmentType.qk);
			soOrderPayMap.setU8BankId(0);
			soOrderPayMap.setOrderPrice(soPay.getAmount());
		}
		soOrderPayMapService.save(soOrderPayMap);
		return payId;
	}
}
