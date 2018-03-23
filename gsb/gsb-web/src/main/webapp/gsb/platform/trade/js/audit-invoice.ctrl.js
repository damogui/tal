System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditInvoiceListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {

        this.base();
        //this.service = 'com.gongsibao.trade.web.audit.AuditInvoiceController';
        //订单下单方式枚举
        this.auditLogStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.bd.dic.AuditLogStatusType');
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
            IMessageBox.error("该审核记录的状态不是【" + me.auditLogStatusTypeEnum[1051] + "】，禁止审核");
            return;
        }
    }
});
