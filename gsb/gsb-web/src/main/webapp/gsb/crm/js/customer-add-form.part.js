System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.NCustomerAddFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    onload: function () {

        var id = this.queryString("id");
        if (System.isnull(id)) {

    		this.matching();
            this.add();
        }else {
            this.byId(id);
        }
        
        var ctrlsIds = ['mobile','telephone','weixin','qq'];
		$(ctrlsIds).each(function(i,item){
			$("#"+item).validatebox('disableValidation');
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

        return true;
    },
	contactWayChange:function(el){
		
		var ctrlsIds = [{code:'mobile',text:'手机号'},
		                {code:'telephone',text:'座机'},
		                {code:'weixin',text:'微信'},
		                {code:'qq',text:'QQ'}];
		var ctrlId = el.id;
		var currentItem = null;
		$(ctrlsIds).each(function(i,item){
			
			if(ctrlId != item.code){
				
				$("#"+item.code).validatebox('disableValidation');
			}else{
				
				currentItem = item;
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
    onSaved: function (jmessage) {
        this.currentItem = jmessage;
        if(this.currentItem!=null){

        	IMessageBox.toast("保存成功！",1,function(){
        		
//            	var top = window.top;
//            	var parent = window.parent;
//            	if(top){
//            		
//            		top.workbench.closeSelectedTab();
//            	}else if(parent){
//            		
//            		var index = parent.layer.getFrameIndex(window.name); 
//            		parent.layer.close(index);
//            	}
        	});
        }else{
        	
        	IMessageBox.error("保存失败！");
        }
    },
    matching:function(){

    	var url='/panda/operation/customer/verify';
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


com.gongsibao.crm.web.NCustomerTaskDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
    ctor: function () {
        this.base();
    },
    add: function() {
    	
    	if(this.parent.viewModel.currentItem.entityState == EntityState.New){
    		
    		IMessageBox.info('请先保存！');
    		return;
    	}
    	
//    	var swtCustomerId = this.queryString("swtCustomerId");
//    	if(!System.isnull(swtCustomerId)){
//    		
//    		//设置默认值
//    		currentItem.customerSourceId = 4181;
//    		currentItem.consultWay = 42143;
//    	}
    	
    	var customerId = this.parent.viewModel.currentItem.id;
    	var url='/panda/operation/task/add?fk=customerId:'+customerId;
    	layer.open({
  		  type: 2,
  		  title: '新增任务',
  		  fixed: false,
  		  maxmin: true,
  		  shadeClose:true,
  		  area: ['90%','90%'],
  		  content: url,
  		  cancel: function(){ 

		  }
  	    });
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
