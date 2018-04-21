System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.SelectCompanyCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
        this.base();
        this.multiple = false;
        this.$gridCtrlId = '#company_grid';
        this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
        this.addCompanyUrl = "/panda/crm/company/form";
    },
    open: function (title, multiple, callback) {

        this.multiple = multiple;
        var me = this;
        var builder = new System.StringBuilder();
        builder.append('<div style="margin:0 10px;">');
        builder.append('	<table cellpadding="5" cellspacing="5" class="form-panel">');
        builder.append('		<tr><td><a href="javascript:;" class="l-btn l-btn-small l-btn-plain" id="addCompany"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">新增</span><span class="l-btn-icon fa fa-plus">&nbsp;</span></span></a></td></tr>');
        builder.append('		<tr><td style="color:red;">提示：填写关键字后敲回车执行查询</td></tr>');
        builder.append('		<tr><td><input id="company_keyword"/></td></tr>');
        builder.append('		<tr><td><table id="company_grid"></table></td></tr>');
        builder.append('	</table>');
        builder.append('</div>');

        //短信通知客户
        layer.open({
            type: 1,
            title: title,
            fixed: false,
            maxmin: false,
            shadeClose: true,
            zIndex: 100000,
            area: ['600px', '440px'],
            content: builder.toString(),
            btn: ['确定', '取消'],
            success: function (layero, index) {
                me.initGrid();
                me.addCompany();
                $("#company_keyword").searchbox({
                    height: 30,
                    width: 560,
                    prompt: '名称',
                    searcher: function (value, name) {
                        me.query();
                    }
                });
            },
            btn1: function (index, layero) {
                var rows = $(me.$gridCtrlId).datagrid('getChecked');
                if (rows.length == 0) {
                    layer.msg('请选择');
                    return;
                }
                callback(rows);
                layer.close(index);
            }
        });
    },
    initGrid: function () {

        var me = this;
        $(this.$gridCtrlId).datagrid({
            idField: 'id',
            height: 240,
            width: 560,
            striped: true,
            pagination: true,
            showFooter: true,
            pageSize: 10,
            singleSelect: !this.multiple,
            onLoadSuccess: function (data) {

                var pager = $(this).datagrid('getPager');
                $(pager).pagination('options').onSelectPage = function (pageNumber, pageSize) {

                    me.query(pageNumber, pageSize);
                };
            },
            columns: [[
                {field: 'id', checkbox: true, align: 'center'},
                {field: 'name', title: '名称', width: 100},
                {field: 'fullName', title: '完整公司名称', width: 200},
                {field: 'companyName', title: '工商审核批准的公司名称', width: 200}
            ]]
        });
    },
    query: function (pageIndex, pageSize) {

        pageIndex = pageIndex || 1;
        pageSize = pageSize || 10;
        var me = this;
        var keyWord = $('#company_keyword').searchbox('getValue');
        this.invokeService("queryCompanys", [keyWord, pageIndex, pageSize], function (data) {

            $(me.$gridCtrlId).datagrid('loadData', data);
        });
    },
    addCompany: function () {
        var me = this;
        var url = me.addCompanyUrl;
        $("#addCompany").click(function () {
            layer.open({
                id: "companyIframe",
                type: 2,
                title: '新增企业',
                fixed: false,
                maxmin: true,
                shadeClose: true,
                area: ['55%', '80%'],
                content: url,
                success: function (layero, index) {
                    layerIndex = index; //获取当前窗口的索引
                    layerInitWidth = $("#layui-layer" + layerIndex).width(); //获取layer的宽度
                    layerInitHeight = $("#layui-layer" + layerIndex).height(); //获取layer的高度
                    resizeLayer(layerIndex, layerInitWidth, layerInitHeight); //调用resizeLayer方法
                }
            });
        });
    }
});