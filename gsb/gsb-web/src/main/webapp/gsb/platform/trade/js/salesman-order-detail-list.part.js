System.Declare("com.gongsibao.trade.web");
//订单明细
com.gongsibao.trade.web.SalesmanOrderDetailListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
    },
    operateFormatter:function(value,row,index){
    	var builder = new System.StringBuilder();
    	builder.append("<a class='grid-btn' href='javascript:controllerorderProdList.detail(" + row.id + ")';>查看</a>");
    	if(row.beginOption == null || row.beginOption == false){    		
    		builder.append("<a class='grid-btn' href='javascript:controllerorderProdList.begOption(" + row.id + ")';>开始操作</a>");
    	}else{    		
    		builder.append("<a class='grid-btn' href='javascript:controllerorderProdList.operateGroup(" + row.id + ")';>变更操作组</a>");
    	}
    	return builder.toString();
    },
    begOption: function (id) {
    	//开始操作
    	var me = this;
    	var row = me.getSelectedItem();
    	var orderId = row.orderId;
    	var productId = row.productId;
    	var companyId = row.soOrder_companyId;//关联公司Id
    	var operateCompaneName = row.companyIntention_name;//获取订单产品表中叫操作公司(订单表中叫关联公司)
    	
    	//1.开始操作前提
    	me.invokeService("meetBegOption", [orderId], function (data) {
    		if(data == 2){
    			me.isHandle(id,productId,orderId,operateCompaneName,companyId);
    		}else if(data == 0){
    			layer.msg("请付全款或者申请分期，才能开始操作！");
                return false;
    		}else if(data == 1){
    			layer.msg("订单余额大于一期款时，才能开始操作！");
                return false;
    		}else if(data == 3){
    			layer.msg("订单分期审核中，审核通过，才能开始操作！");
                return false;
    		}else{
    			layer.msg("请核实订单信息！");
                return false;
    		}
        });
    },
    isHandle:function(id,productId,orderId,operateCompaneName,companyId){
    	//判断是否有办理名称 
    	var me = this;
    	me.invokeService("isHandle", [productId], function (data) {
    		me.companiesName(id,data,orderId,operateCompaneName,companyId);
        });
    },
    companiesName:function(id,isHandle,orderId,operateCompaneName,companyId){
    	//获取关联公司名称
    	var me = this;
    	me.invokeService("getCompaniesName", [orderId], function (data) {
    		me.staticForm(id,isHandle,data,operateCompaneName,companyId);
        });
    },
    staticForm: function(id,isHandle,companeName,operateCompaneName,companyId){
    	//静态表单(是否有办理名称、关联公司名称、操作公司名称、关联公司Id)
    	var me = this;
    	var pubControlList = new org.netsharp.controls.PubControlList();
        var items = pubControlList.getSupplierCombogrid();        
        var builder = new System.StringBuilder();    	
    	builder.append('<form id="dynamicForm">');
    	builder.append('<div style="margin:10px;">');
    	builder.append('<table cellpadding="5" cellspacing="10" class="form-panel">');
    	builder.append('<tr><td class="title">'+items.title+'</td><td><input id="'+items.id+'" style="'+items.style+'"/></td></tr>');
    	//是否显示办理名称
    	if(isHandle){
    		builder.append('<tr><td class="title">办理名称 </td><td><textarea id="txtHandle" style="width:300px;height:31px;" class="" ></textarea></td></tr>'); 
    	}
    	//是否显示操作公司
    	if(companeName != null && companeName != ""){
    		builder.append('<tr><td class="title"></td><td style="color:red;">订单明细（产品）操作公司：</td></tr>');
    		if(operateCompaneName == null || operateCompaneName == ""){
    			builder.append('<tr><td class="title"></td><td><input type="radio" name="czgs" checked="true" value="'+companyId+'"/>使用订单签约公司【' + companeName + '】</td></tr>');
    			builder.append('<tr><td class="title"></td><td><input type="radio" name="czgs" value="0"/>不更新订单明细（产品）操作公司</td></tr>');
    		}else{
    			builder.append('<tr><td class="title"></td><td><input type="radio" name="czgs" value="'+companyId+'"/>使用订单签约公司【' + companeName + '】</td></tr>');
    			builder.append('<tr><td class="title"></td><td><input type="radio" name="czgs" checked="true" value="0"/>不更新订单明细（产品）操作公司</td></tr>');
    		}
    	}
    	
    	
    	builder.append('	</table>');
    	builder.append('</div>');
    	builder.append('</form>');
    	layer.open({
    		type : 1,
    		title : '开始操作',
    		fixed : false,
    		maxmin : false,
    		shadeClose : true,
    		zIndex : 100000,
    		area : [ '460px','330px' ],
    		content : builder.toString(),
    		btn : [ '提交', '取消' ],
    		success : function(layero, index) {    			
    			$(items).each(function(i,item){
    				var expression = '$("#'+item.id+'").'+item.type+'(item.option);';
					eval(expression);
    			});
    		},
    		btn1 : function(index, layero) {
    			var supplierId = $('#supplier_name').combogrid('getValue');
                if (System.isnull(supplierId)) {
                    IMessageBox.info('请选择服务商');
                    return;
                }
                var txtHandle = typeof($("#txtHandle").val()) == "undefined" ? "" : $("#txtHandle").val();
                var seleValue = typeof($('input[name="czgs"]:checked ').val())  == "undefined" ? 0 : $('input[name="czgs"]:checked ').val();
                
               //更新订单明细
                me.invokeService("begOption", [id,supplierId,txtHandle,seleValue], function (data) {
                    me.reload();
                    IMessageBox.toast('操作成功');
                    layer.closeAll();
                    return;
                });
    		}
    	});
    },
    operateGroup: function(id){    	
    	//变更操作组
    	var me = this;
        var pubControlList = new org.netsharp.controls.PubControlList();
        PandaHelper.openDynamicForm({
            title: "选择操作组",
            width: 450,
            height: 300,
            items: [
                pubControlList.getSupplierCombogrid()
            ],
            callback: function (index, layero) {                
            	var supplierId = $('#supplier_name').combogrid('getValue');
                if (System.isnull(supplierId)) {
                    IMessageBox.info('请选择服务商');
                    return;
                }
                
               me.invokeService("operateGroup", [id, supplierId], function (data) {
                    me.reload();
                    IMessageBox.toast('操作成功');
                    layer.closeAll();
                    return;
                });
            }
        });
    },
    detail: function (id) {
        var rows = this.getSelections();
        if (id == null && rows.length != 1) {
            IMessageBox.info('请先选择一条订单数据');
            return false;
        }
        var row = id == null ? this.getSelectedItem() : {};
        id = id == null ? row.id : id;
        var url = '/nav/gsb/platform/trade/orderProdDetail?id=' + id;
        window.open(url);
    },
    contactFormatter: function (value, row, index, typeName) {    	
        if (value) {
            var ctrl = workspace.parts.byIndex(0).key;
            return '<sapn>' + PandaHelper.dimString(value) + '</span><i class="fa fa-eye" onclick="' + ctrl + '.showPlaintext(\'' + row.customerId + '\',\'' + value + '\',\'' + typeName + '\',this);"></i>';
        }
    }, 
    showPlaintext: function (customerId, value, typeName, obj) {
        $(obj).parent().text(value);
    }
});