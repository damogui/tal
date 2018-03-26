package com.gongsibao.panda.supplier.order.workspace.audit;

import com.gongsibao.panda.platform.trade.workspace.order.ContractFormWorkspaceTest;
import com.gongsibao.trade.web.OrderProdItemDetailPart;
import com.gongsibao.trade.web.department.DepartmentOrderAllListPart;
import org.junit.Before;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.*;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

/**
 * Created by zhangchao on 2018/3/26.
 */
public class AuditContractFormWorkspaceTest extends ContractFormWorkspaceTest {

    //合同审核界面
    @Before
    public void setup() {
        super.setup();
        urlForm = "/trade/audit/contract/form";
        resourceNodeCode = "GSB_Trade_Audit_Form_Contract";
    }

    protected PForm createForm(ResourceNode node) {
        PForm form = super.createForm(node);
        String groupName = "审批意见";
        PFormField formField = null;
        formField = addFormField(form, "auditRemark", "审批意见", groupName, ControlTypes.TEXTAREA, false, false);
        return form;
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
            addColumn(datagrid, "status", "状态", ControlTypes.ENUM_BOX, 100);
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
            part.setJsController(OrderProdItemDetailPart.class.getName());

        }
        workspace.getParts().add(part);
    }

}
