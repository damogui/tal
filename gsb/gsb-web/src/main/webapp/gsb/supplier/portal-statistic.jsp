<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="org.netsharp.authorization.UserPermissionManager"%>
<%@ page import="org.netsharp.authorization.UserPermission"%>
<%@ page import="org.netsharp.organization.entity.Employee"%>
<%@ page import="org.netsharp.panda.core.HttpContext"%>
<%@ page import="org.netsharp.panda.core.comunication.ServletRequest"%>
<%@ page import="org.netsharp.panda.core.comunication.ServletResponse"%>
<%@ page import="org.netsharp.panda.core.comunication.HtmlWriter"%>
<%
	HttpContext ctx = new HttpContext();
	{
		ctx.setRequest(new ServletRequest(request, response));
		ctx.setResponse(new ServletResponse(response));
		ctx.setContext(request.getServletContext());
		ctx.setWriter(new HtmlWriter(response.getWriter()));
	}
	HttpContext.setCurrent(ctx);
	UserPermission permission = UserPermissionManager.getUserPermission();
	Employee employee = permission.getEmployee();
%>
<style>
	.cell .title{
		color:#76838f;
		text-align: center;
	}
	.cell .num{
		    text-align: center;
	}	
	.cell .num span{
		color:#000;
		font-size:36px;
	}
</style>
<div>
		 <div class="row" style="height:150px;">
        	<div class="cell cell-12">
	        	<div id="sbyctj" class="easyui-panel" title="商标异常统计"  style="padding:0px 10px !important;" data-options="tools:'#refreshtool'" >
				<div id="refreshtool">
					<a href="#" class="icon-reload" onclick="getAbnorvalNotice()"></a>
				</div> 
				<div class="row" onclick="window.open('/panda/igirl/notice/follow/list')">
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>部分驳回</span></div>
	        				<div class="num" id="bfbh" name="abnormal"><span id="new_count">0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>全部驳回</span></div>
	        				<div class="num" id="qbbh" name="abnormal"><span id="un_start_count">0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>不予受理</span></div>
	        				<div class="num" id="bysl" name="abnormal"><span id="stay_foolow_count">0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>补证通知</span></div>
	        				<div class="num" id="bztz" name="abnormal"><span id="timeout_count">0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>裁定通知</span></div>
	        				<div class="num" id="cdtz" name="abnormal"><span id="abnormal_count" >0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>不予核准</span></div>
	        				<div class="num" id="byhz" name="abnormal"><span id="public_count">0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>同日申请协商</span></div>
	        				<div class="num" id="trsqxs" name="abnormal"><span id="abnormal_count" ">0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>同日申请补送证据</span></div>
	        				<div class="num" id="trsqbszj" name="abnormal"><span id="public_count" ">0</span></div>
	        			</div>
	        		</div>
			   </div> 
        	</div>	
        </div>
		<div class="row" style="height:150px;">
        	<div class="cell cell-12">
	        	<div id="briefing" class="easyui-panel" title="销售简报" style="padding:0px 10px !important;" data-options="fit:true,border:false">
	        	
	        		<div class="row">
	        			<div class="cell cell-2">
	        				<div class="title"><span>新增商机</span></div>
	        				<div class="num"><span id="new_count">0</span></div>
	        			</div>
	        			<div class="cell cell-2">
	        				<div class="title"><span>未启动</span></div>
	        				<div class="num"><span id="un_start_count">0</span></div>
	        			</div>
	        			<div class="cell cell-2">
	        				<div class="title"><span>待跟进</span></div>
	        				<div class="num"><span id="stay_foolow_count">0</span></div>
	        			</div>
	        			<div class="cell cell-2">
	        				<div class="title"><span>超时未跟进</span></div>
	        				<div class="num"><span id="timeout_count">0</span></div>
	        			</div>
	        			<div class="cell cell-2">
	        				<div class="title"><span>异常未处理</span></div>
	        				<div class="num"><span id="abnormal_count" style="color:red;">0</span></div>
	        			</div>
	        			<div class="cell cell-2">
	        				<div class="title"><span>公海</span></div>
	        				<div class="num"><span id="public_count">0</span></div>
	        			</div>
	        		</div>
			    </div>
        	</div>	
        </div>
       
        <div class="row" style="height:200px;">
        	<div class="cell cell-6">
	        	<div id="foolow" class="easyui-panel" title="跟进统计（今日）" style="padding:10 20px;" data-options="fit:true,border:false">
			    </div>
        	</div>
        	<div class="cell cell-6">
	        	<div id="forecast" class="easyui-panel" title="预估业绩" data-options="fit:true,border:false">
	        		
			    </div>
        	</div>
        </div>
		<div class="row" style="height:200px;">
        	<div class="cell cell-12">
	        	<div class="easyui-panel" title="漏斗统计" data-options="fit:true,border:false">
	        		<p>
	        			<span id="allTasks">全部商机：0个</span>
	        		</p>
	        		<p>
	        			<span id="xClass">X类：0个</span>
	        			<span id="sClass" style="padding-left: 50px;">S类：0个</span>
	        		</p>
	        		<p>
	        			<span>A类：&nbsp;</span>
	        			<span id ="A0">A0:0个</span>
	        			<span id ="A1" class="padding-15">A1:0个</span>
	        			<span id ="A2" class="padding-15">A2:0个</span>
	        			<span id ="A3" class="padding-15">A3:0个</span>
	        			<span id ="A4" class="padding-15">A4:0个</span>
	        			<span class="padding-15">B类：&nbsp;</span>
	        			<span id ="B1">B1:0个</span>
	        			<span id ="B2" class="padding-15">B2:0个</span>
	        		</p>
	        		<p>
	        			<span>C类：&nbsp;</span>
	        			<span id ="C1">C1:0个</span>
	        			<span id ="C2" class="padding-15">C2:0个</span>
	        			<span id ="C3" class="padding-15">C3:0个</span>
	        			<span id ="C4" class="padding-15">C4:0个</span>
	        			<span style="padding-left: 67px;">D类：&nbsp;</span>
	        			<span id ="D1">D1:0个</span>
	        			<span id ="D2" class="padding-15">D2:0个</span>
	        		</p>
			    </div>
			    
			    <div class="easyui-panel" title="漏斗统计" data-options="fit:true,border:false">
	        		<p>
	        			<span id="allTasks">全部商机：0个</span>
	        		</p>
	        		<p>
	        			<span id="xClass">X类：0个</span>
	        			<span id="sClass" style="padding-left: 50px;">S类：0个</span>
	        		</p>
	        		<p>
	        			<span>A类：&nbsp;</span>
	        			<span id ="A0">A0:0个</span>
	        			<span id ="A1" class="padding-15">A1:0个</span>
	        			<span id ="A2" class="padding-15">A2:0个</span>
	        			<span id ="A3" class="padding-15">A3:0个</span>
	        			<span id ="A4" class="padding-15">A4:0个</span>
	        			<span class="padding-15">B类：&nbsp;</span>
	        			<span id ="B1">B1:0个</span>
	        			<span id ="B2" class="padding-15">B2:0个</span>
	        		</p>
	        		<p>
	        			<span>C类：&nbsp;</span>
	        			<span id ="C1">C1:0个</span>
	        			<span id ="C2" class="padding-15">C2:0个</span>
	        			<span id ="C3" class="padding-15">C3:0个</span>
	        			<span id ="C4" class="padding-15">C4:0个</span>
	        			<span style="padding-left: 67px;">D类：&nbsp;</span>
	        			<span id ="D1">D1:0个</span>
	        			<span id ="D2" class="padding-15">D2:0个</span>
	        		</p>
			    </div>
        	</div>
        </div>
