/*平台的所有订单*/
System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderAllListPart = com.gongsibao.trade.web.SalesmanAllOrderListPart.Extends({
    ctor: function () {
        this.base();
        this.originType = 1;//来源类型（0或null：业务员跳转过来的；1：平台跳转过来的）
    },
    addContract:function(){  //创建合同
    	 var me = this;
    	 var row = this.getSelectedItem();
         var rows = this.getSelections();
         if (rows.length <= 0) {
             IMessageBox.info('请先选择订单数据');
             return false;
         }
         //增加订单是否创建合同
         me.invokeService("checkContract", [row.id], function (data) {
        	 if (data) {
                 IMessageBox.info('该订单已经创建合同');
             }else{
            	 var url = '/panda/trade/order/contract/form?fk=orderId:'+row.id;
            	 layer.open({
            		 id: "contractCreateIframe",
                     type: 2,
                     title: '合同信息',
                     fixed: false,
                     maxmin: true,
                     shadeClose:true,
                     area: ['60%','90%'],
                     content: url,
                     btn : [ '提交', '取消' ],
                     success: function (layero, index) {
                    	
                     },
                     yes:function (){
                    	 document.getElementById('contractCreateIframe').firstElementChild.contentWindow.controllercontract.save();
                     }
                 });
             }
         });
    },
    addInvoice:function (){ //申请发票
    	var me = this;
   	 	var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }
        var url = '/panda/trade/order/invoice/form?fk=orderId:'+row.id;
        layer.open({
   		 	id: "invoiceCreateIframe",
            type: 2,
            title: '基本信息',
            fixed: false,
            maxmin: true,
            shadeClose:true,
            area: ['60%','90%'],
            content: url,
            btn : [ '提交', '取消' ],
            success: function (layero, index) {
           	
            },
            yes:function (){
           	 document.getElementById('invoiceCreateIframe').firstElementChild.contentWindow.controllercontract.save();
            }
        });
    }
});
