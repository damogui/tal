System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.NCustomerTaskAddFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    supplierChange:function(newValue,oldValue){
    	
    	//alert(newValue);
    },
    departmentChange:function(newValue,oldValue){
    	
    	//alert(newValue);
    }
});


com.gongsibao.crm.web.NCustomerTaskProductDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
    ctor: function () {
        this.base();
    },
    productCategory1Select:function(record){
    	
    	//alert(record.id);
    	
    	//加载二级分类
        this.invokeService("queryByProducCategoryId1", [record.id], function (data) {
        	

        });
    },
    productCategory2Select:function(record){
    	
    	//反写一级分类，加载产品
        this.invokeService("queryByProducCategoryId2", [record.id], function (data) {
        	

        });
    },
    productChange:function(newValue,oldValue){
    	
    	//判断一级分类，二级分类是否为空，如果为空，则
    	//反写一级分类、二级分类
        this.invokeService("queryByProducId", [newValue], function (data) {
        	

        });
    }
});

