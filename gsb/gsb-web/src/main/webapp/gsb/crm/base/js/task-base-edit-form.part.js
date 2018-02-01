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
    	
    	var taskId = this.queryString("id");
    	FollowBox.info(taskId);
    	
    },
	doubleClickRow : function(rowIndex, rowData) {
		FollowBox.info(rowData.id);
	},
});
