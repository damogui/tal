package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_role_auth_map")
public class RoleAuthMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -3658273068667734452L;
	
	@Column(name="role_id",header="角色Id")
    private Integer roleId;
	
    @Reference(foreignKey="roleId")
    private Role role;
    
    @Column(name="auth_id",header="权限Id")
    private Integer authId;
    
    @Reference(foreignKey="authId")
    private Auth auth;

    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getAuthId() {
        return authId;
    }
    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Auth getAuth() {
		return auth;
	}
	public void setAuth(Auth auth) {
		this.auth = auth;
	}
}