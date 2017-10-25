package org.netsharp.organization.service;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IOrganizationEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.service.PersistableService;

@Service
public class OrganizationEmployeeService extends
		PersistableService<OrganizationEmployee> implements
		IOrganizationEmployeeService {
	public OrganizationEmployeeService() {
		super();
		this.type = OrganizationEmployee.class;
	}

	@Override
	public Employee getEmpByPostOrgId(Integer orgid) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("OrganizationEmployee.*,employee.*");
			oql.setFilter("  organizationId =" + orgid);
		}
		OrganizationEmployee oe = this.pm.queryFirst(oql);
		if (oe != null) {
			return oe.getEmployee();
		}

		return null;
	}
}
