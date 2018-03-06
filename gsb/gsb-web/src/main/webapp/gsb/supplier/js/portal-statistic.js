System.Declare("com.gongsibao.crm.web.home");
com.gongsibao.crm.web.home.PortalStatistic = System.Object.Extends({
	ctor : function() {
		this.service = "com.gongsibao.crm.web.home.PortalStatistic";
	},
	invoke : function(method, pars, callback) {
		if (this.jServiceLocator == null) {
			this.jServiceLocator = new org.netsharp.core.JServiceLocator();
		}
		this.jServiceLocator.invoke(this.service, method, pars, callback);
	}
});
//销售简报
com.gongsibao.crm.web.home.Briefing = com.gongsibao.crm.web.home.PortalStatistic.Extends({
	briefingCount : function(method,pars){
		var brieCount = 0;
		this.invoke(method,null,function(count){
			brieCount = count;
			return brieCount;
		});
	}
});
//跟进统计
com.gongsibao.crm.web.home.Foolow = com.gongsibao.crm.web.home.PortalStatistic.Extends({
	foolowCount : function(method,pars){
		this.invoke(method,null,function(message){
			$.each(message, function(key, value) {
				$("#foolow").append("<p><span>"+key+"："+value+"个</span></p>");
			});
		});
	}
});
//预估业绩
com.gongsibao.crm.web.home.Forecast = com.gongsibao.crm.web.home.PortalStatistic.Extends({
	forecastAmount : function(method,pars){
		this.invoke(method,[pars],function(message){
			var pContent = "<p>";
			$.each(message, function(key, value) {
				switch(pars){
					case 1:
						pContent += "<span style='padding-left: 50px;'>今日" + key + "：" + value + "元</span>";
						break;
					case 2:
						pContent += "<span style='padding-left: 50px;'>本周" + key + "：" + value + "元</span>";
						break;
					case 3:
						pContent += "<span style='padding-left: 50px;'>本月" + key + "：" + value + "元</span>";
						break;	
				}
			});
			pContent += "</p>";
			$("#forecast").append(pContent);
		});
	}
});
//漏斗统计
com.gongsibao.crm.web.home.Funnel = com.gongsibao.crm.web.home.PortalStatistic.Extends({
	funnelXSCount : function(method,pars){
		this.invoke(method,null,function(message){
			$.each(message, function(key, value) {
				switch(key){
					case '全部任务':
						$("#allTasks").text(key + "："+ value +"个");
						break;
					case 'S类':
						$("#sClass").text(key + "："+ value +"个");
						break;
					case 'X类':
						$("#xClass").text(key + "："+ value +"个");
						break;	
				}
			});
		});
	},
	funnelCodeCount : function(method,pars){
		this.invoke(method,null,function(message){
			$.each(message, function(key, value) {
				$("#"+key).text(key + ":" + value + "个");
			});
		});
	}
});