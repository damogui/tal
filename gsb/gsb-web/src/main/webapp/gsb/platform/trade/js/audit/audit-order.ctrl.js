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

		    		me.initAuditLog(orderId,1042);
		    		
		    	}else if (title == '分期信息') {
		    		
                    me.initStage(orderId);

                }else if (title == '附件信息') {
		    		
                    me.initFile(orderId);

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
    initStage:function(orderId){

        var me = this;
        this.invokeService("queryStageList", [orderId], function (data) {

            $('#order_stage_grid').datagrid({
                idField: 'id',
                emptyMsg: '暂无记录',
                striped: true,
                pagination: false,
                showFooter: true,
                singleSelect: true,
                height: '100%',
                data: data,
                columns: [[

                    {
                        field: 'instalmentIndex',
                        title: '分期期数',
                        width: 100,
                        align: 'center',
                        formatter: function (value, row, index) {
                            return '第'+value+'期';
                        }
                    },
                    {
                        field: 'amount',
                        title: '分期金额',
                        width: 100,
                        align: 'right',
                        formatter: function (value, row, index) {
                            return System.RMB.fenToYuan(value);
                        }
                    },
                    {field: 'creator', title: '创建人', width: 100, align: 'center'},
                    {field: 'createTime', title: '创建时间', width: 130, align: 'center'}
                ]]
            });
        });
    },
    initFile:function(orderId){

        var me = this;
        this.invokeService("queryOrderFileList", [orderId], function (data) {

            $('#order_file_grid').datagrid({
                idField: 'id',
                emptyMsg: '暂无记录',
                striped: true,
                pagination: false,
                showFooter: true,
                singleSelect: true,
                height: '100%',
                data: data,
                columns: [[

                    {field: 'url', title: '操作', width: 80, align: 'center',formatter: function (value, row, index) {

                        return  '<a class="grid-btn" href="javascript:window.open(\'' + row.url + '\');">查看</a>';
                    }},
                    {field: 'name', title: '名称', width: 200},
                    {field: 'creator', title: '上传人', width: 80, align: 'center'},
                    {field: 'createTime', title: '上传时间', width: 130, align: 'center'}
                ]]
            });
        });
    }
});
