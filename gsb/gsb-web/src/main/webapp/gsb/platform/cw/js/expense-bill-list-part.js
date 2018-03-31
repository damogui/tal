System.Declare("com.gongsibao.cw.web");
//申请报销单
com.gongsibao.cw.web.ExpenseBillListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor: function () {
	   this.base();
	},
	applyExpense:function (){ 
		 layer.open({
    		 id: "expenseBillIframe",
             type: 2,
             title: '新建借款单',
             fixed: false,
             maxmin: true,
             shadeClose:true,
             area: ['60%','90%'],
             content: "/panda/cw/bill/expense/form",
             btn : [ '提交', '取消' ],
             success: function (layero, index) {
            	
             },
             yes:function (){
            	 document.getElementById('expenseBillIframe').firstElementChild.contentWindow.controllerexpense.save();
             }
         });
	}
});