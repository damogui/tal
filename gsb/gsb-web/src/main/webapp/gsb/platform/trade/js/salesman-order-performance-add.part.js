System.Declare("com.gongsibao.trade.web");
//创建订单业绩
com.gongsibao.trade.web.OrderPerformanceDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({

    ctor: function () {
        this.base();
        this.addUrl = null;
        this.editUrl = null;
        this.followUrl = null;
        this.addCustomerUrl = null;
        var employeeOption = getEmployeeOption();//重新制定
        $('#salesman_name').combogrid(employeeOption);


    },
    onload: function () {

        var subheight = $('#center').height();
        if ($('#center').find('.easyui-tabs').length > 0) {

            subheight = subheight - 35;
        } else {

            subheight = subheight - 10;
        }
        this.getGrid().datagrid('resize', {'height': subheight});

        this.setState();
    },
    supplierChange: function (newValue, oldValue) {//进行联动

        //改变部门的查询条件
        $('#department_name').combogrid('clear');
        var grid = $('#department_name').combogrid('grid');
        var options = $(grid).datagrid('options');
        var filter = ' supplier_id ____ ----' + newValue + '----';
        options.url = '\/panda\/rest\/reference?code=OrderSupplierDepartment&filter=' + filter;
        $(grid).datagrid(options);

        //改变业务员的查询条件
        $('#salesman_name').combogrid('clear');
        var grid = $('#salesman_name').combogrid('grid');
        var options = $(grid).datagrid('options');
        var filter = ' department_id ____ ----' + newValue + '----';
        options.url = '\/panda\/rest\/reference?code=Salesman&filter=' + filter;
        $(grid).datagrid(options);

    },
    departmentChange: function (newValue, oldValue) {
        
        //改变业务员的查询条件
        $('#salesman_name').combogrid('clear');
        var grid = $('#salesman_name').combogrid('grid');
        var options = $(grid).datagrid('options');
        var filter = ' department_id ____ ----' + newValue + '----';
        options.url = '\/panda\/rest\/reference?code=Salesman&filter=' + filter;
        $(grid).datagrid(options);
    },
    savebase: function () {
        this.parent.save();

    },

    saveAfter: function () {//统计表格的划分金额
        var rows = this.getGrid().datagrid('getRows');
        var totalAmount = 0;

        $(rows).each(function (i, item) {

            totalAmount += parseInt(item.amount);

        });
        totalAmount = totalAmount / 100;
        $("#performancePrice").val(totalAmount);
    },
    remove: function (rowIndex, row) {//也需要进行计算

        var $grid = this.getGrid();
        if (row) {

            $grid.datagrid('deleteRow', rowIndex);
            this.dataSource.remove(row);
            return;
        }

        var rows = this.getSelections();
        if (rows.length == 0) {
            return;
        }

        var me = this;
        rows.forEach(function (row, index, array) {

            rowIndex = $grid.datagrid('getRowIndex', row);
            $grid.datagrid('deleteRow', rowIndex);
            me.dataSource.remove(row);
        });

        me.saveAfter();

    },

    detail: function (id) {

        this.edit(id);
    },
    doubleClickRow: function (index, row) {
        this.edit(row.id);
    },
    edit: function (id) {

        $('#' + this.context.id).datagrid('unselectAll');
        $('#' + this.context.id).datagrid('selectRecord', id);
        var selectRow = this.getSelectedItem();
        var url = this.editUrl + "?taskId=" + id + "&customerId=" + selectRow.customerId;
        layer.open({
            type: 2,
            title: '编辑商机',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['90%', '90%'],
            content: url
        });
    }

});

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
        // width: 160,
        fitColumns: true,
        // panelWidth: 450,
        // panelHeight: 310,
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



