package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.utils.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.controls.CityComboBox;
import com.gongsibao.controls.DictComboBox;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NotifyType;

public class CustomerALLWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = NCustomer.class;// 实体
		urlList = "/operation/customer/all/list";// 列表的url
		listPartName = formPartName = "全部客户";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_Customer_Manager_ALL";
		formJsImport = "/gsb/gsb.customer.controls.js";
		formOpenMode = OpenMode.OPEN;// 编辑框打开的形式
	}

	// 默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("全部客户列表");
		}

		PDatagridColumn column = null;
		addColumn(datagrid, "realName", "名称", ControlTypes.TEXT_BOX, 120);
		addColumn(datagrid, "sex", "性别", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "mobile", "手机号码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "telephone", "座机", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "qq", "QQ", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "weixin", "微信", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "province.name", "省份", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "city.name", "城市", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "county.name", "区/县", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "consultWay", "CRM咨询途径", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "important", "重要程度", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "intentionCategory", "质量分类", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "lastFollowTime", "最近跟进时间", ControlTypes.DATETIME_BOX, 20);
		addColumn(datagrid, "lastFoolowUser.name", "最后跟进人", ControlTypes.DATETIME_BOX, 100);
		addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATETIME_BOX, 20);
		addColumn(datagrid, "customerSource.name", "客户来源", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "creator", "添加人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "添加时间", ControlTypes.DATETIME_BOX, 20);
		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		addQueryItem(queryProject, "realName", "名称", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
