com.gongsibao.crm.web.NCustomerTaskEditFormPart = com.gongsibao.crm.web.NCustomerTaskAddFormPart.Extends( {

    ctor: function () {
        this.base();
    },
    databindafter:function(){
    	
        $('.easyui-combobox,.easyui-combogrid').combobox("initClearBtn");
        $('.easyui-filebox').filebox("initClearBtn");
        
        var entity = this.viewModel.currentItem;
        if(entity != null && entity.allocationState == 2){
//        	WAIT(1, "待分配"),
//        	ALLOCATED(2, "已分配"),
//        	NOALLOCATED(3, "不分配");
        	this.disable();
        	$('#remark').prop('disabled',false);
        	$('#smsRemark').prop('disabled',false);
        	
        	$('#controllernCustomerTasksave').linkbutton('enable');
        }
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
		taskFollowCtrl.type='form';
		taskFollowCtrl.open(taskId,customerId,function(index, layero){
			
			me.parent.byId(taskId);
		});
    },
	doubleClickRow : function(rowIndex, rowData) {
		
		//FollowBox.info(rowData.id);
	},
});
