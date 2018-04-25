System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.NCustomerFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
        this.isPlatform = 1;
        this.verifyUrl = null;
        this.addUrl=null;
        this.editUrl=null;
    },
    getsaveState:function(){
    	
    	var id = this.queryString("id");
    	if(id){
    	
    		return UiElementState.Disable;
    	}
    },
    showContactWay:function(){
    	
    	$('.easyui-passwordbox').passwordbox('showPassword');
    	$('#controllernCustomereye').hide();
    	
    	var customerId = this.queryString("id");
		var serviceLocator = new org.netsharp.core.JServiceLocator();
		serviceLocator.invoke(this.context.service, "recordLookLog",[customerId]);
    },
    databindafter:function(){
    	
        $('.easyui-combobox,.easyui-combogrid').combobox("initClearBtn");
        $('.easyui-filebox').filebox("initClearBtn");
        
        var entity = this.viewModel.currentItem;
        if(entity != null && entity.entityState != EntityState.New){

        	this.disable();
        	$('#controllernCustomeredit').linkbutton('enable');
        	$('#controllernCustomereye').linkbutton('enable');
        }
        
    },
    edit:function(){

    	this.enable();
    	
    	//启用【保存】
    	$('#controllernCustomersave').linkbutton('enable');
    	$('.easyui-passwordbox').passwordbox('showPassword');
    	var mobile = $('#mobile').val();
    	if(!System.isnull(mobile)){
    		
    		$('#mobile').passwordbox('disable');
    	}
    	
        $('#controllernCustomeredit').linkbutton('disable');
    	$('#controllernCustomereye').linkbutton('disable');
    },
    onload: function () {

        var id = this.queryString("id");
        if (System.isnull(id)) {

        	var swtCustomerId = this.queryString("swtCustomerId");
        	if(swtCustomerId){
            	
        		this.bySwtCustomerId(swtCustomerId);
        	}else{
        		this.verify();
                this.add();
        	}
        }else {
            this.byId(id);
        }
        
        var ctrlsIds = ['mobile','telephone','weixin','qq'];
		$(ctrlsIds).each(function(i,item){
			$("#"+item).validatebox('disableValidation');
		});
		
    },
    bySwtCustomerId: function (swtCustomerId) {
    	
    	var vm = this.viewModel;
        var me = this;
        this.invokeService("bySwtCustomerId", [swtCustomerId], function (jmessage) {

        	 var nav = jmessage;
        	 var entity = nav.Entity;
             if(entity.entityState != EntityState.New){

        		 var swtServiceId = me.queryString("swtServiceId");
        		 window.location.href = me.editUrl+'?id=' + jmessage.Entity.id+'&swtCustomerId='+swtCustomerId+'&swtServiceId='+swtServiceId;
             }
             vm.currentItem = entity;
             me.paging = nav.Paging;
             me.databind();
        });
    },
    validate: function () {

        var isValidate = $("#" + this.context.formName).form('validate');
       
        if(isValidate){
        	
        	var mobile = $("#mobile").val();
        	var telephone = $("#telephone").val();
        	var weixin = $("#weixin").val();
        	var qq = $("#qq").val();
        	if(System.isnull(mobile) && System.isnull(telephone) && System.isnull(weixin) && System.isnull(qq)){
        		
        		IMessageBox.error("【手机】、【座机】、【微信】、【QQ】 最少填写一项");
        		return false;
        	}
        	
        	if(!System.isnull(mobile)&&!/^(1[0-9])\d{9}$/.test(mobile)){
        		
        		IMessageBox.error("【手机】格式错误");
        		return false;
        	}
        }

        return isValidate;
    },
	contactWayChange:function(el){
		
		var ctrlId = el.id;
		var value = $('#'+ctrlId).val();
//		if(System.isnull(value)){
//			
//			return;
//		}

		var ctrlsIds = [{code:'mobile',text:'手机号'},
		                {code:'telephone',text:'座机'},
		                {code:'weixin',text:'微信'},
		                {code:'qq',text:'QQ'}];
		var currentItem = null;
		$(ctrlsIds).each(function(i,item){
			
			var requiredLabel = $("#"+item.code).parent().prev().find('label').first();
			if(ctrlId != item.code){
				
				requiredLabel.hide();
				$("#"+item.code).validatebox('disableValidation');
			}else{
				
				currentItem = item;
				requiredLabel.show();
				$("#"+item.code).validatebox('enableValidation');
			}
		});

		var me = this;
		var swtCustomerId = this.queryString("swtCustomerId");
		if(!System.isnull(swtCustomerId) && this.viewModel.currentItem.entityState == EntityState.New){
			//从商务通客户端打开
			var contactWay = $(el).val();
			if(System.isnull(contactWay)){
				return;
			}
			var type = $(el).attr('id');
	        this.invokeService("byContactWay", [contactWay,type], function (jmessage) {
	        	
	        	var nav = jmessage;
	        	if(nav.Entity){

	        		var msg = currentItem.text+'【'+contactWay+'】已存在，是否绑定至已有客户信息？';
	        		IMessageBox.confirm(msg,function(isOk){
	        			
	        			if(isOk){
	        				
	        				me.bindSwtCustomerId(swtCustomerId,nav.Entity.id);
	        			}
	        		});
	        	}
	        	
	        });
		}
	},
	validationContactWay:function(contactWay,type,callback){
		
		var id = null;
		if (this.viewModel.currentItem != null) {
			
			id = this.viewModel.currentItem.id;
		}
        this.invokeService("validationContactWay", [id,contactWay,type], function (data) {
        	
        	if(data){
        		
        		callback(data);
        	}
        	
        });
	},
    addExtraProp:function(entity){

    	var swtCustomerId = this.queryString("swtCustomerId");
    	if(swtCustomerId){
        	
    		entity.swtCustomerId = swtCustomerId;
    	}
    	
    	var swtServiceId = this.queryString("swtServiceId");
    	if(swtServiceId){
        	
    		entity.swtServiceId = swtServiceId;
    	}
    },
    onSaving: function (entity) {

    	//提高效率，将明细全部置空
    	if(entity.entityState != EntityState.New){

        	entity.tasks = [];
    	}
    	entity.products = [];
    	entity.follows = [];
    	entity.notifys = [];
    	entity.changes = [];
    	
        return true;
    },
    onSaved: function (jmessage) {
        this.currentItem = jmessage;
        if(this.currentItem!=null){
        	
            this.currentItem.entityState = EntityState.Persist;
            this.viewModel.currentItem = this.currentItem;
            this.databind();
            var me = this;
        	layer.msg("保存成功！", {time: 500, icon:1},function(){

        		me.toNewUrl(jmessage);
        	});
        	
        }else{
        	
        	IMessageBox.error("保存失败！");
        }
    },
    toNewUrl:function(entity){
    	window.location.href=this.editUrl+'?id='+entity.id;
    	
    },
    verify:function(){
    	
    	layer.open({
    		  type: 2,
    		  title: '客户校验',
    		  fixed: false,
    		  maxmin: false,
    		  shadeClose:false,
    		  //closeBtn:false,
    		  area: ['70%','70%'],
    		  content: this.verifyUrl,
    		  cancel: function(){}
    	    });	
    },
    bindCustomer:function(customerId){

    	var swtCustomerId = this.queryString("swtCustomerId");
    	if(swtCustomerId){
    		
    		// 商务通过来的
    		window.location.href=this.editUrl+'?id='+customerId;
    	}else{
    		
        	var url = this.addUrl +'?id='+customerId;
        	if(window.top.workbench){

            	window.top.workbench.openWorkspace("绑定客户",url,'fa fa-edit',true);
        		//关闭当前页签
            	window.top.$('#tabs').tabs('close','新增客户');
        	}else{
        		
        		window.location.href=this.editUrl+'?id='+customerId;
        	}
    	}
    }
});


