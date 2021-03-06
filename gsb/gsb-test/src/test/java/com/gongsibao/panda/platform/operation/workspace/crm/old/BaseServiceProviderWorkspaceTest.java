//package com.gongsibao.panda.platform.operation.workspace.crm.old;
//
//import org.junit.Before;
//import org.netsharp.communication.ServiceFactory;
//import org.netsharp.core.MtableManager;
//import org.netsharp.meta.base.WorkspaceCreationBase;
//import org.netsharp.organization.base.IOperationService;
//import org.netsharp.organization.dic.OperationTypes;
//import org.netsharp.panda.controls.ControlTypes;
//import org.netsharp.panda.dic.DatagridAlign;
//import org.netsharp.panda.dic.DockType;
//import org.netsharp.panda.dic.PartType;
//import org.netsharp.panda.entity.PDatagrid;
//import org.netsharp.panda.entity.PDatagridColumn;
//import org.netsharp.panda.entity.PForm;
//import org.netsharp.panda.entity.PFormField;
//import org.netsharp.panda.entity.PPart;
//import org.netsharp.panda.entity.PQueryProject;
//import org.netsharp.panda.entity.PWorkspace;
//import org.netsharp.resourcenode.entity.ResourceNode;
//import org.netsharp.util.ReflectManager;
//
//import com.gongsibao.controls.CityComboBox;
//import com.gongsibao.entity.crm.base.ServiceProvider;
//import com.gongsibao.entity.crm.base.ServiceProviderScope;
//import com.gongsibao.entity.product.Product;
//
//public class BaseServiceProviderWorkspaceTest extends WorkspaceCreationBase{
//
//	@Override
//	@Before
//	public void setup() {
//		entity = ServiceProvider.class;
//		//配置资源路径
//		urlList = "/crm/service/provider/list";
//		//配置表单路径
//		urlForm = "/crm/service/provider/from";
//		listPartName = formPartName = "服务商档案";
//		meta = MtableManager.getMtable(entity);
//		formPartName = listPartName = meta.getName();
//		resourceNodeCode = "Service_Provider_"+ServiceProvider.class.getSimpleName();
//		
//		//选项卡页面的js
//		formJsImport = "/gsb/platform/operation/crm/js/old/service.provider.form.part.js|/gsb/panda-extend/gsb.customer.controls.js";
//	}
//	
//	@Override
//	protected PDatagrid createDatagrid(ResourceNode node) {
//		PDatagrid datagrid = super.createDatagrid(node);
//		//datagrid.setToolbar("panda/datagrid/row/edit");
//		PDatagridColumn column = null;
//		addColumn(datagrid, "serviceName", "服务商名称", ControlTypes.TEXT_BOX, 100, true);
//		column = addColumn(datagrid, "address", "地址", ControlTypes.TEXT_BOX, 100, true);{
//			column.setFormatter("return '<span title='+value+'>'+value+'</span>'");
//		}
//		column = addColumn(datagrid, "mobilePhone", "账号", ControlTypes.TEXT_BOX, 100);{			
//			column.setAlign(DatagridAlign.CENTER);
//		}
//		addColumn(datagrid, "isProprietary", "是否自营", ControlTypes.BOOLCOMBO_BOX, 100);
//		addColumn(datagrid, "customerPoolNumber", "客户池数量", ControlTypes.NUMBER_BOX, 100);		
//		addColumn(datagrid, "isPushReport", "是否推送报表", ControlTypes.BOOLCOMBO_BOX, 120);
//		addColumn(datagrid, "messageNotifiedType", "消息通知类型 ", ControlTypes.NUMBER_BOX, 120);
//		addColumn(datagrid, "isAutoAssign", "是否自动分配", ControlTypes.BOOLCOMBO_BOX, 120);
//		addColumn(datagrid, "isAutoRelease", "是否自动释放", ControlTypes.BOOLCOMBO_BOX, 120);
//		addColumn(datagrid, "noFollowDays", "未跟进天数释放 ", ControlTypes.NUMBER_BOX, 120);
//		
//		column = addColumn(datagrid, "isEnableDepart", "是否启用部门", ControlTypes.BOOLCOMBO_BOX, 120);
//		{
//			column.setStyler("return row.enabled==false?'color:red;':'color:#5FB878;';");
//			column.setFormatter(" return value==false?'不启用':'启用';");
//		}
//		addColumn(datagrid, "departLevel", "部门级次", ControlTypes.NUMBER_BOX, 120);
//		return datagrid;
//	}
//	
//	//配置查询条件
//	@Override
//	protected PQueryProject createQueryProject(ResourceNode node) {
//
//		PQueryProject queryProject = super.createQueryProject(node);
//		queryProject.toNew();
//		addQueryItem(queryProject, "serviceName", "服务商名称", ControlTypes.TEXT_BOX);
//		addQueryItem(queryProject, "mobilePhone", "账号", ControlTypes.TEXT_BOX);
//		return queryProject;
//	}
//	
//	//创建选项卡
//	@Override
//	protected void addDetailGridPart(PWorkspace workspace) {
//		addServiceCapacityPart(workspace);
//	}
//	//选项卡加载项
//	private void addServiceCapacityPart(PWorkspace workspace) {
//		ResourceNode node = this.resourceService.byCode(ServiceProviderScope.class.getSimpleName());
//		PDatagrid datagrid = new PDatagrid(node, "服务范围");
//		{
//			addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 300);
//			addColumn(datagrid, "dProvince.name", "省份", ControlTypes.TEXT_BOX, 150);
//			addColumn(datagrid, "dCity.name", "城市", ControlTypes.TEXT_BOX, 150);
//			addColumn(datagrid, "dCounty.name", "区/县", ControlTypes.TEXT_BOX, 150);
//		}
//		PForm form = new PForm();
//		{
//			form.toNew();
//			form.setResourceNode(node);
//			form.setColumnCount(1);
//			form.setName("服务范围");
//			PFormField formField = null;
//			formField = addFormFieldRefrence(form, "product.name", "服务能力配置", null, "CRM_" + Product.class.getSimpleName(), true, false);
//			{
//				//参数controllerserviceScope-页面源码中的、productChange-自定义的js中
//				formField.setTroikaTrigger("controllerserviceScope.productChange(newValue,oldValue);");
//				formField.setWidth(300);
//			}
//			
//			formField = addFormField(form, "dProvince.name", "省份", ControlTypes.CUSTOM, false, false);
//			{
//				formField.setCustomControlType(CityComboBox.class.getName());
//				formField.setDataOptions("level:1,changeCtrlId:'dCity_name'");
//				formField.setWidth(300);
//			}
//			formField = addFormField(form, "dCity.name", "城市", ControlTypes.CUSTOM, false, false);
//			{
//				formField.setCustomControlType(CityComboBox.class.getName());
//				formField.setDataOptions("level:2,changeCtrlId:'dCounty_name'");
//				formField.setWidth(300);
//			}
//			formField = addFormField(form, "dCounty.name", "区/县", ControlTypes.CUSTOM, false, false);
//			{
//				formField.setCustomControlType(CityComboBox.class.getName());
//				formField.setDataOptions("level:3");
//				formField.setWidth(300);
//			}
//		}
//
//		PPart part = new PPart();
//		{
//			part.toNew();
//			part.setName("服务范围");
//			part.setCode("serviceScope");
//			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
//			part.setRelationRole("serviceScope");
//			part.setResourceNode(node);
//			part.setPartTypeId(PartType.DETAIL_PART.getId());
//			part.setDatagrid(datagrid);
//			part.setDockStyle(DockType.DOCUMENTHOST);
//			part.setToolbar("panda/datagrid/detail");
//			part.setJsController("com.gongsibao.crm.web.ProdMapDetailPart");
//			part.setWindowWidth(550);
//			part.setWindowHeight(350);
//			part.setForm(form);
//		}
//		workspace.getParts().add(part);
//
//		part = workspace.getParts().get(0);
//		{
//			part.setName("服务商档案");
//			part.setStyle("height:500px;");
//			part.setDockStyle(DockType.DOCUMENTHOST);
//		}
//	}
//	
//	
//	//创建表单。须配置urlForm路径
//	@Override
//	protected PForm createForm(ResourceNode node) {
//		PForm form = super.createForm(node);
//		String groupName = null;
//		PFormField field = null;
//		addFormField(form, "serviceName", "服务商名称", groupName, ControlTypes.TEXT_BOX, true, false);
//		field = addFormField(form, "mobilePhone", "手机号", groupName, ControlTypes.TEXT_BOX, true, false);
//		{
//			field.setTroikaValidation("['validationMobile']");
//		}
//		addFormField(form, "isProprietary", "是否自营", groupName, ControlTypes.SWITCH_BUTTON, false, false);
//		
//		addFormField(form, "customerPoolNumber", "客户池数量", groupName, ControlTypes.NUMBER_BOX, false, false);
//		addFormField(form, "isPushReport", "是否推送报表", groupName, ControlTypes.SWITCH_BUTTON, false, false);
//		addFormField(form, "messageNotifiedType", "消息通知类型", groupName, ControlTypes.ENUM_BOX, false, false);
//		
//		addFormField(form, "isAutoAssign", "是否自动分配", groupName, ControlTypes.SWITCH_BUTTON, false, false);
//		addFormField(form, "isAutoRelease", "是否自动释放", groupName, ControlTypes.SWITCH_BUTTON, false, false);
//		addFormField(form, "noFollowDays", "未跟进天数释放", groupName, ControlTypes.NUMBER_BOX, false, false);
//		
//		addFormField(form, "isEnableDepart", "是否启用部门", groupName, ControlTypes.SWITCH_BUTTON, false, false);
//		addFormField(form, "departLevel", "部门级次", groupName, ControlTypes.NUMBER_BOX, false, false);
//		
//		
//		field = addFormField(form, "address", "地址", groupName, ControlTypes.TEXT_BOX, false, false);{
//			
//			field.setFullColumn(true);
//	    }
//		return form;
//	}
//	
//	
//	public void operation() {
//		ResourceNode node = resourceService.byCode(resourceNodeCode);
//		IOperationService service = ServiceFactory.create(IOperationService.class);
//		service.addOperation(node, OperationTypes.view);
//		service.addOperation(node, OperationTypes.add);
//		service.addOperation(node, OperationTypes.update);
//	}
//}
