package com.gongsibao.panda.platform.operation.workspace.taurus;

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

import com.gongsibao.entity.taurus.JnzUserBehaviorStatistics;
public class JnzUserBalanceWorkSpaceTest  extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = JnzUserBehaviorStatistics.class;
		urlList = "/taurus/user/statistic/list";
		listPartName = formPartName = "用户行为数据统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_TAURUS_"+ JnzUserBehaviorStatistics.class.getSimpleName();
		formOpenMode = OpenMode.WINDOW;
//		listPartServiceController = JnzUserBalanceListPart.class.getName();
	}

	@SuppressWarnings("unused")
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
//		datagrid.setTreeField("showOrganName");
		
		PDatagridColumn column = null;
		column = addColumn(datagrid, "addTime", "日期", ControlTypes.DATE_BOX, 150, true);
		{
		}
		column = addColumn(datagrid, "mobile", "手机号", ControlTypes.TEXT_BOX, 150, true);
		{
		}
		column = addColumn(datagrid, "loginButton", "点击登录", ControlTypes.TEXT_BOX, 70, true);
		{
		}
		column = addColumn(datagrid, "loginSuccess", "登录成功", ControlTypes.TEXT_BOX, 70, true);
		{
		}
		column = addColumn(datagrid, "registerButton", "点击注册", ControlTypes.TEXT_BOX, 70, true);
		{
		}
		column = addColumn(datagrid, "improvingPersonalData", "完成注册资料", ControlTypes.TEXT_BOX, 80, true);
		{
		}
		column = addColumn(datagrid, "homePage", "首页贡献量", ControlTypes.TEXT_BOX, 80, true);
		{
		}
		column = addColumn(datagrid, "enterpriseList", "搜索关键词", ControlTypes.TEXT_BOX, 70, true);
		{
		}
		column = addColumn(datagrid, "enterpriseAnalysis", "选择分析企业", ControlTypes.TEXT_BOX, 80, true);
		{
		}
		column = addColumn(datagrid, "chargingSuccess", "成功扣费", ControlTypes.TEXT_BOX, 70, true);
		{
		}
		column = addColumn(datagrid, "noDeductions", "未扣费", ControlTypes.TEXT_BOX, 50, true);
		{
		}
		column = addColumn(datagrid, "balanceInsufficientRecharge", "余额不足点击充值", ControlTypes.TEXT_BOX, 100, true);
		{
		}
		column = addColumn(datagrid, "personalCenterRecharge", "直接充值", ControlTypes.TEXT_BOX, 70, true);
		{
		}
		column = addColumn(datagrid, "rechargeSuccess", "充值成功", ControlTypes.TEXT_BOX, 70, true);
		{
		}
		
		return datagrid;
	}
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "addTime", "日期", ControlTypes.DATE_BOX);
		addQueryItem(queryProject, "mobile", "手机号", ControlTypes.TEXT_BOX);
		return queryProject;
	}
	public void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
