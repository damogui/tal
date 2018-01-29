/// <reference path="system.js"/>

System.Declare("org.netsharp.core");

var EntityState =
    {
        Transient: "Transient",
        New: "New",
        Persist: "Persist",
        Deleted: "Deleted"
    };

var UiElementState =
    {
        Empty: 1,
        Hide: 2,
        Show: 3,
        Disable: 4,
        Enable: 5
    };

var ResultType =
    {
        info: "info",
        warn: "warn",
        error: "error",
        loginTimeout: "loginTimeout"
    };

var OpenType =
    {
        redirect: 0,
        window: 1,
        open: 2,
        tabs: 3
    };

var AggregateType =
    {
        None: "None",
        Count: "Count",
        Sum: "Sum",
        Min: "Min",
        Max: "Max",
        Average: "Average"
    };


//-------------------------------------------------------------------------------------------------------------------------------

org.netsharp.core.JServiceLocator = System.Object.Extends({

    ctor: function () {
        this.url = System.Url.getUrl("/panda/rest/service");
    },

    invoke: function (service, methodName, pars, callback, vid, isAsyn, errorCallback) {

        if (System.isnull(vid)) {
            vid = null;
        }

        var message = {
            service: service,
            methodName: methodName,
            vid: vid,
            parameters: []
        };

        if (pars) {
            for (var i = 0; i < pars.length; i++) {

                var j = JSON.stringify(pars[i]);
                var jpa = { value: encodeURIComponent(j) };
                message.parameters.push(jpa);
            }
        }

        this.doInvoke(this.url, message, callback, isAsyn, errorCallback);
    },
    doInvoke: function (url, message, callback, isAsyn, errorCallback) {

        if (isAsyn == undefined || isAsyn == null) {
            isAsyn = true;
        }
        var me = this;
        var jstr = JSON.stringify(message);
        $.ajax({
            url: url,
            type: "Post",
            dataType: "text",
            contentType: "json;charset=utf-8",
            data: jstr,
            async: isAsyn,
            success: function (json) {

                var result = null;
                try {
                    result = order = JSON.parse(json);
                }
                catch (error2) {
                    IMessageBox.error("系统异常");
                    return;
                }

                if (result.type == ResultType.info) {

                    if (callback == undefined || callback == null) {
                        return;
                    }
                    callback(result.data);
                }
                else if (result.type == ResultType.warn) {

                    if (errorCallback == undefined || errorCallback == null) {
                        IMessageBox.warning(result.message);
                    } else {
                        IMessageBox.warning(result.message, errorCallback);
                    }
                }
                else if (result.type == ResultType.error) {

                    if (errorCallback == undefined || errorCallback == null) {
                        IMessageBox.error(result.message);
                    } else {
                        IMessageBox.error(result.message, errorCallback);
                    }
                } else if (result.type == ResultType.loginTimeout) {

//                    IMessageBox.confirm("未登录或登录超时！重新登录吗？", function (r) {
//
//                        if (r) {
//
//                            PandaHelper.ShowLogin();
//                        } else {
//
//                            window.open('', '_self', '');
//                            window.close();
//                        }
//                    });
                	
                	window.top.location.href='/nav/panda-bizbase/authorization/login';
                }
                if ($.messager) {
                    $.messager.progress('close');
                }

                IMessageBox.loading.hide();
            },
            error: function (p1, p2, p3) {
                try {

                    if (p1.responseText != "") {

                    	IMessageBox.info(p1.responseText);
                    }
                    return;
                }
                catch (error) {
                	IMessageBox.info(data);
                }
            }
        });
    }
});

//-------------------------------------------------------------------------------------------------------------------------------
var IMessageBox =
    {
        info: function (message, callback) {

        	window.top.layer.alert(message,{title:'提示'},function (index, layero) {
                
                if (callback) {

                    callback();
                }
                window.top.layer.close(index);
            });
        },

        warning: function (message, callback) {

            window.top.layer.alert(message,{title:'警告',icon: 0},function (index, layero) {
                
                if (callback) {

                    callback();
                }
                window.top.layer.close(index);
            });
        },

        error: function (message, callback) {

        	window.top.layer.alert(message,{title:'错误',icon: 2},function (index, layero) {
                
                if (callback) {

                    callback();
                }
                window.top.layer.close(index);
            });
        },

        confirm: function (message, callback) {

        	window.top.layer.confirm(message,{title:'确认',icon: 3}, function (index, layero) {
                
        		window.top.layer.close(index);
            	callback(true);
            }, function () {
            	callback(false);
            });
        },
        toast:function(message,icon,callback){

        	var _icon = icon || 1;
        	window.top.layer.msg(message, {time: 3000, icon:_icon},function(){
        		
        		if(callback){
        			
        			callback();
        		}
        	});
        },
        loading:{
        	
        	show:function(){
        		
        		layer.load(1,{shade: [0.3, '#000']});
        	},
        	hide:function(){
        		
        		layer.closeAll('loading');
        	}        	
        },
        prompt: function (title, message, callback) {

        	layer.prompt({title: title, formType: 0}, function(pass, index){
              if (callback && pass) {
                  callback(pass,index);
              }
    		  //layer.close(index);
    		});
        },
        open: function (header, url, width, height, callback) {

        	width = width+'px';
        	height = height+'px';
        	window.top.layer.open({
        		  type: 2,
        		  title: header,
        		  fixed: false,
        		  maxmin: true,
        		  shadeClose:true,
        		  area: [width, height],
        		  content: url,
        		  cancel: function(){ 
        			  
                    if (callback) {
  
                          callback();
                      }
    			  }
        	});
        },
        area:function(value){
        	
        	layer.prompt({
      		  formType: 2,
      		  value: value,
      		  area: ['800px', '350px']
      		}, function(value, index, elem){
    		  layer.close(index);
      		});
        },
        closeWindow: function () {
            $('#pandaWindow').window("close");
        }
    };

