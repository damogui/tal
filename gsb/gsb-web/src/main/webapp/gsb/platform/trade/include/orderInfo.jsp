<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<style>
	.control_td{
		min-width:120px;
	}
	
	.form-panel td.label_td {
	
		line-height: 25px !important;
		font-size: 12px;
		font-weight: bold;
	}
</style>
  <table cellpadding="3" cellspacing="0" class="form-panel" style="width:1000px;margin:5px 0;">
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
          <td class="control_td" id="orderStageNum"></td>
	</tr>
      <tr>
          <td class="label_td"><label>渠道订单号：</label></td>
          <td class="control_td" id="channelOrderNo"></td>
          <td class="label_td"><label></label></td>
          <td class="control_td"></td>
          <td class="label_td"><label></label></td>
          <td class="control_td"></td>
	</tr>
      <tr>
          <td class="label_td"><label>备注：</label></td>
          <td colspan="5" class="control_td" id="remark"></td>
	</tr>
</table>
<script src='/gsb/platform/trade/js/order/order-info.ctrl.js'></script>
