System.Declare("com.gongsibao.cw.web");
//所有订单
com.gongsibao.cw.web.LoansBillListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor: function () {
	   this.base();
	},
	applyLoan:function (){ //申请借款
		layer.open({
			id: "loanBillIframe",
			type: 2,
			title: '新建借款单',
			fixed: false,
			maxmin: true,
			shadeClose:true,
			area: ['60%','90%'],
			content: "/panda/cw/bill/loan/form",
			btn : [ '提交', '取消' ],
			success: function (layero, index) {
				
			},
			yes:function (){
				document.getElementById('loanBillIframe').firstElementChild.contentWindow.controllerloan.save();
			}
		});
	},
	checkLoan : function(){
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