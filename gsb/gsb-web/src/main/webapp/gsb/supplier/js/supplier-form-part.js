System.Declare("com.gongsibao.supplier.web");
com.gongsibao.supplier.web.SupplierFormPart = org.netsharp.panda.commerce.FormPart.Extends({

    ctor: function () {
        this.base();
    }
});

System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.SupplierProductDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({
    ctor: function () {
        this.base();
    },
    productCategory1Select: function (newValue, oldValue) {

        if (System.isnull(newValue))return;

        var rows = $("#" + this.context.id).datagrid('getSelections');
        //加载二级分类
        this.invokeService("queryByFirstProductCategoryId", [newValue], function (data) {
            $('#productCategory2_name').combobox('loadData', data);
            var productCategory2 = $('#productCategory2_name').combobox('getValue');
            //原产品大类别id
            var rowProductCategory2 = rows.length > 0 ? rows["0"].productCategoryId2 : "";
            var isHas = '';
            data.forEach(function (item, index) {
                if (item.id.toString() == rowProductCategory2) {
                    isHas += "t";
                }
            });
            //防止异步，慢于修改时的赋值，清空产品二级分类
            if (!(rows.length > 0 && rows["0"].productCategoryId2 == productCategory2 && isHas.indexOf("t") > -1))
                $('#productCategory2_name').combobox('clear');
        });
    },
    productCategory2Select: function (newValue, oldValue) {

        try {

            if (System.isnull(newValue))return;

            $('#product_name').combogrid('clear');
            var grid = $('#product_name').combogrid('grid');
            var options = $(grid).datagrid('options');
            var filter = ' enabled____1 and type_id____' + newValue;
            options.url = '\/panda\/rest\/reference?code=CRM_Product&filter=' + filter;
            $(grid).datagrid(options);

        } catch (ex) {

        }
    }
});