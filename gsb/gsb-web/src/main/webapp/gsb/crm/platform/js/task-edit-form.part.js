com.gongsibao.crm.web.NCustomerTaskEditFormPart = com.gongsibao.crm.web.NCustomerTaskAddFormPart.Extends( {

    ctor: function () {
        this.base();
    }
});
com.gongsibao.crm.web.PlatformTaskFollowDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
    ctor: function () {
        this.base();
        this.addTaskUrl = '/panda/crm/platform/task/followUp/from';
        this.addFollowUrl = '/panda/crm/platform/task/followUp/from';
    }
});
