System.Declare("com.gongsibao.igirl.web");
com.gongsibao.igirl.web.TradeMarkCasePart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    // getaddState:function(){
    //
    // 	var swtCustomerId = this.queryString("swtCustomerId");
    // 	if(swtCustomerId){
    //
    // 		return UiElementState.Hide;
    // 	}
    // },
    // onload: function () {
    //
    //     var id = this.queryString("id");
    //     if (System.isnull(id)) {
    //
    //     	var swtCustomerId = this.queryString("swtCustomerId");
    //     	var swtServiceId = this.queryString("swtServiceId");
    //     	if(swtCustomerId){
    //
    //     		this.bySwtCustomerId(swtCustomerId);
    //     	}else{
    //
    //             this.add();
    //     	}
    //     }else {
    //
    //         this.byId(id);
    //     }
    //
		// var ctrlsIds = ['mobile','telephone','weixin','qq'];
		// $(ctrlsIds).each(function(i,item){
		// 	$("#"+item).validatebox('disableValidation');
		// });
		//
    // },
    // added: function (currentItem) {
    //
    // 	var swtCustomerId = this.queryString("swtCustomerId");
    // 	if(!System.isnull(swtCustomerId)){
    //
    // 		//设置默认值
    // 		currentItem.customerSourceId = 4181;
    // 		currentItem.consultWay = 42143;
    // 	}
    // },
    // databindafter:function(){
    //
    // 	if(this.viewModel.currentItem.entityState == EntityState.Persist){
    //
    // 		var mobile = $("#mobile").val();
    // 		if(!System.isnull(mobile)){
    //
    //     		$("#mobile").prop("disabled", true);
    // 		}
    // 		//修改状态：禁用全表单
    // 		this.viewModel.disable();
    // 	}
    //
    //     $('.easyui-combobox,.easyui-combogrid').combobox("initClearBtn");
    //     $('.easyui-filebox').filebox("initClearBtn");
    // },
    // bySwtCustomerId: function (swtCustomerId) {
    //
    //     var vm = this.viewModel;
    //     var me = this;
    //     this.invokeService("bySwtCustomerId", [swtCustomerId], function (jmessage) {
    //
    //     	 var nav = jmessage;
    //          vm.currentItem = nav.Entity;
    //          if(vm.currentItem.entityState != EntityState.New){
    //
    //              vm.currentItem.entityState = EntityState.Persist;
    //          }else{
    //         	 vm.currentItem.customerSourceId = 4181;
    //         	 vm.currentItem.consultWay = 42143;
    //          }
    //          me.paging = nav.Paging;
    //          me.databind();
    //     });
    // },
    companyNameChange:function(newValue, oldValue){
        alert(newValue);

    	//TYPE_1(1, "自然分配"),
    	//TYPE_2(2, "指定部门");
        // if(newValue==1){
			// $("#allocationOrgId").combobox('disable').combobox('disableValidation').combobox('setValue','-1');
        // }else{
        //
			// $("#allocationOrgId").combobox('enable').combobox('enableValidation');
        // }
    },
    applierTypeChange:function (newValue, oldValue) {
        if(newValue==1){
            $("#companyName").validatebox('disable').validatebox('disableValidation');
        }else{
            $("#companyName").validatebox('enable').validatebox('enableValidation');
        }
    },
   mobileChange:function (ctl) {
	   var newValue=$(ctl).val();
    	var qcurl="http://192.168.4.1:3000/qc?detailLink=http://192.168.28.41:8080/gsb/igirl/tmcase.html?mobile="+newValue;
    	$("#tokenImgUrl").attr("src",qcurl)

       this.invokeService("tmsForRobot",[72],function (msg) {
           alert(JSON.stringify(msg));
       })
       
    }
});

com.gongsibao.igirl.web.DownloadAttachmentDetailPart=org.netsharp.panda.commerce.DetailPart.Extends( {



});

