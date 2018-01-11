package com.gongsibao.entity.supplier;

import java.util.List;

import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@Table(name="sp_function_module",header="功能模块")
public class FunctionModule extends BizEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 7671996201657364628L;

	@Subs(foreignKey="functionModuleId",header="对应角色",subType=FunctionModuleRole.class)
	private List<FunctionModuleRole> roles;

	public List<FunctionModuleRole> getRoles() {
		return roles;
	}

	public void setRoles(List<FunctionModuleRole> roles) {
		this.roles = roles;
	}
}
