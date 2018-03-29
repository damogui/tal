System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.ExpenseBillFormCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    },
    init:function(){

    },
    addTripRows:function (){
    	var row_html = '<tr> '+
				    	' <td style="text-align: center;"><input class="easyui-datebox" style="width:150px;height:32px"></td>'+
				    	' <td style="text-align: center;"><input class="easyui-datebox" style="width:150px;height:32px"></td>'+
						' <td style="text-align: center;"><input class="easyui-textbox" style="width:150px;height:32px"></td>'+
						' <td style="text-align: center;"><input class="easyui-textbox" style="width:150px;height:32px"></td>'+
						' <td style="text-align: center;"><input class="easyui-textbox" style="width:200px;height:32px"></td>'+
						' <td style="text-align: center;"><a href="#" onclick="$(this).parent().parent().remove();" data-options="iconCls:\'icon-remove\'" class="easyui-linkbutton" >删除</a></td>'+
					    '</tr>';
				$('#trip_detail_table').append(row_html);
				//重写加载样式
				$.parser.parse($('#trip_detail_table'));  
    },
    addSubsidyRows:function (){
    	var row_html = '<tr> '+
    	' <td style="text-align: center;"><input class="easyui-textbox" style="width:150px;height:32px"></td>'+
    	' <td style="text-align: center;"><input class="easyui-textbox" style="width:150px;height:32px"></td>'+
    	' <td style="text-align: center;"><input class="easyui-textbox" style="width:150px;height:32px"></td>'+
    	' <td style="text-align: center;"><input class="easyui-textbox" style="width:150px;height:32px"></td>'+
    	' <td style="text-align: center;"><input class="easyui-textbox" style="width:200px;height:32px"></td>'+
    	' <td style="text-align: center;"><a href="#" onclick="$(this).parent().parent().remove();" data-options="iconCls:\'icon-remove\'" class="easyui-linkbutton" >删除</a></td>'+
    	'</tr>';
    	$('#subsidy_detail_table').append(row_html);
    	//重写加载样式
    	$.parser.parse($('#subsidy_detail_table'));  
    },
    addRows:function (){
    	var row_html = '<tr> '+
						' <td style="text-align: center;" >'+
    					'<select id="cc" class="easyui-combobox"  style="width:200px;">'+
    					'<option value="1">通讯费</option>'+
    					'<option value="2">交通费</option>'+
    					'<option value="3">补助</option>'+
    					'</select>'+
						'</td>'+
						' <td style="text-align: center;"><input class="easyui-textbox" style="width:200px;height:32px"></td>'+
						' <td style="text-align: center;"><input class="easyui-textbox" style="width:300px;height:32px"></td>'+
						' <td style="text-align: center;"><a href="#" onclick="$(this).parent().parent().remove();" data-options="iconCls:\'icon-remove\'" class="easyui-linkbutton" >删除</a></td>'+
					    '</tr>';
    	$('#cost_detail_table').append(row_html);
    	//重写加载样式
    	$.parser.parse($('#cost_detail_table'));  
    }
});

