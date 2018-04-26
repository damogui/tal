System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.NCustomerPlatformAddFormPart =com.gongsibao.crm.web.NCustomerAddFormPart.Extends( {

    ctor: function () {
        this.base();
        this.isPlatform = 1;
        this.verifyUrl = '/panda/crm/platform/customer/verify';
        this.addUrl='/panda/crm/platform/customer/add';
        this.editUrl='/panda/crm/platform/customer/edit';
    },
    getsaveState:function(){
    	
    	return UiElementState.Enable;
    },
    getverifyState:function(){
    	
    	var id = this.queryString("id");
    	if(id){
    	
    		return UiElementState.Hide;
    	}
    }
});


com.gongsibao.crm.web.PlatformTaskDetailPart = com.gongsibao.crm.web.NCustomerTaskDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.isPlatform = 1;
        this.addUrl = "/panda/crm/platform/task/add";
        this.editUrl = "/panda/crm/platform/task/add";
    },
	doubleClickRow : function(rowIndex, rowData) {
		
		var url='';

    	if(rowData.entityState != EntityState.New){
    		
        	url = this.editUrl+'?id='+rowData.id;
    	}else{
    		
        	url = this.editUrl+'?isPlatform=1&type=edit&ctrl='+this.context.instanceName;
    	}
    	
    	layer.open({
  		  type: 2,
  		  title: '商机信息',
  		  fixed: false,
  		  maxmin: true,
  		  shadeClose:true,
  		  area: ['98%','98%'],
  		  content: url,
          success: function(layero, index){
        	  
              var body = layer.getChildFrame('body',index);
              var iframeWin = window[layero.find('iframe')[0]['name']];
              var subMainCtrl = iframeWin.workspace.parts.byIndex(0).value;//得到子页面的主控制器对象
              subMainCtrl.setEntity(rowData);
          },
  		  cancel: function(){ 

		  }
  	    });
	}
});
