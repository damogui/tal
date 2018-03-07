System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.BaseCtrl = System.Object.Extends({
    ctor: function () {
    	
    	this.service = 'com.gongsibao.trade.web.OrderDetailController';
    },
    invokeService: function (method, pars, callback, isAsyn, errorCallback) {

        var serviceLocator = new org.netsharp.core.JServiceLocator();
        var me = this;
        var thisCallback = function (data) {
        	
            if (!System.isnull(callback)) {
            	
                callback(data);
            }
        };
        serviceLocator.invoke(this.service, method, pars, thisCallback, null, isAsyn, errorCallback);
    },
    queryString: function (name) {

        var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
        if (result == null || result.length < 1) {

            return "";
        }
        return result[1];
    },
    getOrderId:function(){
    	
    	return this.queryString('id');
    }
});

/*
 * 订单表单
 */
com.gongsibao.trade.web.OrderFormCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.initializeDetailList = new System.Dictionary();
    },
    init:function(){
    	
    	var me = this;
    	var orderId = this.getOrderId();
    	this.invokeService ("getSoOrder", [orderId], function(data){
    		
    		me.bindData(data);
    	});

		$('#detail_tabs').tabs({    
			tabHeight:30,
		    onSelect:function(title){    

		    	var detailCtrl = me.initializeDetailList.byKey(title);
		    	if(detailCtrl){
		    		
		    		//已经初始化过的不再执行
		    		return;
		    	}
		    	
		    	if(title=='回款记录'){

			    	var paymentCollectionDetailCtrl = new com.gongsibao.trade.web.OrderPaymentCollectionDetailCtrl();
			    	paymentCollectionDetailCtrl.init();
			    	me.initializeDetailList.add(title,paymentCollectionDetailCtrl);
			    	
		    	}else if(title=='退款记录'){

			    	var refundDetailCtrl = new com.gongsibao.trade.web.OrderRefundDetailCtrl();
			    	refundDetailCtrl.init();
			    	me.initializeDetailList.add(title,refundDetailCtrl);
			    	
		    	}else if(title=='改价记录'){

			    	var changePriceDetailCtrl = new com.gongsibao.trade.web.OrderChangePriceDetailCtrl();
			    	changePriceDetailCtrl.init();
			    	me.initializeDetailList.add(title,changePriceDetailCtrl);
			    	
		    	}else if(title=='优惠明细'){

			    	var discountDetailCtrl = new com.gongsibao.trade.web.OrderDiscountDetailCtrl();
			    	discountDetailCtrl.init();
			    	me.initializeDetailList.add(title,discountDetailCtrl);
			    	
		    	}else if(title=='流转日志'){

			    	var fllowDetailCtrl = new com.gongsibao.trade.web.OrderFollowDetailCtrl();
			    	fllowDetailCtrl.init();
			    	me.initializeDetailList.add(title,fllowDetailCtrl);
		    	}
		    }   
		});

    	//加载产品信息
    	var productDetailCtrl = new com.gongsibao.trade.web.OrderProductDetailCtrl();
    	productDetailCtrl.init();
    	me.initializeDetailList.add('产品信息',productDetailCtrl);
    	
    },
    bindData:function(soOrder){
    	
    	$('#no').text(soOrder.no);
    	
    	var payablePrice=soOrder.payablePrice/100;
    	$('#payablePrice').text(payablePrice.toFixed(2));
    	
    	var paidPrice=(soOrder.paidPrice/100).toFixed(2);
    	$('#paidPrice').text(paidPrice);
    	
    	$('#accountName').text(soOrder.accountName);
    	$('#accountMobile').text(soOrder.accountMobile);
    	$('#addTime').text(soOrder.addTime);
    	
    	$('#platformSource').text(soOrder.platformSource);
    	$('#payStatus').text(soOrder.payStatus);
    	
    	var installmentMode = soOrder.installmentMode;
    	if(installmentMode){
    		
    		var ss = installmentMode.split('|');
    		var count = ss.length;
        	$('#installmentCount').text(count+'期');
    	}else{
    		
    		$('#installmentCount').text('-');
    	}
    	
    	$('#channelOrderNo').text(soOrder.channelOrderNo||'');
    	
    	$('#remark').text(soOrder.remark||'');
    }
});


