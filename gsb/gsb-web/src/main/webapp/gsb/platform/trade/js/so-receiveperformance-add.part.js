System.Declare("com.gongsibao.trade.web");

var isInit = 0;//0是页面初始化1否
var payId = 0;//在线支付的时候从后台获取的支付id
com.gongsibao.trade.web.SoCreatReceivePerformanceFormPart = org.netsharp.panda.commerce.FormPart.Extends({

    save: function () {
        var me = this;
        var depPayMapDTO = {};
        var imgs = [];
        var totalCutAmount=0;//添加的划分金额总额

        var checkVal = checkFormVal();//校验表单
        if (checkVal == 1) {
            return 0;//校验没通过
        }
        depPayMapDTO.isOnlinePay = $("#isOnlinePay")[0].checked;//是否在线支付
        if(depPayMapDTO.isOnlinePay){
            if (payId==0){
                
                IMessageBox.toast("不存在支付记录",2);
                return;
            }



        }


        depPayMapDTO.noCutAmount =parseFloat($("#onLineNotCutPay").numberbox('getValue')) ;//未创建业绩总额
        depPayMapDTO.payId = payId;
        //depPayMapDTO.orderPayMaps = []; //$("#pays_u8Bank_setOfBooks_name").textbox("getValue");
        depPayMapDTO.setOfBooks = $("#pays_u8Bank_setOfBooks_name").combogrid("getValue");
        depPayMapDTO.u8Bank = $("#pays_u8Bank_name").combogrid("getValue");
        depPayMapDTO.offlinePayerName = $("#offlinePayerName").val();
        depPayMapDTO.offlineBankNo = $("#offlineBankNo").val();
        depPayMapDTO.payForOrderCount = $("#payForOrderCount")[0].checked;
        depPayMapDTO.amount =parseFloat($("#amount").numberbox('getValue')) ;

        //depPayMapDTO.files = $(".btn-preview").attr("href");//凭证图片
        imgs.push($(".btn-preview").attr("href"));
        depPayMapDTO.imgs=imgs;
        depPayMapDTO.offlineRemark = $("#offlineRemark").val();
        var rows = $('#datagridpays').datagrid('getRows');//添加的行
        var orderRelations = [];
        
        $(rows).each(function (i, item) {
            var orderRelation = {};
            orderRelation.orderId = item.orderId;
            orderRelation.orderCutAmount =parseFloat(item.orderCutAmount);
            orderRelation.payType = item.payType;

            orderRelation.items = item.items;
            orderRelations.push(orderRelation);
            totalCutAmount+=parseFloat(item.orderCutAmount) ;

        });
        depPayMapDTO.orderRelations = orderRelations;
        var  maxCut=$("#amount").numberbox('getValue');
        
        if(!depPayMapDTO.payForOrderCount&&totalCutAmount>parseFloat(maxCut)){

            IMessageBox.toast('业绩分配总额超过支付金额');
            return;
        }

        me.invokeService('saveNDepReceivableBySoder', [depPayMapDTO], function (data) {

            if (data > 0) {

                IMessageBox.toast('保存成功');
                return 1;

            } else {

                IMessageBox.toast('保存失败');
                return 0;
            }
        });


        return 1;

    },
});
//创建回款业绩
com.gongsibao.trade.web.OrderReceivePerformanceDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({

    ctor: function () {
        this.base();
        this.init();

    }, init: function () {

        $("#imagefiles").hide();
        /*图片上传*/
        $("body").off("click", "#imagefiles");
        $("body").on("click", "#imagefiles", function () {
            //var imageUrl=uploadImg(files)

            alert(imageUrl);

        });


        $("body").off("click", "#peperformanceAdd");
        $("body").on("click", "#peperformanceAdd", function () {

            var me = this;
            var supplierOption = getSupplierOption();
            var departmentOption = getDepartmentOption();
            var employeeOption = getEmployeeOption();
            PandaHelper.openDynamicForm({
                title: '回款业绩分配金额',
                width: 450,
                height: 400,
                items: [{
                    id: 'cut_supplier_name',
                    title: '服务商',
                    type: 'combogrid',
                    className: '',
                    option: supplierOption
                },

                    {
                        id: 'cut_department_name',
                        title: '部门',
                        type: 'combogrid',
                        className: '',
                        option: departmentOption
                    },

                    {
                        id: 'cut_employee_name',
                        title: '业务员',
                        type: 'combogrid',
                        className: '',
                        option: employeeOption
                    },
                    {
                        id: 'cut_amount',
                        title: '回款业绩分配',
                        type: 'numberbox',
                        className: '',
                        option: employeeOption
                    }
                ],
                callback: function (index, layero) {

                    var supplierId = $('#cut_supplier_name').combogrid('getValue');
                    var departmentId = $('#cut_department_name').combogrid('getValue');
                    var toUserId = $('#cut_employee_name').combogrid('getValue');
                    var cutamount = $('#cut_amount').numberbox('getValue');

                    var suppliername = $('#cut_supplier_name').combogrid('getText');
                    var departmentname = $('#cut_department_name').combogrid('getText');
                    var toUsername = $('#cut_employee_name').combogrid('getText');


                    if (System.isnull(supplierId)) {
                        IMessageBox.info('请选择服务商');
                        return;
                    }
                    if (System.isnull(departmentId)) {
                        IMessageBox.info('请选择部门');
                        return;
                    }
                    if (System.isnull(toUserId)) {
                        IMessageBox.info('请选择业务员');
                        return;
                    }
                    if (System.isnull(cutamount)) {
                        IMessageBox.info('请输入金额');
                        return;
                    }
                    layer.close(index);
                    //layer.closeAll();
                    var orderDepPay = {};//业绩划分弹窗数据
                    orderDepPay.supplierId = supplierId;
                    orderDepPay.departmentId = departmentId;
                    orderDepPay.salesmanId = toUserId;
                    orderDepPay.amount = cutamount;
                    orderDepPay.suppliername = suppliername;
                    orderDepPay.departmentname = departmentname;
                    orderDepPay.salesmanname = toUsername;
                    $('#order_product_grid').datagrid('appendRow', orderDepPay);//赋值
                    return;

                }
            });

        });


        $("body").off("click", "#peperformanceDel");
        $("body").on("click", "#peperformanceDel", function () {

            var rows = $('#order_product_grid').datagrid('getSelected');
            if (rows.length != undefined) {
                for (var i = rows.length - 1; i >= 0; i--) {
                    var index = $('#order_product_grid').datagrid('getRowIndex', rows[i]);
                    $('#order_product_grid').datagrid('deleteRow', index);
                }
            }
            else {

                var index = $('#order_product_grid').datagrid('getRowIndex', rows);
                $('#order_product_grid').datagrid('deleteRow', index);
            }

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
                {field: 'id', checkbox: true},
                {field: 'suppliername', title: '服务商', width: 100, align: 'center'},
                {field: 'departmentname', title: '部门', width: 150},

                {field: 'salesmanname', title: '业务员', width: 150},

                {
                    field: 'amount',
                    title: '回款业绩分配',
                    width: 100,
                    align: 'right',
                    formatter: function (value, row, index) {
                        return value;

                        // return (value / 100).toFixed(2);
                    }
                }

            ]]
        });
    },
    
    add: function () {
        var me = this;
        var checkVal = checkFormVal();//校验表单
        if (checkVal == 1) {
            return;//校验没通过
        }
        var formId = System.GUID.newGUID();
        var builder = new System.StringBuilder();
        builder.append('<form id="addFormPerformance">');
        builder.append('<div style="margin:10px;">');
        builder.append('<table cellpadding="3" cellspacing="0" class="form-panel" style="width:100%;">');
        builder.append('<tr><td class="title" style="width:100px;text-align: right;">订单编号</td><td><input id="orderNo" class="easyui-numberbox"/></td>');
        builder.append('<td class="title" style="width:100px;text-align: right;"> 订单分配金额</td><td><input id="orderCutPrice"  class="easyui-numberbox"/> </td>');
        builder.append('<td class="title" style="width:100px;text-align: right;"> 付款类别</td><td> <input id="payType" class="easyui-combobox" /></td></tr>');
        builder.append('</table>');
        builder.append('</div>');
        builder.append('</form>');
        builder.append('<div class="panel-header" style="width: 1019px;"><div class="panel-title">业绩划分</div><div class="panel-tool"></div></div>');
        builder.append('<div id="controllerdepReceivableToolbar" class="toolbar datagrid-toolbar"> <a iconcls="fa fa-plus fa-fw"  plain="true" id="peperformanceAdd" class="easyui-linkbutton l-btn l-btn-small l-btn-plain"  group=""><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">新增</span><span class="l-btn-icon fa fa-plus fa-fw">&nbsp;</span></span></a> <a iconcls="fa fa-trash-o"  plain="true" id="peperformanceDel" class="easyui-linkbutton l-btn l-btn-small l-btn-plain" code="performanceDetailDel" group=""><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">删除</span><span class="l-btn-icon fa fa-trash-o">&nbsp;</span></span></a> </div>');

        builder.append('  <table id="order_product_grid"></table>');

        layer.open({
            type: 1,
            title: '添加回款订单',
            fixed: false,
            maxmin: false,
            shadeClose: true,
            zIndex: 100000,
            area: ['70%', '70%'],
            content: builder.toString(),
            btn: ['提交', '取消'],
            success: function (layero, index) {
                var options = {
                    editable: false,
                    valueField: 'value',
                    textField: 'text',
                    width: 150,
                    method: 'get',
                    url: '/panda/rest/enum?name=com.gongsibao.entity.trade.dic.OrderStageNum',
                    onLoadSuccess: function () {  //加载完成后,设置选中第一项
                        var val = $(this).combobox("getData");
                        for (var item in val[0]) {
                            if (item == "value") {
                                $(this).combobox("select", val[0][item]);
                            }
                        }
                    }


                };
                $('#orderNo').numberbox({
                    // min:0,
                    // precision:2
                });

                $("#orderCutPrice").numberbox();
                $("#payType").combobox(options);

            },
            yes: function (index, layero) {
                //如果是一笔多单的情况校验是不是存在订单号
                var orderMultiple = $("#payForOrderCount")[0].checked;
                if (orderMultiple) {
                    alert("校验订单号是否存在");

                    me.invokeService('checkOrderId', [orderId], function (data) {
                        //


                        // if (data > 0) {
                        //
                        //     IMessageBox.toast('保存成功');
                        //
                        // } else {
                        //
                        //     IMessageBox.toast('保存失败');
                        // }
                    });

                }

                //进行绑定数据
                var orderBack = me.getOrderBack();
                var orderCutAmount=$("#orderCutPrice").numberbox('getValue');

                if(orderBack.allAmount!=parseFloat(orderCutAmount)){

                    IMessageBox.toast("回款业绩分配金额必须等于订单分配金额",2);
                    return
                }else{

                    $('#datagridpays').datagrid('appendRow', orderBack);//赋值

                    layer.closeAll();
                }


            }
        });
        me.initGrid();

        me.initOrderInfo();


    },
    initOrderInfo: function () {//处理一笔单单和一笔多单的情况

        var orderMultiple = $("#payForOrderCount")[0].checked;
        // var orderOnlne = $("#isOnlinePay")[0].checked;

        if (!orderMultiple) {//一笔单单和在线支付
            $("#orderNo").numberbox("setValue", $("#no").val()).numberbox("disable");
            $("#orderCutPrice").numberbox("setValue", $("#amount").val()).numberbox("disable");


        } else {
            $("#orderNo").numberbox("enable");
            $("#orderCutPrice").numberbox("enable");

        }


    },
    getOrderBack: function () {

        // //构建关联回款订单
        var rows = $('#order_product_grid').datagrid('getRows');//添加的行

        var orderBack = {};
        orderBack.orderId = $("#orderNo").numberbox("getValue");
        orderBack.orderCutAmount = $("#orderCutPrice").numberbox("getValue");
        orderBack.payType = $("#payType").combogrid("getValue");
        orderBack.payTypeStr = $("#payType").combogrid("getText");
         var orderBackAllAmount=0;

        var suppliernameStr = "";
        var departmentnameStr = "";
        var salesmannameStr = "";
        var amountStr = "";
        var items = [];
        $(rows).each(function (i, item) {
            suppliernameStr += '<p>' + item.suppliername + '</p>';
            departmentnameStr += '<p>' + item.departmentname + '</p>';
            salesmannameStr += '<p>' + item.salesmanname + '</p>';
            amountStr += '<p>' + item.amount + '</p>';
            orderBackAllAmount+=parseFloat(item.amount);
            var allDepPay = {};
            allDepPay.supplierId = item.supplierId;
            allDepPay.departmentId = item.departmentId;
            allDepPay.employeeId = item.salesmanId;
            allDepPay.amount = parseFloat( item.amount);
            items.push(allDepPay);
        });

        orderBack.supperName = suppliernameStr;
        orderBack.depName = departmentnameStr;
        orderBack.cutMan = salesmannameStr;
        orderBack.cutAmountStr = amountStr;
        orderBack.items = items;//循环保存实体

        orderBack.allAmount=orderBackAllAmount;//总金额保存的时候跟订单金额进行比较

        return orderBack;
    },
    choiceNameFormatter: function (value, row, index) {

        var items = row.items;
        if (items.length == 1) {

            return items[0].serviceName;
        } else {

            var str = '';
            $(items).each(function (i, item) {

                str += '<p>' + item.serviceName + '</p>';
            });
            return str;
        }
    },
    bankBooksChange: function (newValue, oldValue) {//账套联动


        //改变部门的查询条件
        $('#pays_u8Bank_name').combogrid('clear');
        var grid = $('#pays_u8Bank_name').combogrid('grid');
        var options = $(grid).datagrid('options');
        var filter = ' set_of_books_id ____ ----' + newValue + '----';
        options.url = '\/panda\/rest\/reference?code=SupplierU8Bank&filter=' + filter;
        $(grid).datagrid(options);

    },
    isOnlineChange: function (checked) {
        var me = this;
        // var isInit = $("#isOnlinePay").attr("disabled");
        //是否是线上支付
        var stateStr = "enable";
        if (checked && isInit == 1) {

            stateStr = "disable";
            //处理业务获取订单支付payId  未创建业绩金额
            var orderId = $("#no").val();
            $("#payForOrderCount").switchbutton('reset');
            me.invokeService('getOnlinePayInfoBySoderOId', [orderId], function (data) {
                if (data > 0) {

                    payId = data;

                } else {
                    $('#isOnlinePay').switchbutton('clear');
                    $('#isOnlinePay').switchbutton('disable');


                    IMessageBox.toast("不存在支付记录",2);

                }
            });


        }

        $("#pays_u8Bank_setOfBooks_name").combogrid(stateStr);
        $("#pays_u8Bank_name").combogrid(stateStr);
        $("#offlinePayerName").attr("disabled", checked);
        $("#offlineBankNo").attr("disabled", checked);
        $("#payForOrderCount").switchbutton(stateStr);
        $("#amount").numberbox(stateStr);

        $("#files").attr("disabled", checked);
        $("#offlineRemark").attr("disabled", checked);

        if (!checked && isInit == 0) {
            isInit = 1;//代表初始化完成
        }


    }


});


