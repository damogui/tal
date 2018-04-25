package com.gongsibao.panda.supplier.crm.workspace.salesman;

import org.junit.Before;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.controls.PropertyQueryDictComboBox;
import com.gongsibao.entity.crm.NCustomerTaskQuality;


public class SalesmanCheckAbnormalWorkspaceTest extends SalesmanAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		
		listPartName = "抽查异常商机";
		urlList = "/crm/salesman/check/abnormal/list";
		resourceNodeCode = "CRM_SALESMAN_CHECK_ABNORMAL";
		//listFilter = "inspectionState in (3,4) and foolowStatus=4 and ownerId = '{userId}'";
		
		listFilter = "inspectionState in (3,4) and ownerId = '{userId}'";
	}	
	
	@Override
	public PToolbar createListToolbar() {		
		return null;
	}
	
	@Override
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
		column = addColumn(datagrid, "id", "商机ID", ControlTypes.TEXT_BOX, 60, false);{
			
			StringBuilder builder = new StringBuilder();
			builder.append("if(row.qualityProgress=='上升'){return value+'<i title=\\'意向度上升\\' style=\\'font-size: 12px;color:red;float:right;\\' class=\\'fa fa-level-up\\'></i>';}");
			builder.append("else if(row.qualityProgress=='下降'){return value+'<i title=\\'意向度下降\\' style=\\'font-size: 12px;color:gray;float:right;\\' class=\\'fa fa-level-down\\'></i>';}");;
			builder.append("else{return value+'<i title=\\'意向度无变化\\' style=\\'font-size: 12px;color:gray;float:right;\\' class=\\'fa fa-fa-arrows-h\\'></i>';}");
			column.setFormatter(builder.toString());
		}
		addColumn(datagrid, "allocationState", "分配状态", ControlTypes.ENUM_BOX, 100, false);
		addColumn(datagrid, "inspectionState", "抽查状态", ControlTypes.ENUM_BOX, 100, false);
		
		addColumn(datagrid, "owner.name", "业务员", ControlTypes.ENUM_BOX, 100, false);
		addColumn(datagrid, "name", "商机名称", ControlTypes.TEXT_BOX, 250, false);
		addColumn(datagrid, "customer.companyName", "关联公司", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "customerId", "客户ID", ControlTypes.TEXT_BOX, 60, false);
		addColumn(datagrid, "customer.realName", "客户名称", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "allocationDispositon", "自营/平台", ControlTypes.ENUM_BOX, 100, false);
		addColumn(datagrid, "customer.important", "客户等级", ControlTypes.ENUM_BOX, 100, false);
		// addColumn(datagrid, "customer.realName", "公司名称",
		// ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "customer.isMember", "是否会员", ControlTypes.TEXT_BOX, 100, false);
		{

			StringBuilder builder = new StringBuilder();
			builder.append("if(value===true){return '是   ';}");
			builder.append("else{");
			builder.append("if(row.customer_mobile){");
			builder.append("var ctrl = workspace.parts.byIndex(0).key;");
			builder.append("return '否<a title=\\'开通会员\\' class=\\'grid-btn\\' href=javascript:'+ctrl+'.openMember('+row.customerId+',true);>");
			builder.append("<i style=\\'font-size: 12px;color:red;\\' class=\\'fa fa-user-plus\\'></i><a>");
			builder.append("<a title=\\'静默开通\\' class=\\'grid-btn\\' href=javascript:'+ctrl+'.openMember('+row.customerId+',false);>");
			builder.append("<i style=\\'font-size: 12px;\\' class=\\'fa fa-user-plus\\'></i><a>';");
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

		
		addColumn(datagrid, "intentionCategory", "质量分类", ControlTypes.ENUM_BOX, 100, false);
		column = addColumn(datagrid, "qualityId", "客户质量id", ControlTypes.TEXT_BOX, 100, false);{
			column.setSystem(true);
			column.setVisible(false);
		}
		addColumn(datagrid, "quality.name", "客户质量", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 100, false);
		addColumn(datagrid, "lastFoolowUser.name", "最后跟进人", ControlTypes.TEXT_BOX, 100, false);

		// addColumn(datagrid, "customer.realName", "意向产品",
		// ControlTypes.TEXT_BOX, 100, true );
		addColumn(datagrid, "source.name", "商机来源", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "lastContent", "最后跟进内容", ControlTypes.TEXT_BOX, 300, false);
		addColumn(datagrid, "lastFollowTime", "最后跟进时间", ControlTypes.DATETIME_BOX, 130, false);
		//未跟进天数:最近跟进时间距离当天的天数
		column = addColumn(datagrid, "unFollowDays", "未跟进天数", ControlTypes.TEXT_BOX, 130, false);{
			column.setNotPersist(true);
			column.setFormatter(" var ctrl=workspace.parts.byIndex(0).key; return eval(ctrl+'.unFollowDaysFormatter(value,row,index)');");
		}
		
		// 费用部门
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130, false);

		column = addColumn(datagrid, "qualityProgress", "质量进度", ControlTypes.TEXT_BOX, 130, false);{
			
			column.setVisible(false);
			column.setSystem(true);
		}

		column = addColumn(datagrid, "customer.accountId", "会员ID", ControlTypes.TEXT_BOX, 60, false);{

			column.setVisible(false);
			column.setSystem(true);
		}
		return datagrid;
	}
	
	 @Override
	    protected PQueryProject createQueryProject(ResourceNode node) {

	        PQueryProject queryProject = new PQueryProject();
	        queryProject.toNew();
	        queryProject.setResourceNode(node);
	        queryProject.setColumnCount(3);
	        queryProject.setName(listPartName);
	        PQueryItem item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
	        {
	            item.setTooltip("输入客户ID、客户名称、联系方式、关联公司");
	            item.setWidth(300);
	        }
	        item = addQueryItem(queryProject, "source.name", "商机来源", ControlTypes.CUSTOM);
	        {

	            item.setCustomControlType(PropertyQueryDictComboBox.class.getName());
	            item.setRefFilter("type=411");
	        }
//			addRefrenceQueryItem(queryProject, "supplier.name", "服务商", Supplier.class.getSimpleName());
	        addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
	        addRefrenceQueryItem(queryProject, "quality.name", "客户质量", NCustomerTaskQuality.class.getSimpleName());

	        //意向产品
	        addQueryItem(queryProject, "foolowStatus", "跟进状态", ControlTypes.ENUM_BOX);

	        //未跟进天数
	        addQueryItem(queryProject, "lastFollowTime", "最后跟进时间", ControlTypes.DATE_BOX);
	        addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
	        return queryProject;
	    }
}