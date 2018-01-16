package com.gongsibao.entity.supplier;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Role;


@Table(name="sp_function_module_role",header="功能模块")
public class FunctionModuleRole extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7374870951855028336L;

	@Column(name="function_module_id")
	private Integer functionModuleId;
	
	@JsonIgnore
	@Reference(foreignKey="functionModuleId",header="功能模块")
	private FunctionModule functionModule;
	
	@Column(name="role_id")
	private Integer roleId;
	
	@Reference(foreignKey="roleId",header="角色")
	private Role role;

	public Integer getFunctionModuleId() {
		return functionModuleId;
	}

	public void setFunctionModuleId(Integer functionModuleId) {
		this.functionModuleId = functionModuleId;
	}

	public FunctionModule getFunctionModule() {
		return functionModule;
	}

	public void setFunctionModule(FunctionModule functionModule) {
		this.functionModule = functionModule;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
