var ip="http://192.168.29.137:3000";
$(function(){
	var siteCtl=new org.netsharp.core.JServiceLocator()
	var ctlServiceStr="com.gongsibao.igirl.web.SiteInfoListPart";
	axios.get(ip+"/vue/comp/base").then(function(res){
		  //加载所有基础vue组件
	   console.log(res)
	   res.data.forEach(function(comp){
		      var obj=eval("("+comp+")");
	    	  Vue.component(obj.vname,obj);
	      });
	   var tms={
				template:'<div>tm search</div>',
				created:function(){		
				}
		}
		var pt={template:'<gsb-accordion :items="tms"></gsb-accordion>',
			data:function(){
				return {
					tms:[
						{id:"1",name:"商标选项1",desc:"okokok"},{id:"2",name:"商标选项2",desc:"okokok"},
					]
				}
			}
		}
		var cr={template:'<div>copyright</div>'}
		var def={
				template:'<div></div>',
				created:function(){
					//$("#content").height(0)
					
				}
		}
		var tm=()=>Promise.resolve(new Promise(function(resolve,reject){
			//异步加载组件
			axios.get(ip+"/vue/comp/tm").then(function(res){	
				 var obj=eval("("+res.data+")");
				 console.log(obj)
				 resolve(obj);
			}).catch(function(reason){
				console.log(reason)
			})
			
		}))
		var tmc=()=>Promise.resolve(new Promise(function(resolve,reject){
			//异步加载组件
			axios.get(ip+"/vue/comp/tmconfirm").then(function(res){	
				 var obj=eval("("+res.data+")");
				 console.log(obj)
				 resolve(obj);
			}).catch(function(reason){
				console.log(reason)
			})
			
		}))
		var payment=()=>Promise.resolve(new Promise(function(resolve,reject){
			//异步加载组件
			axios.get(ip+"/vue/comp/payment").then(function(res){	
				 var obj=eval("("+res.data+")");
				 console.log(obj)
				 resolve(obj);
			}).catch(function(reason){
				console.log(reason)
			})
			
		}))
		var routes=[
			{path:'/',component:def},
			{path:'/tms',component:tms},
			{path:'/tm',component:tm},
			{path:'/tmc',component:tmc},//案件确认
			{path:'/payment',component:payment},//案件确认
			{path:'/pt',component:tmc},
			{path:'/cr',component:cr},
		]
		var router=new VueRouter({
			routes:routes,
			//mode:'history'
		});
		router.$ctlService=siteCtl;
		router.onError(function(err){
			console.log(err);
		});
		router.beforeEach(function(to,from,next){
		   if(to.path!="/"){
			      $("#content").height($(window).height())
			      $("#content").show()
			      $("#navDiv").hide();
		      }else{
		    	  $("#content").hide();
		    	  //$("#navDiv").height($(window).height())
		    	  $("#navDiv").show();
		      }
			 next()
		});
		function getHashParameter(key) {
			var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
			var index=window.location.href.indexOf("?");
			if(index!=-1){
				var searchStr=window.location.href.substr(index);
				var r = searchStr.substr(1).match(reg);
				if (r != null) {
					var x = unescape(r[2]);
					return decodeURI(x);
				} else {
					return null;
				}
			}
			
		}
		//获取服务商ID
		var supplierId=getHashParameter("spid");
		var casecode=getHashParameter("casecode");
		var source=getHashParameter("source");
		
		var siteInfo=null;
		var sourceInfo={supplierId:supplierId,casecode:casecode,source:source};
		
		siteCtl.invoke(ctlServiceStr,"fetchSiteInfo",[supplierId],function(d){
			 var app = new Vue({
					  el: '#kpMain',
					  router:router,
					  data: function(){
						  return{
							  logo:"",
							  loopImgs:[],
							  siteInfo:"",
							  sourceInfo:"",		  
						  }
					  },
					 created:function(){
						  console.log("root vue created...")
						  $("#navDiv").height($(window).height());
						  this.siteInfo=d;
						  this.sourceInfo=sourceInfo;
						  this.fetchData();
				          //判断url来源，如果是案件，那么就跳转到案件商标页面
				  		if(this.sourceInfo.source && this.sourceInfo.source!=""){
				  			 this.$router.push({path:"/tmc",query:{spid:sourceInfo.supplierId,source:sourceInfo.source,casecode:sourceInfo.casecode}})
				  			 }
					  },
					  methods:{
						  fetchData:function(){
							  var me=this;
							  this.logo=this.siteInfo.logoUrl;
				  			this.siteInfo.loopImgs.forEach(function(url){
				  				me.loopImgs.push(url);
				  			    });
						  }
					  }
				});
			
	    });

	}).catch(function(reason){
		console.log(reason)
	});
	
});


