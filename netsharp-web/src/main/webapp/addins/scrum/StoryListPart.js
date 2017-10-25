System.Declare("org.netsharp.scrum.web");

org.netsharp.scrum.web.StoryListPart = org.netsharp.panda.commerce.ListPart.Extends({
	
	traces : function(){
		var item = this.getSelectedItem();
		if(item == null ){
			return;
		}
		
		var url = "/panda/scrum/trace/list?filter=storyId|"+item.id;
		window.open(url);
	},
	
	addtrace : function(){
		
		var item = this.getSelectedItem();
		if(item == null ){
			return;
		}
		
		var url = "/panda/scrum/trace/form?storyId="+item.id;
		window.open(url);
	},
});
