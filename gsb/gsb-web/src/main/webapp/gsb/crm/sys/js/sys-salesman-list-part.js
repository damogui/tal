System.Declare("com.gongsibao.crm.web");

com.gongsibao.crm.web.SysSalesmanListPart = org.netsharp.panda.commerce.ListPart.Extends( {

    ctor: function () {
        this.base();
    },
    doQuery : function(filter,queryParams) {
        

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
    },addExtraParams:function(urls){//进行扩展

        var supplierId = this.queryString('supplierId');
        if(supplierId){

            urls.push("supplierId=" + supplierId);
        }
    }
});


//重新调用查询
$(function() {

    controllerdepartments.query();
});