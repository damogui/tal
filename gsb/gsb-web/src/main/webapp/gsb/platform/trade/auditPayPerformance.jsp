<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>创建回款业绩审核</title>
    <link href='/package/font-awesome/css/font-awesome.min.css' rel='stylesheet' type='text/css' />
    <link href='/package/easyui/themes/material/easyui.css' rel='stylesheet' type='text/css' />
    <link href='/package/easyui/themes/easyui.extend.css' rel='stylesheet' type='text/css' />
    <link href='/package/easyui/themes/color.css' rel='stylesheet' type='text/css' />
    <link href='/package/easyui/themes/icon.css' rel='stylesheet' type='text/css' />
    <link href='/panda-res/css/panda.form.css' rel='stylesheet' type='text/css' />
    <style>
        .control_td{min-width:120px;}
        .form-panel td.label_td {

            width:170px;
            line-height: 25px !important;
            font-size: 12px;
            font-weight: bold;
        }
    </style>
    <script src='/package/easyui/jquery.min.js'></script>
    <script src='/package/layer/layer.js'></script>
    <script src='/package/easyui/jquery.easyui.min.js'></script>
    <script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
    <script src='/package/easyui/jquery.easyui.extend.js'></script>
    <script src='/package/qiniu/plupload.full.min.js'></script>
    <script src='/panda-res/js/system.js'></script>
    <script src='/panda-res/js/panda.core.js'></script>
    <script src='/panda-res/js/panda.js'></script>
    <script src='/panda-res/js/panda.controls.js'></script>
    <script src='/gsb/platform/trade/js/audit-base.ctrl.js'></script>
    <script src='/gsb/platform/trade/js/audit-pay-performance.ctrl.js'></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',split:false,collapsible:false,closed:false,height:220">
    <div class="formContent">
        <table cellpadding="3" cellspacing="0" class="form-panel">
            <tr>
                <td class="label_td"><label>订单编号：</label></td>
                <td class="control_td" id="no"></td>
                <td class="label_td"><label>订单金额：</label></td>
                <td class="control_td" id="payablePrice"></td>
                <td class="label_td"><label>已付金额：</label></td>
                <td class="control_td" id="paidPrice"></td>
            </tr>
            <tr>
                <td class="label_td"><label>客户名称：</label></td>
                <td class="control_td" id="accountName"></td>
                <td class="label_td"><label>手机：</label></td>
                <td class="control_td" id="accountMobile"></td>
                <td class="label_td"><label>下单时间：</label></td>
                <td class="control_td" id="addTime"></td>
            </tr>
            <tr>
                <td class="label_td"><label>订单来源：</label></td>
                <td class="control_td" id="platformSource"></td>
                <td class="label_td"><label>付款状态：</label></td>
                <td class="control_td" id="payStatus"></td>
                <td class="label_td"><label>分期次数：</label></td>
                <td class="control_td" id="installmentCount"></td>
            </tr>
            <tr>
                <td class="label_td"><label>渠道订单号：</label></td>
                <td class="control_td" id="channelOrderNo"></td>
                <td class="label_td"><label>未划分回款业绩额：</label></td>
                <td class="control_td" id="unAllotPayPrice"></td>
                <%--<td class="control_td" colspan="2" style="color:red;">注：本次需把未划分回款业绩额全部分配</td>--%>
            </tr>
            <tr>
                <td class="label_td"><label>备注：</label></td>
                <td colspan="5" class="control_td" id="remark"></td>
            </tr>
        </table>
    </div>
</div>

<div id="center" data-options="region:'center'">
    <div id="detail_tabs" style="height:100%;">

        <div title="业绩分配">
            <table id="order_amount_cut"></table>
        </div>
        <div title="审批进度">
            <table id="audit_progress_grid"></table>
        </div>
    </div>


</div>

</body>


<script>

    var payPerformanceCtrl = null;
    $(function(){

        payPerformanceCtrl = new com.gongsibao.trade.web.AuditPayPerformanceCtrl ();
        payPerformanceCtrl.init();
    });
</script>
</html>
