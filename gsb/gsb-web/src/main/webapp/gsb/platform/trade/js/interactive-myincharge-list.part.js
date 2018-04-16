System.Declare("com.gongsibao.trade.web.interactive");
com.gongsibao.trade.web.interactive.MyInChargeListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
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
                    IMessageBox.toast(zyStr + '成功');
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
    }
});



