package com.gongsibao.supplier.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.SupplierCategory;
import com.gongsibao.supplier.base.ISupplierCategoryService;
import com.gongsibao.supplier.base.ISupplierService;

@Service
public class SupplierCategoryService extends PersistableService<SupplierCategory> implements ISupplierCategoryService {

	public SupplierCategoryService() {
		super();
		this.type = SupplierCategory.class;
	}

	public SupplierCategory save(SupplierCategory entity) {

		if (entity.getEntityState() == EntityState.Deleted) {

			// 校验是否有下级
			Boolean isHasChild = hasChild(entity.getId());
			if (isHasChild) {

				throw new BusinessException("存在下级分类，不能删除");
			}
			
			// 校验是否有服务商
			ISupplierService supplierService = ServiceFactory.create(ISupplierService.class);
			Integer supplierCount = supplierService.getSupplierCount(entity.getId());
			if (supplierCount > 0) {

				throw new BusinessException("存在服务商，不能删除");
			}
		}

		entity = super.save(entity);
		return entity;
	}

	private Boolean hasChild(Integer id) {

		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setFilter("parentId=?");
			oql.getParameters().add("@parentId", id, Types.INTEGER);
		}

		return this.queryCount(oql) > 0;
	}

}