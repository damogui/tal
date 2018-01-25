package com.gongsibao.panda.ncl.workspace;

import com.gongsibao.entity.ncl.NclBatch;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.*;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

public class NclBatchWorkspaceTest extends WorkspaceCreationBase {
    @Override
    public void setup() {
        super.setup();
        urlList = "/ncl/batch/list";
        urlForm = "/ncl/batch/form";
        entity = NclBatch.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        formOpenMode = OpenMode.WINDOW;
        openWindowWidth = 800;
        openWindowHeight = 600;
        resourceNodeCode = "NCL_All_NclBatch";
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {
        PDatagrid pid = super.createDatagrid(node);
        pid.setToolbar("panda/datagrid/row/edit"); //系统默认的工具栏
        pid.setName("分类列表");
        PDatagridColumn column = null;
        addColumn(pid, "code", "编号", ControlTypes.TEXT_BOX, 100);
        addColumn(pid, "context", "说明", ControlTypes.TEXT_BOX, 200);
        return pid;
    }

    @Override
    protected PForm createForm(ResourceNode node) {
        PForm form = new PForm(node, this.formPartName);
        form.setResourceNode(node);
        form.setName(this.meta.getName() + "表单");
        form.setColumnCount(2);//每行列数
        PFormField field = null;
        addFormField(form, "code", "编号", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
        addFormField(form, "context", "说明", null, ControlTypes.TEXTAREA, true,false).setWidth(200);
        return form;
    }

    /*protected void addDetailGridPart(PWorkspace workspace) {
        createNclBatchPart(workspace);
    }*/

    private void createNclBatchPart(PWorkspace workspace) {
        ResourceNode node = this.resourceService.byCode("NCL_All_NclBatch");
        PDatagrid pid = new PDatagrid(node, "尼斯分类");
        {
            addColumn(pid, "code", "编号", ControlTypes.TEXT_BOX, 100);
            addColumn(pid, "context", "说明", ControlTypes.TEXT_BOX, 150);
        }
        PForm form = new PForm();
        {
            form.toNew();
            form.setResourceNode(node);
            form.setColumnCount(1);
            form.setName("尼斯分类");
            String groupName = null;
            PFormField formField = null;
            addFormField(form, "code", "编号", groupName, ControlTypes.TEXT_BOX, true, false);
            addFormField(form, "context", "说明", groupName, ControlTypes.TEXT_BOX, true, false);
        }

        PPart part = new PPart();
        {
            part.toNew();
            part.setName("尼斯分类");
            part.setCode("nclBatch");
            part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
            part.setRelationRole("nclBatch");
            part.setResourceNode(node);
            part.setPartTypeId(PartType.DETAIL_PART.getId());
            part.setDatagrid(pid);
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setToolbar("panda/datagrid/detail");
            part.setWindowWidth(800);
            part.setWindowHeight(600);
            part.setForm(form);
        }
        workspace.getParts().add(part);
        part = workspace.getParts().get(0);
        {
            part.setName("尼斯分类");
            part.setStyle("height:500px;");
            part.setDockStyle(DockType.DOCUMENTHOST);
        }
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {
        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        addQueryItem(queryProject, "code", "编号", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "context", "说明", ControlTypes.TEXT_BOX);
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