var PandaHelper = {};

PandaHelper.ShowLogin = function () {

//	layer.prompt({
//	  formType: 1,
//	  title: '请输入密码',
//	  area: ['800px', '350px'] //自定义文本域宽高
//	}, function(value, index, elem){
//	  alert(value); //得到value
//	  layer.close(index);
//});

    var content = '<br/><p style="padding-left:50px;">&nbsp;帐号：<input id="loginName" type="text" class="easyui-validatebox nsInput" required="true" style="width:180px;"></input></p>'
        + '<p style="padding-left:50px;">&nbsp;密码：<input id="loginPassword" type="password" class="easyui-validatebox nsInput" required="true" style="width:180px;"></input></p>';

	layer.open({
		  type: 1,
		  title: '登录',
		  fixed: false,
		  maxmin: false,
		  shadeClose:true,
		  area: ['350px', '230px'],
		  content: content,
		  btn: ['登录', '取消'],
		  yes:function(index, layero){
			  
              var loginName = $("#loginName").val();
              var loginPassword = $("#loginPassword").val();
              if (System.isnull(loginName)) {

                  $("#loginName").focus();
                  return;
              }
              if (System.isnull(loginPassword)) {

                  $("#loginPassword").focus();
                  return;
              }
              var pars = [];
              pars.push(loginName);

              try {

                  pars.push($.md5(loginPassword + "user!@#123").substring(8,24));

              } catch (e) {
                  document.write("<script language=javascript src='/package/jquery/jquery.md5.js'></script>");
                  pars.push($.md5(loginPassword + "user!@#123").substring(8,24));
              }
              var jServiceLocator = new org.netsharp.core.JServiceLocator();
              jServiceLocator.invoke("org.netsharp.organization.controller.LoginController", "login", pars, function (message) {
            	  
                  if (message.result == 1) {
                	  
                	  	IMessageBox.toast('登录成功');
                	  	layer.closeAll();
                  } else if (message.result == 2) {
                	  
                  		IMessageBox.info("已经停用的用户不能登录!");
                  } else {
                	  
                  		IMessageBox.info("您的用户名或密码错误!");
                  }
              });
		  }
	});

}

//弹出支柱表单窗口
PandaHelper.openDynamicForm = function(option){
	
//	option = {
//		title:'',
//		width:400,
//		height:300,
//		items :[{
//			id:null,
//			title:null,
//			type:null,
//          className:'',
//			option:null
//		}], 
//		callback:function(){
//			
//		}
//	};

	var items = option.items;
	if(items==null || items.length==0){
		
		IMessageBox.error('items不能为空');
		return;
	}
	var formId = System.GUID.newGUID();
	var builder = new System.StringBuilder();
	
	builder.append('<form id="formsalesman">');
	builder.append('<div style="margin:10px;">');
	builder.append('<table cellpadding="5" cellspacing="10" class="query-panel">');
	$(items).each(function(i,item){

		if(item.type == 'textarea'){

			builder.append('<tr><td class="title">'+item.title+'</td><td><textarea id="'+item.id+'" style="width:'+item.width+'px;height:'+item.height+'px;" className="'+(item.className||'')+'" ></textarea></td></tr>');
		}else{

			builder.append('<tr><td class="title">'+item.title+'</td><td><input id="'+item.id+'"/></td></tr>');
		}
	});
	builder.append('	</table>');
	builder.append('</div>');
	builder.append('</form>');

	layer.open({
		type : 1,
		title : option.title,
		fixed : false,
		maxmin : false,
		shadeClose : false,
		zIndex : 100000,
		area : [ option.width+'px', option.height+'px' ],
		content : builder.toString(),
		btn : [ '提交', '取消' ],
		success : function(layero, index) {
			
			$(items).each(function(i,item){

				if(item.type != 'textarea'){
					
					var expression = '$("#'+item.id+'").'+item.type+'(item.option);';
					eval(expression);
					//alert(expression);
				}
			});
		},
		btn1 : function(index, layero) {
			
			if(option.callback){
				
				var obj = {};//获取表单上的值（暂时不实现）
				option.callback(index, layero);
			}
		}
	});
}
//-------------------------------------------------------------------------------------------------------------------------------
var LODOP;//用于Lodop打印控件
