System.Declare("com.gongsibao.trade.web");
//创建订单业绩
com.gongsibao.trade.web.OrderPerformanceDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({

    ctor: function () {
        this.base();
        this.addUrl = null;
        this.editUrl = null;
        this.followUrl = null;
        this.addCustomerUrl = null;
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
        options.url = '\/panda\/rest\/reference?code=SupplierDepartment&filter=' + filter;
        $(grid).datagrid(options);

        //改变业务员的查询条件
        $('#employee_name').combogrid('clear');
        var grid = $('#employee_name').combogrid('grid');
        var options = $(grid).datagrid('options');
        var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE supplier_id ____ ----' + newValue + '----)';
        options.url = '\/panda\/rest\/reference?code=Employee&filter=' + filter;
        $(grid).datagrid(options);

    },
    departmentChange: function (newValue, oldValue) {

        //改变业务员的查询条件
        $('#employee_name').combogrid('clear');
        var grid = $('#employee_name').combogrid('grid');
        var options = $(grid).datagrid('options');
        var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE department_id ____ ----' + newValue + '----)';
        options.url = '\/panda\/rest\/reference?code=Employee&filter=' + filter;
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
        totalAmount=totalAmount/100;
        $("#performancePrice").val(totalAmount);
    },
    remove : function(rowIndex,row) {//也需要进行计算

        var $grid = this.getGrid();
        if(row){

            $grid.datagrid('deleteRow',rowIndex);
            this.dataSource.remove(row);
            return;
        }

        var rows = this.getSelections();
        if (rows.length == 0) {
            return;
        }

        var me = this;
        rows.forEach(function(row, index, array) {

            rowIndex = $grid.datagrid('getRowIndex',row);
            $grid.datagrid('deleteRow',rowIndex);
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
            title: '编辑任务',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['90%', '90%'],
            content: url
        });
    }

});

