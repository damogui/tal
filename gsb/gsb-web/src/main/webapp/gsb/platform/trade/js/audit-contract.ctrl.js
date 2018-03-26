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
        
        var serviceLocator = new org.netsharp.core.JServiceLocator();
        var url = '/panda/trade/order/contract/form?id=' + row.formId;
        //增加订单是否创建合同

        layer.open({
            id: "contractCreateIframe",
            type: 2,
            title: '合同信息',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['60%', '90%'],
            content: url,
            btn: ['提交', '取消'],
            success: function (layero, index) {

            },
            yes: function () {
                //document.getElementById('contractCreateIframe').firstElementChild.contentWindow.controllercontract.save();
            }
        });

        /*serviceLocator.invoke("com.gongsibao.trade.web.OrderAllListPart", "checkContract", [row.id], function (data) {

         if (data) {
         IMessageBox.info('该订单已经创建合同');
         } else {


         }
         }, null, false);*/


    }
});
