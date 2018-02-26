package com.gongsibao.panda.supplier.sys;

import org.junit.Test;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.supplier.DepartmentProduct;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.SalesmanProduct;
import com.gongsibao.entity.supplier.SalesmanRole;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.supplier.base.IDepartmentProductService;
import com.gongsibao.supplier.base.ISalesmanProductService;
import com.gongsibao.supplier.base.ISalesmanRoleService;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;

public class SysResourceTest extends ResourceCreationBase {
	@Test
	public void run() {

		ResourceNode node = resourceNodeService.byCode("Gsb_Supplier_System");
		this.createResourceNodeVouchers(node);
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = this.createResourceNodeCategory("系统设置", "GSB_CRM_SYS", node.getId());
		{
			node1 = this.createResourceNodeCategory("组织机构", "GSB_CRM_SYS_Organization", node1.getId());{

				this.createResourceNodeVoucher(SupplierDepartment.class.getName(), "部门列表", "GSB_CRM_SYS_DEPARTMENT", ISupplierDepartmentService.class.getName(), node1.getId());
				this.createResourceNodeVoucher(Salesman.class.getName(), "员工列表", "GSB_CRM_SYS_SALESMAN", ISalesmanService.class.getName(), node1.getId());
				this.createResourceNodeVoucher(SalesmanRole.class.getName(), "添加角色", "GSB_CRM_SYS_SALESMAN_ADDROLE", ISalesmanRoleService.class.getName(), node1.getId());
				this.createResourceNodeVoucher(SalesmanProduct.class.getName(), "业务员服务范围", "GSB_CRM_SYS_SALESMAN_Product", ISalesmanProductService.class.getName(), node1.getId());
				this.createResourceNodeVoucher(DepartmentProduct.class.getName(), "部门服务范围", "GSB_CRM_SYS_Department_Product", IDepartmentProductService.class.getName(), node1.getId());
			}
		}
	}
}
