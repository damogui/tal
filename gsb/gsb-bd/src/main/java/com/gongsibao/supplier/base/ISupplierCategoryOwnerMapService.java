package com.gongsibao.supplier.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.supplier.SupplierCategoryOwnerMap;

public interface ISupplierCategoryOwnerMapService extends IPersistableService<SupplierCategoryOwnerMap> {

	/**
	 * 根据服务商分组Id,获取服务商分组运营人员配置
	 * @param categoryId
	 * @return
	 */
	public List<SupplierCategoryOwnerMap> getListByCategoryId(Integer categoryId);
}
