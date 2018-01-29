System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.SalesmaProductDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
    ctor: function () {
        this.base();
    },
    productCategory1Select:function(record){

    	//加载二级分类
        this.invokeService("queryByProductCategoryId1", [record.id], function (data) {
        	
        	$('#productCategory2_name').combobox('clear').combobox('loadData',data);
        });
    },
    productCategory2Select:function(record){
    	
    	try{
    		
        	$('#product_name').combogrid('clear');
    		var grid = $('#product_name').combogrid('grid');
    		var options = $(grid).datagrid('options');
    		var filter = ' enabled____1 and type_id____'+record.id;
    		options.url = '\/panda\/rest\/reference?code=CRM_Product&filter='+ filter;
    		$(grid).datagrid(options);	
    		
    	}catch(ex){
    		
    	}
    },
    productChange:function(newValue,oldValue){
    	
    	//判断一级分类，二级分类是否为空，如果为空，则
    	//反写一级分类、二级分类
//        this.invokeService("queryByProductId", [newValue], function (data) {
//        	
//
//        });
    }
});