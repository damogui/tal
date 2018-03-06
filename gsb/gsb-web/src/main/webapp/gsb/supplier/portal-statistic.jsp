<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="com.gongsibao.crm.web.home.PortalStatistic"%>
<%@ page import="com.gongsibao.entity.crm.home.HomeSupplierEntity"%>

<%
	/* HomeSupplierEntity homeEntity = PortalStatistic.getStatisticDate(); */
%>
<div>
		<div class="row" style="height:150px;">
        	<div class="cell cell-12">
	        	<div class="easyui-panel" title="销售简报" data-options="fit:true,border:false">
	        		<p>
	        			<span>新增任务数：0个</span>&nbsp;&nbsp;&nbsp;
	        			<span>未启动任务数：0个</span>&nbsp;&nbsp;&nbsp;
	        			<span>待跟进任务数：0个</span>&nbsp;&nbsp;&nbsp;
	        			<span>超时任务数：0个</span>&nbsp;&nbsp;&nbsp;
	        		</p>
	        		<p>
	        			<span>异常待跟进任务数：0个</span>&nbsp;&nbsp;&nbsp;
	        			<span>公海：0个</span>&nbsp;&nbsp;&nbsp;
	        		</p>
			    </div>
        	</div>	
        </div>
        <div class="row" style="height:200px;">
        	<div class="cell cell-6">
	        	<div class="easyui-panel" title="跟进统计（今日）" style="padding:10 20px;" data-options="fit:true,border:false">
			    	<p>
	        			<span>跟进任务数：0个</span>
	        		</p>
	        		<p>
	        			<span>质量下降任务数：0个</span>
	        		</p>
	        		<p>
	        			<span>质量上升任务数：0个</span>
	        		</p>
			    </div>
        	</div>
        	<div class="cell cell-6">
	        	<div class="easyui-panel" title="预估业绩" data-options="fit:true,border:false">
	        		<p>
	        			<span>今日预估签单金额：0元</span>
	        			<span style="padding-left: 100px;">今日预估回款金额：0元</span>
	        		</p>
	        		<p>
	        			<span>本周预估签单金额：0元</span>
	        			<span style="padding-left: 100px;">本周预估回款金额：0元</span>
	        		</p>
	        		<p>
	        			<span>本月预估签单金额：0元</span>
	        			<span style="padding-left: 100px;">本月预估回款金额：0元</span>
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
	        			<span>X类：0个</span>
	        			<span style="padding-left: 50px;">S类：0个</span>
	        		</p>
	        		<p>
	        			<span>A类：&nbsp;A0:0个</span>
	        			<span style="padding-left: 15px;">A1:0个</span>
	        			<span style="padding-left: 15px;">A2:0个</span>
	        			<span style="padding-left: 15px;">A3:0个</span>
	        			<span style="padding-left: 15px;">A4:0个</span>
	        			<span style="padding-left: 15px;">B类：&nbsp;B1:0个</span>
	        			<span style="padding-left: 15px;">B2:0个</span>
	        		</p>
	        		<p>
	        			<span>C类：&nbsp;C1:0个</span>
	        			<span style="padding-left: 15px;">C2:0个</span>
	        			<span style="padding-left: 15px;">C3:0个</span>
	        			<span style="padding-left: 15px;">C4:0个</span>
	        			<span style="padding-left: 67px;">D类：&nbsp;D1:0个</span>
	        			<span style="padding-left: 15px;">D2:0个</span>
	        		</p>
			    </div>
        	</div>
        </div>
</div>
