System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.StatisticalCustomerListPart = org.netsharp.panda.commerce.ListPart.Extends({

    ctor: function () {
        this.base();
    }
});

// 进入页面就执行
$(function () {
    
    //产品一级分类的change事件
    /*var options = $("#productCategory1_name").combobox('options');
     options.onChange = function (newValue, oldValue) {
     var id = parseInt(newValue);
     if (System.isnull(newValue) || typeof id != 'number') {
     return;
     }
     //清空
     $('#productCategory2_name').combobox('clear');

     var serviceLocator = new org.netsharp.core.JServiceLocator();
     //加载二级分类
     var service = "com.gongsibao.crm.web.TaskProductDetailPart";
     serviceLocator.invoke(service, "queryByFirstProductCategoryId", [newValue], function (data) {
     $('#productCategory2_name').combobox('loadData', data);
     });
     }
     $("#productCategory1_name").combobox(options);*/

    //产品一级分类的change事件
    $("#productCategory1_name").combobox({
        onChange: function (newValue, oldValue) {
            var id = parseInt(newValue);
            if (System.isnull(newValue) || typeof id != 'number') {
                return;
            }
            //清空
            $('#productCategory2_name').combobox('clear');

            var serviceLocator = new org.netsharp.core.JServiceLocator();
            //加载二级分类
            var service = "com.gongsibao.crm.web.TaskProductDetailPart";
            serviceLocator.invoke(service, "queryByFirstProductCategoryId", [newValue], function (data) {
                $('#productCategory2_name').combobox('loadData', data);
            });
        }
    });

    //产品二级分类的change事件
    $("#productCategory2_name").combobox({
        onChange: function (newValue, oldValue) {
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
        }
    });
});