function getSupplierOption() {
    var supplierOption = {
        columns: [[{
            field: 'name',
            title: '名称',
            width: 100
        }]],
        url: '\/panda\/rest\/reference?code=CRM_Supplier&filter=',
        idField: 'id',
        textField: 'name',
        width: 300,
        fitColumns: true,
        panelWidth: 450,
        panelHeight: 310,
        pagination: true,
        pageSize: 10,
        mode: 'remote',
        multiple: false,
        onChange: function (newValue, oldValue) {
            //改变部门的查询条件
            $('#cut_department_name').combogrid('clear');
            var grid = $('#cut_department_name').combogrid('grid');
            var options = $(grid).datagrid('options');
            var filter = ' supplier_id ____ ----' + newValue + '----';
            options.url = '\/panda\/rest\/reference?code=CRM_Supplier_Depart&filter=' + filter;
            $(grid).datagrid(options);
            //改变业务员的查询条件
            $('#cut_employee_name').combogrid('clear');
            var grid = $('#cut_employee_name').combogrid('grid');
            var options = $(grid).datagrid('options');
            //var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE supplier_id ____ ----'+ newValue + '----)';
            var filter = ' supplier_id ____ ----' + newValue + '----';
            options.url = '\/panda\/rest\/reference?code=Salesman&filter=' + filter;
            $(grid).datagrid(options);

        }
    };

    return supplierOption;
}

