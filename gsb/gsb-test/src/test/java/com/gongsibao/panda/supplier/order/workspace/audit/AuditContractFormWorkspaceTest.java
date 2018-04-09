package com.gongsibao.panda.supplier.order.workspace.audit;

import com.gongsibao.panda.platform.trade.workspace.order.ContractFormWorkspaceTest;
import com.gongsibao.trade.web.AuditLogDetailPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchao on 2018/3/26.
 */
public class AuditContractFormWorkspaceTest extends ContractFormWorkspaceTest {

    //合同审核界面
    @Before
    public void setup() {
        super.setup();
        urlForm = "/trade/audit/contract/form";
        resourceNodeCode = "GSB_Trade_Audit_Contract_Form";
        fileToolBarPath = "";
    }

    protected PForm createForm(ResourceNode node) {
        PForm form = super.createForm(node);
        List<PFormField> fieldList = form.getFields();
        for (PFormField field : fieldList) {
            if (!getNotToLab().contains(field.getControlType())) {
                field.setControlType(ControlTypes.LABEL);
            }
            field.setRequired(false);
        }
        return form;
    }

    @Override
    public void createDetailRowToolbar() {
    }


    private List<ControlTypes> getNotToLab() {
        List<ControlTypes> controlTypesList = new ArrayList();
        controlTypesList.add(ControlTypes.ENUM_BOX);
        controlTypesList.add(ControlTypes.DECIMAL_FEN_BOX);
        controlTypesList.add(ControlTypes.SWITCH_BUTTON);
        return controlTypesList;
    }

    protected void addDetailGridPart(PWorkspace workspace) {
        super.addDetailGridPart(workspace);
        createAuditListPart(workspace);
    }

    private void createAuditListPart(PWorkspace workspace) {
        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PDatagrid datagrid = new PDatagrid(node, "审批进度");
        {
            datagrid.setReadOnly(true);
            datagrid.setResourceNode(node);
            datagrid.setShowCheckbox(false);
            PDatagridColumn column = null;
            addColumn(datagrid, "employee.name", "审批人", ControlTypes.TEXT_BOX, 100);
            column = addColumn(datagrid, "status", "状态", ControlTypes.TEXT_BOX, 100);
            {
                column.setFormatter("return controllerauditLogs.auditNameFormatter(value,row,index);");
            }
            addColumn(datagrid, "content", "审批说明", ControlTypes.TEXT_BOX, 150);
            addColumn(datagrid, "remark", "审批内容", ControlTypes.TEXT_BOX, 150);
            addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 100);
        }
        PPart part = new PPart();
        {
            part.toNew();
            part.setName("审批进度");
            part.setCode("auditLogs");
            part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
            part.setRelationRole("auditLogs");
            part.setResourceNode(node);
            part.setPartTypeId(PartType.DETAIL_PART.getId());
            part.setDatagrid(datagrid);
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setJsController(AuditLogDetailPart.class.getName());

        }
        workspace.getParts().add(part);
    }

}
