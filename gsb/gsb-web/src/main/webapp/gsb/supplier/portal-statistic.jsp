<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="com.gongsibao.crm.web.home.PortalStatistic"%>
<%@ page import="com.gongsibao.entity.crm.home.HomeSupplierEntity"%>

<%
	HomeSupplierEntity homeEntity = PortalStatistic.getStatisticDate();
%>
<div>
		<div class="row" style="height:150px;">
        	<div class="cell cell-12">
	        	<div class="easyui-panel" title="销售简报" data-options="fit:true,border:false">
	        		<p>
	        			<span>新增任务数：<%=homeEntity.getNewTasksCount()%>个</span>&nbsp;&nbsp;&nbsp;
	        			<span class="paddingLeft15">未启动任务数：<%=homeEntity.getUnStartTasksCount()%>个</span>
	        			<span class="paddingLeft15">待跟进任务数：<%=homeEntity.getUnfoolowTasksCount()%>个</span>
	        			<span class="paddingLeft15">超时任务数：<%=homeEntity.getTimeOutTasksCount()%>个</span>
	        		</p>
	        		<p>
	        			<span>异常待跟进任务数：<%=homeEntity.getExceptUntreatedTasksCount()%>个</span>&nbsp;&nbsp;&nbsp;
	        			<span class="paddingLeft15">公海：<%=homeEntity.getHighSeasCount()%>个</span>&nbsp;&nbsp;&nbsp;
	        		</p>
			    </div>
        	</div>	
        </div>
        <div class="row" style="height:200px;">
        	<div class="cell cell-6">
	        	<div class="easyui-panel" title="跟进统计（今日）" style="padding:10 20px;" data-options="fit:true,border:false">
			    	<p>
	        			<span>跟进任务数：<%=homeEntity.getFoolowTasksCount()%>个</span>
	        		</p>
	        		<p>
	        			<span>质量下降任务数：<%=homeEntity.getQualityDeclinetaskCount()%>个</span>
	        		</p>
	        		<p>
	        			<span>质量上升任务数：<%=homeEntity.getQualityRisetaskCount()%>个</span>
	        		</p>
			    </div>
        	</div>
        	<div class="cell cell-6">
	        	<div class="easyui-panel" title="预估业绩" data-options="fit:true,border:false">
	        		<p>
	        			<span>今日预估签单金额：<%=homeEntity.getDaySigningAmount()%>元</span>
	        			<span style="padding-left: 100px;">今日预估回款金额：<%=homeEntity.getDayReturnedAmount()%>元</span>
	        		</p>
	        		<p>
	        			<span>本周预估签单金额：<%=homeEntity.getWeekSigningAmount()%>元</span>
	        			<span style="padding-left: 100px;">本周预估回款金额：<%=homeEntity.getWeekReturnedAmount()%>元</span>
	        		</p>
	        		<p>
	        			<span>本月预估签单金额：<%=homeEntity.getMonthSigningAmount()%>元</span>
	        			<span style="padding-left: 100px;">本月预估回款金额：<%=homeEntity.getMonthReturnedAmount()%>元</span>
	        		</p>
			    </div>
        	</div>
        </div>
		<div class="row" style="height:200px;">
        	<div class="cell cell-12">
	        	<div class="easyui-panel" title="漏斗统计" data-options="fit:true,border:false">
	        		<p>
	        			<span>全部任务：200个</span>
	        		</p>
	        		<p>
	        			<span>X类：<%=homeEntity.getXCount()%>个</span>
	        			<span style="padding-left: 50px;">S类：<%=homeEntity.getSCount()%>个</span>
	        		</p>
	        		<p>
	        			<span>A类：&nbsp;A0:<%=homeEntity.getA0Count()%>个</span>
	        			<span class="paddingLeft15">A1:<%=homeEntity.getA1Count()%>个</span>
	        			<span class="paddingLeft15">A2:<%=homeEntity.getA2Count()%>个</span>
	        			<span class="paddingLeft15">A3:<%=homeEntity.getA3Count()%>个</span>
	        			<span class="paddingLeft15">A4:<%=homeEntity.getA4Count()%>个</span>
	        			<span class="paddingLeft15">B类：&nbsp;B1:<%=homeEntity.getB1Count()%>个</span>
	        			<span class="paddingLeft15">B2:<%=homeEntity.getB2Count()%>个</span>
	        		</p>
	        		<p>
	        			<span>C类：&nbsp;C1:<%=homeEntity.getC1Count()%>个</span>
	        			<span class="paddingLeft15">C2:<%=homeEntity.getC2Count()%>个</span>
	        			<span class="paddingLeft15">C3:<%=homeEntity.getC3Count()%>个</span>
	        			<span class="paddingLeft15">C4:<%=homeEntity.getC4Count()%>个</span>
	        			<span style="padding-left: 67px;">D类：&nbsp;D1:<%=homeEntity.getD1Count()%>个</span>
	        			<span class="paddingLeft15">D2:<%=homeEntity.getD2Count()%>个</span>
	        		</p>
			    </div>
        	</div>
        </div>
</div>
