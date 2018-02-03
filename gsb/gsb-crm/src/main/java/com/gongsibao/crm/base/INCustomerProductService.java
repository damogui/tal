package com.gongsibao.crm.base;

import java.math.BigDecimal;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.NCustomerProduct;

public interface INCustomerProductService  extends IPersistableService<NCustomerProduct> {

	BigDecimal getSigningAmount(Integer taskId);
}