</div>
<script src='/gsb/supplier/home/js/portal-statistic.js'></script>

	<script>
		//销售简报
		var brief = new com.gongsibao.crm.web.home.BriefingCtrl();
		//跟进统计
		var foolow = new com.gongsibao.crm.web.home.FoolowCtrl();
		//预估业绩
		var forecast = new com.gongsibao.crm.web.home.ForecastCtrl();
		//漏斗统计
		var funnel = new com.gongsibao.crm.web.home.FunnelCtrl();
		$(function() {
			
 			//这几个数据可以用一个DTO一次性返回，这样调用次数太多
			brief.briefingCountPars2('getNewTasksCount',2,1,function(count){
				$("#new_count").text(count);
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
			
			
			getAbnorvalNotice();
			//setInterval("getAbnorvalNotice()",10000);
		});
		
		function getAbnorvalNotice(){
			var userId=<%=employee.getId()%>;
			var siteCtl=new org.netsharp.core.JServiceLocator();
			siteCtl.invoke("com.gongsibao.igirl.tm.web.TradeMarkCasePart","getAbnormalNotice",[userId],function(d){
				d=d.data;
				var len = d.length;
				for(var i=0;i<len;i++){
					switch(d[i].tmState)
					{
					case "6":
						changeAbnormalCount("bfbh",d[i].count);
						break;
					case "7":
						changeAbnormalCount("qbbh",d[i].count);
						break;
					case "13":
						changeAbnormalCount("bysl",d[i].count);
						break;
					case "15":
						changeAbnormalCount("bztz",d[i].count);
						break;
					case "17":
						changeAbnormalCount("cdtz",d[i].count);
						break;
					case "18":
						changeAbnormalCount("byhz",d[i].count);
						break;
					case "20":
						changeAbnormalCount("trsqxs",d[i].count);
						break;
					case "21":
						changeAbnormalCount("trsqbszj",d[i].count);
						break;
					default:
						break;
					}
				}
			})
		};
		
		function changeAbnormalCount(id,count){
			id="#"+id;
			$(id).empty();
			if(count>0){
				$(id).append("<span style='color:red;'>"+count+"</span>");
			}else{
				$(id).append("<span>"+count+"</span>");
			}
			
		};
		
	</script>