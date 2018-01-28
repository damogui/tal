
com.gongsibao.crm.web.NCustomerPlatformEditFormPart = com.gongsibao.crm.web.NCustomerFormPart.Extends( {

    ctor: function () {
        this.base();
    },
});


com.gongsibao.crm.web.PlatformTaskDetailPart = com.gongsibao.crm.web.NCustomerTaskDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.addUrl = '/panda/crm/salesman/task/add';
        this.editUrl = '/panda/crm/salesman/task/edit';
    }
});
