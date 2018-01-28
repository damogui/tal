
com.gongsibao.crm.web.NCustomerSalesmanEditFormPart = com.gongsibao.crm.web.NCustomerFormPart.Extends( {

    ctor: function () {
        this.base();
        this.verifyUrl = '/panda/crm/salesman/customer/verify';
        this.addUrl='/panda/crm/salesman/customer/edit';
        this.editUrl='/panda/crm/salesman/customer/edit';
    },
});

com.gongsibao.crm.web.SalesmanTaskDetailPart = com.gongsibao.crm.web.NCustomerTaskDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.addUrl = '/panda/crm/salesman/task/add';
        this.editUrl = '/panda/crm/salesman/task/edit';
    }
});
