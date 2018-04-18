System.Declare("com.gongsibao.cw.web");
//申请报销单
com.gongsibao.cw.web.ExpenseBillListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor: function () {
	   this.base();
	},
	applyExpense:function (){ 
		var me = this;
		 layer.open({
    		 id: "expenseBillIframe",
             type: 2,
             title: '新建报销单',
             fixed: false,
             maxmin: true,
             shadeClose:true,
             area: ['90%','90%'],
             content: "/panda/cw/bill/expense/form",
             btn : [ '提交', '取消' ],
             success: function (layero, index) {
            	
             },
             yes:function (){
            	 document.getElementById('expenseBillIframe').firstElementChild.contentWindow.controllerexpense.save();
             }
         });
	},
	updateExpense : function (){
		var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择报销单据');
            return false;
        }
        var row = this.getSelectedItem();
        if(row.status != "被驳回"){
        	IMessageBox.info('单据被驳回才可以修改');
            return false;
        }
		var expenseUrl = "/panda/cw/bill/expense/form?id=" + row.id;
		layer.open({
   		 	id: "expenseBillIframe",
            type: 2,
            title: '新建报销单',
            fixed: false,
            maxmin: true,
            shadeClose:true,
            area: ['90%','90%'],
            content: expenseUrl,
            btn : [ '提交', '取消' ],
            success: function (layero, index) {
           	
            },
            yes:function (){
           	 document.getElementById('expenseBillIframe').firstElementChild.contentWindow.controllerexpense.save();
            }
        });
	}
});