System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.BaseCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {

        this.service = 'com.gongsibao.trade.web.OrderDetailController';
        this.processStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderProcessStatusType');
        this.offlineWayType = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OfflineWayType');
        this.receiptStatus = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.PayReceiptStatus');
        this.payForOrderCount = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.PayForOrderCountType');
        this.payWayTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.PayWayType');
        this.auditStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.AuditStatusType');
        this.refundWayTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.RefundWayType');
        this.auditLogStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.bd.dic.AuditLogStatusType');
        this.platformSourceTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPlatformSourceType');
        this.payStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPayStatusType');
        this.initBtn();//初始化按钮
    },
    initBtn:function () {
        $("body").off("click", ".show");
        $("body").on("click", ".show", function () {

            var orderId = $(this).attr("data-id");
            var url = $(this).attr("data-url");
            var title = $(this).attr("data-title");
            var idFraeam = $(this).attr("data-iframe");
            var urlEnd = url + "?id=" + orderId;
            showDetail(idFraeam, title, urlEnd);

        });

    }
});

/*
 * 订单表单
 */
com.gongsibao.trade.web.OrderFormCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({

    ctor: function () {

        this.base();
        this.initializeDetailList = new System.Dictionary();
    },
    init: function () {
        //1.添加‘合同信息’页签显示
        var orderId = this.queryString('id');
        this.invokeService("queryContractFirst", [orderId], function (data) {
            if (data != null) {
                var content = '<iframe id="iframe_contractInfo" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>';
                addTab("合同信息", content);
            }
        });
        //2.添加‘商机信息’页签显示
        var taskId = 0;
        var customerId = 0;
        this.invokeService("queryTaskInfo", [orderId], function (data) {
            if (data != null) {
                taskId = data.id;
                customerId = data.customerId;
                var content = '<iframe id="iframe_taskInfo" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>';
                addTab("商机信息", content);
            }
        });
        var me = this;
        //tab页签
        $('#tabs').tabs({
            tabHeight: 30,
            onSelect: function (title) {
                var detailCtrl = me.initializeDetailList.byKey(title);
                if (detailCtrl) {
                    //已经初始化过的不再执行
                    return;
                }
                if (title == '合同信息') {
                    var contractCtrl = new com.gongsibao.trade.web.ContractCollectionDetailCtrl();
                    contractCtrl.init();
                    me.initializeDetailList.add(title, contractCtrl);

                } else if (title == '商机信息') {
                    var url = '/nav/gsb/supplier/crm/salesman/task?taskId=' + taskId + '&customerId=' + customerId;
                    $('#iframe_taskInfo').attr('src', url);
                }
            }
        });
        //详情页签
        $('#detail_tabs').tabs({
            tabHeight: 30,
            onSelect: function (title) {

                var detailCtrl = me.initializeDetailList.byKey(title);
                if (detailCtrl) {
                    //已经初始化过的不再执行
                    return;
                }
                if (title == '回款记录') {

                    var paymentCollectionDetailCtrl = new com.gongsibao.trade.web.OrderPaymentCollectionDetailCtrl();
                    paymentCollectionDetailCtrl.init();
                    me.initializeDetailList.add(title, paymentCollectionDetailCtrl);

                }
                else if (title == '订单业绩分配') {

                    var orderPerCtrl = new com.gongsibao.trade.web.OrderPerCollectionDetailCtrl();
                    orderPerCtrl.init();
                    me.initializeDetailList.add(title, orderPerCtrl);

                }
                else if (title == '回款业绩记录') {
                    var orderPayPerCtrl = new com.gongsibao.trade.web.OrderPayPerCollectionDetailCtrl();
                    orderPayPerCtrl.init();
                    me.initializeDetailList.add(title, orderPayPerCtrl);

                }
                else if (title == '退款记录') {
                    var refundDetailCtrl = new com.gongsibao.trade.web.OrderRefundDetailCtrl();
                    refundDetailCtrl.init();
                    me.initializeDetailList.add(title, refundDetailCtrl);

                } else if (title == '改价记录') {
                    var changePriceDetailCtrl = new com.gongsibao.trade.web.OrderChangePriceDetailCtrl();
                    changePriceDetailCtrl.init();
                    me.initializeDetailList.add(title, changePriceDetailCtrl);

                } else if (title == '优惠明细') {
                    var discountDetailCtrl = new com.gongsibao.trade.web.OrderDiscountDetailCtrl();
                    discountDetailCtrl.init();
                    me.initializeDetailList.add(title, discountDetailCtrl);

                } else if (title == '流转日志') {
                    var fllowDetailCtrl = new com.gongsibao.trade.web.OrderFollowDetailCtrl();
                    fllowDetailCtrl.init();
                    me.initializeDetailList.add(title, fllowDetailCtrl);
                }
                else if (title == '签单公司') {
                    var fllowDetailCtrl = new com.gongsibao.trade.web.OrderCompanysCtrl();
                    fllowDetailCtrl.init();
                    me.initializeDetailList.add(title, fllowDetailCtrl);
                }
            }
        });

        //加载产品信息
        var productDetailCtrl = new com.gongsibao.trade.web.OrderProductDetailCtrl();
        productDetailCtrl.init();
        me.initializeDetailList.add('产品信息', productDetailCtrl);
    }
});

