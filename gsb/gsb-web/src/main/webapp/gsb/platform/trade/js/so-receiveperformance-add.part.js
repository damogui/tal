System.Declare("com.gongsibao.trade.web");
//创建回款业绩
com.gongsibao.trade.web.OrderReceivePerformanceDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({

    ctor: function () {
        this.base();
       

    },init:function () {
        $("body").on("click","#peperformanceAdd",function () {
            var me = this;
            var supplierOption = getSupplierOption();
            var departmentOption = getDepartmentOption();
            var employeeOption = getEmployeeOption();
            PandaHelper.openDynamicForm({
                title:'回款业绩分配',
                width:450,
                height:300,
                items:[{id:'allot_supplier_name',
                    title:'服务商',
                    type:'combogrid',
                    className:'',
                    option:supplierOption},

                    {id:'allot_department_name',
                        title:'部门',
                        type:'combogrid',
                        className:'',
                        option:departmentOption},

                    {id:'allot_employee_name',
                        title:'业务员',
                        type:'combogrid',
                        className:'',
                        option:employeeOption}
                ],
                callback:function(index, layero){

                    var supplierId = $('#allot_supplier_name').combogrid('getValue');
                    var departmentId = $('#allot_department_name').combogrid('getValue');
                    var toUserId = $('#allot_employee_name').combogrid('getValue');

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
                    var rows=me.getSelections();
                    var ttmId = rows[0].id;
                    me.invokeService("updateOwner", [ttmId,toUserId],function(data) {
                        me.reload();
                        IMessageBox.toast('分配成功');
                        layer.closeAll();
                        return;
                    });
                }
            });

        });

        $("body").on("click","#peperformanceDel",function () {

            alert("删除");

        });



    },
    // init: function () {
    //     debugger;
    //     var me = this;
    //     var orderId = this.queryString('id');
    //     this.invokeService("queryProductList", [orderId], function (data) {
    //
    //         me.initGrid(data);
    //     });
    // },
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
                    field: 'price',
                    title: '回款业绩分配金额',
                    width: 100,
                    align: 'right',
                    formatter: function (value, row, index) {

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
        builder.append('<tr><td class="title" style="width:100px;text-align: right;">订单编号</td><td><input id="orderNo"/></td>');
        builder.append('<td class="title" style="width:100px;text-align: right;"> 订单分配金额</td><td><input id="orderCutPrice"/> </td>');
        builder.append('<td class="title" style="width:100px;text-align: right;"> 付款类别</td><td>  <select  id="payType" class="easyui-combobox" name="dept" style="width:200px;"> <option value="0">全款</option> <option value="1">一期款</option> <option value="2">二期款</option> <option value="3">三期款</option> <option value="4">四期款</option> </select> </td></tr>');
        builder.append('</table>');
        builder.append('</div>');
        builder.append('</form>');
        builder.append('<div class="panel-header" style="width: 1019px;"><div class="panel-title">业绩划分</div><div class="panel-tool"></div></div>');
        builder.append('<div id="controllerdepReceivableToolbar" class="toolbar datagrid-toolbar"> <a iconcls="fa fa-plus fa-fw"  plain="true" id="peperformanceAdd" class="easyui-linkbutton l-btn l-btn-small l-btn-plain" code="performanceDetailAdd" group=""><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">新增</span><span class="l-btn-icon fa fa-plus fa-fw">&nbsp;</span></span></a> <a iconcls="fa fa-trash-o"  plain="true" id="peperformanceDel" class="easyui-linkbutton l-btn l-btn-small l-btn-plain" code="performanceDetailDel" group=""><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">删除</span><span class="l-btn-icon fa fa-trash-o">&nbsp;</span></span></a> </div>');

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
                //me.initializeCtrl();

            },
            yes: function (index, layero) {

                // var orderProd = me.getOrderProd();
                // callback(orderProd);
                layer.closeAll();
            }
        });
        debugger;
        //  var  dataArr=[{id:"0", text:"全款"},{id:"1", text:"一期款"},{id:"2", text:"二期款"},{id:"3", text:"三期款"},{id:"4", text:"四期款"}];
        //var data = [['0', '累计雨量'], ['1', '时雨量'], ['2', '日雨量'], ['3', '旬雨量'], ['4', '月雨量'], ['5', '年雨量']];

        //$("#payType").combobox("loadData", dataArr); //最后，加载数组数据
        me.initGrid();
        me.init();

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


