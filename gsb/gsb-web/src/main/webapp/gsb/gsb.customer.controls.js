System.Declare("com.gongsibao.controls");
com.gongsibao.controls.DictComboBox = org.netsharp.controls.ReferenceBox.Extends({
	ctor: function() {
		this.base();
	},
	getValue: function() {
		return $(this.uiElement).combobox('getValue');
	},
	getText: function() {
		return $(this.uiElement).combobox('getText').trim();
	},

	setValue: function(val) {

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
