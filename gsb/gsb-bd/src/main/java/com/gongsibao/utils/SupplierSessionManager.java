package com.gongsibao.utils;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.supplier.base.ISalesmanService;

/**   
 * @ClassName:  EmployeeSupplierSessionManager   
 * @Description:TODO 处理与服务商相关
 * @author: 韩伟
 * @date:   2018年1月17日 下午12:12:44   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class SupplierSessionManager {

	/**   
	 * @Title: getSupplierId   
	 * @Description: TODO(获取当前登录人所在服务商Id)   
	 * @param: @return      
	 * @return: Integer      
	 * @throws   
	 */
	public static Integer getSupplierId(){
		
		Integer employeeId = SessionManager.getUserId();
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		Integer supplierId = salesmanService.getSupplierId(employeeId);
		return supplierId;
	}
	
	/**   
	 * @Title: getDepartmentId   
	 * @Description: TODO(获取当前登录人所在服务商下部门Id)   
	 * @param: @return      
	 * @return: Integer      
	 * @throws   
	 */
	public static Integer getDepartmentId(){
		
		Integer employeeId = SessionManager.getUserId();
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		Integer departmentId = salesmanService.getDepartmentId(employeeId);
		return departmentId;
	}
}