function getSupplierOption(){
    var supplierOption = {columns : [ [ {
        field : 'name',
        title : '名称',
        width : 100
    }] ],
        url : '\/panda\/rest\/reference?code=CRM_Supplier&filter=',
        idField : 'id',
        textField : 'name',
        width : 300,
        fitColumns : true,
        panelWidth : 450,
        panelHeight : 310,
        pagination : true,
        pageSize : 10,
        mode : 'remote',
        multiple : false,
        onChange : function(newValue, oldValue) {
            //改变部门的查询条件
            $('#allot_department_name').combogrid('clear');
            var grid = $('#allot_department_name').combogrid('grid');
            var options = $(grid).datagrid('options');
            var filter = ' supplier_id ____ ----'+ newValue + '----';
            options.url = '\/panda\/rest\/reference?code=CRM_Supplier_Depart&filter='+ filter;
            $(grid).datagrid(options);
            //改变业务员的查询条件
            $('#allot_employee_name').combogrid('clear');
            var grid = $('#allot_employee_name').combogrid('grid');
            var options = $(grid).datagrid('options');
            //var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE supplier_id ____ ----'+ newValue + '----)';
            var filter = ' supplier_id ____ ----'+ newValue + '----';
            options.url = '\/panda\/rest\/reference?code=Salesman&filter='+ filter;
            $(grid).datagrid(options);

        }};

    return supplierOption;
}

function getDepartmentOption(){
    var departmentOption = {columns : [ [ {
        field : 'name',
        title : '名称',
        width : 100
    }] ],
        url : '\/panda\/rest\/reference?code=CRM_Supplier_Depart&filter=',
        idField : 'id',
        textField : 'name',
        width : 300,
        fitColumns : true,
        panelWidth : 450,
        panelHeight : 310,
        pagination : true,
        pageSize : 10,
        mode : 'remote',
        multiple : false,
        onChange : function(newValue, oldValue) {
            //改变业务员的查询条件
            $('#allot_employee_name').combogrid('clear');
            var grid = $('#allot_employee_name').combogrid('grid');
            var options = $(grid).datagrid('options');
            //var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE department_id ____ ----'+ newValue + '----)';
            var filter = ' department_id ____ ----'+ newValue + '----';
            options.url = '\/panda\/rest\/reference?code=Salesman&filter='+ filter;
            $(grid).datagrid(options);
        }};
    return departmentOption;
}

function getEmployeeOption(){
    var employeeOption = {columns : [ [ {
        field : 'supplier_name',
        title : '服务商',
        width : 100
    },{
        field : 'department_name',
        title : '部门',
        width : 100
    },{
        field : 'employee_name',
        title : '名称',
        width : 100
    },{
        field : 'receiving',
        title : '自动接受任务',
        width : 100,
        formatter : function(value,row,index){return value===false?'否':'是';}
    }] ],
        rowStyler: function(index,row){if(row.receiving ===false) {return 'color:red;';  }},
        url : '\/panda\/rest\/reference?code=Salesman&filter=',
        idField : 'employeeId',
        textField : 'employee_name',
        width : 300,
        fitColumns : true,
        panelWidth : 450,
        panelHeight : 310,
        pagination : true,
        pageSize : 10,
        mode : 'remote',
        multiple : false,
        onChange : function(newValue, oldValue) {
            /*if(oldValue!="" && newValue != oldValue){
             }*/
        }};

    return employeeOption;
}




