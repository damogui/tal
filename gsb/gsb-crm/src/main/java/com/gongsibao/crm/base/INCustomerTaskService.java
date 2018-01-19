package com.gongsibao.crm.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.NCustomerTask;

public interface INCustomerTaskService  extends IPersistableService<NCustomerTask> {

	public int updateInspectionState(Integer taskId,Integer selectValue,String getNote);
}
