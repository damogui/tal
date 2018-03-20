System.Declare("com.gongsibao.trade.web");
//所有订单
com.gongsibao.trade.web.AuditPayListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();

        //this.addOrderReceivedUrl = '/panda/crm/order/salesman/coperformance';//创建订单业绩

    },

    orderNameFormatter:function(value,row,index){
        // debugger;
        // return "哈哈";
        //
        // var items = row.items;
        // if(items.length==1){
        //
        //     return items[0].serviceName;
        // }else{
        //
        //     var str = '';
        //     $(items).each(function(i,item){
        //
        //         str+='<p>'+item.serviceName+'</p>';
        //     });
        //     return str;
        // }
    },
    transfer: function (id) {
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



