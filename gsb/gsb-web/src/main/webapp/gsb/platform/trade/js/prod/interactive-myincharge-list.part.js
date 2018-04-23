System.Declare("com.gongsibao.trade.web.interactive");
com.gongsibao.trade.web.interactive.MyInChargeListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
    },
    onload: function () {
        var me = this;
        $("#inChargeStatus").combobox("setValue", 3141);
        controllerorderProdList.query();
        me.bindKeyupEvent();
    },
    detail: function (id) {
        var url = '/nav/gsb/platform/trade/orderProdDetail?id=' + id;
        window.open(url);
    },
    addFollowUp: function () {
        var me = this;
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }
        var row = this.getSelectedItem();
        var orderProdId = row.id;
        PandaHelper.openDynamicForm({
            title: "跟进",
            width: 450,
            height: 300,
            items: [
                {
                    id: 'followContent',
                    title: '跟进内容',
                    type: 'textarea',
                    height: 100,
                    width: 300,
                    className: 'easyui-validatebox',
                    option: {required: true, validType: ['maxLength[500]']}
                }
            ],
            callback: function (index, layero) {

                var followContent = $("#followContent").val();

                if (System.isnull(followContent)) {
                    IMessageBox.info('请输入跟进内容');
                    return false;
                }

                me.invokeService("addFollowUp", [orderProdId, followContent], function (data) {
                    me.reload();
                    IMessageBox.toast('操作成功');
                    layer.closeAll();
                    return;
                });
            }
        });
    },
    contactFormatter: function (value, row, index, typeName) {
        if (value) {
            var ctrl = workspace.parts.byIndex(0).key;
            return '<sapn>' + PandaHelper.dimString(value) + '</span><i class="fa fa-eye" onclick="' + ctrl + '.showPlaintext(\'' + row.customerId + '\',\'' + value + '\',\'' + typeName + '\',this);"></i>';
        }
    },
    showPlaintext: function (customerId, value, typeName, obj) {
        $(obj).parent().text(value);
    }
});

