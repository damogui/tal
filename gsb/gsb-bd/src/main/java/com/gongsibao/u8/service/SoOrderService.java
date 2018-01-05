package com.gongsibao.u8.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;
import com.gongsibao.u8.base.ISoOrderService;

@Service
public class SoOrderService extends PersistableService<SoOrder> implements ISoOrderService {

	public SoOrderService() {
		super();
		this.type = SoOrder.class;
	}

	@Override
	public Boolean updateManuaVoucherStatus(Integer orderId, OrderManualVoucherStatus status) {

		UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
		{
			updateBuilder.update("so_order");
			updateBuilder.set("manual_voucher_status", status.getValue());
			updateBuilder.where("pkid=" + orderId);
		}

		String cmdText = updateBuilder.toSQL();
		return this.pm.executeNonQuery(cmdText, null) > 0;
	}

	@Override
	public Map<Integer, String> getCustNameByOrderIdList(List<Integer> orderIdList) {

		Map<Integer, String> map = new HashMap<Integer, String>();
		String orderIds = StringManager.join(",", orderIdList);

		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT oi.pkid 'orderId', ");
		sqlBuffer.append("(CASE WHEN (cri1.`pkid` IS NOT NULL AND cri1.`company_name`!='' ) THEN cri1.`company_name`   ");
		sqlBuffer.append("WHEN (c.pkid IS NULL) THEN (CASE WHEN a.real_name='' THEN a.name ELSE a.real_name END) ");
		sqlBuffer.append("WHEN (ccm.pkid IS NULL OR cri.company_name='') THEN (CASE WHEN c.real_name='' THEN c.`mobile` ELSE c.real_name END) ELSE cri.company_name END) 'custName' FROM so_order oi  ");
		sqlBuffer.append("JOIN uc_account a ON a.pkid = oi.account_id ");
		sqlBuffer.append("LEFT JOIN crm_customer c ON c.account_id = a.pkid ");
		sqlBuffer.append("LEFT JOIN crm_customer_company_map ccm ON ccm.customer_id = c.pkid ");
		sqlBuffer.append("LEFT JOIN crm_company_intention cri ON cri.pkid = ccm.company_id ");
		sqlBuffer.append("LEFT JOIN crm_company_intention cri1 ON cri1.pkid = oi.company_id ");
		sqlBuffer.append("WHERE oi.pkid IN(" + orderIds + ") ");

		DataTable executeTable = this.pm.executeTable(sqlBuffer.toString(), null);

		for (IRow row : executeTable) {
			map.put(row.getInteger("orderId"), row.getString("custName"));
		}
		return map;
	}

}
