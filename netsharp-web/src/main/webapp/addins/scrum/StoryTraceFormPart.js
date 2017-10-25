System.Declare("org.netsharp.scrum.web");

org.netsharp.scrum.web.StoryTraceFormPart = org.netsharp.panda.commerce.FormPart.Extends({
    //-----------------------
    // add
    //----------------------
    add: function () {

        var me = this;

        var url = window.location.href;
        
        var parentId = System.Url.getParameter("projectId");

        this.invokeService("newInstance", [parentId], function (jmessage) {
        	
        	me.currentItem = jmessage;
            me.viewModel.currentItem = me.currentItem;
            me.currentItem.entityState = EntityState.New;
            me.added(me.currentItem);
            
            if (me.currentItem == null) {

                me.viewModel.clear();

            }
            else {
                me.databind();
            }
        });
    }	
});
