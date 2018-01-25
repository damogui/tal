package com.gongsibao.supplier.web.panda;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.util.StringManager;

import com.gongsibao.utils.SupplierSessionManager;

/**   
 * @ClassName:  BaseSupplierListPart   
 * @Description:TODO 所有涉及到服务商列表ListPart都继承此类
 * @author: 韩伟
 * @date:   2018年1月17日 下午2:11:07   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class BaseSupplierListPart extends AdvancedListPart{

	@Override
	protected String getExtraFilter() {

		List<String> ss = new ArrayList<String>();
		
		//父类过滤条件
		String filter = super.getExtraFilter();
		if(!StringManager.isNullOrEmpty(filter)){

			ss.add(filter);
		}
		

		//过滤服务商ID
		Integer supplierId = SupplierSessionManager.getSupplierId();
		if (supplierId != null) {

			ss.add("supplierId=" + supplierId);
		}

		return StringManager.join(" and ", ss);
	}
}