//动态添加tab标签
function addTab(title, content) {
    if ($('#tabs').tabs('exists', title)) {
        $('#tabs').tabs('select', title);
    } else {
        $('#tabs').tabs('add', {
            title: title,
            selected: false,
            content: content,
            closable: false
        });
    }
}

/*
 * 合同详情记录
 */
function showContract(contractId) {
    var contentUrl = "/panda/trade/audit/contract/form?id=" + contractId + "";
    layer.open({
        id: "contractCreateIframe",
        type: 2,//1是字符串 2是内容
        title: '合同信息',
        fixed: false,
        maxmin: true,
        shadeClose: false,
        area: ['60%', '90%'],
        zIndex: 100000,
        content: contentUrl
    });
}

com.gongsibao.trade.web.ContractCollectionDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
        this.base();
    },
    init: function () {
        var me = this;
        var orderId = this.queryString('id');
        this.invokeService("queryContractFirst", [orderId], function (data) {
            me.initGrid(data);
        });
    },
    initGrid: function (data) {
        var me = this;
        var contractId = data.id;
        $('#iframe_contractInfo').datagrid({
            idField: 'id',
            emptyMsg: '暂无记录',
            striped: true,
            pagination: false,
            showFooter: true,
            singleSelect: true,
            height: '100%',
            data: [data],
            columns: [[
                {
                    field: 'a', title: '操作', width: 80, align: 'center', formatter: function (value, row, index) {
                        return '<a class="grid-btn" href="javascript:" onclick="showContract(' + contractId + ')";>查看</a>';
                    }
                },
                {field: 'id', title: '合同审核编号', width: 80, align: 'center'},
                {
                    field: 'dataFee',
                    title: '材料撰写费',
                    width: 100,
                    align: 'center',
                    formatter: function (value, row, index) {
                        return (value / 100).toFixed(2);
                    }
                },
                {
                    field: 'liquidatedDamages',
                    title: '违约金',
                    width: 100,
                    align: 'center',
                    formatter: function (value, row, index) {
                        return (value / 100).toFixed(2);
                    }
                },
                {
                    field: 'liquidatedDamages',
                    title: '订单金额',
                    width: 80,
                    align: 'center',
                    formatter: function (value, row, index) {
                        return (value / 100).toFixed(2);
                    }
                },
                {
                    field: 'realAmount',
                    title: '合同总额',
                    width: 100,
                    align: 'center',
                    formatter: function (value, row, index) {
                        return (value / 100).toFixed(2);
                    }
                },
                {field: 'creator', title: '创建人', width: 100},
                {field: 'createTime', title: '创建时间', width: 130, align: 'center'},
                {
                    field: 'auditStatusId',
                    title: '审核状态',
                    width: 100,
                    align: 'center',
                    formatter: function (value, row, index) {
                        return me.auditStatusTypeEnum[value];
                    }
                }
            ]]
        });
    }
});


/*
 * 订单产品明细
 */
