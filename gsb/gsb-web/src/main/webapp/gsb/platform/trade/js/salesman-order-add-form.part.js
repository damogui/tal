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
    	this.bindAccount(mobile);
    },
    bindAccount:function(mobile){
    	
    	var me = this;
        this.invokeService("getAccount", [mobile], function (data) {
        	
        	var account = data;
        	if(account){
        		
        		//会员名称
        		$('#accountName').val(account.realName);
        		
        		//会员邮箱
        		$('#email').val(account.email);
        		
        		//客户等级
        		$('#important').combobox('setValue',account.important);
        		
        		//会员Id
        		me.currentItem.accountId = account.id;
        		
        		//关联企业
        		if(account.company){

            		$('#companyIntention_companyName').combogrid('setValue',account.companyId);
            		$('#companyIntention_companyName').combogrid('setText',account.company.companyName);
        		}
        		
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
    },
    addExtraProp:function(entity){

    },
    added: function (currentItem) {

    	var taskId = this.queryString('taskId') || null;
    	if(taskId){
    	
    		currentItem.taskId = taskId; 
    	}
    	
    	var customerId = this.queryString('customerId') || null;
    	if(customerId){
        	
    		currentItem.customerId = customerId; 
    	}
    	
    	var accountId = this.queryString('accountId') || null;
    	if(accountId){
        	
    		currentItem.accountId = accountId;
    	}
    	
    	var mobile = this.queryString('mobile') || null;
    	if(mobile){
        	
    		currentItem.accountMobile = mobile;
    		this.bindAccount(mobile);
    	}
    	
    },
    onSaving: function (entity) {

        return true;
    }
});


com.gongsibao.trade.web.OrderProdItemDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
    ctor: function () {
    	
        this.base();
    },
    doubleClickRow:function(){
    	
    	//覆盖
    },
    add:function(){
    	
    	var me = this;
    	var selectCtrl = new com.gongsibao.trade.web.SelectServiceItemCtrl();
    	selectCtrl.parentCtrl = this;
    	selectCtrl.show(function(orderProd){

        	me.setEditor();
    		$('#datagridproducts').datagrid('appendRow',orderProd);
        	me.calculateTotalPrice();
        	
    	});
    },
    setEditor:function(){
    	
    	//设置数量可编辑
    	var me = this;
    	var options = $('#datagridproducts').datagrid('options');
    	var columns = options.columns[0];
    	var hasEditor = false;
    	$(columns).each(function(i,col){
    		
    		if(col.field == 'quantity'){

    			var editor = col.editor;
    			if(editor != undefined){
    				
    				hasEditor = true;
    			}else{
    			
    				col.editor = {type:'numberbox',options:{precision:0,height:31,min:1,required:true}}
    			}
    		}
    	});
    	if(!hasEditor){
    		
    		options.onBeginEdit=function(index, row){

			     var ed = $(this).datagrid('getEditor', {index:index,field:'quantity'});
			     var quantityEditCtrl = $(ed.target[0]).next().children()[0];
			     $(quantityEditCtrl).bind('blur',function(){
			    	 
			    	 //结束编辑
			    	 $('#datagridproducts').datagrid('endEdit',index);
			    	 me.calculateTotalPrice();
			     });
			}
    		$('#datagridproducts').datagrid(options);
    		$('#datagridproducts').datagrid('enableCellEditing');
    	}
    },
    calculateTotalPrice:function(){
    	
    	var rows = $('#datagridproducts').datagrid('getRows');
    	var totalPayablePrice = 0;
    	var totalPrice = 0;
    	$(rows).each(function(i,row){
    		
    		var quantity = row.quantity;
    		
    		var items = row.items;
    		$(items).each(function(j,item){

        		totalPayablePrice += parseInt(item.price*quantity);
        		totalPrice += parseInt(item.priceOriginal*quantity);
    		});
    		
    	});
    	totalPrice = System.RMB.fenToYuan(totalPrice);
    	totalPayablePrice = System.RMB.fenToYuan(totalPayablePrice);
    	$('#totalPrice').numberbox('setValue',totalPrice);
    	$('#payablePrice').numberbox('setValue',totalPayablePrice);
    },
    serviceNameFormatter:function(value,row,index){
    	
    	var items = row.items;
    	if(items.length==1){
    		
    		return items[0].serviceName;
    	}else{

        	var str = '';
        	$(items).each(function(i,item){
        		
        		str+='<p>'+item.serviceName+'</p>';
        	});
        	return str;
    	}
    },
    unitNameFormatter:function(value,row,index){
    	
    	var items = row.items;
    	if(items.length==1){
    		
    		return items[0].unitName;
    	}else{

        	var str = '';
        	$(items).each(function(i,item){
        		
        		str+='<p>'+item.unitName+'</p>';
        	});
        	return str;
    	}
    },
    priceOriginalFormatter:function(value,row,index){
    	
    	var items = row.items;
    	if(items.length==1){
    		
    		return System.RMB.fenToYuan(items[0].priceOriginal);
    	}else{

        	var str = '';
        	$(items).each(function(i,item){
        		
        		str+='<p>'+System.RMB.fenToYuan(item.priceOriginal)+'</p>';
        	});
        	return str;
    	}
    },
    priceFormatter:function(value,row,index){
    	
    	var items = row.items;
    	if(items.length==1){
    		
    		return System.RMB.fenToYuan(items[0].price);
    	}else{

        	var str = '';
        	$(items).each(function(i,item){
        		
        		str+='<p>'+System.RMB.fenToYuan(item.price)+'</p>';
        	});
        	return str;
    	}
    }
});


