
org.netsharp.panda.Workbench = System.Object.Extends({
	ctor : function() {
		
		this.isTabs = $('body').attr('isTabs')=='true';
	},
	init:function(){
		
		//创建右击菜单
		var builder = new System.StringBuilder();{
			
			builder.append('<div id="rightPanel" class="easyui-menu">');
			builder.append('<div onclick="$(\'#tabs\').tabs(\'closeCurrent\');">关闭</div>');
			builder.append('<div onclick="$(\'#tabs\').tabs(\'closeAll\');">关闭全部</div>');
			builder.append('<div onclick="$(\'#tabs\').tabs(\'closeOther\');">关闭其他</div>');
			builder.append('<div class="menu-sep"></div>');
			builder.append('<div onclick="$(\'#tabs\').tabs(\'closeRight\');">关闭右侧</div>');
			builder.append('<div onclick="$(\'#tabs\').tabs(\'closeLeft\');">关闭左侧</div>');
			builder.append('</div>');
		}
		var menuStr = builder.toString();
		$(menuStr).appendTo("body").menu();
		
		//渲染菜单树
	    $('ul[isMenu=true]').each(function(index, item) {
	        var json = $(item).attr('data');
	        var data = eval(json);
	        if (data != undefined && data != null) {
	            $(item).tree('loadData', data);
	        }
	    });
	    
	    //打开首页
	    this.openWorkspace("首页","/nav/panda-bizbase/home",'fa fa-home',false);
	    //默认打开第1个模块
	    var firstNavItem = $("#nav li").first();
	    if(firstNavItem){
		  this.selectNav(firstNavItem);
	    }
	},
    openWorkspace: function(subtitle, url, icon, closable, id,openMode,width,height) {

        if (url == undefined || url == null || url == "") {

            return;
        }

        //弹出窗口
		if(openMode == OpenType.window){
			url = System.Url.join(url, "openType="+OpenType.window);
			IMessageBox.open(subtitle, url, width, height);
			return;
		}
		
		//打开浏览器窗口
		if(openMode == OpenType.open){
			url = System.Url.join(url, "openType="+OpenType.open);
			window.open(url);
			return;
		}
		
		url = System.Url.join(url, "openType="+OpenType.redirect);
        var iframe = this.createFrame(url, id);
        if (this.isTabs) {

            if (!$('#tabs').tabs('exists', subtitle)) {

                $('#tabs').tabs('add', {
                    title: subtitle,
                    content: iframe,
                    closable: closable,
                    selected: true,
                    iconCls: icon
                });

            } else {

                $('#tabs').tabs('select', subtitle);
                var tab = $('#tabs').tabs('getTab', subtitle);
                $("#tabs").tabs('update', {
                    tab: tab,
                    options: {
                        content: this.createFrame(url, id)
                    }
                });
            }

            if (closable) {
                this.closeWorkspace();
            }

        } else {
        	
        	subtitle = '系统管理 > 业务类型';
            $("#center1").html(iframe);
            $("#center1").panel({
                title: subtitle,
                tools: [{    
                    iconCls:'fa fa-clone',    
                    handler:function(){
                    	window.open(url);
                    }    
                }]
            });
        }
    },
    closeSelectedTab:function(){
    	
    	var currentTab = $('#tabs').tabs('getSelected');
    	var currentTabIndex = $('#tabs').tabs('getTabIndex',currentTab);
    	$('#tabs').tabs('close',currentTabIndex);
    },
    createFrame: function(url, id) {
    	var h = $("body").height()-76;
        var s = '<iframe id="' + id + '" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:'+h+'px;"></iframe>';
        return s;
    },

    closeWorkspace: function() {

        /* 双击关闭TAB选项卡 */
        $(".tabs-inner").dblclick(function() {
        	
            var subtitle = $(this).children(".tabs-closable").text();
            if(subtitle){
                $('#tabs').tabs('close', subtitle);
            }
        });

        /* 为选项卡绑定右键 */
        $(".tabs-inner").bind('contextmenu',function(e) {
        	
        	var isClose = $(this).next(".tabs-close").length > 0
        	if(!isClose){
        		return;
        	}
        	
            $('#rightPanel').menu('show', {
                left: e.pageX,
                top: e.pageY
            });

            var subtitle = $(this).children(".tabs-closable").text();
            $('#rightPanel').data("currtab", subtitle);
            $('#tabs').tabs('select', subtitle);
            return false;
        });
    },
    exit: function() {

    	IMessageBox.confirm('您确定要退出本次登录吗?',function(r){
    		
            if (r) {

                var serviceLocator = new org.netsharp.core.JServiceLocator();
                var service = "org.netsharp.organization.controller.LoginController";
                serviceLocator.invoke(service, "logout", null,function(jMessage) {
                    if (jMessage == "1") {
                        window.location.href = "/nav/panda-bizbase/authorization/login";
                    } else {
                        IMessageBox.error(jMessage.Exception.Message);
                    }
                });
            }
    	});
    },
    changePassword: function() {

        var url = "/panda/system/modify/password/form";
        url = System.Url.getUrl(url);
        
    	layer.open({
		  type: 2,
		  title: '修改密码',
		  fixed: false,
		  maxmin: false,
		  shadeClose:true,
		  area: ['430px', '295px'],
		  btn: ['确定', '取消'],
		  content: url,
		  yes:function(index, layero){
			  
			  var iframeId = 'layui-layer-iframe'+index;
			  document.getElementById(iframeId).contentWindow.controlleremployee.save();
		  }
		});
    },

    selectNav:function(item){

    	var $item = $(item);
    	var is_selected = $item.hasClass("selected");
    	if(is_selected){
    		return;
    	}
    	
    	var west_panel = $(".easyui-layout").layout("panel","west");
    	var options = $(west_panel).panel("options");
    	options.title = $item.text();
    	$(west_panel).panel(options);
    	
    	var me = this;
    	$item.parent().children().removeClass("selected");
    	$item.addClass("selected").parent();
    	var id = $item.attr("id");
    	var accordionId = "accordion_" + id;
    	var $accordionId = "#" + accordionId;
    	var $accordion = $($accordionId);
    	$(".accordion").hide();
    	if($accordion.length==0){
    		
    		$("#west").append("<div id='"+accordionId+"'></div>");
    		$($accordionId).accordion({
    			fit:true,
    			border:false
    		});
    		
        	var data = $item.attr("data");
        	var items = eval(data);
        	$(items).each(function(index,item){
        		
        		var selected = index==0?true:false;
        		var content = me.getNavItemsHtml(item.children);
        		var iconCls = item.iconCls;
        		$($accordionId).accordion('add', {
        			title: item.text,
        			iconCls:item.iconCls,
        			content: content,
        			selected:selected
        		});

        	});
        	

    	}else{
    		
    		$accordion.show();
    	}
    },
    
    getNavItemsHtml:function(items){
    	
    	var builder = new System.StringBuilder();
    	builder.appendLine('<ul class="accordion_item_nav">');
    	$(items).each(function(index,item){

    		var li = '<li><a href="javascript:void(0);" onclick=\'workbench.accordionNavClick(this,"{0}","{1}","{2}",true,"{3}","{4}",{5},{6});\'><span>{0}</span></a></li>'.format(
					item.text,
    				item.attributes.url,
					'',
					true,
					item.id,
					item.attributes.openMode,
					item.attributes.windowWidth,
					item.attributes.windowHeight);
    		builder.appendLine(li);
    	});
    	builder.appendLine('</ul>');
    	return builder.toString();
    },
    accordionNavClick:function(obj,text, url, icon, closable, id,openMode,width,height){
    	
    	$(obj).parent().addClass('selected').siblings().removeClass('selected');
    	workbench.openWorkspace(text,url,icon,true,id,openMode,width,height);
    }
});

var workbench = new org.netsharp.panda.Workbench();
$(function(){

	workbench.init();
});