com.gongsibao.trade.web.OrderProductDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {

        this.base();
    },
    init: function () {

        var me = this;
        var orderId = this.queryString('id');
        this.invokeService("queryProductList", [orderId], function (data) {

            me.initGrid(data);
        });
    },
    initGrid: function (data) {
        var me = this;
        $('#order_product_grid').datagrid({
            idField: 'id',
            emptyMsg: '暂无记录',
            striped: true,
            pagination: false,
            showFooter: true,
            singleSelect: true,
            height: '100%',
            data: data,
            columns: [[
                {
                    field: 'a', title: '操作', width: 80, align: 'center', formatter: function (value, row, index) {

                        return '<a class="grid-btn" target="_blank" href="/nav/gsb/platform/trade/orderProdDetail?id=' + row.id + '">查看</a>';
                    }
                },
                {field: 'id', title: '订单明细编号', width: 100, align: 'center'},
                {field: 'productName', title: '产品名称', width: 150},
                {
                    field: 'serviceName', title: '服务名称', width: 200, formatter: function (value, row, index) {

                        var items = row.items;
                        if (items && items.length > 0) {

                            var len = items.length;
                            if (len == 1) {
                                return items[0].serviceName;
                            } else {

                                var serviceName = items[0].serviceName + '...';
                                var ss = [];
                                $(items).each(function (i, item) {

                                    ss.push(item.serviceName);
                                });
                                var fullServiceName = ss.join(',');
                                var tipId = 'tip' + row.id;
                                return '<a id="' + tipId + '" onmouseover="layer.tips(\'' + fullServiceName + '\',\'#' + tipId + '\',{tips: [1, \'#1E7BB6\']})">' + serviceName + '</a>';
                            }
                        }
                    }
                },
                {field: 'cityName', title: '产品地区', width: 150},
                {
                    field: 'priceOriginal',
                    title: '原价',
                    width: 100,
                    align: 'right',
                    formatter: function (value, row, index) {
                        return (value / 100).toFixed(2);
                    }
                },
                {
                    field: 'price', title: '售价', width: 100, align: 'right', formatter: function (value, row, index) {

                        return (value / 100).toFixed(2);
                    }
                },
                {
                    field: 'processStatusId',
                    title: '办理进度',
                    width: 100,
                    align: 'center',
                    formatter: function (value, row, index) {

                        if (value) {

                            return me.processStatusEnum[value];
                        }
                        return '-';
                    }
                },
//		        {field:'ownerId',title:'业务员',width:80,align:'center',formatter:function(value,row,index){
//	        		
//	        		if(row.owner){
//	        			
//	        			return row.owner.name;
//	        		}else {
//	        			
//	        			return '-';
//	        		}
//		        }}
            ]]
        });
    }
});

/*
 * 订单回款记录
 */
com.gongsibao.trade.web.OrderPaymentCollectionDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {

        this.base();
    },
    init: function () {

        var me = this;
        var orderId = this.queryString('id');
        this.invokeService("queryPayList", [orderId], function (data) {

            me.initGrid(data);
        });


    },
    initGrid: function (data) {

        var me = this;
        $('#order_payment_grid').datagrid({
            idField: 'id',
            emptyMsg: '暂无记录',
            striped: true,
            pagination: false,
            showFooter: true,
            singleSelect: true,
            height: '100%',
            data: data,
            columns: [[

                {
                    field: 'id', title: '操作', width: 80, align: 'center', formatter: function (value, row, index) {


                        return '<a class="grid-btn show"  data-iframe="auditPay" data-title="回款信息"   data-id="' + value + '"  data-url="/nav/gsb/platform/trade/auditPay"    href="javascript:void(0)">查看</a>';
                    }
                },
                {
                    field: 'payForOrderCount',
                    title: '回款类别',
                    width: 80,
                    align: 'center',
                    formatter: function (value, row, index) {

                        return me.payForOrderCount[value];
                    }
                },
                {field: 'no', title: '支付编号', width: 100},
                // {field: 'receiptNo', title: '审核编号', width: 100},
                {
                    field: 'amount', title: '支付金额', width: 80, align: 'right', formatter: function (value, row, index) {

                        return (value / 100).toFixed(2);
                    }
                },
                {field: 'offlinePayerName', title: '账户名称', width: 100},
                {field: 'offlineBankNo', title: '付款账号', width: 100},
                {
                    field: 'payWayType',
                    title: '付款类别',
                    width: 80,
                    align: 'center',
                    formatter: function (value, row, index) {

                        return me.payWayTypeEnum[value];
                    }
                },
                {
                    field: 'offlineWayType',
                    title: '付款方式',
                    width: 80,
                    align: 'center',
                    formatter: function (value, row, index) {

                        return me.offlineWayType[value];
                    }
                },
                {field: 'payTime', title: '回款日期', width: 130, align: 'center'},
                {field: 'confirmTime', title: '审核通过时间', width: 130, align: 'center'},
                {field: 'createTime', title: '创建时间', width: 120, align: 'center'},
                {
                    field: 'receiptStatus',
                    title: '状态',
                    width: 100,
                    align: 'center',
                    formatter: function (value, row, index) {

                        return me.receiptStatus[value];
                    }
                }
            ]]
        });

    }
});

