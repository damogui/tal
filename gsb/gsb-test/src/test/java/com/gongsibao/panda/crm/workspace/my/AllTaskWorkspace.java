package com.gongsibao.panda.crm.workspace.my;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.panda.utils.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.controls.CityComboBox;
import com.gongsibao.controls.DictComboBox;
import com.gongsibao.crm.web.MyAllTaskListPart;
import com.gongsibao.crm.web.NCustomerFollowPart;
import com.gongsibao.entity.cms.Product;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerProduct;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.entity.crm.dic.QualityCategory;

public class AllTaskWorkspace extends WorkspaceCreationBase{

	//行的toolbar
	String listRowToolbarPath=null;
	@Override
	@Before
	public void setup() {
			
		entity = NCustomerTask.class;
		//配置资源路径
		urlList = "/crm/my/task/all/list";
		//配置表单路径
		urlForm = "/crm/my/task/all/from";
		listPartName = formPartName = "全部任务";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_MY_TASK_ALL";
		listFilter = "ownerId = '{userId}'";
		//扩展子页面操作
		formJsImport = "/gsb/crm/js/crm-allTask-part.js|/gsb/gsb.customer.controls.js";
		
		//行的toolbar
		listRowToolbarPath = "crm/my/task/all/row/toolbar";
		
		//扩展列表操作
		listToolbarPath = "crm/my/task/all/toolbar";
		listPartImportJs ="/gsb/crm/js/crm-allTask-list.js";
		listPartJsController = MyAllTaskListPart.class.getName();
		listPartServiceController = MyAllTaskListPart.class.getName();
	}

