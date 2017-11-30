package com.gongsibao.entity.er;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_user_auth")
public class UserAuth extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5257397305719826361L;
	@Column(name="user_id",header="UserId")
    private Integer userId;
    @Column(name="auth_id",header="AuthId")
    private Integer authId;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getAuthId() {
        return authId;
    }
    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
}