package org.netsharp.panda.controls.other;

import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;

@HtmlNode(html="label", isValue=true)
public class Label extends Control
{
	public Label(){}
	
	public Label(String value){
		this.value=value;
	}
    public String value;
}