/*
 * 订单业绩分配
 */
com.gongsibao.trade.web.OrderPerCollectionDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {

        this.base();
    },
    init: function () {

        var me = this;
        var orderId = this.queryString('id');
        this.invokeService("queryOrderPerList", [orderId], function (data) {

            me.initGrid(data);
        });


    },
    initGrid: function (data) {

        var me = this;
        $('#order_per').datagrid({
            idField: 'id',
            emptyMsg: '暂无记录',
            striped: true,
            pagination: false,
            showFooter: true,
            singleSelect: true,
            height: '100%',
            data: data,
            columns: [[

                {
                    field: 'orderId', title: '操作', width: 80, align: 'center', formatter: function (value, row, index) {
                        return '<a class="grid-btn show"  data-iframe="auditPerformance" data-title="订单业绩信息"  data-id="' + value + '"  data-url="/nav/gsb/platform/trade/auditPerformance"    href="javascript:void(0)">查看</a>';
                    }
                },
                // {field: 'auditNo', title: '审核编号', width: 100},
                {
                    field: 'totalPrice',
                    title: '原价金额',
                    width: 80,
                    align: 'right',
                    formatter: function (value, row, index) {

                        return (value / 100).toFixed(2);
                    }
                },
                {
                    field: 'payablePrice',
                    title: '应付金额',
                    width: 80,
                    align: 'right',
                    formatter: function (value, row, index) {

                        return (value / 100).toFixed(2);
                    }
                },
                {
                    field: 'amount',
                    title: '订单业绩分配金额',
                    width: 80,
                    align: 'right',
                    formatter: function (value, row, index) {

                        return (value / 100).toFixed(2);
                    }
                },

                {
                    field: 'statusType',
                    title: '审核状态',
                    width: 80,
                    align: 'center'

                },
                {field: 'createTime', title: '创建时间', width: 120, align: 'center'},
                {field: 'confirmTime', title: '审核通过时间', width: 130, align: 'center'},
                {field: 'creator', title: '订单业绩创建人', width: 100, align: 'center'}
            ]]
        });

    }
});


/*
 * 回款业绩记录
 */
com.gongsibao.trade.web.OrderPayPerCollectionDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {

        this.base();
    },
    init: function () {

        var me = this;
        var orderId = this.queryString('id');
        this.invokeService("queryOrderPayPerList", [orderId], function (data) {

            me.initGrid(data);
        });



    },
    initGrid: function (data) {

        var me = this;
        $('#order_pay_per').datagrid({
            idField: 'id',
            emptyMsg: '暂无记录',
            striped: true,
            pagination: false,
            showFooter: true,
            singleSelect: true,
            height: '100%',
            data: data,
            columns: [[

                {
                    field: 'orderId', title: '操作', width: 80, align: 'center', formatter: function (value, row, index) {

                        return '<a class="grid-btn show"  data-iframe="auditPayPerformance"  data-title="回款业绩信息"  data-id="' + value + '"  data-url="/nav/gsb/platform/trade/auditPayPerformance"    href="javascript:void(0)">查看</a>';
                    }
                },
                // {field: 'auditNo', title: '审核编号', width: 100},

                {
                    field: 'amount',
                    title: '回款业绩分配金额',
                    width: 80,
                    align: 'right',
                    formatter: function (value, row, index) {

                        return (value / 100).toFixed(2);
                    }
                },

                {
                    field: 'statusType',
                    title: '审核状态',
                    width: 80,
                    align: 'center',
                    formatter: function (value, row, index) {

                        return me.auditStatusTypeEnum[value];
                    }
                },
                {field: 'createTime', title: '创建时间', width: 120, align: 'center'},
                {field: 'confirmTime', title: '审核通过时间', width: 130, align: 'center'},
                {field: 'creator', title: '回款业绩创建人', width: 100, align: 'center'}
            ]]
        });

    }
});


