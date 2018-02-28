System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.StatisticalCustomerListPart = org.netsharp.panda.commerce.ListPart.Extends({

    ctor: function () {
        this.base();
    }
});

// // 进入页面就执行
// $(function () {
//
//     var options = $('#book_name').combogrid('options');
//     options.onChange = function (newValue, oldValue) {
//
//         $('#bank_name').combogrid('setText', '').combogrid('setValue', '');
//         if (System.isnull(newValue)) {
//             return;
//         }
//
//         var grid = $('#bank_name').combogrid('grid');
//         var options = $(grid).datagrid('options');
//
//         var filter = ' setOfBooksId ____ ----' + newValue + '----';// 和后台约定了“____”就是“=”，“----”就是“'”(单引号)
//
//         options.url = '\/panda\/rest\/reference?code=U8Bank&filter=' + filter;
//         $(grid).datagrid(options);
//     }
//
//     $('#book_name').combogrid(options);
// });