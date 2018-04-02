<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>
	<link href='/package/font-awesome/css/font-awesome.min.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/material/easyui.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/easyui.extend.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/color.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/icon.css' rel='stylesheet' type='text/css' />
	<link href='/panda-bizbase/home.css' rel='stylesheet' type='text/css' />
</head>
<body>
	<div class="page-content page-index">
		<jsp:include page="/panda-bizbase/account-info.jsp"></jsp:include>
		<jsp:include page="portal-statistic.jsp"></jsp:include>
	</div>
</body>
<script src='/package/easyui/jquery.min.js'></script>
<script src='/package/layer/layer.js'></script>
<script src='/package/easyui/jquery.easyui.min.js'></script>
<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
<script src='/package/easyui/jquery.easyui.extend.js'></script>

<script src='/panda-res/js/system.js'></script>
<script src='/panda-res/js/panda.core.js'></script>
<script src='/gsb/supplier/home/js/portal-statistic.js'></script>

	<script>
		//销售简报
		var brief = new com.gongsibao.crm.web.home.Briefing();
		//跟进统计
		var foolow = new com.gongsibao.crm.web.home.Foolow();
		//预估业绩
		var forecast = new com.gongsibao.crm.web.home.Forecast();
		//漏斗统计
		var funnel = new com.gongsibao.crm.web.home.Funnel();
		$(function() {
			
 			//这几个数据可以用一个DTO一次性返回，这样调用次数太多
			brief.briefingCountPars2('getNewTasksCount',2,1,function(count){
				$("#add_count").text(count);
			});
			brief.briefingCountPars2('getUnStartTasksCount',2,1,function(count){
				$("#un_start_count").text(count);
			});
			brief.briefingCountPars0('getUnfoolowTasksCount',function(count){
				$("#stay_foolow_count").text(count);
			});
			brief.briefingCountPars0('getTimeOutTasksCount',function(count){
				$("#timeout_count").text(count);
			});
			
			
			brief.briefingCountPars0('getExceptUntreatedTasksCount',function(count){
				$("#abnormal_count").text(count);
			});
			brief.briefingCountPars0('currentSalesMan',function(entity){
				if(entity.isLeader){
					brief.briefingCountPars2('getHighSeasCount',2,-1,function(count){
						$("#public_count").text(count);
					});
				}
			});
			
			foolow.foolowCountPars0('getFoolowSatatistic');
			
			forecast.forecastAmountPars1('getForecastAmount',1);
			forecast.forecastAmountPars1('getForecastAmount',2);
			forecast.forecastAmountPars1('getForecastAmount',3);
			
			funnel.funnelXSCountPars0('getXSCount'); 
			funnel.funnelCodeCountPars0('getCodeTaskCount'); 
		});
	</script>
</html>
