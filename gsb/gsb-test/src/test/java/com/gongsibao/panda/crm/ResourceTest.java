package com.gongsibao.panda.crm;

import com.gongsibao.entity.supplier.*;
import com.gongsibao.supplier.base.*;

import org.junit.Before;
import org.netsharp.base.IPersistableService;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.base.ICompanyIntentionService;
import com.gongsibao.crm.base.ICustomerCompanyMapService;
import com.gongsibao.crm.base.ICustomerFollowService;
import com.gongsibao.crm.base.ICustomerOrderService;
import com.gongsibao.crm.base.ICustomerProdMapService;
import com.gongsibao.crm.base.ICustomerService;
import com.gongsibao.crm.base.ICustomerServiceConfigService;
import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.crm.base.INCustomerCompanyService;
import com.gongsibao.crm.base.INCustomerProductService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.crm.base.INCustomerTaskInspectionService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.crm.CustomerCompanyMap;
import com.gongsibao.entity.crm.CustomerFollow;
import com.gongsibao.entity.crm.CustomerOrder;
import com.gongsibao.entity.crm.CustomerProdMap;
import com.gongsibao.entity.crm.CustomerServiceConfig;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerOperationLog;
import com.gongsibao.entity.crm.NCustomerCompany;
import com.gongsibao.entity.crm.NCustomerProduct;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskInspection;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.trade.SoOrder;

public class ResourceTest extends ResourceCreationBase {

