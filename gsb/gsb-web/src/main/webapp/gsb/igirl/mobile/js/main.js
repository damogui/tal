$(function(){
	var siteCtl=new org.netsharp.core.JServiceLocator()
	var ctlServiceStr="com.gongsibao.igirl.tm.web.SiteInfoListPart";
	var  hrefurl=window.location.href;
	if(window.location.href.indexOf("from=singlemessage#")!=-1){
		hrefurl=hrefurl.replace("from=singlemessage#","").replace("main.html?","main.html#");
	}
	if(window.location.href.indexOf("from=singlemessage&isappinstalled=0#")!=-1){
		hrefurl=hrefurl.replace("from=singlemessage&isappinstalled=0#","").replace("main.html?","main.html#");
	}
	function getHashParameter(key) {
		var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
		var index=hrefurl.indexOf("?");
		if(index!=-1){
			var searchStr=hrefurl.substr(index);
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
	var ip=d.webApiIp;
	var domain=d.domain;
	axios.get(ip+"/vue/comp/base").then(function(res){
		  //加载所有基础vue组件
	  // console.log(res)
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
				 //console.log(obj)
				 resolve(obj);
			}).catch(function(reason){
				console.log(reason)
			})
			
		}))
		var tmc=()=>Promise.resolve(new Promise(function(resolve,reject){
			//异步加载组件
			axios.get(ip+"/vue/comp/tmconfirm").then(function(res){	
				 var obj=eval("("+res.data+")");
				 //console.log(obj)
				 resolve(obj);
			}).catch(function(reason){
				console.log(reason)
			})
			
		}))
		var payment=()=>Promise.resolve(new Promise(function(resolve,reject){
			//异步加载组件
			axios.get(ip+"/vue/comp/payment").then(function(res){	
				 var obj=eval("("+res.data+")");
				 //console.log(obj)
				 resolve(obj);
			}).catch(function(reason){
				console.log(reason)
			})
			
		}))
		var zzty=()=>Promise.resolve(new Promise(function(resolve,reject){
			//异步加载组件
			axios.get(ip+"/vue/comp/zzty").then(function(res){	
				 var obj=eval("("+res.data+")");
				 //console.log(obj)
				 resolve(obj);
			}).catch(function(reason){
				console.log(reason)
			})
			
		}))
		var viewimg=()=>Promise.resolve(new Promise(function(resolve,reject){
			//异步加载组件
			axios.get(ip+"/vue/comp/viewimg").then(function(res){	
				 var obj=eval("("+res.data+")");
				 //console.log(obj)
				 resolve(obj);
			}).catch(function(reason){
				console.log(reason)
			})
			
		}))
	  var downdele=()=>Promise.resolve(new Promise(function(resolve,reject){
			//异步加载组件
			axios.get(ip+"/vue/comp/downdele").then(function(res){	
				 var obj=eval("("+res.data+")");
				 //console.log(obj)
				 resolve(obj);
			}).catch(function(reason){
				console.log(reason)
			})
			
		}))
	  var downup=()=>Promise.resolve(new Promise(function(resolve,reject){
			//异步加载组件
			axios.get(ip+"/vue/comp/downup").then(function(res){	
				 var obj=eval("("+res.data+")");
				 //console.log(obj)
				 resolve(obj);
			}).catch(function(reason){
				console.log(reason)
			})
			
		}))
        var noticeqr=()=>Promise.resolve(new Promise(function(resolve,reject){
            //异步加载组件
            axios.get(ip+"/vue/comp/noticeqr").then(function(res){
                var obj=eval("("+res.data+")");
                //console.log(obj)
                resolve(obj);
            }).catch(function(reason){
                console.log(reason)
            })

        }))
    var progresslist=()=>Promise.resolve(new Promise(function(resolve,reject){
            //异步加载组件
            axios.get(ip+"/vue/comp/progresslist").then(function(res){
                var obj=eval("("+res.data+")");
                //console.log(obj)
                resolve(obj);
            }).catch(function(reason){
                console.log(reason)
            })

        }))
     var attachments=()=>Promise.resolve(new Promise(function(resolve,reject){
            //异步加载组件
            axios.get(ip+"/vue/comp/attachments").then(function(res){
                var obj=eval("("+res.data+")");
                //console.log(obj)
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
			{path:'/payment',component:payment},//服务费用支付
			{path:'/zzty',component:zzty,},//营业执照和图样身份证
			{path:'/zzty/viewimg',component:viewimg},//营业执照和图样身份证
			{path:'/zzty/downdele',component:downdele},//下载委托书
			{path:'/zzty/downup',component:downup},//上传委托书
      {path:'/noticeqr',component:noticeqr},//提醒关注
      {path:'/progresslist',component:progresslist},//商标进度列表
      {path:'/attachments',component:attachments},//商标进度列表
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
		var app = new Vue({
					  el: '#kpMain',
					  router:router,
					  data: function(){
						  return{
							  logo:"",
							  loopImgs:[],
							  siteInfo:"",
							  sourceInfo:"",
							  caseinfo:null,
							  caseOrder:null,
							  ossconfig:null,
							  webApiIp:ip,
							  currentDomian:domain,
						  }
					  },
					 created:function(){
						  console.log("root vue created...");
	
						  $("#navDiv").height($(window).height());
						  this.siteInfo=d;
						  this.sourceInfo=sourceInfo;
						  this.fetchData();
				          //判断url来源，如果是案件，那么就跳转到案件商标页面
				  		if(this.sourceInfo.source && this.sourceInfo.source!=""){
				  			      //应该获取案件信息，根据状态来决定跳转到哪个页面
				  			      //如果是待确认或异议，那么调转到确认页面
				  			      //如果是已确认，那么跳专到付款页面
				  			      //如果是已经付款，那么跳专到上传营业执照和商标图样页面或身份证明...
				  			      //如果执照和图样已经上传，那么跳专到下载委托书页面
				  			      //如果是委托书已经上传那么就跳专到首页
				  			 var caseState=-1;
				  		
				  			 if(sourceInfo.casecode && sourceInfo.casecode!=""){
				  				 
				  				 var ctlServiceStr2="com.gongsibao.igirl.tm.web.TradeMarkCasePart";
				  				 var me=this;
				  				 siteCtl.invoke(ctlServiceStr2,"fetchUnconfirmedCaseInfoByCode",[sourceInfo.casecode],function(d){
				  					 me.caseinfo=d;
				  				
				  					 if(d.tmcState==0 || d.tmcState==1){//待确认
				  						 me.$router.push({path:"/tmc",query:{spid:sourceInfo.supplierId,source:sourceInfo.source,casecode:sourceInfo.casecode}});
				  					   return;
				  					      }
				  					 if(d.tmcState==2){//如果已经确认，但没有付款
				  						     //跳转到付款页面,付款成功，修改为3状态
				  						 me.$router.push({path:"/payment",query:{spid:sourceInfo.supplierId,source:sourceInfo.source,casecode:sourceInfo.casecode}});
				  						 return;
				  					      }
				  					 if(d.tmcState==3){
				  						    //如果已付款，经跳专到上传基本资料页面，点击下一步后，修改为4状态
				  					  	me.$router.push({path:"/zzty",query:{spid:sourceInfo.supplierId,source:sourceInfo.source,casecode:sourceInfo.casecode}});
				  						 return;
				  					      }
				  					 if(d.tmcState==4){
				  						    //下载委托书，跳专到上传委托书页面,点击下一步修改为5（已经上传状态），且跳专到
				  						me.$router.push({path:"/zzty/downdele",query:{spid:sourceInfo.supplierId,source:sourceInfo.source,casecode:sourceInfo.casecode}});
				  					  return;
				  					      }
				  					 if(d.tmcState==5){
				  						    //已经上传状态，进入微门户
				  						me.$router.push({path:"/",query:{spid:sourceInfo.supplierId}});
				  					      }
				  			    	  });
				  			      }
				  			 
				  			 }
					  },
					  methods:{
						  fetchData:function(){
							  var me=this;
							  this.logo=this.siteInfo.logoUrl;
//				  			this.siteInfo.loopImgs.forEach(function(url){
//				  				me.loopImgs.push(url);
//				  			    });
						  }
					  }
				});
			
		}).catch(function(reason){
			console.log(reason)
		});
    });//
	
});


