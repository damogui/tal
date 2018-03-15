System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderRefundCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderRefundController';
    },
    init:function(){
		
		$('#detail_tabs').tabs({
			fit:true,
			tabHeight:35
		});
		
		this.bindSetOfBooks();
		
		this.productDetailCtrl = new com.gongsibao.trade.web.OrderProductDetailCtrl();
		this.productDetailCtrl.init();
    	
    	this.refundPerformanceCtrl = new com.gongsibao.trade.web.OrderRefundPerformanceCtrl();
    	this.refundPerformanceCtrl.init();
    },
    bindSetOfBooks:function(){
    	
    	this.invokeService ("querySetOfBooksList", [], function(data){
			
    		$('#setOfBooksId').combobox('loadData',data);
		});
    },
    refundTypeChange:function(newValue,oldValue){
    	
    	//全款退
    	if(newValue==='1'){

    		//这里有很多种情况判断，如：结转，已存在退款
    		var paidPrice = parseFloat($('#paidPrice').text());
    		if(paidPrice>0){
    			
    			$('#amount').numberbox('setValue',paidPrice).numberbox('readonly',true);
    			
    			//如果表格只有一行，则直接设置退款金额
    			var refundAmount = paidPrice*100;
    			this.productDetailCtrl.setRefundAmount(refundAmount);
    		}else{
    			
    			$('#amount').numberbox('readonly',false);
    		}
    	}
    },
    save:function(){

    	//还没有做校验 hw 2018-03-13
    	
    	var orderId = this.queryString('id');
    	var refund = new Object();
    	refund.orderId = orderId;
    	refund.setOfBooksId = $('#setOfBooksId').combogrid('getValue');
    	refund.refundType =  $('#refundType').combobox('getValue');
    	refund.payerName =  $('#payerName').val();
    	refund.bankNo =  $('#bankNo').val();
    	refund.amount =  parseFloat($('#amount').numberbox('getValue'))*100;
    	refund.remark =  $('#remark').val();
    	alert($('#remark').val());
    	//退款产品
    	var refundProductRows = $('#order_product_grid').datagrid('getRows');
    	var itemList = [];
    	for(var i=0;i<refundProductRows.length;i++){
    		
    		var prod = refundProductRows[i];
    		if(!System.isnull(prod.refundAmount) && parseFloat(prod.refundAmount) >0){

        		var item = new Object();
        		item.orderId = orderId;
        		item.orderProdId = prod.id;
        		item.amount = prod.refundAmount;
        		itemList.push(item);
    		}
    	}
    	refund.refunds = itemList;
    	
    	//退款业绩分配
    	var depRefunds = $('#order_refund_grid').datagrid('getRows');
    	refund.depRefunds = depRefunds;
    	
    	var me = this;
    	IMessageBox.confirm('确定提交申请吗？',function(r){
    		
    		if(r){

    			me.invokeService("applyRefund", [refund], function(data){
    				IMessageBox.info('申请成功，请等待审核!',function(s){
    	    			window.parent.layer.closeAll();
    	    		});
    	    	});
    		}
    	});
    }
});

