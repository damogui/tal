<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>报销申请</title>
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
		<div  style="height:290px">
		   <div class="easyui-panel" title="基本信息"  >
			  <table cellpadding="3" cellspacing="0" class="form-panel">
                    <tr>
                        <td width="20%" class="label_td">
                            <label >报销类型：</label>
						</td>
						 <td  width="20%"  class="control_td">
							<select id="billType" class="easyui-combobox"  style="width:200px;" >
							    <option value="1" selected="selected">日常报销</option>
							    <option value="2">差旅报销</option>
							    <option value="3" >招待费报销</option>
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
							<label for="soOrder.customer.email">借款方式：</label>
						</td>
						<td width="15%"  class="control_td">
							<select id="payType" class="easyui-combobox"  style="width:200px;" >
							    <option value="1" selected="selected">现金</option>
							    <option value="2">支票</option>
							    <option value="3">转账</option>
							</select>
						</td>
					</tr>
					
					<tr>
                        <td width="20%" class="label_td">
                            <label >费用归属部门：</label>
						</td>
						 <td  width="20%"  class="control_td">
							<select id="cc" class="easyui-combobox"  style="width:200px;">
							    <option value="1">技术部</option>
							    <option value="2">财务部</option>
							    <option value="3">业务部</option>
							</select>
						</td>
						<td  width="15%"  class="label_td">
							<label>单据数量：</label>
						</td>
						<td  width="15%"  class="control_td">
							<input class="easyui-textbox" style="width:100%;height:32px">
						</td>
						<td  width="15%"  class="label_td">
							<label for="soOrder.customer.email">报销人联系电话：</label>
						</td>
						<td width="15%"  class="control_td">
							<input class="easyui-textbox" style="width:100%;height:32px">
						</td>
					</tr>
                    <tr id="pay-tr" >
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
					<tr id="hospitality-tr" >
                        <td width="120px" class="label_td">
                            <label for="soOrder.customer.realName">单位名称：</label>
						</td>
						 <td class="control_td">
							<input class="easyui-textbox" style="width:100%;height:32px">
						</td>
						<td width="120px" class="label_td">
							<label for="soOrder.accountMobile">招待地点：</label>
						</td>
						<td class="control_td">
							<input class="easyui-textbox" style="width:100%;height:32px">
						</td>
						<td width="120px" class="label_td">
							<label for="soOrder.customer.email">客户姓名：</label>
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
		
		</div>
		<div  style="height:70%">
		    
		    <div id="trip_panel" >
			    <div class="easyui-panel" title="行程明细"   >
			    	<table class="gridtable"  id="trip_detail_table">
						<thead>
							<tr>
								<th style="width: 200px;" >开始日期</th>
								<th style="width: 200px;" >开始日期</th>
								<th style="width: 200px;" >出发地</th>
								<th style="width: 200px;" >目的地</th>
								<th style="width: 200px;" >说明</th>
								<th style="width: 200px;" >操作</th>
							</tr>
						</thead>
						<tbody>
						  <tr>
							<td style="text-align: center;" ><input class="easyui-datebox" style="width:150px;;height:32px"/> </td>
							<td style="text-align: center;" ><input class="easyui-datebox" style="width:150px;;height:32px"/>  </td>
							<td style="text-align: center;" ><input class="easyui-textbox" style="width:150px;;height:32px"></td>
							<td style="text-align: center;"><input class="easyui-textbox" style="width:150px;height:32px"></td>
							<td style="text-align: center;"><input class="easyui-textbox" style="width:200px;height:32px"></td>
							<td style="text-align: center;">
								<a href="javascript:expenseBillFormCtrl.addTripRows();" class="easyui-linkbutton" style="margin: 10px;"  data-options="iconCls:'icon-add'">添加</a>
							</td>
						  </tr>
						</tbody>
					</table>
			    </div>
		    
		    </div>
		    <div id="subsidy-panel">
			    <div class="easyui-panel" title="补助明细"   >
			   		<table class="gridtable"  id="subsidy_detail_table">
						<thead>
							<tr>
								<th style="width: 200px;" >补助类型</th>
								<th style="width: 200px;" >天数</th>
								<th style="width: 200px;" >补助标准</th>
								<th style="width: 200px;" >补贴金额</th>
								<th style="width: 200px;" >说明</th>
								<th style="width: 200px;" >操作</th>
							</tr>
						</thead>
						<tbody>
						  <tr>
							<td style="text-align: center;" ><input class="easyui-textbox" style="width:150px;;height:32px"></td>
							<td style="text-align: center;" ><input class="easyui-textbox" style="width:150px;;height:32px"></td>
							<td style="text-align: center;" ><input class="easyui-textbox" style="width:150px;;height:32px"></td>
							<td style="text-align: center;" ><input class="easyui-textbox" style="width:150px;;height:32px"></td>
							<td style="text-align: center;" ><input class="easyui-textbox" style="width:200px;;height:32px"></td>
							<td style="text-align: center;">
								<a href="javascript:expenseBillFormCtrl.addSubsidyRows();" class="easyui-linkbutton" style="margin: 10px;"  data-options="iconCls:'icon-add'">添加</a>
							</td>
						  </tr>
						</tbody>
					</table>	
			   
			    </div>
		    </div>
		    
			<div class="easyui-panel" title="费用明细"  >

				<table class="gridtable"  id="cost_detail_table" style="width: 100%" >
					<thead>
						<tr>
							<th style="width: 200px;" >费用类型</th>
							<th style="width: 200px;" >报销金额</th>
							<th style="width: 300px;" >说明</th>
							<th style="width: 200px;" >操作</th>
						</tr>
					</thead>
					<tbody>
					  <tr>
						<td style="text-align: center;" >
							<select id="cc" class="easyui-combobox"  style="width:200px;">
		    					<option value="1">通讯费</option>
		    					<option value="2">交通费</option>
		    					<option value="3">补助</option>
	    					</select>
						</td>
						<td style="text-align: center;" ><input class="easyui-textbox" style="width:200px;;height:32px"></td>
						<td style="text-align: center;"><input class="easyui-textbox" style="width:300px;height:32px"></td>
						<td style="text-align: center;">
							<a href="javascript:expenseBillFormCtrl.addRows();" class="easyui-linkbutton" style="margin: 10px;"  data-options="iconCls:'icon-add'">添加</a>
						</td>
					  </tr>
					</tbody>
				</table>
				<div style="width: 98%;border-top: 1px solid #e4e4e4; padding-left: 10px;"  >
				  <p>合计：0 </p>
				</div>
				
			</div>
		   
		    <div class="easyui-panel" title="冲销借款"  >
		    	<table class="gridtable"  id="loan_bill_table" style="width: 100%" >
					<thead>
						<tr>
							<th style="width: 200px;" >序号</th>
							<th style="width: 200px;" >借款单</th>
							<th style="width: 300px;" >未还金额</th>
							<th style="width: 200px;" >说明</th>
						</tr>
					</thead>
					<tbody>
					  <tr>
						<td style="text-align: center;" ><input type="checkbox" value="0" >1</td>
						<td style="text-align: center;" >JK20180323001</td>
						<td style="text-align: center;">3000</td>
						<td style="text-align: center;">出差申请借款</td>
					  </tr>
					  <tr>
						<td style="text-align: center;" ><input type="checkbox" value="0" >2</td>
						<td style="text-align: center;" >JK20180323001</td>
						<td style="text-align: center;">3000</td>
						<td style="text-align: center;">出差申请借款</td>
					  </tr>
					  <tr>
						<td style="text-align: center;" ><input type="checkbox" value="0" >3</td>
						<td style="text-align: center;" >JK20180323001</td>
						<td style="text-align: center;">3000</td>
						<td style="text-align: center;">出差申请借款</td>
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

<script src='/gsb/platform/cw/js/expense-bill-form.ctrl.js'></script>
<script>
	var expenseBillFormCtrl = null;
	$(function(){
		expenseBillFormCtrl = new com.gongsibao.cw.web.ExpenseBillFormCtrl();
		expenseBillFormCtrl.init(); 
		
		$("#hospitality-tr").hide();
		$("#pay-tr").hide();
		$('#payType').combobox({
			onChange: function(newValue,oldValue){
				if(newValue == 3){
		    		$("#pay-tr").show();
		    	}else{
		    		$("#pay-tr").hide();
		    	}
			}
		});
		
		
		$("#trip_panel").hide();
		$("#subsidy-panel").hide();
		$('#billType').combobox({
			onChange: function(newValue,oldValue){
				if(newValue == 3){
		    		$("#hospitality-tr").show();
		    	}else{
		    		$("#hospitality-tr").hide();
		    	}
				if(newValue == 2){
					$("#trip_panel").show();
					$("#subsidy-panel").show();
				}else{
					$("#trip_panel").hide();
					$("#subsidy-panel").hide();
				}
				
			}
		});


	});
</script>
</html>
