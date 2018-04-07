System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.SelectSalesmanCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.multiple = false;
    	this.$gridCtrlId = '#salesman_grid';
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    },
    open:function(title,multiple,callback){
    	
    	this.multiple = multiple;
    	var me = this;
		var builder = new System.StringBuilder();
		builder.append('<div style="margin:0 10px;">');
		builder.append('	<table cellpadding="5" cellspacing="5" class="form-panel">');
		builder.append('		<tr><td style="color:red;">提示：填写关键字后敲回车执行查询</td></tr>');
		builder.append('		<tr><td><input id="salesman_keyword"/></td></tr>');
		builder.append('		<tr><td><table id="salesman_grid"></table></td></tr>');
		builder.append('	</table>');
		builder.append('</div>');
		
		//短信通知客户
		layer.open({
			type : 1,
			title : title,
			fixed : false,
			maxmin : false,
			shadeClose : true,
			zIndex : 100000,
			area : [ '600px', '440px' ],
			content : builder.toString(),
			btn : [ '确定', '取消' ],
			success : function(layero, index) {

			    me.initGrid();
			    
			    $("#salesman_keyword").searchbox({
			    	height:30,
			    	width:560,
			    	prompt:'姓名、服务商、部门',
			    	searcher:function(value,name){
			    		
			    		me.query();
			    	}
			    });
			},
			btn1 : function(index, layero) {

				var rows = $(me.$gridCtrlId).datagrid('getChecked');
				if(rows.length == 0){
					
					layer.msg('请选择');
					return;
				}
				
				callback(rows);
				layer.close(index);
			}
		});
    },
    initGrid:function(){
    	
    	var me = this;
		$(this.$gridCtrlId).datagrid({
			idField:'id',
			height:240,
			width:560,
			striped:true,
			pagination:true,
			showFooter:true,
			pageSize:10,
			singleSelect:!this.multiple,
			onLoadSuccess:function(data){
				
				var pager = $(this).datagrid('getPager');
				$(pager).pagination('options').onSelectPage = function(pageNumber, pageSize){
					
					me.query(pageNumber,pageSize);
				}; 
			},
		    columns:[[
		        {field:'id',checkbox:true,align:'center'},
		        {field:'name',title:'姓名',width:100},
		        {field:'departmentId',title:'部门',width:200,formatter:function(value,row,index){

		        	if(row.department){
		        		
		        		return row.department.name;
		        	}
		        }},
		        {field:'supplierId',title:'服务商',width:200,formatter:function(value,row,index){

		        	if(row.supplier){
		        		
		        		return row.supplier.name;
		        	}
		        }}
		    ]]
		});
    },
    query:function(pageIndex,pageSize){
    	
    	pageIndex = pageIndex || 1;
    	pageSize = pageSize || 10;
    	var me = this;
    	var keyWord = $('#salesman_keyword').searchbox('getValue');
		this.invokeService("querySalesmans", [keyWord,pageIndex,pageSize], function(data){
			
			$(me.$gridCtrlId).datagrid('loadData',data);
		});
    }
});