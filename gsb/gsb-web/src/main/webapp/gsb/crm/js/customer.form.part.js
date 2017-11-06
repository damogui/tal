System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.CustomerFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    onload: function () {

        var id = this.queryString("id");
        if (System.isnull(id)) {

        	var swtCustomerId = this.queryString("swtCustomerId");
        	var swtServiceId = this.queryString("swtServiceId");
        	if(swtCustomerId){
            	
        		this.bySwtCustomerId(swtCustomerId);
        	}else{

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
    databindafter:function(){
    	
    	if(this.viewModel.currentItem.entityState == EntityState.Persist){
    		
    		var mobile = $("#mobile").val();
    		if(!System.isnull(mobile)){

        		$("#mobile").prop("disabled", true);
    		}
    		//修改状态：禁用全表单
    		this.viewModel.disable();
    	}
    	
        $('.easyui-combobox,.easyui-combogrid').combobox("initClearBtn");
        $('.easyui-filebox').filebox("initClearBtn");
    },
    bySwtCustomerId: function (swtCustomerId) {

        var vm = this.viewModel;
        var me = this;
        this.invokeService("bySwtCustomerId", [swtCustomerId], function (jmessage) {
        	
        	 var nav = jmessage;
             vm.currentItem = nav.Entity;
             if(vm.currentItem.entityState != EntityState.New){

                 vm.currentItem.entityState = EntityState.Persist;
             }
             me.paging = nav.Paging;
             me.databind();
        });
    },
    allocationTypeChange:function(newValue, oldValue){

    	//TYPE_1(1, "自然分配"), 
    	//TYPE_2(2, "指定部门");
		if(newValue==1){

			$("#allocationOrgId").combobox('disable').combobox('disableValidation').combobox('clear');
		}else{

			$("#allocationOrgId").combobox('enable').combobox('enableValidation');
		}
	},
    validate: function () {

        var isValidate = $("#" + this.context.formName).form('validate');
        if(isValidate){
        	
        	var ctrlsIds = ['mobile','telephone','weixin','qq'];
        	var mobile = $("#mobile").val();
        	var telephone = $("#telephone").val();
        	var weixin = $("#weixin").val();
        	var qq = $("#qq").val();
        	if(System.isnull(mobile) && System.isnull(telephone) && System.isnull(weixin) && System.isnull(qq)){
        		
        		IMessageBox.error("【手机】、【座机】、【微信】、【QQ】 最少填写一项。");
        		return false;
        	}
        }
        return isValidate;
    },
	contactWayChange:function(el){
		
		var ctrlsIds = ['mobile','telephone','weixin','qq'];
		var ctrlId = el.id;
		
		$(ctrlsIds).each(function(i,item){
			
			if(ctrlId != item){
				
				$("#"+item).validatebox('disableValidation');
			}else{
				
				$("#"+item).validatebox('enableValidation');
			}
		});
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
    	
    	if(entity.fProvinceId){
    	
    		entity.cityId = entity.fProvinceId;
    	}
    	
    	if(entity.fCityId){
        	
    		entity.cityId = entity.fCityId;
    	}
    	
    	if(entity.fCountyId){
        	
    		entity.cityId = entity.fCountyId;
    	}

    	var swtCustomerId = this.queryString("swtCustomerId");
    	if(swtCustomerId){
        	
    		entity.swtCustomerId = swtCustomerId;
    	}
    	
    	var swtServiceId = this.queryString("swtServiceId");
    	if(swtServiceId){
        	
    		entity.swtServiceId = swtServiceId;
    	}
    	
    	if(entity.allocationOrgId == null){
    		
    		entity.allocationOrgId = 0;
    	}
    	
    	var ts = this.queryString("ts");
    },
    customerSourceChange:function(newValue, oldValue){
    	
    	if(newValue === '4177'){

    		$("#customerSourceOther").prop("disabled", false);
    	}else{
    		$("#customerSourceOther").prop("disabled", true);
    	}
    },
    consultWayChange:function(newValue, oldValue){
    	
    	if(newValue === '4219'){

    		$("#consultWayOther").prop("disabled", false);
    	}else{
    		$("#consultWayOther").prop("disabled", true);
    	}
    },
    followStatusChange:function(newValue, oldValue){
    	
    	//无效客户
    	if(newValue === '4015'){

    		$("#unvalidRemark").prop("disabled", false);
    	}else{
    		$("#unvalidRemark").prop("disabled", true);
    	}
    	
    	//潜在客户
    	if(newValue === '4020'){

    		$("#maybeRemark").prop("disabled", false);
    	}else{
    		$("#maybeRemark").prop("disabled", true);
    	}
    	
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
    		if (controllercustomer.viewModel.currentItem != null) {
    			
    			id = controllercustomer.viewModel.currentItem.id;
    		}
        	serviceLocator.invoke(controllercustomer.context.service, 'validationContactWay', [id,value,param[0]], function(data){

        		isValidator = data==null?true:false;
        	},null, false);

        	return isValidator;
        },    
        message: '该{1}已存在'   
    }
});

System.Declare("com.gongsibao.controls");
com.gongsibao.controls.CityComboBox = org.netsharp.controls.PccBox.Extends({
	ctor: function() {
		this.base();
		this.service = 'com.gongsibao.controls.CityComboBoxController';
	}
});