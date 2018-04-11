package com.gongsibao.crm.service.action.customer.save;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.crm.NCustomer;

/**
 * @author hw 客户保存：校验
 */
public class ActionSaveCustomerVerify implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomer customer = (NCustomer) ctx.getItem();
		EntityState state = ctx.getState();

		String mobile = customer.getMobile();
		if (state == EntityState.New) {

			// 校验手机号是否存在
			boolean isHasMobile = hasMobile(mobile);
			if (isHasMobile) {

				throw new BusinessException("[" + mobile + "]已存在！");
			}

		} else if (state == EntityState.Persist) {

			// 校验手机号是否存在
			boolean isHasMobile = hasMobile(customer.getId(), mobile);
			if (isHasMobile) {

				throw new BusinessException("[" + mobile + "]已存在！");
			}

		} else if (state == EntityState.Deleted) {

			throw new BusinessException("客户信息不允许删除！");
		}
	}

	private boolean hasMobile(String mobile) {

		return hasMobile(null, mobile);
	}

	private boolean hasMobile(Integer id, String mobile) {

		if (StringManager.isNullOrEmpty(mobile) || StringManager.isNullOrEmpty(mobile.trim())) {

			return false;
		}

		Oql oql = new Oql();
		{
			oql.setType(NCustomer.class);
		}

		List<String> ss = new ArrayList<String>();
		ss.add("mobile=?");
		oql.getParameters().add("mobile", mobile, Types.VARCHAR);
		if (id != null) {
			ss.add("pkid<>?");
			oql.getParameters().add("pkid", id, Types.INTEGER);
		}
		String filter = StringManager.join(" AND ", ss);
		oql.setFilter(filter);

		IPersister<NCustomer> pm = PersisterFactory.create();
		return pm.queryCount(oql) > 0;
	}
}
