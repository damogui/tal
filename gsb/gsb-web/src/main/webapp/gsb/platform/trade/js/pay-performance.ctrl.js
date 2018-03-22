System.Declare("com.gongsibao.trade.web");

/***
 * 
 * 回款主控制器
 */
com.gongsibao.trade.web.PayPerformanceCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.PayPerformanceController';
    },
    init:function(){

    	var me = this;
    	var orderId = this.queryString('id');
    	if(System.isnull(orderId)){
    		
    		return;
    	}
    	this.invokeService ("getSoOrder", [orderId], function(data){
    		
    		me.bindData(data);
    	});
    	
    	this.payPerformanceCtrl = new com.gongsibao.trade.web.OrderPayPerformanceCtrl();
    	this.payPerformanceCtrl.init();
    },
    bindData:function(soOrder){
    	
    	$('#no').text(soOrder.no);
    	
    	var payablePrice=soOrder.payablePrice/100;
    	$('#payablePrice').text(payablePrice.toFixed(2));
    	
    	var paidPrice=(soOrder.paidPrice/100).toFixed(2);
    	$('#paidPrice').text(paidPrice);
    	
    	$('#accountName').text(soOrder.accountName);
    	$('#accountMobile').text(soOrder.accountMobile);
    	$('#addTime').text(soOrder.addTime);
    	
    	//$('#platformSource').text(this.platformSourceTypeEnum[soOrder.platformSource]);
    	$('#payStatus').text(this.payStatusTypeEnum[soOrder.payStatus]);
    	var installmentMode = soOrder.installmentMode;
    	if(installmentMode){
    		
    		var ss = installmentMode.split('|');
    		var count = ss.length;
        	$('#installmentCount').text(count+'期');
    	}else{
    		
    		$('#installmentCount').text('-');
    	}
    	
    	$('#channelOrderNo').text(soOrder.channelOrderNo||'');
    	
    	$('#remark').text(soOrder.remark||'');
    },
    save:function(){
    	

    }
});


/***
 * 
 * 回款业绩明细控制器
 */
com.gongsibao.trade.web.OrderPayPerformanceCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
	
    ctor: function () {

    	this.base();
    	this.$gridId = '#order_pay_performance_grid';
    	this.service = 'com.gongsibao.trade.web.PayPerformanceController';
    },
    init:function(){

    	this.initGrid();
    },
	initGrid:function(){
	    
		var me = this;
		$(this.$gridId).datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			title:'回款业绩分配',
			striped:true,
			pagination:false,
			showFooter:true,
			singleSelect:true,
			height:'100%',
			toolbar: [{
				iconCls: 'fa fa-plus',
				text:'新增',
				handler: function(){

					me.add();
				}
			},'-',{
				iconCls: 'fa fa-remove',
				text:'删除',
				handler: function(){

					me.remove();
				}
			}],
		    columns:[[
		        {field:'id',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	var str = '<a class="grid-btn" href="javascript:payMapCtrl.payDepDetailCtrl.remove('+index+');">删除</a>';
		        	return str;
		        }},
		        {field:'supplierId',title:'服务商',width:200,formatter:function(value,row,index){
	        		
		        	if(row.supplier){
		        		
		        		return row.supplier.name;
		        	}
		        }},
		        {field:'departmentId',title:'部门',width:100,formatter:function(value,row,index){
	        		
		        	if(row.department){
		        		
		        		return row.department.name;
		        	}
		        }},
		        {field:'salesmanId',title:'业务员',width:100,align:'center',formatter:function(value,row,index){
	        		
		        	if(row.salesman){
		        		
		        		return row.salesman.name;
		        	}
		        }},
		        {field:'amount',title:'分配金额',width:100,align:'right',formatter:function(value,row,index){
	        		
		        	return System.RMB.FenToYuan(value);
		        }}
		    ]]
		});
	},
	add:function(){
		
		var me = this;
		var orderAllotCtrl = new com.gongsibao.trade.web.OrderAllotCtrl();
		orderAllotCtrl.show('回款业绩分配',function(obj){
			
			//这里要校验最大分配金额不能超过【订单分配金额】-【已分配给员工金额之和】
			
			me.appendRow(obj);
		});
	},
	remove:function(index){
		
		$(this.$gridId).datagrid('deleteRow',index);
	},
	appendRow:function(obj){
		
		$(this.$gridId).datagrid('appendRow',obj);
	},
	getDepPays:function(){
		
		return $(this.$gridId).datagrid('getRows');
	}
});
