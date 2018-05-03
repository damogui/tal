
var c1={template:'<div>hello</div>'}
var c3=()=>Promise.resolve(new Promise(function(resolve,reject){
	//异步加载组件
	axios.get("http://192.168.10.109:3000/vue/comp").then(function(res){
		console.log(res.data.template)
		resolve(res.data);
	}).catch(function(reason){
		console.log(reason)
	})
}))

var routes=[
	{path:'/home',component:c1},
	{path:'/test',component:c3},
]
var router=new VueRouter({
	routes:routes,
//	mode:'history'
});
var app = new Vue({
	  el: '#TmCase',
	  router:router,
	  data: {
	    mobile: "13381139519",
	    currentView:"gsb-text"
	     }
});