/*
 * 订单产品明细
 */
com.gongsibao.trade.web.OrderProductDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    },
    init:function(){
    	
    	var me = this;
    	var orderId = this.getOrderId();
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
			height:'100%',
			data:data,
		    columns:[[
		        {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	return '<a class="grid-btn" href="javascript:;">查看</a>';
		        }},
		        {field:'id',title:'订单明细编号',width:100,align:'center'},
		        {field:'productName',title:'产品名称',width:150},
		        {field:'serviceName',title:'服务名称',width:200,formatter:function(value,row,index){

		        	var items = row.items;
		        	if(items){
		        		
		        		var len = items.length;
		        		if(len==1){
		        			return items[0].serviceName;
		        		}else{
		        			
		        			var serviceName = items[0].serviceName+'...';
		        			var ss = [];
		        			$(items).each(function(i,item){
		        				
		        				ss.push(item.serviceName);
		        			});
		        			var fullServiceName = ss.join(',');
		        			var tipId='tip'+row.id;
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
		        {field:'processStatusId',title:'办理进度',width:100,align:'center',formatter:function(value,row,index){
	        		
	        		if(value){
	        		
	        			return '-';
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
		    ]]
		});
    }
});

/*
 * 订单回款记录
 */
com.gongsibao.trade.web.OrderPaymentCollectionDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    },
    init:function(){

		$('#order_payment_grid').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			height:'100%',
		    columns:[[

		        {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	return '<a class="grid-btn" href="javascript:;">查看</a>';
		        }},
		        {field:'no',title:'支付编号',width:100},
		        {field:'serviceName',title:'审核编号',width:200},
		        {field:'amount',title:'支付金额',width:150,formatter: function(value,row,index){
		        	
		        	return value/100;
		        }},   
		        {field:'offlinePayerName',title:'账户名称',width:100},
		        {field:'offlineBankNo',title:'付款账号',width:100,formatter:function(value,row,index){
		        		
		        	return value/100;
		        }},
		        {field:'payWayType',title:'付款类别',width:100,formatter:function(value,row,index){
	        		
	        		
		        }},
		        {field:'handleName',title:'付款方式',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }},
		        {field:'handleName',title:'回款日期',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }},
		        {field:'handleName',title:'审核通过日期',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }},
		        {field:'handleName',title:'创建时间',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }},
		        {field:'handleName',title:'状态',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }}
		    ]]
		});
    }
});

   
/*
 * 订单退款明细
 */
com.gongsibao.trade.web.OrderRefundDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    },
    init:function(){
    	
		$('#order_refund_grid').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			height:'100%',
		    columns:[[

		        {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	return '<a class="grid-btn" href="javascript:;">查看</a>';
		        }},
		        {field:'productName',title:'退款记录编号',width:200},
		        {field:'serviceName',title:'审核编号',width:200},
		        {field:'name',title:'退款金额',width:150,formatter: function(value,row,index){
		        	
		        	if(row.service && row.service.type){
		        		
		        		var name = row.service.type.name;
		        		if(row.service.property){
		        			
		        			name = row.service.property.name+'-'+name;
		        		}
		        		return name;
		        	}
		        }},   
		        {field:'originalPrice',title:'退款产品',width:100,align:'right',formatter:function(value,row,index){
		        	return value/100;
		        }},
		        {field:'price',title:'退款产品地区',width:100,align:'right',formatter:function(value,row,index){
		        		
		        	return value/100;
		        }},
		        {field:'processStatusId',title:'办理进度',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }},
		        {field:'handleName',title:'退款方式',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }},
		        {field:'handleName',title:'退款时间',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }},
		        {field:'handleName',title:'创建时间',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }},
		        {field:'handleName',title:'审核状态',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }}
		    ]]
		});
    }
});






 
/*
 * 订单改价明细
 */
