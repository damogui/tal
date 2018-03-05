<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="com.gongsibao.crm.web.home.PortalStatistic"%>
<%@ page import="com.gongsibao.entity.crm.home.BaseHomeSupplierEntity"%>

<%
	BaseHomeSupplierEntity homeEntity = PortalStatistic.getDate();
%>
<div>
		<div class="row" style="height:150px;">
        	<div class="cell cell-12">
	        	<div class="easyui-panel" title="销售简报" data-options="fit:true,border:false">
	        		<p>
	        			<span>新增任务数：<%=homeEntity.getNewTasksCount()%>个</span>
	        		</p>
			    </div>
        	</div>	
        </div>
        <div class="row" style="height:300px;">
        	<div class="cell cell-6">
	        	<div class="easyui-panel" title="指标" style="padding:10 20px;" data-options="fit:true,border:false">
			    </div>
        	</div>
        	<div class="cell cell-6">
	        	<div class="easyui-panel" title="交易" data-options="fit:true,border:false">
			    </div>
        	</div>
        </div>
		<div class="row" style="height:150px;">
        	<div class="cell cell-12">
	        	<div class="easyui-panel" title="线索转化" data-options="fit:true,border:false">
			    </div>
        	</div>
        </div>

        <div class="row" style="height:300px;">
        	<div class="cell cell-6">
	        	<div class="easyui-panel" title="转化分析" style="padding:10 20px;" data-options="fit:true,border:false">
			    </div>
        	</div>
        	<div class="cell cell-6">
	        	<div class="easyui-panel" title="抽查异常" data-options="fit:true,border:false">
			    </div>
        	</div>
        </div>
</div>
