com.gongsibao.crm.web.NCustomerTaskEditFormPart = com.gongsibao.crm.web.NCustomerTaskAddFormPart.Extends( {

    ctor: function () {
        this.base();
    }
});

com.gongsibao.crm.web.TaskFollowDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
    ctor: function () {
        this.base();
        this.addFollowUrl = null;
    },
    add:function(){
    	
    	var me = this;
    	var entity = this.parent.viewModel.currentItem;
		var taskId = entity.id;
		var customerId = entity.customerId;
		var taskFollowCtrl = new com.gongsibao.crm.web.TaskFollowCtrl();
		taskFollowCtrl.open(taskId,customerId,function(index, layero){
			
			debugger;
			me.parent.byId(taskId);
		});
    },
	doubleClickRow : function(rowIndex, rowData) {
		
		//FollowBox.info(rowData.id);
	},
});
