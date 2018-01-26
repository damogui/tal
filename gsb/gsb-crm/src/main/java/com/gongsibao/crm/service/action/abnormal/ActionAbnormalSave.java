package com.gongsibao.crm.service.action.abnormal;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.crm.base.INCustomerTaskInspectionService;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskInspection;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.TaskInspectionState;
import com.gongsibao.entity.crm.dic.TaskInspectionType;

/**
 * @author hw
 * 抽查异常：保存
 */
public class ActionAbnormalSave implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		NCustomerTask getEntity = (NCustomerTask)ctx.getItem();
		//1.保存抽查记录
		INCustomerTaskInspectionService inspectionService = ServiceFactory.create(INCustomerTaskInspectionService.class);
		NCustomerTaskInspection inspectionEntity = new NCustomerTaskInspection();
		
		inspectionEntity.toNew();//标示下类型，有多种
		TaskInspectionState getState = getEntity.getInspectionState();
		inspectionEntity.setContent(getEntity.getLastInspectionContent());
		//inspectionEntity.setInspectionState(inspectionState);
		inspectionEntity.setInspectionType(TaskInspectionType.getItem((Integer)ctx.getStatus().get("type")) );
		
		inspectionEntity.setTaskId(getEntity.getId());
		inspectionEntity.setSupplierId(getEntity.getSupplierId());
		inspectionEntity.setDepartmentId(getEntity.getDepartmentId());
		inspectionEntity.setCustomerId(getEntity.getCustomerId());
		inspectionService.save(inspectionEntity);		
	}
}