	@Before
	public void setup() {

		parentNodeName = "客户管理";
		parentNodeCode = "GSB_CRM";
		pluginName = "客户管理";
		seq = 3;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("客户管理", "GSB_CRM_Manager", node.getId());
		{
			this.createResourceNodeVoucher(Customer.class.getName(), "全部客户", "CRM_All_" + Customer.class.getSimpleName(), ICustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Customer.class.getName(), "我的客户", "CRM_My_" + Customer.class.getSimpleName(), ICustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Customer.class.getName(), "企业信息库", "CRM_Enterprise", ICustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Customer.class.getName(), "客户池", "CRM_Pool_" + Customer.class.getSimpleName(), ICustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Customer.class.getName(), "客户操作", "CRM_Operation_" + Customer.class.getSimpleName(), ICustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Dict.class.getName(), "城市", Dict.class.getSimpleName(), IPersistableService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerProdMap.class.getName(), "意向产品", CustomerProdMap.class.getSimpleName(), ICustomerProdMapService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Product.class.getName(), "产品", "CRM_" + Product.class.getSimpleName(), IPersistableService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerServiceConfig.class.getName(), "客服配置", "CRM_" + CustomerServiceConfig.class.getSimpleName(), ICustomerServiceConfigService.class.getName(),
					node1.getId());
			this.createResourceNodeVoucher(CompanyIntention.class.getName(), "企业信息", "CRM_" + CompanyIntention.class.getSimpleName(), ICompanyIntentionService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerCompanyMap.class.getName(), "关联企业", CustomerCompanyMap.class.getSimpleName(), ICustomerCompanyMapService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerFollow.class.getName(), "沟通日志", CustomerFollow.class.getSimpleName(), ICustomerFollowService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "订单记录", SoOrder.class.getSimpleName(), IPersistableService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(CustomerOrder.class.getName(), "订单列表", "CRM_" + CustomerOrder.class.getSimpleName(), ICustomerOrderService.class.getName(), node1.getId());
		}

		node1 = this.createResourceNodeCategory("我的任务", "GSB_CRM_MY", node.getId());
		{
			this.createResourceNodeVoucher(NCustomer.class.getName(), "新增客户", "CRM_SALESMAN_CUSTOMER_ADD", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomer.class.getName(), "修改客户", "CRM_SALESMAN_CUSTOMER_Edit", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "新增任务", "CRM_SALESMAN_TASK_ADD", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "修改任务", "CRM_SALESMAN_TASK_EDIT", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskFoolow.class.getName(), "跟进", "CRM_SALESMAN_TASK_Foolow", INCustomerTaskFoolowService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(NCustomer.class.getName(), "全部客户", "CRM_SALESMAN_CUSTOMER", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "全部任务", "CRM_SALESMAN_TASK_ALL", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "未启动任务", "CRM_SALESMAN_TASK_START", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "未跟进任务", "CRM_SALESMAN_TASK_UNFOOLOW", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "跟进中任务", "CRM_SALESMAN_TASK_FOLLOWING", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "已签单任务", "CRM_SALESMAN_TASK_SIGNED", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "超时提醒", "CRM_SALESMAN_TIMEOUT", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "无法签单任务", "CRM_SALESMAN_TASK_DEFEATED", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "跟进统计", "CRM_SALESMAN_REPORT", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "抽查异常", "CRM_SALESMAN_CHECK_ABNORMAL", INCustomerTaskService.class.getName(), node1.getId());

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

		node1 = this.createResourceNodeCategory("部门管理", "GSB_CRM_DEPARTMENT", node.getId());
		{
			this.createResourceNodeVoucher(NCustomer.class.getName(), "新增客户", "CRM_DEPARTMENT_CUSTOMER_ADD", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomer.class.getName(), "修改客户", "CRM_DEPARTMENT_CUSTOMER_Edit", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "新增任务", "CRM_DEPARTMENT_TASK_ADD", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "修改任务", "CRM_DEPARTMENT_TASK_EDIT", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskFoolow.class.getName(), "跟进", "CRM_DEPARTMENT_TASK_Foolow", INCustomerTaskFoolowService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(NCustomer.class.getName(), "全部客户", "CRM_DEPARTMENT_CUSTOMER_ALL", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "全部任务", "CRM_DEPARTMENT_TASK_ALL", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "未启动任务", "CRM_DEPARTMENT_TASK_START", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "跟进中任务", "CRM_DEPARTMENT_TASK_FOLLOWING", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "已签单任务", "CRM_DEPARTMENT_TASK_SIGNED", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "待跟进任务", "CRM_DEPARTMENT_TASK_UNFOOLOW", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "超时提醒", "CRM_DEPARTMENT_TASK_TIMEOUT", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "无法签单任务", "CRM_DEPARTMENT_TASK_DEFEATED", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "抽查异常", "CRM_DEPARTMENT_TASK_CHECK_ABNORMAL", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "公海", "CRM_DEPARTMENT_TASK_HIGHSEAS", INCustomerTaskService.class.getName(), node1.getId());

			//======选项卡加载项
			this.createResourceNodeVoucher(NCustomerCompany.class.getName(), "关联企业", "CRM_DEPARTMENT_Companys", INCustomerCompanyService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerProduct.class.getName(), "意向产品", "CRM_DEPARTMENT_Products", INCustomerProductService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskFoolow.class.getName(), "跟进日志", "CRM_DEPARTMENT_Foolow",INCustomerTaskFoolowService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskNotify.class.getName(), "通知日志", "CRM_DEPARTMENT_Notify",INCustomerTaskNotifyService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerOperationLog.class.getName(), "操作日志", "CRM_DEPARTMENT_Change",INCustomerOperationLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskInspection.class.getName(), "抽查日志", "CRM_DEPARTMENT_Inspection",INCustomerTaskInspectionService.class.getName(), node1.getId());
		}

		node1 = this.createResourceNodeCategory("系统设置", "GSB_CRM_SYS", node.getId());
		{
			this.createResourceNodeVoucher(SupplierDepartment.class.getName(), "部门列表", "GSB_CRM_SYS_DEPARTMENT", ISupplierDepartmentService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Salesman.class.getName(), "员工列表", "GSB_CRM_SYS_SALESMAN", ISalesmanService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(SalesmanRole.class.getName(), "添加角色", "GSB_CRM_SYS_SALESMAN_ADDROLE", ISalesmanRoleService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(SalesmanProduct.class.getName(), "服务范围", "GSB_CRM_SYS_SALESMAN_Product", ISalesmanProductService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(DepartmentProduct.class.getName(), "部门服务范围", "GSB_CRM_SYS_Department_Product", IDepartmentProductService.class.getName(), node1.getId());
		}
	}
}
