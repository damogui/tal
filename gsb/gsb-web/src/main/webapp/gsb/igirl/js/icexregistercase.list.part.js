System.Declare("com.gongsibao.igirl.ic.web");
com.gongsibao.igirl.ic.web.IcExRegisterCaseListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor : function() {
        this.base();
    },
    doAllot : function(type) {
        var me = this;
        var rows=me.getSelections();
        if (rows.length == 0) {

            IMessageBox.info("您没有选择记录!");
            return;
        } else if (rows.length > 1) {
            IMessageBox.info("只能选择一条记录!");
            return;
        }
        me.invokeService("getIcSupplierId",[],function (data) {
            var supplierOption = getSupplierOption(data);
            var departmentOption = getDepartmentOption();
            var employeeOption = getEmployeeOption();
            PandaHelper.openDynamicForm({
                title:'分配所属人',
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

                    if (System.isnull(supplierId) && System.isnull(departmentId) && System.isnull(toUserId)) {

                        IMessageBox.info('请选择');
                        return;
                    }
                    var id = rows[0].id;
                    me.invokeService("updateOwner", [id,toUserId,type],function(data) {
                        me.reload();
                        IMessageBox.toast('分配成功');
                        layer.closeAll();
                        return;
                    });
//				me.invokeService("allocation", [taskId,supplierId,departmentId,toUserId],function(data) {
//					me.reload();
//					IMessageBox.toast('分配成功');
//					layer.closeAll();
//					return;
//				});
                }
            });
        });
    },
});

function getSupplierOption(supplierId){
    var filter = ' id ____ ----'+supplierId+'----';
    var supplierOption = {columns : [ [ {
            field : 'name',
            title : '名称',
            width : 100
        }] ],
        url : '\/panda\/rest\/reference?code=CRM_Supplier&filter='+filter,
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

