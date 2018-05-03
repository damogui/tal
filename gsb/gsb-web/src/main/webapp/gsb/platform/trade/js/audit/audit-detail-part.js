com.gongsibao.trade.web.AuditLogDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({
    ctor: function () {
        this.base();
        this.auditLogStatusType = PandaHelper.Enum.get('com.gongsibao.entity.bd.dic.AuditLogStatusType');
    },
    doubleClickRow: function () {
        //覆盖
    },
    auditNameFormatter: function (value, row, index) {
        var me = this;
        if (value) {
            return me.auditLogStatusType[value];
        }
    }
});