com.gongsibao.trade.web.SelectServiceItemCtrl = System.Object.Extends({
    ctor: function () {
    	
    	this.parentCtrl = null;
    },
    show: function(callback) {
    	
    	var formId = System.GUID.newGUID();
    	var builder = new System.StringBuilder();
    	builder.append('<form id="dynamicForm">');
    	builder.append('<div style="margin:10px;">');
    	builder.append('<table cellpadding="5" cellspacing="10" class="form-panel" style="width:100%;">');
    	builder.append('<tr><td class="title" style="width:100px;text-align: right;"><label style="color:Red">*</label> 产品名称</td><td><input id="product"/></td></tr>');
    	builder.append('<tr><td class="title" style="width:100px;text-align: right;"><label style="color:Red">*</label> 产品地区</td><td><input id="province"/> <input id="city"/> <input id="county"/></td></tr>');
    	builder.append('<tr><td class="title" style="width:100px;text-align: right;"><label style="color:Red">*</label> 服务项目</td><td><table id="serviceItems"></table></td></tr>');
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
    			
    			me.initializeCtrl();
    		},
            yes: function (index, layero) {
            	
            	var orderProd = me.getOrderProd();
            	if(orderProd != null){

                	callback(orderProd);
                	layer.closeAll();
            	}
            }
    	});
    },
    getOrderProd:function(){
    	
    	//构建订单产品明细
    	
    	//校验未做
    	
    	var rows = $('#serviceItems').datagrid('getChecked');
    	if(rows== null || rows.length == 0){
    		
    		layer.msg('请选择服务项目！');
    		return null;
    	}
    	var productId = $('#product').combogrid('getValue');
    	var productName = $('#product').combogrid('getText');
    	var cityId = $('#county').combobox('getValue');
    	var cityName = $('#province').combobox('getText')+'-'+$('#city').combobox('getText')+'-'+$('#county').combobox('getText');
    	var orderProd = {};
    	orderProd.productId = productId;
    	orderProd.productName = productName;
    	orderProd.cityId = cityId;
    	orderProd.price = 0;
    	orderProd.priceOriginal = 0;
    	orderProd.cityName = cityName;
    	orderProd.quantity = 1;
    	orderProd.items = [];
    	$(rows).each(function(i,item){

        	var orderProdItem = {};
        	
        	//单位名称
        	orderProdItem.unitName = item.service.unit.name;
        	
        	//价格Id
        	orderProdItem.priceId = item.id;
        	
        	//服务名称
    		var serviceName = item.service.type.name;
    		if(item.service.property){
    			
    			serviceName = item.service.property.name+'-'+serviceName;
    		}
        	orderProdItem.serviceName = serviceName;
        	orderProdItem.quantity = 1;
        	orderProdItem.priceOriginal = item.originalPrice;
        	orderProdItem.price =  item.price;
        	
        	orderProd.priceOriginal = orderProd.priceOriginal + item.originalPrice;
        	orderProd.price = orderProd.price + item.price;
        	orderProd.items.push(orderProdItem);
    	});
    	return orderProd;
    },
    initializeCtrl:function(){
    	
    	var me = this;
    	var maxWidth = $('#dynamicForm').width()-180;
    	//初始化产品
		$("#product").combogrid({columns : [ [ {
			field : 'name',
			title : '名称',
			width : 100
		}] ],
		url : '\/panda\/rest\/reference?code=CRM_Product&filter=',
		idField : 'id',
		textField : 'name',
		width : maxWidth,
		fitColumns : true,
		panelWidth : maxWidth,
		panelHeight : 310,
		pagination : true,
		pageSize : 10,
		mode : 'remote',
		multiple : false,
		onChange : function(newValue, oldValue) {
			
	    	var id = parseInt(newValue);
			if(System.isnull(newValue) || typeof id != 'number' || isNaN(id)){
				
				return;
			}
			me.bindProvince(newValue,null,"province");
		}});
		
		var options = null;
		//省份
		options = {width:114,valueField : 'id',textField : 'name',onSelect : function(record) {

			me.clearPcc('city');
			me.bindPcc('city',record.items);
		}};
		$("#province").combobox(options);
		
		//城市
		options = {width:114,valueField : 'id',textField : 'name',onSelect : function(record) {

			me.clearPcc('county');
			me.bindPcc('county',record.items);
		}};
		$("#city").combobox(options);
		
		//地区
		options = {width:114,valueField : 'id',textField : 'name',onSelect : function(record) {
			
			//绑定服务项目
			var productId = $('#product').combogrid('getValue');
			if(productId){

				var cityId = record.id;
				me.bindService(productId,cityId);
			}
		}};
		$("#county").combobox(options);
		
		this.initializeGrid();
    },
    initializeGrid:function(){
    	
    	var me = this;
    	var maxWidth = $('#dynamicForm').width()-180;
    	var maxHeight = $('#dynamicForm').parent().height()-150;
		//服务项目
		$('#serviceItems').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			width:maxWidth,
			height:maxHeight,
			onBeginEdit:function(index, row){

			     var ed = $(this).datagrid('getEditor', {index:index,field:'price'});
			     var price = System.RMB.fenToYuan(row.price);
			     $(ed.target).numberbox('setValue', price);

			     var priceEditCtrl = $(ed.target[0]).next().children()[0];
			     $(priceEditCtrl).bind('blur',function(){
			    	 
			    	 var value = System.RMB.yuanToFen($(this).val());
			    	 $(ed.target).numberbox('setValue', value);
			    	 //结束编辑
			    	 $('#serviceItems').datagrid('endEdit',index);
			    	 me.calculateTotalPrice();
			     });
			},
			onUncheck:function(index, row){
				
				me.calculateTotalPrice();
			},
			onBeforeCheck:function(index, row){

				return true;
				//内资公司注册
			},
			onCheck:function(index, row){
				
				var rows =  me.getRows();
				var propertyId = row.service.propertyId;
				var hasProperty = !System.isnull(propertyId) && propertyId>0;

				//1.验证同一属性下，如果是必选
				//1.1：如果当前行是唯一选中项，则return false
				//1.2：如果有同属性其它选中项，则取消其它选中项
				if(hasProperty){
					
					var checkedRows = $('#serviceItems').datagrid('getChecked');
					$(checkedRows).each(function(i,item){

						if(item.service.typeId == row.service.typeId && item.id != row.id ){
							
			        		var name = item.service.property.name+'-'+item.service.type.name;
							var rowIndex = $('#serviceItems').datagrid('getRowIndex',item);
							$('#serviceItems').datagrid('uncheckRow',rowIndex);
						}
					})
				}
				
				$('#serviceItems').datagrid('highlightRow',index);
				me.calculateTotalPrice();
			},
			onBeforeUncheck:function(index, row){

				var typeId = row.service.typeId;
				var propertyId = row.service.propertyId;
				var hasProperty = !System.isnull(propertyId) && propertyId>0;

				//1.验证同一属性下
				if(hasProperty){

					var checkedRows = $('#serviceItems').datagrid('getChecked');
					//1.1必选：并且当前行是唯一选中项，则return false
					if(row.necessary === true){
						
						var isCheck = false;
						$(checkedRows).each(function(i,item){
							
							if(item.id != row.id && item.service.typeId==typeId){
								
				        		var name = row.service.type.name;
				        		if(row.service.property){
				        			
				        			name = row.service.property.name+'-'+name;
				        		}
				        		console.log("可以取消："+name);
				        		
								isCheck = true;
								return;
							}
						})
						return isCheck;
					}else{

						//1.2非必选:return true
						return true;
					}
				}
				//2.如果是必选项，无多属性时return false;
				else if(row.necessary === true){
					
					return false;
				}
				return true;
			},
			onLoadSuccess:function(data){
				
				//隐藏全选
				$(this).parent().find('.datagrid-header-check').children().hide();

				$('#serviceItems').datagrid('enableCellEditing');

				 //设置必选项
				 $(data.rows).each(function(i,item){

					 if(item.necessary === true){

						 me.checkRow(item,i);
					 }
				 });
			},
		    columns:[[
		        {field:'id',checkbox:true},
		        {field:'name',title:'服务名称',width:200,formatter: function(value,row,index){
		        	
		        	if(row.service && row.service.type){
		        		
		        		var name = row.service.type.name;
		        		if(row.service.property){
		        			
		        			name = row.service.property.name+'-'+name;
		        		}
		        		return name;
		        	}
		        }},
		        {field:'unit',title:'单位',width:50,align:'center',formatter: function(value,row,index){
		        	
		        	if(row.service && row.service.unit){
		        		
		        		return row.service.unit.name;
		        	}
		        	return value;
		        }},    
		        {field:'originalPrice',title:'原售价',width:100,align:'right',formatter:function(value,row,index){
		        	
		        	return System.RMB.fenToYuan(value);
		        }},
		        {field:'price',title:'现售价',width:100,align:'right',editor:{type:'numberbox',options:{precision:2,height:31}},
		        	formatter:function(value,row,index){
		        		
		        		return System.RMB.fenToYuan(value);
		        	}}
		    ]]
		});
    },
    getRows:function(){
    	
    	var rows =  $('#serviceItems').datagrid('getRows');
    	return rows;
    },
    checkRow:function(checkeditem,index){
    	
    	 var typeId = checkeditem.service.typeId;
    	 var propertyId = checkeditem.service.propertyId;
    	 
		 //设置必选项
    	 if(!System.isnull(propertyId) && propertyId>0){

    		 var isChecked = true;
    		 var checkedRows = $('#serviceItems').datagrid('getChecked');
    		 $(checkedRows).each(function(i,item){

    			 if(item.id != checkeditem.id && item.service.typeId==typeId){
    				 
    				 isChecked = false;
    			 }
    		 });
    		 
    		 if(isChecked){

        		 $('#serviceItems').datagrid('checkRow',index);
    		 }
    		 
    	 }else{
    		 
    		 $('#serviceItems').datagrid('checkRow',index);
    	 }
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
    	this.initializeGrid();
    },
    clearPcc:function(ctrlId){
    	
		 $('#' + ctrlId).combobox('setValue',null);
		 $('#serviceItems').datagrid('loadData',[]);
    },
    bindPcc:function(ctrlId,items){

		 if(items){

			 $('#' + ctrlId).combobox('loadData',items);
			 if(items.length == 1){
				 
				 $('#' + ctrlId).combobox('select',items[0].id);
			 }
		 }
    },
    calculateTotalPrice:function(){
    	
    	var rows = $('#serviceItems').datagrid('getChecked');
    	var totalPrice = 0;
    	var totalOriginalPrice = 0;
    	$(rows).each(function(i,item){
    		
    		totalPrice+=parseInt(item.price);
    		totalOriginalPrice+=parseInt(item.originalPrice);
    	});
    	
    	var rows = $('#serviceItems').datagrid('getFooterRows');
    	rows[0]['originalPrice'] =totalOriginalPrice;
    	rows[0]['price'] = totalPrice;
    	$('#serviceItems').datagrid('reloadFooter',rows);

    },
    bindService:function(productId,cityId){
    	
    	var me = this;
		this.invokeService('queryServicePriceItem', [productId,cityId], function(rows){

			 var data = {};
			 data.footer =[{"unit":'合计：',"originalPrice":0,"price":0}];
			 data.rows = rows;
			 
			 me.initializeGrid();
			 $('#serviceItems').datagrid('loadData',data);
			 
	    });
    },
    invokeService: function (method, pars, callback, isAsyn, errorCallback) {

        var serviceLocator = new org.netsharp.core.JServiceLocator();
        var me = this;
        var thisCallback = function (data) {
        	
            if (!System.isnull(callback)) {
            	
                callback(data);
            }
        };
        serviceLocator.invoke(this.parentCtrl.context.service, method, pars, thisCallback, this.parentCtrl.context.vid, isAsyn, errorCallback);
    },
});