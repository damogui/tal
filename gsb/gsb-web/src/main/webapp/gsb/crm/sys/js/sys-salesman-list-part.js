System.Declare("com.gongsibao.crm.web");

com.gongsibao.crm.web.SysSalesmanListPart = org.netsharp.panda.commerce.ListPart.Extends( {

    ctor: function () {
        this.base();
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