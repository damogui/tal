package com.gongsibao.panda.supplier.settle.workspace;

import com.gongsibao.entity.igirl.settle.Settle;
import com.gongsibao.igirl.settle.web.SettleFormPart;
import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;

public class SettleFormWorkspaceTest extends WorkspaceCreationBase {

    @Override
    @Before
    public void setup() {
        entity = Settle.class;
        urlForm = "/igirl/settle/settleOrder/form";
        listPartName = formPartName = "结算单信息";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "Gsb_Supplier_Settle_form";
        formServiceController = SettleFormPart.class.getName();
        formJsController = SettleFormPart.class.getName();
        formToolbarPath = "";
    }

    @Override
    protected PForm createForm(ResourceNode node) {
        PForm form = super.createForm(node);
        String groupName = "基本信息";
        {
            addFormField(form, "id", "结算编号", groupName, ControlTypes.TEXT_BOX, false, true);
            addFormField(form, "handleStatus", "结算状态", groupName, ControlTypes.ENUM_BOX, false, true);
            addFormField(form, "totalAmount", "总金额", groupName, ControlTypes.DECIMAL_BOX, false, true);
            addFormField(form, "totalCost", "成本", groupName, ControlTypes.DECIMAL_BOX, false, true);
            addFormField(form, "totalCharge", "服务费", groupName, ControlTypes.DECIMAL_BOX, false, true);
            addFormField(form, "commission", "佣金", groupName, ControlTypes.DECIMAL_BOX, false, true);
            addFormField(form, "tax", "税额", groupName, ControlTypes.DECIMAL_BOX, false, true);
            addFormField(form, "taxRate", "税率%", groupName, ControlTypes.DECIMAL_BOX, false, true);
            addFormField(form, "createTime", "创建时间", groupName, ControlTypes.DATE_BOX, false, true);
            addFormField(form, "memo", "备注", groupName, ControlTypes.DECIMAL_BOX, false, true);
        }
        return form;
    }

    @Override
    protected void addDetailGridPart(PWorkspace workspace) {
//        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
//
//        PDatagrid datagrid = new PDatagrid(node, "订单明细");
//        {
//            // 订单明细
//            datagrid.setReadOnly(true);
//            datagrid.setResourceNode(node);
//            datagrid.setShowCheckbox(false);
//
//            addColumn(datagrid, "soOrder.no", "订单号", ControlTypes.TEXT_BOX, 120);
//            addColumn(datagrid, "orderProdId", "订单明细编号", ControlTypes.TEXT_BOX, 120);
//            addColumn(datagrid, "orderProd.productName", "产品名称", ControlTypes.TEXT_BOX, 150);
//            addColumn(datagrid, "orderProd.price", "订单明细价格", ControlTypes.DECIMAL_FEN_BOX, 100);
//            addColumn(datagrid, "cost", "成本", ControlTypes.DECIMAL_BOX, 100);
//            addColumn(datagrid, "charge", "服务费", ControlTypes.DECIMAL_BOX, 100);
//            addColumn(datagrid, "commission", "佣金", ControlTypes.DECIMAL_BOX, 100);
//        }
//
//        PPart part = new PPart();
//        {
//            part.toNew();
//            part.setName("订单信息");
//            part.setCode("settleOrderList");
//            part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
//            part.setRelationRole("settleOrderList");
//            part.setResourceNode(node);
//            part.setPartTypeId(PartType.DETAIL_PART.getId());
//            part.setDatagrid(datagrid);
//            part.setDockStyle(DockType.DOCUMENTHOST);
//            part.setServiceController(SettleFormPart.class.getName());
//            part.setWindowWidth(800);
//            part.setWindowHeight(600);
//            part.setOpenMode(OpenMode.WINDOW);
//        }
//        workspace.getParts().add(part);
    }
}
