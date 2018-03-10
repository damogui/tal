package com.gongsibao.panda.platform.operation.workspace.taurus;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.taurus.UserInfo;

public class UserInfoWorkspaceTest  extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {

		urlList = "/taurus/userinfo/list";
		entity = UserInfo.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "客户信息";
		resourceNodeCode = "GSB_TAURUS_" + UserInfo.class.getSimpleName();
		openWindowHeight = 650;
	}
	

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");

		PDatagridColumn column = null;
		column = addColumn(datagrid, "user.mobile", "手机", ControlTypes.TEXT_BOX, 100);
		{
			column.setFormatter("if(value&&value.length==11){return value.substr(0,3)+'****'+value.substr(7);}");
		}
		column = addColumn(datagrid, "user.amount", "余额", ControlTypes.DECIMAL_FEN_BOX, 100);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}

		addColumn(datagrid, "name", "姓名",ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "companyName", "公司名称",ControlTypes.TEXT_BOX, 250);
		addColumn(datagrid, "province.name", "省份",ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "city.name", "城市",ControlTypes.TEXT_BOX, 100);

		column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}
		addColumn(datagrid, "updateTime", "修改时间", ControlTypes.DATETIME_BOX, 130);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "user.mobile", "手机", ControlTypes.TEXT_BOX);
		return queryProject;
	}
}
