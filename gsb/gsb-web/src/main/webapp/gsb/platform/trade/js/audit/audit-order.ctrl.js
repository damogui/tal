System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditOrderCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.initializeDetailList = new System.Dictionary();
    	//获取枚举
    	this.processStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderProcessStatusType');
    	this.auditLogStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.bd.dic.AuditLogStatusType');
    	this.service = 'com.gongsibao.trade.web.audit.AuditOrderController';
    },
    initData:function(){
    	var me = this;
    	var orderId = this.queryString('id');
    	//加载Tab项
    	$('#detail_tabs').tabs({ 
    		tabHeight:30,
		    onSelect:function(title){
		    	var detailCtrl = me.initializeDetailList.byKey(title);
		    	if(detailCtrl){		    		
		    		//已经初始化过的不再执行
		    		return;
		    	}
		    	if(title=='审批进度'){
		    		me.auditLogInfor(orderId);
		    	}
		    }
    	});
    	//加载产品信息
    	me.initializeDetailList.add('产品信息',me.productInfor(orderId));
    },
    productInfor: function(id){
    	//tab-产品信息
    	var me = this;
    	this.invokeService ("queryProductList", [id], function(data){
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
    		        }}
    		    ]]
    		});
    	});
    },    
    auditLogInfor: function(formId){
    	//tab-审批进度
    	var me = this;
    	this.invokeService("getAuditLogList", [formId,1042], function(data){
    		$('#audit_progress_grid').datagrid({
    			idField:'id',
    			emptyMsg:'暂无记录',
    			striped:true,
    			pagination:false,
    			showFooter:true,
    			singleSelect:true,
    			height:'100%',
    			data:data,
    		    columns:[[
    		        {field:'creatorId',title:'创建人名称',width:80,align:'center',formatter: function(value,row,index){
    		        	return row.employee.name;
    		        }},
    		        {field:'status',title:'审核状态',width:80,align:'center',formatter: function(value,row,index){
    		        	return me.auditLogStatusEnum[value];
    		        }},
    		        {field:'createTime',title:'创建时间',width:150,align:'center'},
    		        {field:'content',title:'审批内容',width:150,align:'right'},
    		        {field:'remark',title:'说明',width:300,align:'center'}
    		    ]]
    		});
    	});
    }
});
