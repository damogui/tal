/**
 * Created by win on 2018/1/22.
 */
System.Declare("com.gongsibao.crm.web");

com.gongsibao.crm.web.SysSalesmanTreePart =org.netsharp.panda.commerce.TreePart.Extends( {

    ctor: function () {
        this.base();
    },
   // addExtraParams:function(urls){//进行扩展
   //
   //      var supplierId = this.queryString('supplierId');
   //      if(supplierId){
   //
   //          urls.push("supplierId=" + supplierId);
   //      }
   //  }
});


//重新调用查询
$(function() {

   // controllerGsbCrmSysDepartmentTree.addExtraParams();
    //controllerGsbCrmSysDepartmentTree.query();
    var supplierId = controllerGsbCrmSysDepartmentTree.queryString('supplierId');
    var url = '/panda/rest/service?vid='+controllerGsbCrmSysDepartmentTree.context.vid+'&method=query&filter=';
    if(supplierId){
        url+='&supplierId='+supplierId;
    }
    //列表
    var options = $('#treeGsbCrmSysDepartmentTree').tree('options');
    options.url = url;
    $('#treeGsbCrmSysDepartmentTree').tree(options);
    //第三种 treegrid
});
