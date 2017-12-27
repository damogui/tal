package com.gongsibao.product.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.ProductPackage;
import com.gongsibao.product.base.IProductPackageService;

@Service
public class ProductPackageService extends PersistableService<ProductPackage> implements IProductPackageService {

	public ProductPackageService() {
		super();
		this.type = ProductPackage.class;
	}

	@Override
	public Boolean updateEnabled(Integer id, Boolean state) {

		String cmdText = "UPDATE prod_package set is_enabled=? where pkid=?";
		QueryParameters qps = new QueryParameters();
		qps.add("enabled", state, Types.BOOLEAN);
		qps.add("id", id, Types.INTEGER);
		return this.pm.executeNonQuery(cmdText, qps) > 0;
	}
}