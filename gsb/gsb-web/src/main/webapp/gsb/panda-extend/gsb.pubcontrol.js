org.netsharp.controls.PubControlList = org.netsharp.controls.Control.Extends({
    get: function () {

    },
    getFilter: function () {

    },
    clear: function () {

    },
    getSupplierCombogrid: function (supplierId, departmentId, employeeId) {//获取【服务商】下拉grid
        supplierId = supplierId == null ? "supplier_name" : supplierId;
        departmentId = departmentId == null ? "department_name" : departmentId;
        employeeId = employeeId == null ? "employee_name" : employeeId;
        var supplierOption = getSupplierOption(departmentId, employeeId);
        var supplierCombogrid = {
            id: supplierId,
            title: '服务商',
            type: 'combogrid',
            className: '',
            option: supplierOption
        };
        return supplierCombogrid;
    },
    getDepartmentCombogrid: function (departmentId, employeeId) {//获取【部门】下拉grid
        departmentId = departmentId == null ? "department_name" : departmentId;
        employeeId = employeeId == null ? "employee_name" : employeeId;
        var departmentOption = getDepartmentOption(employeeId);
        var departmentCombogrid = {
            id: departmentId,
            title: '部门',
            type: 'combogrid',
            className: '',
            option: departmentOption
        };
        return departmentCombogrid;
    },
    getEmployeeCombogrid: function (employeeId, isOnReceiving) {//获取【业务员】下拉grid
        employeeId = employeeId == null ? "employee_name" : employeeId;
        var employeeOption = getEmployeeOption(isOnReceiving);
        var employeeCombogrid = {
            id: employeeId,
            title: '业务员',
            type: 'combogrid',
            className: '',
            option: employeeOption
        };
        return employeeCombogrid;
    }

});


//获取服务商下拉框的列表信息
function getSupplierOption(departmentId, employeeId) {
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
            //是否有该控件
            var departmentgridlength = $('#' + departmentId).combogrid().length;
            var employeegridlength = $('#' + employeeId).combogrid().length;
            if (departmentgridlength <= 0) {
                return;
            }
            $('#' + departmentId).combogrid('clear');
            var grid = $('#' + departmentId).combogrid('grid');
            var options = $(grid).datagrid('options');
            var filter = ' supplier_id ____ ----' + newValue + '----';
            options.url = '\/panda\/rest\/reference?code=CRM_Supplier_Depart&filter=' + filter;
            $(grid).datagrid(options);
            if (employeegridlength <= 0) {
                return;
            }
            //改变业务员的查询条件
            $('#' + employeeId).combogrid('clear');
            var grid = $('#' + employeeId).combogrid('grid');
            var options = $(grid).datagrid('options');
            //var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE supplier_id ____ ----'+ newValue + '----)';
            var filter = ' supplier_id ____ ----' + newValue + '----';
            options.url = '\/panda\/rest\/reference?code=Salesman&filter=' + filter;
            $(grid).datagrid(options);

        }
    };

    return supplierOption;
}
//获取部门下拉框的列表信息
function getDepartmentOption(employeeId) {
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

            //判断是否有【业务员控件】
            var employeegridlength = $('#' + employeeId).combogrid().length;
            if (employeegridlength <= 0) {
                return;
            }

            //改变业务员的查询条件
            $('#' + employeeId).combogrid('clear');
            var grid = $('#' + employeeId).combogrid('grid');
            var options = $(grid).datagrid('options');
            //var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE department_id ____ ----'+ newValue + '----)';
            var filter = ' department_id ____ ----' + newValue + '----';
            options.url = '\/panda\/rest\/reference?code=Salesman&filter=' + filter;
            $(grid).datagrid(options);
        }
    };
    return departmentOption;
}

//获取服务员下拉框的列表信息:isOnReceiving：是否高亮显示”是否接单“
function getEmployeeOption(isOnReceiving) {
    var employeeOption = {
        columns: [[{
            field: 'supplier_name',
            title: '服务商',
            width: 100
        }, {
            field: 'department_name',
            title: '部门',
            width: 100
        }, {
            field: 'employee_name',
            title: '名称',
            width: 100
        }, {
            field: 'receiving',
            title: '自动接受任务',
            width: 100,
            formatter: function (value, row, index) {
                return value === false ? '否' : '是';
            }
        }]],
        rowStyler: function (index, row) {
            if (isOnReceiving && row.receiving === false) {
                return 'color:red;';
            }
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