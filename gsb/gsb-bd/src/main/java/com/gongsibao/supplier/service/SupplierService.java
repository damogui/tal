package com.gongsibao.supplier.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.supplier.base.ISupplierService;

@Service
public class SupplierService extends PersistableService<Supplier> implements ISupplierService {

	public SupplierService() {
		super();
		this.type = Supplier.class;
	}

	/**
	 * <p>
	 * Title: 开户
	 * </p>
	 * <p>
	 * Description: 1.校验是否已开户
	 * ，是否设置功能模块2.创建Employee,创建Salesman,并关联Employee，设置管理员角色
	 * </p>
	 *
	 * @param supplierId
	 * @return
	 * @see com.gongsibao.supplier.base.ISupplierService#openAccount(java.lang.Integer)
	 */
	@Override
	public Boolean openAccount(Integer supplierId) {

		Supplier entity = this.byId(supplierId);
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/operation/supplier/account/open");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}

	/**
	 * <p>
	 * Title: 销户
	 * </p>
	 * <p>
	 * Description: 1.校验是否已销户 2.停用此服务商下所有Salesman对应的Employee
	 * </p>
	 *
	 * @param supplierId
	 * @return
	 * @see com.gongsibao.supplier.base.ISupplierService#closeAccount(java.lang.Integer)
	 */
	@Override
	public Boolean closeAccount(Integer supplierId) {

		Supplier entity = this.byId(supplierId);
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/operation/supplier/account/close");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}

	@Override
	public Integer getSupplierCount(Integer categoryId) {

		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setFilter("categoryId=?");
			oql.getParameters().add("@categoryId", categoryId, Types.INTEGER);
		}
		return this.queryCount(oql);
	}

	@Override
	public Supplier save(Supplier entity) {

		EntityState state = entity.getEntityState();
		if (state.equals(EntityState.Persist)) {

			Supplier oldSupplier = super.byId(entity.getId());

			if (!oldSupplier.getType().equals(entity.getType())) {// 如果平台属性改变去修改部门和员工的平台属性

				String sql1 = "UPDATE  sp_department  SET  type=?  WHERE   supplier_id=?;";
				String sql2 = "UPDATE  sp_salesman  SET  type=?  WHERE   supplier_id=?;";// 更新部门和员工平台属性

				QueryParameters qps = new QueryParameters();
				qps.add("@type", entity.getType().getValue(), Types.INTEGER);
				qps.add("@supplier_id", entity.getId(), Types.INTEGER);

				this.pm.executeNonQuery(sql1, qps);
				this.pm.executeNonQuery(sql2, qps);
			}
		}

		entity = super.save(entity);
		return entity;
	}

	@Override
	public Supplier byId(Object id) {

		String selectFields = getSelectFullFields();
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects(selectFields);
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}

		Supplier entity = this.queryFirst(oql);
		return entity;
	}

	private String getSelectFullFields() {

		StringBuilder builder = new StringBuilder();
		builder.append("Supplier.*,");
		builder.append("Supplier.category.{id,name},");
		builder.append("Supplier.serviceProducts.*,");
		builder.append("Supplier.serviceProducts.productCategory1.{id,name},");
		builder.append("Supplier.serviceProducts.productCategory2.{id,name},");
		builder.append("Supplier.serviceProducts.product.{id,name},");
		builder.append("Supplier.serviceProducts.nProvince.{id,name},");
		builder.append("Supplier.serviceProducts.nCity.{id,name},");
		builder.append("Supplier.serviceProducts.nCounty.{id,name},");
		builder.append("Supplier.modules.*,");
		builder.append("Supplier.modules.functionModule.*");
		return builder.toString();
	}

	@Override
	public NotifyType getNotifyType(Integer supplierId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("id,notifiedType");
			oql.setFilter("id=?");
			oql.getParameters().add("id", supplierId, Types.INTEGER);
		}

		Supplier entity = this.queryFirst(oql);
		if (entity == null) {

			return null;
		}

		return entity.getNotifiedType();
	}

	@Override
	public List<Integer> getSupplierIdListByOwnerId(Integer ownerId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("id,categoryId");
			oql.setFilter("categoryId in ( select category_id from sp_supplier_category_owner_map where owner_id=?)");
			oql.getParameters().add("ownerId", ownerId, Types.INTEGER);
		}
		List<Supplier> list = this.queryList(oql);
		List<Integer> idList = new ArrayList<Integer>();
		for (Supplier supplier : list) {

			idList.add(supplier.getId());
		}
		return idList;
	}
}