package com.gongsibao.panda.auth.authorization;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IAuthorizationPrincipalService;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.base.IPrincipalOperationService;
import org.netsharp.organization.base.IRoleService;
import org.netsharp.organization.dic.PrincipalType;
import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.PrincipalOperation;
import org.netsharp.organization.entity.Role;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

public class AuthBaseTest {

	IRoleService roleService = ServiceFactory.create(IRoleService.class);

	IOperationService operationService = ServiceFactory.create(IOperationService.class);

	IResourceNodeService resourceService = ServiceFactory.create(IResourceNodeService.class);

	IAuthorizationPrincipalService apService = ServiceFactory.create(IAuthorizationPrincipalService.class);

	IPrincipalOperationService principalOperationService = ServiceFactory.create(IPrincipalOperationService.class);

	// GSB_CRM_SYS
	// GSB_CRM
	// Gsb_Supplier_Order
	// GSB_TRADE_AI

	protected String roleCode = null;
	
	protected Integer principalId = null;
	
	protected List<String> resourceNodeCodeList = new ArrayList<String>();

	@Before
	public void setup() {

		AuthorizationPrincipal aPrincipal = this.getAuthorizationPrincipal(roleCode);
		if(aPrincipal != null){
			
			principalId = aPrincipal.getId();
		}
		
		getResourceCodeList();
	}
	
	@Test
	public void run(){
		
		for (String code : resourceNodeCodeList) {

			this.doAuthorization(code);
		}
	}
	
	protected void getResourceCodeList(){
		
		
	}
	
	protected void doAuthorization(String resourceNodeCode){
		
		List<ResourceNode> rnList = querySubResourceNode(resourceNodeCode);
		for (ResourceNode rn : rnList) {

			List<Operation> operationList = queryOperationList(rn.getId());
			for (Operation operation : operationList) {

				this.addPrincipalOperation(principalId, operation, rn.getCode());
			}
		}
	}

	/**
	 * @Title: querySubResourceNode
	 * @Description: TODO(根据资源编码获取所有子资源)
	 * @param: @param resourceNodeCode
	 * @param: @return
	 * @return: List<ResourceNode>
	 * @throws
	 */
	protected List<ResourceNode> querySubResourceNode(String resourceNodeCode) {

		ResourceNode parentNode = resourceService.byCode(resourceNodeCode);
		if (parentNode == null) {

			System.err.println("资源节点：" + resourceNodeCode + "不存在");
		}

		List<ResourceNode> allList = this.queryResourceNode(parentNode.getPathCode());
		return allList;
	}

	protected List<ResourceNode> queryResourceNode(String pathCode) {

		Oql oql = new Oql();
		{
			oql.setType(ResourceNode.class);
			oql.setSelects("*");
			oql.setFilter("path_code like '%" + pathCode + "%'");
		}

		List<ResourceNode> list = resourceService.queryList(oql);
		return list;
	}

	/**
	 * @Title: addPrincipalOperation
	 * @Description: TODO(授权)
	 * @param: @param principalId
	 * @param: @param operation
	 * @param: @param resourcenodeCode
	 * @return: void
	 * @throws
	 */
	protected void addPrincipalOperation(Integer principalId, Operation operation, String resourcenodeCode) {

		PrincipalOperation pOperation = this.getPrincipalOperation(principalId, operation.getId(), resourcenodeCode);
		if (pOperation == null) {

			pOperation = new PrincipalOperation();
			pOperation.toNew();
			pOperation.setPrincipalId(principalId);
			pOperation.setOperationId(operation.getId());
			pOperation.setOperationCode(operation.getCode());
			pOperation.setResourcenodeCode(resourcenodeCode);
			principalOperationService.save(pOperation);
		}
	}

	protected PrincipalOperation getPrincipalOperation(Integer principalId, Integer operationId, String resourcenodeCode) {

		Oql oql = new Oql();
		{
			oql.setType(PrincipalOperation.class);
			oql.setSelects("*");
			oql.setFilter("principalId=? and operationId=? and resourcenodeCode=?");
			oql.getParameters().add("@principalId", principalId, Types.INTEGER);
			oql.getParameters().add("@principalId", operationId, Types.INTEGER);
			oql.getParameters().add("@resourcenodeCode", resourcenodeCode, Types.VARCHAR);
		}
		PrincipalOperation po = principalOperationService.queryFirst(oql);
		return po;
	}

	/**
	 * @Title: getOperation
	 * @Description: TODO(获取授权主体)
	 * @param: @param roleCode
	 * @param: @return
	 * @return: AuthorizationPrincipal
	 * @throws
	 */
	protected AuthorizationPrincipal getAuthorizationPrincipal(String roleCode) {

		Role role = this.getRoleByCode(roleCode);
		if (role == null) {

			System.err.println("角色：" + roleCode + "不存在");
		}
		Oql oql = new Oql();
		{
			oql.setType(AuthorizationPrincipal.class);
			oql.setSelects("*");
			oql.setFilter("principalType=? and principalId=?");
			oql.getParameters().add("@principalType", PrincipalType.Role.getValue(), Types.INTEGER);
			oql.getParameters().add("@principalId", role.getId(), Types.INTEGER);
		}
		AuthorizationPrincipal ap = apService.queryFirst(oql);
		return ap;
	}

	protected Role getRoleByCode(String code) {

		Oql oql = new Oql();
		{
			oql.setType(Role.class);
			oql.setSelects("*");
			oql.setFilter("code=?");
			oql.getParameters().add("@code", code, Types.VARCHAR);
		}

		Role role = roleService.queryFirst(oql);
		return role;
	}

	/**
	 * @Title: queryOperationList
	 * @Description: TODO(根据资源节点Id查询操作集合)
	 * @param: @param resourceNodeId
	 * @param: @return
	 * @return: List<Operation>
	 * @throws
	 */
	protected List<Operation> queryOperationList(Integer resourceNodeId) {

		Oql oql = new Oql();
		{
			oql.setType(Operation.class);
			oql.setSelects("*");
			oql.setFilter("resourceNodeId=?");
			oql.getParameters().add("@resourceNodeId", resourceNodeId, Types.INTEGER);
		}

		List<Operation> list = operationService.queryList(oql);
		return list;
	}
}
