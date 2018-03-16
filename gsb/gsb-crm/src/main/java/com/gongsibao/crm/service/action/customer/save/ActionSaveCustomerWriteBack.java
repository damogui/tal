package com.gongsibao.crm.service.action.customer.save;

import java.sql.Types;

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

		// 1.更新NCustomer
		StringBuilder builer = new StringBuilder();
		{
			builer.append("UPDATE n_crm_customer c ");
			builer.append("LEFT JOIN ( ");
			builer.append("SELECT customer_id, company_id FROM crm_customer_company_map ");
			builer.append("WHERE customer_id = ? ORDER BY add_time DESC LIMIT 1 ");
			builer.append(") a ON c.id = a.customer_id ");
			builer.append("SET c.company_id = a.company_id");
		}
		QueryParameters qps = new QueryParameters();
		qps.add("@customerId", customerId, Types.INTEGER);
		IPersister<NCustomer> pm = PersisterFactory.create();
		pm.executeNonQuery(builer.toString(), qps);

		// 2.更新Acount
		Integer accountId = customer.getAccountId();
		if (accountId != null) {

			builer = new StringBuilder();
			builer.append("UPDATE uc_account a ");
			builer.append("LEFT JOIN n_crm_customer c ON a.pkid = c.account_id ");
			builer.append("SET a.company_id = c.company_id ");
			builer.append("WHERE c.id = ? AND c.company_id IS NOT NULL");
			pm.executeNonQuery(builer.toString(), qps);
		}
	}
}