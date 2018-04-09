System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.FileCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.$gridId = '#order_prod_trace_file_grid';
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.mainCtrl = null;
    },
    init:function(orderProdId){

    	this.initGrid();
    },
    initGrid:function(){
    	
		$(this.$gridId).datagrid({
			idField:'id',
			title:'您已经上传了<span style="color:blue;">2</span>个必传文件：',
			striped:true,
			pagination:false,
			showFooter:false,
			singleSelect:true,
			toolbar: [{
				iconCls: 'fa fa-cloud-download',
				text:'一键下载',
				handler: function(){
					
					alert('一键下载');
				}
			}],
		    columns:[[

		        {field:'id',title:'状态',width:80,align:'center'},
		        {field:'serviceName',title:'材料名称',width:200},
		        {field:'unitName',title:'上传人',width:80,align:'center'},
		        {field:'quantity',title:'备注',width:150,align:'center'},
		        {field:'quantity',title:'上传时间',width:130,align:'center'},
		        {field:'price',title:'操作',width:80,align:'right',formatter:function(value,row,index){
		        	
		        	return System.RMB.fenToYuan(value);
		        }}
		    ]]
		});
    }
});