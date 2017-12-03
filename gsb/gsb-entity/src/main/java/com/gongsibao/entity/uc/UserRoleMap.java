package com.gongsibao.entity.uc;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_user_role_map",header="员工角色")
public class UserRoleMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4537425025070343152L;
	@Column(name="user_id")
    private Integer userId;
	
    @JsonIgnore
    @Reference(foreignKey="userId")
    private User user;
    
    @Column(name="role_id")
    private Integer roleId;
    
    @Reference(foreignKey="roleId")
    private Role role;
    
    @Column(name="can_pass",header="能否传递 0否 1是")
    private Boolean canPass = true;
    
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Boolean getCanPass() {
		return canPass;
	}
	public void setCanPass(Boolean canPass) {
		this.canPass = canPass;
	}
	
	
}