package com.gongsibao.tools.migrate.account;

import org.netsharp.dataccess.DataAccessFactory;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

public abstract class AbstractActionService {

	IPersister<Employee> pm = PersisterFactory.create();
	
	IDataAccess dao = DataAccessFactory.create();
	
	public abstract void run();
}
