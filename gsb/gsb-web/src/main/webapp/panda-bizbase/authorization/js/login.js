System.Declare("org.netsharp.core");

org.netsharp.core.LoginController = System.Object.Extends({
	ctor : function() {
		this.jServiceLocator = null;
		this.service = "org.netsharp.organization.controller.LoginController";
	},
	invoke : function(month, pars, callback) {
		if (this.jServiceLocator == null) {
			this.jServiceLocator = new org.netsharp.core.JServiceLocator();
		}
		this.jServiceLocator.invoke(this.service, month, pars, callback);
	},
	init : function() {
		this.setShortcutKey();
		this.setCookies();
	},
	setCookies : function() {

		var c = System.Cookies.get("netsharp_login");
		if (c != null) {
			var j = null;
			eval("j=" + c);
			if (j.username) {
				$("#username").val(j.username);
			}
			if (j.password) {
				$("#password").val(j.password);
			}
			$("#remember").prop("checked", true);
		}
	},

	setShortcutKey : function() {
		
		var me = this;
		$("#username,#password,#remember").keydown(function(e) {
			if (e.keyCode == 13) {
				me.login();
			}
		});
	},
	getInfo:function (){
		
	    var s = "";   
	    s += " 网页可见区域宽："+ document.body.clientWidth+"\n";
	    s += " 网页可见区域高："+ document.body.clientHeight+"\n";
	    s += " 网页可见区域宽："+ document.body.offsetWidth + " (包括边线和滚动条的宽)"+"\n";
	    s += " 网页可见区域高："+ document.body.offsetHeight + " (包括边线的宽)"+"\n";
	    s += " 网页正文全文宽："+ document.body.scrollWidth+"\n";
	    s += " 网页正文全文高："+ document.body.scrollHeight+"\n";
	    s += " 网页被卷去的高(ff)："+ document.body.scrollTop+"\n";
	    s += " 网页被卷去的高(ie)："+ document.documentElement.scrollTop+"\n";
	    s += " 网页被卷去的左："+ document.body.scrollLeft+"\n";
	    s += " 网页正文部分上："+ window.screenTop+"\n";
	    s += " 网页正文部分左："+ window.screenLeft+"\n";
	    s += " 屏幕分辨率高："+ window.screen.height+"\n";
	    s += " 屏幕分辨率宽："+ window.screen.width+"\n";
	    s += " 屏幕可用工作区高度："+ window.screen.availHeight+"\n";
	    s += " 屏幕可用工作区宽度："+ window.screen.availWidth+"\n";
	    s += " 屏幕设置是 "+ window.screen.colorDepth +" 位彩色"+"\n";
	    s += "的屏幕设置 "+ window.screen.deviceXDPI +" 像素/英寸"+"\n";
	    return s;
	},
	login : function() {
		
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == "") {
			
			IMessageBox.error("账号不能为空！", function(){
				$("#username").focus();
			});
			return;
		}
		
		if (password == "") {
			
			IMessageBox.error("密码不能为空！", function(){
				$("#password").focus();
			});
			return;
		}

		var mpassword = $.md5(password + "user!@#123").substring(8,24);
		var pars = [];
		pars.push(username);
		pars.push(mpassword);
		var me=this;
		this.invoke("login", pars, function(message) {
			
			if (message.result == 1 || message.result == 3) {
				
				var isRemember = $("#remember").prop('checked');
				if (isRemember == true) {
					var c = "{username:'" + username + "',password:'" + password + "'}";
					System.Cookies.set("netsharp_login", c, 100);
				}
				
				//设置ticket
				System.Cookies.set("COOKIE_ER_LOGIN_TICKET", message.data, 100);
				
				var workbenchList = message.workbenchList;
				if(workbenchList.length == 1){

					window.location.href = workbenchList[0].path;

				}else{
					
				    var content = '';
				    $(workbenchList).each(function(index,item){
				    	
				    	content += '<div class="workbench-item"><a href="'+item.path+'">'+(index+1)+'. '+item.name+'</a></div>';
				    });
				    
				    var offsetLeft = ($('body').width() - 410)+'px';
				    var offsetTop = (($('body').height() - 150)/2)+'px';
				    layer.open({
				        type: 1,
				        closeBtn:0,
				        anim: 2,
				        offset: [offsetTop,offsetLeft],
				        title:'请选择工作台',
				        area: ['350px', '150px'],
				        content: content
				    });
				}
				
			} else if (message.result == 2) {
				
				IMessageBox.error("您的帐号已经冻结，请联系管理员！");
				
			} else {
				
				IMessageBox.error("您的用户名或密码错误！");
			}
		});
	}
});

//清除，提升效率
sessionStorage.clear();