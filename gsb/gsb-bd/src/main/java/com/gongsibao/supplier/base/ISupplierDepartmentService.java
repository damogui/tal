package com.gongsibao.supplier.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.supplier.SupplierDepartment;

public interface ISupplierDepartmentService  extends IPersistableService<SupplierDepartment>{

	/**
	 * 递归获取所有子部门Id
	 * @param departmentId
	 * @return
	 */
	List<Integer> getSubDepartmentIdList(Integer departmentId);
	/**
	 * 递归获取上级部门Id
	 * @param departmentId
	 * @return
	 */
	Integer getSupDepartmentId(Integer departmentId);
}
