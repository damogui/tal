System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditInvoiceListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {

        this.base();
        //this.service = 'com.gongsibao.trade.web.audit.AuditInvoiceController';
        //订单下单方式枚举
        this.auditLogStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.bd.dic.AuditLogStatusType');
        this.auditInvoiceUrl = '/panda/trade/audit/invoice/form';
    },
    initData: function () {

    },
    addAudit: function () {
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        var me = this;
        if (rows.length != 1) {
            IMessageBox.info('请先选择一条审核记录');
            return false;
        }

        if (me.auditLogStatusTypeEnum[1051] != row.status) {
            IMessageBox.info("该审核记录的状态不是【" + me.auditLogStatusTypeEnum[1051] + "】，禁止审核");
            return;
        }


        var url = me.auditInvoiceUrl + '?id=' + row.formId;
        //审核id
        var id = row.id;
        layer.open({
            id: "invoiceCreateIframe",
            type: 2,
            title: '发票信息',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['60%', '90%'],
            content: url,
            btn: ['审核通过', '审核驳回'],// 可以无限个按钮
            btn1: function (index, layero) {
                document.getElementById('invoiceCreateIframe').firstElementChild.contentWindow.controllerinvoice.approved(id, function (data) {
                    me.reload();
                });
            },
            btn2: function (index, layero) {
                document.getElementById('invoiceCreateIframe').firstElementChild.contentWindow.controllerinvoice.rejected(id, function (data) {
                    me.reload();
                });
                return false;
            }
        });
    },
    show: function (id) {
        var contentUrl = this.auditInvoiceUrl + "?id=" + id;
        layer.open({
            id: "invoiceCreateIframe",
            type: 2,//1是字符串 2是内容
            title: '发票信息',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['60%', '90%'],
            zIndex: 100000,
            content: contentUrl
        });
    },
    optionFormatter: function (value, row, index) {
        var me = this;
        if (me.auditLogStatusTypeEnum[1051] != row.status) {
            return "<a class='grid-btn' href='javascript:controllerauditLogList.show(" + value + ");'>查看</a>";
        } else {
            return "<a class='grid-btn' href='javascript:controllerauditLogList.addAudit(" + value + ");'>审核</a>";
        }
    }
});
