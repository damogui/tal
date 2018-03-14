System.Declare("com.gongsibao.trade.web");

com.gongsibao.trade.web.SoCreatReceivePerformanceFormPart = org.netsharp.panda.commerce.FormPart.Extends({

    save: function () {
        var me = this;
        var depPayMapDTO = {};
        debugger;
        depPayMapDTO.orderPayMaps = []; //$("#pays_u8Bank_setOfBooks_name").textbox("getValue");
        depPayMapDTO.setOfBooks = $("#pays_u8Bank_setOfBooks_name").combogrid("getValue");
        depPayMapDTO.u8Bank = $("#pays_u8Bank_name").combogrid("getValue");
        depPayMapDTO.offlinePayerName = $("#offlinePayerName").val();
        depPayMapDTO.offlineBankNo = $("#offlineBankNo").val();
        depPayMapDTO.payForOrderCount = $("#payForOrderCount")[0].checked;
        depPayMapDTO.amount = $("#amount").val();
        debugger;
        depPayMapDTO.files = $("#files").val();
        depPayMapDTO.offlineRemark = $("#offlineRemark").val();

        var rows = $('#datagridpays').datagrid('getRows');//添加的行

        var orderRelations = [];
        $(rows).each(function (i, item) {
            var orderRelation = {};
            orderRelation.orderId = item.orderId;
            orderRelation.orderCutAmount = item.orderCutAmount;
            orderRelation.payType = item.payType;

            orderRelation.items = item.items;
            orderRelations.push(orderRelation);
        });
        depPayMapDTO.orderRelations = orderRelations;

        me.invokeService('saveNDepReceivableBySoder', [depPayMapDTO], function (data) {

            if (data > 0) {

                IMessageBox.toast('保存成功');

            } else {

                IMessageBox.toast('保存失败');
            }
        });

        alert("保存");
        return;

    },
});
//创建回款业绩
var depPayMapDTO = [];//关联回款订单的入库实体
com.gongsibao.trade.web.OrderReceivePerformanceDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({

    ctor: function () {
        this.base();
        this.init();

    }, init: function () {
        $("body").off("click", "#peperformanceAdd");
        $("body").on("click", "#peperformanceAdd", function () {

            var me = this;
            var supplierOption = getSupplierOption();
            var departmentOption = getDepartmentOption();
            var employeeOption = getEmployeeOption();
            PandaHelper.openDynamicForm({
                title: '回款业绩分配金额',
                width: 450,
                height: 400,
                items: [{
                    id: 'cut_supplier_name',
                    title: '服务商',
                    type: 'combogrid',
                    className: '',
                    option: supplierOption
                },

                    {
                        id: 'cut_department_name',
                        title: '部门',
                        type: 'combogrid',
                        className: '',
                        option: departmentOption
                    },

                    {
                        id: 'cut_employee_name',
                        title: '业务员',
                        type: 'combogrid',
                        className: '',
                        option: employeeOption
                    },
                    {
                        id: 'cut_amount',
                        title: '回款业绩分配',
                        type: 'numberbox',
                        className: '',
                        option: employeeOption
                    }
                ],
                callback: function (index, layero) {

                    var supplierId = $('#cut_supplier_name').combogrid('getValue');
                    var departmentId = $('#cut_department_name').combogrid('getValue');
                    var toUserId = $('#cut_employee_name').combogrid('getValue');
                    var cutamount = $('#cut_amount').numberbox('getValue');

                    var suppliername = $('#cut_supplier_name').combogrid('getText');
                    var departmentname = $('#cut_department_name').combogrid('getText');
                    var toUsername = $('#cut_employee_name').combogrid('getText');


                    if (System.isnull(supplierId)) {
                        IMessageBox.info('请选择服务商');
                        return;
                    }
                    if (System.isnull(departmentId)) {
                        IMessageBox.info('请选择部门');
                        return;
                    }
                    if (System.isnull(toUserId)) {
                        IMessageBox.info('请选择业务员');
                        return;
                    }
                    if (System.isnull(cutamount)) {
                        IMessageBox.info('请输入金额');
                        return;
                    }
                    layer.close(index);
                    //layer.closeAll();
                    var orderDepPay = {};//业绩划分弹窗数据
                    orderDepPay.supplierId = supplierId;
                    orderDepPay.departmentId = departmentId;
                    orderDepPay.salesmanId = toUserId;
                    orderDepPay.amount = cutamount;
                    orderDepPay.suppliername = suppliername;
                    orderDepPay.departmentname = departmentname;
                    orderDepPay.salesmanname = toUsername;
                    $('#order_product_grid').datagrid('appendRow', orderDepPay);//赋值
                    return;

                }
            });

        });


        $("body").off("click", "#peperformanceDel");
        $("body").on("click", "#peperformanceDel", function () {

            var rows = $('#order_product_grid').datagrid('getSelected');
            if (rows.length != undefined) {
                for (var i = rows.length - 1; i >= 0; i--) {
                    var index = $('#order_product_grid').datagrid('getRowIndex', rows[i]);
                    $('#order_product_grid').datagrid('deleteRow', index);
                }
            }
            else {

                var index = $('#order_product_grid').datagrid('getRowIndex', rows);
                $('#order_product_grid').datagrid('deleteRow', index);
            }

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
                {field: 'id', checkbox: true},
                {field: 'suppliername', title: '服务商', width: 100, align: 'center'},
                {field: 'departmentname', title: '部门', width: 150},

                {field: 'salesmanname', title: '业务员', width: 150},

                {
                    field: 'amount',
                    title: '回款业绩分配',
                    width: 100,
                    align: 'right',
                    formatter: function (value, row, index) {
                        return value;

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
        builder.append('<form id="addFormPerformance">');
        builder.append('<div style="margin:10px;">');
        builder.append('<table cellpadding="3" cellspacing="0" class="form-panel" style="width:100%;">');
        builder.append('<tr><td class="title" style="width:100px;text-align: right;">订单编号</td><td><input id="orderNo" class="easyui-numberbox"/></td>');
        builder.append('<td class="title" style="width:100px;text-align: right;"> 订单分配金额</td><td><input id="orderCutPrice"  class="easyui-numberbox"/> </td>');
        builder.append('<td class="title" style="width:100px;text-align: right;"> 付款类别</td><td> <input id="payType" class="easyui-combobox" /></td></tr>');
        builder.append('</table>');
        builder.append('</div>');
        builder.append('</form>');
        builder.append('<div class="panel-header" style="width: 1019px;"><div class="panel-title">业绩划分</div><div class="panel-tool"></div></div>');
        builder.append('<div id="controllerdepReceivableToolbar" class="toolbar datagrid-toolbar"> <a iconcls="fa fa-plus fa-fw"  plain="true" id="peperformanceAdd" class="easyui-linkbutton l-btn l-btn-small l-btn-plain"  group=""><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">新增</span><span class="l-btn-icon fa fa-plus fa-fw">&nbsp;</span></span></a> <a iconcls="fa fa-trash-o"  plain="true" id="peperformanceDel" class="easyui-linkbutton l-btn l-btn-small l-btn-plain" code="performanceDetailDel" group=""><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">删除</span><span class="l-btn-icon fa fa-trash-o">&nbsp;</span></span></a> </div>');

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
                var options = {
                    editable: false,
                    valueField: 'value',
                    textField: 'text',
                    width: 150,
                    method: 'get',
                    url: '/panda/rest/enum?name=com.gongsibao.entity.trade.dic.OrderStageNum',
                    onLoadSuccess: function () {  //加载完成后,设置选中第一项
                        var val = $(this).combobox("getData");
                        for (var item in val[0]) {
                            if (item == "value") {
                                $(this).combobox("select", val[0][item]);
                            }
                        }
                    }


                };
                $('#orderNo').numberbox({
                    // min:0,
                    // precision:2
                });

                $("#orderCutPrice").numberbox();
                $("#payType").combobox(options);

            },
            yes: function (index, layero) {


                alert("校验订单号是否存在");
                //进行绑定数据
                var orderBack = me.getOrderBack();

                $('#datagridpays').datagrid('appendRow', orderBack);//赋值

                layer.closeAll();
            }
        });
        me.initGrid();


    },
    getOrderBack: function () {

        // //构建关联回款订单
        var rows = $('#order_product_grid').datagrid('getRows');//添加的行

        var orderBack = {};
        orderBack.orderId = $("#orderNo").numberbox("getValue");
        orderBack.orderCutAmount = $("#orderCutPrice").numberbox("getValue");
        orderBack.payType = $("#payType").combogrid("getValue");
        orderBack.payTypeStr = $("#payType").combogrid("getText");


        var suppliernameStr = "";
        var departmentnameStr = "";
        var salesmannameStr = "";
        var amountStr = "";
        var items = [];
        $(rows).each(function (i, item) {
            suppliernameStr += '<p>' + item.suppliername + '</p>';
            departmentnameStr += '<p>' + item.departmentname + '</p>';
            salesmannameStr += '<p>' + item.salesmanname + '</p>';
            amountStr += '<p>' + item.amount + '</p>';
            var allDepPay = {};
            allDepPay.supplierId = item.supplierId;
            allDepPay.departmentId = item.departmentId;
            allDepPay.employeeId = item.salesmanId;
            allDepPay.amount = item.amount;
            items.push(allDepPay);
        });

        orderBack.supperName = suppliernameStr;
        orderBack.depName = departmentnameStr;
        orderBack.cutMan = salesmannameStr;
        orderBack.cutAmountStr = amountStr;
        orderBack.items = items;//循环保存实体

        return orderBack;
    },
    choiceNameFormatter: function (value, row, index) {

        var items = row.items;
        if (items.length == 1) {

            return items[0].serviceName;
        } else {

            var str = '';
            $(items).each(function (i, item) {

                str += '<p>' + item.serviceName + '</p>';
            });
            return str;
        }
    },
    bankBooksChange: function (newValue, oldValue) {//账套联动


        //改变部门的查询条件
        $('#pays_u8Bank_name').combogrid('clear');
        var grid = $('#pays_u8Bank_name').combogrid('grid');
        var options = $(grid).datagrid('options');
        var filter = ' set_of_books_id ____ ----' + newValue + '----';
        options.url = '\/panda\/rest\/reference?code=SupplierU8Bank&filter=' + filter;
        $(grid).datagrid(options);

    }

});


function getSupplierOption() {
    var supplierOption = {
        columns: [[{
            field: 'name',
            title: '名称',
            width: 100
        }]],
        url: '\/panda\/rest\/reference?code=CRM_Supplier&filter=',
        idField: 'id',
        textField: 'name',
        width: 300,
        fitColumns: true,
        panelWidth: 450,
        panelHeight: 310,
        pagination: true,
        pageSize: 10,
        mode: 'remote',
        multiple: false,
        onChange: function (newValue, oldValue) {
            //改变部门的查询条件
            $('#cut_department_name').combogrid('clear');
            var grid = $('#cut_department_name').combogrid('grid');
            var options = $(grid).datagrid('options');
            var filter = ' supplier_id ____ ----' + newValue + '----';
            options.url = '\/panda\/rest\/reference?code=CRM_Supplier_Depart&filter=' + filter;
            $(grid).datagrid(options);
            //改变业务员的查询条件
            $('#cut_employee_name').combogrid('clear');
            var grid = $('#cut_employee_name').combogrid('grid');
            var options = $(grid).datagrid('options');
            //var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE supplier_id ____ ----'+ newValue + '----)';
            var filter = ' supplier_id ____ ----' + newValue + '----';
            options.url = '\/panda\/rest\/reference?code=Salesman&filter=' + filter;
            $(grid).datagrid(options);

        }
    };

    return supplierOption;
}

function getDepartmentOption() {
    var departmentOption = {
        columns: [[{
            field: 'name',
            title: '名称',
            width: 100
        }]],
        url: '\/panda\/rest\/reference?code=CRM_Supplier_Depart&filter=',
        idField: 'id',
        textField: 'name',
        width: 300,
        fitColumns: true,
        panelWidth: 450,
        panelHeight: 310,
        pagination: true,
        pageSize: 10,
        mode: 'remote',
        multiple: false,
        onChange: function (newValue, oldValue) {
            //改变业务员的查询条件
            $('#cut_employee_name').combogrid('clear');
            var grid = $('#cut_employee_name').combogrid('grid');
            var options = $(grid).datagrid('options');
            //var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE department_id ____ ----'+ newValue + '----)';
            var filter = ' department_id ____ ----' + newValue + '----';
            options.url = '\/panda\/rest\/reference?code=Salesman&filter=' + filter;
            $(grid).datagrid(options);
        }
    };
    return departmentOption;
}

function getEmployeeOption() {
    var employeeOption = {
        columns: [[{
            field: 'employee_name',
            title: '名称',
            width: 100
        }]],
        rowStyler: function (index, row) {
            // if (row.receiving === false) {
            //     return 'color:red;';
            // }
        },
        url: '\/panda\/rest\/reference?code=Salesman&filter=',
        idField: 'employeeId',
        textField: 'employee_name',
        width: 300,
        fitColumns: true,
        panelWidth: 450,
        panelHeight: 310,
        pagination: true,
        pageSize: 10,
        mode: 'remote',
        multiple: false,
        onChange: function (newValue, oldValue) {
            /*if(oldValue!="" && newValue != oldValue){
             }*/
        }
    };

    return employeeOption;
}




