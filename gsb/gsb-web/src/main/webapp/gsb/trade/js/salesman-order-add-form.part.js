System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.SalesmanAddOrderFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    accountMobileChange:function(el){
    	
    	var me = this;
    	var mobile = $(el).val().trim();
    	
    	//1.校验手机号格式
    	if(System.isnull(mobile)){
    		
    		me.clearAccount();
    		return false;
    	}
    	
    	if(!/^(1[0-9])\d{9}$/.test(mobile)){
    		
    		IMessageBox.toast('【手机】格式错误',2);
    		me.clearAccount();
    		return false;
    	}
    	
    	//2.验证是否已开户
        this.invokeService("getAccount", [mobile], function (data) {
        	
        	var account = data;
        	if(account){
        		
        		//会员名称
        		$('#accountName').val(account.name);
        		
        		//会员邮箱
        		$('#email').val(account.email);
        		
        		//会员Id
        		me.currentItem.accountId = account.id;
        	}else{
        		
        		IMessageBox.toast('会员不存在',2);
        		me.clearAccount();
        	}
        });
    },
    clearAccount:function(){
    	
    	$('#accountName').val('');
    	$('#email').val('');
    	this.currentItem.accountId = null;
    }
});

com.gongsibao.trade.web.OrderProdItemDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
    ctor: function () {
    	
        this.base();
    },
    add: function() {
    	
    	var formId = System.GUID.newGUID();
    	var builder = new System.StringBuilder();
    	builder.append('<form id="dynamicForm">');
    	builder.append('<div style="margin:10px;">');
    	builder.append('<table cellpadding="5" cellspacing="10" class="form-panel" style="width:100%;">');
    	builder.append('<tr><td class="title" style="width:100px;"><label style="color:Red">*</label> 产品名称</td><td><input id="product"/></td></tr>');
    	builder.append('<tr><td class="title" style="width:100px;"><label style="color:Red">*</label> 产品地区</td><td><input id="province"/> <input id="city"/> <input id="county"/></td></tr>');
    	builder.append('<tr><td class="title" style="width:100px;"></td><td><table id="serviceItems"></table></td></tr>');
    	builder.append('</table>');
    	builder.append('</div>');
    	builder.append('</form>');
    	
    	var me = this;
    	layer.open({
    		type : 1,
    		title : '添加产品',
    		fixed : false,
    		maxmin : false,
    		shadeClose : true,
    		zIndex : 100000,
    		area : [ '50%','70%' ],
    		content : builder.toString(),
    		btn : [ '提交', '取消' ],
    		success : function(layero, index) {
    			
    			var productOptions = me.getProductOption();
    			$("#product").combogrid(productOptions);
    			
    			var options = null;
    			options = {width:114,valueField : 'id',textField : 'name',onSelect : function(record) {

					me.clearPcc('city');
					me.bindPcc('city',record.items);
        		}};
    			$("#province").combobox(options);
    			
    			options = {width:114,valueField : 'id',textField : 'name',onSelect : function(record) {

					me.clearPcc('county');
    				me.bindPcc('county',record.items);
    			}};
    			$("#city").combobox(options);
    			
    			options = {width:114,valueField : 'id',textField : 'name',onSelect : function(record) {
        			
    				//绑定服务项目
    				var productId = $('#product').combogrid('getValue');
    				if(productId){

        				var cityId = record.id;
        				me.bindService(productId,cityId);
    				}
    			}};
    			$("#county").combobox(options);
    			
    		}
    	});
    },
    getProductOption:function (){
    	
    	var me = this;
    	var departmentOption = {columns : [ [ {
    			field : 'name',
    			title : '名称',
    			width : 100
    		}] ],
    		url : '\/panda\/rest\/reference?code=CRM_Product&filter=',
    		idField : 'id',
    		textField : 'name',
    		width : 350,
    		fitColumns : true,
    		panelWidth : 450,
    		panelHeight : 310,
    		pagination : true,
    		pageSize : 10,
    		mode : 'remote',
    		multiple : false,
    		onChange : function(newValue, oldValue) {
    			
    			me.bindProvince(newValue,null,"province");
    		}};
    	return departmentOption;
    },
    bindProvince:function(productId,parentId,ctrlId){
    	
    	var me = this;
		this.invokeService('queryPcc', [productId], function(data){
		 
			 if(System.isnull(data) || data.length==0){
				 return;
			 }
			 me.clearAllPcc();
			 me.bindPcc('province',data);
	   });
    },
    clearAllPcc:function(){
    	
    	this.clearPcc('province');
    	this.clearPcc('city');
    	this.clearPcc('county');
    },
    clearPcc:function(ctrlId){
    	
		 $('#' + ctrlId).combobox('setValue',null);
		 
    },
    bindPcc:function(ctrlId,items){

		 if(items){

			 $('#' + ctrlId).combobox('loadData',items);
			 if(items.length == 1){
				 
				 $('#' + ctrlId).combobox('select',items[0].id);
			 }
		 }
    },
    bindService:function(productId,cityId){
    	
		this.invokeService('queryServicePriceItem', [productId,cityId], function(data){
			 
			 if(System.isnull(data) || data.length==0){
				 return;
			 }

	    });
    }
});