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
    	this.bindData(orderProdId);
    },
    initGrid:function(){
    	
		$(this.$gridId).datagrid({
			idField:'id',
			title:'您已经上传了<span id="alreadyUploadCount" style="color:blue;">2</span>个必传文件：',
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

		        {field:'status',title:'状态',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	if(value==1){
		        		
		        		return '最新';
		        	}else{
		        		
		        		return '历史';
		        	}
		        }},
		        {field:'prodWorkflowFileName',title:'材料名称',width:200},
		        {field:'creator',title:'上传人',width:80,align:'center'},
		        {field:'remark',title:'备注',width:150},
		        {field:'createTime',title:'上传时间',width:130,align:'center'},
		        {field:'id',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	if(row.file&&row.file.url){

			        	return '[&nbsp;<a href="'+row.file.url+'" target="_blank">查看&nbsp;</a>]';
		        	}
		        }}
		    ]]
		});
    },
    bindData:function(orderProdId){

    	this.orderProdId = orderProdId;
    	var me = this;
    	this.invokeService ("queryOrderProdTraceFiles", [orderProdId], function(data){
    		
    		$(me.$gridId).datagrid('loadData',data.fileList);
    		$('#alreadyUploadCount').text(data.alreadyUploadCount);
    		$('#notUploadCount').text(data.notUploadCount);
    		$('#notUploadFileNames').text(data.notUploadFileNames);
    	});
    },
});