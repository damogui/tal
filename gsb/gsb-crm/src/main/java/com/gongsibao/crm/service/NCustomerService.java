package com.gongsibao.crm.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.annotations.Subs;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerCompany;
import com.gongsibao.entity.crm.NCustomerProduct;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskNotify;

@Service
public class NCustomerService extends SupplierPersistableService<NCustomer> implements INCustomerService {

	public NCustomerService() {
		super();
		this.type = NCustomer.class;
	}

	@Override
	public int updateIsMember(Integer customerId) {
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer");
			updateSql.set("is_member", 1);
			updateSql.where("id=" + customerId);
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null);
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
	@Subs(foreignKey = "customerId", header = "客户任务", subType = NCustomerTask.class)
	private List<NCustomerTask> tasks;

	@Subs(foreignKey = "customerId", header = "意向产品", subType = NCustomerProduct.class)
	private List<NCustomerProduct> products;

	@Subs(foreignKey = "customerId", header = "关联企业", subType = NCustomerCompany.class)
	private List<NCustomerCompany> companys;

	@Subs(foreignKey = "customerId", header = "沟通日志", subType = NCustomerTaskFoolow.class)
	private List<NCustomerTaskFoolow> follows;

	@Subs(foreignKey = "customerId", header = "通知日志", subType = NCustomerTaskNotify.class)
	private List<NCustomerTaskNotify> notifys;

	@Subs(foreignKey = "customerId", header = "流转日志", subType = NCustomerChange.class)
	private List<NCustomerChange> changes;

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
}