System.Declare("com.gongsibao.trade.web");
//所有订单
com.gongsibao.trade.web.SalesmanAllOrderListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor : function() {
        this.base();
        // this.addUrl = "/crm/order/salesman/all/form";
        // this.editUrl = "/crm/order/salesman/all/form";
        // this.followUrl = "/crm/order/salesman/all/form";
        //this.addOrderReceivedUrl ='/panda/crm/platform/customer/add';


    },
    addOrderReceived : function(id) {//创建订单业绩
        var me = this;

        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }

        var builder = new System.StringBuilder();
        builder.append('<div style="margin:10px;">');
        builder.append('	<table cellpadding="5" cellspacing="10" class="query-panel">');
        builder.append(' 		<tr><td class="title">创建订单业绩</td><td><input id="ywyUser"/></td></tr>');
        builder.append('	</table>');
        builder.append('</div>');

        layer.open({
            type : 1,
            title : '创建订单业绩',
            fixed : false,
            maxmin : false,
            shadeClose : false,
            area : [ '500px', '300px' ],
            zIndex : 100000,
            content : builder.toString(),
            btn : [ '保存', '取消' ],// 可以无限个按钮
            success : function(layero, index) {

                // 业务员
                var options = {
                    columns : [ [ {
                        field : 'name',
                        title : '姓名',
                        width : 100
                    } ] ],
                    url : '\/panda\/rest\/reference?code=Gsb_User&filter=',
                    idField : 'id',
                    textField : 'name',
                    width : 300,
                    fitColumns : true,
                    panelWidth : 450,
                    panelHeight : 310,
                    pagination : true,
                    pageSize : 10,
                    mode : 'remote',
                    multiple : false
                };
                $('#ywyUser').combogrid(options);

            },
            yes : function(index, layero) {

                IMessageBox.toast('创建成功');

                me.invokeService("BatchTransferSalesman", [ ywyUserId, orderIdList ], function() {
                    me.reload();
                    IMessageBox.toast('创建成功');
                    layer.closeAll();
                    return;
                });


            },

            btn2 : function(index, layero) {
            }
        });
    },
    addReceived:function(id){//创建回款业绩

        this.edit(id);
    },
    addRefund:function(id){//创建退款

        this.edit(id);
    },
    addCarryover:function(id){//创建结转

        this.edit(id);
    },
    addStaging:function(id){//创建分期

        this.edit(id);
    },
    addContract:function(id){//创建合同

        this.edit(id);
    },
    addInvoice:function(id){//申请发票

        this.edit(id);
    },
    batchOrderTran:function(id){//批量订单转移

        this.edit(id);
    },
    orderTran:function(id){//订单转移

        this.edit(id);
    },
    begOption:function(id){//开始操作

        this.edit(id);
    },
    detail:function(id){

        this.edit(id);
    },
    doubleClickRow : function(index, row) {
        this.edit(row.id);
    },
    // edit : function(id) {

    batchAllocation:function(){
        //任务批量分配
        var me = this;
        var row = this.getSelectedItem();
        var id = this.getSelectionIds();
        if(id == "" || id == null ){
            IMessageBox.info('请选择记录');
            return;
        }
        me.doAllot(id);
    },

    batchTransfer : function(){
        //任务批量转移
        var me = this;
        var id = this.getSelectionIds();
        if(id == "" || id == null ){
            IMessageBox.info('请选择记录');
            return;
        }
        me.doTransfer(id);
    },
    transfer : function(id){
        //任务转移
        var me = this;
        var row = this.getSelectedItem();
        if (row == null) {
            IMessageBox.info('请选择记录');
            return;
        }
        me.doTransfer(id);
    }


});