function getDepartmentOption() {
    var departmentOption = {
        columns: [[{
            field: 'name',
            title: '名称',
            width: 100
        }]],
        url: '\/panda\/rest\/reference?code=CRM_Supplier_Depart&filter=',
        idField: 'id',
        textField: 'name',
        width: 300,
        fitColumns: true,
        panelWidth: 450,
        panelHeight: 310,
        pagination: true,
        pageSize: 10,
        mode: 'remote',
        multiple: false,
        onChange: function (newValue, oldValue) {
            //改变业务员的查询条件
            $('#cut_employee_name').combogrid('clear');
            var grid = $('#cut_employee_name').combogrid('grid');
            var options = $(grid).datagrid('options');
            //var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE department_id ____ ----'+ newValue + '----)';
            var filter = ' department_id ____ ----' + newValue + '----';
            options.url = '\/panda\/rest\/reference?code=Salesman&filter=' + filter;
            $(grid).datagrid(options);
        }
    };
    return departmentOption;
}

function getEmployeeOption() {
    var employeeOption = {
        columns: [[{
            field: 'employee_name',
            title: '名称',
            width: 100
        }]],
        rowStyler: function (index, row) {
            // if (row.receiving === false) {
            //     return 'color:red;';
            // }
        },
        url: '\/panda\/rest\/reference?code=Salesman&filter=',
        idField: 'employeeId',
        textField: 'employee_name',
        width: 300,
        fitColumns: true,
        panelWidth: 450,
        panelHeight: 310,
        pagination: true,
        pageSize: 10,
        mode: 'remote',
        multiple: false,
        onChange: function (newValue, oldValue) {
            /*if(oldValue!="" && newValue != oldValue){
             }*/
        }
    };

    return employeeOption;
}


