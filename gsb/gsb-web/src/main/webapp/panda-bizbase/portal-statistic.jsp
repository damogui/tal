<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<div>
		<div class="row" style="height:150px;">
        	<div class="cell cell-12">
	        	<div id="briefing" class="easyui-panel" title="销售简报" data-options="fit:true,border:false">
	        		<button type="button" id="btn_swith_date" onclick="getBriefingCount(1)">今日</button>
	        		<button type="button" id="btn_swith_date" onclick="getBriefingCount(2)">本周</button>
	        		<button type="button" id="btn_swith_date" onclick="getBriefingCount(3)">本月</button>
	        		<button type="button" id="btn_swith_date" onclick="getBriefingCount(4)">本年</button>
	        		<p>
	        			<span></span>
	        			<span class="padding-15"></span>
	        			<span class="padding-15"></span>
	        			<span class="padding-15"></span>
	        			<span class="padding-15"></span>
	        			<span class="padding-15"></span>
	        			<span class="padding-15"></span>
	        			<span class="padding-15"></span>
	        		</p>
			    </div>
        	</div>	
        </div>
</div>
