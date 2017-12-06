System.Declare("com.gongsibao.uc.web");
com.gongsibao.uc.web.UserFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    }
});



/**
 * 扩展联系方式验证
 */
$.extend($.fn.validatebox.defaults.rules, {
	  
	validationMobile: { 
    	
        validator: function(value,param){

        	var isValidator = false;
        	var me = this;
        	var serviceLocator = new org.netsharp.core.JServiceLocator();
    		var id = null;
    		if (controlleruser.viewModel.currentItem != null) {
    			
    			id = controlleruser.viewModel.currentItem.id;
    		}
        	serviceLocator.invoke(controlleruser.context.service, 'hasMobile', [id,value], function(data){

        		isValidator = !data;
        	},null, false);
        	
        	return isValidator;
        },    
        message: '手机号已存在'   
    }
});
