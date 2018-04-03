System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.BaseCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	
    	this.service = 'com.gongsibao.trade.web.OrderDetailController';
    	this.processStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderProcessStatusType');
    	this.offlineWayType = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OfflineWayType');
    	this.receiptStatus = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.PayReceiptStatus');
    	this.payForOrderCount = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.PayForOrderCountType');
    	this.payWayTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.PayWayType');
    	this.auditStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.AuditStatusType');
    	this.refundWayTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.RefundWayType');
    	this.auditLogStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.bd.dic.AuditLogStatusType');
    	this.platformSourceTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPlatformSourceType');
    	this.payStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPayStatusType');
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
    	//1.过滤‘合同信息’页签显示
    	var orderId = this.queryString('id');
    	this.invokeService ("queryContractFirst", [orderId], function(data){    		
    		if(data == null){
    			$('#tabs >.tabs-header >.tabs-wrap ul>li').eq(1).remove();
    		}
    	});
    	var me = this;
    	//tab页签
    	$('#tabs').tabs({    
			tabHeight:30,
		    onSelect:function(title){
		    	var detailCtrl = me.initializeDetailList.byKey(title);
		    	if(detailCtrl){
		    		//已经初始化过的不再执行
		    		return;
		    	}
		    	if(title=='合同信息'){
			    	var contractCtrl = new com.gongsibao.trade.web.ContractCollectionDetailCtrl();
			    	contractCtrl.init();
			    	me.initializeDetailList.add(title,contractCtrl);
			    	
		    	}else if(title=='商机信息'){
		    		alert('商机信息');
		    	}
		    }   
		});
    	//详情页签
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
    contractInfo:function(){
    	//合同信息
    }
});

/*
 * 合同详情记录
 */
function showContract(contractId){	
	var contentUrl = "/panda/trade/audit/contract/form?id=" + contractId + "";
     layer.open({
         id: "contractCreateIframe",
         type: 2,//1是字符串 2是内容
         title: '合同信息',
         fixed: false,
         maxmin: true,
         shadeClose: false,
         area: ['60%', '90%'],
         zIndex: 100000,
         content: contentUrl
     });
} 
com.gongsibao.trade.web.ContractCollectionDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {    	
    	this.base();
    },
    init:function(){    	
    	var me = this;
    	var orderId = this.queryString('id');
    	this.invokeService ("queryContractFirst", [orderId], function(data){    		
    		me.initGrid(data);
    	});
    },
    initGrid:function(data){
    	var me = this;
    	var contractId = data.id;
		$('#contract_info_grid').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			singleSelect:true,
			height:'100%',
			data:[data],
		    columns:[[
		        {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){		        	
		        	return '<a class="grid-btn" href="javascript:" onclick="showContract('+ contractId +')";>查看</a>';
		        }},
		        {field:'id',title:'合同审核编号',width:80,align:'center'},
		        {field:'dataFee',title:'材料撰写费',width:100,align:'center',formatter: function(value,row,index){		        	
		        	return (value/100).toFixed(2);
		        }},
		        {field:'liquidatedDamages',title:'违约金',width:100,align:'center',formatter: function(value,row,index){		        	
		        	return (value/100).toFixed(2);
		        }},
		        {field:'liquidatedDamages',title:'订单金额',width:80,align:'center',formatter: function(value,row,index){		        	
		        	return (value/100).toFixed(2);
		        }},
		        {field:'realAmount',title:'合同总额',width:100,align:'center',formatter: function(value,row,index){		        	
		        	return (value/100).toFixed(2);
		        }},
		        {field:'creator',title:'创建人',width:100},
		        {field:'createTime',title:'创建时间',width:130,align:'center'},
		        {field:'auditStatusId',title:'审核状态',width:100,align:'center',formatter:function(value,row,index){	        		
		        	return me.auditStatusTypeEnum[value];
		        }}
		    ]]
		});    	
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
		        {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	return '<a class="grid-btn" href="javascript:;">查看</a>';
		        }},
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
		        {field:'processStatusId',title:'办理进度',width:100,align:'center',formatter:function(value,row,index){
	        		
	        		if(value){
	        		
	        			return me.processStatusEnum[value];
	        		}
	        		return '-';
		        }},
