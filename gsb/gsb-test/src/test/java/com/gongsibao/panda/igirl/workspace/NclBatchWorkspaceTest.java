package com.gongsibao.panda.igirl.workspace;

import com.gongsibao.entity.igirl.baseinfo.NclBatch;
import com.gongsibao.entity.igirl.dict.ConfigType;
import com.gongsibao.igirl.web.NclBatchListPart;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.panda.utils.EnumUtil;
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
        listToolbarPath="/igirl/nclbatch/list";
        listPartServiceController = NclBatchListPart.class.getName();
        listPartJsController=NclBatchListPart.class.getName();
        listPartImportJs="/gsb/igirl/js/nclbatch.listpart.js";
    }


    @Test
    public void fromToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        OperationType ot1 = operationTypeService.byCode(OperationTypes.add);

        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("案件工具栏");
            toolbar.setResourceNode(node);

        }

        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("add");
            item.setIcon("fa fa-plus");
            item.setName("新增");
            item.setCommand(null);
            item.setOperationType(ot1);
            item.setSeq(3000);
            item.setCommand("{controller}.add();");
            toolbar.getItems().add(item);
        }

        item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("edit");
            item.setIcon("fa fa-edit");
            item.setName("编辑");
            item.setCommand(null);
            item.setOperationType(ot1);
            item.setSeq(3000);
            item.setCommand("{controller}.edit();");
            toolbar.getItems().add(item);
        }
        item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("remove");
            item.setIcon("fa fa-trash-o");
            item.setName("删除");
            item.setCommand(null);
            item.setOperationType(ot1);
            item.setSeq(4000);
            item.setCommand("{controller}.remove();");
            toolbar.getItems().add(item);
        }
        item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("nclBatch");
            item.setIcon("fa fa-trash-o");
            item.setName("数据源导入");
            item.setCommand(null);
            item.setOperationType(ot1);
            item.setSeq(4000);
            item.setCommand("{controller}.nclBatch();");
            toolbar.getItems().add(item);
        }
        toolbarService.save(toolbar);
    }


    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {
        PDatagrid pDatagrid = super.createDatagrid(node);
        pDatagrid.setShowCheckbox(true);
        pDatagrid.setSingleSelect(true);
        pDatagrid.setToolbar("panda/datagrid/row/edit"); //系统默认的工具栏
        pDatagrid.setName("分类列表");
        PDatagridColumn column = null;
        column = addColumn(pDatagrid, "code", "编号", ControlTypes.ENUM_BOX, 100);
        {
            String formatter = EnumUtil.getColumnFormatter(ConfigType.class);
            column.setFormatter(formatter);
        }
        addColumn(pDatagrid, "context", "说明", ControlTypes.TEXT_BOX, 200);
        addColumn(pDatagrid,"url","数据源",ControlTypes.TEXT_BOX,100);
        column = addColumn(pDatagrid, "isInsert", "是否导入", ControlTypes.TEXT_BOX, 200);
        column.setFormatter("if (row.isInsert==1){return '已导入'} else{return '未导入'}");
        column = addColumn(pDatagrid,"currentStatus","是否为当前版本",ControlTypes.TEXT_BOX,100);
        column.setFormatter("if( row.currentStatus==1){ return '是' } else{ return '否' }");
        return pDatagrid;
    }

    @Override
    protected PForm createForm(ResourceNode node) {
        PForm form = new PForm(node, this.formPartName);
        form.setResourceNode(node);
        form.setName(this.meta.getName() + "表单");
        form.setColumnCount(2);//每行列数
        PFormField field = null;
        addFormField(form, "code", "编号", null, ControlTypes.TEXT_BOX, true,true).setWidth(200);
        addFormField(form, "context", "说明", null, ControlTypes.TEXTAREA, true,false).setWidth(200);
        addFormField(form, "url", "数据源", null, ControlTypes.OSS_UPLOAD, true,false).setWidth(200);
        addFormField(form,"currentStatus","是否为当前版本",null, ControlTypes.SWITCH_BUTTON,true,false).setWidth(100);
        return form;
    }

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
            addFormField(form, "code", "编号", groupName, ControlTypes.TEXT_BOX, true, true);
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
        addQueryItem(queryProject, "currentStatus", "是否为当前版本", ControlTypes.TEXT_BOX);
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
