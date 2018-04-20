System.Declare("com.gongsibao.igirl.ic.web");
com.gongsibao.igirl.ic.web.IcExRegisterCasePart = org.netsharp.panda.commerce.FormPart.Extends({
    ctor: function () {
        this.base();
    },
    isTel: function (el) {
        var tel = $(el).val();
        var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(tel)) {
            IMessageBox.error("【手机】格式错误");
            return false;
        }
        return true;
    },
    isCom : function (el) {
        var com = $(el).val();
        var con="北京";
        if(com.indexOf(con)==-1){
            IMessageBox.error("【公司名称】格式错误");
            return false;
        }
        return true;
    },

/**/
    // doAllot : function() {
    //     var me = this;
    //     var rows=me.getSelections();
    //     if (rows.length == 0) {
    //
    //         IMessageBox.info("您没有选择记录!");
    //         return;
    //     } else if (rows.length > 1) {
    //         IMessageBox.info("只能选择一条记录!");
    //         return;
    //     }
    //     me.invokeService("getTradeMarkCaseSupplierId",[],function (data) {
    //         var supplierOption = getSupplierOption(data);
    //         var departmentOption = getDepartmentOption();
    //         var employeeOption = getEmployeeOption();
    //         PandaHelper.openDynamicForm({
    //             /*只在当前服务商内部改 只改名字 部门类整体改变*/
    //             title:'分配所属人',
    //             width:450,
    //             height:300,
    //             items:[{id:'ic_ex_register_owner',
    //                 title:'所属人',
    //                 type:'combogrid',
    //                 className:'',
    //                 option:ownerOption},
    //
    //                 {id:'sp_supplier_name',
    //                     title:'部门',
    //                     type:'combogrid',
    //                     className:'',
    //                     option:departmentOption},
    //
    //             ],
    //             callback:function(index, layero){
    //
    //                 var ownerId = $('#ic_ex_register_owner').combogrid('getValue');
    //                 var supplierNameId = $('#sp_supplier_name').combogrid('getValue');
    //
    //                 if (System.isnull(ownerId)) {
    //                     IMessageBox.info('请选择所属人');
    //                     return;
    //                 }
    //                 if (System.isnull(supplierNameId)) {
    //                     IMessageBox.info('请选择部门');
    //                     return;
    //                 }
    //                 var ttmId = rows[0].id;
    //                 me.invokeService("updateState", [ttmId,toUserId],function(data) {
    //                     me.reload();
    //                     IMessageBox.toast('分配成功');
    //                     layer.closeAll();
    //                     return;
    //                 });
    //             }
    //         });
    //     });
    // },
});

