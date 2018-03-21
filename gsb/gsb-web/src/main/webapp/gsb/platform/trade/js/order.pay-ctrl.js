System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderPayCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderPayController';
    },
    init:function(){

		var centerHeight = $('body').height() - 240;
		$('#center').height(centerHeight);
		
		$('#detail_tabs').tabs({
			fit:true,
			tabHeight:35
		});
		
		this.bindSetOfBooks();
		
		this.payVoucherDetailCtrl = new com.gongsibao.trade.web.PayVoucherDetailCtrl();
		this.payVoucherDetailCtrl.init();
    	
    	this.relevancePerformanceCtrl = new com.gongsibao.trade.web.OrderRelevancePerformanceCtrl();
    	this.relevancePerformanceCtrl.init();
    },
    bindSetOfBooks:function(){
    	
    	this.invokeService ("querySetOfBooksList", [], function(data){
			
    		$('#setOfBooksId').combobox('loadData',data);
		});
    },
    isOnlineChange:function(checked){
    	
    	alert(checked);
    },
    refundTypeChange:function(newValue,oldValue){
    	
    	//全款退
    	if(newValue==='1'){

    		//这里有很多种情况判断，如：结转，已存在退款
    		var paidPrice = parseFloat($('#paidPrice').text());
    		if(paidPrice>0){
    			
    			$('#amount').numberbox('setValue',paidPrice).numberbox('readonly',true);
    			
    			//如果表格只有一行，则直接设置退款金额
    			var refundAmount = paidPrice*100;
    			this.productDetailCtrl.setRefundAmount(refundAmount);
    		}else{
    			
    			$('#amount').numberbox('readonly',false);
    		}
    	}
    },
    save:function(){

    	//还没有做校验 hw 2018-03-13
    	
    	var orderId = this.queryString('id');
    	var refund = new Object();
    	refund.orderId = orderId;
    	refund.setOfBooksId = $('#setOfBooksId').combogrid('getValue');
    	refund.refundType =  $('#refundType').combobox('getValue');
    	refund.payerName =  $('#payerName').val();
    	refund.bankNo =  $('#bankNo').val();
    	refund.amount =  parseFloat($('#amount').numberbox('getValue'))*100;
    	refund.remark =  $('#refundRemark').val();
    	//退款产品
    	var refundProductRows = $('#order_product_grid').datagrid('getRows');
    	var itemList = [];
    	for(var i=0;i<refundProductRows.length;i++){
    		
    		var prod = refundProductRows[i];
    		if(!System.isnull(prod.refundAmount) && parseFloat(prod.refundAmount) >0){

        		var item = new Object();
        		item.orderId = orderId;
        		item.orderProdId = prod.id;
        		item.amount = prod.refundAmount;
        		itemList.push(item);
    		}
    	}
    	refund.refunds = itemList;
    	
    	//退款业绩分配
    	var depRefunds = $('#order_refund_grid').datagrid('getRows');
    	refund.depRefunds = depRefunds;
    	
    	var me = this;
    	IMessageBox.confirm('确定提交申请吗？',function(r){
    		
    		if(r){

    			me.invokeService("applyRefund", [refund], function(data){
    				IMessageBox.info('申请成功，请等待审核!',function(s){
    	    			window.parent.layer.closeAll();
    	    		});
    	    	});
    		}
    	});
    }
});

com.gongsibao.trade.web.PayVoucherDetailCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {

    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderPayController';
    	this.$gridId = '#pay_voucher_grid';
    	this.processStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderProcessStatusType');
    },
    init:function(){

    	this.initGrid();
    	this.initUpload();
    },
	initGrid:function(){
	    
		var me = this;
		$(this.$gridId).datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			singleSelect:true,
			height:'100%',
			toolbar: '#upload_toolbar',
		    columns:[[
		        {field:'id',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	return '<a class="grid-btn" href="javascript:;">查看</a> <a class="grid-btn" href="javascript:;">删除</a>';
		        }},
		        {field:'name',title:'文件名称',width:250},
		        {field:'createTime',title:'上传时间',width:130,align:'center',formatter:function(value,row,index){
	        		
		        }},
		        {field:'creator',title:'创建人',width:80,align:'center'}
		    ]]
		});
	},
	initUpload:function(){

		var upload = new org.netsharp.controls.PayVoucherUpload();
		upload.parent = this;
		upload.init();
	},
	add: function (path,file) {

    	//构建凭证对象并插入到表格中
    },
	remove:function(){
		
		alert('删除文件！');
	}
});


/***
 * 
 * 凭证上传组件
 */
org.netsharp.controls.PayVoucherUpload = org.netsharp.controls.OSSUpload.Extends({
	ctor: function() {
		this.base();
		this.multi_selection = true;
		this.parent = null;
	},
	getButtonId:function(){
		
		return "btn_upload";
	},
	preview:function(path,file){
		
		if(System.isnull(path)){
			return;
		}
		this.parent.add(path,file);
	}
});

com.gongsibao.trade.web.OrderRelevancePerformanceCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {

    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderPayController';
    	this.$gridId = '#order_relevance_grid';
    },
    init:function(){

    	this.initGrid();
    },
	initGrid:function(){
		
		var me = this;
		$(this.$gridId).datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
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

		        {field:'orderId',title:'订单号',align:'center',width:150,formatter:function(value,row,index){
	
		        	if(row.order){
		        		
		        		return row.order.no;
		        	}
		        }},
		        {field:'orderPrice',title:'订单分配金额',align:'center',width:100,formatter:function(value,row,index){
		        	
		        	return System.RMB.FenToYun(value);
		        }},
		        {field:'offlineInstallmentType',title:'付款类别',align:'center',width:100,formatter:function(value,row,index){
		        	
		        }},
		        {field:'supplierId',title:'服务商',width:200,align:'right',formatter:function(value,row,index){
		        		
		        	if(row.supplier){
		        		
		        		return row.supplier.name;
		        	}
		        }},
		        {field:'departmentId',title:'部门',width:100,align:'right',formatter:function(value,row,index){
	        		
		        	if(row.department){
		        		
		        		return row.department.name;
		        	}
		        }},
		        {field:'employeeId',title:'业务员',width:100,align:'right',formatter:function(value,row,index){
	        		
		        	if(row.employee){
		        		
		        		return row.employee.name;
		        	}
		        }},
		        {field:'amount',title:'分配金额',width:100,align:'right',formatter:function(value,row,index){
	        		
		        	return System.RMB.FenToYun(value);
		        }}
		    ]]
		});
	},
	add:function(){
		
		var url = '/nav/gsb/platform/trade/orderPayMap';
        layer.open({
            type: 2,
            title: '关联订单',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['600px', '500px'],
            zIndex: 100000,
            id: "orderPayMap",
            content: url,
            btn: ['保存', '取消'],
            yes: function (index, layero) {

                layer.closeAll();
                var iframeWindow = document.getElementById('orderPayMap').firstElementChild.contentWindow;
                iframeWindow.controllersoOrder.save();
                IMessageBox.toast('保存成功');
            }
        });
	},

	remove:function(){
		
		alert('删除关联订单');
		var row = $(this.$gridId).datagrid('getSelected');
		if(row == null){
			
			return;
		}
		
		//提示确认
		var index = $(this.$gridId).datagrid('getRowIndex',row);
		$(this.$gridId).datagrid('deleteRow',index);
	}
});




