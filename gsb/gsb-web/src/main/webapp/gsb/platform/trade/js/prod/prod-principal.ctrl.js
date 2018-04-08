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
    query:function(){
    	
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
			showFooter:false,
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
		        {field:'status',title:'状态',width:80,align:'center',formatter:function(value,row,index){

		        	return value == 3141 ?'正在负责':'曾经负责';
		        }},
		        {field:'id',title:'操作',width:80,align:'center',formatter:function(value,row,index){

		        	if(me.mainCtrl.loginUserId == row.principalId){
		        		
		        		if(row.status == 3141){

			        		return '[&nbsp;<a href="javascript:principalCtrl.finishPrincipal('+value+')" >我已完成</a>&nbsp;]';
		        		}
		        	}else if(row.principalId != null && row.principalId >0){
		        		
			        	return '[&nbsp;<a href="javascript:principalCtrl.remindPrincipal(\''+row.principal.name+'\',\''+row.principal.mobile+'\')" >提醒Ta</a>&nbsp;]';
		        	}
		        }}
		    ]]
		});
    },
    finishPrincipal:function(orderProdUserMapId){
    	
    	var me = this;
    	this.invokeService ("finishPrincipal", [orderProdUserMapId], function(data){
    		
    		layer.msg('操作成功');
    		me.query();
    	});
    },
    remindPrincipal:function(principalName,principalMobile){
    	
    	var me = this;
		var builder = new System.StringBuilder();
		builder.append('<form id="dynamicForm">');
		builder.append('<div style="margin:10px;">');
		builder.append('	<table cellpadding="5" cellspacing="10" class="form-panel">');
		builder.append('		<tr><td><textarea id="remind_principal_remark" placeholder="请填写内容..." style="width: 450px; height:130px;"></textarea></td></tr>');
		builder.append('		<tr><td><input id="remind_principal_isSendMessage" type="checkbox" style="vertical-align: middle;"/><label for="isSendMessage" style="vertical-align: middle;">短信通知Ta</label></td></tr>');
		builder.append('	</table>');
		builder.append('</div>');
		builder.append('</form>');
		//短信通知客户
		layer.open({
			type : 1,
			title : '提醒负责人',
			fixed : false,
			maxmin : false,
			shadeClose : true,
			zIndex : 100000,
			area : [ '500px', '320px' ],
			content : builder.toString(),
			btn : [ '确定', '取消' ],
			success : function(layero, index) {

			},
			btn1 : function(index, layero) {

				//提交更新状态
				var remark = $('#remind_principal_remark').val();
				if(System.isnull(remark)){
					
					layer.msg('请填写内容');
					return;
				}
				
				var isSendMessage = $('#remind_principal_isSendMessage').prop('checked');
				
				var pars = [];
				pars.push(me.orderProdId);
				pars.push(me.mainCtrl.orderProd.processStatusId);
				pars.push(principalName);
				pars.push(principalMobile);
				
				var orderNo = 100000000 + me.mainCtrl.orderProd.orderId;
				pars.push(orderNo);
				pars.push(remark);
				pars.push(isSendMessage);
				me.invokeService("remindPrincipal", pars, function(data){
					
					layer.close(index);
					layer.msg('操作成功');
					traceCtrl.query();
				});
			}
		});
    },
    addPrincipal:function(){

    	var me = this;
    	var salesmanCtrl = new com.gongsibao.trade.web.SelectSalesmanCtrl();
    	salesmanCtrl.open('添加负责人',true,function(salesmans){
    		
    		var idArr = new Array();
    		var nameArr = new Array();
    		$(salesmans).each(function(index,item){
    			
    			idArr.push(item.employeeId);
    			nameArr.push(item.name);
    		});
    		
    		var principalIds = idArr.join();
    		var principalNames = nameArr.join();
    		
			var pars = [];
			pars.push(me.orderProdId);
			pars.push(principalIds);
			pars.push(principalNames);
			me.invokeService("addPrincipal", pars, function(data){
				
				me.query();
				layer.msg('添加负责人成功');
				traceCtrl.query();
			});
    	});
    }
});