com.gongsibao.trade.web.OrderProductDetailCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {

    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderRefundController';
    	this.processStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderProcessStatusType');
    },
    init:function(){

    	var me = this;
    	var orderId = this.queryString('id');
    	this.invokeService ("queryProductList", [orderId], function(data){
    		
    		me.initGrid(data);
    	});
    },
	initGrid:function(data){
	    
		var me = this;
		$('#order_product_grid').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			singleSelect:true,
			height:'100%',
			data:data,
		    columns:[[
	//	        {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
	//	        	
	//	        	return '<a class="grid-btn" href="javascript:;">查看</a>';
	//	        }},
		        {field:'id',title:'订单明细编号',width:100,align:'center'},
		        {field:'productName',title:'产品名称',width:150},
		        {field:'serviceName',title:'服务名称',width:200,formatter:function(value,row,index){
	
		        	var items = row.items;
		        	if(items && items.length > 0){
		        		
		        		var len = items.length;
		        		if(len==1){
		        			return items[0].serviceName;
		        		}else{
		        			
		        			var serviceName = items[0].serviceName + '...';
		        			var ss = [];
		        			$(items).each(function(i,item){
		        				
		        				ss.push(item.serviceName);
		        			});
		        			var fullServiceName = ss.join(',');
		        			var tipId = 'tip' + row.id;
				        	return '<a id="'+tipId+'" onmouseover="layer.tips(\''+fullServiceName+'\',\'#'+tipId+'\',{tips: [1, \'#1E7BB6\']})">'+serviceName+'</a>';
		        		}
		        	}
		        }},
		        {field:'cityName',title:'产品地区',width:150},   
		        {field:'priceOriginal',title:'原价',width:100,align:'right',formatter:function(value,row,index){
		        	return (value/100).toFixed(2);
		        }},
		        {field:'price',title:'售价',width:100,align:'right',formatter:function(value,row,index){
		        		
		        	return (value/100).toFixed(2);
		        }},
		        {field:'refundAmount',title:'退款金额',width:100,align:'right',editor:{type:'numberbox',options:{precision:0,height:31,min:1,required:true}},formatter:function(value,row,index){
	        		
		        	if(value){
	
			        	return (value/100).toFixed(2);
		        	}
		        }},
		        		        
		        
		        {field:'processStatusId',title:'办理进度',width:100,align:'center',formatter:function(value,row,index){
	        		
	        		if(value){
	        		
	        			return me.processStatusEnum[value];
	        		}
	        		return '-';
		        }},
		        {field:'ownerId',title:'业务员',width:80,align:'center',formatter:function(value,row,index){
	        		
	        		if(row.owner){
	        			
	        			return row.owner.name;
	        		}else {
	        			
	        			return '-';
	        		}
		        }}
		    ]],
		    onBeginEdit:function(index, row){
	
			     var ed = $(this).datagrid('getEditor', {index:index,field:'refundAmount'});
			     var refundType = $('#refundType').combobox('getValue');
			     if(refundType==='1'){
			    	 $('#order_product_grid').datagrid('endEdit',index);
			    	 return false;
			     }else{
			    	 
			    	 var refundAmount = row.refundAmount/100;
			    	 $(ed.target).numberbox('setValue',refundAmount);
				     var editCtrl = $(ed.target[0]).next().children()[0];
				     $(editCtrl).bind('blur',function(){
				    	 
				    	 //结束编辑
				    	 $('#order_product_grid').datagrid('endEdit',index);
				     });
				     
			     }
			},
			onEndEdit:function(index,row,changes){
				
			     var refundType = $('#refundType').combobox('getValue');
			     if(refundType==='1'){
			    	 
			     }else{

					var ed = $(this).datagrid('getEditor', {index:index,field:'refundAmount'});
					var refundAmount = $(ed.target).numberbox('getValue');
					row.refundAmount = parseFloat(refundAmount)*100;
			     }
			}
		});
		$('#order_product_grid').datagrid('enableCellEditing');
	},
	setRefundAmount:function(refundAmount){
		
		var rows = $('#order_product_grid').datagrid('getRows');
		var len = rows.length;
		if(len==1){
			
			rows[0].refundAmount = refundAmount;
			$('#order_product_grid').datagrid('loadData',rows);
		}
	}
});


com.gongsibao.trade.web.OrderRefundPerformanceCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {

    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderRefundController';
    },
    init:function(){

    	this.initGrid();
    },
	initGrid:function(){
		
		var me = this;
		$('#order_refund_grid').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			singleSelect:true,
			height:'100%',
			toolbar: [{
				text: '新增',
				handler: function(){
				
					me.add();
				}
			},'-',{
				text: '删除',
				handler: function(){

					me.remove();
				}
			}],
		    columns:[[
		        {field:'supplierId',title:'服务商',align:'center',width:150,formatter:function(value,row,index){
	
		        	if(row.supplier){
		        		
		        		return row.supplier.name;
		        	}
		        }},
		        {field:'departmentId',title:'部门',align:'center',width:150,formatter:function(value,row,index){
		        	
		        	if(row.department){
		        		
		        		return row.department.name;
		        	}
		        }},
		        {field:'salesmanId',title:'业务员',align:'center',width:100,formatter:function(value,row,index){
		        	
		        	if(row.salesman){
		        		
		        		return row.salesman.name;
		        	}
		        }},
		        {field:'amount',title:'退款业绩分配金额',width:150,align:'right',formatter:function(value,row,index){
		        		
		        	return (value/100).toFixed(2);
		        }}
		    ]]
		});
	},
	add:function(){
		
        var me = this;
        var supplierOption = getSupplierOption();
        var departmentOption = getDepartmentOption();
        var employeeOption = getEmployeeOption();
        PandaHelper.openDynamicForm({
            title:'退款业绩分配',
            width:450,
            height:330,
            items:[{id:'allot_supplier_name',
                title:'服务商',
                type:'combogrid',
                className:'',
                option:supplierOption},

                {id:'allot_department_name',
                    title:'部门',
                    type:'combogrid',
                    className:'',
                    option:departmentOption},

                {id:'allot_salesman_name',
                    title:'业务员',
                    type:'combogrid',
                    className:'',
                    option:employeeOption},
                {id:'allot_amount',
                    title:'分配金额',
                    type:'numberbox',
                    className:'',
                    option:{width:300,precision:2,min:1,required:true}}
            ],
            callback:function(index, layero){

                var supplierId = $('#allot_supplier_name').combogrid('getValue');
                var departmentId = $('#allot_department_name').combogrid('getValue');
                var salesmanId = $('#allot_salesman_name').combogrid('getValue');
                var amount = $('#allot_amount').numberbox('getValue');
                
                if (System.isnull(supplierId)) {
                    IMessageBox.info('请选择服务商');
                    return;
                }
                if (System.isnull(departmentId)) {
                    IMessageBox.info('请选择部门');
                    return;
                }
                if (System.isnull(salesmanId)) {
                    IMessageBox.info('请选择业务员');
                    return;
                }

                if (System.isnull(amount)) {
                    IMessageBox.info('请填写分配金额');
                    return;
                }
                
                //这里要校验金额之和不能大于退款金额
                var depRefund = new Object();
                depRefund.supplierId = supplierId;
                var supplierName = $('#allot_supplier_name').combogrid('getText');
                depRefund.supplier = {
                	id:supplierId,
                	name:supplierName
                };
                
                depRefund.departmentId = departmentId;
                var departmentName = $('#allot_department_name').combogrid('getText');
                depRefund.department = {
                    	id:departmentId,
                    	name:departmentName
                };
                
                depRefund.salesmanId = salesmanId;
                var salesmanName = $('#allot_salesman_name').combogrid('getText');
                depRefund.salesman = {
                    	id:salesmanId,
                    	name:salesmanName
                };
                
                depRefund.amount = parseFloat(amount)*100;
                me.appendRow(depRefund);
                
                //关闭当前窗口
                layer.close(index);
            }
        });
	},
	appendRow:function(row){
		var orderId = this.queryString('id');
		row.orderId = orderId;
		$('#order_refund_grid').datagrid('appendRow',row);
	},
	remove:function(){
		
		var row = $('#order_refund_grid').datagrid('getSelected');
		if(row == null){
			
			return;
		}
		
		//提示确认
		
		var index = $('#order_refund_grid').datagrid('getRowIndex',row);
		$('#order_refund_grid').datagrid('deleteRow',index);
	}
});

