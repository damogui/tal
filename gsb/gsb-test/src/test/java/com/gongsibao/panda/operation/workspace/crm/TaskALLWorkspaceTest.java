package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.web.TaskAllListPart;

public class TaskALLWorkspaceTest extends TaskOpenSeaWorkspaceTest {

	@Override
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/customer/task/all/list";
		listPartName = formPartName = "全部任务";
		resourceNodeCode = "Operation_CRM_Task_ALL";
		listPartJsController = TaskAllListPart.class.getName();
		listPartServiceController = TaskAllListPart.class.getName();
		listPartImportJs = "/gsb/supplier/crm/base/js/task-base-list.part.js|/gsb/platform/operation/crm/js/task-all-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
		listToolbarPath = "task/all/list";
		listFilter = null;
	}

	public PToolbar createListToolbar() {

		PToolbar toolbar = super.createListToolbar();
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("takeBack");
			item.setIcon("fa fa-mail-reply");
			item.setName("收回");
			item.setSeq(5);
			item.setCommand("{controller}.regain();");
			toolbar.getItems().add(item);
		}

		return toolbar;
	}

	public PToolbar createRowToolbar() {

		return null;
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PQueryProject queryProject = this.createQueryProject(node);
		PQueryProject advancedQueryProject = this.createAdvancedQueryProject(node);
		PDatagrid datagrid = new PDatagrid(node, listPartName);
		{
			datagrid.setPageSize(20);
			datagrid.setShowCheckbox(true);
			datagrid.setSingleSelect(false);
			datagrid.setToolbar(rowToolbaPath);
			datagrid.setReadOnly(true);
			datagrid.setFilter(listFilter);
			datagrid.setQueryProject(queryProject);
			datagrid.setAdvancedQueryProject(advancedQueryProject);
			datagrid.setToolbar(rowToolbaPath);
		}
		createRowStyler(datagrid);

		PDatagridColumn column = null;

		addColumn(datagrid, "ownerId", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		column = addColumn(datagrid, "id", "任务ID", ControlTypes.TEXT_BOX, 60, false);{
			
			StringBuilder builder = new StringBuilder();
			builder.append("if(row.qualityProgress=='上升'){return value+'<i title=\\'意向度上升\\' style=\\'font-size: 12px;color:red;float:right;\\' class=\\'fa fa-level-up\\'></i>';}");
			builder.append("else if(row.qualityProgress=='下降'){return value+'<i title=\\'意向度下降\\' style=\\'font-size: 12px;color:gray;float:right;\\' class=\\'fa fa-level-down\\'></i>';}");;
			builder.append("else{return value+'<i title=\\'意向度无变化\\' style=\\'font-size: 12px;color:gray;float:right;\\' class=\\'fa fa-fa-arrows-h\\'></i>';}");
			column.setFormatter(builder.toString());
		}
		addColumn(datagrid, "allocationState", "分配状态", ControlTypes.ENUM_BOX, 100, false);

		addColumn(datagrid, "name", "任务名称", ControlTypes.TEXT_BOX, 250, false);
		addColumn(datagrid, "customerId", "客户ID", ControlTypes.TEXT_BOX, 60, false);
		addColumn(datagrid, "customer.realName", "客户名称", ControlTypes.TEXT_BOX, 100, false);

		// addColumn(datagrid, "customer.realName", "公司名称",
		// ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "customer.isMember", "是否会员", ControlTypes.TEXT_BOX, 60, false);
		{

			StringBuilder builder = new StringBuilder();
			builder.append("if(value===true){return '是   ';}");
			builder.append("else{");
			builder.append("if(row.customer_mobile){");
			builder.append("var ctrl = workspace.parts.byIndex(0).key;");
			builder.append("return '否<a title=\\'开通会员\\' class=\\'grid-btn\\' href=javascript:'+ctrl+'.openMember('+row.customerId+');>");
			builder.append("<i style=\\'font-size: 12px;\\' class=\\'fa fa-user-plus\\'></i>");
			builder.append("<a>';");
			builder.append("}else{return '否';}");
			builder.append("}");
			column.setFormatter(builder.toString());
			// column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "customer.mobile", "手机号", ControlTypes.TEXT_BOX, 100);{
			
			column.setFormatter(" var ctrl=workspace.parts.byIndex(0).key; return eval(ctrl+'.contactFormatter(value,row,index,\\'手机号\\')');");
		}
		column = addColumn(datagrid, "customer.telephone", "座机", ControlTypes.TEXT_BOX, 100);{
			
			column.setFormatter(" var ctrl=workspace.parts.byIndex(0).key; return eval(ctrl+'.contactFormatter(value,row,index,\\'座机号\\')');");
		}
		column = addColumn(datagrid, "customer.qq", "QQ", ControlTypes.TEXT_BOX, 100);{
			
			column.setFormatter(" var ctrl=workspace.parts.byIndex(0).key; return eval(ctrl+'.contactFormatter(value,row,index,\\'QQ号\\')');");
		}
		
		column = addColumn(datagrid, "customer.weixin", "微信", ControlTypes.TEXT_BOX, 100);{
			
			column.setFormatter(" var ctrl=workspace.parts.byIndex(0).key; return eval(ctrl+'.contactFormatter(value,row,index,\\'微信号\\')');");
		}

		// addColumn(datagrid, "customer.realName", "其他联系方式",
		// ControlTypes.TEXT_BOX, 100, true);

		addColumn(datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 80, false);
		addColumn(datagrid, "intentionCategory", "质量分类", ControlTypes.ENUM_BOX, 100, false);
		column = addColumn(datagrid, "qualityId", "客户质量id", ControlTypes.TEXT_BOX, 100, false);{
			column.setSystem(true);
			column.setVisible(false);
		}
		addColumn(datagrid, "quality.name", "客户质量", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 100, false);
		addColumn(datagrid, "lastFoolowUser.name", "最后跟进人", ControlTypes.TEXT_BOX, 100, false);

		// addColumn(datagrid, "customer.realName", "意向产品",
		// ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "source.name", "任务来源", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "lastContent", "最后跟进内容", ControlTypes.TEXT_BOX, 300, false);
		addColumn(datagrid, "lastFollowTime", "最后跟进时间", ControlTypes.DATETIME_BOX, 130, false);

		// 未跟进天数
		// 费用部门
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130, false);

		column = addColumn(datagrid, "qualityProgress", "质量进度", ControlTypes.TEXT_BOX, 130, false);{
			
			column.setVisible(false);
			column.setSystem(true);
		}

		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.setColumnCount(3);
		addQueryItem(queryProject, "lastFollowTime", "最后跟进时间", ControlTypes.DATE_BOX);
		PQueryItem item = addQueryItem(queryProject, "unFollowDayCount", "未跟进天数", ControlTypes.NUMBER_BOX);
		{
			item.setInterzone(true);
		}
		return queryProject;
	}
}