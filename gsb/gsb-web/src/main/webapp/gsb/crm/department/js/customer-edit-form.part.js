
com.gongsibao.crm.web.NCustomerDepartmentEditFormPart = com.gongsibao.crm.web.NCustomerFormPart.Extends( {

    ctor: function () {
        this.base();
    },
});

com.gongsibao.crm.web.DepartmentTaskDetailPart = com.gongsibao.crm.web.NCustomerTaskDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.addUrl = '/panda/crm/department/task/add';
        this.editUrl = '/panda/crm/department/task/edit';
    }
});
