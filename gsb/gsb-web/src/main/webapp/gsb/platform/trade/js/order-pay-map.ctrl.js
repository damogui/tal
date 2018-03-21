System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderPayMapCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderPayController';
    	this.soOrder = null;
    },
    init:function(){

		var centerHeight = $('body').height() - 60;
		$('#center').height(centerHeight);

		this.payDepDetailCtrl = new com.gongsibao.trade.web.PayDepDetailCtrl();
		this.payDepDetailCtrl.init();
    },
    orderNoBlur:function(e){
    	
    	var me = this;
    	var orderNo = $(e).val();
    	if(!System.isnull(orderNo)){
    		
    		//调用后台验证orderNo是否存在
    		this.invokeService("getOrderByNo",[orderNo],function(data){
    			
    			if(data == null){
    				
    				IMessageBox.error('订单号【'+orderNo+'】不存在或已付全款');
    				$(e).val('');
    				return;
    			}
    			
    			me.soOrder = data;
    			var unpaidAmount = me.getUnpaidAmount();
    			unpaidAmount=System.RMB.FenToYuan(unpaidAmount);
    			$('#unpaidAmount').numberbox('setValue',unpaidAmount);
    			$('#amount').numberbox('setValue',unpaidAmount);
    			
    		},false);
    		
    	}else{
    		
    		//有些需要置空
    	}
    },
    getUnpaidAmount:function(){

		//待支付=应付-已支付-退款-结转
		var unpaidAmount = this.soOrder.payablePrice-this.soOrder.paidPrice-this.soOrder.refundPrice-this.soOrder.carryAmount;
		return unpaidAmount;
    },
    amountChange:function(newValue,oldValue){
    	
    	var unpaidAmount = this.getUnpaidAmount();
    	var amount = System.RMB.YuanToFen(newValue);
    	if(amount>unpaidAmount){
    		
    		IMessageBox.error('【订单分配金额】不能超过【待支付金额】');
    		return ;
    	}else{
    		
    		$('#amount').numberbox('setValue',System.RMB.FenToYuan(unpaidAmount));
    	}
    	
    	//支付类别怎么控制？hw 2018-03-21
    	
    },
    getPayMap:function(){

    	//校验未处理
    	
    	var payMap = new Object();
    	payMap.orderId = this.soOrder.id;
    	payMap.soOrder = this.soOrder;
    	payMap.offlineInstallmentType = $('#offlineInstallmentType').combobox('getValue');
    	
    	var amount = $('#amount').numberbox('getValue');
    	payMap.orderPrice = System.RMB.YuanToFen(amount);
    	
    	//分配明细
    	payMap.depPays = this.payDepDetailCtrl.getDepPays();
    	
    	debugger;
    	return payMap;
    }
});

com.gongsibao.trade.web.PayDepDetailCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
	
    ctor: function () {

    	this.base();
    	this.$gridId = '#pay_dep_grid';
    	this.service = 'com.gongsibao.trade.web.OrderPayController';
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
		        {field:'salesmanId',title:'业务员',width:100,formatter:function(value,row,index){
	        		
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
