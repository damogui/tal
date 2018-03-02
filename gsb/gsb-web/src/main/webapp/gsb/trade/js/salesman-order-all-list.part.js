System.Declare("com.gongsibao.trade.web");
//所有订单
com.gongsibao.trade.web.SalesmanAllOrderListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor : function() {
        this.base();
        // this.addUrl = "/crm/order/salesman/all/form";
        // this.editUrl = "/crm/order/salesman/all/form";
        // this.followUrl = "/crm/order/salesman/all/form";
        //this.addOrderReceivedUrl ='/panda/crm/platform/customer/add';
    },
    addCustomer:function(){

        window.open(this.addOrderReceivedUrl);
    },
    addOrderReceived:function(id){
        debugger;
        this.edit(id);
    },
    detail:function(id){

        this.edit(id);
    },
    doubleClickRow : function(index, row) {
        this.edit(row.id);
    },
    // edit : function(id) {
    //
    //     $('#'+this.context.id).datagrid('unselectAll');
    //     $('#'+this.context.id).datagrid('selectRecord',id);
    //     var selectRow = this.getSelectedItem();
    //     var url = this.editUrl+"?taskId="+id+"&customerId="+selectRow.customerId;
    //     layer.open({
    //         type: 2,
    //         title: '编辑任务',
    //         fixed: false,
    //         maxmin: true,
    //         shadeClose:true,
    //         area: ['90%','90%'],
    //         content: url
    //     });
    // },
    batchAllocation:function(){
        //任务批量分配
        var me = this;
        var row = this.getSelectedItem();
        var id = this.getSelectionIds();
        if(id == "" || id == null ){
            IMessageBox.info('请选择记录');
            return;
        }
        me.doAllot(id);
    },
    allocation:function(id){
        //任务分配
        var me = this;
        me.doAllot(id);
    },
    doAllot : function(taskId) {
        var me = this;
        var supplierOption = getSupplierOption();
        var departmentOption = getDepartmentOption();
        var employeeOption = getEmployeeOption();
        PandaHelper.openDynamicForm({
            title:'任务分配',
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

                me.invokeService("allocation", [taskId,supplierId,departmentId,toUserId],function(data) {
                    me.reload();
                    IMessageBox.toast('分配成功');
                    layer.closeAll();
                    return;
                });
            }
        });
    },
    follow : function(id) {
        $("#" + this.context.id).datagrid('unselectAll');
        $("#" + this.context.id).datagrid('selectRecord',id);
        var me = this;
        var taskId = id;
        $('#'+this.context.id).datagrid('selectRecord',id);
        var selectedRow = $('#'+this.context.id).datagrid('getSelected');
        var customerId = selectedRow.customerId;
        var originalQualityId = selectedRow.qualityId;
        var taskFollowCtrl = new com.gongsibao.crm.web.TaskFollowCtrl();
        taskFollowCtrl.open(taskId,customerId,originalQualityId,function(index, layero){

            me.reload();
        });
    },
    doFollowService : function(id,getqualityId,time,amount,getNote) {
        var me = this;
        this.invokeService("follow", [id,getqualityId,time,amount,getNote],function(data) {
            me.reload();
            IMessageBox.toast('操作成功');
            layer.closeAll();
            return;
        });
    },
    regain : function(){
        //任务收回
        var me = this;
        var id = this.getSelectionIds();
        if(id == "" || id == null ){
            IMessageBox.info('请选择记录');
            return;
        }
        me.doRegain(id);
    },
    doRegain : function(id) {
        var me = this;
        PandaHelper.openDynamicForm({
            title:'收回任务',
            width:500,
            height:400,
            items:[{id:'txtNote',
                title:'内容',
                type:'textarea',
                height:130,
                width:300,
                className:''}
            ],
            explain:'任务将会退回至【公海】，进行【二次分配】',
            notice:'',
            callback:function(index, layero){
                var getNote = $("#txtNote").val();
                if (System.isnull(getNote)) {
                    IMessageBox.info('请输入内容');
                    return false;
                }
                me.doRollBackService(id,getNote,'regain');
            }
        });
    },
    rollback : function(id){
        //任务退回
        var me = this;
        //这里先要取消所有行，再选中1行
        $("#" + this.context.id).datagrid('unselectAll');
        $("#" + this.context.id).datagrid('selectRecord',id);
        var row = this.getSelectedItem();
        var intenCategory = row.intentionCategory;

        me.doRollBack(id,intenCategory);
    },
    doRollBack : function(id,intenCategory) {
        var me = this;
        PandaHelper.openDynamicForm({
            title:'退回任务',
            width:500,
            height:400,
            items:[{id:'txtNote',
                title:'内容',
                type:'textarea',
                height:130,
                width:300,
                className:''}
            ],
            explain:'任务将会退回至【公海】，进行【二次分配】',
            notice:customerQuality(intenCategory),
            callback:function(index, layero){
                var getNote = $("#txtNote").val();
                if (System.isnull(getNote)) {
                    IMessageBox.info('请输入内容');
                    return false;
                }
                me.doRollBackService(id,getNote,'rollback');
            }
        });
    },
    doRollBackService : function(id,getNote,functionName) {
        var me = this;
        this.invokeService(functionName, [id,getNote],function(data) {
            me.reload();
            IMessageBox.toast('操作成功');
            layer.closeAll();
            return;
        });
    },
    batchTransfer : function(){
        //任务批量转移
        var me = this;
        var id = this.getSelectionIds();
        if(id == "" || id == null ){
            IMessageBox.info('请选择记录');
            return;
        }
        me.doTransfer(id);
    },
    transfer : function(id){
        //任务转移
        var me = this;
        var row = this.getSelectedItem();
        if (row == null) {
            IMessageBox.info('请选择记录');
            return;
        }
        me.doTransfer(id);
    },
    doTransfer : function(taskId) {
        var me = this;
        var supplierOption = getSupplierOption();
        var departmentOption = getDepartmentOption();
        var employeeOption = getEmployeeOption();

        PandaHelper.openDynamicForm({
            title:'任务转移',
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

                me.doTransferService(taskId,supplierId,departmentId,toUserId);
            }
        });
    },
    doTransferService : function(taskId,supplierId,departmentId,toUserId) {
        var me = this;
        this.invokeService("transfer", [taskId,supplierId,departmentId,toUserId],function(data) {
            me.reload();
            IMessageBox.toast('转移成功');
            layer.closeAll();
            return;
        });
    },
    openMember : function(customerId){

        var me = this;
        IMessageBox.confirm("您确定要开通会员吗？",function(r){

            if(r===true){

                me.invokeService("openMember", [customerId],function(data) {
                    if(data){
                        IMessageBox.toast('开通成功');
                        me.reload();
                        layer.closeAll();
                    }else{
                        IMessageBox.toast('开通失败,稍后再试');
                    }
                });
            }
        });
    },

    verified:function(id){
        //state :1-"未抽查",2-"抽查正常",3-"抽查异常",4-"异常已处理"
        //type :1-"抽查",2-"处理"
        var me = this;
        me.doAbnormalPopup(id,2,1);
    },
    untrue:function(id){
        var me = this;
        me.doAbnormalPopup(id,3,1);
    },
    remark:function(id){
        //state:0  ；只更新内容
        var me = this;
        me.doAbnormalPopup(id,0,1);
    },
    doAbnormalPopup : function(id,state,type) {
        var me = this;
        PandaHelper.openDynamicForm({
            title:'抽查',
            width:450,
            height:300,
            items:[{id:'txtNote',
                title:'内容',
                type:'textarea',
                height:130,
                width:300,
                className:''}
            ],
            callback:function(index, layero){
                var getNote = $("#txtNote").val();
                if (System.isnull(getNote)) {
                    IMessageBox.info('请输入内容');
                    return false;
                }
                me.doAbnormal(id,state,getNote,type);
            }
        });
    },
    doAbnormal : function(id,state,getNote,type) {
        var me = this;
        this.invokeService("abnormal", [id,state,getNote,type],function(data) {
            me.reload();
            IMessageBox.toast('操作成功');
            layer.closeAll();
            return;
        });
    },
    contactFormatter:function(value,row,index,typeName){

        if(value){
            var ctrl = workspace.parts.byIndex(0).key;
            return '<sapn>'+PandaHelper.dimString(value)+'</span><i class="fa fa-eye" onclick="'+ctrl+'.showPlaintext(\''+row.customerId+'\',\''+value+'\',\''+typeName+'\',this);"></i>';
        }
    },
    showPlaintext:function(customerId,value,typeName,obj){

        $(obj).parent().text(value);
        var serviceLocator = new org.netsharp.core.JServiceLocator();
        serviceLocator.invoke(this.context.service, "recordLookLog",[customerId,typeName]);
    }
});



