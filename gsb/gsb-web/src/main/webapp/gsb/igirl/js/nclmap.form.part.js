System.Declare("com.gongsibao.igirl.web");
var countinput=0;
com.gongsibao.igirl.web.NclMapPart = org.netsharp.panda.commerce.FormPart.Extends( {

	ctor : function () {
		this.base();
	},
	nclOneChange : function(newValue, oldValue){
		$("#nclTwoContent").val("");
		function DeleteFromArrayByFilter(attrs,field,fieldvalue,filter){
			var index=-1;
			for(var i=0;i<attrs.length;i++){
				if(filter(attrs[i],field,fieldvalue)){
					index=i;
					break;

				}
			}
			if(index!=-1){
				attrs.splice(index,1)
			}


		}
		if($("#ncltwogrid").length<=0){
			var $t=$("[name='nclOne_name']")
			$("<input id='filterinput' width='30px'/>").insertAfter($t.parent());
			$("<table id='ncltwogrid' style='margin-top:5px;padding-top:3px;'></table>").insertBefore("#nclTwoContent")
		}
		var selectedItems=[];
		var tmpDatas=[];
		$("#filterinput").on("input",function (e) {
			tmpDatas.length=0;
			var currentVal=$(e.currentTarget).val();
			if(rtnData){
				rtnData.forEach(function (obj, index) {
					if(obj.code.indexOf(currentVal)>=0 || obj.name.indexOf(currentVal)>=0 || obj.thirdCode.indexOf(currentVal)>=0){
						tmpDatas.push(obj);
					}
				})
			}   
			setTimeout(function () {
				$('#ncltwogrid').datagrid({
					data:tmpDatas
				});
				//根据文本框内的值反选择grid
				backSelect();
			},1000)
		});

		var rtnData=null;
		//首次加载需要按照大类id获取所有的小类列表数据，按照selectedTwoStr去初始化selectedItems
		//去选择grid,调用selectRecord --idValue
		var me = this;
		if(newValue!=-1){
			this.invokeService("findSubsByNclOneId", [newValue], function(data) {
				//me.reload();
				//IMessageBox.toast(JSON.stringify(data));
				rtnData=data;
				$('#ncltwogrid').datagrid({
					width:450,
					height:100,
					singleSelect:false,
					checkOnSelect:true,
					selectOnCheck:true,
					columns:[[
						{field:'id',title:'id',width:60,checkbox:true},
						{field:'code',title:'二级编码',width:60},
						{field:'thirdCode',title:'小类编码',width:100},
						{field:'name',title:'内容',width:120},
						]],
						data:rtnData,
						onCheck:function (rindex,rowData) {
							push(selectedItems,rowData)
							updateSelectTxtVal();
						},
						onUncheck:function (rindex,rowData) {
							DeleteFromArrayByFilter(selectedItems,"id",rowData.id,function (row,fd,fv) {
								if(row[fd]==fv)
									return true
									else
										return false
							})
							updateSelectTxtVal();
						},
						onCheckAll:function (rows) {
							rows.forEach(function (value) {
								push(selectedItems,value);
							})
							updateSelectTxtVal();

						},
						onUncheckAll:function (rows) {
							rows.forEach(function (rowData) {
								DeleteFromArrayByFilter(selectedItems,"id",rowData.id,function (row,fd,fv) {
									if(row[fd]==fv)
										return true
										else
											return false
								})
							})
							updateSelectTxtVal();
						}
				});

				//根据文本框内的值反选择grid
				backSelect();


			});

		}
		function backSelect(){
			var selectStr=$("#nclTwoContent").val();
			if(selectStr){
				selectStrs=selectStr.split('\n');
				selectStrs.forEach(function(str){
					var index=str.lastIndexOf(":");
					var id=str.substring(index+1);
					console.log(id)
					$("#ncltwogrid").datagrid('getRows').forEach(function(row,i){
						if(row.id==id){
							$("#ncltwogrid").datagrid('checkRow',i)
						}

					});
				});   
			}

		}
		//更新选择的小类文本字符串,加载时需要解析
		function updateSelectTxtVal() {
			var tmpArray=[];
			var i=1;
			selectedItems.forEach(function (row) {
				tmpArray.push(i+":"+row.name+":"+row.code+":"+row.id);
				i++;
			})
			var rtnStr=tmpArray.join("\n")
			$("#nclTwoContent").val(rtnStr)


		}
		function push(arr,obj) {
			var exist=false;
			arr.forEach(function (value) {
				if(value.id==obj.id){
					exist=true;
					return false;
				}
			})
			if(!exist){
				arr.push(obj)
			}

		}
	}

});