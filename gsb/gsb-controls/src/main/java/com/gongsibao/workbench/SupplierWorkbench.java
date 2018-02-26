package com.gongsibao.workbench;

import org.netsharp.application.Application;
import org.netsharp.panda.controls.layout.LayoutPanel;
import org.netsharp.panda.controls.layout.LayoutRegion;
import org.netsharp.panda.controls.other.Body;
import org.netsharp.panda.controls.tab.Tab;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.Workspace;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.core.workbench.WorkbenchPadHost;

public class SupplierWorkbench extends Workspace{
	
	Boolean isTabs = true;// 是否多文档模式
	private String favicon = "/favicon.ico";

	@Override
	protected void doInitialize() {

		Body layout = this.body;
		layout.getInnerValues().put("isTabs", isTabs.toString());
		layout.setClassName("easyui-layout");
		layout.getControls().add(new SupplierWorkbenchHeader());
		layout.getControls().add(new WorkbenchPadHost());

		LayoutPanel center = new LayoutPanel();
		{
			center.setId("center1");
			center.region = LayoutRegion.Center;
		}

		if (isTabs) {
			Tab tab = new Tab();
			{
				tab.setId("tabs");
				tab.fit = true;
				tab.tabHeight = 35;
			}
			center.getControls().add(tab);
		}
		layout.getControls().add(center);
	}

	@Override
	public void initialize() {

		super.initialize();
		this.title = Application.getContext().getName();
	}

	@Override
	protected void importCss(IHtmlWriter writer) {
		
		super.importCss(writer);
		writer.write("    <link href='" + UrlHelper.getUrl("/panda-res/css/workbench.css") + "' rel='stylesheet' type='text/css' />");
	}

	@Override
	protected void importJs(IHtmlWriter writer) {

		super.importJs(writer);
		writer.write("    <link href='" + UrlHelper.getUrl(this.favicon) + "' rel='shortcut icon' type='image/x-icon' />");
		writer.write(UrlHelper.getVersionScript("/panda-res/js/workbench.js", false));
	}
}
