/**
 * Created by win on 2018/1/26.
 */
System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.DepartmentProductDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
    ctor: function () {
        this.base();
    },
    firstProductCategorySelect:function(record){

    	var parentDepartmentId = this.parent.viewModel.currentItem.parentId;
    	var supplierId = this.parent.viewModel.currentItem.supplierId;
    	//加载二级分类
        this.invokeService("queryProductSecondCategory", [parentDepartmentId,supplierId,record.id], function (data) {
        	
        	$('#productCategory2_name').combobox('clear').combobox('loadData',data);
        },false);
    },
    secondProductCategorySelect:function(record){
    	
    	try{
    		
        	$('#product_name').combogrid('clear');
    		var grid = $('#product_name').combogrid('grid');
    		var options = $(grid).datagrid('options');
    		
    		var filter = ' enabled____1 and type_id____' + record.id;
        	var parentDepartmentId = this.parent.viewModel.currentItem.parentId;
        	var supplierId = this.parent.viewModel.currentItem.supplierId;
        	if(parentDepartmentId == null||parentDepartmentId==0){
        		
        		filter+=' and id in (select product_id from sp_supplier_product where id____'+supplierId+')';
        	}else{
        		
        		filter+=' and id in (select product_id from sp_department_product where department_id____'+parentDepartmentId+')';
        	}
    		options.url = '\/panda\/rest\/reference?code=CRM_Product&filter='+ filter;
    		$(grid).datagrid(options);	
    		
    	}catch(ex){
    		
    	}
    },
    provinceSelect:function(record){
    	
    	var parentDepartmentId = this.parent.viewModel.currentItem.parentId;
    	var supplierId = this.parent.viewModel.currentItem.supplierId;
    	this.invokeService("queryCity", [parentDepartmentId,supplierId,record.id], function (data) {
        	
        	$('#city_name').combobox('clear').combobox('loadData',data);
        },false);
    },
    citySelect:function(record){
    	
    	var parentDepartmentId = this.parent.viewModel.currentItem.parentId;
		var supplierId = this.parent.viewModel.currentItem.supplierId;
		this.invokeService("queryCounty", [parentDepartmentId,supplierId,record.id], function (data) {
	    	
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
    	
    	var parentDepartmentId = this.parent.viewModel.currentItem.parentId;
    	var supplierId = this.parent.viewModel.currentItem.supplierId;
        
    	//处理一级分类
    	this.invokeService("queryProductFirstCategory", [parentDepartmentId,supplierId], function (data) {
        	
        	$('#productCategory1_name').combobox('clear').combobox('loadData',data);
        },false);
        
        //处理省份
    	this.invokeService("queryProvince", [parentDepartmentId,supplierId], function (data) {
        	
        	$('#province_name').combobox('clear').combobox('loadData',data);
        },false);
    }
});

