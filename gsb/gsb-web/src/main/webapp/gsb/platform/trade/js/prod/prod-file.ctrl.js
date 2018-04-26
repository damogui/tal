System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.FileCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.$gridId = '#order_prod_trace_file_grid';
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.mainCtrl = null;
    	this.orderProdId = null;
    },
    init:function(orderProdId){

    	this.orderProdId = orderProdId;
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
//			toolbar: [{
//				iconCls: 'fa fa-cloud-download',
//				text:'一键下载',
//				handler: function(){
//					
//					alert('一键下载');
//				}
//			}],
		    columns:[[

		        {field:'status',title:'状态',width:80,align:'center',formatter:function(value,row,index){

		        	if(value==1){
		        		
		        		return '最新';
		        	}else{
		        		
		        		return '历史';
		        	}
		        }},
		        {field:'workflowFileName',title:'材料名称',width:200},
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
    		$('#notUploadFileNames').text(data.notUploadFileNames ||"");
    	});
    },
    openUploadWindow:function(){
    	
    	var me = this;
		var builder = new System.StringBuilder();
		builder.append('	<table cellpadding="5" cellspacing="10" class="form-panel">');
		builder.append('		<tr><td style="color: #009688;">此处上传文件只作补充/更新文件上传之用，不会更改订单状态。</td></tr>');
		builder.append('		<tr><td>相关材料：<input id="workflowFile"/> <a id="btn_select_file" style="margin-left:5px;" class="easyui-linkbutton">选择文件</a> <span style="color:red;">注意:可选择多个文件上传!</span></td></tr>');
		builder.append('		<tr id="other_file_row" style="display:none;"><td>请填写材料名称：<input id="other_file_name" class="easyui-textbox nsInput" placeholder="材料名称" style="width:457px"> </td></tr>');
		builder.append('		<tr><td><table id="upload_file_grid"></table></td></tr>');
		builder.append('		<tr><td><textarea id="upload_file_remark" placeholder="备注信息..." style="width: 570px; height:80px;"></textarea></td></tr>');
		builder.append('	</table>');
		
		//短信通知客户
		layer.open({
			type : 1,
			title : '更改状态',
			fixed : false,
			maxmin : false,
			shadeClose : true,
			zIndex : 100000,
			area : [ '600px', '550px' ],
			content : builder.toString(),
			btn : [ '确定', '取消' ],
			success : function(layero, index) {
				
				$('#btn_select_file').linkbutton({    
				    iconCls: 'fa fa-file'
				});
                var id = me.mainCtrl.orderProd.id;
				me.invokeService("queryWorkflowFileList", [id], function(data){

					var _version = data[0] != null ?data[0].version:0;
					data.push({id:-1,name:'其它',must:true,version:_version});
					$("#workflowFile").combobox({
						width:200,
						editable:false,
						panelHeight:250,
						valueField: 'id',    
				        textField: 'name',
				        data:data,
				    	formatter: function(row){
				    		
				    		var color = row.must==true?'red':'#404040';
				    		var title = row.must==true?'必要文件':'';
			    			return '<span title="'+title+'" style="color:'+color+'">'+row['name']+'</span>';
				    	},
				    	onChange:function(newValue,oldValue){
				    		
				    		if(newValue==-1){

				    			//显示【请填写材料名称】
				    			$('#other_file_row').show();
				    		}else{

				    			$('#other_file_row').hide();
				    		}
				    	}
				    });
				},true,function(){
					
					layer.close(index);
				});
				
				me.initUploadFileGrid();

		        var upload = new org.netsharp.controls.FileUpload();
		        upload.parent = me;
		        upload.init();
			},
			btn1 : function(index, layero) {

				me.save(index);
			}
		});
    },
    initUploadFileGrid:function(){
    	
    	var me = this;
		$("#upload_file_grid").datagrid({
			idField:'id',
			title:'上传进度',
			height:200,
			striped:true,
			pagination:false,
			showFooter:false,
			singleSelect:true,
		    columns:[[

		        {field:'name',title:'名称',width:150},
		        {field:'size',title:'大小',width:100,align:'center',formatter:function(value,row,index){

		        	return value+'KB';
		        }},
		        {field:'progress',title:'进度',width:100,align:'center',formatter:function(value,row,index){

		        	return '<div id="progressbar_'+row.id+'" style="width:90px;"></div> ';
		        }},
		        {field:'status',title:'状态',width:80,align:'center',formatter:function(value,row,index){

		        	//plupload.QUEUED, plupload.UPLOADING, plupload.FAILED, plupload.DONE
		        	if(value==1){
		        		
		        		return '待上传';
		        	}else if(value==2){
		        		
		        		return '上传中';
		        	}else if(value==3){
		        		
		        		return '失败';
		        	}else if(value==5){
		        		
		        		return '完成';
		        	}
		        	return value;
		        }},
		        {field:'id',title:'操作',width:80,align:'center',formatter:function(value,row,index){

		        	return '[&nbsp;<a href="javascript:fileCtrl.remove('+index+')" >删除</a>&nbsp;]';
		        }}
		    ]]
		});

    },
    refreshRow:function(file){
    	
		var index = $('#upload_file_grid').datagrid('getRowIndex',file);
		$('#upload_file_grid').datagrid('updateRow',{
			index: index,
			row: file
		});

		$('#upload_file_grid').datagrid('refreshRow',index);
		$('#progressbar_'+file.id).progressbar({value: file.percent}); 
    },
    remove:function(index){

    	$("#upload_file_grid").datagrid('deleteRow',index);
    },
    save:function(index){
    	
    	var workflowFileId = $("#workflowFile").combobox('getValue');
    	if(System.isnull(workflowFileId)){
			
			layer.msg('请选择材料！');
			return;
		}
    	var otherFileName = $('#other_file_name').val();
    	if(parseInt(workflowFileId)==-1 && System.isnull(otherFileName)){
    		
			layer.msg('请填写材料名称！');
			return;
    		
    	} 
		
		var files = [];
		var rows = $("#upload_file_grid").datagrid('getRows');
		$(rows).each(function(index,item){
			
			if(item.path){
				
				var file = {
						tabName:'so_order_prod_trace_file',
						formId:0,
						name:item.name,
						url:item.path
				};
				files.push(file);
			}
		});
		if(files.length==0){
			
			layer.msg('请上传文件！');
			return;
		}
		
    	var remark =$("#upload_file_remark").val();
		if(System.isnull(remark)){
			
			layer.msg('请填写备注！');
			return;
		}
		
		var workflowFileName = parseInt(workflowFileId)==-1?otherFileName: $("#workflowFile").combobox('getText');
		
		var me = this;
		var dto = new Object();
		dto.orderId = this.mainCtrl.orderProd.orderId
		dto.orderNo = 100000000 + this.mainCtrl.orderProd.orderId;
		dto.orderProdId = this.orderProdId;
		dto.processStatusId = this.mainCtrl.orderProd.processStatusId;
		dto.workflowFileId = workflowFileId;
		dto.workflowFileName =  workflowFileName;
		dto.info = remark;
		dto.files = files;
		
		this.invokeService("addTraceFile", [dto], function(data){

			//刷新
			me.init(me.orderProdId);
			filePreviewCtrl.init(me.orderProdId);
			layer.close(index);
		});
    }
});

org.netsharp.controls.FileUpload = org.netsharp.controls.OSSUpload.Extends({
    ctor: function () {
        this.base();
        this.multi_selection = true;
        this.parent = null;
    },
    getButtonId: function () {

        return "btn_select_file";
    },
	filesAdded:function(up, files){
		
		$(files).each(function(index,item){

			$("#upload_file_grid").datagrid('appendRow',item);
		});
		
	},
	uploadProgress:function(up, file){

		this.parent.refreshRow(file);
	},
	fileUploaded:function(up, file, info){

		this.parent.refreshRow(file);
	},
    preview: function (path, file) {

        if (System.isnull(path)) {
            return;
        }
        
        file.path = path;
        this.parent.refreshRow(file);
    }
});
