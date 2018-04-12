package com.gongsibao.panda.supplier.igirl.workspace.ic.common;

import com.gongsibao.entity.igirl.ic.baseinfo.ChapterOne;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * @Description: 注册刻章公司登记区
 * @UpdateDate: 2018/4/12 11:54
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChapterOneWorkspaceTest extends WorkspaceCreationBase{

    @Before
    public void setup() {

        super.setup();
        urlList = "/igirl/ic/chapterone/all/list";
        urlForm = "/igirl/ic/chapterone/form";

        entity = ChapterOne.class;
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "IGRIL_IC_BASE_ChapterOne";
        formPartName = listPartName = meta.getName();
        formOpenMode = OpenMode.WINDOW;
        openWindowWidth = 800;
        openWindowHeight = 600;
        listToolbarPath="/igirl/chapterone/list";
    }

    @Test
    public void fromToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);

        OperationType ot1 = operationTypeService.byCode(OperationTypes.update);
        OperationType ot2 = operationTypeService.byCode(OperationTypes.add);

        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("工具栏");
            toolbar.setResourceNode(node);

        }

        PToolbarItem item = new PToolbarItem();
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

        PToolbarItem item1 = new PToolbarItem();
        {
            item1.toNew();
            item1.setCode("add");
            item1.setIcon("fa fa-plus");
            item1.setName("新增");
            item1.setCommand(null);
            item1.setOperationType(ot2);
            item1.setSeq(3000);
            item1.setCommand("{controller}.add();");
            toolbar.getItems().add(item1);
        }
        toolbarService.save(toolbar);
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setToolbar(listToolbarPath);
            datagrid.setName("刻章区");
            datagrid.setOrderby("code");
        }
        PDatagridColumn column = null;
        column=addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
        column=addColumn(datagrid, "name", "地区名称", ControlTypes.TEXT_BOX, 200);
        column=addColumn(datagrid, "firstCode",   "一级代码", ControlTypes.TEXT_BOX, 100);
        column=addColumn(datagrid, "secCode",   "二级代码", ControlTypes.TEXT_BOX, 100);
        column=addColumn(datagrid, "firstName",   "一级名称", ControlTypes.TEXT_BOX, 100);
        column=addColumn(datagrid, "secName",   "二级名称", ControlTypes.TEXT_BOX, 100);
        return datagrid;
    }

    //
    @Override
    protected PForm createForm(ResourceNode node) {

        PForm form = new PForm(node, this.formPartName);
        {
            form.setColumnCount(1);
        }

        PFormField field = null;
        addFormField(form, "code", "编码", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
        addFormField(form, "name", "地区名称", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
        addFormField(form, "firstCode", "一级代码", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
        addFormField(form, "secCode", "二级代码", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
        addFormField(form, "name", "一级名称", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
        addFormField(form, "name", "地区名称", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);

        return form;
    }


    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        addQueryItem(queryProject, "name", "地区名称", ControlTypes.TEXT_BOX);
        return queryProject;
    }

    @Override
    protected void doOperation() {

        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node,OperationTypes.view);
        operationService.addOperation(node,OperationTypes.add);
        operationService.addOperation(node,OperationTypes.update);
    }
}
