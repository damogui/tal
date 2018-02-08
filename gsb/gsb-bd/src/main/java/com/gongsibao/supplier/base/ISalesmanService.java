package com.gongsibao.supplier.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.supplier.Salesman;

public interface ISalesmanService extends IPersistableService<Salesman> {

	/**
	 * @Title: getSupplierId
	 * @Description: TODO(根据当前登录人获取服务商Id)
	 * @param: @param employeeId
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	Integer getSupplierId(Integer employeeId);

	/**
	 * @Title: getDepartmentId
	 * @Description: TODO(根据当前登录人获取服务商下对应部门Id)
	 * @param: @param employeeId
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	Integer getDepartmentId(Integer employeeId);

	/**
	 * @Title: getDepartmentId
	 * @Description: TODO(根据当前登录人获取对应部门Id和子部门Id集合)
	 * @param: @param employeeId
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	List<Integer> getDepartmentIdList(Integer employeeId);

	/**
	 * @Title: byEmployeeId
	 * @Description: TODO(根据employeeId获取)
	 * @param: @param employeeId
	 * @param: @return
	 * @return: Salesman
	 * @throws
	 */
	Salesman byEmployeeId(Integer employeeId);

	/**
	 * @Title: getEmployeeId
	 * @Description: TODO(根据salesmanId获取employeeId)
	 * @param: @param salesmanId
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	Integer getEmployeeId(Integer salesmanId);

	/**
	 * @Title: setDisabled
	 * @Description: TODO(设置停用/启用状态)
	 * @param: @param salesmanId
	 * @param: @param state
	 * @param: @return
	 * @return: boolean
	 * @throws
	 */
	@Transaction
	boolean setDisabled(Integer salesmanId, boolean state);

	/**
	 * @Title: setReceiving
	 * @Description: TODO(设置接单状态)
	 * @param: @param salesmanId
	 * @param: @param state
	 * @param: @return
	 * @return: boolean
	 * @throws
	 */
	@Transaction
	boolean setReceiving(Integer salesmanId, boolean state);

	/**根据服务商id获取员工集合
	 * @param supplierId
	 * @return
	 */
	List<Salesman> getBySupplierId(Integer supplierId);
	
	/**   
	 * @Title: hasEmployeeId   
	 * @Description: TODO(是否存在employeeId)   
	 * @param: @param employeeId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean hasEmployeeId(Integer employeeId);
	
	/**   
	 * @Title: getDirectLeader   
	 * @Description: TODO(获取直属领导)   
	 * @param: @param salesmanId
	 * @param: @return      
	 * @return: Salesman      
	 * @throws   
	 */
	Salesman getDirectLeader(Integer salesmanId);
	
	/**   
	 * @Title: getDirectLeader   
	 * @Description: TODO(获取隔级领导)   
	 * @param: @param salesmanId
	 * @param: @return      
	 * @return: Salesman      
	 * @throws   
	 */
	Salesman getSuperiorLeader(Integer salesmanId);
	/**
	 * 获取服务商管理员或者部门领导
	 * @param supplierId 服务商id
	 * @param departmentId 部门id
	 * @return 领导id
	 */
	Integer getLeaderId(Integer supplierId,Integer departmentId);
}
