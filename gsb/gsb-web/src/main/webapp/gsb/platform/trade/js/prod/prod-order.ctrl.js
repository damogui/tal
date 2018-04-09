System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.mainCtrl = null;
    },
    init:function(orderProdId){

    	var me = this;
    	this.invokeService ("getOrderById", [this.mainCtrl.orderProd.orderId], function(data){
    		
    		me.bindData(data);
    	});
    },
    bindData:function(data){
    	
    	$("#order_info_order_no").text($("#orderNo").text());
    	if(this.mainCtrl.orderProd.owner){

        	$("#order_info_salesman").text(this.mainCtrl.orderProd.owner.name);
    	}

    	$("#order_info_city").text(this.mainCtrl.orderProd.cityName);
    	
    	$("#order_info_payablePrice").text(System.RMB.fenToYuan(data.payablePrice));
    	$("#order_info_createTime").text(data.createTime);
    	
    	var payStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPayStatusType');
    	var payStatus = payStatusTypeEnum[data.payStatus];
    	$("#order_info_payStatus").text(payStatus);

    	//服务项目
    	var items = this.mainCtrl.orderProd.items;
		$("#service_item_grid").datagrid({
			idField:'id',
			title:'服务项目',
			striped:true,
			pagination:false,
			showFooter:false,
			singleSelect:true,
			data:items,
		    columns:[[

		        {field:'id',title:'编号',width:100,align:'center'},
		        {field:'serviceName',title:'服务项',width:350},
		        {field:'unitName',title:'单位',width:60,align:'center'},
		        {field:'quantity',title:'数量',width:60,align:'center'},
		        {field:'price',title:'价格',width:80,align:'right',formatter:function(value,row,index){
		        	
		        	return System.RMB.fenToYuan(value);
		        }}
		    ]]
		});

    	var products = data.products;
		$("#other_prod_grid").datagrid({
			idField:'id',
			title:'关联产品（此订单的其它产品）',
			striped:true,
			pagination:false,
			showFooter:false,
			singleSelect:true,
			data:products,
		    columns:[[

		        {field:'id',title:'编号',width:100,align:'center'},
		        {field:'productName',title:'产品名称',width:350},
		        {field:'processStatusId',title:'状态',width:200,align:'center',formatter:function(value,row,index){
		        	
		        	if(row.processStatus){

			        	return row.processStatus.name;
		        	}
		        }}
		    ]]
		});
    }
});