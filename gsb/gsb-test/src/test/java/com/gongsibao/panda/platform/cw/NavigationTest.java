package com.gongsibao.panda.platform.cw;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

/**
 * 财务报销
*    
* 项目名称：gsb-test   
* 类名称：NavigationTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 上午11:26:30   
* @version
 */
public class NavigationTest extends NavigationBase {

	// 初始化导航信息
	@Before
	public void setup() {
		this.treeName = "财务报销";
		this.treePath = "panda/gsb/cw";
		this.resourceNode = ResourceTest.resourcePrefix;
	}

	public void createAccodions() {

		this.doCreateAccodions(ResourceTest.resourcePrefix, "财务报销", "fa fa-recycle fa-fw", 1);
	}

	// 创建菜单树节点
	@Override
	protected void doCreateTree(PNavigation tree) {

		String parentNodeCode = ResourceTest.resourcePrefix + "_Manage";
		createPTreeNode(tree, null, "fa fa-file-text-o fa-fw", parentNodeCode, "财务审批", "", 1);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Todo_Bills", "我的待办", "/cw/bill/todo/list", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Done_Bills", "我的已办", "/cw/bill/done/list", 2);
			//createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_CC_Bills", "抄送我的", "/cw/bill/cc/list", 3);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Finance_Bills", "财务办理", "/cw/bill/finance/list", 4);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_All_Bills", "单据查询", "/cw/bill/all/list", 5);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Loan_Bill", "借款单", "/cw/bill/loan/list", 6);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Expense_Bill", "报销单", "/cw/bill/expense/list", 7);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Payment_Bill", "付款单", "/cw/bill/payment/list", 8);
			//createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Allocation_Bill", "调拨单", "/cw/bill/allocation/list", 9);
			
		}
	

	}
}