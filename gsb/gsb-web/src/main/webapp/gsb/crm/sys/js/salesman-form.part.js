System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.SalesmanProductDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
    ctor: function () {
        this.base();
    },
    firstProductCategorySelect:function(newValue,oldValue){

		if(newValue != null){
			
	    	var newValue = parseInt(newValue);
			if(System.isnull(newValue) || typeof newValue != 'number' || isNaN(newValue)){
				
				return;
			}
		}
    	var departmentId = this.parent.viewModel.currentItem.departmentId;
    	//加载二级分类
        this.invokeService("queryProductSecondCategory", [departmentId,newValue], function (data) {
        	
        	$('#productCategory2_name').combobox('clear').combobox('loadData',data);
        },false);
    },
    secondProductCategorySelect:function(newValue,oldValue){
    	
    	try{
    		if(newValue != null){
    			
    	    	var newValue = parseInt(newValue);
    			if(System.isnull(newValue) || typeof newValue != 'number' || isNaN(newValue)){
    				
    				return;
    			}
    		}
        	$('#product_name').combogrid('clear');
    		var grid = $('#product_name').combogrid('grid');
    		var options = $(grid).datagrid('options');
    		
    		var departmentId = this.parent.viewModel.currentItem.departmentId;
    		var filter = ' enabled____1 and type_id____'+newValue +' and id in (select product_id from sp_department_product where department_id____'+departmentId+')';
    		
    		options.url = '\/panda\/rest\/reference?code=CRM_Product&filter='+ filter;
    		$(grid).datagrid(options);	
    		
    	}catch(ex){
    		
    	}
    },
    provinceSelect:function(newValue,oldValue){
    	
		if(newValue != null){
			
	    	var newValue = parseInt(newValue);
			if(System.isnull(newValue) || typeof newValue != 'number' || isNaN(newValue)){
				
				return;
			}
		}
    	var departmentId = this.parent.viewModel.currentItem.departmentId;
    	this.invokeService("queryCity", [departmentId,newValue], function (data) {
        	
        	$('#city_name').combobox('clear').combobox('loadData',data);
        },false);
    },
    citySelect:function(newValue,oldValue){
    	
		if(newValue != null){
			
	    	var newValue = parseInt(newValue);
			if(System.isnull(newValue) || typeof newValue != 'number' || isNaN(newValue)){
				
				return;
			}
		}
    	var departmentId = this.parent.viewModel.currentItem.departmentId;
		this.invokeService("queryCounty", [departmentId,newValue], function (data) {
	    	
	    	$('#county_name').combobox('clear').combobox('loadData',data);
	    },false);
    },
    addBefore:function(){
    	
    	this.initData();
    },
    editBefore:function(){
    	
    	this.initData();
    },
    initData:function(){
    	
    	var departmentId = this.parent.viewModel.currentItem.departmentId;
        
    	//处理一级分类
    	this.invokeService("queryProductFirstCategory", [departmentId], function (data) {
        	
        	$('#productCategory1_name').combobox('clear').combobox('loadData',data);
        },false);
        
        //处理省份
    	this.invokeService("queryProvince", [departmentId], function (data) {
        	
        	$('#province_name').combobox('clear').combobox('loadData',data);
        },false);
    },

});

com.gongsibao.crm.web.SalesmanFormPart = org.netsharp.panda.commerce.FormPart.Extends( {
    ctor: function () {
        this.base();
    },
    databindafter:function(){
    	
        $('.easyui-combobox,.easyui-combogrid').combobox("initClearBtn");
        $('.easyui-filebox').filebox("initClearBtn");
        
    	var grid = $('#role_name').combogrid('grid');
    	var options = $(grid).datagrid('options');
    	
    	var supplierId = this.viewModel.currentItem.supplierId;
    	var filter = 'id in (SELECT role_id FROM sp_function_module_role WHERE function_module_id IN (SELECT function_module_id FROM sp_supplier_function_module WHERE supplier_id ____ '+supplierId+'))';
    	options.url = '\/panda\/rest\/reference?code=Role&filter='+ filter;
    	$(grid).datagrid(options);	
    }
});
