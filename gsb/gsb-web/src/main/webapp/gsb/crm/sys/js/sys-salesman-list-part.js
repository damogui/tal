System.Declare("com.gongsibao.crm.web");

com.gongsibao.crm.web.SysSalesmanListPart = org.netsharp.panda.commerce.ListPart.Extends( {

    ctor: function () {
        this.base();
    },
    doQuery : function(filter,queryParams) {
        debugger;

        var urls = this.getFilters(filter);
        var url = urls.join("&");
        url = System.Url.getUrl(url);
        url = encodeURI(url);
        this.resetUrl(url,queryParams);
        // this.reload();
    },
    onAdding : function() {

        var relationItem = this.relationItem;
        if(relationItem == null){

            IMessageBox.info("请选择部门");
            return false;
        }

        if(relationItem.children.length>0){

            IMessageBox.info("请选择末节点");
            return false;
        }
        return true;
    }
});

//进行拼接服务商id
$(function() {
  debugger;
    var url = controllerdepartments.context.queryUrl;
    var url2 = controllerdepartments.context.queryUrl;
    var supplierId = controllerdepartments.queryString('supplierId');
    if(supplierId){
        url+='&supplierId='+supplierId;
        url2+='&supplier_Id='+supplierId;
    }

    //controllerGsbCrmSysDepartmentTree
    //树结构加上查询条件
    var optionTree = $('#'+controllerGsbCrmSysDepartmentTree.context.id).tree('options');
    optionTree.url = url2;
    $('#'+controllerGsbCrmSysDepartmentTree.context.id).tree(optionTree);

    //列表
    var options = $(controllerdepartments.datagrid).datagrid('options');
    options.url = url;
    $(controllerdepartments.datagrid).datagrid(options);
    //第三种 treegrid

});