package com.gongsibao.crm.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.uc.Account;
import com.gongsibao.uc.base.IAccountService;

@Service
public class NCustomerService extends SupplierPersistableService<NCustomer> implements INCustomerService {

	public NCustomerService() {
		super();
		this.type = NCustomer.class;
	}

	@Override
	public NCustomer validationContactWay(Integer id, String contactWay, String type) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
		}

		List<String> ss = new ArrayList<String>();
		ss.add(type + "=?");
		oql.getParameters().add("contactWay", contactWay, Types.VARCHAR);
		if (id != null) {
			ss.add("id<>?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		String filter = StringManager.join(" AND ", ss);
		oql.setFilter(filter);

		return this.queryFirst(oql);
	}

	@Override
	public NCustomer bySwtCustomerId(String swtCustomerId) {

		String selectFields = getSelectFullFields();
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects(selectFields);
			oql.setFilter("swtCustomerId=?");
			oql.getParameters().add("swtCustomerId", swtCustomerId, Types.VARCHAR);
		}

		NCustomer entity = this.queryFirst(oql);
		return entity;
	}

	@Override
	public NCustomer byContactWay(String contactWay, String type) {

		String selectFields = getSelectFullFields();
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects(selectFields);
			oql.setFilter(type + "=?");
			oql.getParameters().add("contactWay", contactWay, Types.VARCHAR);
		}

		NCustomer entity = this.queryFirst(oql);
		return entity;
	}

	@Override
	public NCustomer bindSwtCustomerId(String swtCustomerId, int customerId) {

		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
			updateBuilder.set("swt_customer_id", swtCustomerId);
			updateBuilder.where("id =" + customerId);
		}
		this.pm.executeNonQuery(updateBuilder.toSQL(), null);
		NCustomer customer = byId(customerId);
		return customer;
	}

	@Override
	public NCustomer save(NCustomer entity) {

		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/customer/save");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}
		ActionManager action = new ActionManager();
		action.execute(ctx);

		entity = (NCustomer) ctx.getItem();
		return entity;
	}

	private String getSelectFullFields() {

		StringBuilder builder = new StringBuilder();
		builder.append("NCustomer.*,");
		builder.append("NCustomer.tasks.*,");
		builder.append("NCustomer.products.*,");
		builder.append("NCustomer.products.product.{id,name},");
		builder.append("NCustomer.products.province.*,");
		builder.append("NCustomer.products.city.*,");
		builder.append("NCustomer.products.county.*,");
		builder.append("NCustomer.companys.*,");
		builder.append("NCustomer.companys.company.{id,companyName},");
		builder.append("NCustomer.follows.*,");
		return builder.toString();
	}

	@Override
	public boolean openMember(String customerIdsStr) {

		String[] ids = customerIdsStr.split("_");
		for (String id : ids) {

			Integer customerId = Integer.parseInt(id);
			NCustomer customer = this.byId(id);
			if(customer.getIsMember() || StringManager.isNullOrEmpty(customer.getMobile())){
				
				continue;
			}
			this.updateIsMember(customerId);
			this.createAccount(customer);
		}
		return true;
	}

	private boolean createAccount(NCustomer customer) {

		Account account = new Account();
		{
			account.toNew();
			account.setName(customer.getRealName());
			account.setRealName(customer.getRealName());
			account.setEmail(customer.getEmail());
			account.setMobilePhone(customer.getMobile());
			account.setTelephone(customer.getTelephone());
			account.setHeadThumbFileId(0);
			account.setSourceClientId(0);
			
			// 密码算法？
			account.setPasswd("");
			account.setTicket("");
			// 这里要发短信通知吗？
			IAccountService accountService = ServiceFactory.create(IAccountService.class);
			accountService.save(account);
		}
		return true;
	}

	private boolean updateIsMember(Integer customerId) {

		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer");
			updateSql.set("is_member", 1);
			updateSql.where("id=" + customerId);
		}
		return this.pm.executeNonQuery(updateSql.toSQL(), null) > 0;
	}

}