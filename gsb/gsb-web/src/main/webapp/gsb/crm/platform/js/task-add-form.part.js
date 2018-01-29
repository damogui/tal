System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.NCustomerTaskAddFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    supplierChange:function(newValue,oldValue){
    	
    	//改变部门的查询条件
    	$('#department_name').combogrid('clear');
		var grid = $('#department_name').combogrid('grid');
		var options = $(grid).datagrid('options');
		var filter = ' supplier_id ____ ----'+ newValue + '----';
		options.url = '\/panda\/rest\/reference?code=SupplierDepartment&filter='+ filter;
		$(grid).datagrid(options);
		
    	//改变业务员的查询条件
    	$('#owner_name').combogrid('clear');
		var grid = $('#owner_name').combogrid('grid');
		var options = $(grid).datagrid('options');
		var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE supplier_id ____ ----'+ newValue + '----)';
		options.url = '\/panda\/rest\/reference?code=Employee&filter='+ filter;
		$(grid).datagrid(options);
		
		
    },
    departmentChange:function(newValue,oldValue){
    	
    	//改变业务员的查询条件
    	$('#owner_name').combogrid('clear');
		var grid = $('#owner_name').combogrid('grid');
		var options = $(grid).datagrid('options');
		var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE department_id ____ ----'+ newValue + '----)';
		options.url = '\/panda\/rest\/reference?code=Employee&filter='+ filter;
		$(grid).datagrid(options);
    },
    costedChange:function(checked){
    	
    	console.log(checked);
    },
    sourceSelect:function(record){
    	
    	var $ctrl = $("#sourceOther");
    	if(record.id === '4177'){

    		$ctrl.prop("disabled",false);
    		$ctrl.validatebox('enableValidation');
    	}else{
    		$ctrl.prop("disabled",true);
    		//ctrl.validatebox('disableValidation');
    	}
    },
    consultWaySelect:function(record){
    	
    	var $ctrl = $("#consultWayOther");
    	if(record.id === '4219'){

    		$ctrl.prop("disabled",false);
    		$ctrl.validatebox('enableValidation');
    	}else{
    		$ctrl.prop("disabled",true);
    		//ctrl.validatebox('disableValidation');
    	}
    },
    allocationDispositonChange:function(newValue,oldValue){
    	
    	console.log(newValue);
    },
    allocationTypeChange:function(newValue,oldValue){

//    	AUTO(1, "自动分配"), 
//    	MANUAL(2, "手动分配"),
//    	SemiAutomatic(3, "半自动分配");
		if(newValue==1){
			$("#supplier_name").combogrid('setValue','').combogrid('disable');
//			var options1 = $("#supplier_name").combogrid('options');
//			options1.required = false;
//			$("#supplier_name").combogrid(options1);
			
			
			$("#department_name").combogrid('setValue','').combogrid('disable');
//			var options2 = $("#department_name").combogrid('options');
//			options2.required = false;
//			$("#department_name").combogrid(options2);
			
			
			$("#owner_name").combogrid('setValue','').combogrid('disable');
//			var options3 = $("#owner_name").combogrid('options');
//			options3.required = false;
//			$("#owner_name").combogrid(options3);
			
			$('#allocationState').combobox('enable');
			
		}else if(newValue==2){
			
			$("#supplier_name").combogrid('enable');
//			var options = $("#supplier_name").combogrid('options');
//			options.required = true;
//			$("#supplier_name").combogrid(options);
			
			
			$("#department_name").combogrid('enable');
//			var options = $("#department_name").combogrid('options');
//			options.required = true;
//			$("#department_name").combogrid(options);
			
			
			$("#owner_name").combogrid('enable');
//			var options = $("#owner_name").combogrid('options');
//			options.required = true;
//			$("#owner_name").combogrid(options);
			
			$('#allocationState').combobox('disable').combobox('setValue',1);
		}else{

			$("#supplier_name").combogrid('enable');
//			var options =$("#supplier_name").combogrid('options');
//			options.required = true;
//			$("#supplier_name").combogrid(options);
			
			
			$("#department_name").combogrid('enable');
//			var options =$("#department_name").combogrid('options');
//			options.required = false;
//			$("#department_name").combogrid(options);
			
			
			$("#owner_name").combogrid('setValue','').combogrid('disable');
//			var options =$("#owner_name").combogrid('options');
//			options.required = false;
//			$("#owner_name").combogrid(options);
			
			$('#allocationState').combobox('enable');
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

    	//反写一级分类，加载产品
//        this.invokeService("queryByProductCategoryId2", [record.id], function (data) {
//        	
//
//        });
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
