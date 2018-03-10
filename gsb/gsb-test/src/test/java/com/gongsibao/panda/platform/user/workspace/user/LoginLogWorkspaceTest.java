package com.gongsibao.panda.platform.user.workspace.user;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.uc.UserLoginLog;

public class LoginLogWorkspaceTest extends WorkspaceCreationBase{


	@Before
	public void setup() {

		entity = UserLoginLog.class;
		urlList = "/user/center/login/log/list";
		listPartName = formPartName = "登录日志";		
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode =  "User_Center_" + entity.getSimpleName();
		formOpenMode = OpenMode.WINDOW;
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("登录日志");
		}
		
		PDatagridColumn column = null;
		addColumn(datagrid, "user.name", "用户", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "user.mobilePhone", "帐号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "ip", "ip", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "ipAddr", "ip地址", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "userAgent", "UA", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "createTime", "登录时间", ControlTypes.DATETIME_BOX, 130);

		return datagrid;
	}

	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "user.mobilePhone", "帐号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "createTime", "姓名", ControlTypes.DATE_BOX);
		return queryProject;
	}
	
	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
	}
}
