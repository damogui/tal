System.Declare("com.gongsibao.supplier.web");
com.gongsibao.supplier.web.SupplierFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    }
});

System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.SupplierProductDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
    ctor: function () {
        this.base();
    },
    productCategory1Select:function(record){
    	
    	//alert(record.id );	
    	//加载二级分类
        this.invokeService("queryByProductCategoryId1", [record.id], function (data) {
        	

        });
    },
    productCategory2Select:function(record){
    	
    	//反写一级分类，加载产品
        this.invokeService("queryByProductCategoryId2", [record.id], function (data) {
        	

        });
    },
    productChange:function(newValue,oldValue){
    	
    	//判断一级分类，二级分类是否为空，如果为空，则
    	//反写一级分类、二级分类
        this.invokeService("queryByProductId", [newValue], function (data) {
        	

        });
    }
});