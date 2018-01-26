System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.MyNCustomerAddFormPart = com.gongsibao.crm.web.NCustomerAddFormPart.Extends( {

    ctor: function () {
        this.base();
    }
});

com.gongsibao.crm.web.MyNCustomerTaskDetailPart = com.gongsibao.crm.web.NCustomerTaskDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.addUrl = "/panda/my/task/add";
    }
});