/*
 * 订单退款明细
 */
com.gongsibao.trade.web.OrderRefundDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {

        this.base();
    },
    init: function () {

        var me = this;
        var orderId = this.queryString('id');
        this.invokeService("queryRefundList", [orderId], function (data) {
            me.initGrid(data, orderId);
        });
    },
    initGrid: function (data, orderId) {

        var me = this;
        $('#order_refund_grid').datagrid({
            idField: 'id',
            emptyMsg: '暂无记录',
            striped: true,
            pagination: false,
            showFooter: true,
            singleSelect: true,
            height: '100%',
            data: data,
            columns: [[

                {
                    field: 'a', title: '操作', width: 80, align: 'center', formatter: function (value, row, index) {
                        return '<a class="grid-btn" target="_blank" href="/nav/gsb/platform/trade/auditRefund?fefundId=' + row.id + '&id=' + orderId + '">查看</a>';
                    }
                },
                {field: 'no', title: '退款记录编号', width: 100},
                // {field: 'serviceName', title: '审核编号', width: 100},
                {
                    field: 'amount',
                    title: '退款金额',
                    width: 100,
                    align: 'right',
                    formatter: function (value, row, index) {

                        return (value / 100).toFixed(2);
                    }
                },
                {
                    field: 'orderProdId', title: '退款产品', width: 150, formatter: function (value, row, index) {

                    }
                },
                {
                    field: 'cityName',
                    title: '退款产品地区',
                    width: 150,
                    align: 'right',
                    formatter: function (value, row, index) {


                    }
                },
                {
                    field: 'processStatus',
                    title: '办理进度',
                    width: 80,
                    align: 'center',
                    formatter: function (value, row, index) {

                        if (value) {

                            return me.processStatusEnum[value];
                        }
                        return '-';
                        //订单产品的办理进度
                    }
                },
                {
                    field: 'wayType',
                    title: '退款方式',
                    width: 80,
                    align: 'center',
                    formatter: function (value, row, index) {

                        if (value) {

                            return me.refundWayTypeEnum[value];
                        }
                        return '-';
                    }
                },
                {field: 'refundTime', title: '退款时间', width: 130, align: 'center'},
                {field: 'createTime', title: '创建时间', width: 130, align: 'center'},
                {
                    field: 'auditStatus',
                    title: '审核状态',
                    width: 80,
                    align: 'center',
                    formatter: function (value, row, index) {

                        if (value) {

                            return me.auditStatusTypeEnum[value];
                        }
                        return '-';
                    }
                }
            ]]
        });
    }
});


/*
 * 订单改价明细
 */
