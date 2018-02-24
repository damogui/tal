var ip="http://192.168.10.109:3000";
$(function(){
	axios.get(ip+"/vue/comp/base").then(function(res){
	   console.log(res)
	   res.data.forEach(function(comp){
		      var obj=eval("("+comp+")");
	    	  Vue.component(obj.vname,obj);
	      });
	     
	}).catch(function(reason){
		console.log(reason)
	});
});

var tms={
		template:'<div>tm search</div>',
		created:function(){		
		}
}
//var tm={
//		template:'<div class="container-fluid"><gsb-nav>商标智能申请</gsb-nav></div>',
//}
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
		template:'<div>defalut</div>',
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

var routes=[
	{path:'/',component:def},
	{path:'/tms',component:tms},
	{path:'/tm',component:tm},
	{path:'/tmc',component:tmc},//案件确认
	{path:'/pt',component:tmc},
	{path:'/cr',component:cr},
]
var router=new VueRouter({
	routes:routes,
	//mode:'history'
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
var siteCtl=new org.netsharp.core.JServiceLocator()
var ctlServiceStr="com.gongsibao.igirl.web.SiteInfoListPart";
var app = new Vue({
	  el: '#kpMain',
	  router:router,
	  data: function(){
		  return{
			  logo:"",
			  loopImgs:[],
			  spid:"",
			  source:"",
			  mobile:""
		  }
	  },
	  created:function(){
		  console.log("root vue created...")
		  me=this;
		  $("#navDiv").height($(window).height());
		  var spid=this.$router.history.current.query.spid;
		    console.log(this.$router.history.current.query)
		  me.spid=spid;
		  if(this.$router.history.current.query.source){
			     me.source=this.$router.history.current.query.source;
		    }
		  if(this.$router.history.current.query.mobile){
			     me.mobile=this.$router.history.current.query.mobile;
		    }
		  //获取当前站点的信息
//  		siteCtl.invoke(ctlServiceStr,"fetchSiteInfo",[spid],function(d){
//  			    console.log(d)
//			      me.logo=d.logoUrl;
//  			    d.loopImgs.forEach(function(url){
//  			    	    console.log(url)
//  			          me.loopImgs.push(url);
//  			           });
//  			          //判断url来源，如果是案件，那么就跳转到案件商标页面
//  			   if(me.source!=""){
//  				         me.$router.push({path:"/tmc",query:{spid:me.spid,mobile:me.mobile,source:me.source}})
//  			           }
//			      
//		   });
		   this.fetchData();
	  },
	  methods:{
		  fetchData:function(){
			    me=this;
		  		siteCtl.invoke(ctlServiceStr,"fetchSiteInfo",[me.spid],function(d){
	  			    console.log(d)
				      me.logo=d.logoUrl;
	  			    d.loopImgs.forEach(function(url){
	  			    	    console.log(url)
	  			          me.loopImgs.push(url);
	  			           });
	  			          //判断url来源，如果是案件，那么就跳转到案件商标页面
	  			   if(me.source!=""){
	  				         me.$router.push({path:"/tmc",query:{spid:me.spid,mobile:me.mobile,source:me.source}})
	  			           }
				      
			   });
		  }
	  }
});
