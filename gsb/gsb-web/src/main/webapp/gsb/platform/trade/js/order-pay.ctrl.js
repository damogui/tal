System.Declare("com.gongsibao.trade.web");

/***
 * 
 * 回款主控制器
 */
com.gongsibao.trade.web.OrderPayCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderPayController';
    	
    	//是否一笔多单：默认否
    	this.payForOrderCount = false;
    },
    init:function(){

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
    setOfBooksIdChange:function(newValue,oldValue){
    	
    	this.invokeService ("queryU8BankList", [newValue], function(data){
			
    		$('#u8BankId').combobox('clear').combobox('loadData',data);
		});
    },
    getPay:function(){
    	
		var setOfBooksId = $('#setOfBooksId').combobox('getValue');
		if(System.isnull(setOfBooksId)){
			
    		layer.msg('请选择【付款账套】！');
			return null;
		}
		
		var u8BankId = $('#u8BankId').combobox('getValue');
		if(System.isnull(u8BankId)){
			
    		layer.msg('请选择【付款方式】！');
			return null;
		}
		
		var offlinePayerName = $('#offlinePayerName').val();
		if(System.isnull(offlinePayerName)){
			
    		layer.msg('请填写【付款账户名称】！');
			return null;
		}
		
		var offlineBankNo = $('#offlineBankNo').val();
		if(System.isnull(offlineBankNo)){
			
    		layer.msg('请填写【付款账号】！');
			return null;
		}
		
		var amount = $('#amount').numberbox('getValue');
		if(System.isnull(amount)){
			
    		layer.msg('请填写【付款金额】！');
			return null;
		}
		
		var pay = new Object();
		pay.setOfBooksId = setOfBooksId;
		pay.u8BankId = u8BankId;
		pay.offlinePayerName = offlinePayerName;
		pay.offlineBankNo = offlineBankNo;
		pay.amount = System.RMB.yuanToFen(amount);
		pay.payForOrderCount = this.payForOrderCount;
		pay.offlineRemark = $('#offlineRemark').val();
		return pay;
    },
    save:function(){
    	
		var pay = this.getPay();
		if(pay == null){
			
			return;
		}
		
		//校验是否上传凭证
		var voucherFiles = this.payVoucherDetailCtrl.getRows();
		if(voucherFiles.length == 0){
			
			layer.msg('请上传【付款凭证】！');
			return;
		}
		
		var relevanceRows = this.relevancePerformanceCtrl.getRows();
        
		if(relevanceRows.length==0){
			
			layer.msg('请创建【关联订单】！');
			return;
		}
		
		//校验关联订单的总金额是否==支付金额
		var amount = $('#amount').numberbox('getValue');
		var payAmount = System.RMB.yuanToFen(amount);
		
		var allotTotalAmount = 0;
		$(relevanceRows).each(function(i,item){
			
			allotTotalAmount+=item.orderPrice;
            item.orderNo=item.soOrder.no;
		});
		
		if(allotTotalAmount != payAmount){
			
			layer.msg('【关联订单分配金额总和】与【付款金额】不相等！');
			return;
		}
		
		pay.files = voucherFiles;
		pay.orderPayMaps = relevanceRows;
		
		//使用同步提交
		var isSave = false;
		this.invokeService("applyPay",[pay],function(data){
			
			isSave = data;
			
		},false);
		return isSave;
    }
});


/***
 * 
 * 凭证明细控制器
 */
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
	    
		var data =[]; //[{name:'1.png',url:'http://gsb-public.oss-cn-beijing.aliyuncs.com/netsharp_BrPabywwYSdk7pw2PJiahxrAYpfQehFR.png'}];
		
		var me = this;
		$(this.$gridId).datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			striped:true,
			pagination:false,
			showFooter:true,
			singleSelect:true,
			data:data,
			height:'100%',
			toolbar: '#upload_toolbar',
		    columns:[[
		        {field:'id',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	var str = '<a class="grid-btn" href="javascript:window.open(\''+row.url+'\');">查看</a> \
		        			   <a class="grid-btn" href="javascript:payCtrl.payVoucherDetailCtrl.remove('+index+');">删除</a>';
		        	return str;
		        }},
		        {field:'name',title:'文件名称',width:250},
//		        {field:'createTime',title:'上传时间',width:130,align:'center',formatter:function(value,row,index){
//	        		
//		        }},
//		        {field:'creator',title:'创建人',width:80,align:'center'}
		    ]]
		});
	},
	initUpload:function(){

		var upload = new org.netsharp.controls.PayVoucherUpload();
		upload.parent = this;
		upload.init();
	},
	add: function (path,file) {

	    var voucherFile = new Object();
	    voucherFile.name = file.name;
	    voucherFile.url = path;
	    $(this.$gridId).datagrid('appendRow',voucherFile);
	    
    },
    remove:function(index){
    	
    	$(this.$gridId).datagrid('deleteRow',index);
    },
	getRows:function(){
		
		var rows = $(this.$gridId).datagrid('getRows');
		return rows;
	}
});

/***
 * 
 * 关联订单控制器
 */
com.gongsibao.trade.web.OrderRelevancePerformanceCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {

    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderPayController';
    	this.$gridId = '#order_relevance_grid';
    	this.payOfflineInstallmentTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.PayOfflineInstallmentType');
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
	
		        	if(row.soOrder){
		        		
		        		return row.soOrder.no;
		        	}
		        }},
		        {field:'offlineInstallmentType',title:'付款类别',align:'center',width:100,formatter:function(value,row,index){
		        	
		        	return me.payOfflineInstallmentTypeEnum[value];
		        }},
		        {field:'orderPrice',title:'订单分配金额',align:'right',width:100,formatter:function(value,row,index){
		        	
		        	return System.RMB.fenToYuan(value);
		        }}]]
		});
	},
	add:function(){
		
		var pay = payCtrl.getPay();
		if(pay == null){
			
			return;
		}
		
		if(payCtrl.payForOrderCount===false){
			
			//一笔一单：不允许再新增【关联订单】
			
			var relevanceRows = this.getRows();
			if(relevanceRows.length>=1){
				
				layer.msg('【一笔一单】情况下只允许关联1张订单！');
				return;
			}
		}
		
		var me = this;
		var url = '/nav/gsb/platform/trade/orderPayMap';
        layer.open({
            type: 2,
            title: '关联订单',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['400px', '380px'],
            zIndex: 100000,
            id: "orderPayMap",
            content: url,
            btn: ['保存', '取消'],
            yes: function (index, layero) {

                var iframeWindow = document.getElementById('orderPayMap').firstElementChild.contentWindow;
                var payMap = iframeWindow.payMapCtrl.getPayMap();
                if(payMap == undefined){
                	return;
                }
                layer.closeAll();
                $(me.$gridId).datagrid('appendRow',payMap);
            }
        });
	},
	getRows:function(){
		
		var rows = $(this.$gridId).datagrid('getRows');
		return rows;
	},
	remove:function(){
		
		var row = $(this.$gridId).datagrid('getSelected');
		if(row == null){
			
			return;
		}
		
		//提示确认
		var index = $(this.$gridId).datagrid('getRowIndex',row);
		$(this.$gridId).datagrid('deleteRow',index);
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


