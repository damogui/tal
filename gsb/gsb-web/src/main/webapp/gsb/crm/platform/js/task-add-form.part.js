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
    	//TYPE_1(1, "自动分配"), 
    	//TYPE_2(2, "指定分配");
		if(newValue==1){
			$("#owner_name").combobox('disable').combobox('disableValidation').combobox('setValue','-1');
		}else{

			$("#owner_name").combobox('enable').combobox('enableValidation');
		}
    },
    onSaved: function (jmessage) {
    	
        this.currentItem = jmessage;
        if(this.currentItem!=null){
        	
            this.currentItem.entityState = EntityState.Persist;
            this.viewModel.currentItem = this.currentItem;
            this.databind();
        	layer.msg("保存成功！", {time: 500, icon:1},function(){
        		
        		window.parent.location.reload();
        	});
        	
        }else{
        	
        	IMessageBox.error("保存失败！");
        }
    }
});

//initValue 此方法不会触发改变事件，

com.gongsibao.crm.web.TaskProductDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
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
