System.Declare("com.gongsibao.cw.web");
//付款单
com.gongsibao.cw.web.PaymentBillListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor: function () {
	   this.base();
	},
	applyPayment:function (){ //申请付款
		 layer.open({
    		 id: "paymentBillIframe",
             type: 2,
             title: '新建付款单',
             fixed: false,
             maxmin: true,
             shadeClose:true,
             area: ['60%','90%'],
             content: "/gsb/platform/cw/payment_bill_form.jsp",
             btn : [ '提交', '取消' ],
             success: function (layero, index) {
            	
             },
             yes:function (){
            	 document.getElementById('paymentBillIframe').firstElementChild.contentWindow.controllercontract.save();
             }
         });
	},
	checkPayment : function(){
		layer.open({
   		 id: "checkBillIframe",
            type: 2,
            title: '新建借款单',
            fixed: false,
            maxmin: true,
            shadeClose:true,
            area: ['60%','90%'],
            content: "/gsb/platform/cw/check_bill_form.jsp",
            btn : [ '提交', '取消' ],
            success: function (layero, index) {
           	
            },
            yes:function (){
           	 document.getElementById('checkBillIframe').firstElementChild.contentWindow.controllercontract.save();
            }
        });
	}
});