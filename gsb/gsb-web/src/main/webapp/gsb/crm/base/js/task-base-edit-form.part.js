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

    	IMessageBox.open("任务跟进",this.addFollowUrl,700,450,function(){
    		
    	});
    },
	doubleClickRow : function(rowIndex, rowData) {
		
    	var url = this.addFollowUrl+'?id='+rowData.id;
    	IMessageBox.open("任务跟进",url,700,450,function(){
    		
    	});
	},
});