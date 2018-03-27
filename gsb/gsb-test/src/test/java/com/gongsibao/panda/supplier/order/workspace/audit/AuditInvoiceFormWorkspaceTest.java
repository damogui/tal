package com.gongsibao.panda.supplier.order.workspace.audit;

import com.gongsibao.panda.platform.trade.workspace.order.InvoiceFormWorKspace;
import com.gongsibao.trade.web.OrderProdItemDetailPart;
import org.junit.Before;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

/**
 * Created by zhangchao on 2018/3/27.
 */
public class AuditInvoiceFormWorkspaceTest extends InvoiceFormWorKspace {

    //发票审核界面
    @Before
    public void setup() {
        super.setup();
        urlForm = "/trade/audit/invoice/form";
        resourceNodeCode = "GSB_Trade_Audit_Form_Invoice";
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
            //part.setJsController(OrderProdItemDetailPart.class.getName());

        }
        workspace.getParts().add(part);
    }

}