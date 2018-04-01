package com.gongsibao.workbench.supplier;

import org.netsharp.panda.controls.html5.Nav;
import org.netsharp.panda.controls.layout.LayoutPanel;
import org.netsharp.panda.controls.layout.LayoutRegion;
import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.core.workbench.RightNavigation;

public class SupplierWorkbenchHeader extends LayoutPanel{
	
	@Override
	public void initialize() {

		this.setId("header");
		this.split = false;
		this.region = LayoutRegion.Top;
		this.height = 50;

		Div logo = new Div();
		logo.setId("logo");
		this.getControls().add(logo);
		Nav nav = new Nav();
		{
			nav.className = "okayNav";
			nav.id = "nav-main";
			nav.innerValues.put("role", "navigation");
			nav.getControls().add(new SupplierLeftNavigation());
		}
		this.getControls().add(nav);
		this.getControls().add(new RightNavigation());
		super.initialize();
	}
}
