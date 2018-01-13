package com.gongsibao.panda.crm.workspace.my;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
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
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.controls.CityComboBox;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerProduct;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.product.Product;

public class AllTaskWorkspace extends WorkspaceCreationBase{

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
		
		//选项卡页面的js
		formJsImport = "/gsb/crm/js/service.provider.form.part.js|/gsb/gsb.customer.controls.js";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setAutoQuery(false);
		datagrid.setLazy(true);
		datagrid.setPagination(false);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "customer.realName", "客户", ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "supplier.name", "分配服务商", ControlTypes.TEXT_BOX, 100, false);
		//column = addColumn(datagrid, "department.name", "分配服务商部门", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "lastAllocationTime", "最后分配时间", ControlTypes.DATE_BOX, 100, false);
		column = addColumn(datagrid, "lastAllocationUser.loginName", "最后分配人", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "foolowStatus", "跟进状态", ControlTypes.ENUM_BOX, 100, false);
		column = addColumn(datagrid, "intentionCategory", "意向分类", ControlTypes.ENUM_BOX, 100, false);
		column = addColumn(datagrid, "intention", "意向", ControlTypes.ENUM_BOX, 100, false);
		column = addColumn(datagrid, "lastFollowTime", "最近跟进时间", ControlTypes.DATE_BOX, 100, false);
		column = addColumn(datagrid, "lastFoolowUser", "最后跟进人", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "lastContent", "最后跟进内容", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 100, false);
		column = addColumn(datagrid, "old", "是否老客户", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 100, false);
		
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
		addIntenProductPart(workspace);
		addCommunicatLogsPart(workspace);
		addNotificationLogPart(workspace);
		addFlowLogPart(workspace);
	}
	//选项卡加载项
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
			formField = addFormFieldRefrence(form, "product.name", "意向产品", null, "CRM_" + Product.class.getSimpleName(), true, false);
			{
				//参数controllerserviceScope-页面源码中的、productChange-自定义的js中
				formField.setTroikaTrigger("controllerproducts.productChange(newValue,oldValue);");
				formField.setWidth(300);
			}
			
			formField = addFormField(form, "province.name", "省份", ControlTypes.CUSTOM, false, false);
			{
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:1,changeCtrlId:'city.name'");
				formField.setWidth(300);
			}
			formField = addFormField(form, "city.name", "城市", ControlTypes.CUSTOM, false, false);
			{
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:2,changeCtrlId:'county.name'");
				formField.setWidth(300);
			}
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
			part.setJsController("com.gongsibao.crm.web.ProdMapDetailPart");
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);

		part = workspace.getParts().get(0);
		{
			part.setName("全部任务");
			part.setStyle("height:500px;");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
	}
	private void addCommunicatLogsPart(PWorkspace workspace) {
		//需要配置NCustomerProduct资源
		ResourceNode node = this.resourceService.byCode(NCustomerTaskFoolow.class.getSimpleName());
		
		PDatagrid datagrid = new PDatagrid(node, "沟通日志");
		{
			addColumn(datagrid, "foolowStatus", "跟进状态", ControlTypes.ENUM_BOX, 300);
			addColumn(datagrid, "intentionCategory", "意向分类", ControlTypes.ENUM_BOX, 150);
			addColumn(datagrid, "intention", "意向", ControlTypes.ENUM_BOX, 150);
			addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 150);
			addColumn(datagrid, "estimateAmount", "估计签单金额", ControlTypes.DECIMAL_FEN_BOX, 150);
			addColumn(datagrid, "content", "跟进内容", ControlTypes.TEXT_BOX, 150);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("沟通日志");
			PFormField formField = null;
			String groupName = null;
			formField = addFormField(form, "foolowStatus", "跟进状态", groupName, ControlTypes.ENUM_BOX, false, false);
			addFormField(form, "intentionCategory", "意向分类", groupName, ControlTypes.ENUM_BOX, false, false);
			addFormField(form, "intention", "意向", groupName, ControlTypes.ENUM_BOX, false, false);
			addFormField(form, "nextFoolowTime", "下次跟进时间", groupName, ControlTypes.DATE_BOX, false, false);
			addFormField(form, "estimateAmount", "估计签单金额", groupName, ControlTypes.DECIMAL_FEN_BOX, false, false);
			
			formField = addFormField(form, "content", "跟进内容", groupName, ControlTypes.TEXT_BOX, false, false);{			
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
			//part.setToolbar("panda/datagrid/detail");
			//part.setJsController("com.gongsibao.crm.web.ProdMapDetailPart");
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}
	private void addNotificationLogPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode(NCustomerTaskNotify.class.getSimpleName());
		
		PDatagrid datagrid = new PDatagrid(node, "通知日志");
		{
			addColumn(datagrid, "type", "通知类型", ControlTypes.ENUM_BOX, 300);
			addColumn(datagrid, "content", "跟进内容", ControlTypes.TEXT_BOX, 150);
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
			//part.setToolbar("panda/datagrid/detail");
			//part.setJsController("com.gongsibao.crm.web.ProdMapDetailPart");
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
			addColumn(datagrid, "changeType", "流转类型", ControlTypes.ENUM_BOX, 300);
			addColumn(datagrid, "formUserId", "来自", ControlTypes.ENUM_BOX, 150);
			addColumn(datagrid, "toUserId", "去向", ControlTypes.NUMBER_BOX, 150);
			addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 150);
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
			formField = addFormField(form, "formUserId", "来自", groupName, ControlTypes.NUMBER_BOX, false, false);
			formField = addFormField(form, "toUserId", "去向", groupName, ControlTypes.NUMBER_BOX, false, false);
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
			//part.setToolbar("panda/datagrid/detail");
			//part.setJsController("com.gongsibao.crm.web.ProdMapDetailPart");
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}
	
	//创建表单。须配置urlForm路径
	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = super.createForm(node);
		String groupName = null;
		PFormField field = null;
		addFormField(form, "name", "名称", groupName, ControlTypes.TEXT_BOX, true, false);
		field = addFormField(form, "foolowStatus", "跟进状态", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "intentionCategory", "意向分类", groupName, ControlTypes.ENUM_BOX, false, false);
		
		addFormField(form, "old", "是否老客户", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "intention", "意向", groupName, ControlTypes.ENUM_BOX, false, false);
		
		
		field = addFormField(form, "lastContent", "最后跟进内容", groupName, ControlTypes.TEXT_BOX, false, false);{			
			field.setFullColumn(true);
	    }
		field = addFormField(form, "memoto", "备注", groupName, ControlTypes.TEXT_BOX, false, false);{			
			field.setFullColumn(true);
	    }
		return form;
	}	
	public void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
