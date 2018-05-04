<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="settle_info_content">
    <div data-options="region:'north',split:false,collapsible:false,closed:false,height:220">
        <div class="formContent">
            <table cellpadding="3" cellspacing="0" class="form-panel" style="width: 100%;">
                <tr>
                    <td class="label_td"><label>结算编号：</label></td>
                    <td class="control_td"  id="id">

                    </td>
                    <td class="label_td"><label>结算状态：</label></td>
                    <td class="control_td" id="handleStatus">

                    </td>
                    <td class="label_td"><label>总金额：</label></td>
                    <td class="control_td" id="totalAmount">

                    </td>
                </tr>
                <tr>

                    <td class="label_td"><label>成本：</label></td>
                    <td class="control_td" id="totalCost">

                    </td>
                    <td class="label_td"><label>服务费：</label></td>
                    <td class="control_td" id="totalCharge">

                    </td>
                    <td class="label_td"><label>佣金：</label></td>
                    <td class="control_td" id="commission">

                    </td>
                </tr>
                <tr>
                    <td class="label_td"><label>税率%：</label></td>
                    <td class="control_td" id="taxRate">

                    </td>
                    <td class="label_td"><label>税额：</label></td>
                    <td class="control_td"  id="tax">

                    </td>
                    <td class="label_td"><label>创建时间：</label></td>
                    <td class="control_td"  id="createTime">
                </tr>
                <tr>
                    </td>
                    <td class="label_td"><label>备注：</label></td>
                    <td colspan="3" class="control_td" id="memo">

                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div id="center" data-options="region:'center'">
        <div id="detail_tabs" style="height:100%;">
            <div title="明细订单">
                <table id="settle_order_prod_grid"></table>
            </div>
            <div title="操作日志">
                <table id="settle_handle_log"></table>
            </div>

        </div>
    </div>
</div>

<script src='/package/easyui/jquery.min.js'></script>
<script src='/package/layer/layer.js'></script>
<script src='/package/easyui/jquery.easyui.min.js'></script>
<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
<script src='/package/easyui/jquery.easyui.extend.js'></script>
<script src='/panda-res/js/system.js'></script>
<script src='/panda-res/js/panda.core.js'></script>
<script src='/panda-res/js/panda.js'></script>
<script src='/gsb/supplier/settle/js/settle.detail.part.js'></script>

<script>

    var settleCtrl = null;
    $(function(){
        settleCtrl = new com.gongsibao.trade.web.settle.SettleFormPart();
        settleCtrl.init();
    });
</script>