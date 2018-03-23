<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>产品方案</title>
	<link href='/package/font-awesome/css/font-awesome.min.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/material/easyui.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/easyui.extend.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/color.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/icon.css' rel='stylesheet' type='text/css' />
	<link href='/panda-res/css/panda.form.css' rel='stylesheet' type='text/css' />
	<style>
		tr.stage{display: none;}
	</style>
	<script src='/package/easyui/jquery.min.js'></script>
	<script src='/package/layer/layer.js'></script>
	<script src='/package/easyui/jquery.easyui.min.js'></script>
	<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
	<script src='/package/easyui/jquery.easyui.extend.js'></script>
	<script src='/panda-res/js/system.js'></script>
	<script src='/panda-res/js/panda.core.js'></script>
	<script src='/panda-res/js/panda.js'></script>
	<script src='/gsb/platform/trade/js/order-carryover.ctrl.js'></script>
	<script type="text/javascript">
		$('inputp[type=checkbox]').click(function(){
		 	$(this).attr('checked','checked').siblings().removeAttr('checked');
		});
	</script>
</head>
    <body class="easyui-layout">
        <div data-options="region:'center',split:false,collapsible:false,closed:false" style="height:100%;" >
            <div id="tabs" class="easyui-tabs" style="height:100%;" data-options="fit:true,plain:false,tabPosition:'top',tabWidth:0,tabHeight:35">
			    <div title="产品方案">
			        <%-- <div data-options="region:'north',split:false,collapsible:false,closed:false,height:200">
						<jsp:include page="/gsb/platform/trade/include/orderInfo.jsp"></jsp:include>
					</div> --%>
					<div id="center" data-options="region:'center'">
						<div id="detail_tabs">
						    <div title="产品方案">   
						        <div style=" border:0px;padding:10px;">
						           <form id="form1" style="padding-top: 0px;">
									   <table cellpadding="3" cellspacing="0" class="form-panel">
									      <tr>
									          <td class="label_td"><label style="color:Red">*</label><label>产品名称：</label></td>
									          <td class="control_td">
									          		<input id="formOrderNo" class="easyui-validatebox nsInput"
									          			data-options="required:true"/></td>
										  </tr>
										  <tr>
										  	<td class="label_td"> <label style="color:Red">*</label> <label>产品分类：</label></td>
										  		<td>
										         <select id="productType" name="productType">
												    <option value="0">请选择...</option>
												    <option value="2018">工商服务</option>
												    <option value="2017">财税服务</option>
												    <option value="20123">媒体运营</option>
												    <option value="2016">知识产权</option>
												    <option value="20112">人事社保</option>
												    <option value="2019">公司宝典</option>
												    <option value="20110">创业活动</option>
												    <option value="2020">增值服务</option>
												    <option value="20120">海外业务</option>
												    <option value="20122">专家问诊</option>
												 </select> 
								          		</td>
										  </tr>
										  <tr>
										  	<td class="label_td"><label style="color:Red">*</label><label>分成比例：</label></td>
									          <td class="control_td">
									          		<input id="formOrderNo" class="easyui-validatebox nsInput"
									          			data-options="required:true"/>%</td>
										  </tr>
										  <tr>
										  	<td class="label_td"><label style="color:Red">*</label><label>归属事业部：</label></td>
									          <td class="control_td">
									          	<input type="checkbox" name="id1" value="1" ID="Checkbox1">通用 
									          	<input type="checkbox" name="id2" value="2" ID="Checkbox1">公司宝
									          	<input type="checkbox" name="id3" value="3" ID="Checkbox1">知产
									          	<input type="checkbox" name="id4" value="4" ID="Checkbox1">基础
									          </td>
										  </tr>
									      <tr>
									          <td class="label_td"><label style="color:Red">*</label><label>产品描述：</label></td>
									          <td colspan="5" class="control_td">
													<textarea id="carryRemark" style="width:100%;height:50px;" class="easyui-validatebox nsInput" data-options="width:150,required:true"></textarea>
									          </td>
										 </tr>
										 <tr>
										  	<td class="label_td"><label style="color:Red">*</label><label>产品购买信息：</label></td>
									          <td class="control_td">
									          	<input type="checkbox" name="id1" value="1" ID="Checkbox1">通用 
									          	<input type="checkbox" name="id2" value="2" ID="Checkbox1">公司宝
									          	<input type="checkbox" name="id3" value="3" ID="Checkbox1">知产
									          	<input type="checkbox" name="id4" value="4" ID="Checkbox1">基础
									          </td>
										  </tr>
									</table>
									</form>
								</div>
						    </div>
						</div>
					</div>
			    </div>
			</div>
    	</div>
</body>

<script>

	var productProjectCtrl = null;
	$(function(){
		var centerHeight = $('body').height() - 240;
		$('#center').height(centerHeight);
		productProjectCtrl = new com.gongsibao.product.web.ProductListPart();
		productProjectCtrl.init();
	});
</script>
</html>
