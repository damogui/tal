package com.gongsibao.crm.service.action.customer.member;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.crm.NCustomer;

/**
 * @author hw
 * 开通会员更新Customer：is_member，account_id 
 */
public class ActionMemberWriteBack implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomer customer = (NCustomer) ctx.getItem();
		Account account = (Account)ctx.getStatus().get("account");
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer");
			updateSql.set("is_member", 1);
			updateSql.set("account_id", account.getId());
			updateSql.where("id=" + customer.getId());
		}
		
		IPersister<NCustomer> pm = PersisterFactory.create();
		pm.executeNonQuery(updateSql.toSQL(), null);
	}

}
