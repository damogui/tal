package com.gongsibao.panda.crm;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.crm.CustomerOrder;
import com.gongsibao.entity.crm.CustomerServiceConfig;
import com.gongsibao.entity.crm.ServiceFile;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "客户管理";
		this.treePath = "panda/gsb/crm";
		this.resourceNode = "GSB_CRM";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_CRM", "客户管理", "fa fa-users fa-fw", 3);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "fa fa-users fa-fw", "GSB_CRM_Manager", "客户管理", "", 1);
		{
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_All_" + Customer.class.getSimpleName(), "全部客户", "/crm/customer/all/list", 1);
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_My_" + Customer.class.getSimpleName(), "我的客户", "/crm/customer/my/list", 2);
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_" + CompanyIntention.class.getSimpleName(), "企业信息库", "/crm/company/list", 3);
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_Pool_" + Customer.class.getSimpleName(), "客户池", "/crm/customer/pool/list", 4);
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_Operation_" + Customer.class.getSimpleName(), "客户操作", "/crm/customer/operation/list", 5);
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_" + CustomerServiceConfig.class.getSimpleName(), "客服配置", "/crm/customer/service/config/list", 6);
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_" + CustomerOrder.class.getSimpleName(), "订单列表", "/crm/customer/order/list", 7);
		}
		
		createPTreeNode(tree, null, null, "GSB_Service_File", "服务商档案", "", 2);
		{
			createPTreeNode(tree, "GSB_Service_File", null, "Service_File_" + ServiceFile.class.getSimpleName(), "档案列表", "/crm/service/file/list", 1);
		}
	}
}
