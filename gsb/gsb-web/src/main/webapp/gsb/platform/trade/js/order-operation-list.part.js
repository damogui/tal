System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderOperationController = org.netsharp.panda.commerce.ListPart.Extends({

    ctor: function () {
        this.base();
    },
    batchTransferWeb: function (id) {
        var me = this;

        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }

        var builder = new System.StringBuilder();
        builder.append('<div style="margin:10px;">');
        builder.append('	<table cellpadding="5" cellspacing="10" class="query-panel">');
        builder.append(' 		<tr><td class="title">业务员</td><td><input id="ywyUser"/></td></tr>');
        builder.append('	</table>');
        builder.append('</div>');

        layer.open({
            type: 1,
            title: '批量转移',
            fixed: false,
            maxmin: false,
            shadeClose: false,
            area: ['500px', '300px'],
            zIndex: 100000,
            content: builder.toString(),
            btn: ['保存', '取消'],// 可以无限个按钮
            success: function (layero, index) {

                // 业务员
                var options = {
                    columns: [[{
                        field: 'name',
                        title: '姓名',
                        width: 100
                    }]],
                    url: '\/panda\/rest\/reference?code=Gsb_User&filter=',
                    idField: 'id',
                    textField: 'name',
                    width: 300,
                    fitColumns: true,
                    panelWidth: 450,
                    panelHeight: 310,
                    pagination: true,
                    pageSize: 10,
                    mode: 'remote',
                    multiple: false
                };
                $('#ywyUser').combogrid(options);

            },
            yes: function (index, layero) {

                // 业务员id
                var ywyUserId = $('#ywyUser').combogrid('getValue');
                // var rows = this.getSelections();

                if (System.isnull(ywyUserId)) {
                    IMessageBox.info('请选择业务员');
                    return false;
                }

                var orderIdList = [];
                $.each(rows, function (k, v) {
                    orderIdList.push(v.id);
                });

                me.invokeService("BatchTransferSalesman", [ywyUserId, orderIdList], function () {
                    me.reload();
                    IMessageBox.toast('转移成功');
                    layer.closeAll();
                    window.location.reload();
                    return;
                });

                // me.doBatchTransferWeb(ywyUserId, orderIdList);
            },
            /*
             * doBatchTransferWeb : function(ywyUserId, orderIdList) { var me =
             * this; this.invokeService("BatchTransferSalesman", [ ywyUserId,
             * orderIdList ], function() { me.reload();
             * IMessageBox.toast('转移成功'); layer.closeAll(); return; }); },
             */
            btn2: function (index, layero) {
            }
        });
    }
});