package com.gongsibao.workbench.supplier;

import org.netsharp.panda.controls.layout.LayoutPanel;
import org.netsharp.panda.controls.layout.LayoutRegion;

public class SupplierWorkbenchPadHost extends LayoutPanel{
	
    public static String padPath = "panda/workbench/pad";
    
    @Override
    public void initialize()
    {
    	this.width = 180;
        this.split = false;
        this.collapsible=true;
        this.region = LayoutRegion.Left;
        this.setId("west");
        super.initialize();
    }
}
