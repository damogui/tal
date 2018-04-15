System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.NCustomerTaskAddFormPart = org.netsharp.panda.commerce.FormPart.Extends({

    ctor: function () {
        this.base();
    },
    supplierChange: function (newValue, oldValue) {

        //改变部门的查询条件
        $('#department_name').combogrid('clear');
        var grid = $('#department_name').combogrid('grid');
        var options = $(grid).datagrid('options');
        var filter = ' supplier_id ____ ----' + newValue + '----';
        options.url = '\/panda\/rest\/reference?code=SupplierDepartment&filter=' + filter;
        $(grid).datagrid(options);

        //改变业务员的查询条件
        $('#owner_name').combogrid('clear');
        var grid = $('#owner_name').combogrid('grid');
        var options = $(grid).datagrid('options');
        var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE supplier_id ____ ----' + newValue + '----)';
        options.url = '\/panda\/rest\/reference?code=Employee&filter=' + filter;
        $(grid).datagrid(options);

    },
    departmentChange: function (newValue, oldValue) {

        //改变业务员的查询条件
        $('#owner_name').combogrid('clear');
        var grid = $('#owner_name').combogrid('grid');
        var options = $(grid).datagrid('options');
        var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE department_id ____ ----' + newValue + '----)';
        options.url = '\/panda\/rest\/reference?code=Employee&filter=' + filter;
        $(grid).datagrid(options);
    },
    costedChange: function (checked) {

//      1:自动分配
//      2:手动分配
//      3:自动分配到服务商

        var allocationType = $("#allocationType").combobox('getValue');
        if (checked && allocationType != '2') {

            $("#supplier_name").combogrid('enable');
        } else if (allocationType == '1') {
            $("#supplier_name").combogrid('disable');
        }
    },
    sourceSelect: function (newValue, oldValue) {

        var $ctrl = $("#sourceOther");
        if (newValue === '4177') {

            $ctrl.prop("disabled", false);
            $ctrl.validatebox('enableValidation');
        } else {
            $ctrl.prop("disabled", true);
            //ctrl.validatebox('disableValidation');
        }
    },
    consultWaySelect: function (newValue, oldValue) {

        var $ctrl = $("#consultWayOther");
        if (newValue === '4219') {

            $ctrl.prop("disabled", false);
            $ctrl.validatebox('enableValidation');
        } else {
            $ctrl.prop("disabled", true);
            //ctrl.validatebox('disableValidation') ;
        }
    },
    allocationDispositonChange: function (newValue, oldValue) {

        console.log(newValue);
    },
    allocationTypeChange: function (newValue, oldValue) {

//    	AUTO(1, "自动分配"), 
//    	MANUAL(2, "手动分配"),
//    	SemiAutomatic(3, "自动分配到服务商");
        //是否费用部门
        var costedChecked = $('#costed').switchbutton('options').checked;
        if (newValue == 1) {

            $("#owner_name").combogrid('setValue', '').combogrid('disable');

            $('#allocationState').combobox('disable').combobox('setValue', '1');

            if (!costedChecked) {

                $("#supplier_name").combogrid('disable').combogrid('clear');
            }

            $("#department_name").combogrid('disable').combogrid('clear');
        } else if (newValue == 2) {

            $("#supplier_name").combogrid('enable');

            $("#department_name").combogrid('enable');

            $("#owner_name").combogrid('enable');

            $('#allocationState').combobox('enable')
        } else {

            $("#supplier_name").combogrid('enable');

            $("#department_name").combogrid('enable');

            $("#owner_name").combogrid('setValue', '').combogrid('disable');

            $('#allocationState').combobox('enable');
        }
    },
    doSave: function (entity) {
        var me = this;
        var isPlatform = this.queryString("isPlatform");
        var id = this.queryString("id");
        //服务商新增：返回父页面
        var parentCtrl = this.queryString("ctrl");
        if (!System.isnull(id) || System.isnull(parentCtrl)) {
            //平台售前新增：直接保存
            this.invokeService("save", [entity], function (jmessage) {
                me.onSaved(jmessage);
            });
        } else {
            eval("window.parent." + parentCtrl + ".save(entity);");
            //关闭当前窗口
            window.parent.layer.closeAll();
        }
    },
    onSaved: function (jmessage) {

        this.currentItem = jmessage;
        if (this.currentItem != null) {

            this.currentItem.entityState = EntityState.Persist;
            this.viewModel.currentItem = this.currentItem;
            this.databind();
            layer.msg("保存成功！", {time: 500, icon: 1}, function () {

                window.parent.location.reload();
            });

        } else {

            IMessageBox.error("保存失败！");
        }
    },
    onload: function () {

        var id = this.queryString("id");
        var parentCtrl = this.queryString("ctrl");
        var type = this.queryString("type");
        if (!System.isnull(parentCtrl) && type == 'edit') {

            return;
        }

        if (System.isnull(id)) {

            this.add();
        } else {
            this.byId(id);
        }
    },
    setEntity: function (entity) {

        this.currentItem = entity;
        this.viewModel.currentItem = this.currentItem;
        this.currentItem.entityState = EntityState.New;
        this.added(this.currentItem);
        if (this.currentItem == null) {

            this.viewModel.clear();
        } else {

            this.databind();
        }
    },
    added: function (currentItem) {

        var swtCustomerId = this.queryString("swtCustomerId");
        var swtServiceId = this.queryString("swtServiceId");

        if (swtCustomerId) {

            currentItem.swtCustomerId = swtCustomerId;
            currentItem.sourceId = 4181;
            currentItem.source = {id: 4181, name: 'PC官网'};
            currentItem.consultWayId = 42143;
            currentItem.consultWay = {id: 42143, name: '商务通'};
        }

        if (swtServiceId) {

            currentItem.swtServiceId = swtServiceId;
        }
    }
});

//initValue  此方法不会触发改变事件，
com.gongsibao.crm.web.TaskProductDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({
    ctor: function () {
        this.base();
    },
    productCategory1Select: function (newValue, oldValue) {

        var id = parseInt(newValue);
        if (System.isnull(newValue) || typeof id != 'number') {

            return;
        }

        $('#productCategory2_name').combobox('clear');

//    	$('#productCategory2_name').combobox('setValue',null);
//    	$('#productCategory2_name').combobox('setText',null);

        //加载二级分类
        this.invokeService("queryByFirstProductCategoryId", [newValue], function (data) {

            $('#productCategory2_name').combobox('loadData', data);

            var grid = $('#product_name').combogrid('grid');
            var row = $(grid).datagrid('getSelected');
            if (row) {

                $('#productCategory2_name').combobox('setValue', row.typeId);
                $('#productCategory2_name').combobox('setText', row.type_name);
            }
        }, false);
    },
    productCategory2Select: function (newValue, oldValue) {

        var id = parseInt(newValue);
        if (System.isnull(newValue) || typeof id != 'number') {

            return;
        }

        var grid = $('#product_name').combogrid('grid');
        var row = $(grid).datagrid('getSelected');
        if (row == null || row.typeId != newValue) {

            $('#product_name').combogrid('clear');
        }
        var options = $(grid).datagrid('options');
        var filter = ' enabled____1 and type_id____' + newValue;
        options.url = '\/panda\/rest\/reference?code=CRM_Product&filter=' + filter;
        $(grid).datagrid(options);

    },
    productChange: function (newValue, oldValue) {

        var grid = $('#product_name').combogrid('grid');
        var row = $(grid).datagrid('getSelected');
        if (row) {

            $('#productCategory1_name').combobox('select', row.type_parentId);
        }
    }
});
