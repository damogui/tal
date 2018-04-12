System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.SalesmanNCustomerAddFormPart =com.gongsibao.crm.web.NCustomerFormPart.Extends( {

    ctor: function () {
        this.base();
        this.isPlatform = 0;
        this.verifyUrl = '/panda/crm/salesman/customer/verify';
        this.addUrl='/panda/crm/salesman/customer/add';
        this.editUrl='/panda/crm/salesman/customer/edit';
    },
    getsaveState:function(){
    	
    	return UiElementState.Enable;
    },
    getverifyState:function(){
    	
    	var id = this.queryString("id");
    	if(id){
    	
    		return UiElementState.Hide;
    	}
    },
    onSaving: function (entity) {

    	if(entity.entityState == EntityState.New){
    		
    		if(entity.tasks && entity.tasks.length == 0){
    			
    			IMessageBox.error("保存失败：请添加商机！");
    			return false;
    		}
    		
    	}else{
    		
    		entity.tasks = [];
    	}
    	entity.products = [];
    	entity.follows = [];
    	entity.notifys = [];
    	entity.changes = [];
    	
    	$("#controllernCustomersave").linkbutton("disable");
    	//提高效率，将明细全部置空
        return true;
    },
    doSave: function (entity) {

        var me = this;
        this.invokeService("save", [entity], function (jmessage) {
            me.onSaved(jmessage);
        }, true,function () {
            $("#controllernCustomersave").linkbutton("enable");//不放开的话业务异常抛出来没法保存

        });
    },
    databindextra: function (entity) {

    	$("#controllernCustomersave").linkbutton("enable");
    }
});

com.gongsibao.crm.web.SalesmanNCustomerTaskDetailPart = com.gongsibao.crm.web.NCustomerTaskDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.isPlatform = 0;
        this.addUrl = "/panda/crm/salesman/task/add";
        this.editUrl = "/panda/crm/salesman/task/edit";
    }
});
