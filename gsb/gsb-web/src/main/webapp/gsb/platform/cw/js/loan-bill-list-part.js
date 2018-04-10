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
	updateLoan : function(){
		
		var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择借款单据');
            return false;
        }
        var row = this.getSelectedItem();
        if(row.status != "被驳回"){
        	IMessageBox.info('单据被驳回才可以修改');
            return false;
        }
		var loanUrl = "/panda/cw/bill/loan/form?id=" + row.id;
		layer.open({
   		    id: "updateBillIframe",
            type: 2,
            title: '修改借款单',
            fixed: false,
            maxmin: true,
            shadeClose:true,
            area: ['60%','90%'],
            content: loanUrl,
            btn : [ '提交', '取消' ],
            success: function (layero, index) {
           	
            },
            yes:function (){
            	document.getElementById('updateBillIframe').firstElementChild.contentWindow.controllerloan.save();
            }
        });
	}
});