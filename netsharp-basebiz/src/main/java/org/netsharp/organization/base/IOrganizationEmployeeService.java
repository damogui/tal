package org.netsharp.organization.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.OrganizationEmployee;

public interface IOrganizationEmployeeService extends IPersistableService<OrganizationEmployee> {
	/**
	 * 按照岗位组织的id获取雇员
	 * @param orgid
	 * @return
	 */
        Employee getEmpByPostOrgId(Integer orgid);
}
