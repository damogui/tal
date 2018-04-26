package com.gongsibao.supplier.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.entity.supplier.SupplierCategoryOwnerMap;
import com.gongsibao.supplier.base.ISupplierCategoryOwnerMapService;

@Service
public class SupplierCategoryOwnerMapService extends SupplierPersistableService<SupplierCategoryOwnerMap> implements ISupplierCategoryOwnerMapService {

	public SupplierCategoryOwnerMapService() {
		super();
		this.type = SupplierCategoryOwnerMap.class;
	}

	@Override
	public List<SupplierCategoryOwnerMap> getListByCategoryId(Integer categoryId) {
		Oql oql = new Oql();
        {
            oql.setType(type);
            oql.setSelects("SupplierCategoryOwnerMap.ownerId,SupplierCategoryOwnerMap.id");
            oql.setFilter("categoryId=?");
            oql.getParameters().add("@categoryId", categoryId, Types.INTEGER);
        }
        List<SupplierCategoryOwnerMap> entityList = this.queryList(oql);
        return entityList;
	}
}