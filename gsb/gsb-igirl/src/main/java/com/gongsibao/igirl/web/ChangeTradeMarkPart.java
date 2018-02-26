package com.gongsibao.igirl.web;

import com.gongsibao.entity.igirl.ChangeTradeMark;
import com.gongsibao.utils.SupplierSessionManager;
import org.joda.time.DateTime;
import org.netsharp.core.EntityState;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormPart;

public class ChangeTradeMarkPart extends FormPart {
	@Override
	public IPersistable save(IPersistable entity) {
		ChangeTradeMark entity1=(ChangeTradeMark)entity;
		Integer departmentId = SupplierSessionManager.getDepartmentId();
		entity1.setDepartmentId(departmentId);
		if(entity1.getEntityState()== EntityState.New) {
			entity1.setAgentFileNum(DateTime.now().toString("yyyyMMddHHmmss"));
		}
		return super.save(entity1);
	}
}
