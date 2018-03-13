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
		
    	var productDetailCtrl = new com.gongsibao.trade.web.OrderProductDetailCtrl();
    	productDetailCtrl.init();
    	
    	var refundPerformanceCtrl = new com.gongsibao.trade.web.OrderRefundPerformanceCtrl();
    	refundPerformanceCtrl.init();
    },
    bindSetOfBooks:function(){
    	
    	this.invokeService ("querySetOfBooksList", [], function(data){
			
    		$('#setOfBooksId').combobox('loadData',data);
		});
    },
    save:function(){
//    	var stageList = [];
//    	var orderId = this.queryString('id');
//    	var num = parseInt($('#stageNum').combobox('getValue'));
//    	for(var i=1;i<=num;i++){
//    		
//    		var amount = parseFloat($('#stageAmount'+i).numberbox('getValue'))*100;
//    		if(System.isnull(amount)){
//    			
//    			IMessageBox.toast('请填写分期金额!',2);
//    			return;
//    		}
//    		var percentage = $('#stagePercentage'+i).numberbox('getValue');
//    		var stage = {
//    				orderId:orderId,
//    				instalmentIndex:i,
//    				percentage:percentage,
//    					amount:amount
//    		};
//    		stageList.push(stage);
//    	}
//    	
//    	var soOrder = {id:orderId,
//		    			staged:true,
//		    			stageNum:num,
//		    			stages:stageList};
//    	
//    	var me = this;
//    	IMessageBox.confirm('确定提交申请吗？',function(r){
//    		
//    		if(r){
//
//    			me.invokeService("applyStage", [soOrder], function(data){
//    	    		
//    	    		IMessageBox.info('申请成功，请等待审核!');
//    	    	});
//    		}
//    	});
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
	//	        {field:'id',title:'订单明细编号',width:100,align:'center'},
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
			     var quantityEditCtrl = $(ed.target[0]).next().children()[0];
			     $(quantityEditCtrl).bind('blur',function(){
			    	 
			    	 //结束编辑
			    	 $('#order_product_grid').datagrid('endEdit',index);
			    	 //me.calculateTotalPrice();
			     });
			}
		});
		$('#order_product_grid').datagrid('enableCellEditing');
	},
});


com.gongsibao.trade.web.OrderRefundPerformanceCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {

    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderRefundController';
    },
    init:function(){

    	this.initGrid();
    },
	initGrid:function(data){
		
		  
		var me = this;
		$('#order_refund_grid').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			singleSelect:true,
			height:'100%',
			data:data,
		    columns:[[
		        {field:'supplierId',title:'服务员',width:200,formatter:function(value,row,index){
	

		        }},
		        {field:'departmentId',title:'部门',width:200,formatter:function(value,row,index){
		        	

		        }},
		        {field:'salesmanId',title:'业务员',width:200,formatter:function(value,row,index){
		        	

		        }},
		        {field:'amount',title:'退款业绩分配金额',width:100,align:'right',formatter:function(value,row,index){
		        		
		        	return (value/100).toFixed(2);
		        }}
		    ]]
		});
	}
    
});