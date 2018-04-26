
com.gongsibao.crm.web.NCustomerDepartmentEditFormPart = com.gongsibao.crm.web.NCustomerFormPart.Extends( {

    ctor: function () {
        this.base();
        this.isPlatform = 0;
        this.verifyUrl = '/panda/crm/department/customer/verify';
        this.addUrl='/panda/crm/department/customer/edit';
        this.editUrl='/panda/crm/department/customer/edit';
    },
    toNewUrl:function(){
    	
		window.location.href = this.editUrl+'?id='+this.currentItem.id;
    }
});

com.gongsibao.crm.web.DepartmentTaskDetailPart = com.gongsibao.crm.web.NCustomerTaskDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.isPlatform = 0;
        this.addUrl = '/panda/crm/department/task/add';
        this.editUrl = '/panda/crm/department/task/edit';
    }
});