//		        {field:'ownerId',title:'业务员',width:80,align:'center',formatter:function(value,row,index){
//	        		
//	        		if(row.owner){
//	        			
//	        			return row.owner.name;
//	        		}else {
//	        			
//	        			return '-';
//	        		}
//		        }}
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
    	
    	var me = this;
    	var orderId = this.queryString('id');
    	this.invokeService ("queryPayList", [orderId], function(data){
    		
    		me.initGrid(data);
    	});

    },
    initGrid:function(data){

    	var me = this;
		$('#order_payment_grid').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			singleSelect:true,
			height:'100%',
			data:data,
		    columns:[[

		        {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	return '<a class="grid-btn" href="javascript:;">查看</a>';
		        }},
		        {field:'payForOrderCount',title:'回款类别',width:80,align:'center',formatter:function(value,row,index){
	        		
		        	return me.payForOrderCount[value];
		        }},
		        {field:'no',title:'支付编号',width:100},
		        {field:'receiptNo',title:'审核编号',width:100},
		        {field:'amount',title:'支付金额',width:80,align:'right',formatter: function(value,row,index){
		        	
		        	return (value/100).toFixed(2);
		        }},
		        {field:'offlinePayerName',title:'账户名称',width:100},
		        {field:'offlineBankNo',title:'付款账号',width:100},
		        {field:'payWayType',title:'付款类别',width:80,align:'center',formatter:function(value,row,index){
	        		
	        		return me.payWayTypeEnum[value];
		        }},
		        {field:'offlineWayType',title:'付款方式',width:80,align:'center',formatter:function(value,row,index){
	        		
		        	return me.offlineWayType[value];
		        }},
		        {field:'payTime',title:'回款日期',width:130,align:'center'},
		        {field:'confirmTime',title:'审核通过时间',width:130,align:'center'},
		        {field:'createTime',title:'创建时间',width:120,align:'center'},
		        {field:'receiptStatus',title:'状态',width:100,align:'center',formatter:function(value,row,index){
	        		
		        	return me.receiptStatus[value];
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
    	
    	var me = this;
    	var orderId = this.queryString('id');
    	this.invokeService ("queryRefundList", [orderId], function(data){
    		
    		me.initGrid(data);
    	});
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

		        {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	return '<a class="grid-btn" href="javascript:;">查看</a>';
		        }},
		        {field:'no',title:'退款记录编号',width:100},
		        {field:'serviceName',title:'审核编号',width:100},
		        {field:'amount',title:'退款金额',width:100,align:'right',formatter: function(value,row,index){
		        	
		        	return (value/100).toFixed(2);
		        }},   
		        {field:'orderProdId',title:'退款产品',width:150,formatter:function(value,row,index){
		        	
		        }},
		        {field:'cityName',title:'退款产品地区',width:150,align:'right',formatter:function(value,row,index){
		        		
		        	
		        }},
		        {field:'processStatus',title:'办理进度',width:80,align:'center',formatter:function(value,row,index){
		        	
	        		if(value){
		        		
	        			return me.processStatusEnum[value];
	        		}
	        		return '-';
	        		//订单产品的办理进度
		        }},
		        {field:'wayType',title:'退款方式',width:80,align:'center',formatter:function(value,row,index){
	        		
	        		if(value){
		        		
	        			return me.refundWayTypeEnum[value];
	        		}
	        		return '-';
		        }},
		        {field:'refundTime',title:'退款时间',width:130,align:'center'},
		        {field:'createTime',title:'创建时间',width:130,align:'center'},
		        {field:'auditStatus',title:'审核状态',width:80,align:'center',formatter:function(value,row,index){
		        	
	        		if(value){
		        		
	        			return me.auditStatusTypeEnum[value];
	        		}
	        		return '-';
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
    	
    	var me = this;
    	var orderId = this.queryString('id');
    	this.invokeService ("queryChangePriceList", [orderId], function(data){
    		
    		me.initGrid(data);
    	});
    },
    initGrid:function(data){

    	var me = this;
		$('#order_change_price_grid').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			singleSelect:true,
			height:'100%',
			data:data,
		    columns:[[
		              
		        {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	return '<a class="grid-btn" href="javascript:;">查看</a>';
		        }},
		        {field:'no',title:'改价审核编号',width:100,align:'center'},
		        {field:'originalPrice',title:'改价前金额',width:100,align:'right',formatter:function(value,row,index){
		        	return (value/100).toFixed(2);
		        }},
		        {field:'payablePrice',title:'改价后金额',width:100,align:'right',formatter:function(value,row,index){
		        	return (value/100).toFixed(2);
		        }},   
		        {field:'differencePrice',title:'差额',width:100,align:'right',formatter:function(value,row,index){
		        	return (value/100).toFixed(2);
		        }},
		        {field:'creator',title:'发起人',width:100,align:'center'},
		        {field:'createTime',title:'发起时间',width:130,align:'center'},
		        {field:'status',title:'审核状态',width:80,align:'center',formatter:function(value,row,index){
	        		if(value){
		        		
	        			return me.auditLogStatusTypeEnum[value];
	        		}
	        		return '-';
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
    	
    	var me = this;
    	var orderId = this.queryString('id');
    	this.invokeService ("queryDiscountList", [orderId], function(data){
    		
    		me.initGrid(data);
    	});
    },
    initGrid:function(data){

    	var me = this;    	
		$('#order_discount_grid').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			singleSelect:true,
			height:'100%',
			data:data,
		    columns:[[

		        {field:'no',title:'优惠卷码',width:200},
		        {field:'amount',title:'优惠金额',width:80,align:'right',formatter:function(value,row,index){
		        	return (value/100).toFixed(2);
		        }},
		        {field:'preferentialId',title:'优惠券制作人',width:100,formatter: function(value,row,index){
		        	
		        	if(row.preferential){
		        		
		        		return row.preferential.creator;
		        	}
		        }},   
		        {field:'createTime',title:'使用时间',width:130,align:'center'}
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
    	
    	var me = this;
    	var orderId = this.queryString('id');
    	this.invokeService ("queryExchangeLogList", [orderId], function(data){
    		
    		me.initGrid(data);
    	});
    },
    initGrid:function(data){
    	
		$('#order_follow_grid').datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			singleSelect:true,
			height:'100%',
			data:data,
		    columns:[[

		        {field:'formUserName',title:'来自',width:80,align:'center'},
		        {field:'toUserName',title:'去向',width:80,align:'center'},
		        {field:'content',title:'转移通知',width:300},
		        {field:'creator',title:'操作人',width:80,align:'center'},   
		        {field:'createTime',title:'操作时间',width:130,align:'center'}
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
 * 订单商机明细
 */
com.gongsibao.trade.web.OrderTaskDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    },
    init:function(){
    	

    }
});