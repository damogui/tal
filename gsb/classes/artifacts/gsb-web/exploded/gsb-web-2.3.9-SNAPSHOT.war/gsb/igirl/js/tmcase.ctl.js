
var c1={template:'<div>hello</div>'}
var c2={template:'<div>world</div>'}
var routes=[
	{path:'/home',component:c1},
	{path:'/test',component:c2},
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