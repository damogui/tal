/**
 * Created by win on 2018/1/22.
 */
System.Declare("com.gongsibao.crm.web");

com.gongsibao.crm.web.SysSalesmanTreePart =org.netsharp.panda.commerce.TreePart.Extends( {

    ctor: function () {
        this.base();
    }
});


//重新调用查询
$(function() {
    var supplierId = controllerGsbCrmSysDepartmentTree.queryString('supplierId');
    var params = {
        supplierId: supplierId

    };
    $('#treeGsbCrmSysDepartmentTree').tree("options").queryParams = params;
    $('#treeGsbCrmSysDepartmentTree').tree('reload');

});
