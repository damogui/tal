com.gongsibao.crm.web.NCustomerTaskEditFormPart = com.gongsibao.crm.web.NCustomerTaskAddFormPart.Extends( {

    ctor: function () {
        this.base();
    }
});
com.gongsibao.crm.web.PlatformTaskFollowDetailPart = com.gongsibao.crm.web.TaskFollowDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.addFollowUrl = '/panda/crm/platform/task/follow';
    }
});
