<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>付款申请</title>
	<jsp:include page="/gsb/platform/trade/include/meta.jsp"></jsp:include>
	<style type="text/css">
	table.gridtable {
	    font-family: verdana,arial,sans-serif;
	    font-size:11px;
	    color:#333333;
	    border-width: 1px;
	    border-color: #e4e4e4;
	    border-collapse: collapse;
	}
	table.gridtable th {
	    border-width: 1px;
	    padding: 8px;
	    border-style: solid;
	    border-color: #e4e4e4;
	    background-color: #f2f2f2;
	}
	table.gridtable td {
	    border-width: 1px;
	    padding: 8px;
	    border-style: solid;
	    border-color: #e4e4e4;
	    background-color: #ffffff;
	}
	</style>
</head>
<body class="easyui-layout">
	<div id="center" data-options="region:'center'">
		<div  style="height:160px;">
			  <table cellpadding="3" cellspacing="0" class="form-panel">
                    <tr>
                        <td width="30%" class="label_td">
                            <label >业务类型：</label>
						</td>
						 <td  width="20%"  class="control_td">
							<select id="cc" class="easyui-combobox"  style="width:200px;">
							    <option value="1">地址费</option>
							    <option value="2">刻章费</option>
							</select>
						</td>
						<td  width="15%"  class="label_td">
							<label>付款单位：</label>
						</td>
						<td  width="15%"  class="control_td">
							<select id="cc" class="easyui-combobox"  style="width:200px;">
							    <option value="1">汉唐信通（北京）科技有限公司</option>
							    <option value="2">汉唐信通（北京）咨询股份有限公司</option>
							</select>
						</td>
						<td  width="15%"  class="label_td">
							<label for="soOrder.customer.email">发票日期：</label>
						</td>
						<td width="15%"  class="control_td">
							<input  id="dd"  type= "text" class= "easyui-datebox" > </input>  
						</td>
					</tr>
                    <tr>
                        <td width="120px" class="label_td">
                            <label for="soOrder.customer.realName">对方公司名称：</label>
						</td>
						 <td class="control_td">
							<input class="easyui-textbox" style="width:100%;height:32px">
						</td>
						<td width="120px" class="label_td">
							<label for="soOrder.accountMobile">对方公司开户行：</label>
						</td>
						<td class="control_td">
							<input class="easyui-textbox" style="width:100%;height:32px">
						</td>
						<td width="120px" class="label_td">
							<label for="soOrder.customer.email">对方公司银行账号：</label>
						</td>
						<td class="control_td">
							<input class="easyui-textbox" style="width:100%;height:32px">
						</td>
					</tr>
					<tr>
						<td width="120px" class="label_td">
                            <label >备注：</label>
						</td>
						<td colspan="5">
                            <textarea  style="width:100%;height:50px;" ></textarea>
						</td>
					</tr>					                           
			  </table>

		
		</div>
		<div  style="height:70%">
		     
			<div class="easyui-panel" title="费用明细"  >

				<table class="gridtable"  id="cost_detail_table">
					<thead>
						<tr>
							<th style="width: 200px;" >付款金额（元）</th>
							<th style="width: 200px;" >费用归属单位</th>
							<th style="width: 360px;" >说明</th>
							<th style="width: 200px;" >操作</th>
						</tr>
					</thead>
					<tbody>
					  <tr>
						<td style="text-align: center;" ><input class="easyui-textbox" style="width:200px;;height:32px"></td>
						<td style="text-align: center;" >
							<select id="cc" class="easyui-combobox"  style="width:200px;">
		    					<option value="1">技术部</option>
		    					<option value="2">财务部</option>
		    					<option value="3">市场部</option>
	    					</select>
						
						</td>
						<td style="text-align: center;"><input class="easyui-textbox" style="width:360px;height:32px"></td>
						<td style="text-align: center;">
							<a href="javascript:paymentBillFormCtrl.addRows();" class="easyui-linkbutton" style="margin: 10px;"  data-options="iconCls:'icon-add'">添加</a>
						</td>
					  </tr>
					</tbody>
				</table>
				<div style="width: 98%;border-top: 1px solid #e4e4e4; padding-left: 10px;"  >
				  <p>合计：0 </p>
				</div>
				<div style="width: 98%;border-top: 1px solid #e4e4e4;  padding-left: 10px;"  >
				  <p>附件：<a href="#" class="easyui-linkbutton">上传附件</a> </p>
				</div>
				<div style="width: 98%;border-top: 1px solid #e4e4e4;  padding-left: 10px;"  >
				  <p>抄送人： <a href="#" class="easyui-linkbutton">添加抄送人</a></p>
				</div>
			</div>
		
		
		</div>
	</div>
</body>

<script src='/gsb/platform/cw/js/payment-bill-form.ctrl.js'></script>
<script>
	var paymentBillFormCtrl = null;
	$(function(){
		paymentBillFormCtrl = new com.gongsibao.cw.web.PaymentBillFormCtrl();
		paymentBillFormCtrl.init(); 
	});
</script>
</html>
