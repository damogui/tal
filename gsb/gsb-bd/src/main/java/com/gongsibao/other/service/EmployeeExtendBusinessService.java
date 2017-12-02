package com.gongsibao.other.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.other.EmployeeExtendBusiness;
import com.gongsibao.other.base.IEmployeeExtendBusinessService;

@Service
public class EmployeeExtendBusinessService extends PersistableService<EmployeeExtendBusiness> implements IEmployeeExtendBusinessService {
	public EmployeeExtendBusinessService() {
		super();
		this.type = EmployeeExtendBusiness.class;
	}
}