/*进行校验*/
function  checkFormVal () {
    var orderOnlne = $("#isOnlinePay")[0].checked;

    //校验值必填项和金额比对
    if (!orderOnlne) {//非在线的需要校验

        var val1 = $("#pays_u8Bank_setOfBooks_name").combogrid('getValue');
        if (System.isnull(val1)) {
            // IMessageBox.info("付款账套必选");
            IMessageBox.toast("付款账套必选",2);
            return 1;

        }
        var val2 = $("#pays_u8Bank_name").combogrid('getValue');
        if (System.isnull(val2)) {
            // IMessageBox.info("付款方式必选")
            IMessageBox.toast("付款方式必选",2);
            return 1;
        }
        var val3 = $("#offlinePayerName").val();
        if (System.isnull(val3)) {
            //IMessageBox.info("付款账号名称必填")
            IMessageBox.toast("付款账号名称必填",2);
            return 1;
        }
        var val4 = $("#offlineBankNo").val();
        if (System.isnull(val4)) {
            //IMessageBox.info("付款账号必填")
            IMessageBox.toast("付款账号必填",2);
            return 1;
        }
        var val5 = $("#amount").numberbox('getValue');
        var val55 = $("#payablePrice").numberbox('getValue');
        
        if (parseInt(val5) <= 0) {

            IMessageBox.info("付款金额必填")
            return 1;
        }

        if (parseInt(val5) > parseInt(val55)) {

            IMessageBox.info("付款金额不能大于订单金额")
            return 1;
        }
        // var val7 = $("#files").attr("disabled", checked);

        return 0;
    }
    
}


