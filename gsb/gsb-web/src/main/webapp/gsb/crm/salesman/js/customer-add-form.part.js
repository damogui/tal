System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.MyNCustomerAddFormPart = com.gongsibao.crm.web.NCustomerAddFormPart.Extends( {

    ctor: function () {
        this.base();
        this.verifyUrl = '/panda/crm/salesman/customer/verify';
        this.addUrl='/panda/crm/salesman/customer/add';
    }
});

com.gongsibao.crm.web.MyNCustomerTaskDetailPart = com.gongsibao.crm.web.NCustomerTaskDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.addUrl = "/panda/crm/salesman/task/add";
    }
});