com.gongsibao.crm.web.NCustomerTaskDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
    ctor: function () {
    	
        this.base();
        
        //判断是否平台售前添加
        this.isPlatform = 1;
        this.addUrl = null;
        this.editUrl = null;
    },
    add: function() {
    	
//    	var url='';
//    	if(this.isPlatform==1){
//    		
//        	if(this.parent.viewModel.currentItem.entityState == EntityState.New){
//        		
//        		IMessageBox.info('请先保存客户信息！');
//        		return;
//        	}
//        	
//        	var customerId = this.parent.viewModel.currentItem.id;
//        	url=this.addUrl+'?fk=customerId:'+customerId;
//    	}else{
//    		
//        	url=this.addUrl+'?isPlatform=0&ctrl='+this.context.instanceName;
//    	}
    	
    	var url = this.addUrl+'?isPlatform=0&type=add&ctrl='+this.context.instanceName;
    	if(this.parent.viewModel.currentItem.entityState != EntityState.New){
			
    		var customerId = this.parent.viewModel.currentItem.id;
    		url = this.addUrl+'?isPlatform=1&fk=customerId:'+customerId;
		}
    	
    	var swtCustomerId = this.queryString("swtCustomerId");
    	var swtServiceId = this.queryString("swtServiceId");
    	if(!System.isnull(swtCustomerId)){
    		
    		url = url+'&swtCustomerId='+swtCustomerId;
    	}
    	
    	if(!System.isnull(swtServiceId)){
    		
    		url = url+'&swtServiceId='+swtServiceId;
    	}
    	
//    	var swtCustomerId = this.queryString("swtCustomerId");
//    	if(!System.isnull(swtCustomerId)){
//    		
//    		//设置默认值
//    		currentItem.customerSourceId = 4181;
//    		currentItem.consultWay = 42143;
//    	}

    	layer.open({
  		  type: 2,
  		  title: '新增商机',
  		  fixed: false,
  		  maxmin: true,
  		  shadeClose:true,
  		  area: ['90%','90%'],
  		  content: url,
  		  cancel: function(){ 

		  }
  	    });
    },
	doubleClickRow : function(rowIndex, rowData) {
		
		var url='';

    	if(this.isPlatform==1){
    		
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
	},
	save:function(entity){
		
		var isValidated = $("#" + this.context.formName).form('validate');
        if (!isValidated) {
            return;
        }
        layer.closeAll();
        this.viewModel.context = this.context;

        this.saveBefore(entity);
        this.viewModel.clear();
        
        //特殊处理引用字段
        this.referenceField(entity);
        
        //这里主要解决int自增Id的BUG,在提交保存时要删除状态为new的明细实体Id
        entity.id = entity.id || System.GUID.newGUID();
        
        var $grid = this.getGrid();
        
        $grid.datagrid('unselectAll');
        
        //选中一行
        $grid.datagrid('selectRecord', entity.id);
        
        var selectedRow = $grid.datagrid('getSelected');
        if(selectedRow == null){
        	
        	//BUG：在修改新增数据时有问题
        	$grid.datagrid('appendRow', entity);
            
        }else{
        	
        	var rowIndex = $grid.datagrid('getRowIndex', entity);
        	$grid.datagrid('updateRow',{index: rowIndex,row: entity});
        	$grid.datagrid('refreshRow', rowIndex);
        }
        
		this.saveAfter(entity);
	}
});


/**
 * 扩展联系方式验证
 */
$.extend($.fn.validatebox.defaults.rules, {
	  
	validationContactWay: { 
    	
        validator: function(value,param){

        	var isValidator = false;
        	var me = this;
        	var serviceLocator = new org.netsharp.core.JServiceLocator();
    		var id = null;
    		if (controllernCustomer.viewModel.currentItem != null) {
    			
    			id = controllernCustomer.viewModel.currentItem.id;
    		}
        	serviceLocator.invoke(controllernCustomer.context.service, 'validationContactWay', [id,value.trim(),param[0]], function(data){

        		isValidator = data==null?true:false;
        	},null, false);
        	
        	return isValidator;
        },    
        message: '{1}已存在'   
    }
});

com.gongsibao.crm.web.NCustomerAddFormPart = com.gongsibao.crm.web.NCustomerFormPart.Extends( {
    ctor: function () {
        this.base();
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