com.gongsibao.trade.web.OrderChangePriceDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {

        this.base();
    },
    init: function () {

        var me = this;
        var orderId = this.queryString('id');
        this.invokeService("queryChangePriceList", [orderId], function (data) {

            me.initGrid(data, orderId);
        });
    },
    initGrid: function (data, orderId) {

        var me = this;
        $('#order_change_price_grid').datagrid({
            idField: 'id',
            emptyMsg: '暂无记录',
            striped: true,
            pagination: false,
            showFooter: true,
            singleSelect: true,
            height: '100%',
            data: data,
            columns: [[

                {
                    field: 'a', title: '操作', width: 80, align: 'center', formatter: function (value, row, index) {
                        return '<a class="grid-btn" target="_blank" href="/nav/gsb/platform/trade/auditOrder?id=' + orderId + '">查看</a>';
                    }
                },
                {field: 'no', title: '改价审核编号', width: 100, align: 'center'},
                {
                    field: 'originalPrice',
                    title: '改价前金额',
                    width: 100,
                    align: 'right',
                    formatter: function (value, row, index) {
                        return (value / 100).toFixed(2);
                    }
                },
                {
                    field: 'payablePrice',
                    title: '改价后金额',
                    width: 100,
                    align: 'right',
                    formatter: function (value, row, index) {
                        return (value / 100).toFixed(2);
                    }
                },
                {
                    field: 'differencePrice',
                    title: '差额',
                    width: 100,
                    align: 'right',
                    formatter: function (value, row, index) {
                        return (value / 100).toFixed(2);
                    }
                },
                {field: 'creator', title: '发起人', width: 100, align: 'center'},
                {field: 'createTime', title: '发起时间', width: 130, align: 'center'},
                {
                    field: 'status',
                    title: '审核状态',
                    width: 80,
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (value) {

                            return me.auditLogStatusTypeEnum[value];
                        }
                        return '-';
                    }
                }
            ]]
        });
    }
});


/*
 * 优惠明细
 */
com.gongsibao.trade.web.OrderDiscountDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {

        this.base();
    },
    init: function () {

        var me = this;
        var orderId = this.queryString('id');
        this.invokeService("queryDiscountList", [orderId], function (data) {

            me.initGrid(data);
        });
    },
    initGrid: function (data) {

        var me = this;
        $('#order_discount_grid').datagrid({
            idField: 'id',
            emptyMsg: '暂无记录',
            striped: true,
            pagination: false,
            showFooter: true,
            singleSelect: true,
            height: '100%',
            data: data,
            columns: [[

                {field: 'no', title: '优惠券码', width: 200},
                {
                    field: 'amount', title: '优惠金额', width: 80, align: 'right', formatter: function (value, row, index) {
                        return (value / 100).toFixed(2);
                    }
                },
                {
                    field: 'preferentialId', title: '优惠券制作人', width: 100, formatter: function (value, row, index) {

                        if (row.preferential) {

                            return row.preferential.creator;
                        }
                    }
                },
                {field: 'createTime', title: '使用时间', width: 130, align: 'center'}
            ]]
        });
    }
});

/*
 * 订单流转日志
 */
com.gongsibao.trade.web.OrderFollowDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {

        this.base();
    },
    init: function () {

        var me = this;
        var orderId = this.queryString('id');
        this.invokeService("queryExchangeLogList", [orderId], function (data) {

            me.initGrid(data);
        });
    },
    initGrid: function (data) {

        $('#order_follow_grid').datagrid({
            idField: 'id',
            emptyMsg: '暂无记录',
            striped: true,
            pagination: false,
            showFooter: true,
            singleSelect: true,
            height: '100%',
            data: data,
            columns: [[

                {field: 'formUserName', title: '来自', width: 80, align: 'center'},
                {field: 'toUserName', title: '去向', width: 80, align: 'center'},
                {field: 'content', title: '转移通知', width: 300},
                {field: 'creator', title: '操作人', width: 80, align: 'center'},
                {field: 'createTime', title: '操作时间', width: 130, align: 'center'}
            ]]
        });
    }
});


/*
 * 订单关联公司  签单公司
 */