//这些统一一个类处理
function getSupplierOption(){
    var supplierOption = {columns : [ [ {
        field : 'name',
        title : '名称',
        width : 100
    }] ],
        url : '\/panda\/rest\/reference?code=CRM_Supplier&filter=',
        idField : 'id',
        textField : 'name',
        width : 300,
        fitColumns : true,
        panelWidth : 450,
        panelHeight : 310,
        pagination : true,
        pageSize : 10,
        mode : 'remote',
        multiple : false,
        onChange : function(newValue, oldValue) {
            //改变部门的查询条件
            $('#allot_department_name').combogrid('clear');
            var grid = $('#allot_department_name').combogrid('grid');
            var options = $(grid).datagrid('options');
            var filter = ' supplier_id ____ ----'+ newValue + '----';
            options.url = '\/panda\/rest\/reference?code=CRM_Supplier_Depart&filter='+ filter;
            $(grid).datagrid(options);
            //改变业务员的查询条件
            $('#allot_salesman_name').combogrid('clear');
            var grid = $('#allot_salesman_name').combogrid('grid');
            var options = $(grid).datagrid('options');
            //var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE supplier_id ____ ----'+ newValue + '----)';
            var filter = ' supplier_id ____ ----'+ newValue + '----';
            options.url = '\/panda\/rest\/reference?code=Salesman&filter='+ filter;
            $(grid).datagrid(options);

        }};

    return supplierOption;
}

function getDepartmentOption(){
    var departmentOption = {columns : [ [ {
        field : 'name',
        title : '名称',
        width : 100
    }] ],
        url : '\/panda\/rest\/reference?code=CRM_Supplier_Depart&filter=',
        idField : 'id',
        textField : 'name',
        width : 300,
        fitColumns : true,
        panelWidth : 450,
        panelHeight : 310,
        pagination : true,
        pageSize : 10,
        mode : 'remote',
        multiple : false,
        onChange : function(newValue, oldValue) {
            //改变业务员的查询条件
            $('#allot_salesman_name').combogrid('clear');
            var grid = $('#allot_salesman_name').combogrid('grid');
            var options = $(grid).datagrid('options');
            //var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE department_id ____ ----'+ newValue + '----)';
            var filter = ' department_id ____ ----'+ newValue + '----';
            options.url = '\/panda\/rest\/reference?code=Salesman&filter='+ filter;
            $(grid).datagrid(options);
        }};
    return departmentOption;
}

function getEmployeeOption(){
    var employeeOption = {columns : [ [ {
        field : 'employee_name',
        title : '名称',
        width : 100
    }] ],
        rowStyler: function(index,row){if(row.receiving ===false) {return 'color:red;';  }},
        url : '\/panda\/rest\/reference?code=Salesman&filter=',
        idField : 'employeeId',
        textField : 'employee_name',
        width : 300,
        fitColumns : true,
        panelWidth : 450,
        panelHeight : 310,
        pagination : true,
        pageSize : 10,
        mode : 'remote',
        multiple : false,
        onChange : function(newValue, oldValue) {
            /*if(oldValue!="" && newValue != oldValue){
             }*/
        }};

    return employeeOption;
}