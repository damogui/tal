<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<div>
		<div class="row" style="height:190px;">
        	<div class="cell cell-12">
	        	<div class="easyui-panel" title="销售简报" data-options="fit:true,border:false">
	        		<div>
	        			<a href="#" class="easyui-linkbutton" onclick="getBriefingCount(1)" data-options="toggle:true,group:'g2',plain:true,selected:true">今日</a>
						<a href="#" class="easyui-linkbutton" onclick="getBriefingCount(2)" data-options="toggle:true,group:'g2',plain:true">本周</a>
						<a href="#" class="easyui-linkbutton" onclick="getBriefingCount(3)" data-options="toggle:true,group:'g2',plain:true">本月</a>
						<a href="#" class="easyui-linkbutton" onclick="getBriefingCount(4)" data-options="toggle:true,group:'g2',plain:true">本年</a>
	        		</div>
	        		<div id="briefing" style="padding-top: 15px;">
	        			<p>
	        				<span>0-1</span>
	        				<span class="padding-15">0-2</span>
	        				<span class="padding-15">0-3</span>
	        				<span class="padding-15">0-4</span>
	        			</p>
	        			<p>
	        				<span>1-1</span>
	        				<span class="padding-15">1-2</span>
	        				<span class="padding-15">1-3</span>
	        				<span class="padding-15">1-4</span>
	        			</p>
	        		</div>
			    </div>
        	</div>	
        </div>
</div>
