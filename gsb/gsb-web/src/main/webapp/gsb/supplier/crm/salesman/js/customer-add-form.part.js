System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.SalesmanNCustomerAddFormPart =com.gongsibao.crm.web.NCustomerFormPart.Extends( {

    ctor: function () {
        this.base();
        this.isPlatform = 0;
        this.verifyUrl = '/panda/crm/salesman/customer/verify';
        this.addUrl='/panda/crm/salesman/customer/add';
        this.editUrl='/panda/crm/salesman/customer/edit';
    },
    getsaveState:function(){
    	
    	return UiElementState.Enable;
    },
    getverifyState:function(){
    	
    	var id = this.queryString("id");
    	if(id){
    	
    		return UiElementState.Hide;
    	}
    },
    onSaving: function (entity) {

    	if(entity.entityState == EntityState.New){
    		
    		if(entity.tasks && entity.tasks.length == 0){
    			
    			IMessageBox.error("保存失败：请添加商机！");
    			return false;
    		}
    		
    	}else{
    		
    		entity.tasks = [];
    	}
    	entity.products = [];
    	entity.follows = [];
    	entity.notifys = [];
    	entity.changes = [];
    	
    	$("#controllernCustomersave").linkbutton("disable");
    	//提高效率，将明细全部置空
        return true;
    },
    doSave: function (entity) {

        var me = this;
        this.invokeService("save", [entity], function (jmessage) {
            me.onSaved(jmessage);
        }, true,function () {
            $("#controllernCustomersave").linkbutton("enable");//不放开的话业务异常抛出来没法保存

        });
    },
    databindextra: function (entity) {

    	$("#controllernCustomersave").linkbutton("enable");
    }
});

com.gongsibao.crm.web.SalesmanNCustomerTaskDetailPart = com.gongsibao.crm.web.NCustomerTaskDetailPart.Extends( {
    ctor: function () {
        this.base();
        this.isPlatform = 0;
        this.addUrl = "/panda/crm/salesman/task/add";
        this.editUrl = "/panda/crm/salesman/task/add";
    },
	doubleClickRow : function(rowIndex, rowData) {
		
		var url='';

    	if(rowData.entityState != EntityState.New){
    		
        	url = this.editUrl+'?id='+rowData.id;
    	}else{
    		
        	url = this.editUrl+'?isPlatform=0&type=edit&ctrl='+this.context.instanceName;
    	}
    	
    	layer.open({
  		  type: 2,
  		  title: '商机信息',
  		  fixed: false,
  		  maxmin: true,
  		  shadeClose:true,
  		  area: ['90%','90%'],
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
