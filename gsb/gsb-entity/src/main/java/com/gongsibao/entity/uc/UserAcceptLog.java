package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_user_accept_log",header="业务员接单日志")
public class UserAcceptLog extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1408535943976722927L;
	@Column(name="is_accept_orde",header="是否接单 0不接单 1接单")
    private Boolean isAcceptOrde;
	
    @Column(name="user_id")
    private Integer userId;
    
    @Reference(foreignKey="userId",header="业务员")
    private User user;
    
    @Column(name="operator_id")
    private Integer operatorId;
    
    @Reference(foreignKey="operatorId",header="操作员")
    private User operator;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getOperatorId() {
        return operatorId;
    }
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

	public Boolean getIsAcceptOrde() {
		return isAcceptOrde;
	}
	public void setIsAcceptOrde(Boolean isAcceptOrde) {
		this.isAcceptOrde = isAcceptOrde;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getOperator() {
		return operator;
	}
	public void setOperator(User operator) {
		this.operator = operator;
	}
    
}