	//列表Toolbar
	@Test
	public void createListToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("开通会员工具栏操作");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "openMemberPopup", "开通会员", "fa fa-edit", "openMemberPopup()", null, 5);
		toolbarService.save(toolbar);
	}
	@Test
	public void createRowToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/row/edit");
			toolbar.setPath(listRowToolbarPath);
			toolbar.setName("跟进行工具栏操作");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "followUpPopup", "跟进", "fa fa-edit", "followUpPopup()", null, 6);
		addToolbarItem(toolbar, "backTaskPopup", "释放", "fa fa-edit", "backTaskPopup()", null, 7);
		toolbarService.save(toolbar);
	}
	
	@Test
	public void detailPart() {
		ResourceNode node = this.resourceService.byCode(NCustomerTaskFoolow.class.getSimpleName());
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath("crm/task/communicatLog/follow");
			toolbar.setName("子页面中的工具栏操作");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-mail-reply-all");
			item.setName("跟进操作");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(1);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		//添加行的toolbar
		datagrid.setToolbar(listRowToolbarPath);
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		addColumn(datagrid, "department.name", "分配服务商部门", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "name", "任务名称", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.id", "客户ID", ControlTypes.NUMBER_BOX, 100, false);
		addColumn(datagrid, "customer.isMember", "是否会员", ControlTypes.BOOLCOMBO_BOX, 100, false);
		addColumn(datagrid, "customer.realName", "联系人", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "customer.mobile", "手机号", ControlTypes.TEXT_BOX, 100, false);{
			column.setFormatter("if(value&&value.length==11){return value.substr(0,3)+'****'+value.substr(7);}");
		}
		addColumn(datagrid, "customer.telephone", "座机", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.qq", "QQ", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.weixin", "微信", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "intentionCategory", "客户质量分类", ControlTypes.ENUM_BOX, 100, false);
		addColumn(datagrid, "source.name", "任务来源", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "lastFollowTime", "最近跟进时间", ControlTypes.DATE_BOX, 100, false);
		addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 100, false);
		addColumn(datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "ownerId", "业务员Id", ControlTypes.TEXT_BOX, 100, false);{
			column.setSystem(true);
			column.setVisible(false);
		}
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 100, false);
		return datagrid;
	}
	
	//配置查询条件
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		item = addQueryItem(queryProject, "customer.realName", "客户", ControlTypes.TEXT_BOX);{
			item.setRequired(true);
		}
		return queryProject;
	}
	//创建选项卡
	@Override
	protected void addDetailGridPart(PWorkspace workspace) {
		addCustomerInfoPart(workspace);
		addIntenProductPart(workspace);
		addCommunicatLogsPart(workspace);
		addNotificationLogPart(workspace);
		addFlowLogPart(workspace);
	}
	//选项卡加载项
	private void addCustomerInfoPart(PWorkspace workspace) {
			
	}
	private void addIntenProductPart(PWorkspace workspace) {
		//需要配置NCustomerProduct资源
		ResourceNode node = this.resourceService.byCode(NCustomerProduct.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "意向产品");
		{
			addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 300);
			addColumn(datagrid, "province.name", "省份", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "city.name", "城市", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "county.name", "区/县", ControlTypes.TEXT_BOX, 150);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("意向产品");

			PFormField formField = null;
			formField = addFormFieldRefrence(form, "product.name", "意向产品", null, "CRM_N" + Product.class.getSimpleName(), true, false);
			{
				formField.setTroikaTrigger("controllerproducts.productChange(newValue,oldValue);");
				formField.setWidth(300);
			}
			formField = addFormField(form, "province.name", "省份", ControlTypes.CUSTOM, false, false);
			{
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:1,changeCtrlId:'city_name'");
				formField.setWidth(300);
			}
			formField = addFormField(form, "city.name", "城市", ControlTypes.CUSTOM, false, false);
			{
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:2,changeCtrlId:'county_name'");
				formField.setWidth(300);
			}
			//自定义控件(公用的，里面有一些逻辑)
			formField = addFormField(form, "county.name", "区/县", ControlTypes.CUSTOM, false, false);
			{
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:3");
				formField.setWidth(300);
			}
		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("意向产品");
			part.setCode("products");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("products");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setJsController("com.gongsibao.crm.web.NProdMapDetailPart");
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}
	private void addCommunicatLogsPart(PWorkspace workspace) {
		//需要配置NCustomerTaskFoolow资源
		ResourceNode node = this.resourceService.byCode(NCustomerTaskFoolow.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "沟通日志");
		{
			PDatagridColumn column = addColumn(datagrid, "foolowStatus", "跟进状态", ControlTypes.ENUM_BOX, 150);{
				String formatter = EnumUtil.getColumnFormatter(CustomerFollowStatus.class);
				column.setFormatter(formatter);
			}
			column = addColumn(datagrid, "qualityCategory", "质量分类", ControlTypes.ENUM_BOX, 180);{
				String formatter = EnumUtil.getColumnFormatter(QualityCategory.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 200);
			addColumn(datagrid, "estimateAmount", "估计签单金额", ControlTypes.DECIMAL_FEN_BOX, 150);
			addColumn(datagrid, "content", "跟进内容", ControlTypes.TEXT_BOX, 250);{
				column.setFormatter("return '<span title='+value+'>'+value+'</span>'");
			}
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(2);
			form.setName("沟通日志");
			PFormField formField = null;
			String groupName = null;
			
			formField = addFormField(form, "foolowStatus", "跟进状态", groupName, ControlTypes.ENUM_BOX, false, false);
			addFormField(form, "nextFoolowTime", "下次跟进时间", groupName, ControlTypes.DATE_BOX, false, false);
			addFormField(form, "qualityCategory", "质量分类", groupName, ControlTypes.ENUM_BOX, false, false);
			addFormField(form, "estimateAmount", "估计签单金额", groupName, ControlTypes.DECIMAL_FEN_BOX, false, false);
			formField = addFormField(form, "content", "跟进内容", groupName, ControlTypes.TEXTAREA, false, false);{			
				formField.setFullColumn(true);
		    }
		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("沟通日志");
			part.setCode("follows");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("follows");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("crm/task/communicatLog/follow");
			part.setJsController(NCustomerFollowPart.class.getName());
			part.setServiceController(NCustomerFollowPart.class.getName());
			part.setWindowWidth(700);
			part.setWindowHeight(400);
			part.setForm(form);
		}
		workspace.getParts().add(part);
		part = workspace.getParts().get(0);
		{
			part.setName("基本信息");
			part.setStyle("height:500px;");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
	}
	private void addNotificationLogPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode(NCustomerTaskNotify.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "通知日志");
		{
			PDatagridColumn column = addColumn(datagrid, "type", "通知类型", ControlTypes.ENUM_BOX, 100);{
				String formatter = EnumUtil.getColumnFormatter(NotifyType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "content", "跟进内容", ControlTypes.TEXT_BOX, 200);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("通知日志");
			PFormField formField = null;
			String groupName = null;
			
			formField = addFormField(form, "type", "通知类型", groupName, ControlTypes.ENUM_BOX, false, false);
			formField = addFormField(form, "content", "跟进内容", groupName, ControlTypes.TEXT_BOX, false, false);{			
				formField.setFullColumn(true);
		    }
		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("通知日志");
			part.setCode("notifys");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("notifys");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}
	private void addFlowLogPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode(NCustomerChange.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "流转日志");
		{
			//子页面枚举显示需要格式化一下
			PDatagridColumn column = addColumn(datagrid, "changeType", "流转类型", ControlTypes.ENUM_BOX, 100);{
				String formatter = EnumUtil.getColumnFormatter(ChangeType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "formUser.name", "来自", ControlTypes.ENUM_BOX, 100);
			addColumn(datagrid, "toUser.name", "去向", ControlTypes.NUMBER_BOX, 100);
			addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 300);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("流转日志");
			PFormField formField = null;
			String groupName = null;
			formField = addFormField(form, "changeType", "流转类型", groupName, ControlTypes.ENUM_BOX, false, false);
			formField = addFormFieldRefrence(form, "formUser.name", "最后分配人", groupName, "CRM_Employee", false, false);
			formField = addFormFieldRefrence(form, "toUser.name", "最后分配人", groupName, "CRM_Employee", false, false);
			formField = addFormField(form, "content", "内容", groupName, ControlTypes.TEXT_BOX, false, false);{			
				formField.setFullColumn(true);
		    }
		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("流转日志");
			part.setCode("changes");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("changes");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}
	
	//创建表单。须配置urlForm路径
	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(3);
		}
		PFormField formField = null;
		String groupName = "任务信息";	
		addFormField(form, "id", "任务ID", groupName, ControlTypes.NUMBER_BOX, true, true);
		addFormField(form, "name", "任务名称", groupName, ControlTypes.TEXT_BOX, true, false);
		addFormFieldRefrence(form, "supplier.name", "分配服务商", groupName, "CRM_Supplier", false, false);
		addFormFieldRefrence(form, "department.name", "分配服务商部门", groupName, "CRM_Supplier_Depart", false, false);
		formField = addFormField(form, "foolowStatus", "跟进状态", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "intentionCategory", "质量分类", groupName, ControlTypes.ENUM_BOX, false, false);
		formField = addFormField(form, "memoto", "备注", groupName, ControlTypes.TEXTAREA, false, false);{			
			formField.setFullColumn(true);
	    }
		groupName = "分配属性";
		addFormField(form, "allocationType", "分配方式", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "allocationState", "分配状态", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "allocationDispositon", "自营/平台", groupName, ControlTypes.ENUM_BOX, false, false);
		formField = addFormField(form, "source.name", "任务来源", groupName, ControlTypes.CUSTOM, true, false);
		{
			formField.setCustomControlType(DictComboBox.class.getName());
			formField.setRefFilter("type=411");
		}
		//addFormField(form, "taskCustomerType", "任务类型", groupName, ControlTypes.ENUM_BOX, false, false);
		
		groupName = "最近跟进信息";
		addFormFieldRefrence(form, "lastAllocationUser.name", "最后分配人", groupName, "CRM_Employee", false, true);
		addFormFieldRefrence(form, "lastFoolowUser.name", "最后跟进人", groupName, "CRM_Employee", false, true);
		addFormField(form, "foolowStatus", "跟进状态", groupName, ControlTypes.ENUM_BOX, false, true);
		addFormField(form, "lastFollowTime", "最近跟进时间", groupName, ControlTypes.DATE_BOX, false, true);
		addFormField(form, "nextFoolowTime", "下次跟进时间", groupName, ControlTypes.DATE_BOX, false, true);
		formField = addFormField(form, "lastContent", "最后跟进内容", groupName, ControlTypes.TEXTAREA, false, true);{			
			formField.setFullColumn(true);
	    }
		return form;
	}	
	public void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
