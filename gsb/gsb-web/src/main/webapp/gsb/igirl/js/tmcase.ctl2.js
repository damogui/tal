//Vue.component("gsb-app",{
//	render:function(h){	
//		return h(
//				'div',{
//					class:{
//						"container-fluid": true,
//					}
//					
//				},[
//					h('nav',{
//						class:{
//							"navbar":true,
//							"nav-default":true
//						},[
//							this.$slots.default
//						]
//						
//					}),
//				
//				]
//		);
//	}
//});

cust=Vue.component("default",{
	render:function(h){	
		return h(
				'h'+this.level,
				{
					on:{
						'click':this.handleClick
					},
					attrs:{
						id:"he"
					}
				}
				,["hello"]
		)	
	},
	props:{
		level:{
			type:Number,
			required:true
		}
	},
	methods:{
		handleClick:function(e){
			alert(e)
		}
	}
});



Vue.component("Out",{
	render:function(h){	
		var that=this;
		return h(
				'div'
				,[			
					h(cust,{
						props:{
							level:this.level
						}
					}),
					this.$scopedSlots.default({
					      text: this.level
					 })
				]
		)	
	},
	props:["level"]
});

Vue.component("gsb-text",{
	render:function(h){
		return h("span","helloworld")
	}
});
/**
 * 自定义组件播放器
 */
Vue.component("gsb-content",{
	template:'<component :is="cview"></component>',
	props:[cview]
		
});

var app = new Vue({
	  el: '#TmCase',
	  data: {
	    mobile: "13381139519",
	    currentView:"gsb-text"
	     }
});