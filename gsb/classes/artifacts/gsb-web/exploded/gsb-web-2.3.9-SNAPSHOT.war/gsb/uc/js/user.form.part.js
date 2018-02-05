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

        	var isValidator = /^0?(13[0-9]|15[012356789]|18[0123456789]|14[57]|17[0135678])[0-9]{8}$/.test(value);
        	if(!isValidator){
        		
        		return false;
        	}
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
        message: '手机号已存在或格式错误'   
    }
});
