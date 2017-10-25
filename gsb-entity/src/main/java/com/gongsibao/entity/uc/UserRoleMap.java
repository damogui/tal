package com.gongsibao.entity.uc;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_user_role_map")
public class UserRoleMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4537425025070343152L;
	@Column(name="user_id")
    private Integer userId;
    @Column(name="role_id")
    private Integer roleId;
    @Column(name="can_pass")
    private Integer canPass;
    @Column(name="add_time")
    private Date addTime;
    @Column(name="add_user_id")
    private Integer addUserId;

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
    public Integer getCanPass() {
        return canPass;
    }
    public void setCanPass(Integer canPass) {
        this.canPass = canPass;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
}