com.gongsibao.igirl.web.UploadAttachmentDetailPart=org.netsharp.panda.commerce.DetailPart.Extends( {



});
com.gongsibao.igirl.web.TradeMarkDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {

	// edit:function(rowData){
    //
	// 	IMessageBox.info("暂不支持查看");
	// 	return;
	// }
    nclOneChange:function (newValue, oldValue) {
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
        //更新选择的小类文本字符串,加载时需要解析
        function updateSelectTxtVal() {
            var tmpArray=[];
            var i=1;
            selectedItems.forEach(function (row) {
                tmpArray.push(i+":"+row.name+":"+row.code+":"+row.id);
                i++;
            })
            var rtnStr=tmpArray.join("\n")
            $("#selectedTwoStr").val(rtnStr)
            

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
        function backSelect(){
        	  var selectStr=$("#selectedTwoStr").val();
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

      if($("#ncltwogrid").length<=0){
             var $t=$("[name='nclOne_name']")
             $("<input id='filterinput' width='30px'/>").insertAfter($t.parent());
            $("<table id='ncltwogrid'></table>").insertBefore("#selectedTwoStr")
         }
        var selectedItems=[];
        var rtnData=null;
        //首次加载需要按照大类id获取所有的小类列表数据，按照selectedTwoStr去初始化selectedItems
        //去选择grid,调用selectRecord --idValue
        var me = this;
        
      
   
        if(newValue!=-1){
//          	     var g = $('#nclOne_name').combogrid('grid');	// get datagrid object
//                 var r = g.datagrid('getSelected');	// get the selected row  
//                 var en=this.viewModel.getEntity();
//                 if(en && !en.nclOne){
//                	 en.nclOne={};
//                	 en.nclOne.code=r.code;
//                 }else
//                                 {
//                     if(en.nclOne){
//                    	 en.nclOne.code=r.code;
//                                	      }
//                                 }

            this.invokeService("findSubsByNclOneId", [newValue], function(data) {
                //me.reload();
                //IMessageBox.toast(JSON.stringify(data));
                rtnData=data;
                $('#ncltwogrid').datagrid({
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


    }
});

// com.gongsibao.crm.web.ProdMapDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
//
// 	productChange:function(newValue,oldValue){
//
// 		//为空时，重置查询条件：q
// 		if(System.isnull(newValue)){
//
// 			var options = $('#product_name').combogrid('options');
// 			var qp = options.queryParams;
// 		}
// 	}
// });
//
//
// com.gongsibao.crm.web.FlowDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
//
//
// 	addBefore:function(){
//
//     	//将【内容】设置为禁用
//     	$('#content').prop('disabled',false);
//
// 	},
// 	editBefore:function(){
//
//     	//将【内容】设置为禁用
//     	$('#content').prop('disabled',true);
//
// 	},
// 	saveAfter:function(entity){
//
// 		entity.id=null;
// 		var me = this;
// 		this.invokeService("save", [entity], function(data) {
//
// 			me.parent.byId(entity.customerId);
// 		});
//
// 	}
// });
//
//
// /**
//  * 扩展联系方式验证
//  */
// $.extend($.fn.validatebox.defaults.rules, {
//
// 	validationContactWay: {
//
//         validator: function(value,param){
//
//         	var isValidator = false;
//         	var me = this;
//         	var serviceLocator = new org.netsharp.core.JServiceLocator();
//     		var id = null;
//     		if (controllercustomer.viewModel.currentItem != null) {
//
//     			id = controllercustomer.viewModel.currentItem.id;
//     		}
//         	serviceLocator.invoke(controllercustomer.context.service, 'validationContactWay', [id,value.trim(),param[0]], function(data){
//
//         		isValidator = data==null?true:false;
//         	},null, false);
//
//         	return isValidator;
//         },
//         message: '{1}已存在'
//     }
// });
//
// System.Declare("com.gongsibao.controls");
// com.gongsibao.controls.CityComboBox = org.netsharp.controls.PccBox.Extends({
// 	ctor: function() {
// 		this.base();
// 		this.service = 'com.gongsibao.controls.CityComboBoxController';
// 	}
// });