package com.gongsibao.json;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.panda.controls.tree.TreeNode;
import org.netsharp.panda.controls.tree.TreeNodeState;

import com.gongsibao.entity.BaseCatEntity;

public class CustomTreeResultJson {

	private List<?> items = null;
	private List<TreeNode> nodes = new ArrayList<TreeNode>();

	public CustomTreeResultJson(List<?> items, String entityId) {

		this.items = items;
	}	

	public List<TreeNode> parse() {

		for (Object obj : this.items) {

			BaseCatEntity cat = (BaseCatEntity) obj;
			TreeNode node = this.convert(cat);
			this.nodes.add(node);
		}

		return this.nodes;
	}

	private TreeNode convert(BaseCatEntity cat) {

		TreeNode node = new TreeNode();
		node.id = cat.getId().toString();
		node.text = cat.getName();

		if (cat.getIsLeaf()) {
			node.state = TreeNodeState.open;
		} else {
			node.state = TreeNodeState.closed;
		}

		return node;
	}
}
