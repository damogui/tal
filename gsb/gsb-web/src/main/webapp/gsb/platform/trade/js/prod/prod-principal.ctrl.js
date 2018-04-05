System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.ProdPrincipalCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.$gridCtrlId = '#order_prod_principal_grid';
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.orderProdId = null;
    	this.mainCtrl = null;
    },
    init:function(orderProdId){

		this.orderProdId = orderProdId;
		var me = this;
		this.initGrid();
		this.query();
    },
    query:function(pageIndex,pageSize){
    	
    	var me = this;
    	this.invokeService ("queryProdPrincipalList", [this.orderProdId], function(data){
    		
    		$(me.$gridCtrlId).datagrid('loadData',data);
    	});
    },
    initGrid:function(){
    	
    	var me = this;
		$(this.$gridCtrlId).datagrid({
			idField:'id',
			emptyMsg:'暂无负责人',
			height:209,
			striped:true,
			pagination:false,
			showFooter:true,
			singleSelect:true,
			toolbar: [{
				iconCls: 'fa fa-user-plus',
				text:'添加负责人',
				handler: function(){me.addPrincipal()}
			}],

		    columns:[[

		        {field:'principalId',title:'负责人',width:100,align:'center',formatter:function(value,row,index){

		        	if(row.principal){
		        		
		        		return row.principal.name;
		        	}
		        }},
		        {field:'createTime',title:'开始时间',width:130,align:'center'},
		        {field:'orderProdStatusId',title:'状态',width:80,align:'center',formatter:function(value,row,index){

		        	if(row.status){
		        		
		        		return row.status.name;
		        	}
		        }},
		        {field:'id',title:'操作',width:80,align:'center',formatter:function(value,row,index){

		        	return '[&nbsp;<a href="javascript:principalCtrl.finish('+value+')" >我已完成</a>&nbsp;]';
		        	//return '[&nbsp;<a href="javascript:principalCtrl.remind('+value+','+row.principalId+')" >提醒Ta</a>&nbsp;]';
		        }}
		    ]]
		});
    },
    addPrincipal:function(){
    	
    	alert('添加负责人');
    },
    finish:function(OrderProdUserMapId){
    	
    	alert(OrderProdUserMapId);
    },
    remind:function(OrderProdUserMapId,principalId){
    	
    	alert(OrderProdUserMapId);
    }
});