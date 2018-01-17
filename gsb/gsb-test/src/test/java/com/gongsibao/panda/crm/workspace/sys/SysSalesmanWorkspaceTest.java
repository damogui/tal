package com.gongsibao.panda.crm.workspace.sys;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.*;
import org.netsharp.resourcenode.entity.ResourceNode;
import com.gongsibao.entity.supplier.Salesman;

//员工管理
public class SysSalesmanWorkspaceTest  extends WorkspaceCreationBase{
	public void setup() {
		super.setup();
		urlList = "/crm/sys/salesman/list";
        urlForm = "/crm/sys/salesman/form";
		entity = Salesman.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_SYS_SALESMAN";
		formOpenMode = OpenMode.WINDOW;
	
	}



    @Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setName("员工管理");
		}
		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		addColumn(datagrid, "employee_id", "部门编码", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "department.name", "部门名称", ControlTypes.TEXT_BOX, 80);
	
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "create_time", "创建时间", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "update_time", "修改时间", ControlTypes.TEXT_BOX, 100);
		
		return datagrid;
	}
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
//		addQueryItem(queryProject, "name", "部门名称", ControlTypes.TEXT_BOX);
//		addQueryItem(queryProject, "disabled", "是否停用", ControlTypes.BOOLCOMBO_BOX);
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	//表单填充字段
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);
		//addFormFieldRefrence类中类字段
		addFormField(form, "creator", "创建人", null, ControlTypes.TEXT_BOX, false);
		addFormField(form, "create_time", "创建时间", null, ControlTypes.DATETIME_BOX, true);

		return form;
	}

	@Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
		operationService.addOperation(node,OperationTypes.delete);
	}


    @Test
    @Override
    public void run() {
        this.createTreeWorkspace();
//        this.createFormWorkspace();
    }
//配置树状结构
    public void createTreeWorkspace() {

        ResourceNode node = resourceService.byCode("GSB_CRM_SYS_DEPARTMENT");//树状的节点
        IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
        OperationType operationType = operationTypeService.byCode(OperationTypes.view);

        PWorkspace workspace = new PWorkspace();
        {
            workspace.toNew();
            workspace.setResourceNode(node);
            workspace.setOperationType(operationType);
            workspace.setOperationTypeId(operationType.getId());
            workspace.setName("部门管理");
            workspace.setUrl(urlList);
        }

        PPart part = new PPart();//创建部分
        {
            part.toNew();
            part.setCode("GsbCrmSysDepartmentTree");//树名
            part.setPartTypeId(org.netsharp.panda.dic.PartType.TREE_PART.getId());
            part.setDockStyle(DockType.LEFT);
            part.setStyle("width:250px;");
            part.setResourceNode(node);
        }
        workspace.getParts().add(part);

        ResourceNode node2 = resourceService.byCode(resourceNodeCode);
        PDatagrid datagrid = this.createDatagrid(node2);
        part = new PPart();
        {
            part.toNew();
            part.setCode("departments");
            part.setParentCode("GsbCrmSysDepartmentTree");// 点击父之后，刷新自己
            part.setPartTypeId(org.netsharp.panda.dic.PartType.DATAGRID_PART.getId());
            part.setDatagrid(datagrid);
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setRelationRole("departmentId");// 点击父之后，刷新自己所传的参数
            part.setResourceNode(node2);
            part.setUrl(urlForm);
            part.setToolbar(listToolbarPath);
            part.setJsController(listPartJsController);
            part.setServiceController(listPartServiceController);
            part.setImports(listPartImportJs);
        }

        workspace.getParts().add(part);

        workspaceService.save(workspace);
    }

}
