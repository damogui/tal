System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.MyNCustomerAddFormPart = com.gongsibao.crm.web.NCustomerAddFormPart.Extends( {

    ctor: function () {
        this.base();
    },
    matching:function(){

    	var url='/panda/crm/my/customer/verify';
    	layer.open({
  		  type: 2,
  		  title: '客户校验',
  		  fixed: false,
  		  maxmin: false,
  		  shadeClose:false,
  		  closeBtn:false,
  		  area: ['70%','70%'],
  		  content: url,
  		  cancel: function(){ 

		  }
  	    });
    }
});

com.gongsibao.crm.web.MyNCustomerTaskDetailPart = com.gongsibao.crm.web.NCustomerTaskDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.addUrl = "/panda/my/task/add";
    }
});
