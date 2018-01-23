System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.NCustomerTaskAddFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    supplierChange:function(newValue,oldValue){
    	
    	console.log(newValue);
    },
    departmentChange:function(newValue,oldValue){
    	
    	console.log(newValue);
    },
    costedChange:function(checked){
    	
    	console.log(checked);
    },
    sourceSelect:function(record){
    	
    	console.log(record.id);
    },
    consultWaySelect:function(record){
    	
    	console.log(record.id);
    },
    allocationDispositonChange:function(newValue,oldValue){
    	
    	console.log(newValue);
    },
    allocationTypeChange:function(newValue,oldValue){
    	
    	console.log(newValue);
    }
});

//initValue 此方法不会触发改变事件，


com.gongsibao.crm.web.NCustomerTaskProductDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
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
