package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.trade.web.ContractFormPart;
import com.gongsibao.trade.web.OrderProdItemDetailPart;

public class ContractFormWorkspaceTest extends WorkspaceCreationBase {

    @Before
    @Override
    public void setup() {
        entity = Contract.class;
        urlForm = "/trade/order/contract/form";
        listPartName = formPartName = "基本信息";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "Operation_Order_Contract";

        formJsImport = "/gsb/platform/trade/js/contract-add-form.part.js|/panda-res/js/panda.form.js";
        formServiceController = ContractFormPart.class.getName();
        formJsController = ContractFormPart.class.getName();
        formToolbarPath = "";

    }

    @Test
    public void run() {
        createFormWorkspace();
    }

    protected PForm createForm(ResourceNode node) {

        PForm form = super.createForm(node);
        String groupName = "";
        PFormField formField = null;
        addFormField(form, "soOrder.no", "订单编号", ControlTypes.LABEL, false, true);
        addFormField(form, "soOrder.channelOrderNo", "渠道编号", ControlTypes.LABEL, false, true);
        addFormField(form, "soOrder.platformSource", "合同来源", ControlTypes.LABEL, false, true);
        addFormField(form, "soOrder.customer.realName", "客户姓名", ControlTypes.LABEL, false, true);
        addFormField(form, "soOrder.accountMobile", "联系人手机", ControlTypes.LABEL, false, true);
        addFormField(form, "soOrder.customer.email", "邮箱", ControlTypes.LABEL, false, true);
        addFormField(form, "soOrder.owner.name", "签单业务员", ControlTypes.LABEL, false, true);
        addFormField(form, "soOrder.department.name", "所在部门", ControlTypes.LABEL, false, true);


        addFormField(form, "companyName", "公司名称", ControlTypes.TEXT_BOX, true, false);
        formField = addFormField(form, "electronics", "合同类型", ControlTypes.ENUM_BOX, false, false);
        {
            formField.setTroikaTrigger("controllercontract.contractTypeChange(this);");
        }
        formField = addFormField(form, "contractType", "客户类型", ControlTypes.ENUM_BOX, false, false);
        {
            formField.setTroikaTrigger("controllercontract.customerTypeChange(this);");
        }
        addFormField(form, "licenseNo", "营业执照号", ControlTypes.TEXT_BOX, true, false);
        addFormField(form, "idNumber", "身份证号", ControlTypes.TEXT_BOX, true, false);
        addFormField(form, "contractTitle", "合同标题", ControlTypes.TEXT_BOX, true, false);
        addFormField(form, "sginingTime", "签约日期", ControlTypes.DATE_BOX, true, false);
        addFormField(form, "urgeney", "是否加急", ControlTypes.SWITCH_BUTTON, false, false);
        addFormField(form, "sginingCompanyId", "我方签约公司", ControlTypes.ENUM_BOX, false, false);


        groupName = "合同款项";
        addFormField(form, "soOrder.payablePrice", "订单金额", groupName, ControlTypes.LABEL, false, true);
        addFormField(form, "realAmount", "合同总额", groupName, ControlTypes.DECIMAL_FEN_BOX, true, false);
        groupName = "撰写材料费";
        formField = addFormField(form, "hasDataFee", "有/无材料费", groupName, ControlTypes.ENUM_BOX, false, false);
        {
            formField.setTroikaTrigger("controllercontract.hasDataFeeChange(this);");
        }
        addFormField(form, "dataFeeCountTypeId", "撰写次数", groupName, ControlTypes.ENUM_BOX, false, false);
        addFormField(form, "dataFee", "撰写费", groupName, ControlTypes.DECIMAL_FEN_BOX, false, false);
        groupName = "违约责任";
        formField = addFormField(form, "hasLiquidatedDamages", "有/无违约金", groupName, ControlTypes.ENUM_BOX, false, false);
        {
            formField.setTroikaTrigger("controllercontract.hasLiquidatedDamagesChange(this);");
        }
        formField = addFormField(form, "liquidatedDamages", "违约金", groupName, ControlTypes.DECIMAL_FEN_BOX, false, false);
        formField = addFormField(form, "hasBreach", "有/无违约事项", groupName, ControlTypes.ENUM_BOX, false, false);
        {
            formField.setTroikaTrigger("controllercontract.hasBreachChange(this);");
        }
        formField = addFormField(form, "breachInfo", "违约责任", groupName, ControlTypes.TEXT_BOX, false, false);
        groupName = "其他说明";
        addFormField(form, "fileId", "附件", groupName, ControlTypes.FILE_BOX, false, false);
        formField = addFormField(form, "remark", "备注", groupName, ControlTypes.TEXTAREA, false, false);
        formField.setFullColumn(false);
        formField.setWidth(480);
        return form;
    }

    protected void addDetailGridPart(PWorkspace workspace) {
        createOrderProdItemListPart(workspace);
    }

    private void createOrderProdItemListPart(PWorkspace workspace) {
        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PDatagrid datagrid = new PDatagrid(node, "合同内容");
        {
            datagrid.setReadOnly(true);
            datagrid.setResourceNode(node);
            datagrid.setShowCheckbox(false);
            datagrid.setShowTitle(true);
            PDatagridColumn column = null;
            addColumn(datagrid, "productName", "产品名称", ControlTypes.TEXT_BOX, 200);
            addColumn(datagrid, "quantity", "数量", ControlTypes.TEXT_BOX, 100);
            addColumn(datagrid, "cityName", "服务地区", ControlTypes.TEXT_BOX, 150);
            column = addColumn(datagrid, "serviceName", "服务名称", ControlTypes.TEXT_BOX, 150);
            {
                column.setFormatter("return controllerproducts.serviceNameFormatter(value,row,index);");
            }
            column = addColumn(datagrid, "unitName", "单位", ControlTypes.TEXT_BOX, 150);
            {
                column.setAlign(DatagridAlign.CENTER);
                column.setFormatter("return controllerproducts.unitNameFormatter(value,row,index);");
            }
            addColumn(datagrid, "priceOriginal", "原售价", ControlTypes.DECIMAL_FEN_BOX, 100);
            addColumn(datagrid, "price", "现售价", ControlTypes.DECIMAL_FEN_BOX, 100);
        }
        PPart part = new PPart();
        {
            part.toNew();
            part.setName("订单产品明细");
            part.setCode("products");
            part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
            part.setRelationRole("products");
            part.setResourceNode(node);
            part.setPartTypeId(PartType.DETAIL_PART.getId());
            part.setDatagrid(datagrid);
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setJsController(OrderProdItemDetailPart.class.getName());

        }
        workspace.getParts().add(part);
        PPart topPart = workspace.getParts().get(0);
        topPart.setDockStyle(DockType.TOP);
        topPart.setStyle("height:500px");
    }
}
