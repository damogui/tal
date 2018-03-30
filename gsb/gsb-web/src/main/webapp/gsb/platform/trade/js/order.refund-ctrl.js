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
    amountChange:function(newValue,oldValue){
    	//验证 退款总额是否≤订单可退款额
    	var orderId = this.queryString('id');
    	this.invokeService ("getSoOrder", [orderId], function(data){
    		var paidPrice = data.paidPrice;
      		var refundPrice = data.refundPrice;
      		var carryAmount = data.carryAmount;
      		var getFinals = paidPrice - refundPrice - carryAmount;
      		var getNewValue = parseFloat(newValue)*100;
        	if((getFinals - getNewValue) < 0){
        		$('#amount').numberbox('clear');
        		layer.msg('退款业绩总额不等于退款总额，请核实');
        	}
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
    	}else{
    		$('#amount').numberbox('readonly',false);
    		$('#amount').numberbox('clear');
    	}
    },
    save:function(){

    	var booksId = $('#setOfBooksId').combogrid('getValue');
    	var refundType = $('#refundType').combobox('getValue');
    	var payerName = $('#payerName').val();
    	var bankNo = $('#bankNo').val();
    	var amount = parseFloat($('#amount').numberbox('getValue'))*100;
    	var refundRemark = $('#refundRemark').val();
    	//验证
    	if(isEmpty(booksId) || isEmpty(refundType) || isEmpty(payerName) || isEmpty(bankNo) || isNaN(amount) || isEmpty(refundRemark)){
    		layer.msg('请输入必填项');
    		return false;
    	}
    	
    	var orderId = this.queryString('id');
    	var refund = new Object();
    	refund.orderId = orderId;
    	refund.setOfBooksId = booksId;
    	refund.refundType = refundType;
    	refund.payerName = payerName;
    	refund.bankNo = bankNo;
    	refund.amount = amount;
    	refund.remark = refundRemark;
    	
    	
    	//退款产品
    	var refundProductRows = this.productDetailCtrl.getProductRows();
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
    	var depRefunds = this.refundPerformanceCtrl.getDepRefundRows();
    	
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
    	this.$gridId = '#order_product_grid';
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
		$(this.$gridId ).datagrid({
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
			    	 $(me.$gridId).datagrid('endEdit',index);
			    	 return false;
			     }else{
			    	 
			    	 var refundAmount = row.refundAmount/100;
			    	 $(ed.target).numberbox('setValue',refundAmount);
				     var editCtrl = $(ed.target[0]).next().children()[0];
				     $(editCtrl).bind('blur',function(){
				    	 
				    	 //结束编辑
				    	 $(me.$gridId ).datagrid('endEdit',index);
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
		$(this.$gridId).datagrid('enableCellEditing');
	},
	setRefundAmount:function(refundAmount){
		
		var rows = $(this.$gridId).datagrid('getRows');
		var len = rows.length;
		if(len==1){
			
			rows[0].refundAmount = refundAmount;
			$(this.$gridId).datagrid('loadData',rows);
		}
	},
	getProductRows:function(){
		
		 return $(this.$gridId).datagrid('getRows');
	}
});


com.gongsibao.trade.web.OrderRefundPerformanceCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {

    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderRefundController';
    	this.$gridId = '#order_refund_grid';
    },
    init:function(){

    	this.initGrid();
    },
	initGrid:function(){
		
		var me = this;
		$(this.$gridId).datagrid({
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
		var orderAllotCtrl = new com.gongsibao.trade.web.OrderAllotCtrl();
		orderAllotCtrl.show('退款业绩分配',function(obj){
			
			me.appendRow(obj);
		});
	},
	appendRow:function(row){
		var orderId = this.queryString('id');
		row.orderId = orderId;
		$(this.$gridId).datagrid('appendRow',row);
	},
	remove:function(){
		
		var row = $(this.$gridId).datagrid('getSelected');
		if(row == null){
			
			return;
		}
		
		//提示确认
		
		var index = $(this.$gridId).datagrid('getRowIndex',row);
		$(this.$gridId).datagrid('deleteRow',index);
	},
	getDepRefundRows:function(){

		return $('#order_refund_grid').datagrid('getRows');
	}
});
//字符验证
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == "" ){
        return true;
    }else{
        return false;
    }
}