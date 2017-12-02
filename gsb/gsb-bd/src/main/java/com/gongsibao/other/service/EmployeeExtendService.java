package com.gongsibao.other.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.other.EmployeeExtend;
import com.gongsibao.other.base.IEmployeeExtendService;

@Service
public class EmployeeExtendService extends PersistableService<EmployeeExtend> implements IEmployeeExtendService {
	public EmployeeExtendService() {
		super();
		this.type = EmployeeExtend.class;
	}
}
