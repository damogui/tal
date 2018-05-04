package com.gongsibao.panda.supplier.igirl.workspace.ic.common;

import com.gongsibao.entity.igirl.ic.baseinfo.EntLicType;
import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.*;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * @Description: java类作用描述
 * @UpdateDate: 2018/4/12 14:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EntLicTypeWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {

        super.setup();
        urlList = "/igirl/ic/entlictype/all/list";
        urlForm = "/igirl/ic/entlictype/form";

        entity = EntLicType.class;
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "IGRIL_IC_BASE_EntLicType";
        formPartName = listPartName = meta.getName();
        formOpenMode = OpenMode.WINDOW;
        openWindowWidth = 800;
        openWindowHeight = 600;
        listToolbarPath="/igirl/chapterone/list";
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
           datagrid.setToolbar(listToolbarPath);
            datagrid.setName("证照类型");
            datagrid.setOrderby("code");
        }
        PDatagridColumn column = null;
        column=addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "name", "执照名称", ControlTypes.TEXT_BOX, 200);
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
        addFormField(form, "name", "执照名称", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);

        return form;
    }


    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        addQueryItem(queryProject, "name", "执照名称", ControlTypes.TEXT_BOX);
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
