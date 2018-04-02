<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
	        	<div id="briefing" class="easyui-panel" title="销售简报" style="padding:0px 10px !important;" data-options="fit:true,border:false">
	        	
	        		<div class="row">
	        			<div class="cell cell-2">
	        				<div class="title"><span>新增商机</span></div>
	        				<div class="num"><span id="add_count">0</span></div>
	        			</div>
	        			<div class="cell cell-2">
	        				<div class="title"><span>未启动商机</span></div>
	        				<div class="num"><span id="un_start_count">0</span></div>
	        			</div>
	        			<div class="cell cell-2">
	        				<div class="title"><span>待跟进商机</span></div>
	        				<div class="num"><span id="stay_foolow_count">0</span></div>
	        			</div>
	        			<div class="cell cell-2">
	        				<div class="title"><span>超时商机</span></div>
	        				<div class="num"><span id="timeout_count">0</span></div>
	        			</div>
	        			<div class="cell cell-2">
	        				<div class="title"><span>异常未处理商机</span></div>
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
        	</div>
        </div>
</div>
