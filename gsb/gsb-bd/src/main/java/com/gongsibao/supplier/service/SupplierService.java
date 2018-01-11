package com.gongsibao.supplier.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

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
	 * Description: 1.校验是否已开户 ，是否设置功能模块2.创建Employee,创建Salesman,并关联Employee，设置管理员角色
	 * </p>
	 * 
	 * @param supplierId
	 * @return
	 * @see com.gongsibao.supplier.base.ISupplierService#openAccount(java.lang.Integer)
	 */
	@Override
	public Boolean openAccount(Integer supplierId) {

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
}