System.Declare("com.gongsibao.trade.web");
//创建回款业绩
com.gongsibao.trade.web.OrderReceivePerformanceDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({

    ctor: function () {
        this.base();
        this.addUrl = null;
        this.editUrl = null;
        this.followUrl = null;
        this.addCustomerUrl = null;
    },
    add:function () {

        alert("添加");

    },
        bankBooksChange: function (newValue, oldValue) {//账套联动

        //改变部门的查询条件
        $('#department_name').combogrid('clear');
        var grid = $('#department_name').combogrid('grid');
        var options = $(grid).datagrid('options');
        var filter = ' set_of_books_id ____ ----' + newValue + '----';
        options.url = '\/panda\/rest\/reference?code=U8Bank&filter=' + filter;
        $(grid).datagrid(options);

    }

});

