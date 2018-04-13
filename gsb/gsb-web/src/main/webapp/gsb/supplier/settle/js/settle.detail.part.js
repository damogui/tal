
System.Declare("com.gongsibao.trade.web.settle");
com.gongsibao.trade.web.settle.SettleFormPart = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
        this.base();
        this.service = 'com.gongsibao.trade.web.settle.SettleFormPart';
        this.handleStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.settle.dict.SettleHandleStatus');
        this.auditStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.bd.dic.AuditLogStatusType');
    },
    init: function () {
        var id = this.queryString('id');
        this.initStyle();
        this.getDetail(id);
        this.getLogs(id);
    },
    initStyle:function(){

        var centerHeight = $('body').height() - 240;
        $('#center').height(centerHeight);

        $('#detail_tabs').tabs({
            fit:true,
            tabHeight:35
        });
    },
    getDetail: function (id) {
        var me = this;
        me.invokeService("settleDetail", [id], function (data) {
            me.buildBaseInfo(data);
            me.buildOrder(data.settleOrderList)
        });
    },
    getLogs: function (id) {
        var me = this;
        me.invokeService("getLogs", [id], function (data) {
            me.buildLog(data);
        });
    },
    buildBaseInfo: function (data) {
        data = data || {};
        // 基本信息
        $("#id").html(data.id || "");
        $("#handleStatus").html(this.handleStatusEnum[data.handleStatus] || "-");
        $("#totalAmount").html(data.totalAmount || "-");
        $("#totalCost").html(data.totalCost || "-");
        $("#totalCharge").html(data.totalCharge || "-");
        $("#commission").html(data.commission || "-");
        $("#taxRate").html(data.taxRate || "-");
        $("#tax").html(data.tax || "-");
        $("#createTime").html(data.createTime || "-");
        $("#memo").html(data.memo || "无");
    },
    buildLog: function (logList) {
        var me = this;
        logList = logList || [];
        // 操作日志
        $('#settle_handle_log').datagrid({
            idField: 'id',
            emptyMsg: '暂无记录',
            striped: true,
            pagination: false,
            showFooter: true,
            singleSelect: false,
            height: 400,
            fitColumns:true,
            data: logList,
            columns: [[
                // {field: 'id', checkbox: true},
                {field:'afterStatus',title:'状态',width:150,align:'center',formatter:function(value,row,index){
                        if(value){
                        return me.auditStatusEnum[value];
                    }
                    return '-';
                }},
                {field: 'memo', title: '备注', width: 250, align: 'center'},
                {field: 'creator', title: '操作人', width: 150, align: 'center'},
                {field: 'createTime', title: '操作时间', width: 150, align: 'center'},
            ]]
        });
    },
    buildOrder: function (orderList) {
        var me = this;
        orderList = orderList || [];
        // 订单信息
        $('#settle_order_prod_grid').datagrid({
            idField: 'id',
            emptyMsg: '暂无记录',
            striped: true,
            pagination: false,
            showFooter: true,
            singleSelect: false,
            fitColumns:true,
            height: 400,
            data: orderList,
            columns: [[
                // {field: 'id', checkbox: true},
                {field: 'orderProdId', title: '订单明细编号', width: 150, align: 'center'},
                {field: 'no', title: '订单号', width: 150, align: 'center',formatter: function(value,row,index){
                    return row.soOrder.no;
                }},
                {field: 'productName', title: '产品名称', width: 200, align: 'center',formatter: function(value,row,index){
                    return row.orderProd.productName;
                }},
                {field: 'cost', title: '成本', width: 120, align: 'center'},
                {field: 'charge', title: '服务费', width: 120, align: 'center'},
                {field: 'commission', title: '佣金', width: 120, align: 'center'},
                {field: 'orderProd.price', title: '订单明细价格', width: 120, align: 'center',formatter: function(value,row,index){
                    return row.orderProd.price;
                }},
            ]]
        });
    },
});
