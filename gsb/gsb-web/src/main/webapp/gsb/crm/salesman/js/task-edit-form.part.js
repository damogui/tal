com.gongsibao.crm.web.SalesmanTaskFollowDetailPart = com.gongsibao.crm.web.TaskFollowDetailPart.Extends( {
    ctor: function () {
        this.base();
    },
    add:function(){
    	
    	var url = '/panda/crm/task/followUp/from';
    	IMessageBox.open("任务跟进",url,700,450,function(){
    		
    	});
    },
	doubleClickRow : function(rowIndex, rowData) {
		
    	var url = '/panda/crm/task/followUp/from?id='+rowData.id;
    	IMessageBox.open("任务跟进",url,700,450,function(){
    		
    	});
	},
});
