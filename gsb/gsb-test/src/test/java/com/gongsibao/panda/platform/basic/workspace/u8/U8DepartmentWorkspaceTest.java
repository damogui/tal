package com.gongsibao.panda.platform.basic.workspace.u8;

import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Department;
import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.*;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * Created by zhangchao on 2018/4/12.
 */
public class U8DepartmentWorkspaceTest extends WorkspaceCreationBase {

    @Before
    public void setup() {

        entity = U8Department.class;// 实体
        urlList = "/basic/u8/department/list";// 列表的url
        urlForm = "/basic/u8/department/form";// 弹出框的url
        listPartName = formPartName = "部门编码管理";
        meta = MtableManager.getMtable(entity);// 获取实体元数据
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "GSB_Basic_U8_" + U8Department.class.getSimpleName();// 菜单节点码（名称）

        formOpenMode = OpenMode.WINDOW;// 编辑框打开的形式
        openWindowWidth = 800;
        openWindowHeight = 450;
    }

    // 默认的grid信息的配置
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.toNew();
            datagrid.setResourceNode(node);
            datagrid.setName("部门编码管理");
        }

        PDatagridColumn column = null;
        addColumn(datagrid, "code", "部门编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "personnelCode", "个人编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
        addColumn(datagrid, "salesman.name", "业务员名称", ControlTypes.TEXT_BOX, 150);
        addColumn(datagrid, "creator", "添加人", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "createTime", "添加时间", ControlTypes.DATETIME_BOX, 20);
        return datagrid;
    }

    // 默认的表单配置信息
    protected PForm createForm(ResourceNode node) {
        PForm form = super.createForm(node);
        PFormField formField = null;
        form.setColumnCount(2);
        addFormField(form, "code", "部门编号", null, ControlTypes.TEXT_BOX, true, false);
        addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, false, false);
        addFormField(form, "personnelCode", "个人编号", null, ControlTypes.TEXT_BOX, false, false);
        addFormFieldRefrence(form, "salesman.name", "业务员", null, "CRM_Employee", true, false);
        return form;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        PQueryItem item = null;
        queryProject.setColumnCount(3);

        addQueryItem(queryProject, "code", "编号", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "salesman.name", "业务员", ControlTypes.TEXT_BOX);
        return queryProject;
    }

    // 默认的表单操作
    @Override
    protected void doOperation() {

        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
        operationService.addOperation(node, OperationTypes.add);
        operationService.addOperation(node, OperationTypes.update);
        operationService.addOperation(node, OperationTypes.delete);
    }
}
