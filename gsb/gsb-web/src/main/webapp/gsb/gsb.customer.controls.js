System.Declare("com.gongsibao.controls");
com.gongsibao.controls.DictComboBox = org.netsharp.controls.ReferenceBox.Extends({
	ctor: function() {
		this.base();
	},
	getValue: function() {
		
		var val = $(this.uiElement).combobox('getValue');
	    if(System.isnull(val)){
	    	val = 0;
	    }
		return val;
	},
	getText: function() {
		return $(this.uiElement).combobox('getText').trim();
	},

	setValue: function(val) {

		val=val<=0?null:val;
		$(this.uiElement).combobox('setValue', val);
	},

	setText: function(text) {

		//$(this.uiElement).combobox('setText', text);
	},
	disable: function() {
		$(this.uiElement).combobox("disable");
	},
	enable: function() {
		var _disabled = $(this.uiElement).attr("_disabled");
		if (_disabled) {
			return;
		}
		$(this.uiElement).combobox("enable");
	}
});
