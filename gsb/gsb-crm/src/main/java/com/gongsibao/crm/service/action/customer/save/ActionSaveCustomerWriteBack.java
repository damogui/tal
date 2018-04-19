package com.gongsibao.crm.service.action.customer.save;

import java.sql.Types;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.crm.NCustomer;

/**
 * @ClassName: ActionSaveCustomerWriteBack
 * @Description:TODO 回写：更新最后一次关联公司
 * @author: 韩伟
 * @date: 2018年3月16日 上午9:49:09
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ActionSaveCustomerWriteBack implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomer customer = (NCustomer) ctx.getItem();
		Integer customerId = customer.getId();
		Map<String, Object> getMap =ctx.getStatus();
		Integer lastCustSourceId = (Integer) getMap.get("lastCustomerSourceId");

		// 1.更新NCustomer
		StringBuilder builer = new StringBuilder();
		{
			builer.append("UPDATE crm_customer c ");
			builer.append("LEFT JOIN ( ");
			builer.append("SELECT customer_id, company_id FROM crm_customer_company_map ");
			builer.append("WHERE customer_id = ? ORDER BY add_time DESC LIMIT 1 ");
			builer.append(") a ON c.pkid = a.customer_id ");
			builer.append("SET c.company_id = a.company_id");
		}
		QueryParameters qps = new QueryParameters();
		qps.add("@customerId", customerId, Types.INTEGER);
		IPersister<NCustomer> pm = PersisterFactory.create();
		pm.executeNonQuery(builer.toString(), qps);
		
		// 2.更新NCustomer的最近的商机来源
		StringBuilder builerSource = new StringBuilder();
		{
			builerSource.append("UPDATE crm_customer ");
			builerSource.append("SET last_customer_source = ?");
			builerSource.append(" WHERE pkid = ?");
		}
		QueryParameters qpsSource = new QueryParameters();
		qpsSource.add("@last_customer_source", lastCustSourceId, Types.INTEGER);
		qpsSource.add("@pkid", customerId, Types.INTEGER);
		pm.executeNonQuery(builerSource.toString(), qpsSource);
		
		// 3.更新Acount
		Integer accountId = customer.getAccountId();
		if (accountId != null) {

			builer = new StringBuilder();
			builer.append("UPDATE uc_account a ");
			builer.append("LEFT JOIN  crm_customer c ON a.pkid = c.account_id ");
			builer.append("SET a.company_id = c.company_id ");
			builer.append("WHERE c.pkid = ? AND c.company_id IS NOT NULL");
			pm.executeNonQuery(builer.toString(), qps);
		}
	}
}