/*上传图片返回路径*/
function uploadImg(btnid) {
    var buttonId = btnid;
    var filtersStr = $('#' + this.propertyName).attr('filters');
    var filtersObj = null;
    if (!System.isnull(filtersStr)) {

        filtersStr = filtersStr.replaceAll('\'', '"');
        filtersObj = JSON.parse(filtersStr);
    }

    var me = this;
    var options = {
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: buttonId,
        // container: document.getElementById('container'),
        flash_swf_url: '/package/plupload/js/Moxie.swf',
        silverlight_xap_url: '/package/plupload/js/Moxie.xap',
        url: 'http://oss.aliyuncs.com',
        multi_selection: false,
        init: {
            PostInit: function () {
//			            me.setUploadParam(uploader);
            },

            FilesAdded: function (up, files) {

                me.setUploadParam(up);
                uploader.start();
            },

            UploadProgress: function (up, file) {
                IMessageBox.loading.show();
            },

            FileUploaded: function (up, file, info) {


                IMessageBox.loading.hide();
                if (info.status == 200) {
                    var path = up.getOption().url + '/' + up.getOption().multipart_params.key;
                    // $("#" + me.propertyName).filebox("setText", path);
                    // me.preview(path,file);

                    return path;
                }
                else {
                    IMessageBox.info(info.response);
                }
            },

            Error: function (up, err) {

                IMessageBox.error(err.response);
            }
        }
    };
    if (filtersObj) {

        options.filters = filtersObj;
    }

    var uploader = new plupload.Uploader(options);
    uploader.init();


}





