$(function(){
	//
	axios.get("http://192.168.43.207:3000/vue/comp/base").then(function(res){
	   console.log(res)
	   res.data.forEach(function(comp){
		      var obj=eval("("+comp+")");
	    	  Vue.component(obj.vname,obj);
	      });
	     
	}).catch(function(reason){
		console.log("ddd")
		console.log(reason)
	})
})

var tms={
		template:'<div>tm search</div>',
		created:function(){		
		}
}
var tm={
		template:'<gsb-nav></gsb-nav>',
		created:function(){
				
		}
}
var pt={template:'<div>patent</div>'}
var cr={template:'<div>copyright</div>'}
var def={
		template:'<div>defalut</div>',
		created:function(){
			//$("#content").height(0)
			
		}
}
//var c3=()=>Promise.resolve(new Promise(function(resolve,reject){
//	//异步加载组件
//	axios.get("http://192.168.43.207:3000/vue/comp").then(function(res){
//		console.log(res.data.template)
//		resolve(res.data);
//	}).catch(function(reason){
//		console.log(reason)
//	})
//	
//}))

//Vue.component('async-example', function (resolve, reject) {
//	  setTimeout(function () {
//	    // 将组件定义传入 resolve 回调函数
//	    resolve({
//	      template: '<div>I am async!</div>'
//	    })
//	  }, 1000)
//})

var routes=[
	{path:'/',component:def},
	{path:'/tms',component:tms},
	{path:'/tm',component:tm},
	{path:'/pt',component:pt},
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
			  spid:""
		  }
	  },
	  created:function(){
		    console.log("root vue created...")
		  me=this;
		  $("#navDiv").height($(window).height());
		  var spid=this.$router.history.current.query.spid;
		  me.spid=spid;
		  //获取当前站点的信息
  		siteCtl.invoke(ctlServiceStr,"fetchSiteInfo",[spid],function(d){
  			    console.log(d)
			      me.logo=d.logoUrl;
  			    d.loopImgs.forEach(function(url){
  			    	    console.log(url)
  			    me.loopImgs.push(url);
  			    });
			      
		   });
		 	  
	  }
});
