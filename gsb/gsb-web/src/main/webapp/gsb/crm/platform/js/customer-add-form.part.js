System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.NCustomerPlatformAddFormPart =com.gongsibao.crm.web.NCustomerFormPart.Extends( {

    ctor: function () {
        this.base();
        this.verifyUrl = '/panda/crm/platform/customer/verify';
        this.addUrl='/panda/crm/platform/customer/add';
        this.editUrl='/panda/crm/platform/customer/edit';
    }    
});


com.gongsibao.crm.web.PlatformTaskDetailPart = com.gongsibao.crm.web.NCustomerTaskDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.addUrl = "/panda/crm/platform/task/add";
        this.editUrl = "/panda/crm/platform/task/edit";
    }
});
