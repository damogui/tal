package com.gongsibao.panda.supplier.crm;

import org.junit.Test;
import org.netsharp.base.IPersistableService;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.base.INCustomerCompanyService;
import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.crm.base.INCustomerProductService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.crm.base.INCustomerTaskInspectionService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerCompany;
import com.gongsibao.entity.crm.NCustomerOperationLog;
import com.gongsibao.entity.crm.NCustomerProduct;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskInspection;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.report.ComprehenReportEntity;
import com.gongsibao.entity.crm.report.FollowReportEntity;
import com.gongsibao.entity.crm.report.FunnelReportEntity;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.supplier.base.ISupplierService;

public class CrmResourceTest extends ResourceCreationBase{
	@Test
	public void run() {
		
		ResourceNode node = resourceNodeService.byCode("Gsb_Supplier_System");
		this.createResourceNodeVouchers(node);
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		node = this.createResourceNodeCategory("客户管理", "GSB_CRM", node.getId());
		
		ResourceNode node1 = null;


		node1 = this.createResourceNodeCategory("部门管理", "GSB_CRM_DEPARTMENT", node.getId());
		{
			this.createResourceNodeVoucher(NCustomer.class.getName(), "新增客户", "CRM_DEPARTMENT_CUSTOMER_ADD", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomer.class.getName(), "修改客户", "CRM_DEPARTMENT_CUSTOMER_Edit", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "新增商机", "CRM_DEPARTMENT_TASK_ADD", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "修改商机", "CRM_DEPARTMENT_TASK_EDIT", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskFoolow.class.getName(), "跟进", "CRM_DEPARTMENT_TASK_Foolow", INCustomerTaskFoolowService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(NCustomer.class.getName(), "全部客户", "CRM_DEPARTMENT_CUSTOMER_ALL", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "全部商机", "CRM_DEPARTMENT_TASK_ALL", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "未启动商机", "CRM_DEPARTMENT_TASK_START", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "跟进中商机", "CRM_DEPARTMENT_TASK_FOLLOWING", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "已签单商机", "CRM_DEPARTMENT_TASK_SIGNED", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "待跟进商机", "CRM_DEPARTMENT_TASK_UNFOOLOW", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "超时提醒", "CRM_DEPARTMENT_TASK_TIMEOUT", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "无法签单商机", "CRM_DEPARTMENT_TASK_DEFEATED", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "抽查异常", "CRM_DEPARTMENT_TASK_CHECK_ABNORMAL", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "公海", "CRM_DEPARTMENT_TASK_HIGHSEAS", INCustomerTaskService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(NCustomerTaskFoolow.class.getName(), "跟进列表", "CRM_DEPARTMENT_Task_Follow", INCustomerTaskFoolowService.class.getName(), node1.getId());

			//======选项卡加载项
			this.createResourceNodeVoucher(NCustomerCompany.class.getName(), "关联企业", "CRM_DEPARTMENT_Companys", INCustomerCompanyService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerProduct.class.getName(), "意向产品", "CRM_DEPARTMENT_Products", INCustomerProductService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskFoolow.class.getName(), "跟进日志", "CRM_DEPARTMENT_Foolow",INCustomerTaskFoolowService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskNotify.class.getName(), "通知日志", "CRM_DEPARTMENT_Notify",INCustomerTaskNotifyService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerOperationLog.class.getName(), "操作日志", "CRM_DEPARTMENT_Change",INCustomerOperationLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskInspection.class.getName(), "抽查日志", "CRM_DEPARTMENT_Inspection",INCustomerTaskInspectionService.class.getName(), node1.getId());
		}
		
		node1 = this.createResourceNodeCategory("统计分析", "GSB_CRM_STATISTICAL", node.getId());
		{
			this.createResourceNodeVoucher(ComprehenReportEntity.class.getName(), "综合统计", "CRM_STATISTICAL_COMPREHEN", IPersistableService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FunnelReportEntity.class.getName(), "漏斗统计", "CRM_STATISTICAL_Funnel", IPersistableService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FollowReportEntity.class.getName(), "跟进统计", "CRM_STATISTICAL_Follow", IPersistableService.class.getName(), node1.getId());
			//this.createResourceNodeVoucher(CustomerServiceReportEntity.class.getName(), "客服统计", "CRM_STATISTICAL_CustomerService", IPersistableService.class.getName(), node1.getId());
			
		}
		

		node1 = this.createResourceNodeCategory("我的商机", "GSB_CRM_MY", node.getId());
		{
			this.createResourceNodeVoucher(NCustomer.class.getName(), "新增客户", "CRM_SALESMAN_CUSTOMER_ADD", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomer.class.getName(), "修改客户", "CRM_SALESMAN_CUSTOMER_Edit", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "新增商机", "CRM_SALESMAN_TASK_ADD", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "修改商机", "CRM_SALESMAN_TASK_EDIT", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskFoolow.class.getName(), "跟进", "CRM_SALESMAN_TASK_Foolow", INCustomerTaskFoolowService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(NCustomer.class.getName(), "全部客户", "CRM_SALESMAN_CUSTOMER", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "全部商机", "CRM_SALESMAN_TASK_ALL", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "未启动商机", "CRM_SALESMAN_TASK_START", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "未跟进商机", "CRM_SALESMAN_TASK_UNFOOLOW", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "跟进中商机", "CRM_SALESMAN_TASK_FOLLOWING", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "已签单商机", "CRM_SALESMAN_TASK_SIGNED", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "超时提醒", "CRM_SALESMAN_TIMEOUT", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "无法签单商机", "CRM_SALESMAN_TASK_DEFEATED", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "跟进统计", "CRM_SALESMAN_REPORT", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "抽查异常", "CRM_SALESMAN_CHECK_ABNORMAL", INCustomerTaskService.class.getName(), node1.getId());

            this.createResourceNodeVoucher(NCustomerTaskFoolow.class.getName(), "我的跟进", "CRM_SALESMAN_Task_Follow", INCustomerTaskFoolowService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(NCustomer.class.getName(), "客户", "CRM_SALESMAN_REFERENE_NCUSTOMER", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Supplier.class.getName(), "分配服务商", "CRM_SALESMAN_REFERENE_SUPPLIER", ISupplierService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SupplierDepartment.class.getName(), "分配服务商部门", "CRM_SALESMAN_REFERENE_SUPPLIER_DEPART", ISupplierDepartmentService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Employee.class.getName(), "员工信息", "CRM_SALESMAN_REFERENE_EMPLOYEE", IEmployeeService.class.getName(), node1.getId());
			
			//======选项卡加载项
			this.createResourceNodeVoucher(NCustomerCompany.class.getName(), "关联企业", "CRM_SALESMAN_Companys", INCustomerCompanyService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerProduct.class.getName(), "意向产品", "CRM_SALESMAN_Products", INCustomerProductService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskFoolow.class.getName(), "跟进日志", "CRM_SALESMAN_Foolow",INCustomerTaskFoolowService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskNotify.class.getName(), "通知日志", "CRM_SALESMAN_Notify",INCustomerTaskNotifyService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerOperationLog.class.getName(), "操作日志", "CRM_SALESMAN_Change",INCustomerOperationLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskInspection.class.getName(), "抽查日志", "CRM_SALESMAN_Inspection",INCustomerTaskInspectionService.class.getName(), node1.getId());
			
		}
	}
}
