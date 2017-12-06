package com.gongsibao.part;

import java.util.List;

import org.netsharp.panda.commerce.TreePart;
import org.netsharp.panda.controls.tree.TreeNode;

import com.gongsibao.json.CustomTreeResultJson;

public class CustomTreePart extends TreePart{

	protected List<TreeNode> serialize(List<?> rows){
		
		String entityId = this.context.getEntityId();
		CustomTreeResultJson json = new CustomTreeResultJson(rows, entityId);
		List<TreeNode> nodes = json.parse();
		return nodes;
	}
}
