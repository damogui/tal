package com.gongsibao.workbench.supplier;

import java.util.List;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.accordion.Accordion;
import org.netsharp.panda.controls.accordion.AccordionItem;
import org.netsharp.panda.controls.other.I;
import org.netsharp.panda.controls.other.Span;
import org.netsharp.panda.controls.tab.TabItem;
import org.netsharp.panda.controls.tree.JTreeNodeSerializor;
import org.netsharp.panda.controls.tree.Li;
import org.netsharp.panda.controls.tree.Tree;
import org.netsharp.panda.controls.tree.TreeNode;
import org.netsharp.panda.controls.tree.Ul;
import org.netsharp.panda.plugin.entity.PPads;
import org.netsharp.plugin.core.AddInTree;
import org.netsharp.util.StringManager;

public class SupplierLeftNavigation extends Ul {

	@Override
	public void initialize() {
		this.setClassName("left-nav");
		this.setId("nav");

		Li li = null;
		Span span = null;
		String padPath = "panda/workbench/pad";
		List<Object> items = AddInTree.buildItems(PPads.class, null, padPath, "pads");
		if (items != null && items.size() == 1) {

			TabItem tabItem = (TabItem) items.get(0);
			if (tabItem != null) {
				if (tabItem.getControls().size() == 1) {

					Accordion accordion = (Accordion) tabItem.getControls().get(0);
					if (accordion.getControls().size() == 0) {

						// BUG，没有权限时
						this.getControls().add(new I());
					} else {

						for (Control item : accordion.getControls()) {

							if (item != null) {

								AccordionItem accordionItem = (AccordionItem) item;
								if (accordionItem.getCode() == null || !accordionItem.getCode().equals("Gsb_Supplier_System")) {

									continue;
								}
								
								Tree tree = (Tree) accordionItem.getControls().get(0);
								List<TreeNode> nodeList = tree.nodes;
								for (TreeNode treeNode : nodeList) {
									
									li = new Li();
									{
										span = new Span();
										span.value = treeNode.text;
										
										li.onclick = "workbench.selectNav(this);";
										li.setId(treeNode.id);
										
										if(!StringManager.isNullOrEmpty(treeNode.iconCls)){

											I icon = new I();
											icon.setClassName(treeNode.iconCls);
											li.getControls().add(icon);
										}
										
										li.getControls().add(span);
										this.getControls().add(li);
										
										tree = new Tree();
										{
											tree.setName(treeNode.text);
											tree.nodes = treeNode.getChildren();
											JTreeNodeSerializor j = new JTreeNodeSerializor();
											String json = j.Serialize(tree);
											if (!StringManager.isNullOrEmpty(json)) {
												json = j.SubJson(json);
												li.getInnerValues().put("data", json);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		super.initialize();
	}
}
