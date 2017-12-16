package com.gongsibao.panda.franchisee;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.entity.franchisee.FranchiseeBusinessScope;
import com.gongsibao.entity.franchisee.FranchiseeLinkman;
import com.gongsibao.entity.franchisee.FranchiseeReport;
import com.gongsibao.entity.franchisee.FranchiseeTrack;
import com.gongsibao.franchisee.base.IFranchiseeBusinessScopeService;
import com.gongsibao.franchisee.base.IFranchiseeLinkmanService;
import com.gongsibao.franchisee.base.IFranchiseeReportService;
import com.gongsibao.franchisee.base.IFranchiseeService;
import com.gongsibao.franchisee.base.IFranchiseeTrackService;

public class ResourceTest extends ResourceCreationBase {

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	@Before
	public void setup() {

		parentNodeName = "招商CRM";
		parentNodeCode = "GSB_BD";
		pluginName = "招商CRM";
		seq = 3;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("供应商管理", "GSB_BD_MY", node.getId());
		{
			this.createResourceNodeVoucher(Franchisee.class.getName(), "待跟进", "BD_MY_UN_TRACK", IFranchiseeService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Franchisee.class.getName(), "供应商列表", "BD_MY_MY", IFranchiseeService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Franchisee.class.getName(), "工作台", "BD_WORKBENCH", IFranchiseeService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FranchiseeLinkman.class.getName(), "联系人信息", FranchiseeLinkman.class.getSimpleName(), IFranchiseeLinkmanService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FranchiseeTrack.class.getName(), "跟进信息", FranchiseeTrack.class.getSimpleName(), IFranchiseeTrackService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FranchiseeBusinessScope.class.getName(), "经营范围", FranchiseeBusinessScope.class.getSimpleName(), IFranchiseeBusinessScopeService.class.getName(), node1.getId());
		}
		
		node1 = this.createResourceNodeCategory("部门统计", "GSB_BD_DEPARTMENT", node.getId());
		{
			this.createResourceNodeVoucher(Franchisee.class.getName(), "供应商列表", "BD_DEPARTMENT_Franchisee", IFranchiseeService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Franchisee.class.getName(), "未跟进供应商", "BD_DEPARTMENT_Franchisee_UnTrack", IFranchiseeService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FranchiseeTrack.class.getName(), "跟进列表", "BD_DEPARTMENT_Franchisee_Track" , IFranchiseeTrackService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FranchiseeTrack.class.getName(), "跟进统计", "BD_DEPARTMENT_Franchisee_Track_Report", IFranchiseeTrackService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FranchiseeReport.class.getName(), "日统计", "BD_DEPARTMENT_Day_Report" , IFranchiseeReportService.class.getName(), node1.getId());			
			this.createResourceNodeVoucher(FranchiseeReport.class.getName(), "月统计", "BD_DEPARTMENT_Month_Report", IFranchiseeReportService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FranchiseeReport.class.getName(), "年统计", "BD_DEPARTMENT_Year_Report", IFranchiseeReportService.class.getName(), node1.getId());
		}
		
		node1 = this.createResourceNodeCategory("运营统计", "GSB_BD_OPERATION", node.getId());
		{
			this.createResourceNodeVoucher(Franchisee.class.getName(), "供应商列表", "GSB_BD_OPERATION_Franchisee", IFranchiseeService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Franchisee.class.getName(), "未跟进供应商", "GSB_BD_OPERATION_UnTrack", IFranchiseeService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FranchiseeTrack.class.getName(), "跟进列表", "GSB_BD_OPERATION_Track", IFranchiseeTrackService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FranchiseeTrack.class.getName(), "跟进统计", "GSB_BD_OPERATION_Track_Report", IFranchiseeTrackService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FranchiseeReport.class.getName(), "日统计", "GSB_BD_OPERATION_Day_Report", IFranchiseeReportService.class.getName(), node1.getId());			
			this.createResourceNodeVoucher(FranchiseeReport.class.getName(), "月统计", "GSB_BD_OPERATION_Month_Report", IFranchiseeReportService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FranchiseeReport.class.getName(), "年统计", "GSB_BD_OPERATION_Year_Report", IFranchiseeReportService.class.getName(), node1.getId());
		}
	}
}






