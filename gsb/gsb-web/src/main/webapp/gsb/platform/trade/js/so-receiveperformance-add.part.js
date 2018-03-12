System.Declare("com.gongsibao.trade.web");
//创建回款业绩
com.gongsibao.trade.web.OrderReceivePerformanceDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({

    ctor: function () {
        this.base();
        this.addUrl = null;
        this.editUrl = null;
        this.followUrl = null;
        this.addCustomerUrl = null;

    },
    init: function () {
        debugger;
        var me = this;
        var orderId = this.queryString('id');
        this.invokeService("queryProductList", [orderId], function (data) {

            me.initGrid(data);
        });
    },
    initGrid: function (data) {
        var me = this;
        $('#order_product_grid').datagrid({
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
                    field: 'a', title: '操作', width: 80, align: 'center', formatter: function (value, row, index) {

                    return '<a class="grid-btn" href="javascript:;">查看</a>';
                }
                },
                {field: 'id', title: '服务商', width: 100, align: 'center'},
                {field: 'productName', title: '部门', width: 150},

                {field: 'cityName', title: '业务员', width: 150},
                // {
                //     field: 'priceOriginal',
                //     title: '原价',
                //     width: 100,
                //     align: 'right',
                //     formatter: function (value, row, index) {
                //         return (value / 100).toFixed(2);
                //     }
                // },
                {
                    field: 'price', title: '回款业绩分配金额', width: 100, align: 'right', formatter: function (value, row, index) {

                    // return (value / 100).toFixed(2);
                }
                }

            ]]
        });
    },
    add: function () {
        var me = this;
        var formId = System.GUID.newGUID();
        var builder = new System.StringBuilder();
        // builder.append(' <table cellpadding="3" cellspacing="0" class="form-panel"> <tbody> <tr> <td width="120px" class="label_td"> <label for="no"> 订单编号： </label> </td> <td class="control_td"> <input validtype="unnormal" type="text" collected="true"  controltype="TextBox" id="no" style="width:180px;" class="easyui-validatebox nsInput validatebox-text validatebox-disabled" data-options="validateOnCreate:false,validateOnBlur:true,required:false"> </td> <td width="120px" class="label_td"> <label for="payablePrice"> 订单分配金额： </label> </td> <td class="control_td"> <input validtype="unnormal" type="text" collected="true"  controltype="TextBox" id="payablePrice" style="width:180px;" class="easyui-validatebox nsInput validatebox-text validatebox-disabled" data-options="validateOnCreate:false,validateOnBlur:true,required:false"> </td> <td width="120px" class="label_td"> <label for="paidPrice"> 付款类别： </label> </td> <td class="control_td"> <input validtype="unnormal" type="text" collected="true" controltype="TextBox" id="paidPrice" style="width:180px;" class="easyui-validatebox nsInput validatebox-text validatebox-disabled" data-options="validateOnCreate:false,validateOnBlur:true,required:false"> </td> </tr> </tbody> </table>');


        builder.append('<form id="dynamicForm">');
        builder.append('<div style="margin:10px;">');
        builder.append('<table cellpadding="3" cellspacing="0" class="form-panel" style="width:100%;">');
        builder.append('<tr><td class="title" style="width:100px;text-align: right;"><label style="color:Red">*</label> 订单编号</td><td><input id="no"/></td>');
        builder.append('<td class="title" style="width:100px;text-align: right;"><label style="color:Red">*</label> 订单分配金额</td><td><input id="payablePrice"/> </td>');
        builder.append('<td class="title" style="width:100px;text-align: right;"><label style="color:Red">*</label> 付款类别</td><td><input id="paidPrice"/> </td></tr>');
        builder.append('</table>');
        builder.append('</div>');
        builder.append('</form>');
        builder.append('<div class="panel-header" style="width: 1019px;"><div class="panel-title">业绩划分</div><div class="panel-tool"></div></div>');
        builder.append('<div id="controllerdepReceivableToolbar" class="toolbar datagrid-toolbar"> <a iconcls="fa fa-plus fa-fw"  plain="true" id="controllerdepReceivableperformanceDetailAdd" class="easyui-linkbutton l-btn l-btn-small l-btn-plain" code="performanceDetailAdd" group=""><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">新增</span><span class="l-btn-icon fa fa-plus fa-fw">&nbsp;</span></span></a> <a iconcls="fa fa-trash-o"  plain="true" id="controllerdepReceivableperformanceDetailDel" class="easyui-linkbutton l-btn l-btn-small l-btn-plain" code="performanceDetailDel" group=""><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">删除</span><span class="l-btn-icon fa fa-trash-o">&nbsp;</span></span></a> </div>');

        builder.append('  <table id="order_product_grid"></table>');

        layer.open({
            type: 1,
            title: '添加回款订单',
            fixed: false,
            maxmin: false,
            shadeClose: true,
            zIndex: 100000,
            area: ['70%', '70%'],
            content: builder.toString(),
            btn: ['提交', '取消'],
            success: function (layero, index) {
               // me.initializeCtrl();

            },
            yes: function (index, layero) {

                // var orderProd = me.getOrderProd();
                // callback(orderProd);
                layer.closeAll();
            }
        });

        me.initGrid();

    },
    bankBooksChange: function (newValue, oldValue) {//账套联动
        debugger;

        //改变部门的查询条件
        $('#pays_u8Bank_name').combogrid('clear');
        var grid = $('#pays_u8Bank_name').combogrid('grid');
        var options = $(grid).datagrid('options');
        var filter = ' set_of_books_id ____ ----' + newValue + '----';
        options.url = '\/panda\/rest\/reference?code=SupplierU8Bank&filter=' + filter;
        $(grid).datagrid(options);

    }

});



