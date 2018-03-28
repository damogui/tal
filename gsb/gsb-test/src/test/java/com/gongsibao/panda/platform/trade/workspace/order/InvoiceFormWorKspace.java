package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.trade.web.InvoiceFormPart;
import org.netsharp.util.ReflectManager;

public class InvoiceFormWorKspace extends WorkspaceCreationBase {

    @Before
    @Override
    public void setup() {
        entity = Invoice.class;
        urlForm = "/trade/order/invoice/form";
        listPartName = formPartName = "发票信息";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "Operation_Order_Invoice";

        formJsImport = "/gsb/platform/trade/js/invoice-add-form.part.js|/package/qiniu/plupload.full.min.js";
        formServiceController = InvoiceFormPart.class.getName();
        formJsController = InvoiceFormPart.class.getName();
        formToolbarPath = "";
    }


    protected PForm createForm(ResourceNode node) {
        PForm form = super.createForm(node);
        form.setLabelWidth(150);
        String groupName = "";
        PFormField formField = null;
        addFormField(form, "soOrderNo", "订单编号", ControlTypes.LABEL, false, true);
        addFormField(form, "payablePrice", "订单金额", ControlTypes.LABEL, false, true);
        addFormField(form, "paidPrice", "已付金额", ControlTypes.LABEL, false, true);
        addFormField(form, "customerName", "客户姓名", ControlTypes.LABEL, false, true);
        addFormField(form, "accountMobile", "客户电话", ControlTypes.LABEL, false, true);
        addFormField(form, "createTime", "下单时间", ControlTypes.LABEL, false, true);
        addFormField(form, "platformSource", "订单来源", ControlTypes.LABEL, false, true);
        addFormField(form, "payStatus", "付款状态", ControlTypes.LABEL, false, true);
        addFormField(form, "stageNum", "分期次数", ControlTypes.LABEL, false, true);
        addFormField(form, "channelOrderNo", "渠道订单号", ControlTypes.LABEL, false, true);
        formField = addFormField(form, "remark", "备注", ControlTypes.LABEL, false, true);
        {
            formField.setColumnSpan(3);
            formField.setWidth(500);
        }
        groupName = "发票信息";
        addFormField(form, "title", "发票抬头", groupName, ControlTypes.TEXT_BOX, true, false);
        addFormField(form, "titleType", "抬头类型", groupName, ControlTypes.ENUM_BOX, true, false);
        addFormField(form, "companyId", "开票公司", groupName, ControlTypes.ENUM_BOX, true, false);
        formField = addFormField(form, "typeId", "发票类型", groupName, ControlTypes.ENUM_BOX, true, false);
        {
            formField.setTroikaTrigger("controllerinvoice.changeInvoiceType(this);");  //增值税发票
        }
        formField = addFormField(form, "amount", "发票金额", groupName, ControlTypes.DECIMAL_FEN_BOX, true, false);
        {
            formField.setTroikaTrigger("controllerinvoice.checkAmount(this);");  //验证发票金额
        }
        formField = addFormField(form, "content", "发票内容", groupName, ControlTypes.TEXTAREA, true, false);
        {
            formField.setFullColumn(true);
        }
        addFormField(form, "vatTaxNo", "开票公司税号", groupName, ControlTypes.TEXT_BOX, true, false);
        addFormField(form, "receiverName", "发票接收人", groupName, ControlTypes.TEXT_BOX, true, false);
        addFormField(form, "receiverMobilePhone", "手机", groupName, ControlTypes.TEXT_BOX, true, false);
        addFormField(form, "receiverEmail", "邮箱", groupName, ControlTypes.TEXT_BOX, true, false);
        addFormField(form, "receiverAddress", "邮寄地址", groupName, ControlTypes.TEXT_BOX, true, false);

        addFormField(form, "vatAddress", "开票公司注册地址", groupName, ControlTypes.TEXT_BOX, true, true);
        addFormField(form, "vatPhone", "开票公司注册电话", groupName, ControlTypes.TEXT_BOX, true, true);
        addFormField(form, "vatBankName", "开户银行名称", groupName, ControlTypes.TEXT_BOX, true, true);
        addFormField(form, "vatBankNo", "开户银行账号", groupName, ControlTypes.TEXT_BOX, true, true);

        return form;
    }

    protected void addDetailGridPart(PWorkspace workspace) {
        createOrderFileListPart(workspace);
    }

    @Test
    public void createDetailRowToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath("invoice/file/toolbar");
            toolbar.setName("发票附件上传");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }
        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("upload");
            item.setIcon("fa fa-cloud-upload");
            item.setName("上传");
            item.setSeq(1);
            toolbar.getItems().add(item);
        }

        toolbarService.save(toolbar);
    }

    private void createOrderFileListPart(PWorkspace workspace) {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);//"Operation_Order_Invoice_File"
        PDatagrid datagrid = new PDatagrid(node, "发票附件");
        {
            datagrid.setReadOnly(true);
            datagrid.setResourceNode(node);
            datagrid.setShowCheckbox(false);
            PDatagridColumn column = null;
            column = addColumn(datagrid, "url", "操作", ControlTypes.TEXT_BOX, 80);
            {
                column.setFormatter("return controllerfiles.urlFormatter(value,row,index);");
            }
            addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
        }
        PPart part = new PPart();
        {
            part.toNew();
            part.setName("发票附件");
            part.setCode("files");
            part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
            part.setRelationRole("files");
            part.setResourceNode(node);
            part.setPartTypeId(PartType.DETAIL_PART.getId());
            part.setDatagrid(datagrid);
            part.setToolbar("invoice/file/toolbar");
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setJsController("com.gongsibao.trade.web.OrderInvoiceFileDetailPart");
        }
        workspace.getParts().add(part);
    }

}
