System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderAllotCtrl = System.Object.Extends({
    ctor: function () {

    },
    show: function (title,callback) {
    	debugger;
        var me = this;
        var supplierOption = this.getSupplierOption();
        var departmentOption = this.getDepartmentOption();
        var employeeOption = this.getEmployeeOption();
        
        PandaHelper.openDynamicForm({
            title:title,
            width:450,
            height:330,
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

                {id:'allot_salesman_name',
                    title:'业务员',
                    type:'combogrid',
                    className:'',
                    option:employeeOption},
                {id:'allot_amount',
                    title:'分配金额',
                    type:'numberbox',
                    className:'',
                    option:{width:300,precision:2,min:1,required:true}}
            ],
            callback:function(index, layero){
                var supplierId = $('#allot_supplier_name').combogrid('getValue');
                var departmentId = $('#allot_department_name').combogrid('getValue');
                var salesmanId = $('#allot_salesman_name').combogrid('getValue');
                var amount = $('#allot_amount').numberbox('getValue');
                if(isNaN(supplierId)){                	
                	layer.msg('请选择有效的服务商！');
                    return;
                }
                if(isNaN(departmentId)){
                	layer.msg('请选择有效的部门！');
                    return;
                }
                if(isNaN(salesmanId)){
                	layer.msg('请选择有效的业务员！');
                    return;
                }
                if (System.isnull(supplierId)) {
                    IMessageBox.info('请选择服务商');
                    return;
                }
                if (System.isnull(departmentId)) {
                    IMessageBox.info('请选择部门');
                    return;
                }
                if (System.isnull(salesmanId)) {
                    IMessageBox.info('请选择业务员');
                    return;
                }

                if (System.isnull(amount)) {
                    IMessageBox.info('请填写分配金额');
                    return;
                }
                
                //这里要校验金额之和不能大于退款金额
                var depRefund = new Object();
                depRefund.supplierId = supplierId;
                var supplierName = $('#allot_supplier_name').combogrid('getText');
                depRefund.supplier = {
                	id:supplierId,
                	name:supplierName
                };
                
                depRefund.departmentId = departmentId;
                var departmentName = $('#allot_department_name').combogrid('getText');
                depRefund.department = {
                    	id:departmentId,
                    	name:departmentName
                };
                
                depRefund.salesmanId = salesmanId;
                var salesmanName = $('#allot_salesman_name').combogrid('getText');
                depRefund.salesman = {
                    	id:salesmanId,
                    	name:salesmanName
                };
                
                depRefund.amount = parseFloat(amount)*100;
                callback(depRefund);
                //关闭当前窗口
                layer.close(index);
            }
        });
    },
    getSupplierOption:function(){
    	
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
                $('#allot_salesman_name').combogrid('clear');
                var grid = $('#allot_salesman_name').combogrid('grid');
                var options = $(grid).datagrid('options');
                //var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE supplier_id ____ ----'+ newValue + '----)';
                var filter = ' supplier_id ____ ----'+ newValue + '----';
                options.url = '\/panda\/rest\/reference?code=Salesman&filter='+ filter;
                $(grid).datagrid(options);

            }};

        return supplierOption;
    },
    getDepartmentOption:function(){
    	
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
                $('#allot_salesman_name').combogrid('clear');
                var grid = $('#allot_salesman_name').combogrid('grid');
                var options = $(grid).datagrid('options');
                //var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE department_id ____ ----'+ newValue + '----)';
                var filter = ' department_id ____ ----'+ newValue + '----';
                options.url = '\/panda\/rest\/reference?code=Salesman&filter='+ filter;
                $(grid).datagrid(options);
            }};
        return departmentOption;
    },
    getEmployeeOption:function(){
    	
        var employeeOption = {columns : [ [ {
            field : 'employee_name',
            title : '名称',
            width : 100
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
});