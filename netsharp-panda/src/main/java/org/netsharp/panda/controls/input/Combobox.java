package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;

@HtmlNode(html = "select")
public class Combobox extends Input {

	@DataOption(html = "onChange", isEvent = true)
	public String onChange;
	
	@HtmlAttr(html = "defaultValue")
	public String defaultValue;

	@HtmlAttr(html = "multiple")
	public Boolean multiple;

	@DataOption(html = "valueField")
	public String valueField;
	
	@DataOption(html = "textField")
	public String textField;
	
	@DataOption(html = "width")
	public int width;

	@DataOption(html = "editable")
	public Boolean editable;
	
    @DataOption(html="validateOnCreate")
    public boolean validateOnCreate = false;
    
    @DataOption(html="validateOnBlur")
    public boolean validateOnBlur = true;
    
	public Combobox() {
		super();
		this.setClassName("easyui-combobox");
	}
}
