System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.QueryInteractionGroupCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
        this.base();
        this.multiple = false;
        this.$gridCtrlId = '#interaction_group';
        this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    },
    open: function (title, multiple, callback) {

        this.multiple = multiple;
        var me = this;
        var builder = new System.StringBuilder();
        builder.append('<div style="margin:0 10px;">');
        builder.append('	<table cellpadding="5" cellspacing="5" class="form-panel">');
        builder.append('		<tr><td style="color:red;">提示：填写关键字后敲回车执行查询</td></tr>');
        builder.append('		<tr><td><input id="interaction_group_keyword"/></td></tr>');
        builder.append('		<tr><td>订单应付金额：<span id="payablePrice" style="margin-right: 10px;"></span>订单余额：<span id="balance" style="margin-right: 10px;"></span>订单关联公司：<span id="companyName"></span></td><td></td></tr>');
        builder.append('		<tr><td><table id="interaction_group"></table></td></tr>');
        builder.append('	</table>');
        builder.append('</div>');

        layer.open({
            type: 1,
            title: title,
            fixed: false,
            maxmin: false,
            shadeClose: true,
            zIndex: 100000,
            area: ['850px', '440px'],
            content: builder.toString(),
            success: function (layero, index) {
                me.initGrid();
                $("#interaction_group_keyword").searchbox({
                    height: 30,
                    width: 560,
                    prompt: '订单编号',
                    searcher: function (value, name) {
                        me.query();
                    }
                });
            }
        });
    },
    initGrid: function () {
        var me = this;
        $(this.$gridCtrlId).datagrid({
            idField: 'id',
            height: 240,
            width: 800,
            striped: true,
            pagination: true,
            showFooter: true,
            pageSize: 10,
            singleSelect: !this.multiple,
            onLoadSuccess: function (data) {
                var pager = $(this).datagrid('getPager');
                $(pager).pagination('options').onSelectPage = function (pageNumber, pageSize) {
                    me.query(pageNumber, pageSize);
                };
            },
            columns: [[
                {field: 'id', checkbox: true, align: 'center'},
                {
                    field: 'orderProdId', title: '明细订单号', width: 80, formatter: function (value, row, index) {
                        return row.orderProdId == null ? "无" : row.orderProdId;
                    }
                },
                {
                    field: 'supplierName', title: '操作组名称', width: 150, formatter: function (value, row, index) {
                        return row.supplierName == null || row.supplierName == "" ? "无" : row.supplierName;
                    }
                },
                {
                    field: 'operator', title: '操作员', width: 100, formatter: function (value, row, index) {
                        return row.operator == null || row.operator == "" ? "无" : row.operator;
                    }
                },
                {
                    field: 'productName', title: '产品名称', width: 120, formatter: function (value, row, index) {
                        return row.orderProd == null ? "无" : row.orderProd.productName;
                    }
                },
                {
                    field: 'cityName', title: '地区', width: 150, formatter: function (value, row, index) {
                        return row.orderProd == null ? "无" : row.orderProd.cityName;
                    }
                },
                {
                    field: 'companyName', title: '明细订单公司', width: 150, formatter: function (value, row, index) {
                        return row.orderProd == null || row.orderProd.companyIntention == null || row.orderProd.companyIntention.companyName == null ? "无" : row.orderProd.companyIntention.companyName;
                    }
                }
            ]]
        });
    },
    query: function (pageIndex, pageSize) {
        pageIndex = pageIndex || 1;
        pageSize = pageSize || 10;
        var me = this;
        var keyWord = $('#interaction_group_keyword').searchbox('getValue');
        if (System.isnull(keyWord)) {
            IMessageBox.info('请输入订单编号');
            return false;
        }
        me.clearOrderInfo();
        //去掉空格
        keyWord = keyWord.replace(/^\s+|\s+$/g, '');
        this.invokeService("queryInteractionGroup", [keyWord, pageIndex, pageSize], function (data) {
            $(me.$gridCtrlId).datagrid('loadData', data);
            //设置订单金额信息
            me.setOrderInfo(data, keyWord);
        });
    },
    setOrderInfo: function (orderProdData, keyWord) {//设置订单信息
        //获取订单金额信息
        if (orderProdData.rows.length > 0) {
            var serviceLocator = new org.netsharp.core.JServiceLocator();
            serviceLocator.invoke("com.gongsibao.trade.web.SalesmanAllOrderListPart", "getOrderByOrderNo", [keyWord], function (data) {
                    var payablePrice = System.RMB.fenToYuan(data.payablePrice);
                    //balance = paidPrice + carryIntoAmount - refundPrice - carryAmount;
                    var paidPrice = data.paidPrice == null ? 0 : data.paidPrice;
                    var carryIntoAmount = data.carryIntoAmount == null ? 0 : data.carryIntoAmount;
                    var refundPrice = data.refundPrice == null ? 0 : data.refundPrice;
                    var carryAmount = data.carryAmount == null ? 0 : data.carryAmount;
                    var balance = System.RMB.fenToYuan(paidPrice + carryIntoAmount - refundPrice - carryAmount);
                    var companyName = data.companyIntention == null ? "无" : data.companyIntention.companyName;
                    $("#payablePrice").text(payablePrice);
                    $("#balance").text(balance);
                    $("#companyName").text(companyName);
                }, null, false
            );
        }
    },
    clearOrderInfo: function () {
        $("#payablePrice").text("");
        $("#balance").text("");
        $("#companyName").text("");
    }
});