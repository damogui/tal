package com.gongsibao.panda.igirl.workspace;

import com.gongsibao.entity.igirl.baseinfo.IGirlConfig;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.*;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ConfigWorkspaceTest extends WorkspaceCreationBase {

    @Override
    public void setup() {
        super.setup();
        urlList = "/igirl/config/list";
        urlForm = "/igirl/config/form";
        entity = IGirlConfig.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        formOpenMode = OpenMode.WINDOW;
        openWindowWidth = 800;
        openWindowHeight = 600;
        resourceNodeCode = "IGRIL_BASE_IGirlConfig";
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {
        PDatagrid pid = super.createDatagrid(node);
        pid.setToolbar("panda/datagrid/row/edit"); //系统默认的工具栏
        pid.setName("参数列表");
        PDatagridColumn column = null;
        addColumn(pid, "name", "名称", ControlTypes.TEXT_BOX, 200);
        addColumn(pid, "code", "编号", ControlTypes.TEXT_BOX, 200);
        addColumn(pid, "value", "内容", ControlTypes.TEXT_BOX, 200);
        return pid;
    }

    @Override
    protected PForm createForm(ResourceNode node) {
        PForm form = new PForm(node, this.formPartName);
        form.setResourceNode(node);
        form.setName(this.meta.getName() + "表单");
        form.setColumnCount(2);//每行列数
        PFormField field = null;
        addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
        addFormField(form, "code", "编号", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
        addFormField(form, "value", "内容", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
        return form;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {
        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "code", "编号", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "value", "内容", ControlTypes.TEXT_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
        operationService.addOperation(node,OperationTypes.add);
        operationService.addOperation(node,OperationTypes.update);
    }

}
