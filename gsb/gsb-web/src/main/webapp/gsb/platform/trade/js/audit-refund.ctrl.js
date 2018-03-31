System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditRefundCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.initializeDetailList = new System.Dictionary();
    	//获取枚举
    	this.processStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderProcessStatusType');
    	this.auditLogStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.bd.dic.AuditLogStatusType');
    	this.service = 'com.gongsibao.trade.web.audit.AuditRefundController';
    },
    initData:function(){
    	var me = this;
    	var refundId = this.queryString('fefundId');
    	var orderId = this.queryString('id');
    	//临时存储OrderId,为了获取审核进度
		$("#tempOrderId").val(orderId);
		
    	me.refundInfor(refundId);
    	//加载Tab项
    	$('#detail_tabs').tabs({ 
    		tabHeight:30,
		    onSelect:function(title){
		    	var detailCtrl = me.initializeDetailList.byKey(title);
		    	if(detailCtrl){		    		
		    		//已经初始化过的不再执行
		    		return;
		    	}
		    	if(title=='退款业绩分配'){
		    		me.resultsfundInfor(refundId);
		    	}else if(title=='审批进度'){
		    		me.auditLogInfor(refundId);
		    	}
		    }
    	});
    },
    refundInfor: function(id){
    	//退款信息
    	var me = this;
    	this.invokeService("getRefundById", [id], function(data){
    		$("#refund_info_grid tr").eq(0).find("td").eq(1).html(data.setOfBooks.name);
    		$("#refund_info_grid tr").eq(0).find("td").eq(3).html(data.refundType);
    		
    		$("#refund_info_grid tr").eq(1).find("td").eq(1).html(data.payerName);
    		$("#refund_info_grid tr").eq(1).find("td").eq(3).html(data.bankNo);
    		$("#refund_info_grid tr").eq(1).find("td").eq(5).html((data.amount/100).toFixed(2));
    		
    		$("#refund_info_grid tr").eq(2).find("td").eq(1).html(data.remark);
    		
    		//加载默认第一项‘退款产品’
    		me.initializeDetailList.add('退款产品',me.refundProductInfor($("#tempOrderId").val()));
    	});
    },
    refundProductInfor: function(id){
    	//tab-退款产品
    	var me = this;
    	this.invokeService ("queryProductList", [id], function(data){
    		$("#audit_product_grid").datagrid({
    			idField:'id',
    			emptyMsg:'暂无记录',
    			striped:true,
    			pagination:false,
    			showFooter:true,
    			singleSelect:true,
    			height:'100%',
    			data:data,
    		    columns:[[
    		        {field:'productName',title:'产品名称',width:150},
    		        {field:'cityName',title:'产品地区',width:150},   
    		        {field:'priceOriginal',title:'原价',width:100,align:'right',formatter:function(value,row,index){
    		        	return (value/100).toFixed(2);
    		        }},
    		        {field:'price',title:'售价',width:100,align:'right',formatter:function(value,row,index){
    		        	return (value/100).toFixed(2);
    		        }},
    		        {field:'refundAmount',title:'退款金额',width:100,align:'right',editor:{type:'numberbox',options:{precision:0,height:31,min:1,required:true}},formatter:function(value,row,index){
    	        		
    		        	if(value){
    			        	return (value/100).toFixed(2);
    		        	}
    		        }},
    		        {field:'processStatusId',title:'办理进度',width:100,align:'center',formatter:function(value,row,index){
    	        		
    	        		if(value){
    	        		
    	        			return me.processStatusEnum[value];
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
    	});
    },    
    resultsfundInfor: function(id){
    	//tab-退款业绩信息
    	this.invokeService("getNDepRefund", [id], function(data){    		
    		$('#audit_refund_grid').datagrid({
    			idField:'id',
    			emptyMsg:'暂无记录',
    			striped:true,
    			pagination:false,
    			showFooter:true,
    			singleSelect:true,
    			height:'100%',
    			data:data,
    		    columns:[[
    		        {field:'supplierId',title:'服务商',width:80,align:'center',formatter: function(value,row,index){
    		        	return row.supplier.name;
    		        }},
    		        {field:'departmentId',title:'部门',width:80,align:'center',formatter: function(value,row,index){
    		        	return row.department.name;
    		        }},
    		        {field:'salesmanId',title:'业务员',width:300,align:'center',formatter: function(value,row,index){
    		        	return row.salesman.name;
    		        }},
    		        {field:'amount',title:'退款业绩分配金额',width:280,align:'right',formatter: function(value,row,index){
    		        	return (value/100).toFixed(2);
    		        }},
    		    ]]
    		});
    	});
    },
    auditLogInfor: function(formId){
    	//tab-审批进度
    	var me = this;
    	this.invokeService("getAuditLogList", [formId,1046], function(data){
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