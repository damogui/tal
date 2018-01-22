package com.gongsibao.panda.crm.workspace.sys;

import com.gongsibao.crm.web.SysDepartmentTreeGridPart;
import com.gongsibao.crm.web.SysSalesmanTreePart;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.organization.entity.Role;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.crm.web.SysSalesmanListPart;
import com.gongsibao.entity.supplier.Salesman;

//员工管理
public class SysSalesmanWorkspaceTest extends WorkspaceCreationBase {
    public void setup() {
        super.setup();
        urlList = "/crm/sys/salesman/list";
        urlForm = "/crm/sys/salesman/form";
        entity = Salesman.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "GSB_CRM_SYS_SALESMAN";
        formOpenMode = OpenMode.WINDOW;
        listPartImportJs = "/gsb/crm/sys/js/sys-salesman-list-part.js";
        listPartJsController = SysSalesmanListPart.class.getName();
        listPartServiceController = SysSalesmanListPart.class.getName();

    }


    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setName("员工管理");
            datagrid.setToolbar("panda/datagrid/row/edit");//列表出现操作必须填写
            datagrid.setAutoQuery(false);//进来的时候列表不自动查询数据
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "employee.name", "姓名", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "employee.login_name", "登录名", ControlTypes.TEXT_BOX, 100, true);//login_name
//        addColumn(datagrid, "employee.disabled", "状态2", ControlTypes.TEXT_BOX, 100, true);
        column = addColumn(datagrid, "employee.disabled", "状态", ControlTypes.BOOLCOMBO_BOX, 100, true);
        {
//            column.setStyler("color:red;");
            column.setStyler(" return value==true?'color:red;':'color:#5FB878;';");
            column.setFormatter(" return value==false?'启用':'停用';");
        }
        addColumn(datagrid, "department.name", "部门名称", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "create_time", "创建时间", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "update_time", "修改时间", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "employee.login_num", "登录次数", ControlTypes.TEXT_BOX, 100, true);

        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
//		addQueryItem(queryProject, "name", "部门名称", ControlTypes.TEXT_BOX);
//		addQueryItem(queryProject, "disabled", "是否停用", ControlTypes.BOOLCOMBO_BOX);
        addQueryItem(queryProject, "employee.name", "姓名", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "employee.mobile", "手机号", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);


        return queryProject;
    }

    //表单填充字段
    protected PForm createForm(ResourceNode node) {

        PForm form = super.createForm(node);
        form.setColumnCount(2);
        //addFormFieldRefrence类中类字段
        addFormField(form, "name", "姓名", null, ControlTypes.TEXT_BOX, true);
        addFormField(form, "mobile", "手机号", null, ControlTypes.TEXT_BOX, true);
        // addFormField(form, "employeeId", "登录名", null, ControlTypes.D, false);
        addFormField(form, "bankNo", "工资卡号", null, ControlTypes.TEXT_BOX, false);
        addFormField(form, "entryDate", "入职日期", null, ControlTypes.DATETIME_BOX, false);
        addFormField(form, "quitDate", "离职日期", null, ControlTypes.DATETIME_BOX, false);
        addFormField(form, "disabled", "停用", null, ControlTypes.SWITCH_BUTTON, false);


        return form;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode(resourceNodeCode);
        operationService.addOperation(node, OperationTypes.view);
        operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
		operationService.addOperation(node,OperationTypes.delete);
    }


    @Test
    @Override
    public void run() {
        this.createTreeWorkspace();
        this.createFormWorkspace();
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
            workspace.setName("员工管理");
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
            //进行扩展
           // part.setToolbar(listToolbarPath);

            part.setJsController(SysSalesmanTreePart.class.getName());
            part.setServiceController(SysSalesmanTreePart.class.getName());
            part.setImports("/gsb/crm/sys/js/sys-salesman-tree-part.js");
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

//创建明细里面的弹窗操作
    @Override
    protected void addDetailGridPart(PWorkspace workspace) {

        // 添加角色
      addRolesDetailPart(workspace);



    }

    // 添加角色
    private void addRolesDetailPart(PWorkspace workspace) {

        ResourceNode node = this.resourceService.byCode("GSB_CRM_SYS_SALESMAN_ADDROLE");
        PDatagrid datagrid = new PDatagrid(node, "添加角色");//列表展示
        {
//            datagrid.setShowCheckbox(true);
//            datagrid.setSingleSelect(false);
//            datagrid.setReadOnly(true);
//            datagrid.setName("添加角色");
            PDatagridColumn column = null;
            /*addColumn(datagrid, "role.name", "角色", ControlTypes.TEXT_BOX, 100);
            column = addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100, false, null, null, null);
            {
                column.setAlign(DatagridAlign.CENTER);
            }
            addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.DATETIME_BOX, 150, false, null, null, null);
            column = addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false, null, null, null);
            {
                column.setAlign(DatagridAlign.CENTER);
            }*/
            addColumn(datagrid, "role.name", "角色", ControlTypes.TEXT_BOX, 150, false, null, null, null);

        }

        PForm form = new PForm();//添加表单
        {
            form.setResourceNode(node);
            form.toNew();
            form.setColumnCount(2);
            form.setName("添加角色");
            PFormField field = null;
            field = addFormFieldRefrence(form, "role.name", "角色", null, Role.class.getSimpleName(), true, false);

        }

        PPart part = new PPart();
        {
            part.toNew();
            part.setName("添加角色");
            part.setCode("roles");
            part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
            part.setRelationRole("roles");
            part.setResourceNode(node);
            part.setPartTypeId(PartType.DETAIL_PART.getId());
            part.setDatagrid(datagrid);
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setToolbar("panda/datagrid/detail");
            part.setWindowWidth(550);
            part.setWindowHeight(200);
            part.setForm(form);
        }
        workspace.getParts().add(part);

       part = workspace.getParts().get(0);
       {
            part.setName("基本信息");
           part.setDockStyle(DockType.TOP);
      }


    }


}
