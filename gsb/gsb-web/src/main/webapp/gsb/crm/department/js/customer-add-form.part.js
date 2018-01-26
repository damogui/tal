System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.DepartmentNCustomerAddFormPart = com.gongsibao.crm.web.NCustomerAddFormPart.Extends( {

    ctor: function () {
        this.base();
        this.verifyUrl = '/panda/crm/department/customer/verify';
        this.addUrl='/panda/crm/department/customer/add';
    }
});

com.gongsibao.crm.web.DepartmentNCustomerTaskDetailPart = com.gongsibao.crm.web.NCustomerTaskDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.addUrl = "/panda/crm/department/task/add";
    }
});