com.gongsibao.trade.web.OrderCompanysCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {

        this.base();
    },
    init: function () {

        var me = this;
        var  orderId = this.queryString('id');
        this.invokeService("getCompanyInfo", [orderId], function (data) {

            me.initGrid(data);
        });
    },
    initGrid: function (data) {
       
        var me = this;
        $('#order_bill_company').datagrid({
            idField: 'id',
            emptyMsg: '暂无记录',
            striped: true,
            pagination: false,
            showFooter: true,
            singleSelect: true,
            height: '100%',
            toolbar: [{
                iconCls: 'fa fa-plus',
                text: '新增',
                handler: function () {

                    me.add();
                }
            }, '-', {
                iconCls: 'fa fa-remove',
                text: '删除',
                handler: function () {

                    me.remove();
                }
            }],
            data: data,
            columns: [[
                {field: 'companyId', title: 'ID', width: 100, align: 'center'},
                {
                    field: 'companyName',
                    title: '公司名称',
                    width: 300,
                    align: 'center'

                }


            ]]
        });
    },
    add: function () {

        var rows = $("#order_bill_company").datagrid('getRows');

        if (rows.length > 0) {

            layer.msg("已经添加签单公司");
            return;
        }

        //添加进行关联
        var me = this;
        var builder = new System.StringBuilder();
        builder.append(' <form id="companys_form" style="" class="layui-layer-wrap"><div class="formContent">');
        builder.append('	<table cellpadding="5" cellspacing="5" class="form-panel">');
        builder.append('		<tr class="label_tr"> <td  class="label_td"> <label style="color:Red">*</label> <label for="companyName">公司名称：</label></td>');
        builder.append('		<td class="label_td"><input id="company_companyName"  name="companyName" type="combogrid"/></td></tr></table></div></form>');
        
        var  orderId = me.queryString('id');
        layer.open({
            type: 1,
            title: '添加签单公司',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['25%', '20%'],
            zIndex: 100000,
            id: "ordercom",
            content: builder.toString(),
            btn: ['确定', '取消'],
            success: function (layero, index) {
                $("#company_companyName").combogrid(me.getComOption());

            },
            yes: function (index, layero) {

                var g = $('#company_companyName').combogrid('grid');	// 获取数据表格对象
                var row = g.datagrid('getSelected');	// 获取选择的行
               
                var obj = {companyId: row.id, companyName: row.companyName};
                me.invokeService("optCompanyInfoByorderId", [orderId,row.id], function (data) {//删除


                });

                //进行追加
                $("#order_bill_company").datagrid('appendRow', obj);//然后保存

                layer.closeAll();


            }
        });
    },


    getRows: function () {

        var rows = $("#order_bill_company").datagrid('getRows');
        return rows;
    },
    remove: function () {

       
        var row = $("#order_bill_company").datagrid('getSelected');
        if (row == null) {
            layer.msg("请选择数据记录");
            return;
        }

        //提示确认
        var index = $("#order_bill_company").datagrid('getRowIndex', row);

        this.invokeService("optCompanyInfoByorderId", [row.id,0], function (data) {//删除
            $("#order_bill_company").datagrid('deleteRow', index);
        });
    },
    getComOption: function () {//配置options
       

        var comOption = {
            columns: [[{
                field: 'companyName',
                title: '名称',
                width: 100
            }]],
            url: '\/panda\/rest\/reference?code=CompanyIntention', //+ filter,
            idField: 'id',
            textField: 'companyName',
            width: 300,
            fitColumns: true,
            panelWidth: 500,
            panelHeight: 310,
            pagination: true,
            pageSize: 10,
            mode: 'remote',
            multiple: false,
            toolbar: [{
            	text:'新增',iconCls:'fa fa-plus',handler:function(){
            		
            		IMessageBox.open('新增', '/panda/crm/company/form?openType=window', 1000, 600, function(){});
            		
            		}
            	}
            ],
            onChange: function (newValue, oldValue) {
                //
                // //var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE supplier_id ____ ----'+ newValue + '----)';
                // var grid = this.grid;
                // filter = ' companyName ____ ----' + newValue + '----';
                // grid.options.url = '\/panda\/rest\/reference?code=CompanyIntention&filter=' + filter;
                // $(grid).datagrid(options);

            }
        };

        return comOption;

    }
});


/*
 * 订单合同
 */
com.gongsibao.trade.web.OrderContractCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {

        this.base();
    },
    init: function () {

    }
});

/*
 * 订单商机明细
 */
com.gongsibao.trade.web.OrderTaskDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {

        this.base();
    },
    init: function () {


    }
});


/*进行显示查看详情*/
function showDetail(id, title, url) {
    layer.open({
        type: 2,//1是字符串 2是内容
        title: title,
        fixed: false,
        maxmin: true,
        shadeClose: false,
        area: ['60%', '60%'],
        zIndex: 100000,
        id: id,
        content: url,
        // btn: ['保存', '取消'],// 可以无限个按钮
        success: function (layero, index) {

        }


    });


}