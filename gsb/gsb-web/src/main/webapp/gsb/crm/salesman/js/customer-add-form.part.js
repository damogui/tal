System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.SalesmanNCustomerAddFormPart =com.gongsibao.crm.web.NCustomerFormPart.Extends( {

    ctor: function () {
        this.base();
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
    }
});

com.gongsibao.crm.web.SalesmanNCustomerTaskDetailPart = com.gongsibao.crm.web.NCustomerTaskDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.addUrl = "/panda/crm/salesman/task/add";
        this.editUrl = "/panda/crm/salesman/task/edit";
    }
});
