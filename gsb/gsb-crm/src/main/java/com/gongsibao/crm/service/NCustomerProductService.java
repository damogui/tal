package com.gongsibao.crm.service;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerProductService;
import com.gongsibao.entity.crm.NCustomerProduct;

@Service
public class NCustomerProductService extends SupplierPersistableService<NCustomerProduct> implements INCustomerProductService {

	public NCustomerProductService() {
		super();
		this.type = NCustomerProduct.class;
	}

	@Override
	public BigDecimal getSigningAmount(Integer taskId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("taskId=?");
			oql.getParameters().add("taskId", taskId, Types.INTEGER);
		}

		List<NCustomerProduct> list = this.queryList(oql);
		if (list.size() == 0) {

			return BigDecimal.ZERO;
		}

		for (NCustomerProduct cp : list) {

		}

		//涉及的表
		//prod_product,prod_service,prod_price
		//return new BigDecimal(100);
		
		//这里还没处理完
		return null;
	}
}