com.gongsibao.trade.web.OrderChangePriceDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    },
    init:function(){
    	
		$('#order_change_price_grid').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			height:'100%',
		    columns:[[
		              
		        {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	return '<a class="grid-btn" href="javascript:;">查看</a>';
		        }},
		        {field:'productName',title:'改价审核编号',width:200},
		        {field:'serviceName',title:'改价前金额',width:200},
		        {field:'name',title:'改价后金额',width:150,formatter: function(value,row,index){
		        	
		        	if(row.service && row.service.type){
		        		
		        		var name = row.service.type.name;
		        		if(row.service.property){
		        			
		        			name = row.service.property.name+'-'+name;
		        		}
		        		return name;
		        	}
		        }},   
		        {field:'originalPrice',title:'差额',width:100,align:'right',formatter:function(value,row,index){
		        	return value/100;
		        }},
		        {field:'price',title:'发起人',width:100,align:'right',formatter:function(value,row,index){
		        		
		        	return value/100;
		        }},
		        {field:'processStatusId',title:'发起时间',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }},
		        {field:'handleName',title:'审核状态',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }}
		    ]]
		});
    }
});


/*
 * 优惠明细
 */
com.gongsibao.trade.web.OrderDiscountDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    },
    init:function(){
    	
		$('#order_discount_grid').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			height:'100%',
		    columns:[[
		              
		        {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	return '<a class="grid-btn" href="javascript:;">查看</a>';
		        }},
		        {field:'productName',title:'产品名称',width:200},
		        {field:'serviceName',title:'服务名称',width:200},
		        {field:'name',title:'产品地区',width:150,formatter: function(value,row,index){
		        	
		        	if(row.service && row.service.type){
		        		
		        		var name = row.service.type.name;
		        		if(row.service.property){
		        			
		        			name = row.service.property.name+'-'+name;
		        		}
		        		return name;
		        	}
		        }},   
		        {field:'originalPrice',title:'原价',width:100,align:'right',formatter:function(value,row,index){
		        	return value/100;
		        }},
		        {field:'price',title:'售价',width:100,align:'right',formatter:function(value,row,index){
		        		
		        	return value/100;
		        }},
		        {field:'processStatusId',title:'办理进度',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }},
		        {field:'handleName',title:'业务员',width:100,align:'right',formatter:function(value,row,index){
	        		
	        		
		        }}
		    ]]
		});
    }
});

/*
 * 订单流转日志
 */
com.gongsibao.trade.web.OrderFollowDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    },
    init:function(){
    	
		$('#order_follow_grid').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			height:'100%',
		    columns:[[
		        {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	return '<a class="grid-btn" href="javascript:;">查看</a>';
		        }},
		        {field:'id',title:'来自',width:100},
		        {field:'productName',title:'去向',width:200},
		        {field:'serviceName',title:'转移通知',width:200},
		        {field:'name',title:'操作人',width:150,formatter: function(value,row,index){
		        	
		        	if(row.service && row.service.type){
		        		
		        		var name = row.service.type.name;
		        		if(row.service.property){
		        			
		        			name = row.service.property.name+'-'+name;
		        		}
		        		return name;
		        	}
		        }},   
		        {field:'originalPrice',title:'操作时间',width:100,align:'right',formatter:function(value,row,index){
		        	return value/100;
		        }}
		    ]]
		});
    }
});


/*
 * 订单合同
 */
com.gongsibao.trade.web.OrderContractCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    },
    init:function(){

    }
});

/*
 * 订单任务明细
 */
com.gongsibao.trade.web.OrderTaskDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    },
    init:function(){
    	

    }
});