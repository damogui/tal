System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditContractListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {

        this.base();
        //this.service = 'com.gongsibao.trade.web.audit.AuditContractController';
        //订单下单方式枚举
        this.auditLogStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.bd.dic.AuditLogStatusType');
    },
    initStyle: function () {

        $('#detail_tabs').tabs({
            fit: true,
            tabHeight: 35
        });
    },
    initData: function () {

    },
    addAudit: function (id) {//审核弹框
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

        IMessageBox.info('可以审批');


    }
});
