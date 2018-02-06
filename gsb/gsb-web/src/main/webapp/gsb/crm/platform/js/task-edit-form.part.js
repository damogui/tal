com.gongsibao.crm.web.NCustomerTaskEditFormPart = com.gongsibao.crm.web.NCustomerTaskAddFormPart.Extends( {

    ctor: function () {
        this.base();
    },
    databindafter:function(){
    	
        $('.easyui-combobox,.easyui-combogrid').combobox("initClearBtn");
        $('.easyui-filebox').filebox("initClearBtn");
        
        var entity = this.viewModel.currentItem;
        if(entity != null && (entity.allocationState == 2 ||entity.allocationState == 4 || entity.allocationState == 5)){
        	
//        	WAIT(1, "待分配"),
//        	ALLOCATED(2, "已分配-业务员"),
//        	NOALLOCATED(3, "不分配"),
//        	ALLOCATED_Supplier(4, "已分配-服务商"),
//        	ALLOCATED_Department(5, "已分配-部门");
        	this.disable();
        	$('#remark').prop('disabled',false);
        	$('#smsRemark').prop('disabled',false);

        	//$('#allocationType').combobox('disable');
        	$('#controllernCustomerTasksave').linkbutton('enable');
        }
    }
});
com.gongsibao.crm.web.PlatformTaskFollowDetailPart = com.gongsibao.crm.web.TaskFollowDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.addFollowUrl = '/panda/crm/platform/task/follow';
    }
});
