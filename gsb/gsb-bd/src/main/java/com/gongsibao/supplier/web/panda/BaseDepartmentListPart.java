package com.gongsibao.supplier.web.panda;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.util.StringManager;

import com.gongsibao.utils.SupplierSessionManager;

/**   
 * @ClassName:  BaseDepartmentListPart   
 * @Description:TODO 所有涉及到服务商部门的列表ListPart都继承此类
 * @author: 韩伟
 * @date:   2018年1月17日 下午2:10:00   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class BaseDepartmentListPart extends BaseSupplierListPart{

	
	@Override
	protected String getExtraFilter() {

		List<String> ss = new ArrayList<String>();
		
		//父类过滤条件
		String filter = super.getExtraFilter();
		if(!StringManager.isNullOrEmpty(filter)){

			ss.add(filter);
		}
		
		//过滤服务商ID
		Integer departmentId = SupplierSessionManager.getDepartmentId();
		if (departmentId != null) {

			ss.add("departmentId=" + departmentId);
		}

		return StringManager.join(" and ", ss);
	}
}
