package com.gongsibao.tools.db;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.uc.base.IOrganizationService;

public class SetOrganizationTest {
	IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
	IPersister<Employee> pm = PersisterFactory.create();

	@Test
	public void run() {
		doRun();
	}

	public void doRun() {
		String getParentId = "SELECT pkid from uc_organization where pid=0";
		DataTable getDt = organizationService.executeTable(getParentId.toString(), null);
		for (IRow row : getDt) {
			Integer getPkid = Integer.parseInt(row.getString("pkid"));
			setOrfanization(getPkid);
		}
		System.out.println("ok......");
	}

	private void setOrfanization(Integer pid) {
		String getParentId = "SELECT pkid from uc_organization where pid=" + pid;
		DataTable getDt = organizationService.executeTable(getParentId.toString(), null);
		if (getDt.size() > 0) {
			for (IRow row : getDt) {
				String updateSql = "UPDATE uc_organization set is_leaf=0 where pkid=" + pid;
				int i = pm.executeNonQuery(updateSql.toString(), null);
				setOrfanization(Integer.parseInt(row.getString("pkid")));
			}
		} else {
			String updateSql = "UPDATE uc_organization set is_leaf=1 where pkid=" + pid;
			int i = pm.executeNonQuery(updateSql.toString(), null);
		}

	}
}
