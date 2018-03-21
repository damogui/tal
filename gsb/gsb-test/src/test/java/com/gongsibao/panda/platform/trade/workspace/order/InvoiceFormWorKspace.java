package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.trade.web.InvoiceFormPart;

public class InvoiceFormWorKspace extends WorkspaceCreationBase {

    @Before
    @Override
    public void setup() {
        entity = Invoice.class;
        urlForm = "/trade/order/invoice/form";
        listPartName = formPartName = "发票信息";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "Operation_Order_Invoice";

        formJsImport = "/gsb/platform/trade/js/invoice-add-form.part.js|/panda-res/js/panda.form.js";
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
        addFormField(form, "sourceType", "订单来源", ControlTypes.LABEL, false, true);
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

		addFormField(form, "vatAddress", "开票公司注册地址", groupName,  ControlTypes.TEXT_BOX,true, true);
		addFormField(form, "vatPhone", "开票公司注册电话", groupName,  ControlTypes.TEXT_BOX,true, true);
		addFormField(form, "vatBankName", "开户银行名称", groupName,  ControlTypes.TEXT_BOX,true, true);
		addFormField(form, "vatBankNo", "开户银行账号", groupName,  ControlTypes.TEXT_BOX,true, true);

        return form;
    }
}
